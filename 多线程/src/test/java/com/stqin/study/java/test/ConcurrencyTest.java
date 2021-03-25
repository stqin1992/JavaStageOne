package com.stqin.study.java.test;

public class ConcurrencyTest {
    private static final long count = 10;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count ; i++) {
                    try {
                        Thread.sleep(3000);
                        a += 5;
                        System.out.println("a--->"+a);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        thread.start();

        int b = 0;
        for (int i = 0; i < count ; i++) {
            Thread.sleep(3000);
            b--;
            System.out.println("b--->"+b);
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency: " + time + "ms,b=" + b);
    }
}
