package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

/**
 * 树的子结构。
 */
public class SubtreeStructure {

    public boolean isSubStructure(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return false;
        }
        return match(a, b) || isSubStructure(a.left, b) || isSubStructure(a.right, b);
    }

    private boolean match(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null || a.value != b.value) {
            return false;
        }
        return match(a.left, b.left) && match(a.right, b.right);
    }
}
