package com.xujn.offer.binarytree;

/**
 * 二叉搜索树的后序遍历序列。
 */
public class VerifyPostorderOfBst {

    public boolean verify(int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return false;
        }
        return verify(postorder, 0, postorder.length - 1);
    }

    private boolean verify(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int root = postorder[right];
        int split = left;
        while (split < right && postorder[split] < root) {
            split++;
        }
        for (int i = split; i < right; i++) {
            if (postorder[i] < root) {
                return false;
            }
        }
        return verify(postorder, left, split - 1) && verify(postorder, split, right - 1);
    }
}
