package dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 动态规划算法实现集合 包含0-1背包、最小路径和、编辑距离、最长公共子序列、最长递增子序列等经典DP问题
 */
public class DynamicProgramming {

  // ==================== 0-1背包问题 ====================

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 动态规划算法测试 ===\n");

    // 测试1：0-1背包问题
    System.out.println("1. 0-1背包问题测试:");

    Knapsack01DP.Item[] items = {
        new Knapsack01DP.Item(10, 60, 0),
        new Knapsack01DP.Item(20, 100, 1),
        new Knapsack01DP.Item(30, 120, 2)
    };

    int capacity = 50;

    Knapsack01DP.KnapsackSolution solution = Knapsack01DP.knapsack01(items, capacity);
    int optimizedValue = Knapsack01DP.knapsack01Optimized(items, capacity);

    System.out.println("背包容量: " + capacity);
    System.out.println("二维DP解: " + solution);
    System.out.println("一维DP最大价值: " + optimizedValue);
    System.out.println("结果一致性: " + (solution.maxValue == optimizedValue));

    // 测试2：最小路径和
    System.out.println("\n2. 最小路径和测试:");

    int[][] grid = {
        {1, 3, 1},
        {1, 5, 1},
        {4, 2, 1}
    };

    int minSum = MinPathSum.minPathSum(grid);
    int minSumOpt = MinPathSum.minPathSumOptimized(grid);
    List<int[]> path = MinPathSum.getMinPath(grid);

    System.out.println("二维DP最小路径和: " + minSum);
    System.out.println("一维DP最小路径和: " + minSumOpt);
    System.out.println("结果一致性: " + (minSum == minSumOpt));

    // 测试3：编辑距离
    System.out.println("\n3. 编辑距离测试:");

    String word1 = "horse";
    String word2 = "ros";

    int editDist = EditDistance.minDistance(word1, word2);
    int editDistOpt = EditDistance.minDistanceOptimized(word1, word2);

    System.out.println("字符串1: \"" + word1 + "\"");
    System.out.println("字符串2: \"" + word2 + "\"");
    System.out.println("二维DP编辑距离: " + editDist);
    System.out.println("优化DP编辑距离: " + editDistOpt);
    System.out.println("结果一致性: " + (editDist == editDistOpt));

    // 测试4：最长公共子序列
    System.out.println("\n4. 最长公共子序列测试:");

    String text1 = "abcde";
    String text2 = "ace";

    int lcsLength = LongestCommonSubsequence.longestCommonSubsequence(text1, text2);
    String lcsString = LongestCommonSubsequence.getLCS(text1, text2);

    System.out.println("字符串1: \"" + text1 + "\"");
    System.out.println("字符串2: \"" + text2 + "\"");
    System.out.println("LCS长度: " + lcsLength);
    System.out.println("LCS字符串: \"" + lcsString + "\"");

    // 测试5：最长递增子序列
    System.out.println("\n5. 最长递增子序列测试:");

    int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

    int lisLength = LongestIncreasingSubsequence.lengthOfLIS(nums);
    int lisLengthOpt = LongestIncreasingSubsequence.lengthOfLISOptimized(nums);
    List<Integer> lisSequence = LongestIncreasingSubsequence.getLIS(nums);

    System.out.println("数组: " + Arrays.toString(nums));
    System.out.println("DP方法 LIS长度: " + lisLength);
    System.out.println("二分优化 LIS长度: " + lisLengthOpt);
    System.out.println("结果一致性: " + (lisLength == lisLengthOpt));
    System.out.println("LIS序列: " + lisSequence);
  }

  // ==================== 最小路径和 ====================

  /**
   * 0-1背包问题（动态规划解法） 给定一组物品，每个物品有重量和价值，在限定的背包容量下，选择物品使价值最大
   */
  public static class Knapsack01DP {

    /**
     * 0-1背包问题求解（二维DP） 时间复杂度：O(nW)，空间复杂度：O(nW)
     *
     * @param items    物品数组
     * @param capacity 背包容量
     * @return 背包解决方案
     */
    public static KnapsackSolution knapsack01(Item[] items, int capacity) {
      int n = items.length;

      // dp[i][w] 表示前i个物品在容量为w时的最大价值
      int[][] dp = new int[n + 1][capacity + 1];

      // 填充DP表
      for (int i = 1; i <= n; i++) {
        Item item = items[i - 1];
        for (int w = 0; w <= capacity; w++) {
          // 不选择当前物品
          dp[i][w] = dp[i - 1][w];

          // 如果能选择当前物品，比较选择和不选择的价值
          if (w >= item.weight) {
            dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - item.weight] + item.value);
          }
        }
      }

      // 回溯找出选择的物品
      List<Item> selectedItems = new ArrayList<>();
      int w = capacity;
      for (int i = n; i > 0 && w > 0; i--) {
        // 如果当前状态的值不等于上一行同列的值，说明选择了当前物品
        if (dp[i][w] != dp[i - 1][w]) {
          selectedItems.add(items[i - 1]);
          w -= items[i - 1].weight;
        }
      }

      return new KnapsackSolution(dp[n][capacity], selectedItems);
    }

    /**
     * 0-1背包问题求解（一维DP优化） 时间复杂度：O(nW)，空间复杂度：O(W)
     *
     * @param items    物品数组
     * @param capacity 背包容量
     * @return 最大价值
     */
    public static int knapsack01Optimized(Item[] items, int capacity) {
      int[] dp = new int[capacity + 1];

      for (Item item : items) {
        // 从后往前遍历，避免重复使用同一物品
        for (int w = capacity; w >= item.weight; w--) {
          dp[w] = Math.max(dp[w], dp[w - item.weight] + item.value);
        }
      }

      return dp[capacity];
    }

    /**
     * 物品类
     */
    public static class Item {

      public int weight;
      public int value;
      public int index;

      public Item(int weight, int value, int index) {
        this.weight = weight;
        this.value = value;
        this.index = index;
      }

      @Override
      public String toString() {
        return "Item{weight=" + weight + ", value=" + value + ", index=" + index + "}";
      }
    }

    /**
     * 背包解决方案
     */
    public static class KnapsackSolution {

      public int maxValue;
      public List<Item> selectedItems;
      public int totalWeight;

      public KnapsackSolution(int maxValue, List<Item> selectedItems) {
        this.maxValue = maxValue;
        this.selectedItems = new ArrayList<>(selectedItems);
        this.totalWeight = selectedItems.stream().mapToInt(item -> item.weight).sum();
      }

      @Override
      public String toString() {
        return "KnapsackSolution{maxValue=" + maxValue + ", totalWeight=" + totalWeight +
            ", itemCount=" + selectedItems.size() + "}";
      }
    }
  }

  // ==================== 编辑距离 ====================

  /**
   * 最小路径和问题 给定一个包含非负整数的 m x n 网格，找到一条从左上角到右下角的路径，使得路径上的数字总和为最小
   */
  public static class MinPathSum {

    /**
     * 最小路径和（二维DP） 时间复杂度：O(mn)，空间复杂度：O(mn)
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

      // dp[i][j] 表示到达位置(i,j)的最小路径和
      int[][] dp = new int[m][n];

      // 初始化起点
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
     * 最小路径和（一维DP优化） 时间复杂度：O(mn)，空间复杂度：O(n)
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

      // 初始化第一行
      dp[0] = grid[0][0];
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
     * 获取最小路径（包含路径信息）
     *
     * @param grid 网格
     * @return 路径坐标列表
     */
    public static List<int[]> getMinPath(int[][] grid) {
      if (grid == null || grid.length == 0 || grid[0].length == 0) {
        return new ArrayList<>();
      }

      int m = grid.length;
      int n = grid[0].length;
      int[][] dp = new int[m][n];

      // 计算最小路径和
      dp[0][0] = grid[0][0];

      for (int j = 1; j < n; j++) {
        dp[0][j] = dp[0][j - 1] + grid[0][j];
      }

      for (int i = 1; i < m; i++) {
        dp[i][0] = dp[i - 1][0] + grid[i][0];
      }

      for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
      }

      // 回溯构建路径
      List<int[]> path = new ArrayList<>();
      int i = m - 1, j = n - 1;

      while (i > 0 || j > 0) {
        path.add(new int[]{i, j});

        if (i == 0) {
          j--;
        } else if (j == 0) {
          i--;
        } else {
          // 选择来源路径和更小的方向
          if (dp[i - 1][j] < dp[i][j - 1]) {
            i--;
          } else {
            j--;
          }
        }
      }

      path.add(new int[]{0, 0});
      Collections.reverse(path);

      return path;
    }
  }

  // ==================== 最长公共子序列 ====================

  /**
   * 编辑距离（莱文斯坦距离） 计算将一个字符串转换为另一个字符串所需的最少编辑操作数 允许的操作：插入、删除、替换
   */
  public static class EditDistance {

    /**
     * 计算编辑距离 时间复杂度：O(mn)，空间复杂度：O(mn)
     *
     * @param word1 第一个字符串
     * @param word2 第二个字符串
     * @return 编辑距离
     */
    public static int minDistance(String word1, String word2) {
      int m = word1.length();
      int n = word2.length();

      // dp[i][j] 表示word1的前i个字符转换为word2的前j个字符的最小编辑距离
      int[][] dp = new int[m + 1][n + 1];

      // 初始化边界条件
      for (int i = 0; i <= m; i++) {
        dp[i][0] = i; // 删除i个字符
      }
      for (int j = 0; j <= n; j++) {
        dp[0][j] = j; // 插入j个字符
      }

      // 填充DP表
      for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
          if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            // 字符相同，不需要操作
            dp[i][j] = dp[i - 1][j - 1];
          } else {
            // 字符不同，选择三种操作中的最小值
            dp[i][j] = Math.min(
                Math.min(
                    dp[i - 1][j] + 1,     // 删除
                    dp[i][j - 1] + 1      // 插入
                ),
                dp[i - 1][j - 1] + 1      // 替换
            );
          }
        }
      }

      return dp[m][n];
    }

    /**
     * 计算编辑距离（空间优化） 时间复杂度：O(mn)，空间复杂度：O(min(m,n))
     *
     * @param word1 第一个字符串
     * @param word2 第二个字符串
     * @return 编辑距离
     */
    public static int minDistanceOptimized(String word1, String word2) {
      // 确保word1是较短的字符串
      if (word1.length() > word2.length()) {
        return minDistanceOptimized(word2, word1);
      }

      int m = word1.length();
      int n = word2.length();

      int[] prev = new int[m + 1];
      int[] curr = new int[m + 1];

      // 初始化第一行
      for (int i = 0; i <= m; i++) {
        prev[i] = i;
      }

      // 逐行计算
      for (int j = 1; j <= n; j++) {
        curr[0] = j;

        for (int i = 1; i <= m; i++) {
          if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            curr[i] = prev[i - 1];
          } else {
            curr[i] = Math.min(
                Math.min(prev[i] + 1, curr[i - 1] + 1),
                prev[i - 1] + 1
            );
          }
        }

        // 交换数组
        int[] temp = prev;
        prev = curr;
        curr = temp;
      }

      return prev[m];
    }

    /**
     * 获取编辑操作序列
     *
     * @param word1 第一个字符串
     * @param word2 第二个字符串
     * @return 操作序列
     */
    public static List<String> getEditOperations(String word1, String word2) {
      int m = word1.length();
      int n = word2.length();
      int[][] dp = new int[m + 1][n + 1];

      // 计算编辑距离
      for (int i = 0; i <= m; i++) {
        dp[i][0] = i;
      }
      for (int j = 0; j <= n; j++) {
        dp[0][j] = j;
      }

      for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
          if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            dp[i][j] = dp[i - 1][j - 1];
          } else {
            dp[i][j] = Math.min(
                Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                dp[i - 1][j - 1] + 1
            );
          }
        }
      }

      // 回溯构建操作序列
      List<String> operations = new ArrayList<>();
      int i = m, j = n;

      while (i > 0 || j > 0) {
        if (i > 0 && j > 0 && word1.charAt(i - 1) == word2.charAt(j - 1)) {
          // 字符相同，无需操作
          i--;
          j--;
        } else if (i > 0 && j > 0 && dp[i][j] == dp[i - 1][j - 1] + 1) {
          // 替换操作
          operations.add("Replace '" + word1.charAt(i - 1) + "' with '" + word2.charAt(j - 1)
              + "' at position " + (i - 1));
          i--;
          j--;
        } else if (i > 0 && dp[i][j] == dp[i - 1][j] + 1) {
          // 删除操作
          operations.add("Delete '" + word1.charAt(i - 1) + "' at position " + (i - 1));
          i--;
        } else {
          // 插入操作
          operations.add("Insert '" + word2.charAt(j - 1) + "' at position " + i);
          j--;
        }
      }

      Collections.reverse(operations);
      return operations;
    }
  }

  // ==================== 最长递增子序列 ====================

  /**
   * 最长公共子序列（LCS） 找到两个字符串的最长公共子序列
   */
  public static class LongestCommonSubsequence {

    /**
     * 计算最长公共子序列长度 时间复杂度：O(mn)，空间复杂度：O(mn)
     *
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return LCS长度
     */
    public static int longestCommonSubsequence(String text1, String text2) {
      int m = text1.length();
      int n = text2.length();

      // dp[i][j] 表示text1前i个字符和text2前j个字符的LCS长度
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
     * 获取最长公共子序列
     *
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return LCS字符串
     */
    public static String getLCS(String text1, String text2) {
      int m = text1.length();
      int n = text2.length();
      int[][] dp = new int[m + 1][n + 1];

      // 计算LCS长度
      for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
          if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            dp[i][j] = dp[i - 1][j - 1] + 1;
          } else {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
          }
        }
      }

      // 回溯构建LCS
      StringBuilder lcs = new StringBuilder();
      int i = m, j = n;

      while (i > 0 && j > 0) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          lcs.append(text1.charAt(i - 1));
          i--;
          j--;
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
          i--;
        } else {
          j--;
        }
      }

      return lcs.reverse().toString();
    }

    /**
     * 空间优化版本 时间复杂度：O(mn)，空间复杂度：O(min(m,n))
     *
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return LCS长度
     */
    public static int longestCommonSubsequenceOptimized(String text1, String text2) {
      // 确保text1是较短的字符串
      if (text1.length() > text2.length()) {
        return longestCommonSubsequenceOptimized(text2, text1);
      }

      int m = text1.length();
      int n = text2.length();

      int[] prev = new int[m + 1];
      int[] curr = new int[m + 1];

      for (int j = 1; j <= n; j++) {
        for (int i = 1; i <= m; i++) {
          if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            curr[i] = prev[i - 1] + 1;
          } else {
            curr[i] = Math.max(prev[i], curr[i - 1]);
          }
        }

        // 交换数组
        int[] temp = prev;
        prev = curr;
        curr = temp;
      }

      return prev[m];
    }
  }

  /**
   * 最长递增子序列（LIS） 找到数组中最长的严格递增子序列
   */
  public static class LongestIncreasingSubsequence {

    /**
     * 计算最长递增子序列长度（DP方法） 时间复杂度：O(n²)，空间复杂度：O(n)
     *
     * @param nums 输入数组
     * @return LIS长度
     */
    public static int lengthOfLIS(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }

      int n = nums.length;
      int[] dp = new int[n];
      Arrays.fill(dp, 1); // 每个元素自身构成长度为1的递增子序列

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
     * 计算最长递增子序列长度（二分查找优化） 时间复杂度：O(n log n)，空间复杂度：O(n)
     *
     * @param nums 输入数组
     * @return LIS长度
     */
    public static int lengthOfLISOptimized(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }

      List<Integer> tails = new ArrayList<>();

      for (int num : nums) {
        int left = 0, right = tails.size();

        // 二分查找第一个大于等于num的位置
        while (left < right) {
          int mid = left + (right - left) / 2;
          if (tails.get(mid) < num) {
            left = mid + 1;
          } else {
            right = mid;
          }
        }

        // 如果找到末尾，说明num比所有元素都大，直接添加
        if (left == tails.size()) {
          tails.add(num);
        } else {
          // 否则替换找到位置的元素
          tails.set(left, num);
        }
      }

      return tails.size();
    }

    /**
     * 获取最长递增子序列
     *
     * @param nums 输入数组
     * @return LIS数组
     */
    public static List<Integer> getLIS(int[] nums) {
      if (nums == null || nums.length == 0) {
        return new ArrayList<>();
      }

      int n = nums.length;
      int[] dp = new int[n];
      int[] prev = new int[n];
      Arrays.fill(dp, 1);
      Arrays.fill(prev, -1);

      int maxLength = 1;
      int maxIndex = 0;

      // 计算DP数组和前驱数组
      for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
          if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
            dp[i] = dp[j] + 1;
            prev[i] = j;
          }
        }

        if (dp[i] > maxLength) {
          maxLength = dp[i];
          maxIndex = i;
        }
      }

      // 回溯构建LIS
      List<Integer> lis = new ArrayList<>();
      int current = maxIndex;

      while (current != -1) {
        lis.add(nums[current]);
        current = prev[current];
      }

      Collections.reverse(lis);
      return lis;
    }
  }
}