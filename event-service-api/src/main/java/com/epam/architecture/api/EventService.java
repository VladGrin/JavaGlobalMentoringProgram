package com.epam.architecture.api;

import com.epam.architecture.dto.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(Event event);

    Event updateEvent(Long id, Event event);

    Optional<Event> getEvent(Long id);

    void deleteEvent(Long id);

    Iterable<Event> getAllEvents();

    List<Event> getAllEventsByTitle(String title);
}
