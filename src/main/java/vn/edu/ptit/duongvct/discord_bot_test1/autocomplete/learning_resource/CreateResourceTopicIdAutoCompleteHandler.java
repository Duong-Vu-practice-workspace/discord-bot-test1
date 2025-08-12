package vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.learning_resource;

import discord4j.core.event.domain.interaction.ChatInputAutoCompleteEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.AutoCompleteHandler;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.resource.LearningResourceCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.service.TopicService;

import java.util.List;

@Component
public class CreateResourceTopicIdAutoCompleteHandler implements AutoCompleteHandler {
    private final TopicService topicService;

    public CreateResourceTopicIdAutoCompleteHandler(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public boolean supports(ChatInputAutoCompleteEvent event) {
        return SlashCommandCommon.CREATE_RESOURCE_COMMAND.equals(event.getCommandName())
                && LearningResourceCommandCommon.TOPIC_ID_PARAMETER.equals(event.getFocusedOption().getName());
    }

    @Override
    public Mono<Void> handle(ChatInputAutoCompleteEvent event) {
        String query = event.getFocusedOption().getValue().map(ApplicationCommandInteractionOptionValue::asString).orElse("");
        String userId = event.getInteraction().getUser().getId().asString();

        List<ApplicationCommandOptionChoiceData> choices = topicService.getAllTopicChoices(query, userId)
                .stream()
                .limit(25)
                .toList();

        return event.respondWithSuggestions(choices);
    }
}