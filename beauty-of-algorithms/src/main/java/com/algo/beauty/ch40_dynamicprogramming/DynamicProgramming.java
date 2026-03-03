package com.algo.beauty.ch40_dynamicprogramming;

/**
 * 动态规划基础：解决0-1背包问题
 */
public class DynamicProgramming {

    // weight:物品重量，n:物品个数，w:背包可承载重量
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1]; // 默认值false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
                if (states[i - 1][j]) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) {// 把第i个物品放入背包
                if (states[i - 1][j]) states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n - 1][i]) return i;
        }
        return 0;
    }

    // 0-1背包问题升级版（加入物品价值的最大化） 回溯算法实现用于对比
    private int maxV = Integer.MIN_VALUE; // 结果放到maxV中
    public void f(int i, int cw, int cv, int[] items, int[] value, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cv > maxV) maxV = cv;
            return;
        }
        f(i + 1, cw, cv, items, value, n, w); // 选择不装第i个物品
        if (cw + items[i] <= w) {
            f(i + 1, cw + items[i], cv + value[i], items, value, n, w); // 选择装第i个物品
        }
    }

    // 0-1背包问题升级版：给定背包重量和物品价值，求不超过背包容量下可装的最大价值 动态规划实现
    public int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1]; // 记录最大价值，全初始化为-1
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { // 动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第i个物品
                if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) { // 选择第i个物品
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i]; // 当前价值+加上上个状态存的价值
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
        }
        return maxvalue;
    }
}
