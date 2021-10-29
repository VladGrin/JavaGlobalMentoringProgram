package com.event.ticket.booking.service.impl;

import com.event.ticket.booking.dao.CommonDao;
import com.event.ticket.booking.domain.User;
import com.event.ticket.booking.exception.EntityNotFoundException;
import com.event.ticket.booking.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String USER_ID = "1";
    private static final String FIRST_NAME = "name";
    private static final String LAST_NAME = "surname";
    private static final String EMAIL = "email@gmail.com";
    @Mock
    private CommonDao<User> userDao;
    private UserService userService;
    private User user;

    @Before
    public void init() {
        userService = new UserServiceImpl(userDao);
        user = new User(USER_ID, FIRST_NAME, LAST_NAME, EMAIL);
    }

    @Test
    public void saveUser() {
        when(userDao.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        verify(userDao).save(user);
        assertEquals(user, savedUser);
    }

    @Test
    public void deleteUser() {
        userService.deleteUser(user);

        verify(userDao).delete(user);
    }

    @Test
    public void findUserById() throws EntityNotFoundException {
        when(userDao.findById(USER_ID)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserById(USER_ID);

        verify(userDao).findById(USER_ID);
        assertEquals(user, foundUser);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findUserByIdWhenEntityNotFound() throws EntityNotFoundException {
        when(userDao.findById(USER_ID)).thenReturn(Optional.empty());

        userService.findUserById(USER_ID);

        verify(userDao).findById(USER_ID);
    }

    @Test
    public void findAllUsers() {
        when(userDao.findAll()).thenReturn(Collections.singletonList(user));

        List<User> allUsers = userService.findAllUsers();

        verify(userDao).findAll();
        assertEquals(Collections.singletonList(user), allUsers);
    }
}