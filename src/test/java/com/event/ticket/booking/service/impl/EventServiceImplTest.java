package com.event.ticket.booking.service.impl;

import com.event.ticket.booking.dao.CommonDao;
import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

    private static final String EVENT_ID = "1";
    private static final String EVENT_NAME = "event";
    private static final double BASE_PRICE = 10.99;
    @Mock
    private CommonDao<Event> eventDao;
    private EventService eventService;
    private Event event;

    @Before
    public void init() {
        eventService = new EventServiceImpl(eventDao);
        event = new Event(EVENT_ID, EVENT_NAME, BASE_PRICE);
    }

    @Test
    public void saveEvent() {
        when(eventDao.save(event)).thenReturn(event);

        Event savedEvent = eventService.saveEvent(event);

        verify(eventDao).save(event);
        assertEquals(event, savedEvent);
    }

    @Test
    public void deleteEvent() {
        eventService.deleteEvent(event);

        verify(eventDao).delete(event);
    }

    @Test
    public void findEventById() throws EntityNotFoundException {
        when(eventDao.findById(EVENT_ID)).thenReturn(Optional.of(event));

        Event foundEvent = eventService.findEventById(EVENT_ID);

        verify(eventDao).findById(EVENT_ID);
        assertEquals(event, foundEvent);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findEventByIdWhenEntityNotFound() throws EntityNotFoundException {
        when(eventDao.findById(EVENT_ID)).thenReturn(Optional.empty());

        eventService.findEventById(EVENT_ID);

        verify(eventDao).findById(EVENT_ID);
    }

    @Test
    public void findAllEvents() {
        when(eventDao.findAll()).thenReturn(Collections.singletonList(event));

        List<Event> allEvents = eventService.findAllEvents();

        verify(eventDao).findAll();
        assertEquals(Collections.singletonList(event), allEvents);
    }
}