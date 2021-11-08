package com.epam.spring.repository;

import com.epam.spring.domain.RoleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findRoleByNameAdmin() {
        RoleEntity admin = roleRepository.findRoleByName("ROLE_ADMIN");

        assertEquals(2L, admin.getId().longValue());
        assertEquals("ROLE_ADMIN", admin.getName());
    }
}