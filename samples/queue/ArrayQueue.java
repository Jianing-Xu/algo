package queue;

/**
 * 基于数组实现的顺序队列 支持动态扩容的队列数据结构
 */
public class ArrayQueue<T> {

  private static final int DEFAULT_CAPACITY = 10;  // 默认容量
  private Object[] data;          // 存储队列元素的数组
  private int front;              // 队头指针
  private int rear;               // 队尾指针
  private int size;               // 队列中元素个数
  private int capacity;           // 队列容量

  /**
   * 默认构造函数
   */
  public ArrayQueue() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * 指定初始容量的构造函数
   *
   * @param capacity 初始容量
   */
  public ArrayQueue(int capacity) {
    this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
    this.data = new Object[this.capacity];
    this.front = 0;
    this.rear = 0;
    this.size = 0;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 数组队列测试 ===");

    ArrayQueue<Integer> queue = new ArrayQueue<>(5);

    // 测试入队
    System.out.println("入队操作:");
    for (int i = 1; i <= 6; i++) {
      queue.enqueue(i);
      System.out.println("入队 " + i + ": " + queue + " (大小: " + queue.size() + ")");
    }

    // 测试查看队头
    System.out.println("\n队头元素: " + queue.peek());

    // 测试出队
    System.out.println("\n出队操作:");
    for (int i = 0; i < 3; i++) {
      int element = queue.dequeue();
      System.out.println("出队 " + element + ": " + queue + " (大小: " + queue.size() + ")");
    }

    // 继续入队测试循环使用
    System.out.println("\n继续入队:");
    for (int i = 7; i <= 9; i++) {
      queue.enqueue(i);
      System.out.println("入队 " + i + ": " + queue + " (大小: " + queue.size() + ")");
    }

    // 清空队列
    System.out.println("\n清空队列:");
    while (!queue.isEmpty()) {
      int element = queue.dequeue();
      System.out.println("出队 " + element + ": " + queue + " (大小: " + queue.size() + ")");
    }

    // 测试空队列异常
    System.out.println("\n测试空队列异常:");
    try {
      queue.dequeue();
    } catch (RuntimeException e) {
      System.out.println("捕获异常: " + e.getMessage());
    }
  }

  /**
   * 入队操作
   *
   * @param element 要入队的元素
   */
  public void enqueue(T element) {
    // 检查是否需要扩容
    if (size == capacity) {
      resize(capacity * 2);
    }

    data[rear] = element;
    rear = (rear + 1) % capacity;  // 循环使用数组空间
    size++;
  }

  /**
   * 出队操作
   *
   * @return 队头元素
   * @throws RuntimeException 如果队列为空
   */
  @SuppressWarnings("unchecked")
  public T dequeue() {
    if (isEmpty()) {
      throw new RuntimeException("队列为空，无法执行出队操作");
    }

    T element = (T) data[front];
    data[front] = null;  // 清除引用，帮助GC
    front = (front + 1) % capacity;
    size--;

    // 检查是否需要缩容
    if (size > 0 && size <= capacity / 4 && capacity > DEFAULT_CAPACITY) {
      resize(capacity / 2);
    }

    return element;
  }

  /**
   * 查看队头元素但不出队
   *
   * @return 队头元素
   * @throws RuntimeException 如果队列为空
   */
  @SuppressWarnings("unchecked")
  public T peek() {
    if (isEmpty()) {
      throw new RuntimeException("队列为空，无法查看队头元素");
    }
    return (T) data[front];
  }

  /**
   * 判断队列是否为空
   *
   * @return true如果队列为空，否则false
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 判断队列是否已满
   *
   * @return true如果队列已满，否则false
   */
  public boolean isFull() {
    return size == capacity;
  }

  /**
   * 获取队列中元素个数
   *
   * @return 元素个数
   */
  public int size() {
    return size;
  }

  /**
   * 获取队列容量
   *
   * @return 队列容量
   */
  public int capacity() {
    return capacity;
  }

  /**
   * 清空队列
   */
  public void clear() {
    // 清除所有引用
    for (int i = 0; i < capacity; i++) {
      data[i] = null;
    }
    front = rear = size = 0;
  }

  /**
   * 调整队列容量
   *
   * @param newCapacity 新容量
   */
  private void resize(int newCapacity) {
    Object[] newData = new Object[newCapacity];

    // 将原数组中的元素按顺序复制到新数组
    for (int i = 0; i < size; i++) {
      newData[i] = data[(front + i) % capacity];
    }

    data = newData;
    capacity = newCapacity;
    front = 0;
    rear = size;
  }

  /**
   * 转换为字符串表示
   *
   * @return 字符串表示
   */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "队头 [] 队尾";
    }

    StringBuilder sb = new StringBuilder();
    sb.append("队头 [");

    for (int i = 0; i < size; i++) {
      sb.append(data[(front + i) % capacity]);
      if (i < size - 1) {
        sb.append(", ");
      }
    }

    sb.append("] 队尾");
    return sb.toString();
  }
}