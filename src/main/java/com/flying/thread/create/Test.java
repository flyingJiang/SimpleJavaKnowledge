package com.flying.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * https://www.cnblogs.com/zhou-test/p/9811771.html
 */
public class Test {
    public static void main(String[] args) {
        //1
//        Thread.currentThread().setName("main thread");
//        MyThread myThread = new MyThread();
//        myThread.setName("thread 1");
//        myThread.start();
//        for (int i = 0; i < 5; i++) {
//            System.out.println(Thread.currentThread().getName() + i);
//        }
        //2
        //设置线程名字
//        Thread.currentThread().setName("main thread:");
//        Thread thread = new Thread(new MyRunnable());
//        thread.setName("子线程:");
//        //开启线程
//        thread.start();
//        for(int i = 0; i <5;i++){
//            System.out.println(Thread.currentThread().getName() + i);
//        }
        //3
        //执行Callable 方式，需要FutureTask 实现实现，用于接收运算结果
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCallable());
//        new Thread(futureTask).start();
//        //接收线程运算后的结果
//        try {
//            Integer sum = futureTask.get();
//            System.out.println(sum);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        //4
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyRunnable myRunnable = new MyRunnable();
        for (int i = 0; i < 10; i++) {
            executorService.submit(myRunnable);
        }
        executorService.shutdown();


    }
}
/**
 * wait()：使线程处于一种等待状态，释放所持有的对象锁。
 * <p>
 * sleep()：使一个正在运行的线程处于睡眠状态，是一个静态方法，调用它时要捕获InterruptedException异常，不释放对象锁。
 * <p>
 * notify()：唤醒一个正在等待状态的线程。注意调用此方法时，并不能确切知道唤醒的是哪一个等待状态的线程，是由JVM来决定唤醒哪个线程，不是由线程优先级决定的。
 * <p>
 * notifyAll()：唤醒所有等待状态的线程，注意并不是给所有唤醒线程一个对象锁，而是让它们竞争。
 */

/**
 * 在JDK1.5之前，创建线程就只有两种方式，即继承java.lang.Thread类和实现java.lang.Runnable接口；
 * 而在JDK1.5以后，增加了两个创建线程的方式，即实现java.util.concurrent.Callable接口和线程池
 */
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

// 相较于实现Runnable 接口的实现，方法可以有返回值，并且抛出异常。
class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + i);
            sum += i;
        }
        return sum;
    }
}
