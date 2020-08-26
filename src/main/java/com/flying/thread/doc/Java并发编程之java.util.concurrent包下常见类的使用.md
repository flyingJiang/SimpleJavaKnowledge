# [Java并发编程之java.util.concurrent包下常见类的使用](https://www.cnblogs.com/miketwais/p/java_util_concurrent.html)

## 一，Condition
   创建方式：通过Lock创建，Lock.newCondition()；
   常用方法：
   await()：阻塞，直到相同的Condition调用了signal方法。
   signal()：通知。
   总结：Condition必须与Lock一起使用（wait()、notify()必须与synchronized一起使用，否则运行会报错java.lang.IllegalMonitorStateException），相比于wait与notify更加的灵活，可以设置各种情形，如上例中的到达3和到达6两个条件。

二，CountDownLatch
创建方式：直接创建，new CountDownLatch(int num);

常用方法：
await()：阻塞，直到countDown方法被执行了num次。
countDown()：减
总结：适用于一个线程等待其他线程的情景。

三，CyclicBarrier

与CountDownLatch有什么区别？
CyclicBarrier强调的是n个线程，大家相互等待，只要有一个没完成，所有人都得等着。正如上例，只有5个人全部跑到终点，大家才能开喝，否则只能全等着。
CountDownLatch强调一个线程等多个线程完成某件事情。CyclicBarrier是多个线程互等，等大家都完成。

另外：
1.CountDownLatch减计数，CyclicBarrier加计数。
2.CountDownLatch是一次性的，CyclicBarrier可以重用。

创建方式：直接创建，new CyclicBarrier(int num);

常用方法：
await()：阻塞，直到阻塞的线程数量达到num个。
总结：想想一下百米跑，所有运动员都就位之后才会发令起跑，线程调用await意味着说，我准备好了。

四，Semaphore
创建方式：直接创建，new Semaphore(int num);

常用方法：
availablePermits()：看现在可用的信号量。
acquire()：尝试获取一个位置，如果获取不到则阻塞。
release()：释放位置。
acquireUninterruptibly(int num)：尝试获取num个许可，如果没有足够的许可则阻塞，一直阻塞到有足够的许可释放出来。调用这个方法的线程具有优先获取许可的权利。如果调用线程被interrupted,该线程并不会被打断，它会继续阻塞等待许可。
总结：抢位置。

五，ReentrantLock
创建方式：
new ReentrantLock(); 此种创建方式会创建出一个非公平锁。
new ReentrantLock(true); 此种方式会创建出一个公平锁。
非公平锁：当锁处于无线程占有的状态，此时其他线程和在队列中等待的线程都可以抢占该锁。
公平锁：当锁处于无线程占有的状态，在其他线程抢占该锁的时候，都需要先进入队列中等待。
tryLock()方法：尝试去获取锁，如果没有获取到直接返回，不等待。

六，ReentrantReadWriteLock
创建方式：new ReentrantReadWriteLock();
常用方法：
readLock().lock();写锁
writeLock().lock();读锁
readLock().unlock();解锁
writeLock().unlock();解锁
总结：
 * 如果目前是读锁，其他读锁也可以进请求，写锁不能进。
 * 如果目前是写锁，那么其他所有的锁都不可以进。
 * 适用于读多写少的情况，如果是写多读少用ReentrantLock。

七，Callable接口
*Callable接口支持返回执行结果，此时需要调用FutureTask.get()方法实现，此方法会阻塞主线程直到获取结果；当不调用此方法时，主线程不会阻塞！
与Runnable对比:
1.Callable可以有返回值，Runnable没有
2.Callable接口的call()方法允许抛出异常；而Runnable接口的run()方法的异常只能在内部消化，不能继续上抛；

八，线程池
提供的线程池有几种：
//有数量限制的线程池
ExecutorService service=Executors.newFixedThreadPool(4);
//没有数量限制的线程池
ExecutorService service=Executors.newCachedThreadPool();
//单线程池
ExecutorService service=Executors.newSingleThreadExecutor();
他们都是通过下面这个线程池实现的
有数量线程池的实现方式

public static ExecutorService newFixedThreadPool(int nThreads) {
return new ThreadPoolExecutor(nThreads/*核心线程数*/,
                              nThreads/*最高线程数*/,
                                      0L/*高出核心线程数的线程最高存活时间*/,
                                       TimeUnit.MILLISECONDS/*高出核心线程数的线程最高存活时间单位*/,
                                      new LinkedBlockingQueue<Runnable>()/*任务队列*/);

}
标签:





