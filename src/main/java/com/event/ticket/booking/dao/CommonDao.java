package com.event.ticket.booking.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommonDao<T> {

    T save(T entity);

    List<T> save(Collection<T> entities);

    void delete(T id);

    Optional<T> findById(String id);

    List<T> findAll();
}
