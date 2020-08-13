package com.flying.basicKnowledge.BeanUtils;


import org.springframework.beans.BeanUtils;

/**
 *
 * https://mp.weixin.qq.com/s/5sLuaF-sSZMyYAmLBZcS3Q
 * 可以看到，成员变量赋值是基于目标对象的成员列表，并且会跳过ignore的以及在源对象中不存在，
 * 所以这个方法是安全的，不会因为两个对象之间的结构差异导致错误，但是必须保证同名的两个成员变量类型相同
 *
 */
public class SpringBeanUtils {
    public static void main(String[] args) {

        PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
        System.out.println("personSource: "+personSource);
        PersonDest personDest = new PersonDest();
        BeanUtils.copyProperties(personDest,personSource);
        System.out.println("persondest: "+personDest);
    }
}
