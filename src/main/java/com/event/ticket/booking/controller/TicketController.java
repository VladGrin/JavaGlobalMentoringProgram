package com.event.ticket.booking.controller;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.service.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
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
    public ResponseEntity<Ticket> saveTicket(@RequestParam("userId") String userId,
                                             @RequestParam("eventId") String eventId) {
        return ResponseEntity.ok(bookingFacade.buyTicket(new User(userId), new Event(eventId),
                LocalDateTime.of(2020, 10, 9, 8, 7, 6)));
    }
}
//{
//        "ticketId": "a7fe75f1-a589-4f8a-80db-219a0ab1dc9d",
//        "userId": "45c6eff5-792e-46f0-b293-59115e18239e",
//        "eventId": "d0f61c11-4ed3-4e90-857f-486ace9e389a",
//        "dateTime": {
//        "dayOfMonth": 9,
//        "hour": 8,
//        "minute": 7,
//        "month": "OCTOBER",
//        "monthValue": 10,
//        "nano": 0,
//        "second": 6,
//        "year": 2020,
//        "dayOfWeek": "FRIDAY",
//        "dayOfYear": 283,
//        "chronology": {
//        "id": "ISO",
//        "calendarType": "iso8601"
//        }
//        }
//        }
