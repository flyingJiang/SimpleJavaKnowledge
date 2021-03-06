package com.flying.com.didi;


/**
 * https://www.cnblogs.com/liyutian/p/9690974.html
 * 感谢@爽 赵爽
 *
 * 四种引用类型
 * 所以在 JDK.1.2 之后，Java 对引用的概念进行了扩充，将引用分为了：强引用（Strong Reference）、软引用（Soft Reference）、弱引用（Weak Reference）、虚引用（Phantom Reference）4 种，这 4 种引用的强度依次减弱。
 *
 * 一，强引用
 * Java中默认声明的就是强引用，比如：
 * Object obj = new Object(); //只要obj还指向Object对象，Object对象就不会被回收
 * obj = null;  //手动置null
 * 只要强引用存在，垃圾回收器将永远不会回收被引用的对象，哪怕内存不足时，JVM也会直接抛出OutOfMemoryError，不会去回收。
 * 如果想中断强引用与对象之间的联系，可以显示的将强引用赋值为null，这样一来，JVM就可以适时的回收对象了
 *
 *
 * 二，软引用
 * 软引用是用来描述一些非必需但仍有用的对象。在内存足够的时候，软引用对象不会被回收，只有在内存不足时，系统则会回收软引用对象，
 * 如果回收了软引用对象之后仍然没有足够的内存，才会抛出内存溢出异常。这种特性常常被用来实现缓存技术，比如网页缓存，图片缓存等。
 * 在 JDK1.2 之后，用java.lang.ref.SoftReference类来表示软引用。
 *
 * 三，弱引用
 * 弱引用的引用强度比软引用要更弱一些，无论内存是否足够，只要 JVM 开始进行垃圾回收，那些被弱引用关联的对象都会被回收。
 * 在 JDK1.2 之后，用 java.lang.ref.WeakReference 来表示弱引用。
 *
 * 四，虚引用
 * 虚引用是最弱的一种引用关系，如果一个对象仅持有虚引用，那么它就和没有任何引用一样，它随时可能会被回收，在 JDK1.2 之后，
 * 用 PhantomReference 类来表示，通过查看这个类的源码，发现它只有一个构造函数和一个 get() 方法，而且它的 get() 方法仅仅是返回一个null，也就是说将永远无法通过虚引用来获取对象，虚引用必须要和 ReferenceQueue 引用队列一起使用。
 */
public class Main {
}
/***
 * Spring事务传播机制
 * JVM什么时候回收直接引用
 * TCP连接的TIME_WAIT和CLOSE_WAIT 状态解说
 * 线程池的实现原理
 * Spring原理
 */
