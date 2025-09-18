package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * 堆排序算法实现 利用堆的性质进行排序，时间复杂度O(n log n)，空间复杂度O(1)
 */
public class HeapSort {

  /**
   * 堆排序 - 升序排列 使用大顶堆实现升序排序 时间复杂度：O(n log n)，空间复杂度：O(1)
   *
   * @param array 要排序的数组
   */
  public static void heapSortAscending(int[] array) {
    if (array == null || array.length <= 1) {
      return;
    }

    int n = array.length;

    // 1. 构建大顶堆（从最后一个非叶子节点开始向上调整）
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapifyDown(array, n, i);
    }

    // 2. 逐个取出堆顶元素（最大值），放到数组末尾
    for (int i = n - 1; i > 0; i--) {
      // 将堆顶（最大值）与当前未排序部分的最后一个元素交换
      swap(array, 0, i);

      // 重新调整堆（堆大小减1）
      heapifyDown(array, i, 0);
    }
  }

  /**
   * 堆排序 - 降序排列 使用小顶堆实现降序排序
   *
   * @param array 要排序的数组
   */
  public static void heapSortDescending(int[] array) {
    if (array == null || array.length <= 1) {
      return;
    }

    int n = array.length;

    // 1. 构建小顶堆
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapifyDownMin(array, n, i);
    }

    // 2. 逐个取出堆顶元素（最小值），放到数组末尾
    for (int i = n - 1; i > 0; i--) {
      swap(array, 0, i);
      heapifyDownMin(array, i, 0);
    }
  }

  /**
   * 向下调整（大顶堆）
   *
   * @param array     数组
   * @param heapSize  堆的大小
   * @param rootIndex 要调整的根节点索引
   */
  private static void heapifyDown(int[] array, int heapSize, int rootIndex) {
    int largest = rootIndex;        // 假设根节点最大
    int leftChild = 2 * rootIndex + 1;   // 左子节点
    int rightChild = 2 * rootIndex + 2;  // 右子节点

    // 找到根节点和其子节点中的最大值
    if (leftChild < heapSize && array[leftChild] > array[largest]) {
      largest = leftChild;
    }

    if (rightChild < heapSize && array[rightChild] > array[largest]) {
      largest = rightChild;
    }

    // 如果最大值不是根节点，交换并继续调整
    if (largest != rootIndex) {
      swap(array, rootIndex, largest);
      heapifyDown(array, heapSize, largest);
    }
  }

  /**
   * 向下调整（小顶堆）
   *
   * @param array     数组
   * @param heapSize  堆的大小
   * @param rootIndex 要调整的根节点索引
   */
  private static void heapifyDownMin(int[] array, int heapSize, int rootIndex) {
    int smallest = rootIndex;
    int leftChild = 2 * rootIndex + 1;
    int rightChild = 2 * rootIndex + 2;

    // 找到根节点和其子节点中的最小值
    if (leftChild < heapSize && array[leftChild] < array[smallest]) {
      smallest = leftChild;
    }

    if (rightChild < heapSize && array[rightChild] < array[smallest]) {
      smallest = rightChild;
    }

    // 如果最小值不是根节点，交换并继续调整
    if (smallest != rootIndex) {
      swap(array, rootIndex, smallest);
      heapifyDownMin(array, heapSize, smallest);
    }
  }

  /**
   * 交换数组中两个位置的元素
   *
   * @param array 数组
   * @param i     第一个位置
   * @param j     第二个位置
   */
  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * 泛型版本的堆排序 - 升序
   *
   * @param array      要排序的数组
   * @param comparator 比较器
   */
  public static <T> void heapSort(T[] array, Comparator<T> comparator) {
    if (array == null || array.length <= 1) {
      return;
    }

    int n = array.length;

    // 构建大顶堆
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapifyDown(array, n, i, comparator);
    }

    // 排序
    for (int i = n - 1; i > 0; i--) {
      swap(array, 0, i);
      heapifyDown(array, i, 0, comparator);
    }
  }

  /**
   * 泛型版本的向下调整
   */
  private static <T> void heapifyDown(T[] array, int heapSize, int rootIndex,
      Comparator<T> comparator) {
    int largest = rootIndex;
    int leftChild = 2 * rootIndex + 1;
    int rightChild = 2 * rootIndex + 2;

    if (leftChild < heapSize && comparator.compare(array[leftChild], array[largest]) > 0) {
      largest = leftChild;
    }

    if (rightChild < heapSize && comparator.compare(array[rightChild], array[largest]) > 0) {
      largest = rightChild;
    }

    if (largest != rootIndex) {
      swap(array, rootIndex, largest);
      heapifyDown(array, heapSize, largest, comparator);
    }
  }

  /**
   * 泛型版本的交换方法
   */
  private static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * 使用堆排序对列表进行排序
   *
   * @param list       要排序的列表
   * @param comparator 比较器
   */
  @SuppressWarnings("unchecked")
  public static <T> void heapSort(List<T> list, Comparator<T> comparator) {
    T[] array = (T[]) list.toArray();
    heapSort(array, comparator);

    // 将排序结果复制回列表
    for (int i = 0; i < array.length; i++) {
      list.set(i, array[i]);
    }
  }

  /**
   * 验证数组是否已排序（升序）
   *
   * @param array 要验证的数组
   * @return true如果已排序，否则false
   */
  public static boolean isSortedAscending(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i] < array[i - 1]) {
        return false;
      }
    }
    return true;
  }

  /**
   * 验证数组是否已排序（降序）
   *
   * @param array 要验证的数组
   * @return true如果已排序，否则false
   */
  public static boolean isSortedDescending(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i] > array[i - 1]) {
        return false;
      }
    }
    return true;
  }

  /**
   * 打印数组
   *
   * @param array 要打印的数组
   * @param title 标题
   */
  public static void printArray(int[] array, String title) {
    System.out.println(title + ": " + Arrays.toString(array));
  }

  /**
   * 生成随机数组
   *
   * @param size     数组大小
   * @param maxValue 最大值
   * @return 随机数组
   */
  public static int[] generateRandomArray(int size, int maxValue) {
    Random random = new Random();
    int[] array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = random.nextInt(maxValue);
    }
    return array;
  }

  /**
   * 性能测试
   *
   * @param array        测试数组
   * @param sortMethod   排序方法名称
   * @param sortFunction 排序函数
   */
  public static void performanceTest(int[] array, String sortMethod, Runnable sortFunction) {
    long startTime = System.nanoTime();
    sortFunction.run();
    long endTime = System.nanoTime();

    double timeMs = (endTime - startTime) / 1_000_000.0;
    System.out.printf("%s: 数组大小=%d, 耗时=%.3fms%n", sortMethod, array.length, timeMs);
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 堆排序算法测试 ===\n");

    // 测试1：基本堆排序（升序）
    System.out.println("1. 基本堆排序测试（升序）:");
    int[] array1 = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    printArray(array1, "排序前");

    heapSortAscending(array1);
    printArray(array1, "排序后");
    System.out.println("是否已排序: " + isSortedAscending(array1));

    // 测试2：堆排序（降序）
    System.out.println("\n2. 堆排序测试（降序）:");
    int[] array2 = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    printArray(array2, "排序前");

    heapSortDescending(array2);
    printArray(array2, "排序后");
    System.out.println("是否已排序: " + isSortedDescending(array2));

    // 测试3：泛型堆排序
    System.out.println("\n3. 泛型堆排序测试:");
    String[] words = {"banana", "apple", "cherry", "date", "elderberry"};
    System.out.println("排序前: " + Arrays.toString(words));

    heapSort(words, String::compareTo);
    System.out.println("排序后: " + Arrays.toString(words));

    // 按长度排序
    String[] words2 = {"banana", "apple", "cherry", "date", "elderberry"};
    heapSort(words2, Comparator.comparing(String::length));
    System.out.println("按长度排序: " + Arrays.toString(words2));

    // 测试4：列表排序
    System.out.println("\n4. 列表排序测试:");
    List<Integer> list = new ArrayList<>(Arrays.asList(4, 1, 3, 2, 16, 9, 10, 14, 8, 7));
    System.out.println("排序前: " + list);

    heapSort(list, Integer::compareTo);
    System.out.println("排序后: " + list);

    // 测试5：边界情况
    System.out.println("\n5. 边界情况测试:");

    // 空数组
    int[] emptyArray = {};
    heapSortAscending(emptyArray);
    System.out.println("空数组排序: " + Arrays.toString(emptyArray));

    // 单元素数组
    int[] singleArray = {42};
    heapSortAscending(singleArray);
    System.out.println("单元素数组排序: " + Arrays.toString(singleArray));

    // 两元素数组
    int[] twoArray = {2, 1};
    printArray(twoArray, "两元素数组排序前");
    heapSortAscending(twoArray);
    printArray(twoArray, "两元素数组排序后");

    // 已排序数组
    int[] sortedArray = {1, 2, 3, 4, 5};
    printArray(sortedArray, "已排序数组排序前");
    heapSortAscending(sortedArray);
    printArray(sortedArray, "已排序数组排序后");

    // 逆序数组
    int[] reverseArray = {5, 4, 3, 2, 1};
    printArray(reverseArray, "逆序数组排序前");
    heapSortAscending(reverseArray);
    printArray(reverseArray, "逆序数组排序后");

    // 重复元素数组
    int[] duplicateArray = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
    printArray(duplicateArray, "重复元素数组排序前");
    heapSortAscending(duplicateArray);
    printArray(duplicateArray, "重复元素数组排序后");

    // 测试6：性能测试
    System.out.println("\n6. 性能测试:");

    // 测试不同大小的数组
    int[] sizes = {1000, 5000, 10000, 50000};

    for (int size : sizes) {
      int[] testArray = generateRandomArray(size, 100000);
      int[] copyArray = Arrays.copyOf(testArray, testArray.length);

      performanceTest(testArray, "堆排序(大小" + size + ")",
          () -> heapSortAscending(testArray));

      // 验证排序结果
      System.out.println("  排序正确性: " + isSortedAscending(testArray));

      // 与Arrays.sort比较
      performanceTest(copyArray, "Arrays.sort(大小" + size + ")",
          () -> Arrays.sort(copyArray));

      System.out.println();
    }

    // 测试7：稳定性测试（堆排序是不稳定的）
    System.out.println("7. 稳定性测试:");

    // 创建包含相同值但不同对象的数组
    class Person {

      String name;
      int age;

      Person(String name, int age) {
        this.name = name;
        this.age = age;
      }

      @Override
      public String toString() {
        return name + "(" + age + ")";
      }
    }

    Person[] people = {
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 25),
        new Person("David", 30),
        new Person("Eve", 25)
    };

    System.out.println("排序前: " + Arrays.toString(people));
    heapSort(people, Comparator.comparing(p -> p.age));
    System.out.println("按年龄排序后: " + Arrays.toString(people));
    System.out.println("注意: 堆排序是不稳定的，相同年龄的人的相对顺序可能改变");

    // 测试8：大数据量测试
    System.out.println("\n8. 大数据量测试:");
    int largeSize = 100000;
    int[] largeArray = generateRandomArray(largeSize, 1000000);

    System.out.println("测试数组大小: " + largeSize);
    long startTime = System.currentTimeMillis();
    heapSortAscending(largeArray);
    long endTime = System.currentTimeMillis();

    System.out.println("排序耗时: " + (endTime - startTime) + "ms");
    System.out.println("排序正确性: " + isSortedAscending(largeArray));
    System.out.println("最小值: " + largeArray[0]);
    System.out.println("最大值: " + largeArray[largeArray.length - 1]);
  }
}