package com.xujn.offer.array;

import java.util.Arrays;

/**
 * 把数组排成最小的数。
 */
public class MinNumberByConcatenation {

    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] values = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            values[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(values, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder builder = new StringBuilder();
        for (String value : values) {
            builder.append(value);
        }
        return builder.toString();
    }
}
