package com.xujn.offer.searchsort;

/**
 * 0 到 n-1 中缺失的数字。
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
