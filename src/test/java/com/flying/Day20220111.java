package com.flying;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2022-01-11 19:50
 **/
public class Day20220111 {
    @Test
    public void test_1() {
        List<String> result = new ArrayList<>();
        result.add("1016");
        result.add("1015");
        result.add("1013");
        result.add("101");
        result = result.stream()
                .sorted((a, b) -> Long.valueOf(a).compareTo(Long.valueOf(b)))
                .collect(Collectors.toList());
        System.out.println("****");

    }

    @Test
    public void test_2() {
        Long a1 = null;
        Long a2 = 1L;
        // 1
        System.out.println((a1 == null || a2 == null) ? true : !a1.equals(a2) && get1());
        // 2 其中1和2是等价的
        System.out.println((a1 == null || a2 == null) ? true : (!a1.equals(a2) && get1()));

        // 3
        System.out.println((a1 == null || a2 == null ? true : !a1.equals(a2)) && get1());
        // 4 其中3和4是等价的, 如果非要这样写，建议用4
        System.out.println(((a1 == null || a2 == null) ? true : !a1.equals(a2)) && get1());

    }

    private boolean get1() {
        System.out.println("122");
        return false;
    }

}
