package com.coding.practice.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SingletonTest {
  public static void main(String[] args) throws Exception {
    Singleton s1 = Singleton.getInstance();

    // Serialize
    ObjectOutputStream out =
        new ObjectOutputStream(
            new FileOutputStream(
                "D:\\GIT\\java-playground\\java-coding-practice\\src\\main\\java\\com\\coding\\practice\\singleton\\singleton.obj"));
    out.writeObject(s1);
    out.close();

    // Deserialize
    ObjectInputStream in =
        new ObjectInputStream(
            new FileInputStream(
                "D:\\GIT\\java-playground\\java-coding-practice\\src\\main\\java\\com\\coding\\practice\\singleton\\singleton.obj"));
    Singleton s2 = (Singleton) in.readObject();
    in.close();

    System.out.println("s1 hash: " + s1.hashCode());
    System.out.println("s2 hash: " + s2.hashCode());
    System.out.println("Same instance? " + (s1 == s2));
  }
}
