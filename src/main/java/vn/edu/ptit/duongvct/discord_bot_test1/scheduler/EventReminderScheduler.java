package vn.edu.ptit.duongvct.discord_bot_test1.scheduler;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Event;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.EventRepository;
import vn.edu.ptit.duongvct.discord_bot_test1.service.TopicService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventReminderScheduler {
    private final EventRepository eventRepository;
    private final GatewayDiscordClient discordClient;
    private final TopicService topicService;
    @Value("${event.notification.channel.id}")
    private long CHANNEL_ID;
    private static final long SCAN_TIME_PERIOD = 60 * 1000;

    private void sendReminderEmbedAndInline(Event event, String timeText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String userPing = "<@" + event.getUserId() + ">";
        String inlineMsg = String.format("%s: **%s** | %s → %s | %s",
                userPing,
                event.getName(),
                event.getStartTime().format(formatter),
                event.getEndTime().format(formatter),
                event.getLocation()
        );

        // Send embed message
        discordClient.getChannelById(Snowflake.of(CHANNEL_ID))
                .ofType(MessageChannel.class)
                .flatMap(channel -> channel.createMessage(
                        MessageCreateSpec.builder()
                                .addEmbed(buildEventEmbed(event, timeText))
                                .build()
                ))
                .subscribe();

        // Send inline ping message
        discordClient.getChannelById(Snowflake.of(CHANNEL_ID))
                .ofType(MessageChannel.class)
                .flatMap(channel -> channel.createMessage(inlineMsg))
                .subscribe();
    }

    private EmbedCreateSpec buildEventEmbed(Event event, String timeText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return EmbedCreateSpec.builder()
                .title("Event Reminder")
                .description(timeText + "\n<@" + event.getUserId() + ">")
                .addField("Name", event.getName(), false)
                .addField("Description", event.getDescription(), false)
                .addField("Location", event.getLocation(), false)
                .addField("Start Time", event.getStartTime().format(formatter), true)
                .addField("End Time", event.getEndTime().format(formatter), true)
                .addField("Topic", topicService.convertTopicNameToMeaningfulName(event.getTopicId()), true)
                .build();
    }

    @Scheduled(fixedRate = SCAN_TIME_PERIOD)
    public void scanAndSendReminders() {
        log.info("Start scan events and send reminders");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDay = now.plusDays(1);
        List<Event> events = eventRepository.findByStartTimeBetween(now, nextDay);

        for (Event event : events) {
            LocalDateTime startTime = event.getStartTime();
            Duration duration = Duration.between(now, startTime);

            String timeText = null;
            if (duration.toMinutes() <= 1440 && duration.toMinutes() >= 1439) {
                timeText = "**Starts in 1 day!**";
            } else if (duration.toMinutes() <= 60 && duration.toMinutes() >= 59) {
                timeText = "**Starts in 1 hour!**";
            } else if (duration.toMinutes() <= 30 && duration.toMinutes() >= 29) {
                timeText = "**Starts in 30 minutes!**";
            } else if (duration.toMinutes() <= 15 && duration.toMinutes() >= 14) {
                timeText = "**Starts in 15 minutes!**";
            }

            if (timeText != null) {
                sendReminderEmbedAndInline(event, timeText);
            }
        }
    }
}