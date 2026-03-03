package com.xujn.offer.bit;

/**
 * 不用加减乘除做加法。
 */
public class AddWithoutArithmetic {

    public int add(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }
}
