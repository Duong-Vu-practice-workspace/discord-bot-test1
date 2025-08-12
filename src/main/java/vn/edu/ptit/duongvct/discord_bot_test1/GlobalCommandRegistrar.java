package vn.edu.ptit.duongvct.discord_bot_test1;

import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import vn.edu.ptit.duongvct.discord_bot_test1.command_request.course.CreateCourseCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.command_request.event.CreateEventCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.command_request.learning_resource.CreateResourceCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.command_request.topic.CreateTopicCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.service.TopicService;


import java.util.Arrays;
import java.util.List;

import static vn.edu.ptit.duongvct.discord_bot_test1.command_request.basic.GetAvatarCommandRequest.GET_AVATAR_COMMAND_REQUEST;
import static vn.edu.ptit.duongvct.discord_bot_test1.command_request.basic.GreetCommandRequest.GREET_COMMAND_REQUEST;
import static vn.edu.ptit.duongvct.discord_bot_test1.command_request.basic.PingCommandRequest.PING_COMMAND_REQUEST;

@Component
@AllArgsConstructor
@Slf4j
public class GlobalCommandRegistrar implements ApplicationRunner {
    private final RestClient client;
    private final TopicService topicService;

    @Override
    public void run(ApplicationArguments args) {
        final ApplicationService applicationService = client.getApplicationService();
        final long applicationId = client.getApplicationId().block();
        // Define your commands here
        List<ApplicationCommandRequest> commands = Arrays.asList(
                GREET_COMMAND_REQUEST(),
                PING_COMMAND_REQUEST(),
                GET_AVATAR_COMMAND_REQUEST(),
                CreateTopicCommandRequest.CREATE_TOPIC_COMMAND_REQUEST(),
                CreateEventCommandRequest.CREATE_EVENT_COMMAND_REQUEST(),
                CreateCourseCommandRequest.CREATE_COURSE_COMMAND_REQUEST(),
                CreateResourceCommandRequest.CREATE_RESOURCE_COMMAND_REQUEST()
        );

        applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, commands)
                .doOnNext(ignore -> log.debug("Successfully registered Global Commands"))
                .doOnError(e -> log.error("Failed to register global commands", e))
                .subscribe();
    }
}