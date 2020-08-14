package com.flying.basicKnowledge.basic;

import java.math.BigInteger;

public class basic {
    public static void main(String[] args) {

        //     * (2-2<sup>-52</sup>)&middot;2<sup>1023</sup>.  It is equal to
        System.out.println(Double.MAX_VALUE);
        //     * {@code float}, (2-2<sup>-23</sup>)&middot;2<sup>127</sup>.
        System.out.println(Float.MAX_VALUE);
        //     * have, 2<sup>31</sup>-1.
        System.out.println(Integer.MAX_VALUE);
        //     * have, 2<sup>15</sup>-1.
        System.out.println(Short.MAX_VALUE);
        //     * have, 2<sup>63</sup>-1.
        System.out.println(Long.MAX_VALUE);


        /**
         * -2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive) to
         * +2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive).
         */
        BigInteger bigInteger = new BigInteger("90000000000000000000");
        System.out.println(bigInteger.toString());

        Integer integer = new Integer("999");
        System.out.println(integer.toString());
//        Integer integer1 = new Integer("90000000000000000000");
//        System.out.println(integer1.toString());
        Long l = new Long("90000000000000000000");
        System.out.println(l.toString());


    }
}

class TestInteger {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(1);
        if (a == 1){
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }

        Integer b = new Integer("1280");
        if (b == 1280){
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }
        if (b == 1280){
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }
    }
}
