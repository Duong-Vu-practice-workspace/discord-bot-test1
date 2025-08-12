package vn.edu.ptit.duongvct.discord_bot_test1.commands.event;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.event.EventCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.EventRepository;
import vn.edu.ptit.duongvct.discord_bot_test1.service.EventService;

@Component
@AllArgsConstructor
public class DeleteEventCommand implements SlashCommand {
    private final EventService eventService;


    @Override
    public String getName() {
        return SlashCommandCommon.DELETE_EVENT_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String eventId = event.getOption(EventCommandCommon.EVENT_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            eventService.deleteEvent(eventId);
            return "Event deleted.";
        }).flatMap(reply -> event.reply().withEphemeral(true).withContent(reply));
    }
}