package com.xujn.beauty.leetcode.array;

/**
 * LeetCode 41. 缺失的第一个正数 (First Missing Positive)
 * 难度：困难
 *
 * 【题目描述】
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 要求时间复杂度 O(n)，空间复杂度 O(1)。
 *
 * 【解题思路：原地哈希 / 原地置换】
 * 核心思想：把数字 1 放到下标 0 的位置，数字 2 放到下标 1 的位置……
 * 即 nums[i] 应该放在 nums[nums[i] - 1] 的位置上。
 * 
 * 然后再遍历一遍，第一个 nums[i] != i + 1 的位置，i + 1 就是缺失的最小正数。
 * 
 * 为什么这样做是 O(n)？
 * 虽然有 while 嵌套在 for 里面看起来像 O(n^2)，但每次 swap 至少能把一个元素放到正确位置上，
 * 而 n 个元素最多被放置 n 次，所以总的 swap 次数不会超过 n，整体仍然是 O(n)。
 *
 * 时间复杂度：O(n)   空间复杂度：O(1)
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // 条件：nums[i] 是一个在 [1, n] 范围内的正整数，并且它还没有在正确的位置上
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 把 nums[i] 交换到它应该呆的位置 nums[nums[i]-1]
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        // 再遍历一次，找第一个不在位的
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 如果 1~n 全部到位了，那缺失的就是 n+1
        return n + 1;
    }
}
