package vn.edu.ptit.duongvct.discord_bot_test1.exception;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class DiscordExceptionHandler {
    public Mono<Void> handleException(ChatInputInteractionEvent event, Throwable ex) {
        log.error("Command error", ex);
        String errorMessage = "Error: " + ex.getMessage();
        return event.reply(errorMessage).withEphemeral(true).then();
    }
}
