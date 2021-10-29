package com.event.ticket.booking.service.impl;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.BookingFacade;
import com.event.ticket.booking.service.EventService;
import com.event.ticket.booking.service.TicketService;
import com.event.ticket.booking.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class BookingFacadeImpl implements BookingFacade {

    private UserService userService;
    private EventService eventService;
    private TicketService ticketService;

    @Override
    public User addUser(User user) {
        return userService.saveUser(user);
    }

    @Override
    public User getUser(String userId) throws EntityNotFoundException {
        User userById = userService.findUserById(userId);
        log.info("Found user {} by id {}", userById, userId);
        return userById;
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
        Event eventById = eventService.findEventById(eventId);
        log.info("Found event {} by id {}", eventById, eventId);
        return eventById;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventService.findAllEvents();
    }

    @Override
    public Ticket bookEvent(User user, Event event, LocalDateTime dateTime) {
        Ticket ticket = new Ticket();
        ticket.setUserId(user.getUserId());
        ticket.setEventId(event.getEventId());
        ticket.setDateTime(dateTime);
        Ticket saveTicket = ticketService.saveTicket(ticket);
        log.info("Book {} by User {} Event {} date {}", saveTicket, user, event, dateTime);
        return saveTicket;
    }

    @Override
    public Ticket cancelBooking(User user, Event event) throws EntityNotFoundException {
        Ticket ticket = ticketService.findAllTickets().stream()
                .filter(t -> t.getUserId().equals(user.getUserId()))
                .filter(t -> t.getEventId().equals(event.getEventId()))
                .findAny().orElseThrow(EntityNotFoundException::new);
        ticketService.deleteTicket(ticket);
        log.info("Canceled ticket {}", ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getTicketsByUser(User user) {
        List<Ticket> tickets = ticketService.findAllTickets().stream()
                .filter(ticket -> ticket.getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
        log.info("Found tickets {} by User {}", tickets, user);
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByEvent(Event event) {
        List<Ticket> tickets = ticketService.findAllTickets().stream()
                .filter(ticket -> ticket.getEventId().equals(event.getEventId()))
                .collect(Collectors.toList());
        log.info("Found tickets {} by Event {}", tickets, event);
        return tickets;
    }
}
