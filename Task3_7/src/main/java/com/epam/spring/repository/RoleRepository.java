package com.epam.spring.repository;

import com.epam.spring.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findRoleByName(String name);
}
