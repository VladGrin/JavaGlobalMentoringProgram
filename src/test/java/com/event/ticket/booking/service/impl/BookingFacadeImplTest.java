package com.event.ticket.booking.service.impl;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.BookingFacade;
import com.event.ticket.booking.service.EventService;
import com.event.ticket.booking.service.TicketService;
import com.event.ticket.booking.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookingFacadeImplTest {

    private static final String USER_ID = "1";
    private static final String FIRST_NAME = "name";
    private static final String LAST_NAME = "surname";
    private static final String EMAIL = "email@gmail.com";
    private static final String EVENT_ID = "2";
    private static final String EVENT_NAME = "event";
    private static final double BASE_PRICE = 10.99;
    private static final String TICKET_ID = "3";
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2021, 10, 5, 12, 30, 0);

    @Mock
    private UserService userService;
    @Mock
    private EventService eventService;
    @Mock
    private TicketService ticketService;
    private BookingFacade bookingFacade;

    private User user;
    private Event event;
    private Ticket ticket;

    @Before
    public void init() {
        bookingFacade = new BookingFacadeImpl(userService, eventService, ticketService);
        user = new User(USER_ID, FIRST_NAME, LAST_NAME, EMAIL);
        event = new Event(EVENT_ID, EVENT_NAME, BASE_PRICE);
        ticket = new Ticket(TICKET_ID, USER_ID, EVENT_ID, DATE_TIME);
    }

    @Test
    public void addUser() {
        when(userService.saveUser(user)).thenReturn(user);

        User savedUser = bookingFacade.addUser(user);

        verify(userService).saveUser(user);
        assertEquals(user, savedUser);
    }

    @Test
    public void getUser() throws EntityNotFoundException {
        when(userService.findUserById(USER_ID)).thenReturn(user);

        User foundUser = bookingFacade.getUser(USER_ID);

        verify(userService).findUserById(USER_ID);
        assertEquals(user, foundUser);
    }

    @Test
    public void getAllUsers() {
        when(userService.findAllUsers()).thenReturn(Collections.singletonList(user));

        List<User> allUsers = bookingFacade.getAllUsers();

        verify(userService).findAllUsers();
        assertEquals(Collections.singletonList(user), allUsers);
    }

    @Test
    public void addEvent() {
        when(eventService.saveEvent(event)).thenReturn(event);

        Event savedEvent = bookingFacade.addEvent(event);

        verify(eventService).saveEvent(event);
        assertEquals(event, savedEvent);
    }

    @Test
    public void getEvent() throws EntityNotFoundException {
        when(eventService.findEventById(EVENT_ID)).thenReturn(event);

        Event foundEvent = bookingFacade.getEvent(EVENT_ID);

        verify(eventService).findEventById(EVENT_ID);
        assertEquals(event, foundEvent);
    }

    @Test
    public void getAllEvents() {
        when(eventService.findAllEvents()).thenReturn(Collections.singletonList(event));

        List<Event> allEvents = bookingFacade.getAllEvents();

        verify(eventService).findAllEvents();
        assertEquals(Collections.singletonList(event), allEvents);
    }

    @Test
    public void bookEvent() {
        when(ticketService.saveTicket(any())).thenReturn(ticket);

        Ticket savedTicket = bookingFacade.bookEvent(user, event, DATE_TIME);

        verify(ticketService).saveTicket(any());
        assertEquals(ticket.getUserId(), savedTicket.getUserId());
        assertEquals(ticket.getEventId(), savedTicket.getEventId());
        assertEquals(ticket.getDateTime(), savedTicket.getDateTime());
    }

    @Test
    public void cancelBooking() throws EntityNotFoundException {
        when(ticketService.findAllTickets()).thenReturn(Collections.singletonList(ticket));

        Ticket deletedTicket = bookingFacade.cancelBooking(user, event);

        verify(ticketService).findAllTickets();
        verify(ticketService).deleteTicket(this.ticket);
        assertEquals(ticket, deletedTicket);
    }

    @Test
    public void getTicketsByUser() {
        when(ticketService.findAllTickets()).thenReturn(Collections.singletonList(ticket));

        List<Ticket> ticketsByUser = bookingFacade.getTicketsByUser(user);

        verify(ticketService).findAllTickets();
        assertEquals(Collections.singletonList(ticket), ticketsByUser);
    }

    @Test
    public void getTicketsByEvent() {
        when(ticketService.findAllTickets()).thenReturn(Collections.singletonList(ticket));

        List<Ticket> ticketsByEvent = bookingFacade.getTicketsByEvent(event);

        verify(ticketService).findAllTickets();
        assertEquals(Collections.singletonList(ticket), ticketsByEvent);
    }
}