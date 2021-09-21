package com.cache.lru;

import com.cache.ICache;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LRUCacheTest {

    private ICache cache;

    @Before
    public void init() {
        cache = new LRUCache(10);
    }

    @Test
    public void whenCacheMissThenValueIsComputed() {
        assertEquals(0, cache.size());
        cache.put("value");
        assertEquals("value", cache.get("value"));
        assertEquals(1, cache.size());
    }

    @Test
    public void whenCacheReachMaxSizeThenEviction() {
        cache = new LRUCache(3);
        cache.put("first");
        cache.put("second");
        cache.put("third");
        cache.put("forth");

        assertEquals(3, cache.size());
        assertNull(cache.get("first"));
        assertEquals("forth", cache.get("forth"));
    }

    @Test
    public void whenEntryIdleThenEviction() throws InterruptedException {
        cache.put("hello");
        assertEquals(1, cache.size());

        assertEquals("hello", cache.get("hello"));
        TimeUnit.SECONDS.sleep(6L);

        cache.put("test");
        assertEquals(1, cache.size());
        assertNull(cache.get("hello"));
        assertEquals("test", cache.get("test"));
    }

    @Test
    public void size() {
        cache.put("value");
        assertEquals(1, cache.size());
    }
}