package com.xujn.offer.string;

/**
 * 左旋转字符串。
 */
public class LeftRotateString {

    public String reverseLeftWords(String s, int n) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        int offset = n % s.length();
        return s.substring(offset) + s.substring(0, offset);
    }
}
