package com.ymm.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Template<Apple> appleTemplate = new Template<>();
        Apple apple = new Apple();
        apple.setName("APPLE");
        apple.setTaste("SWEET");
        apple.setCountry("CHINA");
        appleTemplate.setTemplate(apple);
        System.out.println(getInfo(appleTemplate));

        Template<Fruit> fruitTemplate = new Template<>();
        Fruit fruit = new Fruit();
        fruit.setName("FRUIT");
        fruit.setTaste("GOOD");
        fruitTemplate.setTemplate(fruit);
        System.out.println(getInfo(fruitTemplate));

        Template<Food> foodTemplate = new Template<>();
        Food food = new Food();
        food.setName("FOOD");
        foodTemplate.setTemplate(food);

        System.out.println(getCountryInfo(fruitTemplate));
        System.out.println(getCountryInfo(foodTemplate));

        //  定义了下线。所以不能读出（无法确定读出对象的类型，因为只要是Fruit的父类都可以）。
        //  可以写入。只要是Fruit的子类都可以。
        List<? super Fruit> list1 = new ArrayList<Fruit>();
        List<? super Fruit> list2 = new ArrayList<Food>();

        List<? super Fruit> fruitList2 = new ArrayList<>();
        fruitList2.add(fruit);
        fruitList2.add(apple);

        //  定义了上限。索引不能写入（无法确定写入对象的类型，因为只要是Fruit的子类都可以）
        //  可以读出。只要是Fruit的父类都可以（获取父类属性、方法）。
        List<? extends Fruit> list3 = new ArrayList<Fruit>();
        List<? extends Fruit> list4 = new ArrayList<Apple>();

        List<Fruit> fruitList = new ArrayList<>();
        List<Apple> appleList = new ArrayList<>();

        loopFood(fruitList);
        loopFood(appleList);

    }

    public static void loopFood(List<? extends Fruit> list) {
        list.forEach(e->{
            System.out.println(e.getName() + "; " + e.getTaste());
        });
    }

    public static String getInfo(Template<? extends Fruit> object) {
        return "NAME: " + object.getTemplate().getName() + "; TASTE: " + object.getTemplate().getTaste();
    }

    public static String getCountryInfo(Template<? super Fruit> object) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = object.getTemplate().getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals("getName")) {
                System.out.println(method.invoke(object.getTemplate(), null).toString());
            }
        }
        return object.getTemplate().toString();
    }

    public static <T extends Fruit> String getName(T t) {
        System.out.println("getName: " + t.getName() + "; " + t.getTaste());
        return t.getClass().getSimpleName();
    }

    public static <T extends Number, E extends Fruit> String getTaste(T t, E e) {
        return "Fruit: " + e.getName() + "; Price: " + t.doubleValue();
    }

}
