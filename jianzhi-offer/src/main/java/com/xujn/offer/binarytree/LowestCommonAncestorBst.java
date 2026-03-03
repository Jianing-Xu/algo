package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

/**
 * 二叉搜索树的最近公共祖先。
 */
public class LowestCommonAncestorBst {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        while (current != null) {
            if (p.value < current.value && q.value < current.value) {
                current = current.left;
            } else if (p.value > current.value && q.value > current.value) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }
}
