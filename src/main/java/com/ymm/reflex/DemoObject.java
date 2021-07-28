package com.ymm.reflex;

public class DemoObject {

    public static String NAME;

    static {
        System.out.println("STATIC BLOCK");
        NAME = "DemoObject";
    }

    public static Integer VALUE = calculate();

    private static Integer calculate() {
        System.out.println("STATIC METHOD");
        return 100;
    }

    private String title;

    {
        System.out.println("INSTANCE BLOCK");
        title = "OBJECT TITLE";
    }

    public DemoObject() {
        System.out.println("CONSTRUCTOR");
    }

    public String getTitle() {
        return title;
    }
}
