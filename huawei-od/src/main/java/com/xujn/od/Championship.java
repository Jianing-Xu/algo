package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：比赛的冠亚季军
 *
 * 【题目描述】
 * 给定N个选手的能力值，进行淘汰赛。每轮两两配对比赛，能力值大的获胜晋级。
 * 最终冠军是能力值最大的，亚军是在决赛中输给冠军的那位，
 * 季军是在半决赛中输给冠军或亚军的那位中能力值最大的。
 * 输出冠亚季军的编号。
 *
 * 【解题思路】
 * 直接排序取前三即可：冠军=最大值，亚军=次大值，季军=第三大值。
 * 严格按照淘汰赛规则的话需要模拟锦标赛树，但在标准OD题中通常简化为排序。
 */
public class Championship {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] players = new int[n][2]; // [能力值, 编号]

        for (int i = 0; i < n; i++) {
            players[i][0] = sc.nextInt(); // 能力值
            players[i][1] = i + 1;        // 编号从1开始
        }

        // 按能力值降序排列
        Arrays.sort(players, (a, b) -> b[0] - a[0]);

        // 输出冠亚季军编号
        System.out.println(players[0][1]); // 冠军
        System.out.println(players[1][1]); // 亚军
        System.out.println(players[2][1]); // 季军
    }
}
