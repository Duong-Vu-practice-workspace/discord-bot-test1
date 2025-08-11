package vn.edu.ptit.duongvct.discord_bot_test1.listeners;


import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputAutoCompleteEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandOptionChoice;
import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.CreateTopicCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.service.TopicService;

import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class SlashCommandListener {

    private final Collection<SlashCommand> commands;
    private final TopicService topicService;
    public SlashCommandListener(List<SlashCommand> slashCommands, GatewayDiscordClient client, TopicService topicService) {
        commands = slashCommands;
        this.topicService = topicService;

        client.on(ChatInputInteractionEvent.class, this::handle).subscribe();
        client.on(ChatInputAutoCompleteEvent.class, this::handleAutocomplete).subscribe();
    }


    public Mono<Void> handle(ChatInputInteractionEvent event) {
        //Convert our list to a flux that we can iterate through
        return Flux.fromIterable(commands)
                //Filter out all commands that don't match the name this event is for
                .filter(command -> command.getName().equals(event.getCommandName()))
                //Get the first (and only) item in the flux that matches our filter
                .next()
                //Have our command class handle all logic related to its specific command.
                .flatMap(command -> command.handle(event));
    }
    private Mono<Void> handleAutocomplete(ChatInputAutoCompleteEvent event) {
        if (SlashCommandCommon.CREATE_TOPIC_COMMAND.equals(event.getCommandName()) &&
                CreateTopicCommandCommon.PARENT_ID_PARAMETER.equals(event.getFocusedOption().getName())) {
            String userId = event.getInteraction().getUser().getId().asString();

            List<ApplicationCommandOptionChoiceData> choices =
                    topicService.getAllTopicChoices(userId);

            return event.respondWithSuggestions(choices);
        }
        return Mono.empty();
    }
}