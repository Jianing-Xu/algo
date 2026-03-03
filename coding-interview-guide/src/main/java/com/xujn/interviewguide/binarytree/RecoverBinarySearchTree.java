package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 恢复两个节点交换后的搜索二叉树。
 */
public class RecoverBinarySearchTree {

    public void recover(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (prev != null && prev.value > current.value) {
                if (first == null) {
                    first = prev;
                }
                second = current;
            }
            prev = current;
            current = current.right;
        }
        if (first != null && second != null) {
            int tmp = first.value;
            first.value = second.value;
            second.value = tmp;
        }
    }
}
