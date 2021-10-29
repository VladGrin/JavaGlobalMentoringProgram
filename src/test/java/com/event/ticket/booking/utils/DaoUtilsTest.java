package com.event.ticket.booking.utils;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.domain.User;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DaoUtilsTest {

    @Test
    public void uploadUsers() throws IOException {
        Map<String, User> userMap = DaoUtils.uploadUsers("data/users.csv");

        assertEquals(10, userMap.size());
    }

    @Test
    public void uploadEvents() throws IOException {
        Map<String, Event> eventMap = DaoUtils.uploadEvents("data/events.csv");

        assertEquals(10, eventMap.size());
    }

    @Test
    public void uploadTickets() throws IOException {
        Map<String, Ticket> ticketMap = DaoUtils.uploadTickets("data/tickets.csv");

        assertEquals(20, ticketMap.size());
    }
}