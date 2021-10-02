package com.concurrency.task3;

import java.util.concurrent.TimeUnit;

public class MsgConsumer implements Runnable {

    private final MessageBus messageBus;

    public MsgConsumer(MessageBus messageBus) {
        this.messageBus = messageBus;
    }

    @Override
    public void run() {
        while (true) {
            messageBus.getMessage();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
