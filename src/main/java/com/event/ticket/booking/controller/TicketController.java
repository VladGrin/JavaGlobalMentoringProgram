package com.event.ticket.booking.controller;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.BookingFacade;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Setter
    private BookingFacade bookingFacade;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Ticket>> getTicketByUser(@PathVariable String id) {
        return ResponseEntity.ok(bookingFacade.getTicketsByUser(new User(id)));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<Ticket>> getTicketByEvent(@PathVariable String id) {
        return ResponseEntity.ok(bookingFacade.getTicketsByEvent(new Event(id)));
    }

    @PostMapping
    public ResponseEntity<Ticket> bookEvent(@RequestParam("userId") String userId,
                                            @RequestParam("eventId") String eventId) {
        return ResponseEntity.ok(bookingFacade.bookEvent(new User(userId), new Event(eventId),
                LocalDateTime.of(2020, 10, 9, 8, 7, 6)));
    }

    @DeleteMapping
    public ResponseEntity<Ticket> cancelBooking(@RequestParam("userId") String userId,
                                                @RequestParam("eventId") String eventId) {
        try {
            return ResponseEntity.ok(bookingFacade.cancelBooking(new User(userId), new Event(eventId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

