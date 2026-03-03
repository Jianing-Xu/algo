package com.xujn.offer.string;

/**
 * 替换空格。
 */
public class ReplaceSpaces {

    public String replace(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                builder.append("%20");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
