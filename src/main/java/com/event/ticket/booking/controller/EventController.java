package com.event.ticket.booking.controller;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(bookingFacade.getAllEvents());
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@ModelAttribute Event event) {
        return ResponseEntity.ok(bookingFacade.addEvent(event));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable String id) {
        try {
            return ResponseEntity.ok(bookingFacade.getEvent(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
//{
//        "eventId": "d0f61c11-4ed3-4e90-857f-486ace9e389a",
//        "eventName": "Java Conf",
//        "basePrice": 99.9
//        }