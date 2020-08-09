package com.flying.leetcode.Q300;

import java.util.Arrays;

/**
 * 300. 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Solution {
}

/**
 *     # 动态规划的思路：将 dp 数组定义为：以 nums[i] 结尾的最长上升子序列的长度
 *     # 那么题目要求的，就是这个 dp 数组中的最大者
 *     # 以数组  [10, 9, 2, 5, 3, 7, 101, 18] 为例：
 *     # dp 的值： 1  1  1  2  2  3  4    4
 */
class Solution1 {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] dp = new int[len];
        // 自己一定是一个子序列
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            // 看以前的，比它小的，说明可以接在后面形成一个更长的子序列
            // int curMax = Integer.MIN_VALUE; 不能这样写，万一前面没有比自己小的，
            // 这个值就得不到更新
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int res = dp[0];
        for (int i = 0; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

class Solution2 {
    public int lengthOfLIS(int[] nums) {
        /**
         dp[i]: 所有长度为i+1的递增子序列中, 最小的那个序列尾数.
         由定义知dp数组必然是一个递增数组, 可以用 maxL 来表示最长递增子序列的长度.
         对数组进行迭代, 依次判断每个数num将其插入dp数组相应的位置:
         1. num > dp[maxL], 表示num比所有已知递增序列的尾数都大, 将num添加入dp
         数组尾部, 并将最长递增序列长度maxL加1
         2. dp[i-1] < num <= dp[i], 只更新相应的dp[i]
         *     # 以数组  [10, 9, 2, 5, 3, 7, 101, 18] 为例：
         *     1
         *
         **/
        int maxL = 0;
        int[] dp = new int[nums.length];
        for(int num : nums) {
            // 二分法查找, 也可以调用库函数如binary_search
            int lo = 0, hi = maxL;
            while(lo < hi) {
                int mid = lo+(hi-lo)/2;
                if(dp[mid] < num)
                    lo = mid+1;
                else
                    hi = mid;
            }
            dp[lo] = num;
            if(lo == maxL)
                maxL++;
        }
        return maxL;
    }
}
