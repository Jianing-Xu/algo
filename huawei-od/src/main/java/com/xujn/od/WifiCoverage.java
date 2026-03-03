package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：WIFI覆盖
 *
 * 【题目描述】
 * 在一条长度为N的走廊上布置WiFi路由器。每个路由器覆盖半径为R。
 * 求最少需要多少个路由器才能覆盖整条走廊。
 *
 * 【解题思路：贪心】
 * 典型的区间覆盖贪心：每次放置路由器时，放在能覆盖到最右边的位置。
 * 第一个路由器放在位置 R（从0开始），覆盖 [0, 2R]。
 * 下一个放在 2R + R 的位置... 以此类推。
 * 总数 = ceil(N / (2*R))
 */
public class WifiCoverage {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 走廊长度
        int r = sc.nextInt(); // 路由器覆盖半径

        int diameter = 2 * r;
        int count = (n + diameter - 1) / diameter; // 向上取整
        System.out.println(count);
    }
}
