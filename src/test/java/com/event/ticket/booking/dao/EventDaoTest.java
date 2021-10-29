package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.Event;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class EventDaoTest {

    private static final String EVENT_ID = "1";
    private static final String EVENT_NAME = "event";
    private static final double BASE_PRICE = 10.99;
    private static final double NEW_BASE_PRICE = 20.99;

    private final CommonDao<Event> eventDao = new EventDao();

    @Test
    public void saveEvent() {
        Event event = new Event(EVENT_ID, EVENT_NAME, BASE_PRICE);

        eventDao.save(event);
        Event savedEvent = eventDao.findAll().get(0);

        assertEquals(event, savedEvent);
    }

    @Test
    public void updateEvent() {
        Event event = new Event(EVENT_ID, EVENT_NAME, BASE_PRICE);
        eventDao.save(event);
        event.setBasePrice(NEW_BASE_PRICE);

        eventDao.save(event);

        Event savedEvent = eventDao.findAll().get(0);
        assertEquals(event, savedEvent);
        assertEquals(NEW_BASE_PRICE, event.getBasePrice(), 0.000001);
    }

    @Test
    public void delete() {
        Event event = new Event(EVENT_ID, EVENT_NAME, BASE_PRICE);
        eventDao.save(event);

        eventDao.delete(event);

        assertEquals(0, eventDao.findAll().size());
    }

    @Test
    public void findById() {
        Event event = new Event(EVENT_ID, EVENT_NAME, BASE_PRICE);
        eventDao.save(event);

        assertEquals(event, eventDao.findById(event.getEventId()).orElse(new Event()));
    }

    @Test
    public void findAll() {
        Event event = new Event(EVENT_ID, EVENT_NAME, BASE_PRICE);
        eventDao.save(event);

        assertEquals(Collections.singletonList(event), eventDao.findAll());
    }
}