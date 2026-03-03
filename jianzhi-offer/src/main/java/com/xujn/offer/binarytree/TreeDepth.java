package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

/**
 * 二叉树的深度。
 */
public class TreeDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
