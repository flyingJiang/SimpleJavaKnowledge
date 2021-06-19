package com.flying.listT;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-04-19 10:34
 **/
public class ListTest {
    @Test
    public void case1(){
        List<String> list = null;
        System.out.println(list.size());
    }
    @Test
    public void case2(){
        List<String> list = new ArrayList<>();
        list.add("C00789");
        list.add("C00769");
        list.add("C000019");
        list.add("C00009");
        List<String> list1=list.stream().sorted().collect(Collectors.toList());
        System.out.println(list1.size());

    }
    @Test
    public void case3(){

    }
}
