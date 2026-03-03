package com.xujn.offer.string;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 第一个只出现一次的字符。
 */
public class FirstUniqueCharacter {

    public char firstUniqChar(String s) {
        if (s == null || s.isEmpty()) {
            return ' ';
        }
        Map<Character, Integer> count = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }
}
