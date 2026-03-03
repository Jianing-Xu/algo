package com.xujn.offer.string;

/**
 * 把字符串转换成整数。
 */
public class StringToInteger {

    public int strToInt(String str) {
        if (str == null) {
            return 0;
        }
        String s = str.trim();
        if (s.isEmpty()) {
            return 0;
        }
        int index = 0;
        int sign = 1;
        long result = 0;
        char first = s.charAt(0);
        if (first == '+' || first == '-') {
            sign = first == '-' ? -1 : 1;
            index++;
        }
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            result = result * 10 + (s.charAt(index) - '0');
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            index++;
        }
        return (int) (sign * result);
    }
}
