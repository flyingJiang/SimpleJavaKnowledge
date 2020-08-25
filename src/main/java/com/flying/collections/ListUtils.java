package com.flying.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 *  推荐的做法就是，支持 Random Access 的列表可用 for 循环遍历，否则建议用 Iterator 或 foreach 遍历。
 *
 *  在需要频繁读取集合中的元素时，更推荐使用 ArrayList，而在插入和删除操作较多时，更推荐使用 LinkedList。
 *
 */
public class ListUtils {
    public static void main(String[] args) {
        //Arraylist： Object数组
        // ArrayList 比较适合顺序添加、随机访问的场景。
        List<String> arrayList = new ArrayList<>();
        //ArrayList 不是线程安全的，如果遇到多线程场景，可以通过 Collections 的 synchronizedList 方法将其转换成线程安全的容器后再使用
        List<String> arrayList1 = Collections.synchronizedList(arrayList);

        //LinkedList： 双向循环链表
        LinkedList<String> linkedList = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        //Vector： Object数组
        // 线程安全：Vector 使用了 Synchronized 来实现线程同步，是线程安全的，而 ArrayList 是非线程安全的。
        Vector<String> vector = new Vector<>();
    }

    public void iteratorCase(List<String> list) {
        // 1
        for (int i=0; i<list.size();i++){

        }
        // 2
        for (String s:list){

        }
        // 3
        list.forEach(s -> {

        });
        // 4
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){

        }
    }
}
