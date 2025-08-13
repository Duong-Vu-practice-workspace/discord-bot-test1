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
import vn.edu.ptit.duongvct.discord_bot_test1.util.CommonUtil;

@Component
@AllArgsConstructor
public class EditResourceCommand implements SlashCommand {
    private LearningResourceService learningResourceService;
    @Override
    public String getName() {
        return SlashCommandCommon.EDIT_RESOURCE_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            User user = event.getInteraction().getUser();
            String resourceId = event.getOption(LearningResourceCommandCommon.RESOURCE_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            LearningResource learningResource = this.learningResourceService.findLearningResourceById(resourceId);
            if (learningResource == null) {
                throw new IllegalArgumentException("Learning resource not found");
            }
            String name = event.getOption(LearningResourceCommandCommon.NAME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
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
                    .orElse("");
            String topicId = event.getOption(LearningResourceCommandCommon.TOPIC_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            if (CommonUtil.validateString(typeStr)) {
                ResourceType type = ResourceType.valueOf(typeStr);
                learningResource.setType(type);
            }
            if (CommonUtil.validateString(name)) {
                learningResource.setName(name);
            }
            if (CommonUtil.validateString(description)) {
                learningResource.setDescription(description);
            }
            if (CommonUtil.validateString(link)) {
                learningResource.setLink(link);
            }
            if (CommonUtil.validateString(courseId)) {
                learningResource.setCourseId(courseId);
            }
            if (CommonUtil.validateString(topicId)) {
                learningResource.setTopicId(topicId);
            }

            return learningResourceService.updateLearningResource(learningResource);

        }).flatMap(msg -> event.reply()
                .withEphemeral(false)
                .withContent("Resource updated: " + msg));
    }
}
