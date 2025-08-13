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
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Event;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.EventRepository;
import vn.edu.ptit.duongvct.discord_bot_test1.service.EventService;
import vn.edu.ptit.duongvct.discord_bot_test1.util.CommonUtil;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class EditEventCommand implements SlashCommand {
    private final EventService eventService;


    @Override
    public String getName() {
        return SlashCommandCommon.EDIT_EVENT_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String eventId = event.getOption(EventCommandCommon.EVENT_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            Event found = eventService.findEventById(eventId);
            if (eventId.isBlank() || found == null) {
                throw new IllegalArgumentException("Event not found.");
            }
            String newName = event.getOption(EventCommandCommon.NAME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse(null);
            String newDescription = event.getOption(EventCommandCommon.DESCRIPTION_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse(null);
            String location = event.getOption(EventCommandCommon.LOCATION_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String topicId = event.getOption(EventCommandCommon.TOPIC_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String startTimeString = event.getOption(EventCommandCommon.START_TIME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String endTimeString = event.getOption(EventCommandCommon.END_TIME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            if (CommonUtil.validateString(newName)) {
                found.setName(newName);
            }
            if (CommonUtil.validateString(newDescription)) {
                found.setDescription(newDescription);
            }
            if (CommonUtil.validateString(location)) {
                found.setLocation(location);
            }
            if (CommonUtil.validateString(topicId)) {
                found.setTopicId(topicId);
            }
            if(CommonUtil.validateString(startTimeString)) {
                found.setStartTime(CommonUtil.convertDateTimeFormat(startTimeString));
            }
            if (CommonUtil.validateString(endTimeString)) {
                CommonUtil.convertDateTimeFormat(endTimeString);
            }

            eventService.updateEvent(eventId, found);
            return "Event updated." + found;
        }).flatMap(reply -> event.reply().withEphemeral(true).withContent(reply));
    }
}