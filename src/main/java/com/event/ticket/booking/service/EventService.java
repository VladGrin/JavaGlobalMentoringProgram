package com.event.ticket.booking.service;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.exception.EntityNotFoundException;

import java.util.List;

public interface EventService {

    Event saveEvent(Event event);

    void deleteEvent(Event event);

    Event findEventById(String eventId) throws EntityNotFoundException;

    List<Event> findAllEvents();
}
