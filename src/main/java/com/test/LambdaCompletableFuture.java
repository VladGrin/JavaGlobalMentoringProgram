package com.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LambdaCompletableFuture {
    private static final UserDao userDao = new UserDao();
    private static final EventDao eventDao = new EventDao();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        User user = userDao.find("Vlad");
//        Event event = eventDao.find(1);
//        System.out.println(event.getUsers());
//        event.addUser(user);
//        System.out.println(event.getUsers());

        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> userDao.find("Vlad"));
        CompletableFuture<Event> eventFuture = CompletableFuture.supplyAsync(() -> eventDao.find(1));
        CompletableFuture<Event> conclusion = userFuture.thenCombine(eventFuture, (User user, Event event) -> {
            event.addUser(user);
            return event;
        });
        System.out.println(conclusion.get().getUsers());
        System.out.println(userFuture.get() );
    }

    @AllArgsConstructor
    @Getter
    @ToString
    private static class User {
        private final String name;
    }

    @AllArgsConstructor
    @Getter
    @ToString
    private static class Event {
        private final List<User> users;
        private final String eventName;

        public void addUser(User user) {
            users.add(user);
        }

        private List<User> getUsers() {
            return users;
        }
    }

    private static class UserDao {
        User find(String name) {
            return new User(name);
        }
    }

    private static class EventDao {
        Event find(int id) {
            return new Event(new ArrayList<>(), "AWS_Event " + id);
        }
    }
}
