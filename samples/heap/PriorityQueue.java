package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

/**
 * 优先级队列实现 基于堆实现，支持自定义比较器 默认为小顶堆（最小优先级队列）
 */
public class PriorityQueue<T> {

  private List<T> heap;                   // 存储元素的堆
  private int size;                       // 队列大小
  private Comparator<T> comparator;       // 比较器

  /**
   * 构造函数 - 使用自然排序（要求T实现Comparable接口）
   */
  @SuppressWarnings("unchecked")
  public PriorityQueue() {
    this.heap = new ArrayList<>();
    this.size = 0;
    this.comparator = (a, b) -> ((Comparable<T>) a).compareTo(b);
  }

  /**
   * 构造函数 - 使用自定义比较器
   *
   * @param comparator 比较器
   */
  public PriorityQueue(Comparator<T> comparator) {
    this.heap = new ArrayList<>();
    this.size = 0;
    this.comparator = comparator;
  }

  /**
   * 构造函数 - 指定初始容量
   *
   * @param initialCapacity 初始容量
   */
  @SuppressWarnings("unchecked")
  public PriorityQueue(int initialCapacity) {
    this.heap = new ArrayList<>(initialCapacity);
    this.size = 0;
    this.comparator = (a, b) -> ((Comparable<T>) a).compareTo(b);
  }

  /**
   * 构造函数 - 指定初始容量和比较器
   *
   * @param initialCapacity 初始容量
   * @param comparator      比较器
   */
  public PriorityQueue(int initialCapacity, Comparator<T> comparator) {
    this.heap = new ArrayList<>(initialCapacity);
    this.size = 0;
    this.comparator = comparator;
  }

  /**
   * 构造函数 - 从集合创建优先级队列
   *
   * @param collection 初始集合
   */
  @SuppressWarnings("unchecked")
  public PriorityQueue(Collection<T> collection) {
    this.heap = new ArrayList<>(collection);
    this.size = collection.size();
    this.comparator = (a, b) -> ((Comparable<T>) a).compareTo(b);
    buildHeap();
  }

  /**
   * 构造函数 - 从集合创建优先级队列，使用自定义比较器
   *
   * @param collection 初始集合
   * @param comparator 比较器
   */
  public PriorityQueue(Collection<T> collection, Comparator<T> comparator) {
    this.heap = new ArrayList<>(collection);
    this.size = collection.size();
    this.comparator = comparator;
    buildHeap();
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 优先级队列测试 ===\n");

    // 测试1：基本操作（整数）
    System.out.println("1. 基本操作测试（整数）:");
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int[] values = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    System.out.println("插入元素: " + Arrays.toString(values));

    for (int value : values) {
      pq.offer(value);
      System.out.println("插入 " + value + " 后队首: " + pq.peek());
    }

    System.out.println("\n按优先级出队:");
    while (!pq.isEmpty()) {
      System.out.print(pq.poll() + " ");
    }
    System.out.println();

    // 测试2：自定义比较器（大顶堆）
    System.out.println("\n2. 自定义比较器测试（大顶堆）:");
    PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b.compareTo(a));

    for (int value : values) {
      maxPQ.offer(value);
    }

    System.out.println("大顶堆出队:");
    while (!maxPQ.isEmpty()) {
      System.out.print(maxPQ.poll() + " ");
    }
    System.out.println();

    // 测试3：任务调度（优先级队列的典型应用）
    System.out.println("\n3. 任务调度测试:");
    PriorityQueue<Task> taskQueue = new PriorityQueue<>();

    taskQueue.offer(new Task("低优先级任务", 5));
    taskQueue.offer(new Task("紧急任务", 1));
    taskQueue.offer(new Task("普通任务", 3));
    taskQueue.offer(new Task("重要任务", 2));
    taskQueue.offer(new Task("后台任务", 4));

    System.out.println("任务执行顺序:");
    while (!taskQueue.isEmpty()) {
      Task task = taskQueue.poll();
      System.out.println("执行: " + task);
    }

    // 测试4：从集合创建
    System.out.println("\n4. 从集合创建测试:");
    List<Integer> list = Arrays.asList(4, 1, 3, 2, 16, 9, 10, 14, 8, 7);
    PriorityQueue<Integer> pqFromList = new PriorityQueue<>(list);

    System.out.println("原列表: " + list);
    System.out.println("优先级队列: " + pqFromList);
    System.out.println("队首元素: " + pqFromList.peek());

    // 测试5：删除操作
    System.out.println("\n5. 删除操作测试:");
    PriorityQueue<Integer> pqForRemove = new PriorityQueue<>(list);

    System.out.println("删除前: " + pqForRemove);
    System.out.println("删除元素3: " + pqForRemove.remove(3));
    System.out.println("删除后: " + pqForRemove);
    System.out.println("是否包含3: " + pqForRemove.contains(3));

    // 测试6：性能测试
    System.out.println("\n6. 性能测试:");
    PriorityQueue<Integer> perfPQ = new PriorityQueue<>();

    long startTime = System.currentTimeMillis();

    // 插入100000个随机数
    Random random = new Random();
    for (int i = 0; i < 100000; i++) {
      perfPQ.offer(random.nextInt(1000000));
    }

    // 删除50000个最小值
    for (int i = 0; i < 50000; i++) {
      perfPQ.poll();
    }

    long endTime = System.currentTimeMillis();

    System.out.println("插入100000个元素并删除50000个最小值耗时: " + (endTime - startTime) + "ms");
    System.out.println("剩余元素数量: " + perfPQ.size());

    // 测试7：迭代器
    System.out.println("\n7. 迭代器测试:");
    PriorityQueue<Integer> pqForIter = new PriorityQueue<>(Arrays.asList(5, 2, 8, 1, 9));

    System.out.println("使用迭代器遍历（注意：不保证有序）:");
    Iterator<Integer> iter = pqForIter.iterator();
    while (iter.hasNext()) {
      System.out.print(iter.next() + " ");
    }
    System.out.println();

    // 测试8：边界情况
    System.out.println("\n8. 边界情况测试:");
    PriorityQueue<Integer> emptyPQ = new PriorityQueue<>();

    System.out.println("空队列大小: " + emptyPQ.size());
    System.out.println("空队列是否为空: " + emptyPQ.isEmpty());
    System.out.println("空队列peek: " + emptyPQ.peek());
    System.out.println("空队列poll: " + emptyPQ.poll());

    try {
      emptyPQ.element();
    } catch (NoSuchElementException e) {
      System.out.println("空队列element异常: " + e.getMessage());
    }

    try {
      emptyPQ.remove();
    } catch (NoSuchElementException e) {
      System.out.println("空队列remove异常: " + e.getMessage());
    }
  }

  /**
   * 从集合构建堆
   */
  private void buildHeap() {
    // 从最后一个非叶子节点开始向上调整
    for (int i = (size - 2) / 2; i >= 0; i--) {
      heapifyDown(i);
    }
  }

  /**
   * 入队（插入元素） 时间复杂度：O(log n)
   *
   * @param element 要插入的元素
   * @return true（总是成功）
   */
  public boolean offer(T element) {
    heap.add(element);
    size++;
    heapifyUp(size - 1);
    return true;
  }

  /**
   * 入队（插入元素）- 别名方法
   *
   * @param element 要插入的元素
   * @return true（总是成功）
   */
  public boolean add(T element) {
    return offer(element);
  }

  /**
   * 出队（删除并返回队首元素） 时间复杂度：O(log n)
   *
   * @return 队首元素，如果队列为空返回null
   */
  public T poll() {
    if (isEmpty()) {
      return null;
    }

    T result = heap.get(0);

    // 将最后一个元素移到堆顶
    heap.set(0, heap.get(size - 1));
    heap.remove(size - 1);
    size--;

    // 如果队列不为空，向下调整
    if (size > 0) {
      heapifyDown(0);
    }

    return result;
  }

  /**
   * 出队（删除并返回队首元素）- 抛出异常版本
   *
   * @return 队首元素
   * @throws NoSuchElementException 如果队列为空
   */
  public T remove() {
    T result = poll();
    if (result == null) {
      throw new NoSuchElementException("队列为空");
    }
    return result;
  }

  /**
   * 查看队首元素（不删除） 时间复杂度：O(1)
   *
   * @return 队首元素，如果队列为空返回null
   */
  public T peek() {
    return isEmpty() ? null : heap.get(0);
  }

  /**
   * 查看队首元素（不删除）- 抛出异常版本
   *
   * @return 队首元素
   * @throws NoSuchElementException 如果队列为空
   */
  public T element() {
    T result = peek();
    if (result == null) {
      throw new NoSuchElementException("队列为空");
    }
    return result;
  }

  /**
   * 删除指定元素 时间复杂度：O(n)
   *
   * @param element 要删除的元素
   * @return true如果删除成功，false如果元素不存在
   */
  public boolean remove(T element) {
    int index = indexOf(element);
    if (index == -1) {
      return false;
    }

    removeAt(index);
    return true;
  }

  /**
   * 删除指定索引的元素
   *
   * @param index 要删除的索引
   * @return 被删除的元素
   */
  private T removeAt(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("索引超出范围");
    }

    T removed = heap.get(index);

    // 将最后一个元素移到要删除的位置
    heap.set(index, heap.get(size - 1));
    heap.remove(size - 1);
    size--;

    if (index < size) {
      // 尝试向上和向下调整
      T moved = heap.get(index);
      heapifyUp(index);

      // 如果向上调整没有改变位置，尝试向下调整
      if (heap.get(index) == moved) {
        heapifyDown(index);
      }
    }

    return removed;
  }

  /**
   * 查找元素的索引
   *
   * @param element 要查找的元素
   * @return 元素索引，如果不存在返回-1
   */
  private int indexOf(T element) {
    for (int i = 0; i < size; i++) {
      if (Objects.equals(heap.get(i), element)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 检查是否包含指定元素 时间复杂度：O(n)
   *
   * @param element 要检查的元素
   * @return true如果包含，否则false
   */
  public boolean contains(T element) {
    return indexOf(element) != -1;
  }

  /**
   * 向上调整（上浮）
   *
   * @param index 要调整的元素索引
   */
  private void heapifyUp(int index) {
    while (index > 0) {
      int parentIndex = (index - 1) / 2;

      // 如果当前元素不小于父元素，堆性质满足
      if (comparator.compare(heap.get(index), heap.get(parentIndex)) >= 0) {
        break;
      }

      // 交换当前元素与父元素
      swap(index, parentIndex);
      index = parentIndex;
    }
  }

  /**
   * 向下调整（下沉）
   *
   * @param index 要调整的元素索引
   */
  private void heapifyDown(int index) {
    while (true) {
      int leftChild = 2 * index + 1;
      int rightChild = 2 * index + 2;
      int smallest = index;

      // 找到当前节点和其子节点中的最小值
      if (leftChild < size &&
          comparator.compare(heap.get(leftChild), heap.get(smallest)) < 0) {
        smallest = leftChild;
      }

      if (rightChild < size &&
          comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0) {
        smallest = rightChild;
      }

      // 如果当前节点就是最小的，堆性质满足
      if (smallest == index) {
        break;
      }

      // 交换当前节点与最小子节点
      swap(index, smallest);
      index = smallest;
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
   * 获取队列大小
   *
   * @return 队列中元素个数
   */
  public int size() {
    return size;
  }

  /**
   * 判断队列是否为空
   *
   * @return true如果为空，否则false
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 清空队列
   */
  public void clear() {
    heap.clear();
    size = 0;
  }

  /**
   * 转换为数组
   *
   * @return 包含所有元素的数组
   */
  public Object[] toArray() {
    return heap.subList(0, size).toArray();
  }

  /**
   * 转换为指定类型的数组
   *
   * @param array 目标数组
   * @return 包含所有元素的数组
   */
  @SuppressWarnings("unchecked")
  public <U> U[] toArray(U[] array) {
    return (U[]) heap.subList(0, size).toArray(array);
  }

  /**
   * 获取迭代器
   *
   * @return 迭代器
   */
  public Iterator<T> iterator() {
    return new PriorityQueueIterator();
  }

  /**
   * 转换为字符串表示
   */
  @Override
  public String toString() {
    return "PriorityQueue" + heap.subList(0, size).toString();
  }

  /**
   * 任务类 - 用于测试优先级队列
   */
  public static class Task implements Comparable<Task> {

    private String name;
    private int priority;

    public Task(String name, int priority) {
      this.name = name;
      this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
      // 优先级越小，优先级越高
      return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
      return name + "(优先级:" + priority + ")";
    }

    public String getName() {
      return name;
    }

    public int getPriority() {
      return priority;
    }
  }

  /**
   * 优先级队列迭代器
   */
  private class PriorityQueueIterator implements Iterator<T> {

    private int index = 0;

    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return heap.get(index++);
    }

    @Override
    public void remove() {
      if (index <= 0) {
        throw new IllegalStateException();
      }
      removeAt(--index);
    }
  }
}