package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：对称字符串
 *
 * 【题目描述】
 * 给定一个字符串S，找到其中最长的对称子串（回文子串）的长度。
 *
 * 【解题思路：中心扩展法】
 * 遍历每个位置作为回文中心，分别以奇数长度和偶数长度向两端扩展。
 * 时间O(n^2)，面试和OD机考中完全够用。
 */
public class SymmetricString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        System.out.println(longestPalindrome(s));
    }

    public static int longestPalindrome(String s) {
        if (s == null || s.length() < 1) return 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            // 以i为中心的奇数长度回文
            int len1 = expandAroundCenter(s, i, i);
            // 以i和i+1为中心的偶数长度回文
            int len2 = expandAroundCenter(s, i, i + 1);
            maxLen = Math.max(maxLen, Math.max(len1, len2));
        }
        return maxLen;
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
