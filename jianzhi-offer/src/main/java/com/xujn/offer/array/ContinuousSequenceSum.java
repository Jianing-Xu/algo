package com.xujn.offer.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 和为 s 的连续正数序列。
 */
public class ContinuousSequenceSum {

    public int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();
        int left = 1;
        int right = 2;
        int sum = left + right;
        while (left < right) {
            if (sum == target) {
                int[] sequence = new int[right - left + 1];
                for (int i = left; i <= right; i++) {
                    sequence[i - left] = i;
                }
                result.add(sequence);
            }
            if (sum >= target) {
                sum -= left++;
            } else {
                sum += ++right;
            }
        }
        return result.toArray(new int[0][]);
    }
}
