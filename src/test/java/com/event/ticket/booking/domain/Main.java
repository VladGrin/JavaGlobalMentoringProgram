package com.event.ticket.booking.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.parse("2021-10-12T12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        System.out.println(ldt.getYear());
    }
}
