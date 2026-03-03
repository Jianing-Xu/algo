package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

/**
 * 完全二叉树节点个数。
 */
public class CompleteBinaryTreeNodeCounter {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return count(root, 1, mostLeftLevel(root, 1));
    }

    private int count(TreeNode node, int level, int height) {
        if (level == height) {
            return 1;
        }
        if (mostLeftLevel(node.right, level + 1) == height) {
            return (1 << (height - level)) + count(node.right, level + 1, height);
        }
        return (1 << (height - level - 1)) + count(node.left, level + 1, height);
    }

    private int mostLeftLevel(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }
}
