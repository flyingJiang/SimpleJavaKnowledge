package com.flying.leetcode.Q144;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历

 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 * 递归：
 *
 * 前序遍历的“前”指的是先访问根结点，然后依次访问它的左孩子和右孩子，最终遍历整一颗树。 可以创建一个辅助函数，访问根结点（做什么？把结点值加入res中，其中res是最后要返回的结果集）,然后遍历左子树、右子树，最后得出结果。
 *
 * public List<Integer> preorderTraversal(TreeNode root) {
 *     List<Integer> res = new ArrayList<>();
 *     preorder(root, res);
 *     return res;
 * }
 *
 * private void preorder(TreeNode root, List<Integer> res){
 *     if(root != null){
 *         res.add(root.val);
 *         preorder(root.left, res);
 *         preorder(root.right, res);
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
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
