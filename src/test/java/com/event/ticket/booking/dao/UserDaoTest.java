package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.User;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {

    private static final String USER_ID = "1";
    private static final String FIRST_NAME = "name";
    private static final String LAST_NAME = "surname";
    private static final String EMAIL = "email@gmail.com";
    private static final String NEW_EMAIL = "new_email@gmail.com";

    private final CommonDao<User> userDao = new UserDao();

    @Test
    public void saveUser() {
        User user = new User(USER_ID, FIRST_NAME, LAST_NAME, EMAIL);

        userDao.save(user);
        User savedUser = userDao.findAll().get(0);

        assertEquals(user, savedUser);
    }

    @Test
    public void updateUser() {
        User user = new User(USER_ID, FIRST_NAME, LAST_NAME, EMAIL);
        userDao.save(user);
        user.setEmail(NEW_EMAIL);

        userDao.save(user);

        User savedUser = userDao.findAll().get(0);
        assertEquals(user, savedUser);
        assertEquals(NEW_EMAIL, user.getEmail());
    }

    @Test
    public void delete() {
        User user = new User(USER_ID, FIRST_NAME, LAST_NAME, EMAIL);
        userDao.save(user);

        userDao.delete(user);

        assertEquals(0, userDao.findAll().size());
    }

    @Test
    public void findById() {
        User user = new User(USER_ID, FIRST_NAME, LAST_NAME, EMAIL);
        userDao.save(user);

        assertEquals(user, userDao.findById(user.getUserId()).orElse(new User()));
    }

    @Test
    public void findAll() {
        User user = new User(USER_ID, FIRST_NAME, LAST_NAME, EMAIL);
        userDao.save(user);

        assertEquals(Collections.singletonList(user), userDao.findAll());
    }

}