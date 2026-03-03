package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按先序序列化和反序列化二叉树。
 */
public class BinaryTreeCodec {

    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString();
    }

    public TreeNode deserialize(String data) {
        String[] tokens = data.split("!");
        Queue<String> queue = new LinkedList<>();
        for (String token : tokens) {
            if (!token.isEmpty()) {
                queue.offer(token);
            }
        }
        return build(queue);
    }

    private void serialize(TreeNode node, StringBuilder builder) {
        if (node == null) {
            builder.append("#!");
            return;
        }
        builder.append(node.value).append('!');
        serialize(node.left, builder);
        serialize(node.right, builder);
    }

    private TreeNode build(Queue<String> queue) {
        String value = queue.poll();
        if ("#".equals(value)) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.left = build(queue);
        head.right = build(queue);
        return head;
    }
}
