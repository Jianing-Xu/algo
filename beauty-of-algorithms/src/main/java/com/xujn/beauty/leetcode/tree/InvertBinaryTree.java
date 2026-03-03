package com.xujn.beauty.leetcode.tree;

/**
 * LeetCode 226. 翻转二叉树 (Invert Binary Tree)
 * 难度：简单
 *
 * 【题目描述】
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 * 【解题思路：递归（前序遍历交换左右子树）】
 * 自顶向下递归：先交换当前节点的左右子树，然后递归地翻转左右子树。
 * 或者自底向上：先递归翻转左右子树，再交换。两种都可以。
 *
 * 【趣闻】Homebrew 作者 Max Howell 因为没做出这道题而被 Google 拒了，
 * 从而让这道题名声大噪。
 *
 * 时间复杂度：O(n)   空间复杂度：O(h)，h 是树的高度
 */
public class InvertBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // 交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归翻转左右子树
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
