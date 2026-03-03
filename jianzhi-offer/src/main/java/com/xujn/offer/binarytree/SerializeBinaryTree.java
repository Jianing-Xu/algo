package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化与反序列化。
 */
public class SerializeBinaryTree {

    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(nodes);
    }

    private void serialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("#,");
            return;
        }
        builder.append(root.value).append(',');
        serialize(root.left, builder);
        serialize(root.right, builder);
    }

    private TreeNode deserialize(Queue<String> nodes) {
        String value = nodes.poll();
        if ("#".equals(value)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }
}
