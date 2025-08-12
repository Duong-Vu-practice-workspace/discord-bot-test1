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
import vn.edu.ptit.duongvct.discord_bot_test1.repository.TopicRepository;

@Component
@AllArgsConstructor
public class DeleteTopicCommand implements SlashCommand {
    private final TopicRepository topicRepository;

    @Override
    public String getName() {
        return SlashCommandCommon.DELETE_TOPIC_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String topicId = event.getOption(TopicCommandCommon.TOPIC_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            topicRepository.deleteById(topicId);
            return "Topic deleted";
        }).flatMap(msg -> event.reply().withEphemeral(true).withContent(msg));
    }
}