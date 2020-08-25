package com.flying.unit;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTest {

    private static String name;

    @BeforeClass
    public static void beforeClass() {
        name = "BeforeClass";
        System.out.println("BeforeClass:" + name);
    }

    @Before
    public void before() {
        name = "Before";
        System.out.println("Before:" + name);
    }

    @After
    public void after() {
        name = "After";
        System.out.println("After:" + name);
    }

    @Test
    public void test1() {
        System.out.println("Test1:" + name);
        name = "test1";
        System.out.println("Test1:" + name);
    }

    @Test
    public void test2() {
        System.out.println("Test2:" + name);
        name = "test2";
        System.out.println("Test2:" + name);
    }


    @Test
    public void test3() {
//        System.out.println("Test2:" +name);
//        name = "test2";
//        System.out.println("Test2:" +name);
        int[] nums = { 10, 2 };
        largestNumber(nums);

    }

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        List<String> list = new ArrayList<>(nums.length);
        for (int n : nums) {
            list.add(String.valueOf(n));
        }
        list.sort((a, b) -> (b + a).compareTo(a + b));
        String s = "";
        for (String s1 : list) {
            s += s1;
        }
        if (s.charAt(0) == '0') {
            s = "0";
        }
        return s;
    }

    /*
    public String largestNumber(int[] nums) {

        //合法性
        if (nums == null || nums.length == 0) {
            return "";
        }
        //数字数组->字符数组  转化
        String[] strArr = new String[nums.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = String.valueOf(nums[i]);
        }
        //重写排序规则 12-14ms
        // Arrays.sort(strArr, new Comparator<String>() {
        //     @Override
        //     public int compare(String o1, String o2) {
        //         //继承此方法的时候，要自定义比较器，conpareTo方法返回值为1(升序),0，-1(降序)。
        //         //返回正值 交换；负值不交换
        //         return (o2 + o1).compareTo((o1 + o2));
        //     }
        // });
        //Lambda表达式 重写排序规则 速度慢了5倍 72-82ms
        Arrays.sort(strArr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        //字符数组->字符串 转化
        StringBuilder sb = new StringBuilder();
        for (String aStrArr : strArr) {
            sb.append(aStrArr);
        }
        String result = sb.toString();
        //特殊情况 若干个零
        if (result.charAt(0) == '0') {
            result = "0";
        }
        return result;



        List<String> list = new ArrayList<>(nums.length);
        for (int n : nums){
            list.add(String.valueOf(n));
        }
//        List<String> list1 =
                list.sort((a,b)-> {
            return (b + a).compareTo(a + b);
        });
        String s = "";
        for (String s1:list){
            s+=s1;
        }
////        int t;
////        for(int i:nums){
////            for (int j=i+1;j<nums.length;j++){
////                if((Long.valueOf(String.valueOf(nums[i])+String.valueOf(nums[j]))).longValue()<
////                        (Long.valueOf(String.valueOf(nums[j])+String.valueOf(nums[i]))).longValue()){
////                    t = nums[i];
////                    nums[i]=nums[j];
////                    nums[j]=t;
////                }
////            }
////        }
////        for(int n:nums){
////            s+=n;
////        }
        return s;

    }
     */

}
