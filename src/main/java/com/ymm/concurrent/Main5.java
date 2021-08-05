package com.ymm.concurrent;

/**
 *  前台线程、后台线程
 */
public class Main5 {
    public static void main(String[] args) {
//        t1();
        t2();
    }

    private static void t1() {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("sub thread is running");
                Thread.sleep(5000);
                System.out.println("sub thread is stopped");
            } catch (Exception e) {

            }
        });
        //  默认为前台线程
        thread.start();
        //  子线程执行完后，进程结束
        System.out.println("main thread is alive");
    }

    private static void t2() {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("sub thread is running");
                Thread.sleep(5000);
                System.out.println("sub thread is stopped");
            } catch (Exception e) {

            }
        });
        //  设置为守护线程-后台线程
        thread.setDaemon(true);
        thread.start();
        //  主线程执行完后，进程结束
        System.out.println("main thread is alive");
    }
}
