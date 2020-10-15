package com.flying.thread.sequence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("打开冰箱！");
            }
        });

        final Thread thread2 =new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("拿出一瓶牛奶！");
            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("关上冰箱！");
            }
        });
        executorService.submit(thread1);
        executorService.submit(thread2);
        executorService.submit(thread3);
        executorService.shutdown();        //使用完毕记得关闭线程池
    }

}
