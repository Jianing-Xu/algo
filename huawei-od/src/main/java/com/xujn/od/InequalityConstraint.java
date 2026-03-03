package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：判断一组不等式是否满足约束并输出最大值
 *
 * 【题目描述】
 * 给定多个不等式约束（如 x > 3, x < 10, x >= 5），
 * 判断是否存在整数x同时满足所有约束。如果存在，输出满足条件的最大x。
 *
 * 【解题思路：求交集范围】
 * 所有 > 和 >= 确定下界，所有 < 和 <= 确定上界。
 * 如果下界 <= 上界，则满足，最大值 = 上界。
 */
public class InequalityConstraint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        int lower = Integer.MIN_VALUE; // 下界
        int upper = Integer.MAX_VALUE; // 上界

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            // 解析：x > 3, x >= 5, x < 10, x <= 8
            line = line.replace("x", "").replace(" ", "");

            if (line.startsWith(">=")) {
                int val = Integer.parseInt(line.substring(2));
                lower = Math.max(lower, val);
            } else if (line.startsWith(">")) {
                int val = Integer.parseInt(line.substring(1));
                lower = Math.max(lower, val + 1);
            } else if (line.startsWith("<=")) {
                int val = Integer.parseInt(line.substring(2));
                upper = Math.min(upper, val);
            } else if (line.startsWith("<")) {
                int val = Integer.parseInt(line.substring(1));
                upper = Math.min(upper, val - 1);
            }
        }

        if (lower <= upper) {
            System.out.println(upper); // 满足条件的最大整数
        } else {
            System.out.println("NO SOLUTION");
        }
    }
}
