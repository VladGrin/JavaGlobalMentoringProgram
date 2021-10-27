package com.event.ticket.booking.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Ticket {

    private String ticketId;
    private String userId;
    private String eventId;
    private LocalDateTime dateTime;

    public Ticket() {
        ticketId = UUID.randomUUID().toString();
    }

    public Ticket(String ticketId) {
        this.ticketId = ticketId;
    }
}
