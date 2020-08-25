package com.flying.middleware.rabbit.queue;

import java.util.LinkedList;

public class Storage implements AbstractStorage {
    //最大容量
    private final int MAX_SIZE = 100;
    //存储载体
    private LinkedList list =new LinkedList();

    @Override
    public void consumer(int num) {
        synchronized (list) {
            while (num > list.size()) {
                try {
                    list.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("【阻塞】当前要消费的数量：" + num + ",当前库存量：" + list.size() + "当前消费阻塞");
            }
            for (int i = 0; i < num; i++) {
                list.removeFirst();
            }
            System.out.println("【consumer】 "+Thread.currentThread().getName()+" 已消费产品数：" + num + ",现库存数：" + list.size());
            list.notifyAll();
        }
    }

    //生产
    @Override
    public void producer(int num) {
        synchronized (list) {
            while (list.size() + num > MAX_SIZE) {
                try {
                    list.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("【阻塞】当前队列已满，生产阻塞");
            }
            for (int i = 0; i < num; i++) {
                list.addLast(new Object());
            }
            System.out.println("【producer】 "+Thread.currentThread().getName()+ " 已生产产品数：" + num + ",现库存数：" + list.size());
            list.notifyAll();
        }
    }

}
