package com.flying.basicKnowledge.basic;

/**
 * https://blog.csdn.net/andyzhaojianhui/article/details/84324466
 *     ①、无论如何，Integer与new Integer不会相等。不会经历拆箱过程，因为它们存放内存的位置不一样。（要看具体位置，可以看看这篇文章：点击打开链接）
 *
 *         ②、两个都是非new出来的Integer，如果数在-128到127之间，则是true,否则为false。
 *
 *         ③、两个都是new出来的,则为false。
 *
 *        ④、int和integer(new或非new)比较，都为true，因为会把Integer自动拆箱为int，其实就是相当于两个int类型比较。
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer a=1000, b=1000;
        System.out.println(a);
        System.out.println(b);
        System.out.println(a.equals(b));
        System.out.println(a==b);
    }
}
