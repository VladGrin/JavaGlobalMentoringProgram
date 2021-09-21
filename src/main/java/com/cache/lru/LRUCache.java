package com.cache.lru;

import com.cache.ICache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Objects;

public class LRUCache implements ICache {

    private static final Logger LOG = LoggerFactory.getLogger(LRUCache.class);

    private static final long EXPIRE_TIME = 5L;
    private static final int MAX_SIZE = 100_000;
    private static final int NUM_OF_THREADS = Runtime.getRuntime().availableProcessors();
    private final LoadingCache<String, String> cache;

    private static final CacheLoader<String, String> LOADER = new CacheLoader<String, String>() {
        @Override
        public String load(String key) {
            return key;
        }
    };

    private static final RemovalListener<String, String> LISTENER = n -> {
        if (n.wasEvicted()) {
            LOG.info("Was removed: {}", n.getValue());
        }
    };

    public LRUCache() {
        this(MAX_SIZE);
    }

    public LRUCache(int maxSize) {
        if (maxSize <= 0) {
            maxSize = 1;
        }
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterAccess(Duration.ofSeconds(EXPIRE_TIME))
                .removalListener(LISTENER)
                .concurrencyLevel(NUM_OF_THREADS)
                .recordStats()
                .build(LOADER);
    }

    @Override
    public synchronized void put(String value) {
        Objects.requireNonNull(value);

        cache.getUnchecked(value);
    }

    @Override
    public synchronized String get(String key) {
        Objects.requireNonNull(key);

        return cache.getIfPresent(key);
    }

    @Override
    public long size() {
        return cache.size();
    }

    @Override
    public void printStats() {
        LOG.info("Number of cache evictions - {}", cache.stats().evictionCount());
        LOG.info("Average time spent for putting new values into the cache - {} nanoseconds", cache.stats().averageLoadPenalty());
    }
}
