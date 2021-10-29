package com.event.ticket.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
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
