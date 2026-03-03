package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印二叉树边界节点。
 */
public class BoundaryNodePrinter {

    public List<Integer> printBoundary(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.value);
        if (root.left == null && root.right == null) {
            return result;
        }
        addLeftBoundary(root.left, result);
        addLeaves(root.left, result);
        addLeaves(root.right, result);
        List<Integer> rightBoundary = new ArrayList<>();
        addRightBoundary(root.right, rightBoundary);
        result.addAll(rightBoundary);
        return result;
    }

    private void addLeftBoundary(TreeNode node, List<Integer> result) {
        while (node != null) {
            if (!(node.left == null && node.right == null)) {
                result.add(node.value);
            }
            node = node.left != null ? node.left : node.right;
        }
    }

    private void addRightBoundary(TreeNode node, List<Integer> result) {
        List<Integer> temp = new ArrayList<>();
        while (node != null) {
            if (!(node.left == null && node.right == null)) {
                temp.add(node.value);
            }
            node = node.right != null ? node.right : node.left;
        }
        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
        }
    }

    private void addLeaves(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.add(node.value);
            return;
        }
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }
}
