package com.xujn.beauty.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 84. 柱状图中最大的矩形 (Largest Rectangle in Histogram)
 * 难度：困难
 *
 * 【题目描述】
 * 给定 n 个非负整数，表示柱状图中各个柱子的高度（宽度均为1）。
 * 求在该柱状图中能够勾勒出来的矩形的最大面积。
 *
 * 【解题思路：单调栈（Monotone Stack）—— 极为经典的模型】
 * 
 * 暴力解法：
 * 遍历每根柱子，向左和向右分别找到第一个比它矮的柱子。
 * 这两根矮柱子之间的宽度 × 当前柱子的高度 = 以当前柱子高度为上限的最大矩形面积。
 * 但暴力做法是 O(n^2)，太慢。
 *
 * 单调栈优化：
 * 维护一个**栈底到栈顶单调递增**的栈（存的是柱子的下标）。
 * - 当遇到比栈顶矮的柱子时，说明栈顶柱子的"右边界"找到了。
 * - 弹出栈顶柱子，此时新的栈顶就是弹出柱子的"左边界"。
 * - 宽度 = 右边界下标 - 左边界下标 - 1
 * - 面积 = 弹出柱子高度 × 宽度
 * 
 * 这样每根柱子最多入栈一次、出栈一次，总时间复杂度就是O(n)。
 *
 * 时间复杂度：O(n)   空间复杂度：O(n)
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new LinkedList<>(); // 单调递增栈，存储下标
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            // 当 i == n 时，使用高度 0 作为哨兵，把栈里剩余的柱子全部弹出结算
            int currentHeight = (i == n) ? 0 : heights[i];

            // 只要当前柱子比栈顶矮，就要弹出栈顶来结算面积
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()]; // 弹出的柱子高度

                // 确定宽度：
                // 左边界是弹出后的新栈顶（如果栈空了说明左边界是 -1，即从最左边开始）
                // 右边界是当前的 i（第一个比弹出柱子矮的）
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}
