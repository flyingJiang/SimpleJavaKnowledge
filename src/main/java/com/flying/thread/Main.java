package com.flying.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建线程有四种方式：
 *
 * 继承 Thread 类；
 * 实现 Runnable 接口；
 * 实现 Callable 接口；
 * 使用 Executors 工具类创建线程池
 */
public class Main {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock();

        ExecutorService service = Executors.newFixedThreadPool(1);
        service = Executors.newWorkStealingPool();

//        ForkJoinPool forkJoinPool =
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        executor.setRejectedExecutionHandler();
    }
}
