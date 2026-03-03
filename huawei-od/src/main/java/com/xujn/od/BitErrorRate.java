package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：计算误码率
 *
 * 【题目描述】
 * 给定两个等长的二进制串（原始数据和接收数据），计算误码率。
 * 误码率 = 不相同的bit数 / 总bit数
 *
 * 【解题思路：逐位比较统计】
 */
public class BitErrorRate {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String original = sc.nextLine().trim();
        String received = sc.nextLine().trim();

        int errorBits = 0;
        int totalBits = original.length();

        for (int i = 0; i < totalBits; i++) {
            if (original.charAt(i) != received.charAt(i)) {
                errorBits++;
            }
        }

        // 输出误码率保留小数
        double rate = (double) errorBits / totalBits;
        System.out.printf("%.4f%n", rate);
    }
}
