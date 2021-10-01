package com.test.lrucache;

@FunctionalInterface
public interface CacheListener {

    void onDelete(String value);
}
