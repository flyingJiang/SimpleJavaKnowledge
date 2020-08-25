package com.flying.algorithm.leetcode.Q102;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历

 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) return lists;
        //Stack<TreeNode> s = new Stack();
        Queue<TreeNode> q = new LinkedList<>();
        //s.push(root);
        q.add(root);
        while (!q.isEmpty()){
            //int c = s.size();
            int c = q.size();
            List<Integer> list = new LinkedList<>();
            while (c>0) {
                /*
                TreeNode node = s.pop();
                list.add(node.val);
                if(node.right!=null){
                    s.push(node.right);
                }
                if(node.left!=null){
                    s.push(node.left);
                }
                */

                TreeNode node = q.poll();
                list.add(node.val);
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
                c--;
            }
            lists.add(list);

        }
        return lists;
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
