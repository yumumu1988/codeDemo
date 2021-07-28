package com.ymm.proxy;

import java.lang.reflect.Proxy;

/**
 *  代理相关
 */
public class Main {
    public static void main(String[] args) {
        staticProxy();
        System.out.println("-=-=-=-=-=-=");
        dynamicProxy();
    }

    private static void dynamicProxy() {
        DemoClass demoClass = new DemoClass();
        DynamicProxyDemoClass dynamicProxyDemoClass = new DynamicProxyDemoClass(demoClass);
        DemoInterface proxy = (DemoInterface) Proxy.newProxyInstance(demoClass.getClass().getClassLoader(), new Class[]{DemoInterface.class}, dynamicProxyDemoClass);
        String hello = proxy.sayHello("DYNAMIC PROXY");
        System.out.println("dynamicProxy: " + hello);
    }

    private static void staticProxy() {
        DemoClass demoClass = new DemoClass();
        StaticProxyDemoClass staticProxyDemoClass = new StaticProxyDemoClass(demoClass);
        String hello = staticProxyDemoClass.sayHello("STATIC PROXY");
        System.out.println("staticProxy: " + hello);
    }

    private static Object dynamicProxyFactory(DemoClass demoClass) {
        return Proxy.newProxyInstance(demoClass.getClass().getClassLoader(), demoClass.getClass().getInterfaces(), new DynamicProxyDemoClass(demoClass));
    }
}
