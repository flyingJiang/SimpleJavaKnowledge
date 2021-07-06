package com.lan.l1480;

import java.util.Arrays;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-06-19 23:04
 **/
class Solution {
    public int[] runningSum(int[] nums) {
        int numsLen = nums.length;
        if (numsLen<1){
            return null;
        }
        int[] result = new int[numsLen];
        result[0]=nums[0];
        for (int i=1;i<numsLen;i++){
            result[i] = result[i-1]+nums[i];
        }
        return result;

    }
}