package vn.edu.ptit.duongvct.discord_bot_test1.commands.learning_resource;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.resource.LearningResourceCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.constants.ResourceType;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.LearningResource;
import vn.edu.ptit.duongvct.discord_bot_test1.service.LearningResourceService;

@Component
@AllArgsConstructor
public class CreateResourceCommand implements SlashCommand {
    private final LearningResourceService learningResourceService;

    @Override
    public String getName() {
        return SlashCommandCommon.CREATE_RESOURCE_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            User user = event.getInteraction().getUser();
            String name = event.getOption(LearningResourceCommandCommon.NAME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("Unnamed");
            String description = event.getOption(LearningResourceCommandCommon.DESCRIPTION_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String link = event.getOption(LearningResourceCommandCommon.LINK_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String courseId = event.getOption(LearningResourceCommandCommon.COURSE_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String typeStr = event.getOption(LearningResourceCommandCommon.TYPE_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("LEARNING");
            String topicId = event.getOption(LearningResourceCommandCommon.TOPIC_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            ResourceType type = ResourceType.valueOf(typeStr);

            LearningResource resource = LearningResource.builder()
                    .name(name)
                    .description(description)
                    .link(link)
                    .courseId(courseId)
                    .topicId(topicId)
                    .userId(user.getId().asString())
                    .type(type)
                    .build();

            return learningResourceService.createLearningResource(resource);

        }).flatMap(msg -> event.reply()
                .withEphemeral(false)
                .withContent("Resource created: " + msg));

    }
}