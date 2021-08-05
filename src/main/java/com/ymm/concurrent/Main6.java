package com.ymm.concurrent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  读写锁
 */
public class Main6 {

    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        //  ReentrantReadWriteLock包含了两个锁对象，一个是读锁一个是写锁。锁的资源争抢还是基于AQS实现。
    }
}
