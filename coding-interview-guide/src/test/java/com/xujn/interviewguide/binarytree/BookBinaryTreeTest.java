package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookBinaryTreeTest {

    @Test
    void shouldFindLongestPathWithTargetSum() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(3, new LongestPathSum().longestPath(root, 4));
    }

    @Test
    void shouldComputeTreeMaxDistance() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        assertEquals(5, new MaxDistanceInTree().maxDistance(root));
    }

    @Test
    void shouldJudgeTopologyContainment() {
        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(2);
        first.right = new TreeNode(3);
        first.left.left = new TreeNode(4);
        first.left.right = new TreeNode(5);
        TreeNode second = new TreeNode(2);
        second.left = new TreeNode(4);
        assertTrue(new TopologyContainment().contains(first, second));
        second.right = new TreeNode(6);
        assertFalse(new TopologyContainment().contains(first, second));
    }
}
