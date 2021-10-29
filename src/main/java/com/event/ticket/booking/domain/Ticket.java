package com.event.ticket.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
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
