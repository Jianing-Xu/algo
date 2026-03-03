package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：路灯照明问题
 *
 * 【题目描述】
 * 一条直线道路上有N盏路灯，给出每盏路灯的位置和照射半径。
 * 求道路上从起点到终点之间没有被任何路灯照到的区间总长度。
 *
 * 【解题思路：区间合并】
 * 1. 每盏路灯的照射范围是 [pos - radius, pos + radius]
 * 2. 将所有区间排序后合并
 * 3. 用道路总长减去被照亮的总区间长度
 */
public class StreetLightIllumination {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int roadLen = sc.nextInt(); // 道路长度 [0, roadLen]
        int n = sc.nextInt();       // 路灯数量

        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            int pos = sc.nextInt();
            int radius = sc.nextInt();
            intervals[i][0] = Math.max(0, pos - radius);
            intervals[i][1] = Math.min(roadLen, pos + radius);
        }

        // 按区间左端点排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // 合并区间并统计照亮的总长度
        int litLength = 0;
        int curStart = intervals[0][0];
        int curEnd = intervals[0][1];

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] <= curEnd) {
                // 有重叠，扩展
                curEnd = Math.max(curEnd, intervals[i][1]);
            } else {
                // 无重叠，结算前一段
                litLength += (curEnd - curStart);
                curStart = intervals[i][0];
                curEnd = intervals[i][1];
            }
        }
        litLength += (curEnd - curStart); // 结算最后一段

        int darkLength = roadLen - litLength;
        System.out.println(darkLength);
    }
}
