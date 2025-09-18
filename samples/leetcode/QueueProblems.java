package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * LeetCode队列相关问题解答 包含设计双端队列、滑动窗口最大值等经典题目
 */
public class QueueProblems {

  // ==================== Design Circular Deque（设计双端队列）====================

  /**
   * LeetCode 239. 滑动窗口最大值 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧 你只可以看到在滑动窗口内的 k
   * 个数字。滑动窗口每次只向右移动一位 返回滑动窗口中的最大值
   * <p>
   * 解题思路：单调双端队列 1. 使用双端队列存储数组索引 2. 队列中的索引对应的值保持单调递减 3. 队首始终是当前窗口的最大值索引
   * <p>
   * 时间复杂度：O(n) 空间复杂度：O(k)
   *
   * @param nums 输入数组
   * @param k    窗口大小
   * @return 每个窗口的最大值数组
   */
  public static int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return new int[0];
    }

    int n = nums.length;
    int[] result = new int[n - k + 1];
    Deque<Integer> deque = new ArrayDeque<>(); // 存储索引

    for (int i = 0; i < n; i++) {
      // 移除超出窗口范围的索引
      while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
        deque.pollFirst();
      }

      // 移除队尾所有小于当前元素的索引，保持单调性
      while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
        deque.pollLast();
      }

      deque.offerLast(i);

      // 当窗口大小达到k时，记录最大值
      if (i >= k - 1) {
        result[i - k + 1] = nums[deque.peekFirst()];
      }
    }

    return result;
  }

  // ==================== Sliding Window Maximum（滑动窗口最大值）====================

  /**
   * 滑动窗口最大值的暴力解法（用于验证） 时间复杂度：O(nk) 空间复杂度：O(1)
   *
   * @param nums 输入数组
   * @param k    窗口大小
   * @return 每个窗口的最大值数组
   */
  public static int[] maxSlidingWindowBruteForce(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return new int[0];
    }

    int n = nums.length;
    int[] result = new int[n - k + 1];

    for (int i = 0; i <= n - k; i++) {
      int max = nums[i];
      for (int j = i + 1; j < i + k; j++) {
        max = Math.max(max, nums[j]);
      }
      result[i] = max;
    }

    return result;
  }

  /**
   * 滑动窗口最大值的优先队列解法 时间复杂度：O(n log k) 空间复杂度：O(k)
   *
   * @param nums 输入数组
   * @param k    窗口大小
   * @return 每个窗口的最大值数组
   */
  public static int[] maxSlidingWindowPriorityQueue(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return new int[0];
    }

    int n = nums.length;
    int[] result = new int[n - k + 1];

    // 使用优先队列存储 [值, 索引] 对
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]); // 最大堆

    // 初始化第一个窗口
    for (int i = 0; i < k; i++) {
      pq.offer(new int[]{nums[i], i});
    }
    result[0] = pq.peek()[0];

    // 滑动窗口
    for (int i = k; i < n; i++) {
      pq.offer(new int[]{nums[i], i});

      // 移除超出窗口范围的元素
      while (!pq.isEmpty() && pq.peek()[1] < i - k + 1) {
        pq.poll();
      }

      result[i - k + 1] = pq.peek()[0];
    }

    return result;
  }

  /**
   * 滑动窗口最小值
   *
   * @param nums 输入数组
   * @param k    窗口大小
   * @return 每个窗口的最小值数组
   */
  public static int[] minSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return new int[0];
    }

    int n = nums.length;
    int[] result = new int[n - k + 1];
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      // 移除超出窗口范围的索引
      while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
        deque.pollFirst();
      }

      // 移除队尾所有大于当前元素的索引，保持单调递增
      while (!deque.isEmpty() && nums[deque.peekLast()] >= nums[i]) {
        deque.pollLast();
      }

      deque.offerLast(i);

      if (i >= k - 1) {
        result[i - k + 1] = nums[deque.peekFirst()];
      }
    }

    return result;
  }

  // ==================== 滑动窗口相关的其他问题 ====================

  /**
   * 滑动窗口平均值
   *
   * @param nums 输入数组
   * @param k    窗口大小
   * @return 每个窗口的平均值数组
   */
  public static double[] avgSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return new double[0];
    }

    int n = nums.length;
    double[] result = new double[n - k + 1];

    // 计算第一个窗口的和
    long sum = 0;
    for (int i = 0; i < k; i++) {
      sum += nums[i];
    }
    result[0] = (double) sum / k;

    // 滑动窗口
    for (int i = k; i < n; i++) {
      sum = sum - nums[i - k] + nums[i];
      result[i - k + 1] = (double) sum / k;
    }

    return result;
  }

  /**
   * 生成随机数组（用于测试）
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
   * 打印数组
   *
   * @param array       数组
   * @param maxElements 最大打印元素数
   */
  public static void printArray(int[] array, int maxElements) {
    if (array == null) {
      System.out.println("null");
      return;
    }

    System.out.print("[");
    for (int i = 0; i < Math.min(array.length, maxElements); i++) {
      System.out.print(array[i]);
      if (i < Math.min(array.length, maxElements) - 1) {
        System.out.print(", ");
      }
    }

    if (array.length > maxElements) {
      System.out.print(", ...");
    }

    System.out.println("]");
  }

  /**
   * 验证两个数组是否相等
   *
   * @param arr1 数组1
   * @param arr2 数组2
   * @return 是否相等
   */
  public static boolean arraysEqual(int[] arr1, int[] arr2) {
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1 == null || arr2 == null) {
      return false;
    }
    if (arr1.length != arr2.length) {
      return false;
    }

    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }

    return true;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== LeetCode 队列问题测试 ===\n");

    // 测试1：设计循环双端队列
    System.out.println("1. 设计循环双端队列测试:");

    MyCircularDeque deque = new MyCircularDeque(3);

    System.out.println("初始状态:");
    deque.printStatus();

    System.out.println("\n执行操作序列:");
    System.out.println("insertLast(1): " + deque.insertLast(1));
    deque.printStatus();

    System.out.println("insertLast(2): " + deque.insertLast(2));
    deque.printStatus();

    System.out.println("insertFront(3): " + deque.insertFront(3));
    deque.printStatus();

    System.out.println("insertFront(4): " + deque.insertFront(4)); // 应该失败
    deque.printStatus();

    System.out.println("getRear(): " + deque.getRear());
    System.out.println("isFull(): " + deque.isFull());

    System.out.println("deleteLast(): " + deque.deleteLast());
    deque.printStatus();

    System.out.println("insertFront(4): " + deque.insertFront(4));
    deque.printStatus();

    System.out.println("getFront(): " + deque.getFront());

    // 测试2：滑动窗口最大值
    System.out.println("\n2. 滑动窗口最大值测试:");

    int[][] testCases = {
        {1, 3, -1, -3, 5, 3, 6, 7},
        {1},
        {1, -1},
        {9, 11},
        {4, -2, -3, 11, -1, 2, 6}
    };

    int[] windowSizes = {3, 1, 1, 2, 3};

    for (int i = 0; i < testCases.length; i++) {
      int[] nums = testCases[i];
      int k = windowSizes[i];

      System.out.println("\n输入数组: " + Arrays.toString(nums) + ", k=" + k);

      long startTime = System.nanoTime();
      int[] result1 = maxSlidingWindow(nums, k);
      long time1 = System.nanoTime() - startTime;

      startTime = System.nanoTime();
      int[] result2 = maxSlidingWindowBruteForce(nums, k);
      long time2 = System.nanoTime() - startTime;

      startTime = System.nanoTime();
      int[] result3 = maxSlidingWindowPriorityQueue(nums, k);
      long time3 = System.nanoTime() - startTime;

      System.out.print("单调队列: ");
      printArray(result1, 10);
      System.out.printf("耗时: %.2fμs%n", time1 / 1000.0);

      System.out.print("暴力解法: ");
      printArray(result2, 10);
      System.out.printf("耗时: %.2fμs%n", time2 / 1000.0);

      System.out.print("优先队列: ");
      printArray(result3, 10);
      System.out.printf("耗时: %.2fμs%n", time3 / 1000.0);

      boolean consistent = arraysEqual(result1, result2) && arraysEqual(result2, result3);
      System.out.println("结果一致性: " + consistent);

      if (!consistent) {
        System.out.println("⚠️ 结果不一致！");
      }
    }

    // 测试3：滑动窗口最小值和平均值
    System.out.println("\n3. 滑动窗口最小值和平均值测试:");

    int[] testArray = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;

    System.out.println("输入数组: " + Arrays.toString(testArray) + ", k=" + k);

    int[] maxResult = maxSlidingWindow(testArray, k);
    int[] minResult = minSlidingWindow(testArray, k);
    double[] avgResult = avgSlidingWindow(testArray, k);

    System.out.print("滑动窗口最大值: ");
    printArray(maxResult, 10);

    System.out.print("滑动窗口最小值: ");
    printArray(minResult, 10);

    System.out.print("滑动窗口平均值: [");
    for (int i = 0; i < avgResult.length; i++) {
      System.out.printf("%.2f", avgResult[i]);
      if (i < avgResult.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");

    // 测试4：性能测试
    System.out.println("\n4. 性能测试:");

    // 双端队列性能测试
    System.out.println("双端队列性能测试:");
    for (int capacity : new int[]{100, 1000, 10000}) {
      MyCircularDeque perfDeque = new MyCircularDeque(capacity);

      long startTime = System.currentTimeMillis();

      // 执行大量操作
      for (int i = 0; i < capacity * 2; i++) {
        perfDeque.insertLast(i);
        if (perfDeque.isFull()) {
          perfDeque.deleteFront();
        }
      }

      for (int i = 0; i < capacity; i++) {
        perfDeque.insertFront(i);
        if (perfDeque.isFull()) {
          perfDeque.deleteLast();
        }
      }

      long time = System.currentTimeMillis() - startTime;
      System.out.printf("容量=%d, 操作数=%d, 耗时=%dms%n",
          capacity, capacity * 3, time);
    }

    // 滑动窗口最大值性能测试
    System.out.println("\n滑动窗口最大值性能测试:");
    for (int size : new int[]{1000, 5000, 10000}) {
      int[] perfArray = generateRandomArray(size, -100, 100);
      int perfK = Math.min(100, size / 10);

      long startTime = System.currentTimeMillis();
      int[] result1 = maxSlidingWindow(perfArray, perfK);
      long time1 = System.currentTimeMillis() - startTime;

      startTime = System.currentTimeMillis();
      int[] result2 = maxSlidingWindowPriorityQueue(perfArray, perfK);
      long time2 = System.currentTimeMillis() - startTime;

      // 只在小数组上测试暴力解法
      long time3 = -1;
      if (size <= 1000) {
        startTime = System.currentTimeMillis();
        int[] result3 = maxSlidingWindowBruteForce(perfArray, perfK);
        time3 = System.currentTimeMillis() - startTime;
      }

      System.out.printf("数组大小=%d, k=%d: 单调队列=%dms, 优先队列=%dms",
          size, perfK, time1, time2);

      if (time3 != -1) {
        System.out.printf(", 暴力解法=%dms", time3);
      }
      System.out.println();
    }

    // 测试5：边界情况
    System.out.println("\n5. 边界情况测试:");

    // 双端队列边界情况
    System.out.println("双端队列边界情况:");
    MyCircularDeque emptyDeque = new MyCircularDeque(1);
    System.out.println("空队列getFront(): " + emptyDeque.getFront());
    System.out.println("空队列getRear(): " + emptyDeque.getRear());
    System.out.println("空队列deleteFront(): " + emptyDeque.deleteFront());
    System.out.println("空队列deleteLast(): " + emptyDeque.deleteLast());

    // 滑动窗口边界情况
    System.out.println("\n滑动窗口边界情况:");
    System.out.println("空数组: " + Arrays.toString(maxSlidingWindow(new int[]{}, 1)));
    System.out.println("k=1: " + Arrays.toString(maxSlidingWindow(new int[]{1, 2, 3}, 1)));
    System.out.println(
        "k等于数组长度: " + Arrays.toString(maxSlidingWindow(new int[]{1, 2, 3}, 3)));
    System.out.println("单元素数组: " + Arrays.toString(maxSlidingWindow(new int[]{42}, 1)));

    // 测试6：复杂情况
    System.out.println("\n6. 复杂情况测试:");

    // 大容量双端队列
    MyCircularDeque largeDeque = new MyCircularDeque(1000);
    System.out.println("大容量双端队列测试:");

    // 填满队列
    for (int i = 0; i < 1000; i++) {
      largeDeque.insertLast(i);
    }
    System.out.println("填满后状态: size=" + largeDeque.size() + ", isFull=" + largeDeque.isFull());

    // 清空队列
    while (!largeDeque.isEmpty()) {
      largeDeque.deleteFront();
    }
    System.out.println(
        "清空后状态: size=" + largeDeque.size() + ", isEmpty=" + largeDeque.isEmpty());

    // 复杂滑动窗口
    System.out.println("\n复杂滑动窗口测试:");
    int[] complexArray = new int[100];
    for (int i = 0; i < 100; i++) {
      complexArray[i] = (int) (Math.sin(i * 0.1) * 100); // 正弦波数据
    }

    int[] complexMax = maxSlidingWindow(complexArray, 10);
    int[] complexMin = minSlidingWindow(complexArray, 10);

    System.out.println("正弦波数据滑动窗口测试完成");
    System.out.println("最大值数组长度: " + complexMax.length);
    System.out.println("最小值数组长度: " + complexMin.length);
    System.out.println("第一个窗口: max=" + complexMax[0] + ", min=" + complexMin[0]);
    System.out.println("最后一个窗口: max=" + complexMax[complexMax.length - 1] +
        ", min=" + complexMin[complexMin.length - 1]);
  }

  /**
   * LeetCode 641. 设计循环双端队列 设计实现双端队列，支持以下操作： - insertFront(): 将一个元素添加到双端队列头部 - insertLast():
   * 将一个元素添加到双端队列尾部 - deleteFront(): 从双端队列头部删除一个元素 - deleteLast(): 从双端队列尾部删除一个元素 - getFront():
   * 从双端队列头部获得一个元素 - getRear(): 从双端队列尾部获得一个元素 - isEmpty(): 检查双端队列是否为空 - isFull(): 检查双端队列是否满了
   */
  public static class MyCircularDeque {

    private int[] data;
    private int front;  // 指向队首元素
    private int rear;   // 指向队尾元素的下一个位置
    private int size;   // 当前元素个数
    private int capacity;

    /**
     * 初始化双端队列
     *
     * @param k 队列的大小
     */
    public MyCircularDeque(int k) {
      this.capacity = k;
      this.data = new int[k];
      this.front = 0;
      this.rear = 0;
      this.size = 0;
    }

    /**
     * 将一个元素添加到双端队列头部。如果操作成功返回 true
     *
     * @param value 要添加的值
     * @return 是否成功
     */
    public boolean insertFront(int value) {
      if (isFull()) {
        return false;
      }

      front = (front - 1 + capacity) % capacity;
      data[front] = value;
      size++;
      return true;
    }

    /**
     * 将一个元素添加到双端队列尾部。如果操作成功返回 true
     *
     * @param value 要添加的值
     * @return 是否成功
     */
    public boolean insertLast(int value) {
      if (isFull()) {
        return false;
      }

      data[rear] = value;
      rear = (rear + 1) % capacity;
      size++;
      return true;
    }

    /**
     * 从双端队列头部删除一个元素。如果操作成功返回 true
     *
     * @return 是否成功
     */
    public boolean deleteFront() {
      if (isEmpty()) {
        return false;
      }

      front = (front + 1) % capacity;
      size--;
      return true;
    }

    /**
     * 从双端队列尾部删除一个元素。如果操作成功返回 true
     *
     * @return 是否成功
     */
    public boolean deleteLast() {
      if (isEmpty()) {
        return false;
      }

      rear = (rear - 1 + capacity) % capacity;
      size--;
      return true;
    }

    /**
     * 从双端队列头部获得一个元素。如果双端队列为空，返回 -1
     *
     * @return 队首元素
     */
    public int getFront() {
      if (isEmpty()) {
        return -1;
      }
      return data[front];
    }

    /**
     * 从双端队列尾部获得一个元素。如果双端队列为空，返回 -1
     *
     * @return 队尾元素
     */
    public int getRear() {
      if (isEmpty()) {
        return -1;
      }
      return data[(rear - 1 + capacity) % capacity];
    }

    /**
     * 检查双端队列是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
      return size == 0;
    }

    /**
     * 检查双端队列是否满了
     *
     * @return 是否已满
     */
    public boolean isFull() {
      return size == capacity;
    }

    /**
     * 获取当前队列大小
     *
     * @return 当前大小
     */
    public int size() {
      return size;
    }

    /**
     * 打印队列状态（用于调试）
     */
    public void printStatus() {
      System.out.printf("Deque: size=%d, capacity=%d, front=%d, rear=%d, isEmpty=%s, isFull=%s%n",
          size, capacity, front, rear, isEmpty(), isFull());

      if (!isEmpty()) {
        System.out.print("Elements: ");
        for (int i = 0; i < size; i++) {
          int index = (front + i) % capacity;
          System.out.print(data[index] + " ");
        }
        System.out.println();
      }
    }
  }
}