package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemainingBinaryTreeTest {

    @Test
    void shouldBuildTreeFromInorderAndPostorder() {
        TreeNode root = new RebuildBinaryTreeByInPost().buildTree(
                new int[]{4, 2, 5, 1, 6, 3, 7},
                new int[]{4, 5, 2, 6, 7, 3, 1}
        );
        assertEquals("1!2!4!#!#!5!#!#!3!6!#!#!7!#!#!", new BinaryTreeCodec().serialize(root));
    }

    @Test
    void shouldPrintBoundaryNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        assertEquals(List.of(1, 2, 4, 5, 6, 7, 3), new BoundaryNodePrinter().printBoundary(root));
    }
}
