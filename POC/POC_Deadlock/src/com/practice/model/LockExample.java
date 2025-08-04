package com.practice.model;

import java.util.concurrent.locks.ReentrantLock;
// Lock Prevents multiple threads from accessing a resource at the same time.
public class LockExample {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable task = () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the lock");
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            } finally {
                System.out.println(Thread.currentThread().getName() + " released the lock");
                lock.unlock();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }
}
