package com.xujn.interviewguide.arraymatrix;

import java.util.HashMap;
import java.util.Map;

/**
 * 累加和等于 k 的最长子数组长度。
 */
public class LongestSubarraySumEqualsK {

    public int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0, -1);
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            firstIndex.putIfAbsent(sum, i);
            if (firstIndex.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - firstIndex.get(sum - k));
            }
        }
        return maxLen;
    }
}
