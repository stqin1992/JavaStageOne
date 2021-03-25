package com.stqin.study.java;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class DistinctByProperty {

    public static void main(String[] args) {
        List<Book> list = new ArrayList<>();
        {
            list.add(new Book("Core Java", 200));
            list.add(new Book("Core Java", 300));
            list.add(new Book("Learning Freemarker", 150));
            list.add(new Book("Spring MVC", 200));
            list.add(new Book("Hibernate", 300));
        }
        list.stream().filter(new Predicate<Book>() {
            Map<Object,Boolean> seen = new ConcurrentHashMap<>();
            @Override
            public boolean test(Book t) {
                return seen.putIfAbsent(t.getName(), Boolean.TRUE) == null;
            }
        }).forEach(b -> System.out.println(b.getName()+ "," + b.getPrice()));

//        list.stream().filter(distinctByKey(b -> b.getName()))
//                .forEach(b -> System.out.println(b.getName()+ "," + b.getPrice()));
    }
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
