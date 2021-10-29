package com.event.ticket.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
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
