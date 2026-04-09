package com.coding.practice.serialization;

import java.io.*;

public class PreventSerialization implements Serializable {

  private String name = "Block me";

  private void writeObject(ObjectOutputStream out) throws IOException {
    throw new NotSerializableException("Serialization not allowed");
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    throw new NotSerializableException("Deserialization not allowed");
  }
}
