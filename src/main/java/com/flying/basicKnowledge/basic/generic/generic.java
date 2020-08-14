package com.flying.basicKnowledge.basic.generic;

import java.util.ArrayList;
import java.util.List;


public class generic {
    public static void main(String[] args) {
        List arrayList = new ArrayList();
        arrayList.add("aaaa");
        arrayList.add(100);

        for (int i = 0; i < arrayList.size(); i++) {
            String item = (String) arrayList.get(i);
            System.out.println("泛型测试" + "item = " + item);
        }
        /**
         * 毫无疑问，程序的运行结果会以崩溃结束：
         *
         * java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
         * ArrayList可以存放任意类型，例子中添加了一个String类型，添加了一个Integer类型，再使用时都以String的方式使用，因此程序崩溃了。为了解决类似这样的问题（在编译阶段就可以解决），泛型应运而生。
         *
         * 我们将第一行声明初始化list的代码更改一下，编译器会在编译阶段就能够帮我们发现类似这样的问题。
         */

    }

    private void test() {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("aaaa");
//        arrayList.add(100);
        //arrayList.add(100); 在编译阶段，编译器就会报错

        for (int i = 0; i < arrayList.size(); i++) {
            String item = (String) arrayList.get(i);
            System.out.println("泛型测试" + "item = " + item);
        }

    }
}

class Test {
    public static void main(String[] args) {
//        看到了很多文章中都会提起泛型数组，经过查看sun的说明文档，在java中是”不能创建一个确切的泛型类型的数组”的。

//        也就是说下面的这个例子是不可以的：

//        List<String>[] ls1 = new ArrayList<String>[10];
//        而使用通配符创建泛型数组是可以的，如下面这个例子：

        List<?>[] ls2 = new ArrayList<?>[10];
//        这样也是可以的：

        List<String>[] ls3 = new ArrayList[10];
        //////////////
//        List<String>[] lsa = new List<String>[10]; // Not really allowed.
//        Object o = lsa;
//        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
//        oa[1] = li; // Unsound, but passes run time store check
//        String s = lsa[1].get(0); // Run-time error: ClassCastException.
    }

    /**
     * 这种情况下，由于JVM泛型的擦除机制，在运行时JVM是不知道泛型信息的，所以可以给oa[1]赋上一个ArrayList而不会出现异常，
     * 但是在取出数据的时候却要做一次类型转换，所以就会出现ClassCastException，如果可以进行泛型数组的声明，
     * 上面说的这种情况在编译期将不会出现任何的警告和错误，只有在运行时才会出错。
     *
     * 而对泛型数组的声明进行限制，对于这样的情况，可以在编译期提示代码有类型安全问题，比没有任何提示要强很多。
     */
    private void test(){
        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK
    }
}
