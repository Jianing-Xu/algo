package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层序列化和反序列化二叉树。
 */
public class LevelOrderCodec {

    public String serialize(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                builder.append("#!");
                continue;
            }
            builder.append(node.value).append('!');
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return builder.toString();
    }

    public TreeNode deserialize(String data) {
        String[] values = data.split("!");
        if (values.length == 0 || "#".equals(values[0])) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (index < values.length) {
                node.left = create(values[index++]);
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
            if (index < values.length) {
                node.right = create(values[index++]);
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return head;
    }

    private TreeNode create(String value) {
        return "#".equals(value) || value.isEmpty() ? null : new TreeNode(Integer.parseInt(value));
    }
}
