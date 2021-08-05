package com.ymm.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main4 {
    private static final Map<String, String> MAP = new HashMap<>();

    private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        MAP.put("!", "3");
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread1 try to acquire lock");
            REENTRANT_LOCK.lock();
            for (int i = 0; i < 5; i++) {
                work();
            }
            //  同一线程可重复获取锁资源
            REENTRANT_LOCK.lock();
            for (int i = 0; i < 2; i++) {
                work();
            }
            //  释放锁资源
            REENTRANT_LOCK.unlock();
            System.out.println("Thread1 unlock1");
            //  多次获取锁资源，需要多次释放
            REENTRANT_LOCK.unlock();
            System.out.println("Thread1 unlock2");
        }, "Thread1");
        Thread thread2 = new Thread(()->{
            System.out.println("Thread2222 try to acquire lock");
            try {
                long t1 = System.currentTimeMillis();
                //  有等待超时的获取锁，等待时间不会无限
                boolean lock = REENTRANT_LOCK.tryLock(500, TimeUnit.MILLISECONDS);
                long t2 = System.currentTimeMillis();
                System.out.println("Thread2222 lock state1: " + lock + "; wait: " + (t2 - t1));
                long t3 = System.currentTimeMillis();
                //  无等待时间的获取锁，直接返回结果
                boolean lock1 = REENTRANT_LOCK.tryLock();
                long t4 = System.currentTimeMillis();
                System.out.println("Thread2222 lock state2: " + lock1 + "; wait: " + (t4 - t3));
                long t5 = System.currentTimeMillis();
                //  无限等待获取锁
                REENTRANT_LOCK.lock();
                long t6 = System.currentTimeMillis();
                System.out.println("Thread2222 get lock. wait: " + (t6 - t5));
                for (int i = 0; i < 3; i++) {
                    work();
                }
                REENTRANT_LOCK.unlock();
                System.out.println("Thread2222 unlock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2222");
        thread1.start();
        Thread.sleep(500);
        thread2.start();
    }

    private static void work() {
        System.out.println("Current Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
