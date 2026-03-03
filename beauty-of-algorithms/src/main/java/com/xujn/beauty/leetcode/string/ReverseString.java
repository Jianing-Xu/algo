package com.xujn.beauty.leetcode.string;

/**
 * LeetCode 344. 反转字符串 (Reverse String)
 * 难度：简单
 *
 * 【解题思路：双指针首尾交换】
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}
