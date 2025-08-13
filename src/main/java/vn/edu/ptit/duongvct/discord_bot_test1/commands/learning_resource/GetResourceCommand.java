package vn.edu.ptit.duongvct.discord_bot_test1.commands.learning_resource;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateSpec;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.resource.LearningResourceCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Course;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.LearningResource;
import vn.edu.ptit.duongvct.discord_bot_test1.service.CourseService;
import vn.edu.ptit.duongvct.discord_bot_test1.service.LearningResourceService;
import vn.edu.ptit.duongvct.discord_bot_test1.service.TopicService;
import vn.edu.ptit.duongvct.discord_bot_test1.util.CommonUtil;

@Component
@AllArgsConstructor
public class GetResourceCommand implements SlashCommand {
    private final LearningResourceService learningResourceService;
    private final CourseService courseService;
    private final TopicService topicService;
    @Override
    public String getName() {
        return SlashCommandCommon.GET_RESOURCE_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String resourceId = event.getOption(LearningResourceCommandCommon.RESOURCE_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            LearningResource learningResource = this.learningResourceService.findLearningResourceById(resourceId);
            if (learningResource == null) {
                throw new IllegalArgumentException("Resource not found");
            }
            String name = CommonUtil.getStringNullSafety(learningResource.getName());
            String description = CommonUtil.getStringNullSafety(learningResource.getDescription());
            String link = CommonUtil.getStringNullSafety(learningResource.getLink());
            Course course = this.courseService.findCourseById(learningResource.getCourseId());;
            if (course == null) {
                throw new RuntimeException("Invalid course");
            }
            String courseName = course.getName();
            String topicName = this.topicService.convertTopicNameToMeaningfulName(learningResource.getTopicId());
            String type = learningResource.getType().toString();
            return EmbedCreateSpec.builder()
                    .title("Resource information")
                    .addField("ID", resourceId, true)
                    .addField("Name ", name, false)
                    .addField("Description", description, false)
                    .addField("Link", link, false)
                    .addField("Course ", courseName, false)
                    .addField("Topic", topicName, false)
                    .addField("Type", type, false)
                    .build();
        }).flatMap(embed -> {
            if (embed == null) {
                return event.reply().withEphemeral(true).withContent("Learning resource not found.");
            }
            return event.reply().withEphemeral(true).withEmbeds(embed);
        });
    }
}
