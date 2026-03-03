package com.xujn.beauty.leetcode.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 239. 滑动窗口最大值 (Sliding Window Maximum)
 * 难度：困难
 *
 * 【题目描述】
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组最左侧移动到最右侧。
 * 每次只移动一位，返回滑动窗口中的最大值。
 *
 * 【解题思路：单调递减双端队列】
 * 维护一个双端队列（存放的是数组下标），保证队列中的元素从队头到队尾对应的值**单调递减**。
 * 
 * 核心逻辑：
 * 1. 队头始终是当前窗口的最大值下标。
 * 2. 新进来的元素如果比队尾的元素大，就不断弹出队尾
 *    （因为它们不可能再成为未来窗口的最大值了——新来的更大且更年轻，完全压制），
 *    然后把新元素入队尾。
 * 3. 如果队头的下标已经滑出窗口了（即 deque.peekFirst() <= i - k），弹出队头。
 * 4. 当窗口完全形成后（i >= k - 1），记录队头值作为当前窗口最大值。
 *
 * 时间复杂度：O(n)   每个元素最多入队一次、出队一次
 * 空间复杂度：O(k)
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>(); // 单调递减队列，存下标

        for (int i = 0; i < n; i++) {
            // 1. 如果队头已经滑出窗口范围，弹出
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 2. 把队尾所有比当前元素小的都弹出（维护单调递减性）
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // 3. 当前元素下标入队尾
            deque.offerLast(i);

            // 4. 窗口形成后，记录结果
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
}
