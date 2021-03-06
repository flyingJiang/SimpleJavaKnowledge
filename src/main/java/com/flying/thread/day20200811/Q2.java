package com.flying.thread.day20200811;
/**
 * 第二：synchronized 、volatile的CPU原语是如何实现的？
 * https://blog.csdn.net/Y18630246793194530/article/details/97618566
 *
 * volatile
 * volatile是轻量级的synchronized，在多处理器开发中保证了共享变量的可见性。不会引起线程上下文切换和调度。
 *
 * volatile的定义与实现原理
 * 有volatile修饰的共享变量进行写操作的时候会多出一行lock前缀的指令。
 * lock前缀的指令在多核处理器下会引发两件事：
 *
 * 将当前处理器缓存行的数据写回到系统内存。
 * lock 前缀的指令导致在执行指令期间，声言处理器的LOCK#信号。在多处理器环境中，LOCK#信号确保在声言该信号期间，处理器可以独占任何共享内存。但是，在最近的处理器，LOCK#信号一般不锁总线，而是锁缓存，锁总线开销大。
 *
 * 这个写回内存的操作回事其他CPU里缓存了该内存地址的数据无效。
 * 处理器使用嗅探技术保证它的内部缓存、系统内存和其他处理器的缓存的数据在总线上保持一致。如果一个正在共享的状态的地址被嗅探到其他处理器打算写内存地址，那么正在嗅探的处理器将使它的缓存行无效，在下次访问相同内存地址时，强制执行缓存行填充。
 *
 * 为了提高处理速度，处理器不直接和内存进行通信，而是先将系统内的数据读到内部缓存（L1、L2或其他）后再进行操作，但操作完不知道何时会写到内存。
 *
 * volatile使用优化
 * 追加字节优化性能：一些处理器的缓存行是64字节款，追加字节能减少不必要参数锁的对象被加载到缓存行导致锁并发效率低。
 * LinkedTransferqueue,用追加到64位字节的方式来填满高速缓冲区的缓存行，避免头节点和尾戒戴呢加载到同一个缓存行，使，头围节点在修改时不会互相锁定。
 * 但不是所有使用volatile变量时都因该追加到64字节：
 *
 * 缓存行非64字节宽度：一些处理器的缓存行是32个字节宽度
 * 共享变量不会被频繁地写：因为追加字节的方式需要处理器读取更多的字节到高速缓冲区，这本身会带来一定的性能消耗，如果共享变量不会频繁写的话，锁的几率也会非常小，就没有必要通过追加字节的方式来避免相互锁定。
 * 这种追加字节的方式在Java7下可能不生效。因为它会淘汰或重新排列无用字段。
 *
 * synchronized的实现原理
 * 重量级锁。但随着JDK版本迭代，synchronized并没有那么重了。
 * synchronized实现同步的形式
 *
 * 对于普通同步方法，锁是当前实例对象
 * 对于静态同步方法，锁是当前的Class对象
 * 对于同步方法块，锁是Synchronized括号里配置的对象
 * synchronized是JVM层的实现，JVM基于进入和退出Monitor对象来实现方法同步和代码块同步。
 * 代码块同步：使用monitorenter和monitorexit指令实现
 * 方法同步：使用另一种方式实现（JVM规范里没讲）但是也可以使用这两个指令实现。
 * monitorenter指令是在编译后插入到同步代码块开始的位置，monitorexit是插入到方法结束处的异常处
 * 任何一个对象都有一个monitor与之关联，当且一个monotor被持有后，它将处于锁定状态。线程只从到monitorenter指令时，将会藏式回去对象所对应的monitor的所有权，即藏式获取对象的锁。
 *
 *
 *
 *
 */
public class Q2 {
}
