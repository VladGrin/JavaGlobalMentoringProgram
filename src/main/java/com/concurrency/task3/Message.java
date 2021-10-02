package com.concurrency.task3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {

    @Getter
    private final String text;

    public static Message createMsg(String text) {
        return new Message(text);
    }
}
