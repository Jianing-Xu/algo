package leetcode;

import java.util.*;

/**
 * LeetCode二分查找相关问题解答
 * 包含x的平方根等经典二分查找题目
 */
public class BinarySearchProblems {
    
    // ==================== Sqrt(x)（x的平方根）====================
    
    /**
     * LeetCode 69. x 的平方根
     * 给你一个非负整数 x ，计算并返回 x 的算术平方根
     * 由于返回类型是整数，结果只保留整数部分，小数部分将被舍去
     * 
     * 解题思路：二分查找
     * 1. 在 [0, x] 范围内查找平方根
     * 2. 对于每个中点 mid，检查 mid * mid 与 x 的关系
     * 3. 找到最大的整数，使得其平方不超过 x
     * 
     * 时间复杂度：O(log x)
     * 空间复杂度：O(1)
     * 
     * @param x 输入的非负整数
     * @return x的算术平方根（整数部分）
     */
    public static int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        
        long left = 1;
        long right = x / 2; // 优化：对于x>=2，平方根不会超过x/2
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            
            if (square == x) {
                return (int) mid;
            } else if (square < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return (int) right; // right是最大的满足square <= x的数
    }
    
    /**
     * 平方根的牛顿迭代法
     * 时间复杂度：O(log x)
     * 空间复杂度：O(1)
     * 
     * @param x 输入的非负整数
     * @return x的算术平方根（整数部分）
     */
    public static int mySqrtNewton(int x) {
        if (x < 2) {
            return x;
        }
        
        long r = x;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        
        return (int) r;
    }
    
    /**
     * 平方根的位运算解法
     * 时间复杂度：O(log x)
     * 空间复杂度：O(1)
     * 
     * @param x 输入的非负整数
     * @return x的算术平方根（整数部分）
     */
    public static int mySqrtBitwise(int x) {
        if (x < 2) {
            return x;
        }
        
        // 找到最高位的位置
        int left = 1;
        int right = 1;
        while (right <= x / right) {
            left = right;
            right <<= 1;
        }
        
        // 在 [left, right) 范围内二分查找
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return right;
    }
    
    // ==================== 平方根的扩展问题 ====================
    
    /**
     * 计算精确的平方根（保留指定小数位）
     * 
     * @param x 输入数字
     * @param precision 精度（小数位数）
     * @return 精确的平方根
     */
    public static double sqrtPrecise(double x, int precision) {
        if (x < 0) {
            throw new IllegalArgumentException("Cannot compute square root of negative number");
        }
        
        if (x == 0 || x == 1) {
            return x;
        }
        
        double left = 0;
        double right = x > 1 ? x : 1;
        double epsilon = Math.pow(10, -precision - 1);
        
        while (right - left > epsilon) {
            double mid = left + (right - left) / 2;
            double square = mid * mid;
            
            if (Math.abs(square - x) < epsilon) {
                return Math.round(mid * Math.pow(10, precision)) / Math.pow(10, precision);
            } else if (square < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return Math.round(left * Math.pow(10, precision)) / Math.pow(10, precision);
    }
    
    /**
     * 判断一个数是否为完全平方数
     * LeetCode 367. 有效的完全平方数
     * 
     * @param num 输入数字
     * @return 是否为完全平方数
     */
    public static boolean isPerfectSquare(int num) {
        if (num < 1) {
            return false;
        }
        
        if (num == 1) {
            return true;
        }
        
        long left = 1;
        long right = num / 2;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            
            if (square == num) {
                return true;
            } else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * 计算立方根
     * 
     * @param x 输入数字
     * @return x的立方根（整数部分）
     */
    public static int myCbrt(int x) {
        if (x == 0) return 0;
        
        boolean negative = x < 0;
        long absX = Math.abs((long) x);
        
        long left = 0;
        long right = absX;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long cube = mid * mid * mid;
            
            if (cube == absX) {
                return negative ? -(int) mid : (int) mid;
            } else if (cube < absX) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return negative ? -(int) right : (int) right;
    }
    
    // ==================== 其他二分查找问题 ====================
    
    /**
     * 搜索插入位置
     * LeetCode 35. 搜索插入位置
     * 
     * @param nums 有序数组
     * @param target 目标值
     * @return 插入位置
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    /**
     * 寻找峰值
     * LeetCode 162. 寻找峰值
     * 
     * @param nums 输入数组
     * @return 峰值的索引
     */
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                right = mid; // 峰值在左侧（包括mid）
            } else {
                left = mid + 1; // 峰值在右侧
            }
        }
        
        return left;
    }
    
    /**
     * 寻找旋转排序数组中的最小值
     * LeetCode 153. 寻找旋转排序数组中的最小值
     * 
     * @param nums 旋转排序数组
     * @return 最小值
     */
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1; // 最小值在右侧
            } else {
                right = mid; // 最小值在左侧（包括mid）
            }
        }
        
        return nums[left];
    }
    
    /**
     * 在旋转排序数组中搜索
     * LeetCode 33. 搜索旋转排序数组
     * 
     * @param nums 旋转排序数组
     * @param target 目标值
     * @return 目标值的索引，不存在返回-1
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // 判断哪一侧是有序的
            if (nums[left] <= nums[mid]) {
                // 左侧有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右侧有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    // ==================== 辅助方法 ====================
    
    /**
     * 生成随机数组用于测试
     * 
     * @param size 数组大小
     * @param min 最小值
     * @param max 最大值
     * @param sorted 是否排序
     * @return 随机数组
     */
    public static int[] generateRandomArray(int size, int min, int max, boolean sorted) {
        Random random = new Random();
        int[] array = new int[size];
        
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        
        if (sorted) {
            Arrays.sort(array);
        }
        
        return array;
    }
    
    /**
     * 创建旋转排序数组
     * 
     * @param sortedArray 有序数组
     * @param rotateIndex 旋转位置
     * @return 旋转后的数组
     */
    public static int[] createRotatedArray(int[] sortedArray, int rotateIndex) {
        int n = sortedArray.length;
        rotateIndex = rotateIndex % n;
        
        int[] rotated = new int[n];
        for (int i = 0; i < n; i++) {
            rotated[i] = sortedArray[(i + rotateIndex) % n];
        }
        
        return rotated;
    }
    
    /**
     * 验证平方根结果的正确性
     * 
     * @param x 输入值
     * @param result 计算结果
     * @return 是否正确
     */
    public static boolean verifySqrt(int x, int result) {
        long square = (long) result * result;
        long nextSquare = (long) (result + 1) * (result + 1);
        
        return square <= x && nextSquare > x;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== LeetCode 二分查找问题测试 ===\n");
        
        // 测试1：x的平方根
        System.out.println("1. x的平方根测试:");
        
        int[] sqrtTestCases = {0, 1, 4, 8, 9, 16, 25, 100, 2147395600};
        
        for (int x : sqrtTestCases) {
            System.out.printf("x = %d:%n", x);
            
            long startTime = System.nanoTime();
            int result1 = mySqrt(x);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int result2 = mySqrtNewton(x);
            long time2 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int result3 = mySqrtBitwise(x);
            long time3 = System.nanoTime() - startTime;
            
            // 使用Math.sqrt作为参考
            int reference = (int) Math.sqrt(x);
            
            System.out.printf("  二分查找: %d (%.2fμs)%n", result1, time1 / 1000.0);
            System.out.printf("  牛顿迭代: %d (%.2fμs)%n", result2, time2 / 1000.0);
            System.out.printf("  位运算: %d (%.2fμs)%n", result3, time3 / 1000.0);
            System.out.printf("  参考值: %d%n", reference);
            
            boolean correct1 = verifySqrt(x, result1);
            boolean correct2 = verifySqrt(x, result2);
            boolean correct3 = verifySqrt(x, result3);
            boolean consistent = result1 == result2 && result2 == result3;
            
            System.out.printf("  正确性: 二分=%s, 牛顿=%s, 位运算=%s, 一致性=%s%n", 
                            correct1, correct2, correct3, consistent);
            System.out.println();
        }
        
        // 测试2：精确平方根
        System.out.println("2. 精确平方根测试:");
        
        double[] preciseTestCases = {2.0, 3.0, 5.0, 10.0, 0.25, 0.5};
        
        for (double x : preciseTestCases) {
            double result1 = sqrtPrecise(x, 6);
            double result2 = Math.sqrt(x);
            
            System.out.printf("sqrt(%.2f): 自实现=%.6f, Math.sqrt=%.6f, 差值=%.8f%n", 
                            x, result1, result2, Math.abs(result1 - result2));
        }
        
        // 测试3：完全平方数
        System.out.println("\n3. 完全平方数测试:");
        
        int[] perfectSquareTests = {1, 4, 9, 16, 25, 14, 15, 17, 100, 101};
        
        for (int num : perfectSquareTests) {
            boolean result = isPerfectSquare(num);
            int sqrt = mySqrt(num);
            boolean expected = sqrt * sqrt == num;
            
            System.out.printf("%d: 是完全平方数=%s, 预期=%s, 一致性=%s%n", 
                            num, result, expected, result == expected);
        }
        
        // 测试4：立方根
        System.out.println("\n4. 立方根测试:");
        
        int[] cbrtTestCases = {0, 1, 8, 27, 64, 125, -8, -27, 10, 26};
        
        for (int x : cbrtTestCases) {
            int result = myCbrt(x);
            double reference = Math.cbrt(x);
            
            System.out.printf("cbrt(%d) = %d, 参考值 = %.2f%n", x, result, reference);
        }
        
        // 测试5：其他二分查找问题
        System.out.println("\n5. 其他二分查找问题测试:");
        
        // 搜索插入位置
        int[] sortedArray = {1, 3, 5, 6};
        int[] targets = {5, 2, 7, 0};
        
        System.out.println("搜索插入位置:");
        System.out.println("数组: " + Arrays.toString(sortedArray));
        
        for (int target : targets) {
            int position = searchInsert(sortedArray, target);
            System.out.printf("目标=%d, 插入位置=%d%n", target, position);
        }
        
        // 寻找峰值
        int[][] peakArrays = {
            {1, 2, 3, 1},
            {1, 2, 1, 3, 5, 6, 4},
            {1},
            {1, 2}
        };
        
        System.out.println("\n寻找峰值:");
        for (int[] array : peakArrays) {
            int peak = findPeakElement(array);
            System.out.printf("数组: %s, 峰值索引: %d, 峰值: %d%n", 
                            Arrays.toString(array), peak, array[peak]);
        }
        
        // 旋转数组最小值
        int[][] rotatedArrays = {
            {3, 4, 5, 1, 2},
            {4, 5, 6, 7, 0, 1, 2},
            {11, 13, 15, 17},
            {2, 1}
        };
        
        System.out.println("\n旋转数组最小值:");
        for (int[] array : rotatedArrays) {
            int min = findMin(array);
            System.out.printf("数组: %s, 最小值: %d%n", Arrays.toString(array), min);
        }
        
        // 旋转数组搜索
        System.out.println("\n旋转数组搜索:");
        int[] searchArray = {4, 5, 6, 7, 0, 1, 2};
        int[] searchTargets = {0, 3, 4, 7, 1};
        
        System.out.println("数组: " + Arrays.toString(searchArray));
        for (int target : searchTargets) {
            int index = search(searchArray, target);
            System.out.printf("目标=%d, 索引=%d%n", target, index);
        }
        
        // 测试6：性能测试
        System.out.println("\n6. 性能测试:");
        
        System.out.println("平方根算法性能对比:");
        int[] perfTestCases = {1000000, 10000000, 100000000, 1000000000};
        
        for (int x : perfTestCases) {
            System.out.printf("x = %d:%n", x);
            
            long startTime = System.currentTimeMillis();
            int result1 = mySqrt(x);
            long time1 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            int result2 = mySqrtNewton(x);
            long time2 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            int result3 = mySqrtBitwise(x);
            long time3 = System.currentTimeMillis() - startTime;
            
            System.out.printf("  二分查找: %dms, 牛顿迭代: %dms, 位运算: %dms%n", 
                            time1, time2, time3);
            System.out.printf("  结果一致性: %s%n", result1 == result2 && result2 == result3);
        }
        
        // 大数组二分查找性能测试
        System.out.println("\n大数组二分查找性能测试:");
        int[] arraySizes = {10000, 100000, 1000000};
        
        for (int size : arraySizes) {
            int[] largeArray = generateRandomArray(size, 1, size * 10, true);
            Random random = new Random();
            
            long totalTime = 0;
            int testCount = 1000;
            
            for (int i = 0; i < testCount; i++) {
                int target = random.nextInt(size * 10) + 1;
                
                long startTime = System.nanoTime();
                searchInsert(largeArray, target);
                totalTime += System.nanoTime() - startTime;
            }
            
            double avgTime = totalTime / (double) testCount / 1000.0;
            System.out.printf("数组大小=%d, 平均查找时间=%.2fμs%n", size, avgTime);
        }
        
        // 测试7：边界情况
        System.out.println("\n7. 边界情况测试:");
        
        System.out.println("平方根边界情况:");
        System.out.println("sqrt(0) = " + mySqrt(0));
        System.out.println("sqrt(1) = " + mySqrt(1));
        System.out.println("sqrt(2147395600) = " + mySqrt(2147395600)); // 接近Integer.MAX_VALUE
        
        System.out.println("\n完全平方数边界情况:");
        System.out.println("isPerfectSquare(1) = " + isPerfectSquare(1));
        System.out.println("isPerfectSquare(0) = " + isPerfectSquare(0));
        System.out.println("isPerfectSquare(-1) = " + isPerfectSquare(-1));
        
        System.out.println("\n立方根边界情况:");
        System.out.println("cbrt(0) = " + myCbrt(0));
        System.out.println("cbrt(1) = " + myCbrt(1));
        System.out.println("cbrt(-1) = " + myCbrt(-1));
        
        System.out.println("\n搜索插入位置边界情况:");
        int[] emptyArray = {};
        int[] singleArray = {1};
        
        System.out.println("空数组插入5: " + searchInsert(emptyArray, 5));
        System.out.println("单元素数组[1]插入0: " + searchInsert(singleArray, 0));
        System.out.println("单元素数组[1]插入1: " + searchInsert(singleArray, 1));
        System.out.println("单元素数组[1]插入2: " + searchInsert(singleArray, 2));
        
        // 测试8：正确性验证
        System.out.println("\n8. 正确性验证:");
        
        // 验证大量随机数的平方根计算
        Random random = new Random(42);
        int correctCount = 0;
        int totalTests = 10000;
        
        for (int i = 0; i < totalTests; i++) {
            int x = random.nextInt(1000000);
            int result = mySqrt(x);
            
            if (verifySqrt(x, result)) {
                correctCount++;
            }
        }
        
        System.out.printf("随机测试: %d/%d 正确, 正确率: %.2f%%%n", 
                        correctCount, totalTests, 100.0 * correctCount / totalTests);
        
        // 验证完全平方数检测
        int perfectSquareCorrect = 0;
        int perfectSquareTotal = 1000;
        
        for (int i = 1; i <= perfectSquareTotal; i++) {
            boolean isPerfect = isPerfectSquare(i);
            int sqrt = mySqrt(i);
            boolean expected = sqrt * sqrt == i;
            
            if (isPerfect == expected) {
                perfectSquareCorrect++;
            }
        }
        
        System.out.printf("完全平方数检测: %d/%d 正确, 正确率: %.2f%%%n", 
                        perfectSquareCorrect, perfectSquareTotal, 
                        100.0 * perfectSquareCorrect / perfectSquareTotal);
    }
}