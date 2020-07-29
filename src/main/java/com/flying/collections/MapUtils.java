package com.flying.collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  https://www.cnblogs.com/chenpt/p/9431602.html
 * 数组+链表的方式，
 static final int TREEIFY_THRESHOLD = 8;
 * jdk1.8后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间。
 static final int UNTREEIFY_THRESHOLD = 6;
 * hash桶中存放的链表长度概率  随着长度的增加而减小
 * 节点的分布频率会遵循泊松分布，链表长度达到8个元素的概率为0.00000006，几乎是不可能事件.
 * 　为什么转化为红黑树的阈值8和转化为链表的阈值6不一样，是为了避免频繁来回转化。
 static final int MIN_TREEIFY_CAPACITY = 64;
 * 实际上转换红黑树有个大前提,就是当前hash table的长度也就是HashMap的capacity(不是size)不能小于64.小于64就只是做个扩容.
 */
public class MapUtils {
    public static void main(String[] args) {
        // HashMap的主干数组，可以看到就是一个Entry数组，初始值为空数组{}，主干数组的长度一定是2的次幂
        //HashMap： JDK1.8之前HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）.
        // JDK1.8以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间

        HashMap<String, String> hashMap = new HashMap<>();

        //TreeMap： 红黑树（自平衡的排序二叉树）
        TreeMap<String,String> treeMap = new TreeMap<>();

        //HashTable： 数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的
        Hashtable<String, String> hashtable = new Hashtable<>();

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    }
}
