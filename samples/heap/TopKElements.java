package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Top K 元素问题解决方案 包含多种求解Top K问题的算法实现
 */
public class TopKElements {

  /**
   * 使用小顶堆求Top K最大元素 维护一个大小为K的小顶堆，堆顶是第K大的元素 时间复杂度：O(n log k)，空间复杂度：O(k)
   *
   * @param nums 输入数组
   * @param k    要求的元素个数
   * @return Top K最大元素列表
   */
  public static List<Integer> topKLargest(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return new ArrayList<>();
    }

    // 使用小顶堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int num : nums) {
      if (minHeap.size() < k) {
        minHeap.offer(num);
      } else if (num > minHeap.peek()) {
        minHeap.poll();
        minHeap.offer(num);
      }
    }

    // 将堆中元素转换为列表（从小到大）
    List<Integer> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      result.add(minHeap.poll());
    }

    // 反转得到从大到小的顺序
    Collections.reverse(result);
    return result;
  }

  /**
   * 使用大顶堆求Top K最小元素 维护一个大小为K的大顶堆，堆顶是第K小的元素 时间复杂度：O(n log k)，空间复杂度：O(k)
   *
   * @param nums 输入数组
   * @param k    要求的元素个数
   * @return Top K最小元素列表
   */
  public static List<Integer> topKSmallest(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return new ArrayList<>();
    }

    // 使用大顶堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b.compareTo(a));

    for (int num : nums) {
      if (maxHeap.size() < k) {
        maxHeap.offer(num);
      } else if (num < maxHeap.peek()) {
        maxHeap.poll();
        maxHeap.offer(num);
      }
    }

    // 将堆中元素转换为列表（从大到小）
    List<Integer> result = new ArrayList<>();
    while (!maxHeap.isEmpty()) {
      result.add(maxHeap.poll());
    }

    // 反转得到从小到大的顺序
    Collections.reverse(result);
    return result;
  }

  /**
   * 使用快速选择算法求第K大元素 基于快速排序的分区思想 平均时间复杂度：O(n)，最坏时间复杂度：O(n²)
   *
   * @param nums 输入数组
   * @param k    第k大（1-indexed）
   * @return 第k大的元素
   */
  public static int findKthLargest(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
      throw new IllegalArgumentException("无效的输入参数");
    }

    // 第k大元素在排序后数组中的索引是 length - k
    return quickSelect(nums, 0, nums.length - 1, nums.length - k);
  }

  /**
   * 快速选择算法的递归实现
   *
   * @param nums        数组
   * @param left        左边界
   * @param right       右边界
   * @param targetIndex 目标索引
   * @return 目标位置的元素
   */
  private static int quickSelect(int[] nums, int left, int right, int targetIndex) {
    if (left == right) {
      return nums[left];
    }

    // 随机选择基准元素以避免最坏情况
    Random random = new Random();
    int pivotIndex = left + random.nextInt(right - left + 1);

    // 分区操作
    pivotIndex = partition(nums, left, right, pivotIndex);

    if (pivotIndex == targetIndex) {
      return nums[pivotIndex];
    } else if (pivotIndex < targetIndex) {
      return quickSelect(nums, pivotIndex + 1, right, targetIndex);
    } else {
      return quickSelect(nums, left, pivotIndex - 1, targetIndex);
    }
  }

  /**
   * 分区操作
   *
   * @param nums       数组
   * @param left       左边界
   * @param right      右边界
   * @param pivotIndex 基准元素索引
   * @return 基准元素的最终位置
   */
  private static int partition(int[] nums, int left, int right, int pivotIndex) {
    int pivotValue = nums[pivotIndex];

    // 将基准元素移到末尾
    swap(nums, pivotIndex, right);

    int storeIndex = left;
    for (int i = left; i < right; i++) {
      if (nums[i] < pivotValue) {
        swap(nums, i, storeIndex);
        storeIndex++;
      }
    }

    // 将基准元素移到正确位置
    swap(nums, storeIndex, right);
    return storeIndex;
  }

  /**
   * 交换数组中两个位置的元素
   */
  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  /**
   * 合并K个有序数组 使用优先级队列（小顶堆）合并多个有序数组 时间复杂度：O(n log k)，其中n是所有元素总数，k是数组个数
   *
   * @param arrays K个有序数组
   * @return 合并后的有序数组
   */
  public static List<Integer> mergeKSortedArrays(int[][] arrays) {
    List<Integer> result = new ArrayList<>();

    if (arrays == null || arrays.length == 0) {
      return result;
    }

    // 优先级队列存储数组元素信息
    PriorityQueue<ArrayElement> minHeap = new PriorityQueue<>(
        Comparator.comparingInt(a -> a.value)
    );

    // 将每个数组的第一个元素加入堆
    for (int i = 0; i < arrays.length; i++) {
      if (arrays[i].length > 0) {
        minHeap.offer(new ArrayElement(arrays[i][0], i, 0));
      }
    }

    // 依次取出最小元素，并添加该数组的下一个元素
    while (!minHeap.isEmpty()) {
      ArrayElement min = minHeap.poll();
      result.add(min.value);

      // 如果该数组还有下一个元素，加入堆
      if (min.elementIndex + 1 < arrays[min.arrayIndex].length) {
        int nextValue = arrays[min.arrayIndex][min.elementIndex + 1];
        minHeap.offer(new ArrayElement(nextValue, min.arrayIndex, min.elementIndex + 1));
      }
    }

    return result;
  }

  /**
   * 频率Top K问题 找出数组中出现频率最高的K个元素
   *
   * @param nums 输入数组
   * @param k    要求的元素个数
   * @return 频率最高的K个元素
   */
  public static List<Integer> topKFrequent(int[] nums, int k) {
    // 统计频率
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    for (int num : nums) {
      frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    // 使用小顶堆维护频率最高的K个元素
    PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
        Comparator.comparingInt(Map.Entry::getValue)
    );

    for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
      if (minHeap.size() < k) {
        minHeap.offer(entry);
      } else if (entry.getValue() > minHeap.peek().getValue()) {
        minHeap.poll();
        minHeap.offer(entry);
      }
    }

    // 提取结果
    List<Integer> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      result.add(minHeap.poll().getKey());
    }

    Collections.reverse(result);
    return result;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== Top K 元素算法测试 ===\n");

    // 测试数据
    int[] testArray = {3, 2, 1, 5, 6, 4, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    // 测试1：Top K最大元素
    System.out.println("1. Top K最大元素测试:");
    System.out.println("原数组: " + Arrays.toString(testArray));

    for (int k = 1; k <= 5; k++) {
      List<Integer> topK = topKLargest(testArray, k);
      System.out.println("Top " + k + " 最大元素: " + topK);
    }

    // 测试2：Top K最小元素
    System.out.println("\n2. Top K最小元素测试:");
    for (int k = 1; k <= 5; k++) {
      List<Integer> topK = topKSmallest(testArray, k);
      System.out.println("Top " + k + " 最小元素: " + topK);
    }

    // 测试3：第K大元素
    System.out.println("\n3. 第K大元素测试:");
    for (int k = 1; k <= 5; k++) {
      int kthLargest = findKthLargest(testArray.clone(), k);
      System.out.println("第 " + k + " 大元素: " + kthLargest);
    }

    // 测试4：动态Top K跟踪器
    System.out.println("\n4. 动态Top K跟踪器测试:");
    TopKTracker tracker = new TopKTracker(3);

    int[] streamData = {4, 5, 8, 2, 3, 9, 1, 7};
    System.out.println("数据流: " + Arrays.toString(streamData));

    for (int num : streamData) {
      tracker.add(num);
      System.out.println("添加 " + num + " 后，Top 3: " + tracker.getTopK() +
          ", 第3大: " + tracker.getKthLargest());
    }

    // 测试5：合并K个有序数组
    System.out.println("\n5. 合并K个有序数组测试:");
    int[][] sortedArrays = {
        {1, 4, 5},
        {1, 3, 4},
        {2, 6}
    };

    System.out.println("输入数组:");
    for (int i = 0; i < sortedArrays.length; i++) {
      System.out.println("数组" + i + ": " + Arrays.toString(sortedArrays[i]));
    }

    List<Integer> merged = mergeKSortedArrays(sortedArrays);
    System.out.println("合并结果: " + merged);

    // 测试6：频率Top K
    System.out.println("\n6. 频率Top K测试:");
    int[] freqArray = {1, 1, 1, 2, 2, 3, 4, 4, 4, 4};
    System.out.println("原数组: " + Arrays.toString(freqArray));

    for (int k = 1; k <= 3; k++) {
      List<Integer> topKFreq = topKFrequent(freqArray, k);
      System.out.println("频率Top " + k + ": " + topKFreq);
    }

    // 测试7：性能测试
    System.out.println("\n7. 性能测试:");

    // 生成大数组
    Random random = new Random();
    int[] largeArray = new int[100000];
    for (int i = 0; i < largeArray.length; i++) {
      largeArray[i] = random.nextInt(1000000);
    }

    int k = 100;

    // 测试堆方法
    long startTime = System.nanoTime();
    List<Integer> heapResult = topKLargest(largeArray, k);
    long heapTime = System.nanoTime() - startTime;

    // 测试快速选择方法
    startTime = System.nanoTime();
    int quickSelectResult = findKthLargest(largeArray.clone(), k);
    long quickSelectTime = System.nanoTime() - startTime;

    System.out.println("数组大小: " + largeArray.length);
    System.out.println("K值: " + k);
    System.out.println("堆方法耗时: " + (heapTime / 1_000_000.0) + "ms");
    System.out.println("快速选择耗时: " + (quickSelectTime / 1_000_000.0) + "ms");
    System.out.println("堆方法第" + k + "大元素: " + heapResult.get(0));
    System.out.println("快速选择第" + k + "大元素: " + quickSelectResult);

    // 测试8：边界情况
    System.out.println("\n8. 边界情况测试:");

    // 空数组
    int[] emptyArray = {};
    System.out.println("空数组Top 3: " + topKLargest(emptyArray, 3));

    // K大于数组长度
    int[] smallArray = {1, 2};
    System.out.println("小数组Top 5: " + topKLargest(smallArray, 5));

    // K为0
    System.out.println("K=0: " + topKLargest(testArray, 0));

    // 重复元素
    int[] duplicateArray = {1, 1, 1, 2, 2, 2};
    System.out.println("重复元素数组: " + Arrays.toString(duplicateArray));
    System.out.println("Top 3最大: " + topKLargest(duplicateArray, 3));
    System.out.println("第2大元素: " + findKthLargest(duplicateArray.clone(), 2));

    // 测试9：复杂场景
    System.out.println("\n9. 复杂场景测试:");

    // 测试合并大量有序数组
    int[][] manyArrays = new int[10][];
    for (int i = 0; i < 10; i++) {
      manyArrays[i] = new int[100];
      for (int j = 0; j < 100; j++) {
        manyArrays[i][j] = i * 100 + j;
      }
    }

    startTime = System.currentTimeMillis();
    List<Integer> largeMerged = mergeKSortedArrays(manyArrays);
    long mergeTime = System.currentTimeMillis() - startTime;

    System.out.println("合并10个长度100的有序数组耗时: " + mergeTime + "ms");
    System.out.println("合并结果大小: " + largeMerged.size());
    System.out.println("结果是否有序: " + isAscending(largeMerged));

    // 测试动态数据流的极限情况
    TopKTracker largeTracker = new TopKTracker(1000);
    startTime = System.currentTimeMillis();

    for (int i = 0; i < 100000; i++) {
      largeTracker.add(random.nextInt(1000000));
    }

    long trackerTime = System.currentTimeMillis() - startTime;
    System.out.println("动态跟踪器处理100000个元素耗时: " + trackerTime + "ms");
    System.out.println("第1000大元素: " + largeTracker.getKthLargest());
  }

  /**
   * 检查列表是否升序排列
   */
  private static boolean isAscending(List<Integer> list) {
    for (int i = 1; i < list.size(); i++) {
      if (list.get(i) < list.get(i - 1)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 求动态数据流中的Top K最大元素 适用于数据流场景，支持动态添加元素
   */
  public static class TopKTracker {

    private PriorityQueue<Integer> minHeap;
    private int k;

    public TopKTracker(int k) {
      this.k = k;
      this.minHeap = new PriorityQueue<>();
    }

    /**
     * 添加新元素
     *
     * @param num 新元素
     */
    public void add(int num) {
      if (minHeap.size() < k) {
        minHeap.offer(num);
      } else if (num > minHeap.peek()) {
        minHeap.poll();
        minHeap.offer(num);
      }
    }

    /**
     * 获取当前Top K最大元素
     *
     * @return Top K元素列表
     */
    public List<Integer> getTopK() {
      Integer poll = minHeap.poll();
      List<Integer> result = new ArrayList<>();
      while (poll != null) {
        result.add(poll);
        poll = minHeap.poll();
      }
      result.sort(Collections.reverseOrder());
      return result;
    }

    /**
     * 获取第K大元素
     *
     * @return 第K大元素，如果元素不足K个返回null
     */
    public Integer getKthLargest() {
      return minHeap.size() == k ? minHeap.peek() : null;
    }
  }

  /**
   * 数组元素信息类
   */
  private static class ArrayElement {

    int value;          // 元素值
    int arrayIndex;     // 数组索引
    int elementIndex;   // 元素在数组中的索引

    ArrayElement(int value, int arrayIndex, int elementIndex) {
      this.value = value;
      this.arrayIndex = arrayIndex;
      this.elementIndex = elementIndex;
    }
  }
}