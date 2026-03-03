package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：猜数字
 *
 * 【题目描述】
 * 一个人心中想了N个不重复的正整数。
 * 另一个人猜了N个不重复的正整数。
 * 如果猜的数中有K个数字与想的数字相同（不考虑顺序），输出K。
 *
 * 【解题思路：HashSet 求交集】
 */
public class GuessNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<Integer> target = new HashSet<>();
        for (int i = 0; i < n; i++) {
            target.add(sc.nextInt());
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            int guess = sc.nextInt();
            if (target.contains(guess)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
