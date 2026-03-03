package com.xujn.beauty.leetcode.bsearch;

/**
 * LeetCode 69. x 的平方根 (Sqrt(x))
 * 难度：简单
 *
 * 【题目描述】
 * 给你一个非负整数 x ，计算并返回 x 的算术平方根（结果只保留整数部分）。
 *
 * 【解题思路：二分查找】
 * 在 [0, x] 的范围内二分查找一个整数 mid，使得 mid * mid <= x 且 (mid+1)*(mid+1) > x。
 * 
 * 注意：mid * mid 可能会溢出 int，所以使用 long 类型来计算。
 *
 * 时间复杂度：O(log x)   空间复杂度：O(1)
 */
public class SqrtX {

    public int mySqrt(int x) {
        if (x == 0) return 0;

        long low = 1, high = x;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // 循环结束后 high < low，high 就是满足 mid^2 <= x 的最大整数
        return (int) high;
    }
}
