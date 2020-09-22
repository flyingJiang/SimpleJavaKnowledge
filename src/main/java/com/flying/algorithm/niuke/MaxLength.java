package com.flying.algorithm.niuke;

import java.util.Scanner;

/**
 * 题目描述
 * 功能: 求一个byte数字对应的二进制数字中1的最大连续数，例如3的二进制为00000011，最大连续2个1
 * <p>
 * 输入: 一个byte型的数字
 * <p>
 * 输出: 无
 * <p>
 * 返回: 对应的二进制数字中1的最大连续数
 * 输入描述:
 * 输入一个byte数字
 * <p>
 * 输出描述:
 * 输出转成二进制之后连续1的个数
 * <p>
 * 示例1
 * 输入
 * 复制
 * 3
 * 输出
 * 复制
 * 2
 *
 * @author Administrator
 */
public class MaxLength {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            String s = Integer.toBinaryString(n);
            int max = 0;
            String[] str = s.split("0");
            for (int i = 0; i < str.length; i++) {
                max = Math.max(max, str[i].length());
            }
            System.out.println(max);
        }
    }
}
