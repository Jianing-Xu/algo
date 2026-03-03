package com.xujn.offer.binarytree;

import com.xujn.offer.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径。
 */
public class PathSumInTree {

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, target, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int target, List<Integer> path, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        path.add(node.value);
        target -= node.value;
        if (node.left == null && node.right == null && target == 0) {
            result.add(new ArrayList<>(path));
        } else {
            dfs(node.left, target, path, result);
            dfs(node.right, target, path, result);
        }
        path.remove(path.size() - 1);
    }
}
