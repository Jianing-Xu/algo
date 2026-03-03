package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：查找接口成功率最优时间段
 *
 * 【题目描述】
 * 给定一组接口调用记录，每条记录包含时间戳和是否成功(1成功/0失败)。
 * 找出成功率最高的连续K秒的时间段。成功率相同取最早的。
 *
 * 【解题思路：滑动窗口】
 */
public class BestSuccessRateInterval {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 记录数
        int k = sc.nextInt(); // 时间窗口大小

        int[] time = new int[n];
        int[] success = new int[n];
        for (int i = 0; i < n; i++) {
            time[i] = sc.nextInt();
            success[i] = sc.nextInt();
        }

        int maxSuccessCount = 0;
        int bestStart = time[0];
        int windowSuccess = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            windowSuccess += success[right];

            // 缩小窗口直到时间差 <= k
            while (time[right] - time[left] >= k) {
                windowSuccess -= success[left];
                left++;
            }

            if (windowSuccess > maxSuccessCount) {
                maxSuccessCount = windowSuccess;
                bestStart = time[left];
            }
        }

        System.out.println(bestStart + " " + (bestStart + k - 1));
    }
}
