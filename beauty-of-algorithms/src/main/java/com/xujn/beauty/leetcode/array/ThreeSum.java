package com.xujn.beauty.leetcode.array;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 15. 三数之和 (3Sum)
 * 难度：中等
 *
 * 【题目描述】
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为0且不重复的三元组。
 *
 * 【解题思路：排序 + 双指针】
 * 1. 先给数组排序（这一步至关重要！排序后可以用双指针高效扫描且容易去重）。
 * 2. 用一个 for 循环固定第一个数字 nums[i]，然后问题就退化成了"两数之和" —— 
 *    在 nums[i+1..n-1] 里面找两个数使得三个数加起来为 0。
 * 3. 对于剩余的两个数，用左右指针 left/right 从两边向中间夹击扫描：
 *    - 如果三数之和 < 0：left 右移以增大总和
 *    - 如果三数之和 > 0：right 左移以减小总和
 *    - 如果三数之和 == 0：记录结果，同时左右指针跳过重复的数字
 * 4. 关键去重：当 nums[i] == nums[i-1] 时跳过（避免同一个值作为第一个数出现多次）。
 *
 * 时间复杂度：O(n^2)   空间复杂度：O(log n) ~ O(n)（排序所需）
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums); // 排序是双指针前提

        for (int i = 0; i < nums.length - 2; i++) {
            // 最小的数已经 > 0，后面不可能凑出 0 了
            if (nums[i] > 0) break;

            // 去重：跳过与前一个数相同的情况
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重：跳过左指针重复
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // 去重：跳过右指针重复
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // 总和太小，左指针右移
                } else {
                    right--; // 总和太大，右指针左移
                }
            }
        }
        return result;
    }
}
