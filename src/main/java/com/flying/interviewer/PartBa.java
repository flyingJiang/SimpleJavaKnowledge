package com.flying.interviewer;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型数组 在java中是”不能创建一个确切的泛型类型的数组”的。
 *
 * 这种情况下，由于JVM泛型的擦除机制，在运行时JVM是不知道泛型信息的，所以可以给oa[1]赋上一个ArrayList而不会出现异常，
 * 但是在取出数据的时候却要做一次类型转换，所以就会出现ClassCastException，如果可以进行泛型数组的声明，上面说的这种情况在编译期将不会出现任何的警告和错误，只有在运行时才会出错。
 *
 * 而对泛型数组的声明进行限制，对于这样的情况，可以在编译期提示代码有类型安全问题，比没有任何提示要强很多。
 *
 * 下面采用通配符的方式是被允许的:数组的类型不可以是类型变量，除非是采用通配符的方式，因为对于通配符的方式，最后取出数据是要做显式的类型转换的。
 */
public class PartBa {
    public static void main(String[] args) {

        List<?>[] ls1 = new ArrayList<?>[10];

        /*
        List<String>[] ls1 = new ArrayList<String>[10];

        List<?>[] ls2 = new ArrayList<?>[10];

        List<String>[] ls3 = new ArrayList[10];
        */
    }
}
