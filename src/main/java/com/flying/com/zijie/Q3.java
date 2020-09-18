package com.flying.com.zijie;

import java.util.HashMap;

/**
 * 问：HashMap了解吗？
 * 答：默认初始大小16，每次扩容2^N，数组加链表，当链表长度超过8，变为红黑树，6的时候退化为链表
 */
public class Q3 {
    HashMap<String, String> hashMap = new HashMap<>();
}
