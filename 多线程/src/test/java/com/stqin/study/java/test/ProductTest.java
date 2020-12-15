package com.stqin.study.java.test;

/**
 * 线程通信的应用：生产者/消费者问题
 *
 * 生产者（Priductor）将产品交给店员（Clerk），而消费者（Customer）从店员处取走产品，店员一次只能持有固定数量的产品（比如20个），
 * 如果生产者视图生产更多的产品，店员会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产：如果店中没有产品了，店员会告诉消费者等一下，
 * 如果店中有产品了再通知消费者来取走产品。
 *
 * 1.是否是多线程问题？是的，有生产者线程和消费者线程（多线程的创建，四种方式）
 * 2.多线程问题是否存在共享数据？ 存在共享数据----产品（同步方法，同步代码块，lock锁）
 * 3.多线程是否存在线程安全问题？ 存在----都对共享数据产品进行了操作。（三种方法）
 * 4.是否存在线程间的通信，是，如果生产多了到20时，需要通知停止生产（wait）。（线程之间的通信问题，需要wait，notify等）
 */


class Clerk {

    private int productCount = 0;


    //生产产品
    public synchronized void produceProduct() {

        if (productCount < 20) {
            productCount++;

            System.out.println(Thread.currentThread().getName() + ":开始生产第" + productCount + "个产品");

            notify();
        } else {
            //当有20个时，等待wait
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //消费产品
    public synchronized void consumeProduct() {
        if (productCount > 0) {

            System.out.println(Thread.currentThread().getName() + ":开始消费第" + productCount + "个产品");

            productCount--;
            notify();
        } else {
            //当0个时等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 使用继承Thread方式创建线程
 * 1、实例不可以共享：一个实例只能建立一个线程，同一个实例不能让多个线程共享
 * 2、适用不需要共享资源的场景
 */
class Producer extends Thread {//生产者线程

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ";开始生产产品......");

        while (true) {
            clerk.produceProduct();
        }
    }
}

/**
 * 实现Runnable方式创建线程
 * 1、一个实例可以让多个线程共享
 * 2、适用需要共享资源的场景
 */
class Consumer implements Runnable {//消费者线程

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + ":开始消费产品");

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.consumeProduct();
        }

    }
}

public class ProductTest {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer p1 = new Producer(clerk);
        p1.setName("生产者1");

        Consumer c1 = new Consumer(clerk);
        Thread t1 = new Thread(c1);
        t1.setName("消费者1");
//        Thread t2 = new Thread(c1);
//        t2.setName("消费者2");

        p1.start();
        t1.start();
//        t2.start();


    }

}
