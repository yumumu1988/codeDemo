package com.ymm.proxy;

public class DemoClass implements DemoInterface {

    private static final String WORD = "Hello ";

    public String sayHello(String name) {
        System.out.println(WORD + name);
        return WORD + name;
    }
}
