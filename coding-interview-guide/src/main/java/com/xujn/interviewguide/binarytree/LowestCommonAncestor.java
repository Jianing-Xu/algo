package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

/**
 * 二叉树两个节点的最近公共祖先。
 */
public class LowestCommonAncestor {

    public TreeNode find(TreeNode root, TreeNode first, TreeNode second) {
        if (root == null || root == first || root == second) {
            return root;
        }
        TreeNode left = find(root.left, first, second);
        TreeNode right = find(root.right, first, second);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
