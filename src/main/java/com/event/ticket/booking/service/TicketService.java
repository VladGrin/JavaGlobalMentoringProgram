package com.event.ticket.booking.service;

import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.exception.EntityNotFoundException;

import java.util.List;

public interface TicketService {

    Ticket saveTicket(Ticket ticket);

    void deleteTicket(Ticket ticket);

    Ticket findTicketById(String ticketId) throws EntityNotFoundException;

    List<Ticket> findAllTickets();
}
