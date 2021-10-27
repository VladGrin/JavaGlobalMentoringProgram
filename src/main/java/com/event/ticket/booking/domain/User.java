package com.event.ticket.booking.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
        userId = UUID.randomUUID().toString();
    }

    public User(String userId) {
        this.userId = userId;
    }
}
