package com.epam.architecture.dto.repository;

import com.epam.architecture.dto.entity.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EventRepositoryTest {

    private static final String TITLE = "Title";
    private static final String NEW_TITLE = "New Title";
    private static final String PLACE = "Place";
    private static final String SPEAKER = "Speaker";
    private static final String EVENT_TYPE = "Event Type";
    private static final LocalDateTime DATE_TIME = LocalDateTime.now();

    @Autowired
    private EventRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private Event event;

    @Test
    @Rollback(value = false)
    void saveEvent() {
        event = new Event(null, TITLE, PLACE, SPEAKER, EVENT_TYPE, DATE_TIME);
        Event savedEvent = repository.save(event);

        Event existEvent = entityManager.find(Event.class, savedEvent.getId());

        assertThat(existEvent.getTitle(), is(TITLE));
        assertThat(existEvent.getPlace(), is(PLACE));
        assertThat(existEvent.getSpeaker(), is(SPEAKER));
        assertThat(existEvent.getEventType(), is(EVENT_TYPE));
        assertThat(existEvent.getDateTime(), is(DATE_TIME));

        entityManager.remove(savedEvent);
    }

    @Test
    @Rollback(value = false)
    void updateEvent() {
        event = new Event(null, TITLE, PLACE, SPEAKER, EVENT_TYPE, DATE_TIME);
        Event savedEvent = repository.save(event);
        event.setId(savedEvent.getId());
        event.setTitle(NEW_TITLE);
        savedEvent = repository.save(event);

        Event existEvent = entityManager.find(Event.class, savedEvent.getId());

        assertThat(existEvent.getTitle(), is(NEW_TITLE));
        assertThat(existEvent.getPlace(), is(PLACE));
        assertThat(existEvent.getSpeaker(), is(SPEAKER));
        assertThat(existEvent.getEventType(), is(EVENT_TYPE));
        assertThat(existEvent.getDateTime(), is(DATE_TIME));

        entityManager.remove(savedEvent);
    }

    @Test
    @Rollback(value = false)
    void findEvent() {
        event = new Event(null, TITLE, PLACE, SPEAKER, EVENT_TYPE, DATE_TIME);
        Event savedEvent = repository.save(event);
        Event foundEvent = repository.findById(savedEvent.getId()).orElse(new Event());

        assertThat(foundEvent.getTitle(), is(TITLE));
        assertThat(foundEvent.getPlace(), is(PLACE));
        assertThat(foundEvent.getSpeaker(), is(SPEAKER));
        assertThat(foundEvent.getEventType(), is(EVENT_TYPE));
        assertThat(foundEvent.getDateTime(), is(DATE_TIME));

        entityManager.remove(savedEvent);
    }

    @Test
    @Rollback(value = false)
    void deleteEvent() {
        event = new Event(null, TITLE, PLACE, SPEAKER, EVENT_TYPE, DATE_TIME);
        Event savedEvent = repository.save(event);
        repository.delete(savedEvent);

        Event existEvent = entityManager.find(Event.class, savedEvent.getId());

        assertThat(existEvent, nullValue());
    }

    @Test
    @Rollback(value = false)
    void deleteEventById() {
        event = new Event(null, TITLE, PLACE, SPEAKER, EVENT_TYPE, DATE_TIME);
        Event savedEvent = repository.save(event);
        repository.deleteById(savedEvent.getId());

        Event existEvent = entityManager.find(Event.class, savedEvent.getId());

        assertThat(existEvent, nullValue());
    }

    @Test
    @Rollback(value = false)
    void findAllEvents() {
        event = new Event(null, TITLE, PLACE, SPEAKER, EVENT_TYPE, DATE_TIME);
        Event savedEvent = repository.save(event);

        List<Event> events = repository.findAll();

        assertThat(events.size(), is(1));
        assertThat(events.get(0), is(savedEvent));

        entityManager.remove(savedEvent);
    }

    @Test
    @Rollback(value = false)
    void getAllEventsByTitle() {
        event = new Event(null, TITLE, PLACE, SPEAKER, EVENT_TYPE, DATE_TIME);
        Event savedEvent1 = repository.save(event);
        event = new Event(null, TITLE, PLACE, SPEAKER, EVENT_TYPE, DATE_TIME);
        Event savedEvent2 = repository.save(event);

        List<Event> events = repository.findEventsByTitle(TITLE);

        assertThat(events.size(), is(2));
        assertThat(events.get(0), is(savedEvent1));
        assertThat(events.get(1), is(savedEvent2));

        entityManager.remove(savedEvent1);
        entityManager.remove(savedEvent2);
    }
}