package com.xujn.interviewguide.recursiondp;

/**
 * 打气球最大分数。
 */
public class BurstBalloons {

    public int maxCoins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] nums = new int[arr.length + 2];
        nums[0] = 1;
        nums[nums.length - 1] = 1;
        for (int i = 0; i < arr.length; i++) {
            nums[i + 1] = arr[i];
        }
        int[][] dp = new int[nums.length][nums.length];
        for (int len = 1; len <= arr.length; len++) {
            for (int left = 1; left + len - 1 <= arr.length; left++) {
                int right = left + len - 1;
                for (int last = left; last <= right; last++) {
                    dp[left][right] = Math.max(dp[left][right],
                            dp[left][last - 1] + dp[last + 1][right] + nums[left - 1] * nums[last] * nums[right + 1]);
                }
            }
        }
        return dp[1][arr.length];
    }
}
