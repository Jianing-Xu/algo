package com.xujn.interviewguide.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串长度。
 */
public class LongestUniqueSubstring {

    public int maxUniqueLength(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < str.length(); right++) {
            char current = str.charAt(right);
            if (lastIndex.containsKey(current)) {
                left = Math.max(left, lastIndex.get(current) + 1);
            }
            lastIndex.put(current, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
