package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：寻找核酸检测点
 *
 * 【题目描述】
 * 在一个二维地图上，给定N个核酸检测点的坐标和当前排队人数。
 * 给定你的位置坐标，找到距离你最近的检测点。如果距离相同，选择排队人数少的。
 *
 * 【解题思路：遍历计算距离 + 排序】
 */
public class FindTestingPoint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int myX = sc.nextInt(), myY = sc.nextInt();
        int n = sc.nextInt();

        int[][] points = new int[n][3]; // [x, y, 排队人数]
        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
            points[i][2] = sc.nextInt();
        }

        // 按距离排序，距离相同按排队人数排序
        Arrays.sort(points, (a, b) -> {
            int distA = (a[0] - myX) * (a[0] - myX) + (a[1] - myY) * (a[1] - myY);
            int distB = (b[0] - myX) * (b[0] - myX) + (b[1] - myY) * (b[1] - myY);
            if (distA != distB) return distA - distB;
            return a[2] - b[2];
        });

        System.out.println(points[0][0] + " " + points[0][1]);
    }
}
