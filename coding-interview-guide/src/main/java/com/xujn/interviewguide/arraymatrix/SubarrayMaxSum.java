package com.xujn.interviewguide.arraymatrix;

/**
 * 子数组最大累加和。
 */
public class SubarrayMaxSum {

    public int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int current = arr[0];
        int best = arr[0];
        for (int i = 1; i < arr.length; i++) {
            current = Math.max(arr[i], current + arr[i]);
            best = Math.max(best, current);
        }
        return best;
    }
}
