package com.flying.interviewer;

/**
 * 基础-稍有提高
 * java中有两种类型
 * <p>
 * 基本类型
 * 基本数据类类型存的是数值本身
 * <p>
 * 引用类型
 * 引用类型变量在内存放的是数据的引用
 * <p>
 * 基本类型通过==比较的是他们的值大小，而引用类型比较的是他们的引用地址
 * 从上面我们可以知道给Interger 赋予的int数值在-128 - 127的时候，直接从cache中获取，这些cache引用对Integer对象地址是不变的，
 * 但是不在这个范围内的数字，则new Integer(i) 这个地址是新的地址，不可能一样的
 * <p>
 * Integer a = new Integer(3);
 * Integer b = 3;  // 将3自动装箱成Integer类型
 * int c = 3;
 * System.out.println(a == b); // false 两个引用没有引用同一对象
 * System.out.println(a.equals(b)); // true
 * System.out.println(a == c); // true a自动拆箱成int类型再和c比较
 * System.out.println(b == c); // true
 * <p>
 * Integer a1 = 128;
 * Integer b1 = 128;
 * System.out.println(a1 == b1); // false
 * <p>
 * Integer a2 = 127;
 * Integer b2 = 127;
 * System.out.println(a2 == b2); // true
 */
public class PartAb {
    public static void main(String[] args) {
        Integer a = new Integer(3);
        Integer b = 3;
        int c = 3;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a == c);
        System.out.println(b == c);

        Integer a1 = 128;
        Integer b1 = 128;
        System.out.println(a1 == b1);

        Integer a2 = 127;
        Integer b2 = 127;
        System.out.println(a2 == b2);

        System.out.println(a1.equals(b1));
        System.out.println(a2.equals(b2));
    }
}
