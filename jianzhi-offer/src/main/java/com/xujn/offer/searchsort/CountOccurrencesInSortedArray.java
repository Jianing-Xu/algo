package com.xujn.offer.searchsort;

/**
 * 在排序数组中查找数字。
 */
public class CountOccurrencesInSortedArray {

    public int search(int[] nums, int target) {
        return firstGreaterEqual(nums, target + 1) - firstGreaterEqual(nums, target);
    }

    private int firstGreaterEqual(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
