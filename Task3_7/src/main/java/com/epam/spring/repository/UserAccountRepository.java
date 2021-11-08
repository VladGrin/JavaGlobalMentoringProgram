package com.epam.spring.repository;

import com.epam.spring.domain.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {

    UserAccountEntity findByEmail(String email);
}
