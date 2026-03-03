package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

/**
 * 二叉搜索树的第 k 大节点。
 */
public class KthLargestInBst {

    private int k;
    private int result;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        reverseInorder(root);
        return result;
    }

    private void reverseInorder(TreeNode node) {
        if (node == null || k == 0) {
            return;
        }
        reverseInorder(node.right);
        if (--k == 0) {
            result = node.value;
            return;
        }
        reverseInorder(node.left);
    }
}
