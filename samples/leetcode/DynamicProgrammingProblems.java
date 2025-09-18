package leetcode;

import java.util.*;

/**
 * LeetCode动态规划相关问题解答
 * 包含0-1背包问题等经典动态规划题目
 */
public class DynamicProgrammingProblems {
    
    // ==================== 0-1 Knapsack（0-1背包问题）====================
    
    /**
     * 0-1背包问题
     * 给定n个物品，每个物品有重量weight[i]和价值value[i]
     * 给定背包容量capacity，求能装入背包的物品的最大价值
     * 
     * 解题思路：动态规划
     * dp[i][w] 表示前i个物品在容量为w的背包中能获得的最大价值
     * 状态转移方程：
     * dp[i][w] = max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i])
     * 
     * 时间复杂度：O(n * capacity)
     * 空间复杂度：O(n * capacity)
     * 
     * @param weights 物品重量数组
     * @param values 物品价值数组
     * @param capacity 背包容量
     * @return 最大价值
     */
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null || weights.length != values.length) {
            return 0;
        }
        
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                // 不选择第i个物品
                dp[i][w] = dp[i - 1][w];
                
                // 如果能装下第i个物品，考虑选择它
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], 
                                      dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    /**
     * 0-1背包问题（空间优化版本）
     * 时间复杂度：O(n * capacity)
     * 空间复杂度：O(capacity)
     * 
     * @param weights 物品重量数组
     * @param values 物品价值数组
     * @param capacity 背包容量
     * @return 最大价值
     */
    public static int knapsack01Optimized(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null || weights.length != values.length) {
            return 0;
        }
        
        int n = weights.length;
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < n; i++) {
            // 从后往前遍历，避免重复使用同一物品
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        
        return dp[capacity];
    }
    
    /**
     * 0-1背包问题（返回选择的物品）
     * 
     * @param weights 物品重量数组
     * @param values 物品价值数组
     * @param capacity 背包容量
     * @return [最大价值, 选择的物品索引列表]
     */
    public static Object[] knapsack01WithItems(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null || weights.length != values.length) {
            return new Object[]{0, new ArrayList<Integer>()};
        }
        
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        // 填充DP表
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w];
                
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], 
                                      dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        
        // 回溯找出选择的物品
        List<Integer> selectedItems = new ArrayList<>();
        int w = capacity;
        
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(i - 1); // 添加物品索引
                w -= weights[i - 1];
            }
        }
        
        Collections.reverse(selectedItems);
        return new Object[]{dp[n][capacity], selectedItems};
    }
    
    // ==================== 其他经典动态规划问题 ====================
    
    /**
     * LeetCode 322. 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额
     * 计算并返回可以凑成总金额所需的最少的硬币个数
     * 
     * @param coins 硬币面额数组
     * @param amount 目标金额
     * @return 最少硬币数，无法凑成返回-1
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins == null || coins.length == 0) return -1;
        
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 初始化为不可能的大值
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * LeetCode 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度
     * 
     * @param nums 整数数组
     * @return 最长递增子序列长度
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        int maxLength = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
    
    /**
     * 最长递增子序列（二分优化版本）
     * 时间复杂度：O(n log n)
     * 
     * @param nums 整数数组
     * @return 最长递增子序列长度
     */
    public static int lengthOfLISOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            int left = 0, right = tails.size();
            
            // 二分查找插入位置
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            if (left == tails.size()) {
                tails.add(num);
            } else {
                tails.set(left, num);
            }
        }
        
        return tails.size();
    }
    
    /**
     * LeetCode 1143. 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度
     * 
     * @param text1 字符串1
     * @param text2 字符串2
     * @return 最长公共子序列长度
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * LeetCode 72. 编辑距离
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数
     * 你可以对一个单词进行如下三种操作：插入一个字符、删除一个字符、替换一个字符
     * 
     * @param word1 单词1
     * @param word2 单词2
     * @return 最小编辑距离
     */
    public static int minDistance(String word1, String word2) {
        if (word1 == null) word1 = "";
        if (word2 == null) word2 = "";
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // 初始化边界条件
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // 删除所有字符
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // 插入所有字符
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 不需要操作
                } else {
                    dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1,     // 删除
                                dp[i][j - 1] + 1),     // 插入
                        dp[i - 1][j - 1] + 1           // 替换
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * LeetCode 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
     * 
     * @param grid 网格
     * @return 最小路径和
     */
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0];
        
        // 初始化第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        
        // 初始化第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        
        // 填充DP表
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        
        return dp[m - 1][n - 1];
    }
    
    /**
     * 最小路径和（空间优化版本）
     * 空间复杂度：O(n)
     * 
     * @param grid 网格
     * @return 最小路径和
     */
    public static int minPathSumOptimized(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        
        dp[0] = grid[0][0];
        
        // 初始化第一行
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        
        // 逐行更新
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0]; // 第一列
            
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * LeetCode 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
     * 
     * @param nums 房屋金额数组
     * @return 能偷窃到的最高金额
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * LeetCode 213. 打家劫舍 II
     * 所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是相邻的
     * 
     * @param nums 房屋金额数组
     * @return 能偷窃到的最高金额
     */
    public static int robCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        // 情况1：偷第一间房，不偷最后一间房
        int rob1 = robRange(nums, 0, nums.length - 2);
        
        // 情况2：不偷第一间房，可以偷最后一间房
        int rob2 = robRange(nums, 1, nums.length - 1);
        
        return Math.max(rob1, rob2);
    }
    
    /**
     * 在指定范围内打家劫舍
     * 
     * @param nums 房屋金额数组
     * @param start 起始位置
     * @param end 结束位置
     * @return 最高金额
     */
    private static int robRange(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;
        
        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // ==================== 辅助方法 ====================
    
    /**
     * 生成随机物品数据
     * 
     * @param n 物品数量
     * @param maxWeight 最大重量
     * @param maxValue 最大价值
     * @return [weights, values]
     */
    public static int[][] generateRandomItems(int n, int maxWeight, int maxValue) {
        Random random = new Random();
        int[] weights = new int[n];
        int[] values = new int[n];
        
        for (int i = 0; i < n; i++) {
            weights[i] = random.nextInt(maxWeight) + 1;
            values[i] = random.nextInt(maxValue) + 1;
        }
        
        return new int[][]{weights, values};
    }
    
    /**
     * 生成随机网格
     * 
     * @param m 行数
     * @param n 列数
     * @param maxValue 最大值
     * @return 随机网格
     */
    public static int[][] generateRandomGrid(int m, int n, int maxValue) {
        Random random = new Random();
        int[][] grid = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = random.nextInt(maxValue) + 1;
            }
        }
        
        return grid;
    }
    
    /**
     * 打印网格
     * 
     * @param grid 网格
     */
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
    
    /**
     * 验证背包问题解的正确性
     * 
     * @param weights 重量数组
     * @param values 价值数组
     * @param capacity 容量
     * @param selectedItems 选择的物品
     * @return 是否正确
     */
    public static boolean validateKnapsackSolution(int[] weights, int[] values, int capacity, 
                                                 List<Integer> selectedItems) {
        int totalWeight = 0;
        int totalValue = 0;
        
        for (int index : selectedItems) {
            if (index < 0 || index >= weights.length) {
                return false;
            }
            totalWeight += weights[index];
            totalValue += values[index];
        }
        
        return totalWeight <= capacity;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== LeetCode 动态规划问题测试 ===\n");
        
        // 测试1：0-1背包问题
        System.out.println("1. 0-1背包问题测试:");
        
        Object[][] knapsackTests = {
            {new int[]{1, 3, 4, 5}, new int[]{1, 4, 5, 7}, 7},
            {new int[]{2, 1, 3, 2}, new int[]{12, 10, 20, 15}, 5},
            {new int[]{10, 20, 30}, new int[]{60, 100, 120}, 50},
            {new int[]{1, 2, 3}, new int[]{6, 10, 12}, 5}
        };
        
        for (int i = 0; i < knapsackTests.length; i++) {
            int[] weights = (int[]) knapsackTests[i][0];
            int[] values = (int[]) knapsackTests[i][1];
            int capacity = (int) knapsackTests[i][2];
            
            System.out.printf("测试 %d:%n", i + 1);
            System.out.printf("  重量: %s%n", Arrays.toString(weights));
            System.out.printf("  价值: %s%n", Arrays.toString(values));
            System.out.printf("  容量: %d%n", capacity);
            
            long startTime = System.nanoTime();
            int result1 = knapsack01(weights, values, capacity);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int result2 = knapsack01Optimized(weights, values, capacity);
            long time2 = System.nanoTime() - startTime;
            
            Object[] resultWithItems = knapsack01WithItems(weights, values, capacity);
            int maxValue = (int) resultWithItems[0];
            @SuppressWarnings("unchecked")
            List<Integer> selectedItems = (List<Integer>) resultWithItems[1];
            
            System.out.printf("  标准DP: %d (%.2fμs)%n", result1, time1 / 1000.0);
            System.out.printf("  优化DP: %d (%.2fμs)%n", result2, time2 / 1000.0);
            System.out.printf("  带物品: %d, 选择物品: %s%n", maxValue, selectedItems);
            System.out.printf("  一致性: %s%n", result1 == result2 && result1 == maxValue);
            
            // 验证解的正确性
            boolean valid = validateKnapsackSolution(weights, values, capacity, selectedItems);
            System.out.printf("  解的正确性: %s%n", valid);
            
            if (valid && !selectedItems.isEmpty()) {
                int totalWeight = 0, totalValue = 0;
                for (int index : selectedItems) {
                    totalWeight += weights[index];
                    totalValue += values[index];
                }
                System.out.printf("  实际重量: %d, 实际价值: %d%n", totalWeight, totalValue);
            }
            
            System.out.println();
        }
        
        // 测试2：零钱兑换
        System.out.println("2. 零钱兑换测试:");
        
        Object[][] coinChangeTests = {
            {new int[]{1, 3, 4}, 6},
            {new int[]{2}, 3},
            {new int[]{1}, 0},
            {new int[]{1, 2, 5}, 11},
            {new int[]{2, 5, 10, 1}, 27}
        };
        
        for (int i = 0; i < coinChangeTests.length; i++) {
            int[] coins = (int[]) coinChangeTests[i][0];
            int amount = (int) coinChangeTests[i][1];
            
            System.out.printf("测试 %d: 硬币=%s, 金额=%d%n", 
                            i + 1, Arrays.toString(coins), amount);
            
            long startTime = System.nanoTime();
            int result = coinChange(coins, amount);
            long time = System.nanoTime() - startTime;
            
            System.out.printf("  最少硬币数: %d (%.2fμs)%n", result, time / 1000.0);
            System.out.println();
        }
        
        // 测试3：最长递增子序列
        System.out.println("3. 最长递增子序列测试:");
        
        int[][] lisTests = {
            {10, 9, 2, 5, 3, 7, 101, 18},
            {0, 1, 0, 3, 2, 3},
            {7, 7, 7, 7, 7, 7, 7},
            {1, 3, 6, 7, 9, 4, 10, 5, 6}
        };
        
        for (int i = 0; i < lisTests.length; i++) {
            int[] nums = lisTests[i];
            
            System.out.printf("测试 %d: %s%n", i + 1, Arrays.toString(nums));
            
            long startTime = System.nanoTime();
            int result1 = lengthOfLIS(nums);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int result2 = lengthOfLISOptimized(nums);
            long time2 = System.nanoTime() - startTime;
            
            System.out.printf("  标准DP: %d (%.2fμs)%n", result1, time1 / 1000.0);
            System.out.printf("  二分优化: %d (%.2fμs)%n", result2, time2 / 1000.0);
            System.out.printf("  一致性: %s%n", result1 == result2);
            System.out.println();
        }
        
        // 测试4：最长公共子序列
        System.out.println("4. 最长公共子序列测试:");
        
        String[][] lcsTests = {
            {"abcde", "ace"},
            {"abc", "abc"},
            {"abc", "def"},
            {"ABCDGH", "AEDFHR"},
            {"AGGTAB", "GXTXAYB"}
        };
        
        for (int i = 0; i < lcsTests.length; i++) {
            String text1 = lcsTests[i][0];
            String text2 = lcsTests[i][1];
            
            System.out.printf("测试 %d: \"%s\" 和 \"%s\"%n", i + 1, text1, text2);
            
            long startTime = System.nanoTime();
            int result = longestCommonSubsequence(text1, text2);
            long time = System.nanoTime() - startTime;
            
            System.out.printf("  LCS长度: %d (%.2fμs)%n", result, time / 1000.0);
            System.out.println();
        }
        
        // 测试5：编辑距离
        System.out.println("5. 编辑距离测试:");
        
        String[][] editDistanceTests = {
            {"horse", "ros"},
            {"intention", "execution"},
            {"", "a"},
            {"a", ""},
            {"abc", "abc"}
        };
        
        for (int i = 0; i < editDistanceTests.length; i++) {
            String word1 = editDistanceTests[i][0];
            String word2 = editDistanceTests[i][1];
            
            System.out.printf("测试 %d: \"%s\" -> \"%s\"%n", i + 1, word1, word2);
            
            long startTime = System.nanoTime();
            int result = minDistance(word1, word2);
            long time = System.nanoTime() - startTime;
            
            System.out.printf("  最小编辑距离: %d (%.2fμs)%n", result, time / 1000.0);
            System.out.println();
        }
        
        // 测试6：最小路径和
        System.out.println("6. 最小路径和测试:");
        
        int[][][] pathSumTests = {
            {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}},
            {{1, 2, 3}, {4, 5, 6}},
            {{1}},
            {{1, 2}, {3, 4}}
        };
        
        for (int i = 0; i < pathSumTests.length; i++) {
            int[][] grid = pathSumTests[i];
            
            System.out.printf("测试 %d:%n", i + 1);
            printGrid(grid);
            
            long startTime = System.nanoTime();
            int result1 = minPathSum(grid);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int result2 = minPathSumOptimized(grid);
            long time2 = System.nanoTime() - startTime;
            
            System.out.printf("  标准DP: %d (%.2fμs)%n", result1, time1 / 1000.0);
            System.out.printf("  优化DP: %d (%.2fμs)%n", result2, time2 / 1000.0);
            System.out.printf("  一致性: %s%n", result1 == result2);
            System.out.println();
        }
        
        // 测试7：打家劫舍
        System.out.println("7. 打家劫舍测试:");
        
        int[][] robTests = {
            {1, 2, 3, 1},
            {2, 7, 9, 3, 1},
            {5},
            {2, 1, 1, 2},
            {5, 1, 3, 9}
        };
        
        for (int i = 0; i < robTests.length; i++) {
            int[] nums = robTests[i];
            
            System.out.printf("测试 %d: %s%n", i + 1, Arrays.toString(nums));
            
            long startTime = System.nanoTime();
            int result1 = rob(nums);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int result2 = robCircular(nums);
            long time2 = System.nanoTime() - startTime;
            
            System.out.printf("  线性房屋: %d (%.2fμs)%n", result1, time1 / 1000.0);
            System.out.printf("  环形房屋: %d (%.2fμs)%n", result2, time2 / 1000.0);
            System.out.println();
        }
        
        // 测试8：性能测试
        System.out.println("8. 性能测试:");
        
        // 0-1背包性能测试
        System.out.println("0-1背包性能测试:");
        int[] itemCounts = {50, 100, 200};
        int[] capacities = {100, 200, 500};
        
        for (int i = 0; i < itemCounts.length; i++) {
            int n = itemCounts[i];
            int capacity = capacities[i];
            
            int[][] items = generateRandomItems(n, 20, 50);
            int[] weights = items[0];
            int[] values = items[1];
            
            System.out.printf("物品数=%d, 容量=%d:%n", n, capacity);
            
            long startTime = System.currentTimeMillis();
            int result1 = knapsack01(weights, values, capacity);
            long time1 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            int result2 = knapsack01Optimized(weights, values, capacity);
            long time2 = System.currentTimeMillis() - startTime;
            
            System.out.printf("  标准DP: %d (%dms)%n", result1, time1);
            System.out.printf("  优化DP: %d (%dms)%n", result2, time2);
            System.out.printf("  一致性: %s%n", result1 == result2);
            System.out.printf("  性能提升: %.2fx%n", time1 == 0 ? 1.0 : (double) time1 / time2);
            System.out.println();
        }
        
        // 最长递增子序列性能测试
        System.out.println("最长递增子序列性能测试:");
        int[] arraySizes = {1000, 5000, 10000};
        
        for (int size : arraySizes) {
            Random random = new Random();
            int[] nums = new int[size];
            for (int i = 0; i < size; i++) {
                nums[i] = random.nextInt(size);
            }
            
            System.out.printf("数组大小=%d:%n", size);
            
            long startTime = System.currentTimeMillis();
            int result1 = lengthOfLIS(nums);
            long time1 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            int result2 = lengthOfLISOptimized(nums);
            long time2 = System.currentTimeMillis() - startTime;
            
            System.out.printf("  标准DP: %d (%dms)%n", result1, time1);
            System.out.printf("  二分优化: %d (%dms)%n", result2, time2);
            System.out.printf("  一致性: %s%n", result1 == result2);
            System.out.printf("  性能提升: %.2fx%n", time1 == 0 ? 1.0 : (double) time1 / time2);
            System.out.println();
        }
        
        // 测试9：边界情况
        System.out.println("9. 边界情况测试:");
        
        System.out.println("0-1背包边界情况:");
        System.out.println("空物品: " + knapsack01(new int[]{}, new int[]{}, 10));
        System.out.println("容量为0: " + knapsack01(new int[]{1, 2}, new int[]{3, 4}, 0));
        System.out.println("单个物品: " + knapsack01(new int[]{5}, new int[]{10}, 10));
        
        System.out.println("\n零钱兑换边界情况:");
        System.out.println("金额为0: " + coinChange(new int[]{1, 2, 5}, 0));
        System.out.println("无法兑换: " + coinChange(new int[]{2}, 3));
        System.out.println("空硬币: " + coinChange(new int[]{}, 1));
        
        System.out.println("\n最长递增子序列边界情况:");
        System.out.println("空数组: " + lengthOfLIS(new int[]{}));
        System.out.println("单元素: " + lengthOfLIS(new int[]{1}));
        System.out.println("递减序列: " + lengthOfLIS(new int[]{5, 4, 3, 2, 1}));
        System.out.println("递增序列: " + lengthOfLIS(new int[]{1, 2, 3, 4, 5}));
        
        System.out.println("\n最长公共子序列边界情况:");
        System.out.println("空字符串: " + longestCommonSubsequence("", "abc"));
        System.out.println("相同字符串: " + longestCommonSubsequence("abc", "abc"));
        System.out.println("无公共字符: " + longestCommonSubsequence("abc", "def"));
        
        System.out.println("\n编辑距离边界情况:");
        System.out.println("空字符串: " + minDistance("", "abc"));
        System.out.println("相同字符串: " + minDistance("abc", "abc"));
        System.out.println("单字符: " + minDistance("a", "b"));
        
        System.out.println("\n打家劫舍边界情况:");
        System.out.println("空房屋: " + rob(new int[]{}));
        System.out.println("单间房屋: " + rob(new int[]{5}));
        System.out.println("两间房屋: " + rob(new int[]{2, 3}));
        
        // 测试10：正确性验证
        System.out.println("\n10. 正确性验证:");
        
        // 大量随机测试
        Random random = new Random(42);
        int correctCount = 0;
        int totalTests = 100;
        
        // 验证0-1背包算法一致性
        for (int i = 0; i < totalTests; i++) {
            int n = random.nextInt(20) + 1;
            int capacity = random.nextInt(50) + 1;
            
            int[][] items = generateRandomItems(n, 10, 20);
            int[] weights = items[0];
            int[] values = items[1];
            
            int result1 = knapsack01(weights, values, capacity);
            int result2 = knapsack01Optimized(weights, values, capacity);
            
            if (result1 == result2) {
                correctCount++;
            }
        }
        
        System.out.printf("0-1背包算法一致性: %d/%d 正确, 正确率: %.2f%%%n", 
                        correctCount, totalTests, 100.0 * correctCount / totalTests);
        
        // 验证最长递增子序列算法一致性
        correctCount = 0;
        for (int i = 0; i < totalTests; i++) {
            int size = random.nextInt(50) + 1;
            int[] nums = new int[size];
            for (int j = 0; j < size; j++) {
                nums[j] = random.nextInt(100);
            }
            
            int result1 = lengthOfLIS(nums);
            int result2 = lengthOfLISOptimized(nums);
            
            if (result1 == result2) {
                correctCount++;
            }
        }
        
        System.out.printf("最长递增子序列算法一致性: %d/%d 正确, 正确率: %.2f%%%n", 
                        correctCount, totalTests, 100.0 * correctCount / totalTests);
        
        // 验证最小路径和算法一致性
        correctCount = 0;
        for (int i = 0; i < totalTests; i++) {
            int m = random.nextInt(10) + 1;
            int n = random.nextInt(10) + 1;
            int[][] grid = generateRandomGrid(m, n, 10);
            
            int result1 = minPathSum(grid);
            int result2 = minPathSumOptimized(grid);
            
            if (result1 == result2) {
                correctCount++;
            }
        }
        
        System.out.printf("最小路径和算法一致性: %d/%d 正确, 正确率: %.2f%%%n", 
                        correctCount, totalTests, 100.0 * correctCount / totalTests);
        
        System.out.println("\n=== 动态规划测试完成 ===");
    }
}