package vn.edu.ptit.duongvct.discord_bot_test1.commands.topic;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateSpec;
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
public class GetTopicCommand implements SlashCommand {
    private final TopicService topicService;


    @Override
    public String getName() {
        return SlashCommandCommon.GET_TOPIC_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String topicId = event.getOption(TopicCommandCommon.TOPIC_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            Topic topic = topicService.findTopicById(topicId);
            return EmbedCreateSpec.builder()
                    .title("Topic Details")
                    .addField("ID", topic.getId(), false)
                    .addField("Name", topic.getName(), false)
                    .addField("Description", topic.getDescription(), false)
                    .addField("Parent topic", topicService.convertTopicNameToMeaningfulName(topic.getId()), false)
                    .build();
        }).flatMap(embed -> {
            if (embed == null) {
                return event.reply().withEphemeral(true).withContent("Topic not found.");
            }
            return event.reply().withEphemeral(true).withEmbeds(embed);
        });
    }
}