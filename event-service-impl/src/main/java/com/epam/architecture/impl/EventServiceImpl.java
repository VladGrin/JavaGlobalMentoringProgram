package com.epam.architecture.impl;

import com.epam.architecture.api.EventService;
import com.epam.architecture.dto.entity.Event;
import com.epam.architecture.dto.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event createEvent(Event event) {
        return repository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Optional<Event> eventById = repository.findById(id);
        if (eventById.isPresent()) {
            event.setId(id);
        }
        return repository.save(event);
    }

    @Override
    public Optional<Event> getEvent(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return repository.findEventsByTitle(title);
    }
}
