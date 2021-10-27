package com.event.ticket.booking.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Event {

    private String eventId;
    private String eventName;
    private double basePrice;

    public Event() {
        eventId = UUID.randomUUID().toString();
    }

    public Event(String eventId) {
        this.eventId = eventId;
    }
}
