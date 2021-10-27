package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDao implements CommonDao<User> {

    private final Map<String, User> users = new HashMap<>();

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
