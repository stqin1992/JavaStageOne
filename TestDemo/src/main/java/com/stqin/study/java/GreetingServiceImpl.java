package com.stqin.study.java;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public void sayMessage(String msg) {
        System.out.println("sayMessage "+ msg);
    }
}
