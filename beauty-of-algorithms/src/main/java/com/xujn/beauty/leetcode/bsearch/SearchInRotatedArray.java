package com.xujn.beauty.leetcode.bsearch;

/**
 * LeetCode 33. 搜索旋转排序数组 (Search in Rotated Sorted Array)
 * 难度：中等
 *
 * 【题目描述】
 * 整数数组 nums 按升序排列，在预先未知的某个点上进行了旋转。
 * 搜索 target ，如果存在返回下标，否则返回 -1。要求时间O(log n)。
 *
 * 【解题思路：二分查找变体】
 * 旋转后的数组虽然整体不有序，但至少有一半是有序的。
 * 
 * 关键判断流程：
 * 1. 取 mid，判断 nums[left..mid] 和 nums[mid..right] 哪一半是有序的。
 *    - 如果 nums[left] <= nums[mid]：左半部分有序
 *    - 否则：右半部分有序
 * 2. 判断 target 是否落在有序的那一半中：
 *    - 如果在：在有序半边二分
 *    - 如果不在：在另一半二分
 * 
 * 这样每一步都能排除一半区间，确保 O(log n)。
 *
 * 时间复杂度：O(log n)   空间复杂度：O(1)
 */
public class SearchInRotatedArray {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // 判断哪半边是有序的
            if (nums[left] <= nums[mid]) {
                // 左半边 [left, mid] 是有序的
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // target 在有序的左半边
                } else {
                    left = mid + 1;  // target 在右半边
                }
            } else {
                // 右半边 [mid, right] 是有序的
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;  // target 在有序的右半边
                } else {
                    right = mid - 1; // target 在左半边
                }
            }
        }

        return -1;
    }
}
