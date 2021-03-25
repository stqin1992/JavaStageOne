package com.stqin.study.java;

public class Test {
    public static void main(String[] args) {
        GreetingService service = new GreetingServiceImpl();
        service.defaultMethod();
        service.sayMessage("sss");
        GreetingService.println();
        GreetingService service1 = msg -> System.out.println("ssssssssss" + msg);
        service1.sayMessage("1111");
    }
}
