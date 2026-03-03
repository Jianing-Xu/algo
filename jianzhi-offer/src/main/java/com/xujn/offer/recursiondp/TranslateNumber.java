package com.xujn.offer.recursiondp;

/**
 * 把数字翻译成字符串。
 */
public class TranslateNumber {

    public int translateNum(int num) {
        String value = String.valueOf(num);
        int prev = 1;
        int current = 1;
        for (int i = 1; i < value.length(); i++) {
            String sub = value.substring(i - 1, i + 1);
            int next = (sub.compareTo("10") >= 0 && sub.compareTo("25") <= 0) ? prev + current : current;
            prev = current;
            current = next;
        }
        return current;
    }
}
