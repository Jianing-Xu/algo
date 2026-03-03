package com.xujn.offer.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 扑克牌中的顺子。
 */
public class IsStraight {

    public boolean isStraight(int[] nums) {
        if (nums == null || nums.length != 5) {
            return false;
        }
        Set<Integer> seen = new HashSet<>();
        int max = 0;
        int min = 14;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (!seen.add(num)) {
                return false;
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min < 5;
    }
}
