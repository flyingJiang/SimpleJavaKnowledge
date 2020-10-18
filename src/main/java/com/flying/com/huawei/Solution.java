package com.flying.com.huawei;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        TreeNode treeNode1 =new TreeNode(1);
        treeNode1.left=new TreeNode(1);
        treeNode1.right=new TreeNode(2);
        TreeNode treeNode2 =new TreeNode(1);
        treeNode2.left=new TreeNode(2);
        treeNode2.right=new TreeNode(2);

        List resInOrder1 = inOrderTraversal(treeNode1);
        List resInOrder2 = inOrderTraversal(treeNode2);

        List resPreOrder1 = preOrderTraversal(treeNode1);
        List resPreOrder2 = preOrderTraversal(treeNode2);

        System.out.println(isEqual(resInOrder1, resInOrder2, resPreOrder1, resPreOrder2));


    }

    private static boolean isEqual(List resInOrder1, List resInOrder2, List resPreOrder1, List resPreOrder2) {
        int resInOrder1Size = resInOrder1.size();
        int resInOrder2Size = resInOrder2.size();
        int resPreOrder1Size = resPreOrder1.size();
        int resPreOrder2Size = resPreOrder2.size();
        if (resInOrder1Size==0 && resInOrder2Size==0 && 0==resPreOrder1Size && 0==resPreOrder2Size){
            return true;
        }
        if (resInOrder1Size!=resInOrder2Size || resPreOrder1Size!=resPreOrder2Size){
            return false;
        }
        return isListEqual(resInOrder1, resInOrder2, resInOrder1Size) && isListEqual(resPreOrder1, resPreOrder2,resInOrder1Size);
    }

    private static boolean isListEqual(List res1, List res2, int resSize) {
        return res1.equals(res2);

    }

    private static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;

    }
    private static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                root = root.left;
                stack.push(root);
                root = stack.pop();
            } else {
                list.add(root.val);
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
