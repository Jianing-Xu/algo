package com.xujn.interviewguide.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 用数组构建 MaxTree。
 */
public class MaxTree {

    public Node build(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i]);
        }
        Map<Node, Node> leftGreater = new HashMap<>();
        Map<Node, Node> rightGreater = new HashMap<>();
        fillGreater(nodes, leftGreater);
        fillGreater(reverse(nodes), rightGreater);

        Node head = null;
        for (Node current : nodes) {
            Node left = leftGreater.get(current);
            Node right = rightGreater.get(current);
            Node parent;
            if (left == null && right == null) {
                head = current;
                continue;
            } else if (left == null) {
                parent = right;
            } else if (right == null) {
                parent = left;
            } else {
                parent = left.value < right.value ? left : right;
            }
            if (parent.left == null) {
                parent.left = current;
            } else {
                parent.right = current;
            }
        }
        return head;
    }

    private void fillGreater(Node[] nodes, Map<Node, Node> greaterMap) {
        Deque<Node> stack = new ArrayDeque<>();
        for (Node current : nodes) {
            while (!stack.isEmpty() && stack.peek().value < current.value) {
                greaterMap.put(stack.pop(), current);
            }
            stack.push(current);
        }
        while (!stack.isEmpty()) {
            greaterMap.put(stack.pop(), null);
        }
    }

    private Node[] reverse(Node[] nodes) {
        Node[] reversed = new Node[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            reversed[i] = nodes[nodes.length - 1 - i];
        }
        return reversed;
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
