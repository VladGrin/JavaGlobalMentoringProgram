package com.concurrency.task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class MessageBus {

    private static final Logger LOG = LoggerFactory.getLogger(MessageBus.class);

    private final int capacity;
    private final Queue<Message> messages;

    public MessageBus(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity not valid");
        }
        this.capacity = capacity;
        this.messages = new LinkedList<>();
    }

    public synchronized void postMessage(Message msg) {
        while (messages.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        messages.offer(msg);
        LOG.info("-->{} Producer produced message: {} Size {}", Thread.currentThread().getName(), msg, messages.size());
        notifyAll();
    }

    public synchronized void getMessage() {
        while (messages.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message message = messages.poll();
        LOG.info("<--{} Consumer received message: {} Size {}", Thread.currentThread().getName(), message, messages.size());
        notifyAll();
    }
}