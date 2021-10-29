package com.event.ticket.booking.integration;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.BookingFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
public class BookingServiceIntegrationTest {

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Wick";
    public static final String EMAIL = "wick@gmail.com";
    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2021, 11, 10, 12, 0, 0);

    @Autowired
    private BookingFacade bookingFacade;

    @Test
    public void bookEvent() throws EntityNotFoundException {
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);

        User savedUser = bookingFacade.addUser(user);
        User userFromMap = bookingFacade.getUser(savedUser.getUserId());

        assertThat(bookingFacade.getAllUsers().size(), is(11));
        assertThat(savedUser, is(userFromMap));

        Event event = new Event();
        event.setEventName("Java Event");
        event.setBasePrice(100.50);

        Event savedEvent = bookingFacade.addEvent(event);
        Event eventFromMap = bookingFacade.getEvent(savedEvent.getEventId());

        assertThat(bookingFacade.getAllEvents().size(), is(11));
        assertThat(savedEvent, is(eventFromMap));

        Ticket savedTicket = bookingFacade.bookEvent(savedUser, savedEvent, DATE_TIME);

        List<Ticket> ticketsByUser = bookingFacade.getTicketsByUser(savedUser);
        assertThat(ticketsByUser.get(0), is(savedTicket));

        List<Ticket> ticketsByEvent = bookingFacade.getTicketsByEvent(event);
        assertThat(ticketsByEvent.get(0), is(savedTicket));

        Ticket deletedTicket = bookingFacade.cancelBooking(user, event);
        assertThat(deletedTicket, is(savedTicket));

        assertThat(bookingFacade.getTicketsByUser(savedUser).size(), is(0));
        assertThat(bookingFacade.getTicketsByEvent(savedEvent).size(), is(0));
    }
}
