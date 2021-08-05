package com.ymm.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> hashTable = new Hashtable<>();
        String aNull = hashMap.put(null, "123");
        System.out.println("value: " + aNull);
        String putValue = hashMap.put(null, "123");
        System.out.println("value: " + putValue);
        //  HashTable key和value都不允许为null
//        hashTable.put(null, "null");
//        hashTable.put(1, null);
        //  HashSet实例化时，创建一个HashMap用于存储数据，用Key来存储Set value。
        Set<String> hashSet = new HashSet<>();
//        public HashSet() {
//            map = new HashMap<>();
//        }
        hashSet.add(null);
//        public boolean add(E e) {
//            return map.put(e, PRESENT)==null;
//        }

        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);
        Boolean aa = true;
        Boolean bb = true;
        System.out.println(aa.equals(bb));

//        ReentrantLock

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        int index = Collections.binarySearch(list, 3);
        int index2 = Collections.binarySearch(list, 3, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(index);
        System.out.println(index2);

        Comparable<Integer> value = 10;
        int compare = value.compareTo(1);
        System.out.println(compare);

        List<Integer> synchronizedList = Collections.synchronizedList(list);

        ReentrantLock reentrantLock = new ReentrantLock();
        String simpleName = Main.class.getSimpleName();
        System.out.println(simpleName);

        Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();

    }
}
