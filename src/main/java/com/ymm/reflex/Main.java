package com.ymm.reflex;

/**
 *  反射、静态代码块、类加载
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("Main");
//        t1();
//        t2();
        t3();
    }

    /**
     *  静态代码块->静态变量赋值
     *  静态代码块和静态变量复制只会执行一次。在类加载时执行。
     * STATIC BLOCK
     * STATIC METHOD
     * INSTANCE BLOCK
     * CONSTRUCTOR
     * ----------
     * ==========
     * INSTANCE BLOCK
     * CONSTRUCTOR
     */
    private static void t1() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DemoObject demoObject = new DemoObject();
        System.out.println("----------");
        Class<?> aClass = Class.forName("com.ymm.reflex.DemoObject");
        System.out.println("==========");
        DemoObject demoObject1 = (DemoObject)aClass.newInstance();
    }

    /**
     *  Class.forName执行的是类加载初始化，加载类信息执行静态代码块和静态变量赋值
     *  class.newInstance执行类的实例化。执行非静态代码块->构造函数
     * ----------
     * STATIC BLOCK
     * STATIC METHOD
     * ==========
     * INSTANCE BLOCK
     * CONSTRUCTOR
     */
    private static void t2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("----------");
        Class<?> aClass = Class.forName("com.ymm.reflex.DemoObject");
        System.out.println("==========");
        DemoObject demoObject1 = (DemoObject)aClass.newInstance();
    }

    /**
     *  类加载器加载获取类对象时不会初始化类，不会执行静态代码块和静态变量赋值
     * ----------
     * ==========
     * STATIC BLOCK
     * STATIC METHOD
     * INSTANCE BLOCK
     * CONSTRUCTOR
     */
    private static void t3() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("----------");
        Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.ymm.reflex.DemoObject");
        System.out.println("==========");
        DemoObject demoObject = (DemoObject) aClass.newInstance();
    }

    /**
     *  Class.forName()与ClassLoader.loadClass()的主要区别：类加载器获取类对象时不进行初始化。
     */
}
