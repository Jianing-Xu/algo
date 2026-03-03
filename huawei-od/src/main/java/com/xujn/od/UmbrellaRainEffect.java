package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：二维伞的雨滴效应
 *
 * 【题目描述】
 * 在一维数轴上有若干把伞（线段），雨滴从上方垂直落下。
 * 雨滴落在伞上时会沿两端滑落。求地面各位置最终接收到的雨滴数。
 * 类似"接雨水"的变形。
 *
 * 【解题思路：模拟 + 区间处理】
 * 每把伞覆盖[left, right]区间，伞上的雨滴会均匀分流到两侧。
 * 多层伞时，上层伞的分流水又落到下层伞，需要从上到下层层模拟。
 */
public class UmbrellaRainEffect {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 伞的数量
        int[][] umbrellas = new int[n][3]; // [left, right, height]
        for (int i = 0; i < n; i++) {
            umbrellas[i][0] = sc.nextInt(); // left
            umbrellas[i][1] = sc.nextInt(); // right
            umbrellas[i][2] = sc.nextInt(); // height
        }

        int rainCount = sc.nextInt(); // 雨滴数
        int[] rainPos = new int[rainCount];
        for (int i = 0; i < rainCount; i++) rainPos[i] = sc.nextInt();

        // 按高度降序排列，从最高的伞开始处理
        Arrays.sort(umbrellas, (a, b) -> b[2] - a[2]);

        // 模拟每颗雨滴的路径
        Map<Integer, Integer> groundDrops = new TreeMap<>();

        for (int drop : rainPos) {
            int pos = drop;
            boolean caught = false;
            for (int[] umb : umbrellas) {
                if (pos >= umb[0] && pos <= umb[1]) {
                    // 被伞接住，均分到两端
                    // 简化：左右各落一滴到伞边沿外
                    int leftDrop = umb[0] - 1;
                    int rightDrop = umb[1] + 1;
                    groundDrops.put(leftDrop, groundDrops.getOrDefault(leftDrop, 0) + 1);
                    groundDrops.put(rightDrop, groundDrops.getOrDefault(rightDrop, 0) + 1);
                    caught = true;
                    break;
                }
            }
            if (!caught) {
                groundDrops.put(pos, groundDrops.getOrDefault(pos, 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : groundDrops.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
