package com.flying.leetcode.Q111;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 111. 二叉树的最小深度
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left==0 || right==0) ?  1+left+right : 1+Math.min(left,right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
