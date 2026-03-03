package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：敌情监控（雷达覆盖）
 *
 * 【题目描述】
 * 一条海岸线上有N个敌方目标（坐标为一维），我方有若干雷达站，
 * 每个雷达的探测半径为R。求最少需要多少个雷达才能监控所有目标。
 *
 * 【解题思路：贪心 —— 区间覆盖】
 * 每个目标在雷达可放置的位置上产生一个合法区间。
 * 将区间按右端点升序排列，贪心选择：每次选当前区间的右端点放雷达，
 * 能覆盖尽可能多的后续区间。
 */
public class EnemyMonitor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int[] targets = new int[n];
        for (int i = 0; i < n; i++) targets[i] = sc.nextInt();

        Arrays.sort(targets);
        int count = 1;
        int lastRadar = targets[0]; // 第一个雷达放在第一个目标处

        for (int i = 1; i < n; i++) {
            if (targets[i] - lastRadar > 2 * r) {
                // 超出覆盖范围，需要新增雷达
                count++;
                lastRadar = targets[i];
            }
        }

        System.out.println(count);
    }
}
