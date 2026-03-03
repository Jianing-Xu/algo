package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：池化资源共享
 *
 * 【题目描述】
 * 有一个资源池，包含N个可用资源。M个任务请求资源，
 * 每个任务需要使用资源一段时间（起止时间给出）。
 * 求最少需要多少个资源才能满足所有任务的需求（即最大并发数）。
 *
 * 【解题思路：扫描线算法 / 差分数组】
 * 经典的会议室问题：统计同一时刻最大重叠区间数。
 */
public class ResourcePoolSharing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(); // 任务数

        List<int[]> events = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            events.add(new int[]{start, 1});  // 开始：+1
            events.add(new int[]{end, -1});    // 结束：-1
        }

        // 按时间排序，时间相同时结束事件排前面（先释放再占用）
        events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        int maxConcurrent = 0, current = 0;
        for (int[] event : events) {
            current += event[1];
            maxConcurrent = Math.max(maxConcurrent, current);
        }

        System.out.println(maxConcurrent);
    }
}
