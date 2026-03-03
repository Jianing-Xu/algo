package com.xujn.beauty.leetcode.array;

/**
 * LeetCode 169. 多数元素 (Majority Element)
 * 难度：简单
 *
 * 【题目描述】
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。
 * 多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 【解题思路：Boyer-Moore 投票算法（摩尔投票法）】
 * 这是一个绝妙的 O(n) 时间 O(1) 空间的算法：
 * 
 * 核心直觉：
 * 想象投票过程：每个数字代表一个候选人。
 * 维护一个 candidate（候选人）和一个 count（票数）。
 * - 遍历数组，如果 count == 0，就把当前数设为新的候选人。
 * - 如果当前数等于候选人，票数+1；否则票数-1（相当于"抵消"了一票）。
 * 
 * 为什么最终剩下的候选人就是多数元素？
 * 因为多数元素占了超过一半的位置，就算所有其他元素联合起来一起"投反对票"，
 * 多数元素的票数也不可能被完全抵消掉！最终计数器上还留着正值的，一定是多数元素。
 *
 * 时间复杂度：O(n)   空间复杂度：O(1)
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        int candidate = nums[0]; // 候选人
        int count = 1;           // 候选人当前的票数

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                // 票数被前面的"反对派"清零了，换个新候选人
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++; // 支持票 +1
            } else {
                count--; // 遇到了不同元素，互相抵消 -1
            }
        }

        return candidate;
    }
}
