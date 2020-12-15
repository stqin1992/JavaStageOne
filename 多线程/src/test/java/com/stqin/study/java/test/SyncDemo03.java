package com.stqin.study.java.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Callable<Integer> {
    private  int ticket = 50;    // 假设一共有5张票


    public synchronized Integer sale() {    // 声明同步方法
        if (ticket > 0) {   // 还有票
            try {
                Thread.sleep(30); // 加入延迟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"卖票：ticket = " + ticket--);
            return ticket;
        } else {
            return 0;
        }

    }

    @Override
    public Integer call() {

        int sum = 0;
        for (int i = 0; i < 10; i++) {
             sum = this.sale();   // 调用同步方法
        }
        return sum;

    }
}

public class SyncDemo03 {
    public static void main(String args[]) {
        MyThread1 mt = new MyThread1();  // 定义线程对象
        FutureTask<Integer> task1 = new FutureTask<>(mt);
        FutureTask<Integer> task2 = new FutureTask<>(mt);
        FutureTask<Integer> task3 = new FutureTask<>(mt);

        Thread t1 = new Thread(task1,"A");    // 定义Thread对象
        Thread t2 = new Thread(task2,"B");    // 定义Thread对象
        Thread t3 = new Thread(task3,"C");    // 定义Thread对象
        t1.start();
        t2.start();
        t3.start();

        try {
            Integer shengyu = task3.get() > task2.get() ? task2.get() : task3.get() ;
             shengyu = shengyu > task1.get() ?task1.get() : shengyu;


            System.out.println(shengyu);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
