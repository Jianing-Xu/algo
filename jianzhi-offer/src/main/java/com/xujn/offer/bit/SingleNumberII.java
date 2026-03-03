package com.xujn.offer.bit;

/**
 * 数组中唯一只出现一次的数字，其余出现三次。
 */
public class SingleNumberII {

    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bits[i] += (num >> i) & 1;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result |= (bits[i] % 3) << i;
        }
        return result;
    }
}
