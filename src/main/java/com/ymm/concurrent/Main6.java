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
        Integer a = 333;
        Integer b = 333;
        System.out.println(a == b);
        Integer c = 33;
        Integer d = 33;
        System.out.println(c == d);
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        System.out.println(i4 == i5);
        Integer ii4 = Integer.valueOf(40);
        Integer ii5 = Integer.valueOf(40);
        System.out.println(ii4 == ii5);
    }
}
