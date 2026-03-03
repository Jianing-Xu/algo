package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

/**
 * 二叉搜索树与双向链表。
 */
public class BstToDoublyLinkedList {

    private TreeNode prev;
    private TreeNode head;

    public TreeNode convert(TreeNode root) {
        prev = null;
        head = null;
        inorder(root);
        return head;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        inorder(node.right);
    }
}
