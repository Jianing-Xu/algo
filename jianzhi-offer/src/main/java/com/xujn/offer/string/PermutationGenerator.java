package com.xujn.offer.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串的排列。
 */
public class PermutationGenerator {

    public List<String> permutation(String s) {
        if (s == null || s.isEmpty()) {
            return List.of();
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        boolean[] used = new boolean[chars.length];
        List<String> result = new ArrayList<>();
        backtrack(chars, used, new StringBuilder(), result);
        return result;
    }

    private void backtrack(char[] chars, boolean[] used, StringBuilder path, List<String> result) {
        if (path.length() == chars.length) {
            result.add(path.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            path.append(chars[i]);
            backtrack(chars, used, path, result);
            path.deleteCharAt(path.length() - 1);
            used[i] = false;
        }
    }
}
