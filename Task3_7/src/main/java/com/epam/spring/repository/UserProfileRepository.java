package com.epam.spring.repository;

import com.epam.spring.domain.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {

    @Query("SELECT up FROM UserProfileEntity up WHERE up.userAccountEntity.email = :email")
    UserProfileEntity findByUserEmail(@Param("email") String email);

    UserProfileEntity findUserProfileEntitiesById(Long id);


}
