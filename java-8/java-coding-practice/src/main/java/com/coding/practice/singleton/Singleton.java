package com.coding.practice.singleton;

import java.io.*;

class Singleton implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final Singleton INSTANCE = new Singleton();

  private Singleton() {
    System.out.println("Singleton Constructor");
  }

  public static Singleton getInstance() {
    return INSTANCE;
  }

  // Ensure same instance is returned after deserialization
  protected Object readResolve() {
    return INSTANCE;
  }
}
