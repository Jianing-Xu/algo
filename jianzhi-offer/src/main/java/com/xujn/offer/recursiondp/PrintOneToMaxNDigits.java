package com.xujn.offer.recursiondp;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印从 1 到最大的 n 位数。
 */
public class PrintOneToMaxNDigits {

    public List<String> printNumbers(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        char[] number = new char[n];
        dfs(0, number, result);
        return result;
    }

    private void dfs(int index, char[] number, List<String> result) {
        if (index == number.length) {
            String value = stripLeadingZero(number);
            if (!value.isEmpty()) {
                result.add(value);
            }
            return;
        }
        for (char digit = '0'; digit <= '9'; digit++) {
            number[index] = digit;
            dfs(index + 1, number, result);
        }
    }

    private String stripLeadingZero(char[] number) {
        int start = 0;
        while (start < number.length && number[start] == '0') {
            start++;
        }
        return start == number.length ? "" : new String(number, start, number.length - start);
    }
}
