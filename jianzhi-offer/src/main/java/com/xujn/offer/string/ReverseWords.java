package com.xujn.offer.string;

/**
 * 翻转单词顺序。
 */
public class ReverseWords {

    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        String[] parts = s.trim().split("\\s+");
        if (parts.length == 1 && parts[0].isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = parts.length - 1; i >= 0; i--) {
            builder.append(parts[i]);
            if (i != 0) {
                builder.append(' ');
            }
        }
        return builder.toString();
    }
}
