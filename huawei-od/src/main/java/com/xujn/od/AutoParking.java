package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：自动泊车
 *
 * 【题目描述】
 * 停车场有N个车位排成一排，1表示已被占用，0表示空闲。
 * 求最长的连续空闲车位段的长度，这就是能自动泊入的最大车辆长度。
 *
 * 【解题思路：一次遍历，统计连续0的最大长度】
 */
public class AutoParking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        String[] parts = line.split(",");

        int maxLen = 0, curLen = 0;
        for (String p : parts) {
            if (p.trim().equals("0")) {
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            } else {
                curLen = 0;
            }
        }
        System.out.println(maxLen);
    }
}
