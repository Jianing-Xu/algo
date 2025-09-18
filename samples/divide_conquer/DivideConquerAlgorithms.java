package divide_conquer;

import java.util.*;

/**
 * 分治算法实现集合
 * 包含归并排序、快速排序、逆序对计算、最大子数组和等经典分治算法
 */
public class DivideConquerAlgorithms {
    
    // ==================== 逆序对计算 ====================
    
    /**
     * 逆序对计算器
     * 使用分治算法计算数组中的逆序对个数
     * 逆序对：对于数组中的两个元素 a[i] 和 a[j]，如果 i < j 但 a[i] > a[j]，则称为一个逆序对
     */
    public static class InversionCounter {
        
        /**
         * 计算数组中的逆序对个数
         * 时间复杂度：O(n log n)，空间复杂度：O(n)
         * 
         * @param array 输入数组
         * @return 逆序对个数
         */
        public static long countInversions(int[] array) {
            if (array == null || array.length <= 1) {
                return 0;
            }
            
            int[] temp = new int[array.length];
            return mergeSort(array, temp, 0, array.length - 1);
        }
        
        /**
         * 归并排序的同时计算逆序对
         * 
         * @param array 原数组
         * @param temp 临时数组
         * @param left 左边界
         * @param right 右边界
         * @return 逆序对个数
         */
        private static long mergeSort(int[] array, int[] temp, int left, int right) {
            long invCount = 0;
            
            if (left < right) {
                int mid = left + (right - left) / 2;
                
                // 递归计算左半部分的逆序对
                invCount += mergeSort(array, temp, left, mid);
                
                // 递归计算右半部分的逆序对
                invCount += mergeSort(array, temp, mid + 1, right);
                
                // 合并两部分并计算跨越中点的逆序对
                invCount += merge(array, temp, left, mid, right);
            }
            
            return invCount;
        }
        
        /**
         * 合并两个有序数组并计算逆序对
         * 
         * @param array 原数组
         * @param temp 临时数组
         * @param left 左边界
         * @param mid 中点
         * @param right 右边界
         * @return 跨越中点的逆序对个数
         */
        private static long merge(int[] array, int[] temp, int left, int mid, int right) {
            // 复制到临时数组
            for (int i = left; i <= right; i++) {
                temp[i] = array[i];
            }
            
            int i = left;    // 左半部分指针
            int j = mid + 1; // 右半部分指针
            int k = left;    // 合并后数组指针
            long invCount = 0;
            
            // 合并过程
            while (i <= mid && j <= right) {
                if (temp[i] <= temp[j]) {
                    array[k++] = temp[i++];
                } else {
                    // temp[i] > temp[j]，形成逆序对
                    // 左半部分从i到mid的所有元素都与temp[j]形成逆序对
                    array[k++] = temp[j++];
                    invCount += (mid - i + 1);
                }
            }
            
            // 复制剩余元素
            while (i <= mid) {
                array[k++] = temp[i++];
            }
            while (j <= right) {
                array[k++] = temp[j++];
            }
            
            return invCount;
        }
        
        /**
         * 暴力方法计算逆序对（用于验证结果）
         * 时间复杂度：O(n²)
         * 
         * @param array 输入数组
         * @return 逆序对个数
         */
        public static long countInversionsNaive(int[] array) {
            long count = 0;
            int n = array.length;
            
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (array[i] > array[j]) {
                        count++;
                    }
                }
            }
            
            return count;
        }
        
        /**
         * 打印逆序对详情（仅适用于小数组）
         * 
         * @param array 输入数组
         */
        public static void printInversions(int[] array) {
            System.out.println("数组: " + Arrays.toString(array));
            System.out.println("逆序对详情:");
            
            int count = 0;
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] > array[j]) {
                        System.out.println("  (" + array[i] + ", " + array[j] + ") at indices (" + i + ", " + j + ")");
                        count++;
                    }
                }
            }
            
            System.out.println("总逆序对数: " + count);
        }
    }
    
    // ==================== 最大子数组和 ====================
    
    /**
     * 最大子数组和问题
     * 使用分治算法求解数组中连续子数组的最大和
     */
    public static class MaxSubarraySum {
        
        /**
         * 子数组信息
         */
        public static class SubarrayInfo {
            public int left;        // 左边界
            public int right;       // 右边界
            public long sum;        // 子数组和
            
            public SubarrayInfo(int left, int right, long sum) {
                this.left = left;
                this.right = right;
                this.sum = sum;
            }
            
            @Override
            public String toString() {
                return "SubarrayInfo{left=" + left + ", right=" + right + ", sum=" + sum + "}";
            }
        }
        
        /**
         * 使用分治算法求最大子数组和
         * 时间复杂度：O(n log n)
         * 
         * @param array 输入数组
         * @return 最大子数组信息
         */
        public static SubarrayInfo maxSubarrayDivideConquer(int[] array) {
            if (array == null || array.length == 0) {
                return new SubarrayInfo(0, -1, 0);
            }
            
            return maxSubarrayHelper(array, 0, array.length - 1);
        }
        
        /**
         * 分治算法辅助方法
         * 
         * @param array 数组
         * @param left 左边界
         * @param right 右边界
         * @return 最大子数组信息
         */
        private static SubarrayInfo maxSubarrayHelper(int[] array, int left, int right) {
            // 基础情况：只有一个元素
            if (left == right) {
                return new SubarrayInfo(left, right, array[left]);
            }
            
            int mid = left + (right - left) / 2;
            
            // 递归求解左半部分
            SubarrayInfo leftInfo = maxSubarrayHelper(array, left, mid);
            
            // 递归求解右半部分
            SubarrayInfo rightInfo = maxSubarrayHelper(array, mid + 1, right);
            
            // 求解跨越中点的最大子数组
            SubarrayInfo crossInfo = maxCrossingSubarray(array, left, mid, right);
            
            // 返回三者中的最大值
            if (leftInfo.sum >= rightInfo.sum && leftInfo.sum >= crossInfo.sum) {
                return leftInfo;
            } else if (rightInfo.sum >= leftInfo.sum && rightInfo.sum >= crossInfo.sum) {
                return rightInfo;
            } else {
                return crossInfo;
            }
        }
        
        /**
         * 求跨越中点的最大子数组
         * 
         * @param array 数组
         * @param left 左边界
         * @param mid 中点
         * @param right 右边界
         * @return 跨越中点的最大子数组信息
         */
        private static SubarrayInfo maxCrossingSubarray(int[] array, int left, int mid, int right) {
            // 从中点向左扩展
            long leftSum = Long.MIN_VALUE;
            long sum = 0;
            int maxLeft = mid;
            
            for (int i = mid; i >= left; i--) {
                sum += array[i];
                if (sum > leftSum) {
                    leftSum = sum;
                    maxLeft = i;
                }
            }
            
            // 从中点+1向右扩展
            long rightSum = Long.MIN_VALUE;
            sum = 0;
            int maxRight = mid + 1;
            
            for (int i = mid + 1; i <= right; i++) {
                sum += array[i];
                if (sum > rightSum) {
                    rightSum = sum;
                    maxRight = i;
                }
            }
            
            return new SubarrayInfo(maxLeft, maxRight, leftSum + rightSum);
        }
        
        /**
         * Kadane算法求最大子数组和（动态规划方法，用于比较）
         * 时间复杂度：O(n)
         * 
         * @param array 输入数组
         * @return 最大子数组信息
         */
        public static SubarrayInfo maxSubarrayKadane(int[] array) {
            if (array == null || array.length == 0) {
                return new SubarrayInfo(0, -1, 0);
            }
            
            long maxSum = array[0];
            long currentSum = array[0];
            int start = 0, end = 0, tempStart = 0;
            
            for (int i = 1; i < array.length; i++) {
                if (currentSum < 0) {
                    currentSum = array[i];
                    tempStart = i;
                } else {
                    currentSum += array[i];
                }
                
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    start = tempStart;
                    end = i;
                }
            }
            
            return new SubarrayInfo(start, end, maxSum);
        }
        
        /**
         * 暴力方法求最大子数组和（用于验证）
         * 时间复杂度：O(n²)
         * 
         * @param array 输入数组
         * @return 最大子数组信息
         */
        public static SubarrayInfo maxSubarrayBruteForce(int[] array) {
            if (array == null || array.length == 0) {
                return new SubarrayInfo(0, -1, 0);
            }
            
            long maxSum = Long.MIN_VALUE;
            int bestStart = 0, bestEnd = 0;
            
            for (int i = 0; i < array.length; i++) {
                long currentSum = 0;
                for (int j = i; j < array.length; j++) {
                    currentSum += array[j];
                    if (currentSum > maxSum) {
                        maxSum = currentSum;
                        bestStart = i;
                        bestEnd = j;
                    }
                }
            }
            
            return new SubarrayInfo(bestStart, bestEnd, maxSum);
        }
    }
    
    // ==================== 快速幂算法 ====================
    
    /**
     * 快速幂算法
     * 使用分治思想快速计算 base^exponent % modulo
     */
    public static class FastPower {
        
        /**
         * 快速幂算法（递归版本）
         * 时间复杂度：O(log n)
         * 
         * @param base 底数
         * @param exponent 指数
         * @param modulo 模数
         * @return base^exponent % modulo
         */
        public static long fastPowerRecursive(long base, long exponent, long modulo) {
            if (exponent == 0) {
                return 1 % modulo;
            }
            
            if (exponent == 1) {
                return base % modulo;
            }
            
            // 分治：base^n = (base^(n/2))^2 * base^(n%2)
            long half = fastPowerRecursive(base, exponent / 2, modulo);
            half = (half * half) % modulo;
            
            if (exponent % 2 == 1) {
                half = (half * base) % modulo;
            }
            
            return half;
        }
        
        /**
         * 快速幂算法（迭代版本）
         * 时间复杂度：O(log n)
         * 
         * @param base 底数
         * @param exponent 指数
         * @param modulo 模数
         * @return base^exponent % modulo
         */
        public static long fastPowerIterative(long base, long exponent, long modulo) {
            long result = 1;
            base = base % modulo;
            
            while (exponent > 0) {
                // 如果指数是奇数，乘以当前的base
                if (exponent % 2 == 1) {
                    result = (result * base) % modulo;
                }
                
                // 指数除以2，base平方
                exponent = exponent >> 1; // 相当于 exponent /= 2
                base = (base * base) % modulo;
            }
            
            return result;
        }
        
        /**
         * 矩阵快速幂（用于斐波那契数列等）
         * 
         * @param matrix 矩阵
         * @param n 幂次
         * @return matrix^n
         */
        public static long[][] matrixPower(long[][] matrix, long n) {
            int size = matrix.length;
            long[][] result = new long[size][size];
            
            // 初始化为单位矩阵
            for (int i = 0; i < size; i++) {
                result[i][i] = 1;
            }
            
            long[][] base = new long[size][size];
            for (int i = 0; i < size; i++) {
                System.arraycopy(matrix[i], 0, base[i], 0, size);
            }
            
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
         */
        private static long[][] multiplyMatrix(long[][] a, long[][] b) {
            int n = a.length;
            long[][] result = new long[n][n];
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        result[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
            
            return result;
        }
    }
    
    // ==================== 大整数乘法 ====================
    
    /**
     * 大整数乘法（Karatsuba算法）
     * 使用分治算法优化大整数乘法
     */
    public static class BigIntegerMultiplication {
        
        /**
         * Karatsuba算法实现大整数乘法
         * 时间复杂度：O(n^log2(3)) ≈ O(n^1.585)
         * 
         * @param x 第一个数字字符串
         * @param y 第二个数字字符串
         * @return 乘积字符串
         */
        public static String karatsuba(String x, String y) {
            // 去除前导零
            x = removeLeadingZeros(x);
            y = removeLeadingZeros(y);
            
            // 处理特殊情况
            if (x.equals("0") || y.equals("0")) {
                return "0";
            }
            
            return karatsubaHelper(x, y);
        }
        
        /**
         * Karatsuba算法辅助方法
         */
        private static String karatsubaHelper(String x, String y) {
            int n = Math.max(x.length(), y.length());
            
            // 基础情况：使用传统乘法
            if (n <= 4) {
                return traditionalMultiply(x, y);
            }
            
            // 补齐长度
            x = padZeros(x, n);
            y = padZeros(y, n);
            
            int half = n / 2;
            
            // 分割数字
            String x1 = x.substring(0, n - half);  // 高位
            String x0 = x.substring(n - half);     // 低位
            String y1 = y.substring(0, n - half);  // 高位
            String y0 = y.substring(n - half);     // 低位
            
            // 递归计算三个乘积
            String z2 = karatsubaHelper(x1, y1);                    // x1 * y1
            String z0 = karatsubaHelper(x0, y0);                    // x0 * y0
            String z1 = karatsubaHelper(addStrings(x1, x0), addStrings(y1, y0)); // (x1+x0) * (y1+y0)
            
            // z1 = z1 - z2 - z0
            z1 = subtractStrings(z1, z2);
            z1 = subtractStrings(z1, z0);
            
            // 计算最终结果：z2 * 10^(2*half) + z1 * 10^half + z0
            String result = addStrings(
                addStrings(multiplyByPowerOf10(z2, 2 * half), multiplyByPowerOf10(z1, half)),
                z0
            );
            
            return removeLeadingZeros(result);
        }
        
        /**
         * 传统乘法（用于小数字）
         */
        private static String traditionalMultiply(String x, String y) {
            try {
                long num1 = Long.parseLong(x);
                long num2 = Long.parseLong(y);
                return String.valueOf(num1 * num2);
            } catch (NumberFormatException e) {
                // 如果数字太大，使用逐位乘法
                return multiplyStrings(x, y);
            }
        }
        
        /**
         * 字符串乘法（逐位相乘）
         */
        private static String multiplyStrings(String num1, String num2) {
            int m = num1.length(), n = num2.length();
            int[] result = new int[m + n];
            
            // 逐位相乘
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                    int p1 = i + j, p2 = i + j + 1;
                    int sum = mul + result[p2];
                    
                    result[p2] = sum % 10;
                    result[p1] += sum / 10;
                }
            }
            
            // 转换为字符串
            StringBuilder sb = new StringBuilder();
            for (int digit : result) {
                if (!(sb.length() == 0 && digit == 0)) {
                    sb.append(digit);
                }
            }
            
            return sb.length() == 0 ? "0" : sb.toString();
        }
        
        /**
         * 字符串加法
         */
        private static String addStrings(String num1, String num2) {
            StringBuilder result = new StringBuilder();
            int carry = 0;
            int i = num1.length() - 1;
            int j = num2.length() - 1;
            
            while (i >= 0 || j >= 0 || carry > 0) {
                int digit1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
                int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
                int sum = digit1 + digit2 + carry;
                
                result.append(sum % 10);
                carry = sum / 10;
            }
            
            return result.reverse().toString();
        }
        
        /**
         * 字符串减法（假设num1 >= num2）
         */
        private static String subtractStrings(String num1, String num2) {
            if (compareStrings(num1, num2) < 0) {
                return "0"; // 简化处理，实际应该处理负数
            }
            
            StringBuilder result = new StringBuilder();
            int borrow = 0;
            int i = num1.length() - 1;
            int j = num2.length() - 1;
            
            while (i >= 0) {
                int digit1 = num1.charAt(i--) - '0' - borrow;
                int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
                
                if (digit1 < digit2) {
                    digit1 += 10;
                    borrow = 1;
                } else {
                    borrow = 0;
                }
                
                result.append(digit1 - digit2);
            }
            
            return removeLeadingZeros(result.reverse().toString());
        }
        
        /**
         * 比较两个数字字符串
         */
        private static int compareStrings(String num1, String num2) {
            if (num1.length() != num2.length()) {
                return Integer.compare(num1.length(), num2.length());
            }
            return num1.compareTo(num2);
        }
        
        /**
         * 乘以10的幂次
         */
        private static String multiplyByPowerOf10(String num, int power) {
            if (num.equals("0") || power == 0) {
                return num;
            }
            return num + "0".repeat(power);
        }
        
        /**
         * 补齐前导零
         */
        private static String padZeros(String num, int length) {
            return "0".repeat(Math.max(0, length - num.length())) + num;
        }
        
        /**
         * 去除前导零
         */
        private static String removeLeadingZeros(String num) {
            int i = 0;
            while (i < num.length() - 1 && num.charAt(i) == '0') {
                i++;
            }
            return num.substring(i);
        }
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 分治算法测试 ===\n");
        
        // 测试1：逆序对计算
        System.out.println("1. 逆序对计算测试:");
        
        int[] inversionArray = {2, 3, 8, 6, 1};
        System.out.println("测试数组: " + Arrays.toString(inversionArray));
        
        // 打印详细逆序对信息
        InversionCounter.printInversions(inversionArray.clone());
        
        // 比较不同算法的结果
        long startTime = System.nanoTime();
        long inversionCount = InversionCounter.countInversions(inversionArray.clone());
        long divideConquerTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        long naiveCount = InversionCounter.countInversionsNaive(inversionArray.clone());
        long naiveTime = System.nanoTime() - startTime;
        
        System.out.println("分治算法结果: " + inversionCount + ", 耗时: " + (divideConquerTime / 1000.0) + "μs");
        System.out.println("暴力算法结果: " + naiveCount + ", 耗时: " + (naiveTime / 1000.0) + "μs");
        System.out.println("结果一致性: " + (inversionCount == naiveCount));
        
        // 大数组性能测试
        System.out.println("\n逆序对计算性能测试:");
        Random random = new Random(42);
        
        for (int size : new int[]{1000, 5000, 10000}) {
            int[] largeArray = new int[size];
            for (int i = 0; i < size; i++) {
                largeArray[i] = random.nextInt(size);
            }
            
            startTime = System.currentTimeMillis();
            long largeInversionCount = InversionCounter.countInversions(largeArray.clone());
            long largeTime = System.currentTimeMillis() - startTime;
            
            System.out.println("数组大小: " + size + ", 逆序对数: " + largeInversionCount + ", 耗时: " + largeTime + "ms");
        }
        
        // 测试2：最大子数组和
        System.out.println("\n2. 最大子数组和测试:");
        
        int[] subarrayTest = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("测试数组: " + Arrays.toString(subarrayTest));
        
        // 比较三种算法
        startTime = System.nanoTime();
        MaxSubarraySum.SubarrayInfo divideConquerResult = MaxSubarraySum.maxSubarrayDivideConquer(subarrayTest);
        long dcTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        MaxSubarraySum.SubarrayInfo kadaneResult = MaxSubarraySum.maxSubarrayKadane(subarrayTest);
        long kadaneTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        MaxSubarraySum.SubarrayInfo bruteForceResult = MaxSubarraySum.maxSubarrayBruteForce(subarrayTest);
        long bfTime = System.nanoTime() - startTime;
        
        System.out.println("分治算法: " + divideConquerResult + ", 耗时: " + (dcTime / 1000.0) + "μs");
        System.out.println("Kadane算法: " + kadaneResult + ", 耗时: " + (kadaneTime / 1000.0) + "μs");
        System.out.println("暴力算法: " + bruteForceResult + ", 耗时: " + (bfTime / 1000.0) + "μs");
        
        // 验证子数组内容
        System.out.println("最大子数组内容: " + Arrays.toString(
            Arrays.copyOfRange(subarrayTest, divideConquerResult.left, divideConquerResult.right + 1)));
        
        // 测试3：快速幂算法
        System.out.println("\n3. 快速幂算法测试:");
        
        long base = 2, exponent = 10, modulo = 1000000007;
        
        startTime = System.nanoTime();
        long recursiveResult = FastPower.fastPowerRecursive(base, exponent, modulo);
        long recursiveTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        long iterativeResult = FastPower.fastPowerIterative(base, exponent, modulo);
        long iterativeTime = System.nanoTime() - startTime;
        
        System.out.println(base + "^" + exponent + " % " + modulo + ":");
        System.out.println("递归版本: " + recursiveResult + ", 耗时: " + (recursiveTime / 1000.0) + "μs");
        System.out.println("迭代版本: " + iterativeResult + ", 耗时: " + (iterativeTime / 1000.0) + "μs");
        System.out.println("结果一致性: " + (recursiveResult == iterativeResult));
        
        // 大指数测试
        System.out.println("\n大指数快速幂测试:");
        long largeExponent = 1000000000L;
        
        startTime = System.currentTimeMillis();
        long largeResult = FastPower.fastPowerIterative(base, largeExponent, modulo);
        long largeTime = System.currentTimeMillis() - startTime;
        
        System.out.println(base + "^" + largeExponent + " % " + modulo + " = " + largeResult);
        System.out.println("计算耗时: " + largeTime + "ms");
        
        // 矩阵快速幂测试（斐波那契数列）
        System.out.println("\n矩阵快速幂测试（斐波那契数列）:");
        long[][] fibMatrix = {{1, 1}, {1, 0}};
        int n = 10;
        
        startTime = System.nanoTime();
        long[][] result = FastPower.matrixPower(fibMatrix, n);
        long matrixTime = System.nanoTime() - startTime;
        
        long fibN = result[0][1]; // F(n)在矩阵的[0][1]位置
        System.out.println("F(" + n + ") = " + fibN + ", 耗时: " + (matrixTime / 1000.0) + "μs");
        
        // 测试4：大整数乘法
        System.out.println("\n4. 大整数乘法测试:");
        
        String num1 = "123456789";
        String num2 = "987654321";
        
        startTime = System.nanoTime();
        String karatsubaResult = BigIntegerMultiplication.karatsuba(num1, num2);
        long karatsubaTime = System.nanoTime() - startTime;
        
        System.out.println(num1 + " × " + num2 + " = " + karatsubaResult);
        System.out.println("Karatsuba算法耗时: " + (karatsubaTime / 1000.0) + "μs");
        
        // 验证结果
        try {
            long expected = Long.parseLong(num1) * Long.parseLong(num2);
            long actual = Long.parseLong(karatsubaResult);
            System.out.println("结果正确性: " + (expected == actual));
        } catch (NumberFormatException e) {
            System.out.println("数字太大，无法用long验证");
        }
        
        // 大数乘法测试
        System.out.println("\n大数乘法测试:");
        String bigNum1 = "12345678901234567890";
        String bigNum2 = "98765432109876543210";
        
        startTime = System.currentTimeMillis();
        String bigResult = BigIntegerMultiplication.karatsuba(bigNum1, bigNum2);
        long bigTime = System.currentTimeMillis() - startTime;
        
        System.out.println("大数乘法结果长度: " + bigResult.length());
        System.out.println("计算耗时: " + bigTime + "ms");
        System.out.println("结果: " + bigResult);
        
        // 测试5：性能比较
        System.out.println("\n5. 算法性能比较:");
        
        // 逆序对计算性能比较
        System.out.println("逆序对计算性能比较:");
        for (int size = 100; size <= 1000; size += 300) {
            int[] perfArray = new int[size];
            for (int i = 0; i < size; i++) {
                perfArray[i] = size - i; // 完全逆序
            }
            
            startTime = System.nanoTime();
            long dcInversions = InversionCounter.countInversions(perfArray.clone());
            long dcPerfTime = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            long naiveInversions = InversionCounter.countInversionsNaive(perfArray.clone());
            long naivePerfTime = System.nanoTime() - startTime;
            
            System.out.printf("大小=%d: 分治=%dμs, 暴力=%dμs, 加速比=%.2fx%n", 
                            size, dcPerfTime / 1000, naivePerfTime / 1000, 
                            (double) naivePerfTime / dcPerfTime);
        }
        
        // 最大子数组和性能比较
        System.out.println("\n最大子数组和性能比较:");
        for (int size = 1000; size <= 10000; size += 3000) {
            int[] perfSubarray = new int[size];
            for (int i = 0; i < size; i++) {
                perfSubarray[i] = random.nextInt(201) - 100; // -100到100的随机数
            }
            
            startTime = System.nanoTime();
            MaxSubarraySum.maxSubarrayDivideConquer(perfSubarray);
            long dcSubTime = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            MaxSubarraySum.maxSubarrayKadane(perfSubarray);
            long kadaneSubTime = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            MaxSubarraySum.maxSubarrayBruteForce(perfSubarray);
            long bfSubTime = System.nanoTime() - startTime;
            
            System.out.printf("大小=%d: 分治=%dμs, Kadane=%dμs, 暴力=%dμs%n", 
                            size, dcSubTime / 1000, kadaneSubTime / 1000, bfSubTime / 1000);
        }
        
        // 测试6：边界情况
        System.out.println("\n6. 边界情况测试:");
        
        // 空数组
        System.out.println("空数组逆序对: " + InversionCounter.countInversions(new int[]{}));
        System.out.println("空数组最大子数组: " + MaxSubarraySum.maxSubarrayDivideConquer(new int[]{}));
        
        // 单元素数组
        int[] singleElement = {42};
        System.out.println("单元素数组逆序对: " + InversionCounter.countInversions(singleElement));
        System.out.println("单元素数组最大子数组: " + MaxSubarraySum.maxSubarrayDivideConquer(singleElement));
        
        // 已排序数组
        int[] sortedArray = {1, 2, 3, 4, 5};
        System.out.println("已排序数组逆序对: " + InversionCounter.countInversions(sortedArray));
        
        // 逆序数组
        int[] reverseArray = {5, 4, 3, 2, 1};
        System.out.println("逆序数组逆序对: " + InversionCounter.countInversions(reverseArray));
        
        // 全负数数组
        int[] allNegative = {-5, -2, -8, -1, -3};
        System.out.println("全负数数组最大子数组: " + MaxSubarraySum.maxSubarrayDivideConquer(allNegative));
        
        // 快速幂边界情况
        System.out.println("0的任意次幂: " + FastPower.fastPowerIterative(0, 5, 1000));
        System.out.println("任意数的0次幂: " + FastPower.fastPowerIterative(5, 0, 1000));
        System.out.println("1的任意次幂: " + FastPower.fastPowerIterative(1, 1000, 1000));
        
        // 大整数乘法边界情况
        System.out.println("0乘以任意数: " + BigIntegerMultiplication.karatsuba("0", "123456"));
        System.out.println("1乘以任意数: " + BigIntegerMultiplication.karatsuba("1", "123456"));
        System.out.println("单位数乘法: " + BigIntegerMultiplication.karatsuba("7", "8"));
    }
}