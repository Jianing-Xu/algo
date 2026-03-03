package com.xujn.offer.bit;

/**
 * 二进制中 1 的个数。
 */
public class NumberOfOneBits {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }
}
