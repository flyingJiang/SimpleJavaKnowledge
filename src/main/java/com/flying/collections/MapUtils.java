package com.flying.collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * https://www.cnblogs.com/chenpt/p/9431602.html
 * 数组+链表的方式，
 * <p>
 * static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
 * <p>
 * static final int TREEIFY_THRESHOLD = 8;
 * jdk1.8后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间。
 * <p>
 * static final int UNTREEIFY_THRESHOLD = 6;
 * hash桶中存放的链表长度概率  随着长度的增加而减小
 * 节点的分布频率会遵循泊松分布，链表长度达到8个元素的概率为0.00000006，几乎是不可能事件.
 * 　为什么转化为红黑树的阈值8和转化为链表的阈值6不一样，是为了避免频繁来回转化。
 * <p>
 * static final int MIN_TREEIFY_CAPACITY = 64;
 * if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
 * 实际上转换红黑树有个大前提,就是当前hash table的长度也就是HashMap的capacity(不是size)不能小于64.小于64就只是做个扩容.
 * <p>
 * return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
 * <p>
 * HashMap 的长度为什么是2的幂次方
 * 为了能让 HashMap 存取高效，尽量较少碰撞，也就是要尽量把数据分配均匀，每个链表/红黑树长度大致相同。这个实现就是把数据存到哪个链表/红黑树中的算法。
 * <p>
 * <p>
 * static final float DEFAULT_LOAD_FACTOR = 0.75f;
 * 问题：如果给定初始化hashmap大小为50，那么初始化要多大
 * 答：50/0.75~=68----128，需要考虑负载因子
 * @author Flying.Jiang
 */
public class MapUtils {
    public static void main(String[] args) {
        // HashMap的主干数组，可以看到就是一个Entry数组，初始值为空数组{}，主干数组的长度一定是2的次幂
        //HashMap： JDK1.8之前HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）.
        // JDK1.8以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间

        //HashMap 的长度为什么是2的幂次方
        //为了能让 HashMap 存取高效，尽量较少碰撞，也就是要尽量把数据分配均匀，每个链表/红黑树长度大致相同。

        //这个算法应该如何设计呢？
        //我们首先可能会想到采用%取余的操作来实现。但是，重点来了：“取余(%)操作中如果除数是2的幂次则等价于与其除数减一的与(&)操作（也就是说 hash%length==hash&(length-1)的前提是 length 是2的 n 次方；）。” 并且 采用二进制位操作 &，相对于%能够提高运算效率，这就解释了 HashMap 的长度为什么是2的幂次方。
        //
        //那为什么是两次扰动呢？
        //答：这样就是加大哈希值低位的随机性，使得分布更均匀，从而提高对应数组存储下标位置的随机性&均匀性，最终减少Hash冲突，两次就够了，已经达到了高位低位同时参与运算的目的；

        HashMap<String, String> hashMap = new HashMap<>();

        //TreeMap： 红黑树（自平衡的排序二叉树）
        //对于在Map中插入、删除和定位元素这类操作，HashMap是最好的选择。
        // 然而，假如你需要对一个有序的key集合进行遍历，TreeMap是更好的选择。
        //基于你的collection的大小，也许向HashMap中添加元素会更快，将map换为TreeMap进行有序key的遍历。
        TreeMap<String, String> treeMap = new TreeMap<>();

        //HashTable： 数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的
        // HashMap 是非线程安全的，HashTable 是线程安全的；HashTable 内部的方法基本都经过 synchronized 修饰。（如果你要保证线程安全的话就使用 ConcurrentHashMap 吧！）
        Hashtable<String, String> hashtable = new Hashtable<>();

        //推荐使用：在 Hashtable 的类注释可以看到，Hashtable 是保留类不建议使用，推荐在单线程环境下使用 HashMap 替代，如果需要多线程使用则用 ConcurrentHashMap 替代。
        //***
        // ConcurrentHashMap对整个桶数组进行了分割分段(Segment)，然后在每一个分段上都用lock锁进行保护，相对于HashTable的synchronized锁的粒度更精细了一些，并发性能更好，
        // 而HashMap没有锁机制，不是线程安全的。（JDK1.8之后ConcurrentHashMap启用了一种全新的方式实现,利用CAS算法。

        //JDK1.7
        //首先将数据分为一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据时，其他段的数据也能被其他线程访问。
        //在JDK1.7中，ConcurrentHashMap采用Segment + HashEntry的方式进行实现

        //JDK1.8
        //在JDK1.8中，放弃了Segment臃肿的设计，取而代之的是采用Node + CAS + Synchronized来保证并发安全进行实现，
        // synchronized只锁定当前链表或红黑二叉树的首节点，这样只要hash不冲突，就不会产生并发，效率又提升N倍。
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    }

    public void mapUtil(Map<Integer, Integer> map){
        for (Integer key:map.keySet()){

        }
        for (Integer v : map.values()){

        }
        for (Map.Entry<Integer, Integer> entry: map.entrySet()){

        }
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> entry = iterator.next();
        }

    }
}

/**
 * https://blog.csdn.net/horero/article/details/53737576
 * <p>
 * 1.语法上面的区别：
 * 1）HashMap允许键值为空，Hashtable不允许。
 * 2）HashMap包含了containsvalue和containsKey，不包含有contains。
 * 2.安全方面的区别
 * HashTable支持线程安全的，而HashMap不支持线程同步，是非线程安全的。因此，HashMap相对来说效率可能会高于Hashtable。
 * 3.源码级别的区别
 * Hashtable，hash数组默认的大小是11，增加的方式是old*2+1,而HashMap中，hash数组的默认大小是16，而且一定是2的指数。
 * 相较于HashMap和HashTable，TreeMap是利用红黑树来实现的，实现了SortMap接口，能够对保存的记录根据键进行排序。所以一般需要排序的情况下是选择TreeMap来进行。
 */
class MapTable {}
