package vn.edu.ptit.duongvct.discord_bot_test1.commands.topic;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.topic.TopicCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Topic;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.TopicRepository;
import vn.edu.ptit.duongvct.discord_bot_test1.service.TopicService;

@Component
@AllArgsConstructor
public class EditTopicCommand implements SlashCommand {
    private final TopicService topicService;

    @Override
    public String getName() {
        return SlashCommandCommon.EDIT_TOPIC_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String topicId = event.getOption(TopicCommandCommon.TOPIC_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            Topic topic = topicService.findTopicById(topicId);
            if (topic == null) {
                throw new IllegalArgumentException("Topic not found.");
            }
            String newName = event.getOption(TopicCommandCommon.NAME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse(null);
            String newDescription = event.getOption(TopicCommandCommon.DESCRIPTION_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse(null);
            String newParentId = event.getOption(TopicCommandCommon.PARENT_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse(null);

            if (newName != null && !newName.isBlank()) {
                topic.setName(newName);
            }
            if (newDescription != null && !newDescription.isBlank()) {
                topic.setDescription(newDescription);
            }

            if (newParentId != null && !newParentId.isBlank()) {
                if (newParentId.equals(topic.getId())) {
                    throw new IllegalArgumentException("Parent topic cannot be the same as the topic itself.");
                }
                topic.setParentId(newParentId);
            }
            return topicService.editTopic(topic);
        }).flatMap(msg -> event.reply().withEphemeral(true).withContent("Topic edited " + msg));
    }
}