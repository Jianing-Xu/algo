package com.xujn.offer.string;

/**
 * 表示数值的字符串。
 */
public class ValidNumberString {

    public boolean isNumber(String s) {
        if (s == null) {
            return false;
        }
        String value = s.trim();
        if (value.isEmpty()) {
            return false;
        }
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExp = false;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '+' || c == '-') {
                if (i != 0 && value.charAt(i - 1) != 'e' && value.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') {
                if (seenDot || seenExp) {
                    return false;
                }
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                if (seenExp || !seenDigit) {
                    return false;
                }
                seenExp = true;
                seenDigit = false;
            } else {
                return false;
            }
        }
        return seenDigit;
    }
}
