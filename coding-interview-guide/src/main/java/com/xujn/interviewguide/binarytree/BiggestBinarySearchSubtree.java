package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

/**
 * 找到二叉树中最大的搜索二叉子树头节点。
 */
public class BiggestBinarySearchSubtree {

    public TreeNode maxSubtreeHead(TreeNode root) {
        return process(root).maxBstHead;
    }

    private Info process(TreeNode node) {
        if (node == null) {
            return new Info(null, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
        Info left = process(node.left);
        Info right = process(node.right);

        int min = Math.min(node.value, Math.min(left.min, right.min));
        int max = Math.max(node.value, Math.max(left.max, right.max));

        if (left.isBst && right.isBst && left.max < node.value && node.value < right.min) {
            return new Info(node, left.size + right.size + 1, min, max, true);
        }
        if (left.size >= right.size) {
            return new Info(left.maxBstHead, left.size, min, max, false);
        }
        return new Info(right.maxBstHead, right.size, min, max, false);
    }

    private static class Info {
        private final TreeNode maxBstHead;
        private final int size;
        private final int min;
        private final int max;
        private final boolean isBst;

        private Info(TreeNode maxBstHead, int size, int min, int max, boolean isBst) {
            this.maxBstHead = maxBstHead;
            this.size = size;
            this.min = min;
            this.max = max;
            this.isBst = isBst;
        }
    }
}
