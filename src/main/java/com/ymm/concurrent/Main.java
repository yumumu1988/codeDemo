package com.ymm.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {

    private static CountDownLatch countDownLatch;

    private static CyclicBarrier cyclicBarrier;

    static {
        countDownLatch = new CountDownLatch(2);
        cyclicBarrier = new CyclicBarrier(4);
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
//        countDownLatchDemo();
        cyclicBarrierDemo();
    }

    private static void countDownLatchDemo() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(1000 * new Random().nextInt(5));
                    countDownLatch.countDown();
                    System.out.println("Do sub task " + Thread.currentThread().getName() + " " + countDownLatch.getCount());
                } catch (Exception e) {

                }
            }).start();
        }
        System.out.println("Main task is waiting");
        countDownLatch.await();
        System.out.println("Main task is ready");
        Thread.sleep(1000);
        System.out.println("Main task is finished");
    }

    private static void cyclicBarrierDemo() throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                try {
                    System.out.println("Sub Task is waiting");
                    Thread.sleep(1000 * new Random().nextInt(10));
                    cyclicBarrier.await();
                    System.out.println("Sub Task starts");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("Main task is waiting");
        cyclicBarrier.await();
        System.out.println("Main task starts");
    }
}
