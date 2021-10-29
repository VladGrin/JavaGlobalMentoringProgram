package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.utils.DaoUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class TicketDao implements CommonDao<Ticket> {

    private final Map<String, Ticket> tickets = new ConcurrentHashMap<>();
    @Setter
    private String path;

    public void init() {
        try {
            tickets.putAll(DaoUtils.uploadTickets(path));
            log.info("PATH: {}, TICKETS DATA: {}", path, tickets);
        } catch (IOException e) {
            log.info("Can not upload tickets by path: {}", path);
        }
    }

    @Override
    public Ticket save(Ticket entity) {
        Ticket result = tickets.get(entity.getTicketId());
        if (Objects.nonNull(result)) {
            result.setUserId(entity.getUserId());
            result.setEventId(entity.getEventId());
            result.setDateTime(entity.getDateTime());
            entity = result;
        }
        tickets.put(entity.getTicketId(), entity);
        log.info("Saved ticket to Map {}", entity);
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
        log.info("Removed ticket from Map {}", entity);
    }

    @Override
    public Optional<Ticket> findById(String id) {
        return Optional.of(tickets.get(id));
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(this.tickets.values());
    }
}
