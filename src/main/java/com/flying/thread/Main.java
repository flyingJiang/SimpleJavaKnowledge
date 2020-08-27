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
//public class Main {
//    public static void main(String[] args) {
//        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.tryLock();
//
//        ExecutorService service = Executors.newFixedThreadPool(1);
//        service = Executors.newWorkStealingPool();
//
////        ForkJoinPool forkJoinPool =
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
////        executor.setRejectedExecutionHandler();
//    }
//}

//class ConstClass {
//    static {
//        System.out.println("ConstClass init");
//    }
//    public static final String HELLOWORLD = "hello world";
//}
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(ConstClass.HELLOWORLD);// 调用类常量
//    }
//}


class SuperClass {
    static {
        System.out.println("superclass init");
    }
    public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("subclass init");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(SubClass.value);// 被动应用1
        SubClass[] sca = new SubClass[10];// 被动引用2
        SubClass subClass = new SubClass();
    }
}
