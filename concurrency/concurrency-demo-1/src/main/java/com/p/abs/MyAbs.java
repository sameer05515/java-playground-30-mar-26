package com.p.abs;

public abstract class MyAbs {

    public abstract void run();

    public static void main(String[] args) {
        MyAbs myAbs=new MyAbs() {
            @Override
            public void run() {
                System.out.println("I am MyAbs child");
            }
        };

        myAbs.run();;
    }
}
