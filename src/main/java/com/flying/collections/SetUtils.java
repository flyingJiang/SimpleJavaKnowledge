package com.flying.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetUtils {
    public static void main(String[] args) {
        //HashSet（无序，唯一）：基于 HashMap 实现的，底层采用 HashMap 来保存元素
        HashSet<String> hashSet = new HashSet<>();
        //TreeSet（有序，唯一）： 红黑树(自平衡的排序二叉树。)
        TreeSet<String> treeSet = new TreeSet<>();
        //LinkedHashSet： LinkedHashSet 继承与 HashSet，并且其内部是通过 LinkedHashMap 来实现的。有点类似于我们之前说的LinkedHashMap 其内部是基于 Hashmap 实现一样，不过还是有一点点区别的。
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

    }
}
