package vn.edu.ptit.duongvct.discord_bot_test1.commands.event;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateSpec;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.event.EventCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Event;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.EventRepository;

@Component
@AllArgsConstructor
public class GetEventCommand implements SlashCommand {
    private final EventRepository eventRepository;

    @Override
    public String getName() {
        return SlashCommandCommon.GET_EVENT_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String eventId = event.getOption(EventCommandCommon.EVENT_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            Event found = eventRepository.findById(eventId).orElse(null);
            if (found == null) {
                throw new IllegalArgumentException("Event not found");
            }
            return EmbedCreateSpec.builder()
                    .title("Event Details")
                    .addField("ID", found.getId(), false)
                    .addField("Name", found.getName(), false)
                    .addField("Description", found.getDescription(), false)
                    .addField("Start Time", found.getStartTime() != null ? found.getStartTime().toString() : "N/A", false)
                    .addField("End Time", found.getEndTime() != null ? found.getEndTime().toString() : "N/A", false)
                    .build();
        }).flatMap(embed -> {
            if (embed == null) {
                return event.reply().withEphemeral(true).withContent("Event not found.");
            }
            return event.reply().withEphemeral(true).withEmbeds(embed);
        });
    }
}