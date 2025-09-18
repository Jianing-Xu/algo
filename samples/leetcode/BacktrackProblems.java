package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode回溯算法相关问题解答 包含N皇后问题等经典回溯题目
 */
public class BacktrackProblems {

  // ==================== N-Queens（N皇后问题）====================

  /**
   * LeetCode 51. N 皇后 n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
   * <p>
   * 解题思路：回溯算法 1. 逐行放置皇后 2. 对于每一行，尝试在每一列放置皇后 3. 检查当前位置是否与之前放置的皇后冲突 4. 如果不冲突，继续递归下一行；如果冲突，回溯尝试下一列
   * <p>
   * 时间复杂度：O(N!) 空间复杂度：O(N)
   *
   * @param n 棋盘大小
   * @return 所有可能的解
   */
  public static List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    int[] queens = new int[n]; // queens[i] 表示第i行皇后所在的列
    Arrays.fill(queens, -1);

    backtrackNQueens(result, queens, 0, n);
    return result;
  }

  /**
   * N皇后回溯递归
   *
   * @param result 结果集
   * @param queens 皇后位置数组
   * @param row    当前行
   * @param n      棋盘大小
   */
  private static void backtrackNQueens(List<List<String>> result, int[] queens, int row, int n) {
    if (row == n) {
      // 找到一个解
      result.add(generateBoard(queens, n));
      return;
    }

    for (int col = 0; col < n; col++) {
      if (isValidPosition(queens, row, col)) {
        queens[row] = col;
        backtrackNQueens(result, queens, row + 1, n);
        queens[row] = -1; // 回溯
      }
    }
  }

  /**
   * 检查位置是否有效
   *
   * @param queens 皇后位置数组
   * @param row    当前行
   * @param col    当前列
   * @return 是否有效
   */
  private static boolean isValidPosition(int[] queens, int row, int col) {
    for (int i = 0; i < row; i++) {
      int queenCol = queens[i];

      // 检查列冲突
      if (queenCol == col) {
        return false;
      }

      // 检查对角线冲突
      if (Math.abs(i - row) == Math.abs(queenCol - col)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 生成棋盘表示
   *
   * @param queens 皇后位置数组
   * @param n      棋盘大小
   * @return 棋盘字符串列表
   */
  private static List<String> generateBoard(int[] queens, int n) {
    List<String> board = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      StringBuilder row = new StringBuilder();
      for (int j = 0; j < n; j++) {
        if (queens[i] == j) {
          row.append('Q');
        } else {
          row.append('.');
        }
      }
      board.add(row.toString());
    }

    return board;
  }

  /**
   * LeetCode 52. N皇后 II 返回 n 皇后问题不同的解的数量
   *
   * @param n 棋盘大小
   * @return 解的数量
   */
  public static int totalNQueens(int n) {
    return backtrackCountNQueens(new int[n], 0, n);
  }

  /**
   * N皇后计数回溯
   *
   * @param queens 皇后位置数组
   * @param row    当前行
   * @param n      棋盘大小
   * @return 解的数量
   */
  private static int backtrackCountNQueens(int[] queens, int row, int n) {
    if (row == n) {
      return 1;
    }

    int count = 0;
    for (int col = 0; col < n; col++) {
      if (isValidPosition(queens, row, col)) {
        queens[row] = col;
        count += backtrackCountNQueens(queens, row + 1, n);
        queens[row] = -1;
      }
    }

    return count;
  }

  /**
   * N皇后问题优化版本（使用位运算）
   *
   * @param n 棋盘大小
   * @return 解的数量
   */
  public static int totalNQueensBitwise(int n) {
    return backtrackBitwise(0, 0, 0, n);
  }

  /**
   * 位运算优化的N皇后回溯
   *
   * @param cols  列占用位图
   * @param diag1 主对角线占用位图
   * @param diag2 副对角线占用位图
   * @param n     棋盘大小
   * @return 解的数量
   */
  private static int backtrackBitwise(int cols, int diag1, int diag2, int n) {
    if (cols == (1 << n) - 1) {
      return 1;
    }

    int count = 0;
    int available = ((1 << n) - 1) & (~(cols | diag1 | diag2));

    while (available != 0) {
      int pos = available & (-available); // 获取最低位的1
      available &= (available - 1); // 清除最低位的1

      count += backtrackBitwise(cols | pos, (diag1 | pos) << 1, (diag2 | pos) >> 1, n);
    }

    return count;
  }

  // ==================== 其他经典回溯问题 ====================

  /**
   * LeetCode 46. 全排列 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列
   *
   * @param nums 数字数组
   * @return 所有排列
   */
  public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();
    boolean[] used = new boolean[nums.length];

    backtrackPermute(result, current, nums, used);
    return result;
  }

  /**
   * 全排列回溯
   *
   * @param result  结果集
   * @param current 当前排列
   * @param nums    原数组
   * @param used    使用标记
   */
  private static void backtrackPermute(List<List<Integer>> result, List<Integer> current,
      int[] nums, boolean[] used) {
    if (current.size() == nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (!used[i]) {
        current.add(nums[i]);
        used[i] = true;

        backtrackPermute(result, current, nums, used);

        current.remove(current.size() - 1);
        used[i] = false;
      }
    }
  }

  /**
   * LeetCode 47. 全排列 II 给定一个可包含重复数字的序列 nums ，按任意顺序返回所有不重复的全排列
   *
   * @param nums 数字数组
   * @return 所有不重复排列
   */
  public static List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();
    boolean[] used = new boolean[nums.length];

    Arrays.sort(nums); // 排序以便处理重复元素
    backtrackPermuteUnique(result, current, nums, used);
    return result;
  }

  /**
   * 去重全排列回溯
   *
   * @param result  结果集
   * @param current 当前排列
   * @param nums    原数组
   * @param used    使用标记
   */
  private static void backtrackPermuteUnique(List<List<Integer>> result, List<Integer> current,
      int[] nums, boolean[] used) {
    if (current.size() == nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }

      // 跳过重复元素
      if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
        continue;
      }

      current.add(nums[i]);
      used[i] = true;

      backtrackPermuteUnique(result, current, nums, used);

      current.remove(current.size() - 1);
      used[i] = false;
    }
  }

  /**
   * LeetCode 77. 组合 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合
   *
   * @param n 范围上限
   * @param k 组合大小
   * @return 所有组合
   */
  public static List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();

    backtrackCombine(result, current, 1, n, k);
    return result;
  }

  /**
   * 组合回溯
   *
   * @param result  结果集
   * @param current 当前组合
   * @param start   起始数字
   * @param n       范围上限
   * @param k       组合大小
   */
  private static void backtrackCombine(List<List<Integer>> result, List<Integer> current,
      int start, int n, int k) {
    if (current.size() == k) {
      result.add(new ArrayList<>(current));
      return;
    }

    // 剪枝：如果剩余数字不够组成k个数的组合，直接返回
    for (int i = start; i <= n - (k - current.size()) + 1; i++) {
      current.add(i);
      backtrackCombine(result, current, i + 1, n, k);
      current.remove(current.size() - 1);
    }
  }

  /**
   * LeetCode 78. 子集 给你一个整数数组 nums ，数组中的元素互不相同。返回该数组所有可能的子集（幂集）
   *
   * @param nums 数字数组
   * @return 所有子集
   */
  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();

    backtrackSubsets(result, current, nums, 0);
    return result;
  }

  /**
   * 子集回溯
   *
   * @param result  结果集
   * @param current 当前子集
   * @param nums    原数组
   * @param start   起始索引
   */
  private static void backtrackSubsets(List<List<Integer>> result, List<Integer> current,
      int[] nums, int start) {
    result.add(new ArrayList<>(current));

    for (int i = start; i < nums.length; i++) {
      current.add(nums[i]);
      backtrackSubsets(result, current, nums, i + 1);
      current.remove(current.size() - 1);
    }
  }

  /**
   * LeetCode 39. 组合总和 给你一个无重复元素的整数数组 candidates 和一个目标整数 target 找出 candidates 中可以使数字和为目标数 target
   * 的所有不同组合
   *
   * @param candidates 候选数组
   * @param target     目标和
   * @return 所有组合
   */
  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();

    Arrays.sort(candidates); // 排序以便剪枝
    backtrackCombinationSum(result, current, candidates, target, 0);
    return result;
  }

  /**
   * 组合总和回溯
   *
   * @param result     结果集
   * @param current    当前组合
   * @param candidates 候选数组
   * @param target     剩余目标
   * @param start      起始索引
   */
  private static void backtrackCombinationSum(List<List<Integer>> result, List<Integer> current,
      int[] candidates, int target, int start) {
    if (target == 0) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = start; i < candidates.length; i++) {
      if (candidates[i] > target) {
        break; // 剪枝：后面的数字都更大，不可能满足条件
      }

      current.add(candidates[i]);
      backtrackCombinationSum(result, current, candidates, target - candidates[i], i);
      current.remove(current.size() - 1);
    }
  }

  /**
   * LeetCode 22. 括号生成 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合
   *
   * @param n 括号对数
   * @return 所有有效括号组合
   */
  public static List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    backtrackParenthesis(result, new StringBuilder(), 0, 0, n);
    return result;
  }

  /**
   * 括号生成回溯
   *
   * @param result  结果集
   * @param current 当前字符串
   * @param open    已使用的左括号数
   * @param close   已使用的右括号数
   * @param max     最大括号对数
   */
  private static void backtrackParenthesis(List<String> result, StringBuilder current,
      int open, int close, int max) {
    if (current.length() == max * 2) {
      result.add(current.toString());
      return;
    }

    if (open < max) {
      current.append('(');
      backtrackParenthesis(result, current, open + 1, close, max);
      current.deleteCharAt(current.length() - 1);
    }

    if (close < open) {
      current.append(')');
      backtrackParenthesis(result, current, open, close + 1, max);
      current.deleteCharAt(current.length() - 1);
    }
  }

  // ==================== 辅助方法 ====================

  /**
   * 打印N皇后解
   *
   * @param solution 解
   */
  public static void printNQueensSolution(List<String> solution) {
    for (String row : solution) {
      System.out.println(row);
    }
    System.out.println();
  }

  /**
   * 验证N皇后解的正确性
   *
   * @param solution 解
   * @return 是否正确
   */
  public static boolean validateNQueensSolution(List<String> solution) {
    int n = solution.size();
    int[] queens = new int[n];

    // 提取皇后位置
    for (int i = 0; i < n; i++) {
      String row = solution.get(i);
      for (int j = 0; j < n; j++) {
        if (row.charAt(j) == 'Q') {
          queens[i] = j;
          break;
        }
      }
    }

    // 验证每一行都有且仅有一个皇后
    for (int i = 0; i < n; i++) {
      if (queens[i] < 0 || queens[i] >= n) {
        return false;
      }
    }

    // 验证没有冲突
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        // 检查列冲突
        if (queens[i] == queens[j]) {
          return false;
        }

        // 检查对角线冲突
        if (Math.abs(i - j) == Math.abs(queens[i] - queens[j])) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * 计算回溯算法的时间复杂度（理论值）
   *
   * @param problemType 问题类型
   * @param n           问题规模
   * @return 理论时间复杂度
   */
  public static String getTimeComplexity(String problemType, int n) {
    switch (problemType.toLowerCase()) {
      case "nqueens":
        return "O(" + n + "!)";
      case "permutation":
        return "O(" + n + "!)";
      case "combination":
        return "O(2^" + n + ")";
      case "subset":
        return "O(2^" + n + ")";
      default:
        return "O(?)";
    }
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== LeetCode 回溯算法问题测试 ===\n");

    // 测试1：N皇后问题
    System.out.println("1. N皇后问题测试:");

    for (int n = 1; n <= 8; n++) {
      System.out.printf("N=%d:%n", n);

      long startTime = System.nanoTime();
      List<List<String>> solutions = solveNQueens(n);
      long time1 = System.nanoTime() - startTime;

      startTime = System.nanoTime();
      int count1 = totalNQueens(n);
      long time2 = System.nanoTime() - startTime;

      startTime = System.nanoTime();
      int count2 = totalNQueensBitwise(n);
      long time3 = System.nanoTime() - startTime;

      System.out.printf("  解的数量: %d (%.2fμs)%n", solutions.size(), time1 / 1000.0);
      System.out.printf("  计数方法1: %d (%.2fμs)%n", count1, time2 / 1000.0);
      System.out.printf("  计数方法2: %d (%.2fμs)%n", count2, time3 / 1000.0);
      System.out.printf("  一致性: %s%n", solutions.size() == count1 && count1 == count2);
      System.out.printf("  时间复杂度: %s%n", getTimeComplexity("nqueens", n));

      // 验证解的正确性
      boolean allValid = true;
      for (List<String> solution : solutions) {
        if (!validateNQueensSolution(solution)) {
          allValid = false;
          break;
        }
      }
      System.out.printf("  解的正确性: %s%n", allValid);

      if (n <= 4 && !solutions.isEmpty()) {
        System.out.println("  第一个解:");
        printNQueensSolution(solutions.get(0));
      }

      System.out.println();
    }

    // 测试2：全排列问题
    System.out.println("2. 全排列问题测试:");

    int[][] permuteTests = {
        {1, 2, 3},
        {0, 1},
        {1},
        {1, 2, 3, 4}
    };

    for (int[] nums : permuteTests) {
      System.out.println("数组: " + Arrays.toString(nums));

      long startTime = System.nanoTime();
      List<List<Integer>> permutations = permute(nums);
      long time = System.nanoTime() - startTime;

      System.out.printf("排列数量: %d (%.2fμs)%n", permutations.size(), time / 1000.0);
      System.out.printf("理论数量: %d%n", factorial(nums.length));
      System.out.printf("时间复杂度: %s%n", getTimeComplexity("permutation", nums.length));

      if (nums.length <= 3) {
        System.out.println("所有排列: " + permutations);
      }
      System.out.println();
    }

    // 测试3：去重全排列
    System.out.println("3. 去重全排列测试:");

    int[][] uniquePermuteTests = {
        {1, 1, 2},
        {1, 2, 1, 1},
        {1, 2, 3}
    };

    for (int[] nums : uniquePermuteTests) {
      System.out.println("数组: " + Arrays.toString(nums));

      long startTime = System.nanoTime();
      List<List<Integer>> uniquePermutations = permuteUnique(nums);
      long time = System.nanoTime() - startTime;

      System.out.printf("去重排列数量: %d (%.2fμs)%n", uniquePermutations.size(), time / 1000.0);
      System.out.println("所有去重排列: " + uniquePermutations);
      System.out.println();
    }

    // 测试4：组合问题
    System.out.println("4. 组合问题测试:");

    int[][] combineTests = {
        {4, 2},
        {1, 1},
        {5, 3}
    };

    for (int[] test : combineTests) {
      int n = test[0];
      int k = test[1];

      System.out.printf("C(%d, %d):%n", n, k);

      long startTime = System.nanoTime();
      List<List<Integer>> combinations = combine(n, k);
      long time = System.nanoTime() - startTime;

      System.out.printf("组合数量: %d (%.2fμs)%n", combinations.size(), time / 1000.0);
      System.out.printf("理论数量: %d%n", combination(n, k));
      System.out.println("所有组合: " + combinations);
      System.out.println();
    }

    // 测试5：子集问题
    System.out.println("5. 子集问题测试:");

    int[][] subsetTests = {
        {1, 2, 3},
        {0},
        {1, 2}
    };

    for (int[] nums : subsetTests) {
      System.out.println("数组: " + Arrays.toString(nums));

      long startTime = System.nanoTime();
      List<List<Integer>> subsets = subsets(nums);
      long time = System.nanoTime() - startTime;

      System.out.printf("子集数量: %d (%.2fμs)%n", subsets.size(), time / 1000.0);
      System.out.printf("理论数量: %d%n", 1 << nums.length);
      System.out.printf("时间复杂度: %s%n", getTimeComplexity("subset", nums.length));
      System.out.println("所有子集: " + subsets);
      System.out.println();
    }

    // 测试6：组合总和问题
    System.out.println("6. 组合总和问题测试:");

    Object[][] combinationSumTests = {
        {new int[]{2, 3, 6, 7}, 7},
        {new int[]{2, 3, 5}, 8},
        {new int[]{2}, 1}
    };

    for (Object[] test : combinationSumTests) {
      int[] candidates = (int[]) test[0];
      int target = (int) test[1];

      System.out.printf("候选数组: %s, 目标: %d%n", Arrays.toString(candidates), target);

      long startTime = System.nanoTime();
      List<List<Integer>> combinations = combinationSum(candidates, target);
      long time = System.nanoTime() - startTime;

      System.out.printf("组合数量: %d (%.2fμs)%n", combinations.size(), time / 1000.0);
      System.out.println("所有组合: " + combinations);
      System.out.println();
    }

    // 测试7：括号生成问题
    System.out.println("7. 括号生成问题测试:");

    for (int n = 1; n <= 4; n++) {
      System.out.printf("n=%d:%n", n);

      long startTime = System.nanoTime();
      List<String> parentheses = generateParenthesis(n);
      long time = System.nanoTime() - startTime;

      System.out.printf("括号组合数量: %d (%.2fμs)%n", parentheses.size(), time / 1000.0);
      System.out.printf("理论数量(卡特兰数): %d%n", catalan(n));
      System.out.println("所有组合: " + parentheses);
      System.out.println();
    }

    // 测试8：性能测试
    System.out.println("8. 性能测试:");

    System.out.println("N皇后性能测试:");
    for (int n = 8; n <= 12; n++) {
      System.out.printf("N=%d:%n", n);

      long startTime = System.currentTimeMillis();
      int count1 = totalNQueens(n);
      long time1 = System.currentTimeMillis() - startTime;

      startTime = System.currentTimeMillis();
      int count2 = totalNQueensBitwise(n);
      long time2 = System.currentTimeMillis() - startTime;

      System.out.printf("  普通方法: %d个解 (%dms)%n", count1, time1);
      System.out.printf("  位运算优化: %d个解 (%dms)%n", count2, time2);
      System.out.printf("  加速比: %.2fx%n", time1 == 0 ? 1.0 : (double) time1 / time2);
      System.out.println();
    }

    // 全排列性能测试
    System.out.println("全排列性能测试:");
    for (int n = 6; n <= 9; n++) {
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = i + 1;
      }

      System.out.printf("n=%d:%n", n);

      long startTime = System.currentTimeMillis();
      List<List<Integer>> permutations = permute(nums);
      long time = System.currentTimeMillis() - startTime;

      System.out.printf("  排列数量: %d (%dms)%n", permutations.size(), time);
      System.out.printf("  理论复杂度: %s%n", getTimeComplexity("permutation", n));
      System.out.println();
    }

    // 测试9：边界情况
    System.out.println("9. 边界情况测试:");

    System.out.println("N皇后边界情况:");
    System.out.println("N=0: " + totalNQueens(0));
    System.out.println("N=1: " + totalNQueens(1));

    System.out.println("\n全排列边界情况:");
    System.out.println("空数组: " + permute(new int[]{}));
    System.out.println("单元素: " + permute(new int[]{1}));

    System.out.println("\n组合边界情况:");
    System.out.println("C(1,1): " + combine(1, 1));
    System.out.println("C(2,0): " + combine(2, 0));

    System.out.println("\n子集边界情况:");
    System.out.println("空数组: " + subsets(new int[]{}));
    System.out.println("单元素: " + subsets(new int[]{1}));

    System.out.println("\n括号生成边界情况:");
    System.out.println("n=0: " + generateParenthesis(0));
    System.out.println("n=1: " + generateParenthesis(1));

    // 测试10：正确性验证
    System.out.println("\n10. 正确性验证:");

    // N皇后解的正确性
    int nQueensCorrect = 0;
    int nQueensTotal = 0;

    for (int n = 1; n <= 8; n++) {
      List<List<String>> solutions = solveNQueens(n);
      nQueensTotal += solutions.size();

      for (List<String> solution : solutions) {
        if (validateNQueensSolution(solution)) {
          nQueensCorrect++;
        }
      }
    }

    System.out.printf("N皇后解正确性: %d/%d 正确, 正确率: %.2f%%%n",
        nQueensCorrect, nQueensTotal, 100.0 * nQueensCorrect / nQueensTotal);

    // 全排列数量验证
    boolean permuteCountCorrect = true;
    for (int n = 1; n <= 7; n++) {
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = i + 1;
      }

      List<List<Integer>> permutations = permute(nums);
      int expected = factorial(n);

      if (permutations.size() != expected) {
        permuteCountCorrect = false;
        break;
      }
    }

    System.out.printf("全排列数量正确性: %s%n", permuteCountCorrect);

    // 组合数量验证
    boolean combineCountCorrect = true;
    for (int n = 1; n <= 10; n++) {
      for (int k = 0; k <= n; k++) {
        List<List<Integer>> combinations = combine(n, k);
        int expected = combination(n, k);

        if (combinations.size() != expected) {
          combineCountCorrect = false;
          break;
        }
      }
      if (!combineCountCorrect) {
        break;
      }
    }

    System.out.printf("组合数量正确性: %s%n", combineCountCorrect);

    // 子集数量验证
    boolean subsetCountCorrect = true;
    for (int n = 0; n <= 10; n++) {
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = i + 1;
      }

      List<List<Integer>> subsets = subsets(nums);
      int expected = 1 << n;

      if (subsets.size() != expected) {
        subsetCountCorrect = false;
        break;
      }
    }

    System.out.printf("子集数量正确性: %s%n", subsetCountCorrect);

    // 括号生成数量验证（卡特兰数）
    boolean parenthesesCountCorrect = true;
    for (int n = 0; n <= 8; n++) {
      List<String> parentheses = generateParenthesis(n);
      int expected = catalan(n);

      if (parentheses.size() != expected) {
        parenthesesCountCorrect = false;
        break;
      }
    }

    System.out.printf("括号生成数量正确性: %s%n", parenthesesCountCorrect);

    System.out.println("\n=== 回溯算法测试完成 ===");
  }

  /**
   * 计算阶乘
   *
   * @param n 输入数字
   * @return n的阶乘
   */
  private static int factorial(int n) {
    if (n <= 1) {
      return 1;
    }
    int result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  /**
   * 计算组合数C(n, k)
   *
   * @param n 总数
   * @param k 选择数
   * @return 组合数
   */
  private static int combination(int n, int k) {
    if (k > n || k < 0) {
      return 0;
    }
    if (k == 0 || k == n) {
      return 1;
    }

    k = Math.min(k, n - k); // 利用对称性

    long result = 1;
    for (int i = 0; i < k; i++) {
      result = result * (n - i) / (i + 1);
    }

    return (int) result;
  }

  /**
   * 计算第n个卡特兰数
   *
   * @param n 输入数字
   * @return 第n个卡特兰数
   */
  private static int catalan(int n) {
    if (n <= 1) {
      return 1;
    }

    long result = 1;
    for (int i = 0; i < n; i++) {
      result = result * (2 * n - i) / (i + 1);
    }

    return (int) (result / (n + 1));
  }
}