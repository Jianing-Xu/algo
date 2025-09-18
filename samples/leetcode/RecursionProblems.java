package leetcode;

import java.util.*;

/**
 * LeetCode递归相关问题解答
 * 包含爬楼梯等经典递归题目
 */
public class RecursionProblems {

    // ==================== Climbing Stairs（爬楼梯）====================

    /**
     * LeetCode 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 解题思路：
     * 这是一个经典的斐波那契数列问题
     * f(n) = f(n-1) + f(n-2)
     * 到达第n阶的方法数 = 到达第n-1阶的方法数 + 到达第n-2阶的方法数
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n 楼梯阶数
     * @return 爬楼梯的方法数
     */
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1; // f(1)
        int prev1 = 2; // f(2)
        int current = 0;

        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    /**
     * 爬楼梯的递归解法（会超时，仅用于理解）
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(n)
     *
     * @param n 楼梯阶数
     * @return 爬楼梯的方法数
     */
    public static int climbStairsRecursive(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
    }

    /**
     * 爬楼梯的记忆化递归解法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param n 楼梯阶数
     * @return 爬楼梯的方法数
     */
    public static int climbStairsMemo(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return climbStairsMemoHelper(n, memo);
    }

    private static int climbStairsMemoHelper(int n, Map<Integer, Integer> memo) {
        if (n <= 2) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = climbStairsMemoHelper(n - 1, memo) + climbStairsMemoHelper(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    /**
     * 爬楼梯的动态规划解法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param n 楼梯阶数
     * @return 爬楼梯的方法数
     */
    public static int climbStairsDP(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * 爬楼梯的矩阵快速幂解法
     * 时间复杂度：O(log n)
     * 空间复杂度：O(1)
     *
     * @param n 楼梯阶数
     * @return 爬楼梯的方法数
     */
    public static int climbStairsMatrix(int n) {
        if (n <= 2) {
            return n;
        }

        // 转移矩阵 [[1, 1], [1, 0]]
        long[][] base = {{1, 1}, {1, 0}};
        long[][] result = matrixPower(base, n);

        return (int) result[0][0];
    }

    /**
     * 矩阵快速幂
     *
     * @param matrix 基础矩阵
     * @param n      幂次
     * @return matrix^n
     */
    private static long[][] matrixPower(long[][] matrix, int n) {
        long[][] result = {{1, 0}, {0, 1}}; // 单位矩阵
        long[][] base = {{matrix[0][0], matrix[0][1]}, {matrix[1][0], matrix[1][1]}};

        while (n > 0) {
            if (n % 2 == 1) {
                result = multiplyMatrix(result, base);
            }
            base = multiplyMatrix(base, base);
            n /= 2;
        }

        return result;
    }

    /**
     * 矩阵乘法
     *
     * @param a 矩阵A
     * @param b 矩阵B
     * @return A * B
     */
    private static long[][] multiplyMatrix(long[][] a, long[][] b) {
        long[][] result = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    // ==================== 爬楼梯的变体问题 ====================

    /**
     * 爬楼梯变体：每次可以爬1、2或3个台阶
     *
     * @param n 楼梯阶数
     * @return 爬楼梯的方法数
     */
    public static int climbStairsThreeSteps(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        int prev3 = 1; // f(1)
        int prev2 = 2; // f(2)
        int prev1 = 4; // f(3)
        int current = 0;

        for (int i = 4; i <= n; i++) {
            current = prev1 + prev2 + prev3;
            prev3 = prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    /**
     * 爬楼梯变体：可以爬任意步数（给定步数数组）
     *
     * @param n     楼梯阶数
     * @param steps 允许的步数数组
     * @return 爬楼梯的方法数
     */
    public static int climbStairsVariableSteps(int n, int[] steps) {
        if (n <= 0) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1; // 0阶有1种方法（不爬）

        for (int i = 1; i <= n; i++) {
            for (int step : steps) {
                if (i >= step) {
                    dp[i] += dp[i - step];
                }
            }
        }

        return dp[n];
    }

    /**
     * 爬楼梯变体：带成本的爬楼梯
     * LeetCode 746. 使用最小花费爬楼梯
     *
     * @param cost 每一阶的成本数组
     * @return 到达楼顶的最小成本
     */
    public static int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }

        int n = cost.length;
        if (n == 1) return cost[0];
        if (n == 2) return Math.min(cost[0], cost[1]);

        int prev2 = cost[0];
        int prev1 = cost[1];

        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = current;
        }

        // 可以从倒数第一阶或倒数第二阶到达楼顶
        return Math.min(prev1, prev2);
    }

    // ==================== 其他经典递归问题 ====================

    /**
     * 斐波那契数列（标准实现）
     *
     * @param n 第n项
     * @return 斐波那契数列第n项的值
     */
    public static int fibonacci(int n) {
        if (n <= 1) return n;

        int prev2 = 0;
        int prev1 = 1;
        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    /**
     * 阶乘计算（递归）
     *
     * @param n 输入数字
     * @return n的阶乘
     */
    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    /**
     * 阶乘计算（迭代）
     *
     * @param n 输入数字
     * @return n的阶乘
     */
    public static long factorialIterative(int n) {
        if (n <= 1) return 1;

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * 汉诺塔问题
     *
     * @param n    盘子数量
     * @param from 起始柱子
     * @param to   目标柱子
     * @param aux  辅助柱子
     * @return 移动步骤列表
     */
    public static List<String> hanoi(int n, char from, char to, char aux) {
        List<String> steps = new ArrayList<>();
        hanoiHelper(n, from, to, aux, steps);
        return steps;
    }

    private static void hanoiHelper(int n, char from, char to, char aux, List<String> steps) {
        if (n == 1) {
            steps.add("Move disk 1 from " + from + " to " + to);
            return;
        }

        // 将前n-1个盘子从from移动到aux
        hanoiHelper(n - 1, from, aux, to, steps);

        // 将第n个盘子从from移动到to
        steps.add("Move disk " + n + " from " + from + " to " + to);

        // 将前n-1个盘子从aux移动到to
        hanoiHelper(n - 1, aux, to, from, steps);
    }

    /**
     * 计算汉诺塔最少移动次数
     *
     * @param n 盘子数量
     * @return 最少移动次数
     */
    public static int hanoiMoves(int n) {
        if (n <= 0) return 0;
        return (1 << n) - 1; // 2^n - 1
    }

    // ==================== 辅助方法 ====================

    /**
     * 测试递归算法的性能
     *
     * @param n         测试参数
     * @param algorithm 算法名称
     * @param function  算法函数
     * @return 执行时间（纳秒）
     */
    public static long testPerformance(int n, String algorithm, java.util.function.IntFunction<Integer> function) {
        long startTime = System.nanoTime();
        int result = function.apply(n);
        long endTime = System.nanoTime();

        System.out.printf("%s(n=%d) = %d, 耗时: %.2fμs%n",
                algorithm, n, result, (endTime - startTime) / 1000.0);

        return endTime - startTime;
    }

    /**
     * 验证不同算法结果的一致性
     *
     * @param n 测试参数
     * @return 是否一致
     */
    public static boolean verifyConsistency(int n) {
        if (n > 40) {
            // 递归版本会很慢，只测试其他版本
            int result1 = climbStairs(n);
            int result2 = climbStairsMemo(n);
            int result3 = climbStairsDP(n);
            int result4 = climbStairsMatrix(n);

            return result1 == result2 && result2 == result3 && result3 == result4;
        } else {
            int result1 = climbStairs(n);
            int result2 = climbStairsRecursive(n);
            int result3 = climbStairsMemo(n);
            int result4 = climbStairsDP(n);
            int result5 = climbStairsMatrix(n);

            return result1 == result2 && result2 == result3 && result3 == result4 && result4 == result5;
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== LeetCode 递归问题测试 ===\n");

        // 测试1：爬楼梯基本测试
        System.out.println("1. 爬楼梯基本测试:");

        int[] testCases = {1, 2, 3, 4, 5, 10, 20, 30};

        for (int n : testCases) {
            System.out.printf("n=%d: ", n);

            long time1 = testPerformance(n, "迭代", RecursionProblems::climbStairs);

            if (n <= 35) { // 递归版本太慢，只测试小数据
                long time2 = testPerformance(n, "递归", RecursionProblems::climbStairsRecursive);
            }

            long time3 = testPerformance(n, "记忆化", RecursionProblems::climbStairsMemo);
            long time4 = testPerformance(n, "DP", RecursionProblems::climbStairsDP);
            long time5 = testPerformance(n, "矩阵", RecursionProblems::climbStairsMatrix);

            System.out.println("结果一致性: " + verifyConsistency(n));
            System.out.println();
        }

        // 测试2：爬楼梯变体
        System.out.println("2. 爬楼梯变体测试:");

        int n = 10;
        System.out.println("n = " + n);

        int result1 = climbStairs(n);
        int result2 = climbStairsThreeSteps(n);
        int result3 = climbStairsVariableSteps(n, new int[]{1, 2});
        int result4 = climbStairsVariableSteps(n, new int[]{1, 2, 3});
        int result5 = climbStairsVariableSteps(n, new int[]{1, 3, 5});

        System.out.println("标准爬楼梯(1,2步): " + result1);
        System.out.println("三步爬楼梯(1,2,3步): " + result2);
        System.out.println("可变步数(1,2步): " + result3);
        System.out.println("可变步数(1,2,3步): " + result4);
        System.out.println("可变步数(1,3,5步): " + result5);

        System.out.println("验证: 标准 == 可变(1,2): " + (result1 == result3));
        System.out.println("验证: 三步 == 可变(1,2,3): " + (result2 == result4));

        // 测试3：带成本的爬楼梯
        System.out.println("\n3. 带成本的爬楼梯测试:");

        int[][] costCases = {
                {10, 15, 20},
                {1, 100, 1, 1, 1, 100, 1, 1, 100, 1},
                {0, 0, 0, 0},
                {1, 2, 3, 4, 5}
        };

        for (int[] cost : costCases) {
            System.out.println("成本数组: " + Arrays.toString(cost));

            long startTime = System.nanoTime();
            int minCost = minCostClimbingStairs(cost);
            long time = System.nanoTime() - startTime;

            System.out.printf("最小成本: %d, 耗时: %.2fμs%n", minCost, time / 1000.0);
        }

        // 测试4：其他递归问题
        System.out.println("\n4. 其他递归问题测试:");

        // 斐波那契数列
        System.out.println("斐波那契数列:");
        for (int i = 0; i <= 10; i++) {
            System.out.printf("F(%d) = %d ", i, fibonacci(i));
        }
        System.out.println();

        // 阶乘
        System.out.println("\n阶乘计算:");
        for (int i = 0; i <= 10; i++) {
            long fact1 = factorial(i);
            long fact2 = factorialIterative(i);
            System.out.printf("%d! = %d (递归) = %d (迭代), 一致性: %s%n",
                    i, fact1, fact2, fact1 == fact2);
        }

        // 汉诺塔
        System.out.println("\n汉诺塔问题:");
        for (int disks = 1; disks <= 4; disks++) {
            List<String> steps = hanoi(disks, 'A', 'C', 'B');
            int expectedMoves = hanoiMoves(disks);

            System.out.printf("%d个盘子: 预期步数=%d, 实际步数=%d%n",
                    disks, expectedMoves, steps.size());

            if (disks <= 3) {
                System.out.println("移动步骤:");
                for (String step : steps) {
                    System.out.println("  " + step);
                }
            }
            System.out.println();
        }

        // 测试5：性能对比
        System.out.println("5. 性能对比测试:");

        System.out.println("爬楼梯算法性能对比:");
        int[] perfTestCases = {20, 30, 40, 50};

        for (int testN : perfTestCases) {
            System.out.printf("n=%d:%n", testN);

            long startTime = System.currentTimeMillis();
            int result = climbStairs(testN);
            long iterativeTime = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            climbStairsMemo(testN);
            long memoTime = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            climbStairsDP(testN);
            long dpTime = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            climbStairsMatrix(testN);
            long matrixTime = System.currentTimeMillis() - startTime;

            System.out.printf("  结果: %d%n", result);
            System.out.printf("  迭代: %dms, 记忆化: %dms, DP: %dms, 矩阵: %dms%n",
                    iterativeTime, memoTime, dpTime, matrixTime);
        }

        // 大数测试
        System.out.println("\n大数测试:");
        int[] bigTestCases = {100, 500, 1000};

        for (int bigN : bigTestCases) {
            System.out.printf("n=%d:%n", bigN);

            long startTime = System.currentTimeMillis();
            int ret1 = climbStairs(bigN);
            long time1 = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            int ret2 = climbStairsMatrix(bigN);
            long time2 = System.currentTimeMillis() - startTime;

            System.out.printf("  迭代: %dms, 矩阵快速幂: %dms, 加速比: %.2fx%n",
                    time1, time2, time1 == 0 ? 1.0 : (double) time1 / time2);
            System.out.printf("  结果一致性: %s%n", ret1 == ret2);
        }

        // 测试6：边界情况
        System.out.println("\n6. 边界情况测试:");

        System.out.println("爬楼梯边界情况:");
        System.out.println("n=0: " + climbStairs(0));
        System.out.println("n=1: " + climbStairs(1));
        System.out.println("n=2: " + climbStairs(2));

        System.out.println("\n带成本爬楼梯边界情况:");
        System.out.println("空数组: " + minCostClimbingStairs(new int[]{}));
        System.out.println("单元素: " + minCostClimbingStairs(new int[]{10}));
        System.out.println("两元素: " + minCostClimbingStairs(new int[]{10, 15}));

        System.out.println("\n斐波那契边界情况:");
        System.out.println("F(0): " + fibonacci(0));
        System.out.println("F(1): " + fibonacci(1));

        System.out.println("\n阶乘边界情况:");
        System.out.println("0!: " + factorial(0));
        System.out.println("1!: " + factorial(1));

        System.out.println("\n汉诺塔边界情况:");
        System.out.println("0个盘子步数: " + hanoiMoves(0));
        System.out.println("1个盘子步数: " + hanoiMoves(1));
        List<String> oneDiscSteps = hanoi(1, 'A', 'C', 'B');
        System.out.println("1个盘子移动: " + oneDiscSteps);
    }
}