package com.test.lrucache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public final class LRUCacheValue extends EventManager {

    private static final Logger LOG = LoggerFactory.getLogger(LRUCacheValue.class);

    public static class Node {
        private String value;
        private Node prev;
        private Node next;

        public Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private static final int MAX_CAPACITY = 10;
    private final Map<String, Node> map;

    private Node head;
    private Node tail;

    public LRUCacheValue() {
        map = new HashMap<>(MAX_CAPACITY);
    }

    private void removeNode(Node node) {
        if (node == null)
            return;

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

    private void offerNode(Node node) {
        if (node == null)
            return;
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            node.next = null;
            tail = node;
        }
    }

    public synchronized void put(String value) {
        if (map.containsKey(value)) {
            Node node = map.get(value);
            node.value = value;
            removeNode(node);
            offerNode(node);
        } else {
            if (map.size() >= MAX_CAPACITY) {
                LOG.info("maxCapacity of cache reached");
                map.remove(value);
                try {
                    notifyListeners(head.value);
                } catch (Exception e) {
                    LOG.error("Cannot notify listeners {}", e.getMessage());
                }
                removeNode(head);
            }

            Node node = new Node(value);
            offerNode(node);
            map.put(value, node);
        }
    }

    public synchronized String get(String key) {
        Node node = map.get(key);
        removeNode(node);
        offerNode(node);
        return node != null ? node.value : null;
    }

    public void printCache() {
        Node node = head;
        StringBuilder log = new StringBuilder();
        while (node != null) {
            log.append(node.value).append(" -> ");
            node = node.next;
        }
        LOG.info(log.toString());
    }
}
