package com.xujn.offer.array;

/**
 * 数组中出现次数超过一半的数字。
 */
public class MajorityElement {

    public int findMajority(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums must not be empty");
        }
        int candidate = 0;
        int votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                candidate = num;
            }
            votes += (num == candidate) ? 1 : -1;
        }
        int count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        return count * 2 > nums.length ? candidate : 0;
    }
}
