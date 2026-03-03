package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据先序和中序重建二叉树。
 */
public class RebuildBinaryTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, indexMap);
    }

    private TreeNode build(int[] preorder, int preLeft, int preRight,
                           int[] inorder, int inLeft, int inRight,
                           Map<Integer, Integer> indexMap) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int inIndex = indexMap.get(root.value);
        int leftSize = inIndex - inLeft;
        root.left = build(preorder, preLeft + 1, preLeft + leftSize, inorder, inLeft, inIndex - 1, indexMap);
        root.right = build(preorder, preLeft + leftSize + 1, preRight, inorder, inIndex + 1, inRight, indexMap);
        return root;
    }
}
