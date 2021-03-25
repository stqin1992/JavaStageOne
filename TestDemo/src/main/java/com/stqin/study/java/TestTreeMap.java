package com.stqin.study.java;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TestTreeMap {
    public static void main(String[] args) {
//        TreeMap<Integer, String> treemap = new TreeMap<>();
//
//        treemap.put(2, "two");
//        treemap.put(1, "one");
//        treemap.put(3, "three");
//        treemap.put(4, "six");
//        treemap.put(5, "five");
//
//        //输出小于等于4对最大对key
//        System.out.println("Value is: "+ treemap.floorKey(6));
//        //输出小于等于2对最大key=value
//        System.out.println("Value is: "+ treemap.floorEntry(6));
//        //输出最小对key=value
//        System.out.println("Value is: "+ treemap.firstEntry());
        List<String> l1 = new ArrayList();
        List<String> l2 = new ArrayList();
        l1.add("111");
        l1.add("222");
        String s = l1.get(0);
        l2.add(s);
        s = l1.get(1);
        String s1 = l2.get(0);

        System.out.println(s);
        System.out.println(s1);
    }
}
