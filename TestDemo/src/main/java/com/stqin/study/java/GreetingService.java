package com.stqin.study.java;

public interface GreetingService {
    void sayMessage(String msg);

    default void defaultMethod(){
        System.out.println("defaultMethod ...");
    }

    static void println(){
        System.out.println("println.....");
    }
}
