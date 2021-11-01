package com.flying.sugar;

import org.junit.Test;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-10-30 20:54
 **/
public class SyntacticSugarTest {
    @Test
    public void test_case1() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer c1 = 3;
        Integer d = 4;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        // == 在不遇到算术运算的情况下不会自动拆箱
        System.out.println(c == (a + b));
        // equals() 不处理数据转型关系
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
        System.out.println(c.equals(c1));
        System.out.println(c == c1);
        System.out.println(e.equals(f));
        System.out.println(e == f);

        Integer e1 = 127;
        Integer e2 = 127;
        Integer e3 = 128;
        Integer e4 = 128;
        System.out.println(e1 == e2);
        System.out.println(e3 == e4);

    }

}
