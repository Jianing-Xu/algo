package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class AdvancedBinaryTreeTest {

    @Test
    void shouldFindLowestCommonAncestor() {
        TreeNode root = buildLcaTree();
        TreeNode lca = new LowestCommonAncestor().find(root, root.left.left, root.left.right);
        assertSame(root.left, lca);
    }

    @Test
    void shouldFindBiggestBinarySearchSubtreeHead() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(1);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(13);
        root.right.left.left = new TreeNode(4);
        root.right.left.right = new TreeNode(14);
        root.right.left.left.left = new TreeNode(2);
        root.right.left.left.right = new TreeNode(5);
        root.right.left.right.left = new TreeNode(11);
        root.right.left.right.right = new TreeNode(15);
        root.right.right.left = new TreeNode(20);
        root.right.right.right = new TreeNode(16);

        TreeNode maxBstHead = new BiggestBinarySearchSubtree().maxSubtreeHead(root);
        assertSame(root.right.left, maxBstHead);
    }

    @Test
    void shouldTraverseTreeInZigzagOrder() {
        TreeNode root = buildLcaTree();
        List<List<Integer>> actual = new ZigzagLevelOrderTraversal().zigzag(root);
        assertEquals(List.of(
                List.of(1),
                List.of(3, 2),
                List.of(4, 5, 6, 7)
        ), actual);
    }

    private TreeNode buildLcaTree() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        return n1;
    }
}
