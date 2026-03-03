package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.ParentTreeNode;

/**
 * 找到带 parent 指针节点的中序后继。
 */
public class InOrderSuccessor {

    public ParentTreeNode getSuccessor(ParentTreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            ParentTreeNode current = node.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
        ParentTreeNode current = node;
        ParentTreeNode parent = node.parent;
        while (parent != null && parent.right == current) {
            current = parent;
            parent = parent.parent;
        }
        return parent;
    }
}
