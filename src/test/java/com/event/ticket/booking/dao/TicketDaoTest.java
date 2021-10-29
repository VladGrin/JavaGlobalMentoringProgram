package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.Ticket;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TicketDaoTest {

    private static final String TICKET_ID = "1";
    private static final String USER_ID = "2";
    private static final String EVENT_ID = "3";
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2021, 10, 5, 12, 30, 0);
    private static final LocalDateTime NEW_DATE_TIME = LocalDateTime.of(2021, 10, 6, 12, 30, 0);

    private final CommonDao<Ticket> ticketDao = new TicketDao();

    @Test
    public void saveTicket() {
        Ticket ticket = new Ticket(TICKET_ID, USER_ID, EVENT_ID, DATE_TIME);

        ticketDao.save(ticket);
        Ticket savedTicket = ticketDao.findAll().get(0);

        assertEquals(ticket, savedTicket);
    }

    @Test
    public void updateTicket() {
        Ticket ticket = new Ticket(TICKET_ID, USER_ID, EVENT_ID, DATE_TIME);
        ticketDao.save(ticket);
        ticket.setDateTime(NEW_DATE_TIME);

        ticketDao.save(ticket);

        Ticket savedTicket = ticketDao.findAll().get(0);
        assertEquals(ticket, savedTicket);
        assertEquals(NEW_DATE_TIME, ticket.getDateTime());
    }

    @Test
    public void delete() {
        Ticket ticket = new Ticket(TICKET_ID, USER_ID, EVENT_ID, DATE_TIME);
        ticketDao.save(ticket);

        ticketDao.delete(ticket);

        assertEquals(0, ticketDao.findAll().size());
    }

    @Test
    public void findById() {
        Ticket ticket = new Ticket(TICKET_ID, USER_ID, EVENT_ID, DATE_TIME);
        ticketDao.save(ticket);

        assertEquals(ticket, ticketDao.findById(ticket.getTicketId()).orElse(new Ticket()));
    }

    @Test
    public void findAll() {
        Ticket ticket = new Ticket(TICKET_ID, USER_ID, EVENT_ID, DATE_TIME);
        ticketDao.save(ticket);

        assertEquals(Collections.singletonList(ticket), ticketDao.findAll());
    }
}