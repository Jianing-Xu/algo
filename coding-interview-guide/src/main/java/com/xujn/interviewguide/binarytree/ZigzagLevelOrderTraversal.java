package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树按层 zigzag 打印。
 */
public class ZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzag(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        boolean leftToRight = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> level = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                if (leftToRight) {
                    TreeNode node = deque.pollFirst();
                    level.add(node.value);
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                } else {
                    TreeNode node = deque.pollLast();
                    level.add(node.value);
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }
                }
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }
}
