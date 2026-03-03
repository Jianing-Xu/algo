package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoreBookBinaryTreeTest {

    @Test
    void shouldSerializeAndDeserializeByLevelOrder() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        LevelOrderCodec codec = new LevelOrderCodec();
        String data = codec.serialize(root);
        assertEquals(data, codec.serialize(codec.deserialize(data)));
    }

    @Test
    void shouldRecoverSwappedBst() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        new RecoverBinarySearchTree().recover(root);
        assertEquals("1!2!3!4!", inOrder(root));
    }

    private String inOrder(TreeNode root) {
        if (root == null) {
            return "";
        }
        return inOrder(root.left) + root.value + "!" + inOrder(root.right);
    }
}
