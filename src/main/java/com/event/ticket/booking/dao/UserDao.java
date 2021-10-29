package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.utils.DaoUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class UserDao implements CommonDao<User> {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    @Setter
    private String path;

    public void init() {
        try {
            users.putAll(DaoUtils.uploadUsers(path));
            log.info("PATH: {}, USERS DATA: {}", path, users);
        } catch (IOException e) {
            log.info("Can not upload users by path: {}", path);
        }
    }

    @Override
    public User save(User entity) {
        User result = users.get(entity.getUserId());
        if (Objects.nonNull(result)) {
            result.setFirstName(entity.getFirstName());
            result.setLastName(entity.getLastName());
            result.setEmail(entity.getEmail());
            entity = result;
        }
        users.put(entity.getUserId(), entity);
        log.info("Saved user to Map {}", entity);
        return entity;
    }

    @Override
    public List<User> save(Collection<User> entities) {
        entities.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(User entity) {
        users.remove(entity.getUserId());
        log.info("Removed user from Map {}", entity);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.of(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
