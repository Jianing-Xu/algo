package com.xujn.interviewguide.arraymatrix;

import java.util.Arrays;

/**
 * 恢复一组最长递增子序列。
 */
public class LisSequenceRecovery {

    public int[] recover(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int[] ends = new int[arr.length];
        int[] dp = new int[arr.length];
        int right = 0;
        ends[0] = arr[0];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int left = 0;
            int r = right;
            while (left <= r) {
                int mid = left + ((r - left) >> 1);
                if (ends[mid] >= arr[i]) {
                    r = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ends[left] = arr[i];
            right = Math.max(right, left);
            dp[i] = left + 1;
        }
        int length = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > length) {
                length = dp[i];
                index = i;
            }
        }
        int[] result = new int[length];
        result[--length] = arr[index];
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                result[--length] = arr[i];
                index = i;
            }
        }
        return result;
    }
}
