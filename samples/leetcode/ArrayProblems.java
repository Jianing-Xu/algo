package leetcode;

import java.util.*;

/**
 * LeetCode数组相关问题解答
 * 包含三数之和、求众数、缺失的第一个正数等经典题目
 */
public class ArrayProblems {
    
    // ==================== Three Sum（三数之和）====================
    
    /**
     * LeetCode 15. 三数之和
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c
     * 使得 a + b + c = 0？找出所有满足条件且不重复的三元组
     * 
     * 解题思路：
     * 1. 先对数组排序
     * 2. 固定第一个数，用双指针寻找另外两个数
     * 3. 跳过重复元素避免重复解
     * 
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     * 
     * @param nums 输入数组
     * @return 所有不重复的三元组
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        // 排序数组
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复的第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // 双指针寻找另外两个数
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    // 找到一个解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 跳过重复的left
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    
                    // 跳过重复的right
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // 需要更大的数
                } else {
                    right--; // 需要更小的数
                }
            }
        }
        
        return result;
    }
    
    /**
     * 三数之和的变体：最接近目标值的三数之和
     * LeetCode 16. 最接近的三数之和
     * 
     * @param nums 输入数组
     * @param target 目标值
     * @return 最接近目标值的三数之和
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                if (currentSum < target) {
                    left++;
                } else if (currentSum > target) {
                    right--;
                } else {
                    return currentSum; // 找到精确匹配
                }
            }
        }
        
        return closestSum;
    }
    
    // ==================== Majority Element（求众数）====================
    
    /**
     * LeetCode 169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素
     * 多数元素是指在数组中出现次数大于 ⌊n/2⌋ 的元素
     * 
     * 解题思路：Boyer-Moore 投票算法
     * 1. 维护一个候选者和计数器
     * 2. 遇到相同元素计数+1，不同元素计数-1
     * 3. 计数为0时更换候选者
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * @param nums 输入数组
     * @return 多数元素
     */
    public static int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        
        // Boyer-Moore 投票算法
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        return candidate;
    }
    
    /**
     * 多数元素的哈希表解法（用于验证）
     * 时间复杂度：O(n)，空间复杂度：O(n)
     * 
     * @param nums 输入数组
     * @return 多数元素
     */
    public static int majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int majority = nums.length / 2;
        
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
            
            if (count > majority) {
                return num;
            }
        }
        
        return -1; // 不应该到达这里
    }
    
    /**
     * 多数元素的排序解法
     * 时间复杂度：O(n log n)，空间复杂度：O(1)
     * 
     * @param nums 输入数组
     * @return 多数元素
     */
    public static int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2]; // 中位数就是多数元素
    }
    
    // ==================== First Missing Positive（缺失的第一个正数）====================
    
    /**
     * LeetCode 41. 缺失的第一个正数
     * 给你一个未排序的整数数组 nums，请你找出其中没有出现的最小的正整数
     * 
     * 解题思路：原地哈希
     * 1. 将每个正数放到它应该在的位置（nums[i] = i+1）
     * 2. 再次遍历找到第一个不在正确位置的数
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * @param nums 输入数组
     * @return 缺失的第一个正数
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        // 第一步：将每个正数放到正确的位置
        for (int i = 0; i < n; i++) {
            // 将nums[i]放到索引nums[i]-1的位置
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 交换nums[i]和nums[nums[i]-1]
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        
        // 第二步：找到第一个不在正确位置的数
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        
        // 如果1到n都存在，返回n+1
        return n + 1;
    }
    
    /**
     * 缺失的第一个正数的标记解法
     * 使用数组本身作为哈希表，用负数标记存在的正数
     * 
     * @param nums 输入数组
     * @return 缺失的第一个正数
     */
    public static int firstMissingPositiveMarking(int[] nums) {
        int n = nums.length;
        
        // 第一步：将所有非正数和大于n的数设为n+1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        
        // 第二步：用负数标记存在的正数
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        
        // 第三步：找到第一个正数的位置
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        
        return n + 1;
    }
    
    // ==================== 辅助方法和测试 ====================
    
    /**
     * 打印二维列表
     */
    private static void printListOfLists(List<List<Integer>> lists) {
        System.out.print("[");
        for (int i = 0; i < lists.size(); i++) {
            System.out.print(lists.get(i));
            if (i < lists.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== LeetCode 数组问题测试 ===\n");
        
        // 测试1：三数之和
        System.out.println("1. 三数之和测试:");
        
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("输入数组: " + Arrays.toString(nums1));
        
        long startTime = System.nanoTime();
        List<List<Integer>> threeSumResult = threeSum(nums1);
        long threeSumTime = System.nanoTime() - startTime;
        
        System.out.print("三数之和结果: ");
        printListOfLists(threeSumResult);
        System.out.println("耗时: " + (threeSumTime / 1000.0) + "μs");
        
        // 测试最接近的三数之和
        int target = 1;
        startTime = System.nanoTime();
        int closestSum = threeSumClosest(nums1, target);
        long closestTime = System.nanoTime() - startTime;
        
        System.out.println("最接近 " + target + " 的三数之和: " + closestSum);
        System.out.println("耗时: " + (closestTime / 1000.0) + "μs");
        
        // 测试2：多数元素
        System.out.println("\n2. 多数元素测试:");
        
        int[] nums2 = {3, 2, 3};
        int[] nums3 = {2, 2, 1, 1, 1, 2, 2};
        
        System.out.println("输入数组1: " + Arrays.toString(nums2));
        
        startTime = System.nanoTime();
        int majority1 = majorityElement(nums2);
        long majorityTime1 = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        int majorityHash1 = majorityElementHashMap(nums2.clone());
        long majorityHashTime1 = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        int majoritySort1 = majorityElementSort(nums2.clone());
        long majoritySortTime1 = System.nanoTime() - startTime;
        
        System.out.println("Boyer-Moore算法: " + majority1 + ", 耗时: " + (majorityTime1 / 1000.0) + "μs");
        System.out.println("哈希表算法: " + majorityHash1 + ", 耗时: " + (majorityHashTime1 / 1000.0) + "μs");
        System.out.println("排序算法: " + majoritySort1 + ", 耗时: " + (majoritySortTime1 / 1000.0) + "μs");
        System.out.println("结果一致性: " + (majority1 == majorityHash1 && majorityHash1 == majoritySort1));
        
        System.out.println("\n输入数组2: " + Arrays.toString(nums3));
        int majority2 = majorityElement(nums3);
        System.out.println("多数元素: " + majority2);
        
        // 测试3：缺失的第一个正数
        System.out.println("\n3. 缺失的第一个正数测试:");
        
        int[][] testCases = {
            {1, 2, 0},
            {3, 4, -1, 1},
            {7, 8, 9, 11, 12},
            {1},
            {-1, -2, -3},
            {1, 2, 3, 4, 5}
        };
        
        for (int[] testCase : testCases) {
            System.out.println("输入数组: " + Arrays.toString(testCase));
            
            startTime = System.nanoTime();
            int missing1 = firstMissingPositive(testCase.clone());
            long missingTime1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int missing2 = firstMissingPositiveMarking(testCase.clone());
            long missingTime2 = System.nanoTime() - startTime;
            
            System.out.println("原地哈希: " + missing1 + ", 耗时: " + (missingTime1 / 1000.0) + "μs");
            System.out.println("标记法: " + missing2 + ", 耗时: " + (missingTime2 / 1000.0) + "μs");
            System.out.println("结果一致性: " + (missing1 == missing2));
            System.out.println();
        }
        
        // 测试4：性能测试
        System.out.println("4. 性能测试:");
        
        Random random = new Random(42);
        
        // 三数之和性能测试
        System.out.println("三数之和性能测试:");
        for (int size : new int[]{100, 500, 1000}) {
            int[] perfArray = new int[size];
            for (int i = 0; i < size; i++) {
                perfArray[i] = random.nextInt(2000) - 1000; // -1000到1000的随机数
            }
            
            startTime = System.currentTimeMillis();
            List<List<Integer>> perfResult = threeSum(perfArray);
            long perfTime = System.currentTimeMillis() - startTime;
            
            System.out.printf("数组大小=%d, 解的个数=%d, 耗时=%dms%n", 
                            size, perfResult.size(), perfTime);
        }
        
        // 多数元素性能测试
        System.out.println("\n多数元素性能测试:");
        for (int size : new int[]{10000, 50000, 100000}) {
            int[] perfArray = new int[size];
            int majorityValue = random.nextInt(100);
            
            // 确保有多数元素
            for (int i = 0; i < size; i++) {
                if (i < size / 2 + 1) {
                    perfArray[i] = majorityValue;
                } else {
                    perfArray[i] = random.nextInt(100);
                }
            }
            
            // 打乱数组
            for (int i = 0; i < size; i++) {
                int j = random.nextInt(size);
                int temp = perfArray[i];
                perfArray[i] = perfArray[j];
                perfArray[j] = temp;
            }
            
            startTime = System.currentTimeMillis();
            int perfMajority1 = majorityElement(perfArray.clone());
            long perfTime1 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            int perfMajority2 = majorityElementHashMap(perfArray.clone());
            long perfTime2 = System.currentTimeMillis() - startTime;
            
            System.out.printf("数组大小=%d: Boyer-Moore=%dms, 哈希表=%dms, 加速比=%.2fx%n", 
                            size, perfTime1, perfTime2, (double) perfTime2 / perfTime1);
        }
        
        // 测试5：边界情况
        System.out.println("\n5. 边界情况测试:");
        
        // 三数之和边界情况
        System.out.println("三数之和边界情况:");
        System.out.println("空数组: " + threeSum(new int[]{}));
        System.out.println("两个元素: " + threeSum(new int[]{1, 2}));
        System.out.println("全零: " + threeSum(new int[]{0, 0, 0}));
        System.out.println("无解: " + threeSum(new int[]{1, 2, 3}));
        
        // 多数元素边界情况
        System.out.println("\n多数元素边界情况:");
        System.out.println("单元素: " + majorityElement(new int[]{1}));
        System.out.println("两个相同: " + majorityElement(new int[]{1, 1}));
        System.out.println("两个不同: " + majorityElement(new int[]{1, 2, 1}));
        
        // 缺失正数边界情况
        System.out.println("\n缺失正数边界情况:");
        System.out.println("空数组: " + firstMissingPositive(new int[]{}));
        System.out.println("单个1: " + firstMissingPositive(new int[]{1}));
        System.out.println("单个非1: " + firstMissingPositive(new int[]{2}));
        System.out.println("连续序列: " + firstMissingPositive(new int[]{1, 2, 3, 4, 5}));
    }
}