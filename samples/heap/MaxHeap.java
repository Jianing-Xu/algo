package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 大顶堆实现 基于数组实现的完全二叉树结构 父节点的值大于等于子节点的值
 */
public class MaxHeap<T extends Comparable<T>> {

  private List<T> heap;           // 存储堆元素的动态数组
  private int size;               // 堆中元素个数

  /**
   * 构造函数 - 创建空堆
   */
  public MaxHeap() {
    this.heap = new ArrayList<>();
    this.size = 0;
  }

  /**
   * 构造函数 - 从数组创建堆
   *
   * @param array 初始数组
   */
  public MaxHeap(T[] array) {
    this.heap = new ArrayList<>(Arrays.asList(array));
    this.size = array.length;
    buildHeap();
  }

  /**
   * 构造函数 - 从列表创建堆
   *
   * @param list 初始列表
   */
  public MaxHeap(List<T> list) {
    this.heap = new ArrayList<>(list);
    this.size = list.size();
    buildHeap();
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 大顶堆测试 ===\n");

    // 测试1：基本操作
    System.out.println("1. 基本操作测试:");
    MaxHeap<Integer> heap = new MaxHeap<>();

    // 插入元素
    int[] values = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    System.out.println("插入元素: " + Arrays.toString(values));

    for (int value : values) {
      heap.insert(value);
      System.out.println("插入 " + value + " 后: " + heap.toList());
    }

    System.out.println("\n堆结构:");
    heap.printHeap();
    System.out.println("是否为有效堆: " + heap.isValidHeap());

    // 测试2：删除操作
    System.out.println("\n2. 删除操作测试:");
    System.out.println("堆顶元素: " + heap.peek());

    while (!heap.isEmpty()) {
      int max = heap.extractMax();
      System.out.println("删除最大值 " + max + ", 剩余: " + heap.toList());
    }

    // 测试3：从数组构建堆
    System.out.println("\n3. 从数组构建堆测试:");
    Integer[] array = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    MaxHeap<Integer> heapFromArray = new MaxHeap<>(array);

    System.out.println("原数组: " + Arrays.toString(array));
    System.out.println("构建的堆: " + heapFromArray.toList());
    System.out.println("是否为有效堆: " + heapFromArray.isValidHeap());

    heapFromArray.printHeap();

    // 测试4：删除指定元素
    System.out.println("\n4. 删除指定元素测试:");
    System.out.println("删除索引2的元素: " + heapFromArray.delete(2));
    System.out.println("删除后的堆: " + heapFromArray.toList());
    System.out.println("是否为有效堆: " + heapFromArray.isValidHeap());

    // 测试5：性能测试
    System.out.println("\n5. 性能测试:");
    MaxHeap<Integer> perfHeap = new MaxHeap<>();

    long startTime = System.currentTimeMillis();

    // 插入10000个随机数
    Random random = new Random();
    for (int i = 0; i < 10000; i++) {
      perfHeap.insert(random.nextInt(50000));
    }

    // 删除5000个最大值
    for (int i = 0; i < 5000; i++) {
      perfHeap.extractMax();
    }

    long endTime = System.currentTimeMillis();

    System.out.println("插入10000个元素并删除5000个最大值耗时: " + (endTime - startTime) + "ms");
    System.out.println("剩余元素数量: " + perfHeap.size());
    System.out.println("是否为有效堆: " + perfHeap.isValidHeap());
  }

  /**
   * 从数组构建堆（堆化过程） 从最后一个非叶子节点开始向上调整 时间复杂度：O(n)
   */
  private void buildHeap() {
    // 最后一个非叶子节点的索引
    int lastNonLeafIndex = (size - 2) / 2;

    // 从下往上进行堆化
    for (int i = lastNonLeafIndex; i >= 0; i--) {
      heapifyDown(i);
    }
  }

  /**
   * 插入元素 将元素添加到堆底，然后向上调整 时间复杂度：O(log n)
   *
   * @param element 要插入的元素
   */
  public void insert(T element) {
    heap.add(element);
    size++;
    heapifyUp(size - 1);
  }

  /**
   * 删除并返回堆顶元素（最大值） 将堆底元素移到堆顶，然后向下调整 时间复杂度：O(log n)
   *
   * @return 堆顶元素
   * @throws IllegalStateException 如果堆为空
   */
  public T extractMax() {
    if (isEmpty()) {
      throw new IllegalStateException("堆为空，无法删除元素");
    }

    T max = heap.get(0);

    // 将最后一个元素移到堆顶
    heap.set(0, heap.get(size - 1));
    heap.remove(size - 1);
    size--;

    // 如果堆不为空，向下调整
    if (size > 0) {
      heapifyDown(0);
    }

    return max;
  }

  /**
   * 查看堆顶元素（不删除） 时间复杂度：O(1)
   *
   * @return 堆顶元素
   * @throws IllegalStateException 如果堆为空
   */
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("堆为空");
    }
    return heap.get(0);
  }

  /**
   * 删除指定索引的元素 时间复杂度：O(log n)
   *
   * @param index 要删除的元素索引
   * @return 被删除的元素
   */
  public T delete(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("索引超出范围");
    }

    T deleted = heap.get(index);

    // 将最后一个元素移到要删除的位置
    heap.set(index, heap.get(size - 1));
    heap.remove(size - 1);
    size--;

    if (index < size) {
      // 尝试向上和向下调整
      T current = heap.get(index);
      heapifyUp(index);

      // 如果向上调整没有改变位置，尝试向下调整
      if (heap.get(index).equals(current)) {
        heapifyDown(index);
      }
    }

    return deleted;
  }

  /**
   * 向上调整（上浮） 将指定位置的元素与其父节点比较，如果大于父节点则交换
   *
   * @param index 要调整的元素索引
   */
  private void heapifyUp(int index) {
    while (index > 0) {
      int parentIndex = (index - 1) / 2;

      // 如果当前元素小于等于父元素，堆性质满足
      if (heap.get(index).compareTo(heap.get(parentIndex)) <= 0) {
        break;
      }

      // 交换当前元素与父元素
      swap(index, parentIndex);
      index = parentIndex;
    }
  }

  /**
   * 向下调整（下沉） 将指定位置的元素与其子节点比较，与较大的子节点交换
   *
   * @param index 要调整的元素索引
   */
  private void heapifyDown(int index) {
    while (true) {
      int leftChild = 2 * index + 1;
      int rightChild = 2 * index + 2;
      int largest = index;

      // 找到当前节点和其子节点中的最大值
      if (leftChild < size && heap.get(leftChild).compareTo(heap.get(largest)) > 0) {
        largest = leftChild;
      }

      if (rightChild < size && heap.get(rightChild).compareTo(heap.get(largest)) > 0) {
        largest = rightChild;
      }

      // 如果当前节点就是最大的，堆性质满足
      if (largest == index) {
        break;
      }

      // 交换当前节点与最大子节点
      swap(index, largest);
      index = largest;
    }
  }

  /**
   * 交换两个位置的元素
   *
   * @param i 第一个位置
   * @param j 第二个位置
   */
  private void swap(int i, int j) {
    T temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

  /**
   * 获取堆的大小
   *
   * @return 堆中元素个数
   */
  public int size() {
    return size;
  }

  /**
   * 判断堆是否为空
   *
   * @return true如果为空，否则false
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 清空堆
   */
  public void clear() {
    heap.clear();
    size = 0;
  }

  /**
   * 获取堆的数组表示
   *
   * @return 堆的数组副本
   */
  public List<T> toList() {
    return new ArrayList<>(heap.subList(0, size));
  }

  /**
   * 验证堆的性质
   *
   * @return true如果满足大顶堆性质，否则false
   */
  public boolean isValidHeap() {
    for (int i = 0; i < size; i++) {
      int leftChild = 2 * i + 1;
      int rightChild = 2 * i + 2;

      // 检查左子节点
      if (leftChild < size && heap.get(i).compareTo(heap.get(leftChild)) < 0) {
        return false;
      }

      // 检查右子节点
      if (rightChild < size && heap.get(i).compareTo(heap.get(rightChild)) < 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * 打印堆的结构
   */
  public void printHeap() {
    if (isEmpty()) {
      System.out.println("堆为空");
      return;
    }

    System.out.println("堆结构（数组表示）: " + toList());
    System.out.println("堆结构（树形表示）:");
    printHeapTree(0, "", true);
  }

  /**
   * 打印堆的树形结构
   */
  private void printHeapTree(int index, String prefix, boolean isLast) {
    if (index < size) {
      System.out.println(prefix + (isLast ? "└── " : "├── ") + heap.get(index));

      int leftChild = 2 * index + 1;
      int rightChild = 2 * index + 2;

      if (leftChild < size || rightChild < size) {
        if (leftChild < size) {
          printHeapTree(leftChild, prefix + (isLast ? "    " : "│   "), rightChild >= size);
        }
        if (rightChild < size) {
          printHeapTree(rightChild, prefix + (isLast ? "    " : "│   "), true);
        }
      }
    }
  }

  /**
   * 转换为字符串表示
   */
  @Override
  public String toString() {
    return "MaxHeap" + toList().toString();
  }
}