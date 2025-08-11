package vn.edu.ptit.duongvct.discord_bot_test1.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;

@Service
@Slf4j
public class GoogleCalendarService {

    private static final String APPLICATION_NAME = "Discord Bot Calendar";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    @Value("${calendar.id}")
    private String CALENDAR_ID;

    private final Calendar service;

    public GoogleCalendarService() throws GeneralSecurityException, IOException {
        log.info("Initializing Google Calendar Service");

        // Set up HTTP transport
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // Load credentials from resources
        InputStream credentialsStream = new ClassPathResource("credentials.json").getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream)
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/calendar"));

        // Build calendar service
        service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();

        log.info("Google Calendar Service initialized successfully");
    }

    /**
     * Creates an event in Google Calendar and returns the event ID
     */
    public String createCalendarEvent(vn.edu.ptit.duongvct.discord_bot_test1.entity.Event discordEvent) {
        try {
            // Create Google Calendar event
            Event event = new Event()
                    .setSummary(discordEvent.getName())
                    .setDescription(discordEvent.getDescription())
                    .setLocation(discordEvent.getLocation());

            // Set start time
            DateTime startDateTime = convertToDateTime(discordEvent.getStartTime());
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime);
            event.setStart(start);

            // Set end time
            DateTime endDateTime = convertToDateTime(discordEvent.getEndTime());
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime);
            event.setEnd(end);

            // Insert event
            event = service.events().insert(CALENDAR_ID, event).execute();
            log.info("Event created: {}", event.getHtmlLink());

            return event.getId();
        } catch (IOException e) {
            log.error("Failed to create Google Calendar event", e);
            throw new RuntimeException("Failed to create Google Calendar event", e);
        }
    }

    /**
     * Updates an existing Google Calendar event
     */
    public void updateCalendarEvent(String googleEventId, vn.edu.ptit.duongvct.discord_bot_test1.entity.Event discordEvent) {
        try {
            // Get existing event
            Event event = service.events().get(CALENDAR_ID, googleEventId).execute();

            // Update event details
            event.setSummary(discordEvent.getName())
                    .setDescription(discordEvent.getDescription())
                    .setLocation(discordEvent.getLocation());

            // Update start time
            DateTime startDateTime = convertToDateTime(discordEvent.getStartTime());
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime);
            event.setStart(start);

            // Update end time
            DateTime endDateTime = convertToDateTime(discordEvent.getEndTime());
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime);
            event.setEnd(end);

            // Update event
            service.events().update(CALENDAR_ID, googleEventId, event).execute();
            log.info("Event updated: {}", event.getHtmlLink());
        } catch (IOException e) {
            log.error("Failed to update Google Calendar event", e);
            throw new RuntimeException("Failed to update Google Calendar event", e);
        }
    }

    /**
     * Deletes a Google Calendar event
     */
    public void deleteCalendarEvent(String googleEventId) {
        try {
            service.events().delete(CALENDAR_ID, googleEventId).execute();
            log.info("Event deleted: {}", googleEventId);
        } catch (IOException e) {
            log.error("Failed to delete Google Calendar event", e);
            throw new RuntimeException("Failed to delete Google Calendar event", e);
        }
    }

    /**
     * Convert LocalDateTime to Google Calendar DateTime
     */
    private DateTime convertToDateTime(LocalDateTime localDateTime) {
        return new DateTime(
                localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        );
    }
}