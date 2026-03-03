package com.algo.beauty.ch40_dynamicprogramming;

/**
 * 动态规划基础：解决0-1背包问题
 * 
 * 【原理解释】
 * 动态规划的核心思想是将复杂问题分解为多个阶段的状态，通过保存前面阶段的状态，
 * 来推导后续阶段的状态，从而避免像回溯算法那样进行大量的重复计算。
 * 
 * 在0-1背包问题中：
 * 我们用一个二维数组 states[n][w+1] 来记录每一层（即考察完每一个物品后）背包中可能出现的总重量。
 * - n 表示物品的数量，即阶段数。
 * - w+1 表示背包可能出现的重量范围（从 0 到 w）。
 * - states[i][j] = true 表示在决策完第 i 个物品后，背包的总重量可以恰好达到 j。
 */
public class DynamicProgramming {

    /**
     * 【基础版本】只考虑重量的0-1背包问题
     * 求解：在不超过背包最大承载重量 w 的前提下，背包能装下的物品最大总重量。
     * 
     * @param weight 物品重量数组
     * @param n      物品个数
     * @param w      背包可承载最大重量
     */
    public int knapsack(int[] weight, int n, int w) {
        // states 数组记录每层决策后背包可能达到的重量状态
        // 默认值为 false
        boolean[][] states = new boolean[n][w + 1]; 
        
        // 【第 1 步】第一行的数据要特殊处理，作为状态转移的起点（或者叫“初始化第一个物品的状态”）
        states[0][0] = true;  // 第0个物品不放入背包，此时重量为0
        if (weight[0] <= w) {
            states[0][weight[0]] = true; // 第0个物品放入背包，此时重量为 weight[0]
        }
        
        // 【第 2 步】动态规划状态转移推导
        for (int i = 1; i < n; ++i) { 
            // 第 i 个阶段，从第 i-1 个阶段的状态推导而来
            
            // 决策 1：不把第 i 个物品放入背包
            // 那么如果上一层（第 i-1 个物品）能达到重量 j，本层也能达到重量 j
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j]) states[i][j] = states[i - 1][j];
            }
            
            // 决策 2：把第 i 个物品放入背包
            // 如果上一层能达到重量 j，并且加上当前物品重量不超载，本层就能达到重量 j + weight[i]
            for (int j = 0; j <= w - weight[i]; ++j) {
                if (states[i - 1][j]) states[i][j + weight[i]] = true;
            }
        }
        
        // 【第 3 步】输出结果：在最后一层（第 n-1 个物品决策完后），倒序查找最大的重量
        for (int i = w; i >= 0; --i) { 
            if (states[n - 1][i]) return i;
        }
        return 0;
    }

    /**
     * 【用于对比的回溯算法版】0-1背包问题升级版（加入物品价值的最大化）
     * 原理：暴力穷举每一个物品“装”与“不装”的两种可能，形成一棵巨大的递归树。
     * 虽然能得到正确结果，但时间复杂度高达 O(2^n)。
     */
    private int maxV = Integer.MIN_VALUE; // 结果放到maxV中
    public void f(int i, int cw, int cv, int[] items, int[] value, int n, int w) {
        if (cw == w || i == n) { // 装满了，或者所有物品都考察完了
            if (cv > maxV) maxV = cv;
            return;
        }
        f(i + 1, cw, cv, items, value, n, w); // 走左分支：选择不装第 i 个物品
        if (cw + items[i] <= w) {
            // 走右分支：选择装第 i 个物品，背包重量和总价值都会增加
            f(i + 1, cw + items[i], cv + value[i], items, value, n, w); 
        }
    }

    /**
     * 【进阶版本】考虑物品价值的0-1背包问题（动态规划实现）
     * 求解：给定背包重量 w，不仅要求不超载，还要使得装入背包的物品总价值最大。
     * 
     * 解法说明：
     * 这一次二维数组 states 不再只存 true/false，而是存 **具体重量下的最大价值**。
     * states[i][j] = v 表示决策完第 i 个物品后，如果当前背包总重量为 j，此时所能达到的最大总价值是 v。
     */
    public int knapsack3(int[] weight, int[] value, int n, int w) {
        // states[i][j] 记录决策到第 i 个物品，总重量为 j 时的【最大价值】
        int[][] states = new int[n][w + 1]; 
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1; // 初始化为一个不可能的负值 -1
            }
        }
        
        // 【初始化第 0 个物品的状态】
        states[0][0] = 0; // 不装
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0]; // 装
        }
        
        // 【开始状态转移】
        for (int i = 1; i < n; ++i) { 
            
            // 决策 1：不选择第 i 个物品
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
            }
            
            // 决策 2：选择第 i 个物品
            for (int j = 0; j <= w - weight[i]; ++j) { 
                if (states[i - 1][j] >= 0) {
                    // 当前状态的新价值 = 上个状态存的满配价值 + 这个新物品的价值
                    int v = states[i - 1][j] + value[i]; 
                    // 【核心】因为达到相同的重量 j+weight[i] 的装法可能有多种，
                    // 这里要在原来的基础和新的组合之中，取一个“最大的价值”写入 states 中。
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        
        // 【找出最大值】在最后一行里找价值最大的返回
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
        }
        return maxvalue;
    }
}
