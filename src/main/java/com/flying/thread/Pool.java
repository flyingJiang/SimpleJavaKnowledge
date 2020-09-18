package com.flying.thread;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;

public class Pool {
    public static void main(String args[]) {
        Instant start = Instant.now();

        ExecutorService executor = Executors.newCachedThreadPool();
        Executors.newWorkStealingPool();
        //ForkJoinPool newWorkStealingPool
        //使用FutureTask包装callbale任务，再交给线程池执行
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++)
                sum += i;
            return sum;
        });
        //线程池执行任务 这个返回值不需要了，直接就在futureTask对象里面了
        executor.submit(futureTask);

        Instant mid = Instant.now();
        System.out.println("Mid拿到futureTask结果对象result：" + Duration.between(start, mid).toMillis());

        try {
            System.out.println("task运行结果计算的总和为：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        Instant end = Instant.now();
        System.out.println("End拿到结果值：" + Duration.between(start, end).toMillis());
    }
}
