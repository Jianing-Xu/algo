package com.xujn.offer.array;

/**
 * 调整数组顺序使奇数位于偶数前面。
 */
public class ReorderArrayByParity {

    public int[] reorder(int[] nums) {
        if (nums == null) {
            return null;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && (nums[left] & 1) == 1) {
                left++;
            }
            while (left < right && (nums[right] & 1) == 0) {
                right--;
            }
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        return nums;
    }
}
