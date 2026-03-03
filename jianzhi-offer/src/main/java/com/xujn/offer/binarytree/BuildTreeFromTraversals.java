package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 重建二叉树。
 */
public class BuildTreeFromTraversals {

    public TreeNode build(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, 0, inorder.length - 1, indexMap);
    }

    private TreeNode build(int[] preorder, int preLeft, int preRight, int inLeft, int inRight,
                           Map<Integer, Integer> indexMap) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int inorderIndex = indexMap.get(root.value);
        int leftSize = inorderIndex - inLeft;
        root.left = build(preorder, preLeft + 1, preLeft + leftSize, inLeft, inorderIndex - 1, indexMap);
        root.right = build(preorder, preLeft + leftSize + 1, preRight, inorderIndex + 1, inRight, indexMap);
        return root;
    }
}
