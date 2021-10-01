package com.test.lrucache;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class LRUCacheMemoryApplication {

    public static final Logger LOG = LoggerFactory.getLogger(LRUCacheMemoryApplication.class);

    public static void main(String[] args) {
        LRUCacheValue cache = new LRUCacheValue();
        cache.addListener(str -> LOG.info("deleted {}", str));

        for (int i = 1; i <= 10; i++) {
            cache.put(String.format("value-%d", i));
        }

        LOG.info("printing cache:");
        cache.printCache();

        final String s = cache.get("value-1");
        LOG.info("printing cache after accessing value-1: {}", s);
        cache.printCache();

        for (int i = 9; i <= 15; i++) {
            cache.put(String.format("value-%d", i));
        }

        LOG.info("printing cache after adding new objects:");
        cache.printCache();
    }
}
