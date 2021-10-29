package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.utils.DaoUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class EventDao implements CommonDao<Event> {

    private final Map<String, Event> events = new ConcurrentHashMap<>();
    @Setter
    private String path;

    public void init() {
        try {
            events.putAll(DaoUtils.uploadEvents(path));
            log.info("PATH: {},  EVENTS DATA: {}", path, events);
        } catch (IOException e) {
            log.info("Can not upload events by path: {}", path);
        }
    }

    @Override
    public Event save(Event entity) {
        Event result = events.get(entity.getEventId());
        if (Objects.nonNull(result)) {
            result.setEventName(entity.getEventName());
            result.setBasePrice(entity.getBasePrice());
            entity = result;
        }
        events.put(entity.getEventId(), entity);
        log.info("Saved event to Map {}", entity);
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
        log.info("Removed event from Map {}", entity);
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
