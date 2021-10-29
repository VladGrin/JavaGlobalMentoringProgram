package com.event.ticket.booking.service;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingFacade {

    User addUser(User user);

    User getUser(String userId) throws EntityNotFoundException;

    List<User> getAllUsers();

    Event addEvent(Event event);

    Event getEvent(String eventId) throws EntityNotFoundException;

    List<Event> getAllEvents();

    Ticket bookEvent(User user, Event event, LocalDateTime dateTime);

    Ticket cancelBooking(User user, Event event) throws EntityNotFoundException;

    List<Ticket> getTicketsByUser(User user);

    List<Ticket> getTicketsByEvent(Event event);
}
