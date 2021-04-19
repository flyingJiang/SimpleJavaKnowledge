package com.flying.designPattern.strategy;

import org.junit.Test;

import java.time.LocalDate;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-04-16 10:31
 **/
public class DateTest {
    @Test
    public void case1(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.minusDays(7));
        System.out.println(localDate.plusDays(7));

    }
}
