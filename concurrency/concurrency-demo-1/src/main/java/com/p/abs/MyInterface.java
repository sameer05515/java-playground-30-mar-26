package com.p.abs;

public interface MyInterface {
    void run();
    public static void main(String[] args) {
        MyInterface myInterface=()-> System.out.println("Implementation");
        myInterface.run();
    }
}
