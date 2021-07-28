package com.ymm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *  动态代理类
 */
public class DynamicProxyDemoClass implements InvocationHandler {

    private DemoClass target;

    public DynamicProxyDemoClass(DemoClass target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before dynamic proxy");
        Object invoke = method.invoke(target, args);
        System.out.println("after dynamic proxy");
        return invoke;
    }
}
