package com.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LambdaSerializableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path path = Paths.get("object.txt");
        try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
            out.writeObject((Runnable & Serializable)() -> System.out.println("Test"));
        }
        try(ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            Runnable r = (Runnable) in.readObject();
            r.run();
        }
    }
}
