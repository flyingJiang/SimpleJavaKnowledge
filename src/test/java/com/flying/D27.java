package com.flying;

import lombok.Synchronized;
import org.junit.Test;

import java.util.Collections;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-09-27 08:34
 **/
public class D27 {
    @Test
    public void a() {
        String s1 = "hello";
        String s2 = new String("hello");
        if (s1.equals(s2)) {
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }
        if (s1 == s2) {
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }

    }

    public void testNo(Object object) {
        if (object != null) {
            // TODO:
        }
    }

    public void testYes(Object object) {
        if (object == null) {
            return;
        }
        // TODO:
    }

}
