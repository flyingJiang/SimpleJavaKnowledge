package com.flying.basic.switchdemo;

/**
 * 疑问
 * 1.为什么可以支持byte、char、short、int，不能支持long呢？
 * 2.为什么可支持enum和String？注意enum是JDK5引入，switch支持String是JDK7支持
 * 不能是boolean类型。
 *
 * 分析
 * 1.为什么可以支持byte、char、short、int，不能支持long呢？
 *
 * 发现一个共同点，这些都是基础数据类型中的整数，并且最大不超过int。正好去研究一下官方文档说明。
 *
 * 意思是说switch的编译会用到两个指令，tableswitch和lookupswitch。 而这2个指令指令只会运行在int指令下，
 * 低于int的正数类型会被转为int类型，而这一点和short、byte等类型在计算时会被转为int来处理的表现是一致的。
 *
 * 到此为止，我们知道第一个问题的答案了。在编译时，switch被编译成对应的2个实现方式的指令，这2种指令只支持int类型。
 *
 * 2.为什么可支持enum和String？
 * 按照网络资料反编译对照来看，enum最终也是转换为enum的int序号来适应switch的。
 * 而String类型要怎么和int对应起来呢，有一种方式叫hashcode计算，最后可以得出一个数值，把这个控制在int范围内，就能适应switch的要求了。
 */
public class Q1 {
    public static void main(String[] args) {
        /**
         * Incompatible types. Found: 'long', required: 'char, byte, short, int, Character, Byte, Short, Integer, String, or an enum'
         */
//        long s = 20L;
//        switch (s) {
//            case 20L:
//                System.out.println("haha");
//                break;
//
//            default:
//                break;
//        }
    }
}
