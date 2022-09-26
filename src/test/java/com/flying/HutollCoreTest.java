package com.flying;

import cn.hutool.core.convert.Convert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-11-15 15:58
 **/
public class HutollCoreTest {
    @Test
    public void test1() {
        String data1 = "2021-01-02 11:09:08";
        LocalDateTime localDateTime = Convert.toLocalDateTime(data1);
        LocalDateTime now1 = LocalDateTime.now(Clock.system(ZoneId.of("+7")));
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("+8")));
        // "Asia/Shanghai"
        LocalDateTime now3 = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        LocalDateTime now2 = LocalDateTime.now(Clock.system(ZoneId.of("+9")));
        System.out.println(now2);
        System.out.println(Convert.toStr(now2));
        if (now.compareTo(localDateTime) < 0) {
            System.out.println("1---");
        }
        if (now.isAfter(localDateTime)) {
            System.out.println("2---");

        }
    }
    @Test
    public void test2(){

        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(now);
        // 1449727200000
        System.out.println("1449727200000");
    }
    @Test
    public void test3(){
        Map<String, String> map = new HashMap<>();
        map.put("a","1");
        map.put("a","2");
        map.put("a","3");
    }
    @Test
    public void test4(){
        int x=2;
        List<String> list = new ArrayList<>();
        if (x==2){
            List<String> list2 = new ArrayList<>();
            list2.add("1");
            list.addAll(list2);
        }
        System.out.println(list);
    }
    // 202.118000.5401040.000.00.0.PUBLIC.0

    @Test
    public void test5(){
        String a = "202.118000.5401040.000.00.0.PUBLIC.0";
        String[] strings = a.split("\\.");
        System.out.println(strings);
        strings[0]="123";
        System.out.println();
    }

/*
    public static void main(String[] args) {
        String a = "202.118000.5401040.000.00.0.PUBLIC.0";
        String[] strings = a.split("\\.");
        System.out.println(strings);
        strings[0]="123";
        System.out.println(StringUtils.join(strings, "."));

    }*/
}
