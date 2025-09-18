package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 递归算法示例 包含斐波那契数列、阶乘、全排列等经典递归问题
 */
public class RecursionExamples {

  /**
   * 计算斐波那契数列第n项（递归版本） f(n) = f(n-1) + f(n-2), f(0) = 0, f(1) = 1 时间复杂度：O(2^n)，空间复杂度：O(n)
   *
   * @param n 第n项
   * @return 斐波那契数列第n项的值
   */
  public static long fibonacciRecursive(int n) {
    // 基础情况
    if (n <= 1) {
      return n;
    }

    // 递归情况
    return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
  }

  /**
   * 计算斐波那契数列第n项（记忆化递归版本） 使用备忘录避免重复计算，时间复杂度：O(n)，空间复杂度：O(n)
   *
   * @param n 第n项
   * @return 斐波那契数列第n项的值
   */
  public static long fibonacciMemo(int n) {
    Map<Integer, Long> memo = new HashMap<>();
    return fibonacciMemoHelper(n, memo);
  }

  /**
   * 斐波那契记忆化递归的辅助方法
   */
  private static long fibonacciMemoHelper(int n, Map<Integer, Long> memo) {
    // 基础情况
    if (n <= 1) {
      return n;
    }

    // 检查备忘录
    if (memo.containsKey(n)) {
      return memo.get(n);
    }

    // 计算并存储结果
    long result = fibonacciMemoHelper(n - 1, memo) + fibonacciMemoHelper(n - 2, memo);
    memo.put(n, result);
    return result;
  }

  /**
   * 计算斐波那契数列第n项（迭代版本） 时间复杂度：O(n)，空间复杂度：O(1)
   *
   * @param n 第n项
   * @return 斐波那契数列第n项的值
   */
  public static long fibonacciIterative(int n) {
    if (n <= 1) {
      return n;
    }

    long prev2 = 0;  // f(0)
    long prev1 = 1;  // f(1)
    long current = 0;

    for (int i = 2; i <= n; i++) {
      current = prev1 + prev2;
      prev2 = prev1;
      prev1 = current;
    }

    return current;
  }

  /**
   * 计算阶乘（递归版本） n! = n * (n-1)!, 0! = 1 时间复杂度：O(n)，空间复杂度：O(n)
   *
   * @param n 非负整数
   * @return n的阶乘
   * @throws IllegalArgumentException 如果n为负数
   */
  public static long factorialRecursive(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("阶乘的参数不能为负数");
    }

    // 基础情况
    if (n <= 1) {
      return 1;
    }

    // 递归情况
    return n * factorialRecursive(n - 1);
  }

  /**
   * 计算阶乘（迭代版本） 时间复杂度：O(n)，空间复杂度：O(1)
   *
   * @param n 非负整数
   * @return n的阶乘
   * @throws IllegalArgumentException 如果n为负数
   */
  public static long factorialIterative(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("阶乘的参数不能为负数");
    }

    long result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  /**
   * 生成数组的所有排列（递归版本） 时间复杂度：O(n! * n)，空间复杂度：O(n)
   *
   * @param nums 要排列的数组
   * @return 所有排列的列表
   */
  public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> current = new ArrayList<>();
    boolean[] used = new boolean[nums.length];

    permuteHelper(nums, used, current, result);
    return result;
  }

  /**
   * 全排列的递归辅助方法
   */
  private static void permuteHelper(int[] nums, boolean[] used,
      List<Integer> current, List<List<Integer>> result) {
    // 基础情况：当前排列已完成
    if (current.size() == nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    // 递归情况：尝试每个未使用的元素
    for (int i = 0; i < nums.length; i++) {
      if (!used[i]) {
        // 选择
        current.add(nums[i]);
        used[i] = true;

        // 递归
        permuteHelper(nums, used, current, result);

        // 撤销选择（回溯）
        current.remove(current.size() - 1);
        used[i] = false;
      }
    }
  }

  /**
   * 生成字符串的所有排列
   *
   * @param str 要排列的字符串
   * @return 所有排列的列表
   */
  public static List<String> permuteString(String str) {
    List<String> result = new ArrayList<>();
    char[] chars = str.toCharArray();
    permuteStringHelper(chars, 0, result);
    return result;
  }

  /**
   * 字符串全排列的递归辅助方法
   */
  private static void permuteStringHelper(char[] chars, int start, List<String> result) {
    // 基础情况：已经处理完所有位置
    if (start == chars.length) {
      result.add(new String(chars));
      return;
    }

    // 递归情况：尝试将每个字符放在当前位置
    for (int i = start; i < chars.length; i++) {
      // 交换字符
      swap(chars, start, i);

      // 递归处理下一个位置
      permuteStringHelper(chars, start + 1, result);

      // 撤销交换（回溯）
      swap(chars, start, i);
    }
  }

  /**
   * 交换数组中两个位置的字符
   */
  private static void swap(char[] chars, int i, int j) {
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
  }

  /**
   * 计算x的n次幂（递归版本） 使用分治法优化，时间复杂度：O(log n)
   *
   * @param x 底数
   * @param n 指数（非负整数）
   * @return x的n次幂
   */
  public static double power(double x, int n) {
    if (n == 0) {
      return 1.0;
    }

    if (n < 0) {
      return 1.0 / power(x, -n);
    }

    // 分治：x^n = x^(n/2) * x^(n/2) * x^(n%2)
    double half = power(x, n / 2);
    if (n % 2 == 0) {
      return half * half;
    } else {
      return half * half * x;
    }
  }

  /**
   * 汉诺塔问题 将n个盘子从源柱子移动到目标柱子，使用辅助柱子
   *
   * @param n         盘子数量
   * @param source    源柱子
   * @param target    目标柱子
   * @param auxiliary 辅助柱子
   */
  public static void hanoi(int n, String source, String target, String auxiliary) {
    if (n == 1) {
      System.out.println("将盘子从 " + source + " 移动到 " + target);
      return;
    }

    // 将前n-1个盘子从源柱子移动到辅助柱子
    hanoi(n - 1, source, auxiliary, target);

    // 将最大的盘子从源柱子移动到目标柱子
    System.out.println("将盘子从 " + source + " 移动到 " + target);

    // 将n-1个盘子从辅助柱子移动到目标柱子
    hanoi(n - 1, auxiliary, target, source);
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 递归算法测试 ===\n");

    // 测试斐波那契数列
    System.out.println("1. 斐波那契数列测试:");
    int n = 10;
    System.out.println("递归版本 F(" + n + ") = " + fibonacciRecursive(n));
    System.out.println("记忆化版本 F(" + n + ") = " + fibonacciMemo(n));
    System.out.println("迭代版本 F(" + n + ") = " + fibonacciIterative(n));

    // 性能比较
    System.out.println("\n性能比较 (F(35)):");
    long start = System.currentTimeMillis();
    long result1 = fibonacciMemo(35);
    long time1 = System.currentTimeMillis() - start;
    System.out.println("记忆化版本: " + result1 + " (耗时: " + time1 + "ms)");

    start = System.currentTimeMillis();
    long result2 = fibonacciIterative(35);
    long time2 = System.currentTimeMillis() - start;
    System.out.println("迭代版本: " + result2 + " (耗时: " + time2 + "ms)");

    // 测试阶乘
    System.out.println("\n2. 阶乘测试:");
    int fact_n = 10;
    System.out.println("递归版本 " + fact_n + "! = " + factorialRecursive(fact_n));
    System.out.println("迭代版本 " + fact_n + "! = " + factorialIterative(fact_n));

    // 测试全排列
    System.out.println("\n3. 全排列测试:");
    int[] nums = {1, 2, 3};
    System.out.println("数组 " + Arrays.toString(nums) + " 的所有排列:");
    List<List<Integer>> permutations = permute(nums);
    for (List<Integer> perm : permutations) {
      System.out.println(perm);
    }

    // 测试字符串排列
    System.out.println("\n字符串 \"ABC\" 的所有排列:");
    List<String> stringPerms = permuteString("ABC");
    for (String perm : stringPerms) {
      System.out.println(perm);
    }

    // 测试幂运算
    System.out.println("\n4. 幂运算测试:");
    double x = 2.0;
    int pow_n = 10;
    System.out.println(x + "^" + pow_n + " = " + power(x, pow_n));
    System.out.println(x + "^" + (-pow_n) + " = " + power(x, -pow_n));

    // 测试汉诺塔
    System.out.println("\n5. 汉诺塔问题 (3个盘子):");
    hanoi(3, "A", "C", "B");
  }
}