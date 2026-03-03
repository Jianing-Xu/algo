package com.xujn.offer.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长不含重复字符的子字符串。
 */
public class LongestSubstringWithoutRepeat {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0;
        int best = 0;
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            if (lastIndex.containsKey(current)) {
                left = Math.max(left, lastIndex.get(current) + 1);
            }
            lastIndex.put(current, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
