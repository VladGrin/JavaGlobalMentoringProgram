package com.event.ticket.booking.service.impl;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.BookingFacade;
import com.event.ticket.booking.service.EventService;
import com.event.ticket.booking.service.TicketService;
import com.event.ticket.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private TicketService ticketService;

    @Override
    public User addUser(User user) {
        return userService.saveUser(user);
    }

    @Override
    public User getUser(String userId) throws EntityNotFoundException {
        return userService.findUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public Event addEvent(Event event) {
        return eventService.saveEvent(event);
    }

    @Override
    public Event getEvent(String eventId) throws EntityNotFoundException {
        return eventService.findEventById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventService.findAllEvents();
    }

    @Override
    public Ticket buyTicket(User user, Event event, LocalDateTime dateTime) {
        Ticket ticket = new Ticket();
        ticket.setUserId(user.getUserId());
        ticket.setEventId(event.getEventId());
        ticket.setDateTime(dateTime);
        return ticketService.saveTicket(ticket);
    }

    @Override
    public List<Ticket> getTicketsByUser(User user) {
        return ticketService.findAllTickets().stream()
                .filter(ticket -> ticket.getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getTicketsByEvent(Event event) {
        return ticketService.findAllTickets().stream()
                .filter(ticket -> ticket.getEventId().equals(event.getEventId()))
                .collect(Collectors.toList());
    }
}
