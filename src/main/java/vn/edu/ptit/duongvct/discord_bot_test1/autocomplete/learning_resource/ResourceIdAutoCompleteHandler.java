package vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.learning_resource;

import discord4j.core.event.domain.interaction.ChatInputAutoCompleteEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.AutoCompleteHandler;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.resource.LearningResourceCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.LearningResourceRepository;
import vn.edu.ptit.duongvct.discord_bot_test1.service.LearningResourceService;

import java.util.List;

@Component
@AllArgsConstructor
public class ResourceIdAutoCompleteHandler implements AutoCompleteHandler {
    private final LearningResourceService learningResourceService;
    @Override
    public boolean supports(ChatInputAutoCompleteEvent event) {
        return ((SlashCommandCommon.GET_RESOURCE_COMMAND.equals(event.getCommandName())) || (SlashCommandCommon.EDIT_RESOURCE_COMMAND.equals(event.getCommandName())) || (SlashCommandCommon.DELETE_RESOURCE_COMMAND.equals(event.getCommandName())))
                && LearningResourceCommandCommon.RESOURCE_ID_PARAMETER.equals(event.getFocusedOption().getName());
    }

    @Override
    public Mono<Void> handle(ChatInputAutoCompleteEvent event) {
        String query = event.getFocusedOption().getValue().map(ApplicationCommandInteractionOptionValue::asString).orElse("");
        String userId = event.getInteraction().getUser().getId().asString();

        List<ApplicationCommandOptionChoiceData> choices = learningResourceService.getAllResourceChoices(query, userId)
                .stream()
                .limit(25)
                .toList();

        return event.respondWithSuggestions(choices);
    }
}
