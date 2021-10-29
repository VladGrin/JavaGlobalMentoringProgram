package com.event.ticket.booking.utils;

import com.event.ticket.booking.domain.Event;
import com.event.ticket.booking.domain.Ticket;
import com.event.ticket.booking.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class DaoUtils {

    private DaoUtils() {
    }

    public static Map<String, User> uploadUsers(String path) throws IOException {
        Map<String, User> users = new HashMap<>();
        try (InputStream in = Objects.requireNonNull(DaoUtils.class.getClassLoader().getResourceAsStream(path));
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(",");
                users.put(str[0], new User(str[0], str[1], str[2], str[3]));
            }
        }
        return users;
    }

    public static Map<String, Event> uploadEvents(String path) throws IOException {
        Map<String, Event> events = new HashMap<>();
        try (InputStream in = Objects.requireNonNull(DaoUtils.class.getClassLoader().getResourceAsStream(path));
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(",");
                events.put(str[0], new Event(str[0], str[1], Double.parseDouble(str[2])));
            }
        }
        return events;
    }

    public static Map<String, Ticket> uploadTickets(String path) throws IOException {
        Map<String, Ticket> tickets = new HashMap<>();
        try (InputStream in = Objects.requireNonNull(DaoUtils.class.getClassLoader().getResourceAsStream(path));
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(",");
                tickets.put(str[0], new Ticket(str[0], str[1], str[2],
                        LocalDateTime.of(Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]),
                                Integer.parseInt(str[6]), Integer.parseInt(str[7]), Integer.parseInt(str[8]))));
            }
        }
        return tickets;
    }
}
