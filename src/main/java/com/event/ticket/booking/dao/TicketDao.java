package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.Ticket;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TicketDao implements CommonDao<Ticket> {

    private static final Map<String, Ticket> tickets = new HashMap<>();

    @Override
    public Ticket save(Ticket entity) {
        Ticket result = tickets.get(entity.getTicketId());
        if(Objects.nonNull(result)) {
            result.setUserId(entity.getUserId());
            result.setEventId(entity.getEventId());
            result.setDateTime(entity.getDateTime());
            entity = result;
        }
        tickets.put(entity.getTicketId(), entity);
        return entity;
    }

    @Override
    public List<Ticket> save(Collection<Ticket> entities) {
        entities.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(Ticket entity) {
        tickets.remove(entity.getTicketId());
    }

    @Override
    public Optional<Ticket> findById(String id) {
        return Optional.of(tickets.get(id));
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(tickets.values());
    }
}
