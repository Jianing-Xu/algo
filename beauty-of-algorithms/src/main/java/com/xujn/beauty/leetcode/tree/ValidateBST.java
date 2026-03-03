package com.xujn.beauty.leetcode.tree;

/**
 * LeetCode 98. 验证二叉搜索树 (Validate Binary Search Tree)
 * 难度：中等
 *
 * 【题目描述】
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 【解题思路：递归 + 上下界约束】
 * BST 的性质：
 * - 左子树的所有节点值都 < 当前节点值
 * - 右子树的所有节点值都 > 当前节点值
 * 
 * 常见错误：只比较了父子关系，没有考虑祖父节点的约束。
 * 例如根节点为5，右子树根为6，但6的左子节点是3（3<5 但出现在右子树中）。
 * 
 * 正确做法：对每个节点维护一个合法的(min, max)范围。
 * - 进入左子树时，把当前节点值作为上界 max
 * - 进入右子树时，把当前节点值作为下界 min
 *
 * 时间复杂度：O(n)   空间复杂度：O(n)
 */
public class ValidateBST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;

        // 当前节点的值必须在 (min, max) 开区间内
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // 左子树的所有值必须 < node.val（当前值成为上界）
        // 右子树的所有值必须 > node.val（当前值成为下界）
        return validate(node.left, min, node.val)
            && validate(node.right, node.val, max);
    }
}
