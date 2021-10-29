package com.event.ticket.booking.service.impl;

import com.event.ticket.booking.dao.CommonDao;
import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.UserService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private CommonDao<User> userDao;

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public User findUserById(String userId) throws EntityNotFoundException {
        return userDao.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }
}
