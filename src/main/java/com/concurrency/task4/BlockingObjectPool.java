package com.concurrency.task4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Pool that block when it has not any items or it full
 */
public class BlockingObjectPool {

    private final BlockingQueue<Object> queue;

    /**
     * Creates filled pool of passed size * * @param size of pool
     */
    public BlockingObjectPool(int size) {
        queue = new ArrayBlockingQueue<>(size);
    }

    /**
     * Gets object from pool or blocks if pool is empty * * @return object from pool
     */
    public Object get() {
        try {
            Object obj = queue.take();
            System.out.println("Received msg " + obj + " Size: " + queue.size());
            return obj;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Puts object to pool or blocks if pool is full * * @param object to be taken back to pool
     */
    public void take(Object object) {
        try {
            queue.put(object);
            System.out.println("Send msg " + object + " Size: " + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
