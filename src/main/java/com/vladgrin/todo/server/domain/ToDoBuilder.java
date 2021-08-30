package com.vladgrin.todo.server.domain;

public class ToDoBuilder {

    private static final ToDoBuilder INSTANT = new ToDoBuilder();
    private String id = null;
    private String description = "";

    private ToDoBuilder() {
    }

    public static ToDoBuilder create() {
        return INSTANT;
    }

    public ToDoBuilder withDescription(String description) {
        this.description = description;
        return INSTANT;
    }

    public ToDoBuilder withId(String id) {
        this.id = id;
        return INSTANT;
    }

    public ToDo builder() {
        ToDo result = new ToDo(this.description);
        if (id != null) {
            result.setId(id);
        }
        return result;
    }
}
