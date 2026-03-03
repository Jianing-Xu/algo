package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTest {

    @Test
    void shouldTraverseBinaryTreeIteratively() {
        TreeNode root = buildTree();
        BinaryTreeTraversal traversal = new BinaryTreeTraversal();

        assertEquals(List.of(1, 2, 4, 5, 3, 6), traversal.preOrder(root));
        assertEquals(List.of(4, 2, 5, 1, 3, 6), traversal.inOrder(root));
        assertEquals(List.of(4, 5, 2, 6, 3, 1), traversal.postOrder(root));
    }

    @Test
    void shouldSerializeAndDeserializeBinaryTree() {
        TreeNode root = buildTree();
        BinaryTreeCodec codec = new BinaryTreeCodec();

        String data = codec.serialize(root);
        TreeNode rebuilt = codec.deserialize(data);

        assertEquals(data, codec.serialize(rebuilt));
    }

    private TreeNode buildTree() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        return n1;
    }
}
