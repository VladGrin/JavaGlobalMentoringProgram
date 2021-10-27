package com.event.ticket.booking.controller;

import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(bookingFacade.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@ModelAttribute User user) {
        return ResponseEntity.ok(bookingFacade.addUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok(bookingFacade.getUser(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
//[
//        {
//        "userId": "45c6eff5-792e-46f0-b293-59115e18239e",
//        "firstName": "Matv",
//        "lastName": "Grin",
//        "email": "matv436887@gmail.com"
//        }
//        ]