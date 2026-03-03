package com.xujn.beauty.leetcode.tree;

/**
 * LeetCode 104. 二叉树的最大深度 (Maximum Depth of Binary Tree)
 * 难度：简单
 *
 * 【题目描述】
 * 给定一个二叉树 root ，返回其最大深度。
 * 最大深度是根节点到最远叶子节点的最长路径上的节点数。
 *
 * 【解题思路：DFS 递归】
 * 树的最大深度 = max(左子树深度, 右子树深度) + 1
 * 递归到 null 节点返回 0，是最简洁的树题入门。
 *
 * 时间复杂度：O(n)   空间复杂度：O(h)
 */
public class MaximumDepth {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
