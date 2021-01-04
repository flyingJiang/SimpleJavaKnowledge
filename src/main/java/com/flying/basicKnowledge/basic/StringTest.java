package com.flying.basicKnowledge.basic;

/**
 * java中用于处理字符串常用的有三个类:
 * 1、java.lang.String
 * 2、java.lang.StringBuffer
 * 3、java.lang.StrungBuilder
 * 三者共同之处:都是final类,不允许被继承，主要是从性能和安全性上考虑的，因为这几个类都是经常被使用着，且考虑到防止其中的参数被参数修改影响到其他的应用。
 *
 * StringBuffer是线程安全，可以不需要额外的同步用于多线程中;
 *
 * StringBuilder是非同步,运行于多线程中就需要使用着单独同步处理，但是速度就比StringBuffer快多了;
 *
 * StringBuffer与StringBuilder两者共同之处:可以通过append、indert进行字符串的操作。
 *
 * String实现了三个接口:Serializable、Comparable<String>、CarSequence
 *
 * StringBuilder只实现了两个接口Serializable、CharSequence，相比之下String的实例可以通过compareTo方法进行比较，其他两个不可以。
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "2";
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        int[] a = new int[10];
        int len = a.length;
        int lenstr = s.length();
    }
}
