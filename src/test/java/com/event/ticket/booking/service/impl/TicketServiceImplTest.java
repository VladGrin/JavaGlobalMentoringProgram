package com.event.ticket.booking.service.impl;

import com.event.ticket.booking.dao.CommonDao;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    private static final String TICKET_ID = "1";
    private static final String USER_ID = "2";
    private static final String EVENT_ID = "3";
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2021, 10, 5, 12, 30, 0);
    @Mock
    private CommonDao<Ticket> ticketDao;
    private TicketService ticketService;
    private Ticket ticket;

    @Before
    public void init() {
        ticketService = new TicketServiceImpl(ticketDao);
        ticket = new Ticket(TICKET_ID, USER_ID, EVENT_ID, DATE_TIME);
    }

    @Test
    public void saveTicket() {
        when(ticketDao.save(ticket)).thenReturn(ticket);

        Ticket savedTicket = ticketService.saveTicket(ticket);

        verify(ticketDao).save(ticket);
        assertEquals(ticket, savedTicket);
    }

    @Test
    public void deleteTicket() {
        ticketService.deleteTicket(ticket);

        verify(ticketDao).delete(ticket);
    }

    @Test
    public void findTicketById() throws EntityNotFoundException {
        when(ticketDao.findById(TICKET_ID)).thenReturn(Optional.of(ticket));

        Ticket foundTicket = ticketService.findTicketById(TICKET_ID);

        verify(ticketDao).findById(TICKET_ID);
        assertEquals(ticket, foundTicket);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findTicketByIdWhenEntityNotFound() throws EntityNotFoundException {
        when(ticketDao.findById(TICKET_ID)).thenReturn(Optional.empty());

        ticketService.findTicketById(TICKET_ID);

        verify(ticketDao).findById(TICKET_ID);
    }

    @Test
    public void findAllTickets() {
        when(ticketDao.findAll()).thenReturn(Collections.singletonList(ticket));

        List<Ticket> allTickets = ticketService.findAllTickets();

        verify(ticketDao).findAll();
        assertEquals(Collections.singletonList(ticket), allTickets);
    }
}