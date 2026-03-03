package com.xujn.offer.searchsort;

/**
 * 数字序列中某一位的数字。
 */
public class FindNthDigit {

    public int findNthDigit(int n) {
        long digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) {
            n -= count;
            digit++;
            start *= 10;
            count = 9 * start * digit;
        }
        long number = start + (n - 1) / digit;
        int index = (int) ((n - 1) % digit);
        return Long.toString(number).charAt(index) - '0';
    }
}
