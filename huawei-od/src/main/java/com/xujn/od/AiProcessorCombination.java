package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：AI处理器组合
 *
 * 【题目描述】
 * 有N个AI处理器，每个有一个算力值。需要从中选出若干个处理器，
 * 使得总算力恰好等于目标值target。输出所有可能的组合方案。
 * 如果没有方案输出-1。组合中元素按升序排列，方案之间按字典序排列。
 *
 * 【解题思路：回溯法（组合求和）】
 * 经典的"组合总和"回溯枚举问题。
 * 排序后使用DFS+剪枝搜索所有可能的子集组合。
 */
public class AiProcessorCombination {

    static List<List<Integer>> results = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] powers = new int[n];
        for (int i = 0; i < n; i++) powers[i] = sc.nextInt();
        int target = sc.nextInt();

        Arrays.sort(powers);
        results.clear();
        backtrack(powers, target, 0, new ArrayList<>(), 0);

        if (results.isEmpty()) {
            System.out.println(-1);
        } else {
            for (List<Integer> combo : results) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < combo.size(); i++) {
                    if (i > 0) sb.append(" ");
                    sb.append(combo.get(i));
                }
                System.out.println(sb.toString());
            }
        }
    }

    private static void backtrack(int[] powers, int target, int start, List<Integer> path, int sum) {
        if (sum == target) {
            results.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) return;

        for (int i = start; i < powers.length; i++) {
            // 去重：同层中跳过相同元素
            if (i > start && powers[i] == powers[i - 1]) continue;
            if (sum + powers[i] > target) break; // 剪枝

            path.add(powers[i]);
            backtrack(powers, target, i + 1, path, sum + powers[i]);
            path.remove(path.size() - 1);
        }
    }
}
