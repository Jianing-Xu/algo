package com.xujn.offer.searchsort;

/**
 * 旋转数组的最小数字。
 */
public class MinInRotatedArray {

    public int minArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums must not be empty");
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right--;
            }
        }
        return nums[left];
    }
}
