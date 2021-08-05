package com.ymm.concurrent;

/**
 * Thread.join()
 */
public class Main3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("sub thread start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sub thread finished");
        });
        System.out.println("run sub thread");
        thread.start();
        System.out.println("waiting sub thread");
        //  当前线程等待任务线程完成后再进行
        thread.join();
        System.out.println("run main thread");
    }
}
