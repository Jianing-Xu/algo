package com.xujn.interviewguide.recursiondp;

/**
 * 最长递增子序列长度。
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLis(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] ends = new int[arr.length];
        int right = 0;
        ends[0] = arr[0];
        int max = 1;
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
            max = Math.max(max, left + 1);
        }
        return max;
    }
}
