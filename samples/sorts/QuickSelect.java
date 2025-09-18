package sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 快速选择算法 在O(n)平均时间复杂度内找到数组中第K大的元素
 */
public class QuickSelect {

  /**
   * 找到数组中第k小的元素（k从1开始） 平均时间复杂度：O(n)，最坏时间复杂度：O(n²)
   *
   * @param arr 数组
   * @param k   第k小（1 <= k <= arr.length）
   * @return 第k小的元素
   */
  public static int findKthSmallest(int[] arr, int k) {
    if (k < 1 || k > arr.length) {
      throw new IllegalArgumentException("k必须在1到" + arr.length + "之间");
    }

    return quickSelect(arr, 0, arr.length - 1, k - 1);
  }

  /**
   * 找到数组中第k大的元素（k从1开始）
   *
   * @param arr 数组
   * @param k   第k大（1 <= k <= arr.length）
   * @return 第k大的元素
   */
  public static int findKthLargest(int[] arr, int k) {
    if (k < 1 || k > arr.length) {
      throw new IllegalArgumentException("k必须在1到" + arr.length + "之间");
    }

    // 第k大等于第(n-k+1)小
    return quickSelect(arr, 0, arr.length - 1, arr.length - k);
  }

  /**
   * 快速选择算法的核心实现
   *
   * @param arr   数组
   * @param left  左边界
   * @param right 右边界
   * @param k     目标索引
   * @return 第k小的元素
   */
  private static int quickSelect(int[] arr, int left, int right, int k) {
    if (left == right) {
      return arr[left];
    }

    // 随机选择基准元素，避免最坏情况
    int randomIndex = left + new Random().nextInt(right - left + 1);
    swap(arr, randomIndex, right);

    // 分区操作
    int pivotIndex = partition(arr, left, right);

    if (k == pivotIndex) {
      // 找到目标元素
      return arr[k];
    } else if (k < pivotIndex) {
      // 目标在左半部分
      return quickSelect(arr, left, pivotIndex - 1, k);
    } else {
      // 目标在右半部分
      return quickSelect(arr, pivotIndex + 1, right, k);
    }
  }

  /**
   * 分区操作（Lomuto分区方案）
   */
  private static int partition(int[] arr, int left, int right) {
    int pivot = arr[right];
    int i = left - 1;

    for (int j = left; j < right; j++) {
      if (arr[j] <= pivot) {
        i++;
        swap(arr, i, j);
      }
    }

    swap(arr, i + 1, right);
    return i + 1;
  }

  /**
   * 使用堆的方法找第k大元素 时间复杂度：O(n log k)，空间复杂度：O(k)
   *
   * @param arr 数组
   * @param k   第k大
   * @return 第k大的元素
   */
  public static int findKthLargestWithHeap(int[] arr, int k) {
    // 使用最小堆维护k个最大元素
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int num : arr) {
      minHeap.offer(num);
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }

    return minHeap.peek();
  }

  /**
   * 使用排序的方法找第k大元素 时间复杂度：O(n log n)，空间复杂度：O(1)
   *
   * @param arr 数组
   * @param k   第k大
   * @return 第k大的元素
   */
  public static int findKthLargestWithSort(int[] arr, int k) {
    Arrays.sort(arr);
    return arr[arr.length - k];
  }

  /**
   * 找到数组中的中位数
   *
   * @param arr 数组
   * @return 中位数
   */
  public static double findMedian(int[] arr) {
    int n = arr.length;
    if (n % 2 == 1) {
      // 奇数个元素，返回中间元素
      return findKthSmallest(arr.clone(), (n + 1) / 2);
    } else {
      // 偶数个元素，返回中间两个元素的平均值
      int[] copy = arr.clone();
      int mid1 = findKthSmallest(copy, n / 2);
      int mid2 = findKthSmallest(copy, n / 2 + 1);
      return (mid1 + mid2) / 2.0;
    }
  }

  /**
   * 找到数组中前k大的元素
   *
   * @param arr 数组
   * @param k   前k大
   * @return 前k大元素的数组
   */
  public static int[] findTopK(int[] arr, int k) {
    if (k <= 0 || k > arr.length) {
      throw new IllegalArgumentException("k必须在1到" + arr.length + "之间");
    }

    int[] copy = arr.clone();

    // 找到第k大的元素
    int kthLargest = findKthLargest(copy, k);

    // 收集所有大于等于第k大的元素
    List<Integer> result = new ArrayList<>();
    for (int num : arr) {
      if (num >= kthLargest) {
        result.add(num);
      }
    }

    // 如果有重复元素，可能超过k个，需要截取
    Collections.sort(result, Collections.reverseOrder());
    return result.stream().limit(k).mapToInt(Integer::intValue).toArray();
  }

  /**
   * 交换数组中两个元素
   */
  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /**
   * 打印数组
   */
  public static void printArray(int[] arr, String name) {
    System.out.println(name + ": " + Arrays.toString(arr));
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 快速选择算法测试 ===\n");

    int[] testArray = {3, 2, 1, 5, 6, 4, 7, 8, 9, 10};
    printArray(testArray, "测试数组");

    // 测试第k大元素
    System.out.println("\n1. 第k大元素测试:");
    for (int k = 1; k <= 5; k++) {
      int kthLargest = findKthLargest(testArray.clone(), k);
      System.out.println("第" + k + "大元素: " + kthLargest);
    }

    // 测试第k小元素
    System.out.println("\n2. 第k小元素测试:");
    for (int k = 1; k <= 5; k++) {
      int kthSmallest = findKthSmallest(testArray.clone(), k);
      System.out.println("第" + k + "小元素: " + kthSmallest);
    }

    // 测试中位数
    System.out.println("\n3. 中位数测试:");
    double median = findMedian(testArray);
    System.out.println("中位数: " + median);

    // 测试前k大元素
    System.out.println("\n4. 前k大元素测试:");
    int[] topK = findTopK(testArray, 3);
    printArray(topK, "前3大元素");

    // 性能比较
    System.out.println("\n5. 性能比较测试:");
    int[] largeArray = new int[100000];
    Random random = new Random();
    for (int i = 0; i < largeArray.length; i++) {
      largeArray[i] = random.nextInt(100000);
    }

    int k = 1000;

    // 快速选择
    long start = System.currentTimeMillis();
    int result1 = findKthLargest(largeArray.clone(), k);
    long time1 = System.currentTimeMillis() - start;
    System.out.println("快速选择: 第" + k + "大 = " + result1 + " (耗时: " + time1 + "ms)");

    // 堆方法
    start = System.currentTimeMillis();
    int result2 = findKthLargestWithHeap(largeArray.clone(), k);
    long time2 = System.currentTimeMillis() - start;
    System.out.println("堆方法: 第" + k + "大 = " + result2 + " (耗时: " + time2 + "ms)");

    // 排序方法
    start = System.currentTimeMillis();
    int result3 = findKthLargestWithSort(largeArray.clone(), k);
    long time3 = System.currentTimeMillis() - start;
    System.out.println("排序方法: 第" + k + "大 = " + result3 + " (耗时: " + time3 + "ms)");

    // 验证结果一致性
    System.out.println("\n结果一致性: " + (result1 == result2 && result2 == result3));
  }
}