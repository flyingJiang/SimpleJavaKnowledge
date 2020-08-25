package com.flying.middleware.rabbit.queue;

public class Test2 {
    public static void main(String[] args) {
        AbstractStorage abstractStorage =new Storage();

        //生产者对象
        Producer p1 = new Producer(abstractStorage,10);
        Producer p2 = new Producer(abstractStorage,10);
        Producer p3 = new Producer(abstractStorage,10);
        Producer p4 = new Producer(abstractStorage,10);
        Producer p5 = new Producer(abstractStorage,10);
        Producer p6 = new Producer(abstractStorage,10);
        Producer p7 = new Producer(abstractStorage,10);
        Producer p8 = new Producer(abstractStorage,50);
        //消费者对象
        Consumer c1 = new Consumer(abstractStorage,20);
        Consumer c2 = new Consumer(abstractStorage,30);
        Consumer c3 = new Consumer(abstractStorage,50);

        c1.start();
        c2.start();
        c3.start();

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
        p8.start();

    }



}
