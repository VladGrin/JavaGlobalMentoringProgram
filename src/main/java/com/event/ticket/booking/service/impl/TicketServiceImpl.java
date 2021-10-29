package com.event.ticket.booking.service.impl;

import com.event.ticket.booking.dao.CommonDao;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.TicketService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private CommonDao<Ticket> ticketDao;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketDao.save(ticket);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        ticketDao.delete(ticket);
    }

    @Override
    public Ticket findTicketById(String ticketId) throws EntityNotFoundException {
        return ticketDao.findById(ticketId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketDao.findAll();
    }
}
