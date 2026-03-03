package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

/**
 * 二叉树的镜像。
 */
public class MirrorBinaryTree {

    public TreeNode mirror(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = mirror(root.right);
        root.right = mirror(tmp);
        return root;
    }
}
