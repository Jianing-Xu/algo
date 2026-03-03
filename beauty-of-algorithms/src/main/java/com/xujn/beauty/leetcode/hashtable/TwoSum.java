package com.xujn.beauty.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1. 两数之和 (Two Sum)
 * 难度：简单
 *
 * 【题目描述】
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 
 * 的那两个整数，并返回它们的数组下标。可以假设每种输入只会对应一个答案。
 *
 * 【解题思路：哈希表 —— 一次遍历】
 * - 遍历数组，对于每个 nums[i]，在哈希表中查找 target - nums[i] 是否存在。
 * - 如果存在，直接返回两个下标。
 * - 如果不存在，把当前 nums[i] 和它的下标存入哈希表用于未来的查找。
 *
 * 时间复杂度：O(n)   空间复杂度：O(n)
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // key: 数值, value: 下标

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 需要找的"互补数"

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i); // 存入当前数字和其下标
        }

        return new int[0]; // 题目保证有解，理论上不会走到这里
    }
}
