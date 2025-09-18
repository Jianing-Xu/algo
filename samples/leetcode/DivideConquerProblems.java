package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * LeetCode分治算法相关问题解答 包含最大子数组和等经典分治题目
 */
public class DivideConquerProblems {

  // ==================== Maximum Subarray（最大子数组和）====================

  /**
   * LeetCode 53. 最大子数组和 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
   * <p>
   * 解题思路：分治算法 1. 将数组分为左右两部分 2. 递归求解左半部分的最大子数组和 3. 递归求解右半部分的最大子数组和 4. 求解跨越中点的最大子数组和 5. 返回三者中的最大值
   * <p>
   * 时间复杂度：O(n log n) 空间复杂度：O(log n) - 递归栈空间
   *
   * @param nums 整数数组
   * @return 最大子数组和
   */
  public static int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    return maxSubArrayDivideConquer(nums, 0, nums.length - 1);
  }

  /**
   * 分治求解最大子数组和
   *
   * @param nums  数组
   * @param left  左边界
   * @param right 右边界
   * @return 最大子数组和
   */
  private static int maxSubArrayDivideConquer(int[] nums, int left, int right) {
    if (left == right) {
      return nums[left];
    }

    int mid = left + (right - left) / 2;

    // 递归求解左半部分
    int leftMax = maxSubArrayDivideConquer(nums, left, mid);

    // 递归求解右半部分
    int rightMax = maxSubArrayDivideConquer(nums, mid + 1, right);

    // 求解跨越中点的最大子数组和
    int crossMax = maxCrossingSubArray(nums, left, mid, right);

    return Math.max(Math.max(leftMax, rightMax), crossMax);
  }

  /**
   * 求解跨越中点的最大子数组和
   *
   * @param nums  数组
   * @param left  左边界
   * @param mid   中点
   * @param right 右边界
   * @return 跨越中点的最大子数组和
   */
  private static int maxCrossingSubArray(int[] nums, int left, int mid, int right) {
    // 从中点向左扩展
    int leftSum = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = mid; i >= left; i--) {
      sum += nums[i];
      leftSum = Math.max(leftSum, sum);
    }

    // 从中点+1向右扩展
    int rightSum = Integer.MIN_VALUE;
    sum = 0;
    for (int i = mid + 1; i <= right; i++) {
      sum += nums[i];
      rightSum = Math.max(rightSum, sum);
    }

    return leftSum + rightSum;
  }

  /**
   * 最大子数组和（动态规划解法，用于对比） 时间复杂度：O(n) 空间复杂度：O(1)
   *
   * @param nums 整数数组
   * @return 最大子数组和
   */
  public static int maxSubArrayDP(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int maxSum = nums[0];
    int currentSum = nums[0];

    for (int i = 1; i < nums.length; i++) {
      currentSum = Math.max(nums[i], currentSum + nums[i]);
      maxSum = Math.max(maxSum, currentSum);
    }

    return maxSum;
  }

  /**
   * 最大子数组和（返回子数组的起始和结束位置）
   *
   * @param nums 整数数组
   * @return [最大和, 起始位置, 结束位置]
   */
  public static int[] maxSubArrayWithIndices(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new int[]{0, -1, -1};
    }

    int maxSum = nums[0];
    int currentSum = nums[0];
    int start = 0, end = 0, tempStart = 0;

    for (int i = 1; i < nums.length; i++) {
      if (currentSum < 0) {
        currentSum = nums[i];
        tempStart = i;
      } else {
        currentSum += nums[i];
      }

      if (currentSum > maxSum) {
        maxSum = currentSum;
        start = tempStart;
        end = i;
      }
    }

    return new int[]{maxSum, start, end};
  }

  // ==================== 其他分治问题 ====================

  /**
   * LeetCode 169. 多数元素 给定一个大小为 n 的数组 nums ，返回其中的多数元素 多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素
   * <p>
   * 分治解法： 1. 将数组分为左右两部分 2. 递归求解左右两部分的多数元素 3. 如果左右多数元素相同，返回该元素 4. 否则统计两个候选元素在整个区间的出现次数，返回出现次数多的
   *
   * @param nums 整数数组
   * @return 多数元素
   */
  public static int majorityElement(int[] nums) {
    return majorityElementDivideConquer(nums, 0, nums.length - 1);
  }

  /**
   * 分治求解多数元素
   *
   * @param nums  数组
   * @param left  左边界
   * @param right 右边界
   * @return 多数元素
   */
  private static int majorityElementDivideConquer(int[] nums, int left, int right) {
    if (left == right) {
      return nums[left];
    }

    int mid = left + (right - left) / 2;

    int leftMajority = majorityElementDivideConquer(nums, left, mid);
    int rightMajority = majorityElementDivideConquer(nums, mid + 1, right);

    if (leftMajority == rightMajority) {
      return leftMajority;
    }

    // 统计两个候选元素的出现次数
    int leftCount = countInRange(nums, leftMajority, left, right);
    int rightCount = countInRange(nums, rightMajority, left, right);

    return leftCount > rightCount ? leftMajority : rightMajority;
  }

  /**
   * 统计元素在指定范围内的出现次数
   *
   * @param nums   数组
   * @param target 目标元素
   * @param left   左边界
   * @param right  右边界
   * @return 出现次数
   */
  private static int countInRange(int[] nums, int target, int left, int right) {
    int count = 0;
    for (int i = left; i <= right; i++) {
      if (nums[i] == target) {
        count++;
      }
    }
    return count;
  }

  /**
   * 多数元素（摩尔投票法，用于对比） 时间复杂度：O(n) 空间复杂度：O(1)
   *
   * @param nums 整数数组
   * @return 多数元素
   */
  public static int majorityElementMoore(int[] nums) {
    int candidate = nums[0];
    int count = 1;

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
   * LeetCode 215. 数组中的第K个最大元素 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素
   * <p>
   * 快速选择算法（分治思想）： 1. 选择一个基准元素进行分区 2. 如果基准位置正好是第k大的位置，返回该元素 3. 如果基准位置大于k，在左半部分继续查找 4.
   * 如果基准位置小于k，在右半部分继续查找
   *
   * @param nums 整数数组
   * @param k    第k大
   * @return 第k个最大元素
   */
  public static int findKthLargest(int[] nums, int k) {
    return quickSelect(nums, 0, nums.length - 1, nums.length - k);
  }

  /**
   * 快速选择算法
   *
   * @param nums  数组
   * @param left  左边界
   * @param right 右边界
   * @param k     目标位置（从小到大排序后的位置）
   * @return 第k小的元素
   */
  private static int quickSelect(int[] nums, int left, int right, int k) {
    if (left == right) {
      return nums[left];
    }

    // 随机选择基准元素以避免最坏情况
    Random random = new Random();
    int pivotIndex = left + random.nextInt(right - left + 1);
    swap(nums, pivotIndex, right);

    int partitionIndex = partition(nums, left, right);

    if (partitionIndex == k) {
      return nums[partitionIndex];
    } else if (partitionIndex > k) {
      return quickSelect(nums, left, partitionIndex - 1, k);
    } else {
      return quickSelect(nums, partitionIndex + 1, right, k);
    }
  }

  /**
   * 分区操作
   *
   * @param nums  数组
   * @param left  左边界
   * @param right 右边界
   * @return 基准元素的最终位置
   */
  private static int partition(int[] nums, int left, int right) {
    int pivot = nums[right];
    int i = left;

    for (int j = left; j < right; j++) {
      if (nums[j] <= pivot) {
        swap(nums, i, j);
        i++;
      }
    }

    swap(nums, i, right);
    return i;
  }

  /**
   * 交换数组中两个元素
   *
   * @param nums 数组
   * @param i    索引i
   * @param j    索引j
   */
  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  /**
   * LeetCode 23. 合并K个升序链表 给你一个链表数组，每个链表都已经按升序排列 请你将所有链表合并到一个升序链表中，返回合并后的链表
   * <p>
   * 分治解法： 1. 将k个链表分为两组 2. 递归合并左半部分和右半部分 3. 合并两个已排序的链表
   *
   * @param lists 链表数组
   * @return 合并后的链表
   */
  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    return mergeKListsDivideConquer(lists, 0, lists.length - 1);
  }

  /**
   * 分治合并K个链表
   *
   * @param lists 链表数组
   * @param left  左边界
   * @param right 右边界
   * @return 合并后的链表
   */
  private static ListNode mergeKListsDivideConquer(ListNode[] lists, int left, int right) {
    if (left == right) {
      return lists[left];
    }

    if (left > right) {
      return null;
    }

    int mid = left + (right - left) / 2;

    ListNode leftList = mergeKListsDivideConquer(lists, left, mid);
    ListNode rightList = mergeKListsDivideConquer(lists, mid + 1, right);

    return mergeTwoLists(leftList, rightList);
  }

  /**
   * 合并两个有序链表
   *
   * @param l1 链表1
   * @param l2 链表2
   * @return 合并后的链表
   */
  private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        current.next = l1;
        l1 = l1.next;
      } else {
        current.next = l2;
        l2 = l2.next;
      }
      current = current.next;
    }

    current.next = (l1 != null) ? l1 : l2;

    return dummy.next;
  }

  /**
   * 生成随机数组
   *
   * @param size 数组大小
   * @param min  最小值
   * @param max  最大值
   * @return 随机数组
   */
  public static int[] generateRandomArray(int size, int min, int max) {
    Random random = new Random();
    int[] array = new int[size];

    for (int i = 0; i < size; i++) {
      array[i] = random.nextInt(max - min + 1) + min;
    }

    return array;
  }

  // ==================== 辅助方法 ====================

  /**
   * 创建链表
   *
   * @param values 值数组
   * @return 链表头节点
   */
  public static ListNode createLinkedList(int[] values) {
    if (values == null || values.length == 0) {
      return null;
    }

    ListNode head = new ListNode(values[0]);
    ListNode current = head;

    for (int i = 1; i < values.length; i++) {
      current.next = new ListNode(values[i]);
      current = current.next;
    }

    return head;
  }

  /**
   * 链表转数组
   *
   * @param head 链表头节点
   * @return 数组
   */
  public static int[] linkedListToArray(ListNode head) {
    List<Integer> list = new ArrayList<>();
    ListNode current = head;

    while (current != null) {
      list.add(current.val);
      current = current.next;
    }

    return list.stream().mapToInt(i -> i).toArray();
  }

  /**
   * 验证数组是否有序
   *
   * @param nums 数组
   * @return 是否有序
   */
  public static boolean isSorted(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] < nums[i - 1]) {
        return false;
      }
    }
    return true;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== LeetCode 分治算法问题测试 ===\n");

    // 测试1：最大子数组和
    System.out.println("1. 最大子数组和测试:");

    int[][] maxSubArrayTests = {
        {-2, 1, -3, 4, -1, 2, 1, -5, 4},
        {1},
        {5, 4, -1, 7, 8},
        {-1},
        {-2, -1},
        {1, 2, 3, 4, 5},
        {-5, -4, -3, -2, -1}
    };

    for (int i = 0; i < maxSubArrayTests.length; i++) {
      int[] nums = maxSubArrayTests[i];

      System.out.printf("测试 %d: %s%n", i + 1, Arrays.toString(nums));

      long startTime = System.nanoTime();
      int result1 = maxSubArray(nums);
      long time1 = System.nanoTime() - startTime;

      startTime = System.nanoTime();
      int result2 = maxSubArrayDP(nums);
      long time2 = System.nanoTime() - startTime;

      int[] resultWithIndices = maxSubArrayWithIndices(nums.clone());

      System.out.printf("  分治算法: %d (%.2fμs)%n", result1, time1 / 1000.0);
      System.out.printf("  动态规划: %d (%.2fμs)%n", result2, time2 / 1000.0);
      System.out.printf("  带索引: 最大和=%d, 范围=[%d, %d]%n",
          resultWithIndices[0], resultWithIndices[1], resultWithIndices[2]);
      System.out.printf("  一致性: %s%n", result1 == result2 && result1 == resultWithIndices[0]);

      if (resultWithIndices[1] >= 0 && resultWithIndices[2] >= 0) {
        int[] subArray = Arrays.copyOfRange(nums, resultWithIndices[1], resultWithIndices[2] + 1);
        System.out.printf("  最大子数组: %s%n", Arrays.toString(subArray));
      }

      System.out.println();
    }

    // 测试2：多数元素
    System.out.println("2. 多数元素测试:");

    int[][] majorityTests = {
        {3, 2, 3},
        {2, 2, 1, 1, 1, 2, 2},
        {1},
        {1, 1, 1, 2, 2},
        {6, 5, 5}
    };

    for (int i = 0; i < majorityTests.length; i++) {
      int[] nums = majorityTests[i];

      System.out.printf("测试 %d: %s%n", i + 1, Arrays.toString(nums));

      long startTime = System.nanoTime();
      int result1 = majorityElement(nums);
      long time1 = System.nanoTime() - startTime;

      startTime = System.nanoTime();
      int result2 = majorityElementMoore(nums);
      long time2 = System.nanoTime() - startTime;

      System.out.printf("  分治算法: %d (%.2fμs)%n", result1, time1 / 1000.0);
      System.out.printf("  摩尔投票: %d (%.2fμs)%n", result2, time2 / 1000.0);
      System.out.printf("  一致性: %s%n", result1 == result2);

      // 验证结果
      int count = 0;
      for (int num : nums) {
        if (num == result1) {
          count++;
        }
      }
      System.out.printf("  验证: 出现%d次，占比%.2f%%，是否为多数: %s%n",
          count, 100.0 * count / nums.length, count > nums.length / 2);
      System.out.println();
    }

    // 测试3：第K个最大元素
    System.out.println("3. 第K个最大元素测试:");

    Object[][] kthLargestTests = {
        {new int[]{3, 2, 1, 5, 6, 4}, 2},
        {new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4},
        {new int[]{1}, 1},
        {new int[]{7, 10, 4, 3, 20, 15}, 3}
    };

    for (int i = 0; i < kthLargestTests.length; i++) {
      int[] nums = (int[]) kthLargestTests[i][0];
      int k = (int) kthLargestTests[i][1];

      System.out.printf("测试 %d: %s, k=%d%n", i + 1, Arrays.toString(nums), k);

      int[] numsCopy = nums.clone();

      long startTime = System.nanoTime();
      int result = findKthLargest(numsCopy, k);
      long time = System.nanoTime() - startTime;

      // 验证结果
      Arrays.sort(nums);
      int expected = nums[nums.length - k];

      System.out.printf("  快速选择: %d (%.2fμs)%n", result, time / 1000.0);
      System.out.printf("  排序验证: %d%n", expected);
      System.out.printf("  正确性: %s%n", result == expected);
      System.out.printf("  排序后数组: %s%n", Arrays.toString(nums));
      System.out.println();
    }

    // 测试4：合并K个有序链表
    System.out.println("4. 合并K个有序链表测试:");

    int[][][] mergeKListsTests = {
        {{1, 4, 5}, {1, 3, 4}, {2, 6}},
        {},
        {{}}
    };

    for (int i = 0; i < mergeKListsTests.length; i++) {
      int[][] listsArray = mergeKListsTests[i];

      System.out.printf("测试 %d:%n", i + 1);

      ListNode[] lists = new ListNode[listsArray.length];
      for (int j = 0; j < listsArray.length; j++) {
        lists[j] = createLinkedList(listsArray[j]);
        System.out.printf("  链表 %d: %s%n", j + 1,
            lists[j] != null ? lists[j].toString() : "null");
      }

      long startTime = System.nanoTime();
      ListNode result = mergeKLists(lists);
      long time = System.nanoTime() - startTime;

      System.out.printf("  合并结果: %s (%.2fμs)%n",
          result != null ? result.toString() : "null", time / 1000.0);

      // 验证结果是否有序
      if (result != null) {
        int[] resultArray = linkedListToArray(result);
        System.out.printf("  是否有序: %s%n", isSorted(resultArray));
      }

      System.out.println();
    }

    // 测试5：性能测试
    System.out.println("5. 性能测试:");

    // 最大子数组和性能测试
    System.out.println("最大子数组和性能测试:");
    int[] sizes = {1000, 10000, 100000};

    for (int size : sizes) {
      int[] largeArray = generateRandomArray(size, -100, 100);

      System.out.printf("数组大小=%d:%n", size);

      long startTime = System.currentTimeMillis();
      int result1 = maxSubArray(largeArray.clone());
      long time1 = System.currentTimeMillis() - startTime;

      startTime = System.currentTimeMillis();
      int result2 = maxSubArrayDP(largeArray.clone());
      long time2 = System.currentTimeMillis() - startTime;

      System.out.printf("  分治算法: %d (%dms)%n", result1, time1);
      System.out.printf("  动态规划: %d (%dms)%n", result2, time2);
      System.out.printf("  一致性: %s%n", result1 == result2);
      System.out.printf("  性能比: %.2fx%n", time1 == 0 ? 1.0 : (double) time1 / time2);
      System.out.println();
    }

    // 第K个最大元素性能测试
    System.out.println("第K个最大元素性能测试:");
    for (int size : new int[]{10000, 50000, 100000}) {
      int[] largeArray = generateRandomArray(size, 1, size * 2);
      int k = size / 2;

      System.out.printf("数组大小=%d, k=%d:%n", size, k);

      long startTime = System.currentTimeMillis();
      int result1 = findKthLargest(largeArray.clone(), k);
      long time1 = System.currentTimeMillis() - startTime;

      // 排序方法作为对比
      startTime = System.currentTimeMillis();
      int[] sortedArray = largeArray.clone();
      Arrays.sort(sortedArray);
      int result2 = sortedArray[size - k];
      long time2 = System.currentTimeMillis() - startTime;

      System.out.printf("  快速选择: %d (%dms)%n", result1, time1);
      System.out.printf("  排序方法: %d (%dms)%n", result2, time2);
      System.out.printf("  一致性: %s%n", result1 == result2);
      System.out.printf("  性能比: %.2fx%n", time2 == 0 ? 1.0 : (double) time2 / time1);
      System.out.println();
    }

    // 测试6：边界情况
    System.out.println("6. 边界情况测试:");

    System.out.println("最大子数组和边界情况:");
    System.out.println("空数组: " + maxSubArray(new int[]{}));
    System.out.println("单元素正数: " + maxSubArray(new int[]{5}));
    System.out.println("单元素负数: " + maxSubArray(new int[]{-3}));
    System.out.println("全负数: " + maxSubArray(new int[]{-5, -2, -8, -1}));
    System.out.println("全正数: " + maxSubArray(new int[]{1, 2, 3, 4}));

    System.out.println("\n多数元素边界情况:");
    System.out.println("单元素: " + majorityElement(new int[]{1}));
    System.out.println("两元素相同: " + majorityElement(new int[]{1, 1}));
    System.out.println("三元素: " + majorityElement(new int[]{2, 2, 1}));

    System.out.println("\n第K个最大元素边界情况:");
    System.out.println("k=1(最大): " + findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 1));
    System.out.println("k=n(最小): " + findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 6));
    System.out.println("单元素: " + findKthLargest(new int[]{1}, 1));

    System.out.println("\n合并K个链表边界情况:");
    System.out.println("空数组: " + mergeKLists(new ListNode[]{}));
    System.out.println("单个空链表: " + mergeKLists(new ListNode[]{null}));
    System.out.println(
        "单个非空链表: " + mergeKLists(new ListNode[]{createLinkedList(new int[]{1, 2, 3})}));

    // 测试7：正确性验证
    System.out.println("\n7. 正确性验证:");

    // 大量随机测试
    Random random = new Random(42);
    int correctCount = 0;
    int totalTests = 1000;

    for (int i = 0; i < totalTests; i++) {
      int size = random.nextInt(100) + 1;
      int[] nums = generateRandomArray(size, -100, 100);

      int result1 = maxSubArray(nums.clone());
      int result2 = maxSubArrayDP(nums.clone());

      if (result1 == result2) {
        correctCount++;
      }
    }

    System.out.printf("最大子数组和算法一致性: %d/%d 正确, 正确率: %.2f%%%n",
        correctCount, totalTests, 100.0 * correctCount / totalTests);

    // 多数元素正确性验证
    correctCount = 0;
    for (int i = 0; i < totalTests; i++) {
      int size = random.nextInt(99) + 1;
      if (size % 2 == 0) {
        size++; // 确保是奇数，便于构造多数元素
      }

      int[] nums = new int[size];
      int majority = random.nextInt(100);

      // 确保多数元素出现次数超过一半
      int majorityCount = size / 2 + 1;
      for (int j = 0; j < majorityCount; j++) {
        nums[j] = majority;
      }
      for (int j = majorityCount; j < size; j++) {
        nums[j] = random.nextInt(100);
        while (nums[j] == majority) {
          nums[j] = random.nextInt(100);
        }
      }

      // 打乱数组
      for (int j = 0; j < size; j++) {
        int swapIndex = random.nextInt(size);
        int temp = nums[j];
        nums[j] = nums[swapIndex];
        nums[swapIndex] = temp;
      }

      int result1 = majorityElement(nums.clone());
      int result2 = majorityElementMoore(nums.clone());

      if (result1 == result2 && result1 == majority) {
        correctCount++;
      }
    }

    System.out.printf("多数元素算法一致性: %d/%d 正确, 正确率: %.2f%%%n",
        correctCount, totalTests, 100.0 * correctCount / totalTests);

    // 第K个最大元素正确性验证
    correctCount = 0;
    for (int i = 0; i < totalTests; i++) {
      int size = random.nextInt(50) + 1;
      int[] nums = generateRandomArray(size, 1, 1000);
      int k = random.nextInt(size) + 1;

      int result1 = findKthLargest(nums.clone(), k);

      int[] sorted = nums.clone();
      Arrays.sort(sorted);
      int result2 = sorted[size - k];

      if (result1 == result2) {
        correctCount++;
      }
    }

    System.out.printf("第K个最大元素正确性: %d/%d 正确, 正确率: %.2f%%%n",
        correctCount, totalTests, 100.0 * correctCount / totalTests);

    System.out.println("\n=== 分治算法测试完成 ===");
  }

  /**
   * 链表节点定义
   */
  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      ListNode current = this;
      while (current != null) {
        sb.append(current.val);
        if (current.next != null) {
          sb.append(" -> ");
        }
        current = current.next;
      }
      return sb.toString();
    }
  }
}