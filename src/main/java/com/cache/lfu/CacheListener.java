package com.cache.lfu;

@FunctionalInterface
public interface CacheListener {

    void onDelete(String value);
}
