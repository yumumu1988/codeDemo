package com.ymm.proxy;

/**
 *  静态代理类
 */
public class StaticProxyDemoClass implements DemoInterface{

    private DemoInterface demoInterface;

    public StaticProxyDemoClass(DemoInterface demoInterface) {
        this.demoInterface = demoInterface;
    }

    public String sayHello(String name) {
        System.out.println("before say hello");
        String hello = demoInterface.sayHello(name);
        System.out.println("after say hello");
        return hello;
    }
}
