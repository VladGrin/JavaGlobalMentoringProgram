package com.epam.spring.repository;

import com.epam.spring.domain.UserProfileEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository repository;

    @Test
    public void findByUserEmail() {
        UserProfileEntity user = repository.findByUserEmail("test@test.com");

        assertEquals(1L, user.getId().longValue());
        assertEquals("TEST", user.getFirstName());
        assertEquals("TEST", user.getLastName());
    }

    @Test
    public void findUserProfileEntitiesById() {
        UserProfileEntity user = repository.findUserProfileEntitiesById(1L);

        assertEquals("TEST", user.getFirstName());
        assertEquals("TEST", user.getLastName());
    }
}