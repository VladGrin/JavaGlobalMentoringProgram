package com.event.ticket.booking.dao;

import com.event.ticket.booking.domain.ToDo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ToDoDao implements CommonDao<ToDo> {

    private static final Map<String, ToDo> toDos = new HashMap<>();

    {
        toDos.put("123", new ToDo("Buy Milk"));
        toDos.put("234", new ToDo("Read a book"));
    }

    @Override
    public ToDo save(ToDo entity) {
        ToDo result = toDos.get(entity.getId());
        if(result != null){
            result.setModified(LocalDateTime.now());
            result.setDescription(entity.getDescription());
            result.setCompleted(entity.isCompleted());
            entity = result;
        }
        toDos.put(entity.getId(), entity);
        return toDos.get(entity.getId());
    }

    @Override
    public List<ToDo> save(Collection<ToDo> entities) {
        entities.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDo entity) {
        toDos.remove(entity.getId());
    }

    @Override
    public Optional<ToDo> findById(String id) {
        return Optional.of(toDos.get(id));
    }

    @Override
    public List<ToDo> findAll() {
        return toDos.entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().getCreated()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
