package com.ymm.concurrent;

public class Main2 {
    private String name;
    private Integer age;
    private Object object;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    private static volatile Integer x = 1;
    private static volatile Main2 m = new Main2();
    static {
        m.setAge(1);
        m.setName("n");
        m.setObject(new Object());
    }
    public static void main(String[] args) throws InterruptedException {
        t1();
        System.out.println("-=-=-=-=-=");
        t2();
    }

    private static void t1() throws InterruptedException {
        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("sub");
                x = 2;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Integer xx = x;
        Thread.sleep(3000);
        System.out.println("main");
        System.out.println(xx);
        System.out.println(x);
    }

    private static void t2() throws InterruptedException {
        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("sub");
                m.setAge(2);
                m.setName("M");
                m.setObject(null);
            } catch (Exception e) {

            }
        }).start();
        Main2 mm = m;
        Thread.sleep(3000);
        System.out.println("main");
        System.out.println(mm.age + "; " + mm.name + "; " + mm.getObject());
        System.out.println(m.age + "; " + m.name + "; " + m.getObject());
    }
}
