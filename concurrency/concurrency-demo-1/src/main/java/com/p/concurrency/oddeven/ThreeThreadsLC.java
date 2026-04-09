package com.p.concurrency.oddeven;

import java.util.concurrent.locks.*;

public class ThreeThreadsLC {

    static int num = 1;
    static final int MAX = 30;

    static final Lock lock = new ReentrantLock();
    static final Condition c1 = lock.newCondition();
    static final Condition c2 = lock.newCondition();
    static final Condition c3 = lock.newCondition();

    public static void main(String[] args) {
        new Thread(ThreeThreadsLC::t1, "T1").start();
        new Thread(ThreeThreadsLC::t2, "T2").start();
        new Thread(ThreeThreadsLC::t3, "T3").start();
    }

    static void t1() {
        while (true) {
            lock.lock();
            try {
                while (num % 3 != 1 && num <= MAX) c1.await();

                if (num > MAX) {
                    c2.signal(); c3.signal();
                    break;
                }

                System.out.print(Thread.currentThread().getName() + ":" + num + " ");
                num++;
                c2.signal();   // next → T2

            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }
    }

    static void t2() {
        while (true) {
            lock.lock();
            try {
                while (num % 3 != 2 && num <= MAX) c2.await();

                if (num > MAX) {
                    c1.signal(); c3.signal();
                    break;
                }

                System.out.print(Thread.currentThread().getName() + ":" + num + " ");
                num++;
                c3.signal();   // next → T3

            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }
    }

    static void t3() {
        while (true) {
            lock.lock();
            try {
                while (num % 3 != 0 && num <= MAX) c3.await();

                if (num > MAX) {
                    c1.signal(); c2.signal();
                    break;
                }

                System.out.print(Thread.currentThread().getName() + ":" + num + " ");
                num++;
                c1.signal();   // next → T1

            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }
    }
}