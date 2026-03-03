package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

/**
 * 对称二叉树。
 */
public class SymmetricBinaryTree {

    public boolean isSymmetric(TreeNode root) {
        return root == null || mirrorEquals(root.left, root.right);
    }

    private boolean mirrorEquals(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.value != right.value) {
            return false;
        }
        return mirrorEquals(left.left, right.right) && mirrorEquals(left.right, right.left);
    }
}
