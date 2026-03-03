package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

/**
 * 二叉树节点间最大距离。
 */
public class MaxDistanceInTree {

    public int maxDistance(TreeNode root) {
        return process(root).maxDistance;
    }

    private Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int height = Math.max(left.height, right.height) + 1;
        int throughHead = left.height + right.height + 1;
        int maxDistance = Math.max(throughHead, Math.max(left.maxDistance, right.maxDistance));
        return new Info(height, maxDistance);
    }

    private record Info(int height, int maxDistance) {
    }
}
