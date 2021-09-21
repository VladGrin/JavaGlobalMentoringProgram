package com.cache.lfu;

import com.cache.ICache;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LFUCacheTest {

    private ICache cache;

    @Before
    public void init() {
        cache = new LFUCache(10);
    }

    @Test
    public void size() {
        cache.put("value");

        assertThat(cache.size(), is(1L));
    }

    @Test
    public void whenCacheMissThenValueIsComputed() {
        assertThat(cache.size(), is(0L));

        cache.put("value");

        assertThat(cache.get("value"), is("value"));
        assertThat(cache.size(), is(1L));
    }

    @Test
    public void whenCacheReachMaxSizeThenEvictionLeastFrequentlyUsed() {
        cache = new LFUCache(3);
        cache.put("first");
        cache.get("first");
        cache.put("second");
        cache.put("third");
        cache.get("third");
        cache.put("forth");

        assertThat(cache.size(), is(3L));
        assertThat(cache.get("second"), nullValue());
        assertThat(cache.get("forth"), is("forth"));
    }

    @Test
    public void whenEntryIdleThenEviction() throws InterruptedException {
        cache.put("hello");
        assertThat(cache.size(), is(1L));

        assertThat(cache.get("hello"), is("hello"));
        TimeUnit.SECONDS.sleep(6L);

        cache.put("test");
        assertThat(cache.size(), is(1L));
        assertThat(cache.get("hello"), nullValue());
        assertThat(cache.get("test"), is("test"));
    }
}