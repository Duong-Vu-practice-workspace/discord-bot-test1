package vn.edu.ptit.duongvct.discord_bot_test1.commands.event;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.event.CreateEventCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Event;
import vn.edu.ptit.duongvct.discord_bot_test1.service.EventService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@AllArgsConstructor
public class CreateEventCommand implements SlashCommand {
    private final EventService eventService;
    @Override
    public String getName() {
        return SlashCommandCommon.CREATE_EVENT_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            User user = event.getInteraction().getUser();
            String name = event.getOption(CreateEventCommandCommon.NAME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("Unnamed");
            String description = event.getOption(CreateEventCommandCommon.DESCRIPTION_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String location = event.getOption(CreateEventCommandCommon.LOCATION_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String topicId = event.getOption(CreateEventCommandCommon.TOPIC_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String startTimeString = event.getOption(CreateEventCommandCommon.START_TIME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String endTimeString = event.getOption(CreateEventCommandCommon.END_TIME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            LocalDateTime startTime = convertDateTimeFormat(startTimeString);
            LocalDateTime endTime = convertDateTimeFormat(endTimeString);

            Event ev = Event.builder()
                    .name(name)
                    .description(description)
                    .startTime(startTime)
                    .endTime(endTime)
                    .location(location)
                    .topicId(topicId)
                    .userId(user.getId().asString())
                    .build();

            Event savedEvent = eventService.createEvent(ev);
            return "New event created: " + savedEvent.getName() + " (synced with Google Calendar)";
        }).flatMap(msg -> event.reply()
                .withEphemeral(true)
                .withContent(msg)
        );
    }
    private LocalDateTime convertDateTimeFormat(String dateTime) {
        DateTimeFormatter formatterWithSeconds = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter formatterWithoutSeconds = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            return LocalDateTime.parse(dateTime, formatterWithSeconds);
        } catch (DateTimeParseException ex) {
            return LocalDateTime.parse(dateTime, formatterWithoutSeconds);
        }
    }
}
