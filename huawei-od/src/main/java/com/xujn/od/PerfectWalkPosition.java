package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：获得完美走位
 *
 * 【题目描述】
 * 一个角色的走位操作由 W(上)、A(左)、S(下)、D(右) 四个字符组成的字符串表示。
 * 要求修改最少的字符数（可以将某个方向改成另一个方向），
 * 使得四个方向的出现次数相同（各占1/4）。
 * 输出最少的修改次数。
 *
 * 【解题思路：滑动窗口】
 * 字符串长度 n 一定是4的倍数。每个方向应该出现 n/4 次。
 * 多余的方向需要转换为不足的方向。
 * 
 * 使用滑动窗口找出最短的子串，使得修改这个子串内的字符就能让整体平衡。
 */
public class PerfectWalkPosition {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        int n = s.length();
        int target = n / 4;

        // 统计各方向出现次数
        Map<Character, Integer> count = new HashMap<>();
        count.put('W', 0); count.put('A', 0);
        count.put('S', 0); count.put('D', 0);

        for (char c : s.toCharArray()) {
            count.put(c, count.get(c) + 1);
        }

        // 如果已经平衡
        if (count.get('W') == target && count.get('A') == target
            && count.get('S') == target && count.get('D') == target) {
            System.out.println(0);
            return;
        }

        int minLen = n;

        // 滑动窗口
        int left = 0;
        for (int right = 0; right < n; right++) {
            count.put(s.charAt(right), count.get(s.charAt(right)) - 1);

            // 检查窗口外的字符是否都 <= target
            while (count.get('W') <= target && count.get('A') <= target
                && count.get('S') <= target && count.get('D') <= target) {
                minLen = Math.min(minLen, right - left + 1);
                count.put(s.charAt(left), count.get(s.charAt(left)) + 1);
                left++;
            }
        }

        System.out.println(minLen);
    }
}
