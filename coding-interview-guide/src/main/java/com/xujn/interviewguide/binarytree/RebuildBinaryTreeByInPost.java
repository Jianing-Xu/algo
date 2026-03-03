package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据中序和后序重建二叉树。
 */
public class RebuildBinaryTreeByInPost {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, indexMap);
    }

    private TreeNode build(int[] inorder, int inLeft, int inRight,
                           int[] postorder, int postLeft, int postRight,
                           Map<Integer, Integer> indexMap) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postRight]);
        int inIndex = indexMap.get(root.value);
        int leftSize = inIndex - inLeft;
        root.left = build(inorder, inLeft, inIndex - 1, postorder, postLeft, postLeft + leftSize - 1, indexMap);
        root.right = build(inorder, inIndex + 1, inRight, postorder, postLeft + leftSize, postRight - 1, indexMap);
        return root;
    }
}
