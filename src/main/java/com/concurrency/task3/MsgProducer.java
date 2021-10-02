package com.concurrency.task3;

import java.util.concurrent.TimeUnit;

public class MsgProducer implements Runnable {

    private final MessageBus messageBus;

    public MsgProducer(MessageBus messageBus) {
        this.messageBus = messageBus;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Message msg = Message.createMsg("Message " + i);
            messageBus.postMessage(msg);
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}