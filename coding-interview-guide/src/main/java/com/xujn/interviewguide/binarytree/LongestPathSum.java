package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树中累加和为指定值的最长路径长度。
 */
public class LongestPathSum {

    public int longestPath(TreeNode root, int target) {
        Map<Integer, Integer> levelMap = new HashMap<>();
        levelMap.put(0, 0);
        return preOrder(root, target, 0, 1, 0, levelMap);
    }

    private int preOrder(TreeNode node, int target, int preSum, int level,
                         int maxLen, Map<Integer, Integer> levelMap) {
        if (node == null) {
            return maxLen;
        }
        int currentSum = preSum + node.value;
        levelMap.putIfAbsent(currentSum, level);
        if (levelMap.containsKey(currentSum - target)) {
            maxLen = Math.max(maxLen, level - levelMap.get(currentSum - target));
        }
        maxLen = preOrder(node.left, target, currentSum, level + 1, maxLen, levelMap);
        maxLen = preOrder(node.right, target, currentSum, level + 1, maxLen, levelMap);
        if (level == levelMap.get(currentSum)) {
            levelMap.remove(currentSum);
        }
        return maxLen;
    }
}
