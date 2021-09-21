package com.cache.lfu;

import com.cache.ICache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LFUCache extends EventManager implements ICache {

    private static final Logger LOG = LoggerFactory.getLogger(LFUCache.class);

    private static final long EXPIRE_TIME = 5L;
    private static final int MAX_SIZE = 100_000;

    private long duration = 0;
    private long settled = 0;
    private long evicted = 0;

    public static class Node {
        private String value;
        private int frequency;
        private final LocalDateTime time;
        private Node prev;
        private Node next;

        public Node(String value, int frequency) {
            this.value = value;
            this.frequency = frequency;
            this.time = LocalDateTime.now();
        }
    }

    private Node head;
    private Node tail;
    private final Map<String, Node> map;
    private final int capacity;

    public LFUCache() {
        this(MAX_SIZE);
    }

    public LFUCache(int maxSize) {
        capacity = (maxSize <= 0) ? 1 : maxSize;
        this.map = new HashMap<>();
    }

    @Override
    public synchronized void put(String value) {
        long start = System.nanoTime();

        Objects.requireNonNull(value);
        removeValuesWithExpireTime();
        if (map.containsKey(value)) {
            Node item = map.get(value);
            item.value = value;
            item.frequency++;
            removeNode(item);
            addNodeWithUpdatedFrequency(item);
        } else {
            if (map.size() >= capacity) {
                map.remove(head.value);
                try {
                    LOG.info("Was removed {}", head.value);
                    notifyListeners(head.value);
                } catch (Exception e) {
                    LOG.error("Cannot notify listeners {}", e.getMessage());
                }
                removeNode(head);
                evicted++;
            }
            Node node = new Node(value, 1);
            addNodeWithUpdatedFrequency(node);
            map.put(value, node);
        }
        settled++;
        duration += System.nanoTime() - start;
    }

    @Override
    public synchronized String get(String key) {
        Objects.requireNonNull(key);
        removeValuesWithExpireTime();
        if (map.get(key) == null) {
            return null;
        }
        Node node = map.get(key);
        removeNode(node);
        node.frequency++;
        addNodeWithUpdatedFrequency(node);

        return node.value;
    }

    @Override
    public long size() {
        return map.size();
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private void addNodeWithUpdatedFrequency(Node node) {
        if (Objects.nonNull(tail) && Objects.nonNull(head)) {
            Node temp = head;
            while (true) {
                if (temp.frequency > node.frequency) {
                    if (temp == head) {
                        node.next = temp;
                        temp.prev = node;
                        head = node;
                    } else {
                        node.next = temp;
                        node.prev = temp.prev;
                        temp.prev.next = node;
                    }
                    break;
                } else {
                    temp = temp.next;
                    if (temp == null) {
                        tail.next = node;
                        node.prev = tail;
                        node.next = null;
                        tail = node;
                        break;
                    }
                }
            }
        } else {
            tail = node;
            head = tail;
        }
    }

    private void removeValuesWithExpireTime() {
        Node node = head;
        while (node != null) {
            if (Duration.between(node.time, LocalDateTime.now()).getSeconds() > EXPIRE_TIME) {
                map.remove(node.value);
                LOG.info("Was removed {}", node.value);
                removeNode(node);
                evicted++;
            }
            node = node.next;
        }
    }

    @Override
    public void printStats() {
        LOG.info("Number of cache evictions - {}", evicted);
        LOG.info("Average time spent for putting new values into the cache - {} nanoseconds",
                (settled != 0) ? duration / settled : 0);
    }
}
