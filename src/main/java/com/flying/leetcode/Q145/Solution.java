package com.flying.leetcode.Q145;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历

 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 * class Solution {
 *     private List<Integer> list = new LinkedList<>();
 *     public List<Integer> postorderTraversal(TreeNode root) {
 *         if(root == null) return list;
 *         postorderTraversal(root.left);
 *         postorderTraversal(root.right);
 *         list.add(root.val);
 *         return list;
 *     }
 * }
 * 核心是求得中间结果后，对于结果进行一次反转
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.right;
            } else {
                root = stack.pop();
                root = root.left;
            }
        }
        Collections.reverse(list);
        return list;
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
