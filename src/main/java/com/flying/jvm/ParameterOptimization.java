package com.flying.jvm;

/**
 * -server
 * -Xms6g
 * -Xmx6g
 * -XX:+UseG1GC
 * -XX:+AlwaysPreTouch
 * -XX:MaxGCPauseMillis=1000
 * -XX:MaxMetaspaceSize=512m
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=${APP_HOME}/dump
 *
 * 配置-XX:+AlwaysPreTouch参数后，JVM进程启动时间慢了几个数量级的根本原因呢？
 * 在没有配置-XX:+AlwaysPreTouch参数即默认情况下，JVM参数-Xms申明的堆只是在虚拟内存中分配，而不是在物理内存中分配：它被以一种内部数据结构的形式记录，从而避免被其他进程使用这些内存。这些内存页直到被访问时，才会在物理内存中分配。当JVM需要内存的时候，操作系统将根据需要分配内存页。
 * 配置-XX:+AlwaysPreTouch参数后，JVM将-Xms指定的堆内存中每个字节都写入’0’，这样的话，除了在虚拟内存中以内部数据结构保留之外，还会在物理内存中分配。并且由于touch这个行为是单线程的，因此它将会让JVM进程启动变慢。所以，要么选择减少接下来对每个缓存页的第一次访问时间，要么选择减少JVM进程启动时间，这是一种trade-off。
 *
 * 参数	含义	默认值	示例	说明
 * -Xms	初始堆大小	物理内存的1/64(<1GB)	-Xms1g	默认(MinHeapFreeRatio参数可以调整)空余堆内存小于40%时，JVM就会增大堆直到-Xmx的最大限制.
 * -Xmx	最大堆大小	物理内存的1/4(<1GB)	-Xmx1g	默认(MaxHeapFreeRatio参数可以调整)空余堆内存大于70%时，JVM会减少堆直到 -Xms的最小限制
 * -Xmn	年轻代大小	 	-Xmn512m	注意：此处的大小是（eden+ 2 survivor space).与jmap -heap中显示的New gen是不同的。
 * 整个堆大小=年轻代大小 + 年老代大小 + 持久代大小.
 * 增大年轻代后,将会减小年老代大小.此值对系统性能影响较大,Sun官方推荐配置为整个堆的3/8
 * -XX:NewRatio	年轻代与年老代的比值	 	-XX:NewRatio=1	-XX:NewRatio=4表示年轻代与年老代所占比值为1:4,年轻代占整个堆栈的1/5
 * Xms=Xmx并且设置了Xmn的情况下，该参数不需要进行设置。
 * -XX:SurvivorRatio	Eden区与Survivor区的大小比值	默认8:1:1 	 	设置为8,则两个Survivor区与一个Eden区的比值为2:8,一个Survivor区占整个年轻代的1/10
 * -Xss 	 每个线程的堆栈大小	 	 	 JDK5.0以后每个线程堆栈大小为1M,以前每个线程堆栈大小为256K.更具应用的线程所需内存大小进行 调整.在相同物理内存下,减小这个值能生成更多的线程.但是操作系统对一个进程内的线程数还是有限制的,不能无限生成,经验值在3000~5000左右
 * 一般小的应用， 如果栈不是很深， 应该是128k够用的 大的应用建议使用256k。这个选项对性能影响比较大，需要严格的测试。
 * -XX:MetaspaceSize 	初始元数据空间大小
 * -XX:MaxMetaspaceSize=128m
 * 最大元数据空间大小
 *
 *
 * -XX:AutoBoxCacheMax
 * -XX:+AlwaysPreTouch
 *
 *
 * https://blog.csdn.net/linsongbin1/article/details/55049477
 *
 * GC参数
 *
 * CMSInitiatingOccupancyFraction=75
 * 	那么当老年代堆空间的使用率达到75%的时候就开始执行垃圾回收,CMSInitiatingOccupancyFraction默认值是92%,这个就太大了。
 * 	CMSInitiatingOccupancyFraction参数必须跟下面两个参数一起使用才能生效的。
 * 	-XX:+UseConcMarkSweepGC
 * 	-XX:+UseCMSInitiatingOccupancyOnly
 *
 * MaxTenuringThreshold
 * 	默认情况下,当新生代执行了15次young gc后,如果还有对象存活在Survivor区中,那么就可以直接将这些对象晋升到老年代,但是由于新生代使用copy算法,如果Survivor区存活的对象太久的话,Survivor区存活的对象就越多,这个就会影响copy算法的性能,使得young gc停顿的时间加长,建议设置成6。
 * 	-XX:MaxTenuringThreshold=6
 *
 * ExplicitGCInvokesConcurrent
 * 	如果系统使用堆外内存,比如用到了Netty的DirectByteBuffer类,那么当想回收堆外内存的时候,需要调用
 * 	System.gc()
 * 	而这个方法将进行full gc,整个应用将会停顿,如果是使用CMS垃圾收集器,那么可以设置
 * 	-XX:+ExplicitGCInvokesConcurrent
 * 	这个参数来改变System.gc()的行为,让其从full gc –> CMS GC,CMS GC是并发收集的,且中间执行的过程中,只有部分阶段需要stop the world。
 * 	注意:设置了ExplicitGCInvokesConcurrent,那就不要设置DisableExplicitGC参数来禁掉System.gc()。
 *
 * 内存参数
 *
 * -Xmx, -Xms
 * 	这两个一般都是设置4个g
 *
 * NewRatio
 * 	GC最多的还是发生在新生代的young gc,所以可以提高一下新生代在整个堆的占用比例,建议设置为对半分,尽量避免young gc
 */
public class ParameterOptimization {
}
