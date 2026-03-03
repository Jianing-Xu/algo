package com.xujn.offer.array;

/**
 * 构建乘积数组。
 */
public class ConstructProductArray {

    public int[] construct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int suffix = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            suffix *= nums[i + 1];
            result[i] *= suffix;
        }
        return result;
    }
}
