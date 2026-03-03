package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：寻找基站（最小覆盖圆）
 *
 * 【题目描述】
 * 在二维平面上有N个用户位置，需要建一个基站覆盖所有用户。
 * 求基站的最佳位置（所有用户坐标的质心），以及需要的最小覆盖半径。
 *
 * 【解题思路：计算质心 + 求最大距离】
 */
public class FindBaseStation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[][] points = new double[n][2];
        double sumX = 0, sumY = 0;

        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextDouble();
            points[i][1] = sc.nextDouble();
            sumX += points[i][0];
            sumY += points[i][1];
        }

        // 质心作为基站位置
        double cx = sumX / n;
        double cy = sumY / n;

        // 最小覆盖半径 = 质心到最远用户的距离
        double maxDist = 0;
        for (int i = 0; i < n; i++) {
            double dist = Math.sqrt(Math.pow(points[i][0] - cx, 2) + Math.pow(points[i][1] - cy, 2));
            maxDist = Math.max(maxDist, dist);
        }

        System.out.printf("%.2f %.2f%n", cx, cy);
        System.out.printf("%.2f%n", maxDist);
    }
}
