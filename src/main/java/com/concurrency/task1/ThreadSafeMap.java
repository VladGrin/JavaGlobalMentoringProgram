package com.concurrency.task1;

import lombok.NonNull;

import java.util.*;

public class ThreadSafeMap<K, V> implements Map<K, V> {
    private final Map<K, V> map;

    private ThreadSafeMap(Map<K, V> map) {
        this.map = map;
    }

    public static <K, V> ThreadSafeMap<K, V> getInstance(Map<K, V> map) {
        return new ThreadSafeMap<>(map);
    }

    @Override
    public synchronized int size() {
        return map.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public synchronized boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public synchronized boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public synchronized V get(Object key) {
        return map.get(key);
    }

    @Override
    public synchronized V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public synchronized V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public synchronized void putAll(@NonNull Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public synchronized void clear() {
        map.clear();
    }

    @Override
    @NonNull
    public synchronized Set<K> keySet() {
        return new HashSet<>(map.keySet());
    }

    @Override
    @NonNull
    public synchronized Collection<V> values() {
        return new LinkedList<>(map.values());
    }

    @Override
    @NonNull
    public synchronized Set<Entry<K, V>> entrySet() {
        return new HashSet<>(map.entrySet());
    }
}