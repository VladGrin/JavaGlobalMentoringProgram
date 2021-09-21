package com.cache.lfu;

import java.util.ArrayList;
import java.util.List;

public abstract class EventManager {

    private final List<CacheListener> listeners = new ArrayList<>();

    public void addListener(CacheListener listener) {
        listeners.add(listener);
    }

    public void removeListener(CacheListener listener) {
        listeners.remove(listener);
    }

    protected void notifyListeners(String value){
        listeners.forEach(listener -> listener.onDelete(value));
    }
}
