package vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.topic;

import discord4j.core.event.domain.interaction.ChatInputAutoCompleteEvent;
import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.AutoCompleteHandler;
import vn.edu.ptit.duongvct.discord_bot_test1.common.topic.CreateTopicCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.service.TopicService;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class CreateTopicAutoCompleteHandler implements AutoCompleteHandler {
    private final TopicService topicService;
    @Override
    public boolean supports(ChatInputAutoCompleteEvent event) {
        return SlashCommandCommon.CREATE_TOPIC_COMMAND.equals(event.getCommandName()) &&
                CreateTopicCommandCommon.PARENT_ID_PARAMETER.equals(event.getFocusedOption().getName());
    }

    @Override
    public Mono<Void> handle(ChatInputAutoCompleteEvent event) {
        String userId = event.getInteraction().getUser().getId().asString();
        String query = Objects.requireNonNull(event.getFocusedOption().getValue().orElse(null)).asString();
        List<ApplicationCommandOptionChoiceData> choices =
                topicService.getAllTopicChoices(query, userId)
                        .stream()
                        .limit(25)
                        .toList();
        return event.respondWithSuggestions(choices);
    }
}
