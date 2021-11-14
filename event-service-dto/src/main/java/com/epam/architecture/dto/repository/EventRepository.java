package com.epam.architecture.dto.repository;

import com.epam.architecture.dto.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventsByTitle(String title);

    void deleteById(Long id);
}
