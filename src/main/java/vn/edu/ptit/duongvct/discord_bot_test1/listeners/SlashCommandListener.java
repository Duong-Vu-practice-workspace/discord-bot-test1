package vn.edu.ptit.duongvct.discord_bot_test1.listeners;


import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputAutoCompleteEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.AutoCompleteHandler;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.exception.DiscordExceptionHandler;

import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class SlashCommandListener {

    private final Collection<SlashCommand> commands;
    private final List<AutoCompleteHandler> autoCompleteHandlers;
    private final DiscordExceptionHandler discordExceptionHandler;
    public SlashCommandListener(List<SlashCommand> slashCommands, List<AutoCompleteHandler> autoCompleteHandlers, DiscordExceptionHandler discordExceptionHandler) {
        this.commands = slashCommands;
        this.autoCompleteHandlers = autoCompleteHandlers;
        this.discordExceptionHandler = discordExceptionHandler;
    }


    private Mono<Void> handle(ChatInputInteractionEvent event) {
        //Convert our list to a flux that we can iterate through
        return Flux.fromIterable(commands)
                //Filter out all commands that don't match the name this event is for
                .filter(command -> command.getName().equals(event.getCommandName()))
                //Get the first (and only) item in the flux that matches our filter
                .next()
                //Have our command class handle all logic related to its specific command.
                .flatMap(
                        command -> command.handle(event)
                                .onErrorResume(ex -> discordExceptionHandler.handleException(event, ex))
                )
                ;
    }
    public Mono<Void> register(discord4j.core.GatewayDiscordClient client) {
        // register handlers and keep subscriptions per-client
        client.on(ChatInputInteractionEvent.class, this::handle)
                .doOnError(e -> log.error("Interaction event stream error", e))
                .subscribe();

        client.on(ChatInputAutoCompleteEvent.class, this::handleAutocomplete)
                .doOnError(e -> log.error("Autocomplete event stream error", e))
                .subscribe();
        return Mono.empty();
    }
    private Mono<Void> handleAutocomplete(ChatInputAutoCompleteEvent event) {
        return autoCompleteHandlers.stream()
                .filter(handler -> handler.supports(event))
                .findFirst()
                .map(handler -> handler.handle(event))
                .orElse(Mono.empty());
    }
}