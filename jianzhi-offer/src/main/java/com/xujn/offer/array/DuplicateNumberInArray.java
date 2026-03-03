package com.xujn.offer.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 数组中重复的数字。
 */
public class DuplicateNumberInArray {

    public int findDuplicateBySwap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                int value = nums[i];
                if (value < 0 || value >= nums.length) {
                    return -1;
                }
                if (nums[value] == value) {
                    return value;
                }
                swap(nums, i, value);
            }
        }
        return -1;
    }

    public int findDuplicateBySet(int[] nums) {
        if (nums == null) {
            return -1;
        }
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return num;
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
