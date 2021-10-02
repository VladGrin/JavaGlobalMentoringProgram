package com.concurrency.task3;

public class Main {
    public static void main(String[] args) {
        MessageBus messageBus = new MessageBus(5);
        new Thread(new MsgProducer(messageBus)).start();
        new Thread(new MsgProducer(messageBus)).start();
        new Thread(new MsgConsumer(messageBus)).start();
        new Thread(new MsgConsumer(messageBus)).start();
    }
}
