package com.stqin.study.java.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread2 implements Callable<Integer>{
    private int sum = 0;

    @Override
    public Integer call() {
        for(int i = 0;i<=100;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
                sum += i;
            }
        }
        return sum;
    }
}

public class MyCallable {
    public static void main(String[] args) {
        //new一个实现callable接口的对象
        MyThread2 numThread = new MyThread2();

        //通过futureTask对象的get方法来接收futureTask的值
        FutureTask<Integer> futureTask = new FutureTask(numThread);

        Thread t1 = new Thread(futureTask);
        t1.setName("线程1");
        t1.start();

        try {
            //get返回值即为FutureTask构造器参数callable实现类重写的call的返回值
            Integer sum = futureTask.get();
            System.out.println(Thread.currentThread().getName()+":"+sum);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
