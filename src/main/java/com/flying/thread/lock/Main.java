package com.flying.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 锁，顾名思义就是锁住一些资源，当只有我们拿到钥匙的时候，才能操作锁住的资源。在我们的Java，数据库，还有一些分布式的环境中，总是充斥着各种各样的锁让人头疼，例如“公平锁”、“自旋锁”、“读写锁”、“分布式锁”等等。
 * 其实真实的情况是，锁并没有那么多，很多概念只是从不同的功能特性，设计，以及锁的状态这些不同的侧重点来说明的，因此我们可以根据不同的分类来搞明白为什么会有这些“锁”？坐稳扶好了，准备开车。
 *
 * “公平锁”与“非公平锁”
 * “重入锁(递归锁)”与“不可重入锁（自旋锁）”
 *  “悲观锁”与“乐观锁”
 *  “共享锁”与“排他锁” 共享锁：也称读锁或S锁。  排它锁：也称独占锁、写锁或X锁
 *  分布式锁了，我们可以通过redis、zookeeper等中间件来实现分布式锁。
 */
public class Main {

    static int count  = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        SimpleSpinningLock simpleSpinningLock = new SimpleSpinningLock();
        for (int i = 0 ; i < 100 ; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    simpleSpinningLock.lock();
                    ++count;
                    simpleSpinningLock.unLock();
                    countDownLatch.countDown();
                }
            });

        }
        countDownLatch.await();
        System.out.println(count);
    }
}
/**
 * 自旋锁开启
 * 虽然在JDK1.4.2的时候就引入了自旋锁，但是需要使用“-XX:+UseSpinning”参数来开启。在到了JDK1.6以后，就已经是默认开启了。下面我们自己来实现一个基于CAS的简易版自旋锁。
 *
 通过上面的代码可以看出，自旋就是在循环判断条件是否满足，那么会有什么问题吗？如果锁被占用很长时间的话，自旋的线程等待的时间也会变长，
 白白浪费掉处理器资源。因此在JDK中，自旋操作默认10次，我们可以通过参数“-XX:PreBlockSpin”来设置，当超过来此参数的值，则会使用传统的线程挂起方式来等待锁释放。

 自适应自旋锁
 随着JDK的更新，在1.6的时候，又出现了一个叫做“自适应自旋锁”的玩意。它的出现使得自旋操作变得聪明起来，不再跟之前一样死板。
 所谓的“自适应”意味着对于同一个锁对象，线程的自旋时间是根据上一个持有该锁的线程的自旋时间以及状态来确定的。

 例如对于A锁对象来说，
 如果一个线程刚刚通过自旋获得到了锁，并且该线程也在运行中，那么JVM会认为此次自旋操作也是有很大的机会可以拿到锁，因此它会让自旋的时间相对延长。
 但是如果对于B锁对象自旋操作很少成功的话，JVM甚至可能直接忽略自旋操作。因此，自适应自旋锁是一个更加智能，对我们的业务性能更加友好的一个锁。
 **/
