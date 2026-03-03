package com.xujn.interviewguide.stackqueue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookStackQueueTest {

    @Test
    void shouldBuildMaxTree() {
        MaxTree.Node head = new MaxTree().build(new int[]{3, 4, 5, 1, 2});
        assertEquals(5, head.value);
        assertEquals(4, head.left.value);
        assertEquals(2, head.right.value);
        assertEquals(List.of(5, 4, 3, 2, 1), preOrder(head));
    }

    @Test
    void shouldCountSubarraysWithinBound() {
        assertEquals(12, new SubarrayCountWithBoundedDiff().count(new int[]{1, 2, 3, 4, 5}, 2));
        assertEquals(6, new SubarrayCountWithBoundedDiff().count(new int[]{1, 1, 1}, 0));
    }

    private List<Integer> preOrder(MaxTree.Node root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(MaxTree.Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.value);
        traverse(node.left, result);
        traverse(node.right, result);
    }
}
