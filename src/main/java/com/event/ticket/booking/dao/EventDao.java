package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.Event;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EventDao implements CommonDao<Event> {

    private static final Map<String, Event> events = new HashMap<>();

    @Override
    public Event save(Event entity) {
        Event result = events.get(entity.getEventId());
        if (Objects.nonNull(result)) {
            result.setEventName(entity.getEventName());
            result.setBasePrice(entity.getBasePrice());
            entity = result;
        }
        events.put(entity.getEventId(), entity);
        return entity;
    }

    @Override
    public List<Event> save(Collection<Event> entities) {
        entities.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(Event entity) {
        events.remove(entity.getEventId());
    }

    @Override
    public Optional<Event> findById(String id) {
        return Optional.of(events.get(id));
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events.values());
    }
}
