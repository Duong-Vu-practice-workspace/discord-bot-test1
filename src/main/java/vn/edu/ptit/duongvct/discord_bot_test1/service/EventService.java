package vn.edu.ptit.duongvct.discord_bot_test1.service;

import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Event;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final GoogleCalendarService googleCalendarService;
    private final TopicService topicService;

    /**
     * Create a new event and sync with Google Calendar
     */
    public Event createEvent(Event event) {
        try {
            // Create event in Google Calendar
            String googleEventId = googleCalendarService.createCalendarEvent(event);
            event.setGoogleEventId(googleEventId);

            // Save to database
            return eventRepository.save(event);
        } catch (Exception e) {
            log.error("Error creating event with Google Calendar", e);
            // Fallback: save to database only if Google Calendar fails
            return eventRepository.save(event);
        }
    }

    /**
     * Update an event and sync with Google Calendar
     */
    public Event updateEvent(String eventId, Event updatedEvent) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Update fields from updatedEvent
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setTopicId(updatedEvent.getTopicId());

        try {
            // Update Google Calendar if we have a Google event ID
            if (existingEvent.getGoogleEventId() != null) {
                googleCalendarService.updateCalendarEvent(existingEvent.getGoogleEventId(), existingEvent);
            } else {
                // Create new Google event if we don't have one
                String googleEventId = googleCalendarService.createCalendarEvent(existingEvent);
                existingEvent.setGoogleEventId(googleEventId);
            }
        } catch (Exception e) {
            log.error("Error updating event in Google Calendar", e);
            // Continue with database update even if Google Calendar fails
        }

        // Update in database
        return eventRepository.save(existingEvent);
    }

    /**
     * Delete an event and remove from Google Calendar
     */
    public void deleteEvent(String eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        try {
            // Delete from Google Calendar if we have a Google event ID
            if (event.getGoogleEventId() != null) {
                googleCalendarService.deleteCalendarEvent(event.getGoogleEventId());
            }
        } catch (Exception e) {
            log.error("Error deleting event from Google Calendar", e);
            // Continue with database deletion even if Google Calendar fails
        }

        // Delete from database
        eventRepository.deleteById(eventId);
    }

    /**
     * Find events between two dates (for reminders)
     */
    public List<Event> findEventsBetween(LocalDateTime from, LocalDateTime to) {
        return eventRepository.findByStartTimeBetween(from, to);
    }
    public List<ApplicationCommandOptionChoiceData> getAllEventChoices(String query, String userId) {
        return new ArrayList<>(eventRepository.findByUserIdAndNameContainingIgnoreCase(userId, query)
                .stream()
                .map(event -> ApplicationCommandOptionChoiceData.builder()
                        .name(event.getName())
                        .value(event.getId())
                        .build())
                .limit(25)
                .toList());
    }
    public Event findEventById(String eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }
}