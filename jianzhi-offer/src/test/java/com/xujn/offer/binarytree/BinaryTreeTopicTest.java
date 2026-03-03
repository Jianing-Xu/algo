package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinaryTreeTopicTest {

    @Test
    void shouldRebuildAndTraverseTree() {
        BuildTreeFromTraversals builder = new BuildTreeFromTraversals();
        LevelOrderTraversal traversal = new LevelOrderTraversal();
        TreeNode root = builder.build(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        assertEquals(List.of(3, 9, 20, 15, 7), traversal.levelOrder(root));
        assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), traversal.levelOrderByLayer(root));
    }

    @Test
    void shouldSolveTreeStructureProblems() {
        SubtreeStructure subtreeStructure = new SubtreeStructure();
        MirrorBinaryTree mirrorBinaryTree = new MirrorBinaryTree();
        SymmetricBinaryTree symmetricBinaryTree = new SymmetricBinaryTree();
        VerifyPostorderOfBst verifyPostorderOfBst = new VerifyPostorderOfBst();
        PathSumInTree pathSumInTree = new PathSumInTree();
        BstToDoublyLinkedList bstToDoublyLinkedList = new BstToDoublyLinkedList();
        SerializeBinaryTree serializeBinaryTree = new SerializeBinaryTree();
        TreeDepth treeDepth = new TreeDepth();
        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
        KthLargestInBst kthLargestInBst = new KthLargestInBst();
        LowestCommonAncestorBst lowestCommonAncestorBst = new LowestCommonAncestorBst();
        LowestCommonAncestorBinaryTree lowestCommonAncestorBinaryTree = new LowestCommonAncestorBinaryTree();

        TreeNode root = node(8,
                node(8, node(9), node(2, node(4), node(7))),
                node(7));
        TreeNode sub = node(8, node(9), node(2));
        assertTrue(subtreeStructure.isSubStructure(root, sub));

        TreeNode mirrored = mirrorBinaryTree.mirror(node(4, node(2, node(1), node(3)), node(7, node(6), node(9))));
        assertEquals(List.of(4, 7, 2, 9, 6, 3, 1), new LevelOrderTraversal().levelOrder(mirrored));

        TreeNode symmetric = node(1, node(2, node(3), node(4)), node(2, node(4), node(3)));
        assertTrue(symmetricBinaryTree.isSymmetric(symmetric));
        assertTrue(verifyPostorderOfBst.verify(new int[]{1, 3, 2, 6, 5}));
        assertFalse(verifyPostorderOfBst.verify(new int[]{1, 6, 3, 2, 5}));

        TreeNode pathRoot = node(5,
                node(4, node(11, node(7), node(2)), null),
                node(8, node(13), node(4, node(5), node(1))));
        assertEquals(List.of(List.of(5, 4, 11, 2), List.of(5, 8, 4, 5)), pathSumInTree.pathSum(pathRoot, 22));

        TreeNode bst = node(4, node(2, node(1), node(3)), node(5));
        TreeNode head = bstToDoublyLinkedList.convert(bst);
        assertEquals(1, head.value);
        assertEquals(2, head.right.value);
        assertEquals(1, head.right.left.value);

        TreeNode serializedRoot = node(1, node(2), node(3, node(4), node(5)));
        String serialized = serializeBinaryTree.serialize(serializedRoot);
        assertEquals(List.of(1, 2, 3, 4, 5), new LevelOrderTraversal().levelOrder(serializeBinaryTree.deserialize(serialized)));
        assertEquals(3, treeDepth.maxDepth(serializedRoot));
        assertTrue(balancedBinaryTree.isBalanced(serializedRoot));
        assertEquals(5, kthLargestInBst.kthLargest(node(5, node(3, node(2), node(4)), node(6)), 2));

        TreeNode bstRoot = node(6, node(2, node(0), node(4, node(3), node(5))), node(8, node(7), node(9)));
        assertEquals(6, lowestCommonAncestorBst.lowestCommonAncestor(bstRoot, bstRoot.left, bstRoot.right).value);

        TreeNode treeRoot = node(3, node(5, node(6), node(2, node(7), node(4))), node(1, node(0), node(8)));
        assertEquals(5, lowestCommonAncestorBinaryTree.lowestCommonAncestor(
                treeRoot, treeRoot.left, treeRoot.left.right.right).value);
    }

    private TreeNode node(int value) {
        return new TreeNode(value);
    }

    private TreeNode node(int value, TreeNode left, TreeNode right) {
        TreeNode root = new TreeNode(value);
        root.left = left;
        root.right = right;
        return root;
    }
}
