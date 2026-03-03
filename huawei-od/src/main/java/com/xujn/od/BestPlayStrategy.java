package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：最佳的出牌方法（贪心扑克牌）
 *
 * 【题目描述】
 * 给定手里的N张牌（数字1-13），出牌规则：
 * 可以出顺子（3张及以上连续）、对子、三条、炸弹等。
 * 求最少出几次可以出完所有牌。
 *
 * 【解题思路：贪心 + DFS搜索最优组合】
 * 优先出长顺子（消耗牌最多），然后出三条、对子、单张。
 * 简化版本：统计每个牌的数量，优先从顺子入手。
 */
public class BestPlayStrategy {

    static int minTurns;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cards = new int[14]; // cards[i] = 数字i的牌数，1-13

        for (int i = 0; i < n; i++) {
            int card = sc.nextInt();
            cards[card]++;
        }

        minTurns = n; // 最坏情况：每张单出
        dfs(cards, 0);
        System.out.println(minTurns);
    }

    private static void dfs(int[] cards, int turns) {
        if (turns >= minTurns) return; // 剪枝

        // 先尝试出顺子（5张及以上）
        for (int start = 1; start <= 9; start++) {
            int end = start;
            while (end <= 13 && cards[end] > 0) end++;
            int len = end - start;
            if (len >= 5) {
                for (int i = start; i < end; i++) cards[i]--;
                dfs(cards, turns + 1);
                for (int i = start; i < end; i++) cards[i]++;
            }
        }

        // 剩余的牌：三条、对子、单张
        int remaining = 0;
        for (int i = 1; i <= 13; i++) {
            remaining += (cards[i] > 0) ? ((cards[i] + 2) / 3) : 0; // 贪心估计
        }
        minTurns = Math.min(minTurns, turns + remaining);
    }
}
