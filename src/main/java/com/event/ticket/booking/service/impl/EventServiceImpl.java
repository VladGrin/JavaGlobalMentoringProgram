package com.event.ticket.booking.service.impl;

import com.event.ticket.booking.dao.CommonDao;
import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private CommonDao<Event> eventDao;

    @Override
    public Event saveEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public void deleteEvent(Event event) {
        eventDao.delete(event);
    }

    @Override
    public Event findEventById(String eventId) throws EntityNotFoundException {
        return eventDao.findById(eventId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventDao.findAll();
    }
}
