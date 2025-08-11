package vn.edu.ptit.duongvct.discord_bot_test1.autocomplete;

import discord4j.core.event.domain.interaction.ChatInputAutoCompleteEvent;
import reactor.core.publisher.Mono;

public interface AutoCompleteHandler {
    boolean supports(ChatInputAutoCompleteEvent event);
    public Mono<Void> handle(ChatInputAutoCompleteEvent event);
}
