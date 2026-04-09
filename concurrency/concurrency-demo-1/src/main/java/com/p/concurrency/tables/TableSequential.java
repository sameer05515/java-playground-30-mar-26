package com.p.concurrency.tables;

public class TableSequential {
    private static final int COUNT=100000;
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        myTask();
        long diff=System.currentTimeMillis()-start;

        System.out.println("---\nTime elapsed: "+diff);
    }

    public static void myTask(){
        for(int i=1;i<=COUNT;i++){
            for (int j = 1; j <= 10; j++) {
                System.out.print("\t\t\t"+(i*j));
            }
            System.out.println();
        }
    }
}
