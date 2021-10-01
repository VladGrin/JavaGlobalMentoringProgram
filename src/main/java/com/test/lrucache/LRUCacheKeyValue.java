package com.test.lrucache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public final class LRUCacheKeyValue<K, V> extends EventManager {

    private static final Logger LOG = LoggerFactory.getLogger(LRUCacheKeyValue.class);

    public static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> prev;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private final int maxCapacity;
    private final Map<K, Node<K, V>> map;

    private Node<K, V> head;
    private Node<K, V> tail;

    public LRUCacheKeyValue(int maxCapacity) {
        this(16, maxCapacity);
    }

    public LRUCacheKeyValue(int initialCapacity, int maxCapacity) {
        this.maxCapacity = maxCapacity;
        if (initialCapacity > maxCapacity)
            initialCapacity = maxCapacity;
        map = new HashMap<>(initialCapacity);
    }

    private void removeNode(Node<K, V> node) {
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

    private void offerNode(Node<K, V> node) {
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

    public synchronized void put(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;
            removeNode(node);
            offerNode(node);
        } else {
            if (map.size() == maxCapacity) {
                LOG.info("maxCapacity of cache reached");
                map.remove(head.key);
                try {
                    notifyListeners(head.value.toString());
                } catch (Exception e) {
                    LOG.error("Cannot notify listeners {}", e.getMessage());
                }
                removeNode(head);
            }

            Node<K, V> node = new Node<>(key, value);
            offerNode(node);
            map.put(key, node);
        }
    }

    public synchronized V get(K key) {
        Node<K, V> node = map.get(key);
        removeNode(node);
        offerNode(node);
        return node != null ? node.value : null;
    }

    public void printCache() {
        Node<K, V> curr = head;
        while (curr != null) {
            System.out.print(curr.value + " -> ");
            curr = curr.next;
        }
        System.out.println();
    }
}
