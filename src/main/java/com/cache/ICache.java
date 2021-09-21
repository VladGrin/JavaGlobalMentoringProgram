package com.cache;

public interface ICache {

    void put(String value);

    String get(String key);

    long size();

    void printStats();
}
