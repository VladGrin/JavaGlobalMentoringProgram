package com.event.ticket.booking.service;

import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    void deleteUser(User user);

    User findUserById(String userId) throws EntityNotFoundException;

    List<User> findAllUsers();
}
