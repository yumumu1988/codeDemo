package com.ymm.reference;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static ReferenceQueue<Object> stringReferenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        t1();
        main();
        Object str = new Object();
        System.out.println("str: " + str);
//        SoftReference<Object> stringSoftReference = new SoftReference<>(str, stringReferenceQueue);
//        System.out.println("softStr: " + stringSoftReference.get());
        WeakReference<Object> weakReference = new WeakReference<>(str, stringReferenceQueue);
        System.out.println("weakStr: " + weakReference.get());
        System.out.println("release string");
        str = null;
        System.out.println("str: " + str);
//        System.out.println("softStr: " + stringSoftReference.get());
        System.out.println("weakStr: " + weakReference.get());
        System.gc();
        int read = System.in.read();
        Thread.sleep(1000);
        Reference<? extends Object> reference = stringReferenceQueue.poll();
        if (reference != null) {
            //  将软引用本身清空，用于GC回收软引用对象。防止内存溢出。
            reference.clear();
        }
//        System.out.println("softStr: " + stringSoftReference.get());
        System.out.println("weakStr: " + weakReference.get());
        int read2 = System.in.read();
//        System.out.println("softStr: " + stringSoftReference.get());
        System.out.println("weakStr: " + weakReference.get());
        int read3 = System.in.read();
//        System.out.println("softStr: " + stringSoftReference.get());
        System.out.println("weakStr: " + weakReference.get());
        List a = new ArrayList();
        a.add("1");
        List b = a;
        a.clear();
        //  赋值为null表示释放a对集合的引用
        a = null;
        System.out.println(b);
    }

    public static void main() {
        Object a = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(a);//软引用
        //a和弱引用指向同一个对象
        System.out.println(a);//java.lang.Object@4554617c
        System.out.println(weakReference.get());//java.lang.Object@4554617c 10
        //内存够用，弱引用也会被回收
        a = null;
        System.gc();//内存够用不会自动gc，手动唤醒gc
        System.out.println(a);//null
        System.out.println(weakReference.get());//null

        System.out.println("-=-=-=-=-=-=");
    }

    public static void t1() {
        String a = "123";
        System.out.println(a.hashCode());
        String b = "123";
        System.out.println(b.hashCode());
        System.out.println(a==b);
        String c = new String("123");
        System.out.println(c.hashCode());
        String d = new String("123");
        System.out.println(d.hashCode());
        System.out.println(c==d);
        System.out.println(a==d);;
    }
}
