package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.ParentTreeNode;
import com.xujn.interviewguide.model.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class MoreBinaryTreeTest {

    @Test
    void shouldRebuildBinaryTreeFromTraversals() {
        TreeNode root = new RebuildBinaryTree().buildTree(
                new int[]{1, 2, 4, 5, 3, 6, 7},
                new int[]{4, 2, 5, 1, 6, 3, 7}
        );
        BinaryTreeCodec codec = new BinaryTreeCodec();
        assertEquals("1!2!4!#!#!5!#!#!3!6!#!#!7!#!#!", codec.serialize(root));
    }

    @Test
    void shouldFindInOrderSuccessor() {
        ParentTreeNode n1 = new ParentTreeNode(1);
        ParentTreeNode n2 = new ParentTreeNode(2);
        ParentTreeNode n3 = new ParentTreeNode(3);
        ParentTreeNode n4 = new ParentTreeNode(4);
        ParentTreeNode n5 = new ParentTreeNode(5);
        n4.left = n2;
        n2.parent = n4;
        n4.right = n5;
        n5.parent = n4;
        n2.left = n1;
        n1.parent = n2;
        n2.right = n3;
        n3.parent = n2;

        InOrderSuccessor solution = new InOrderSuccessor();
        assertSame(n4, solution.getSuccessor(n3));
        assertSame(n2, solution.getSuccessor(n1));
        assertNull(solution.getSuccessor(n5));
    }

    @Test
    void shouldCountCompleteBinaryTreeNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        assertEquals(6, new CompleteBinaryTreeNodeCounter().countNodes(root));
    }
}
