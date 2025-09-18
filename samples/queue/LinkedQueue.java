package queue;

/**
 * 基于链表实现的链式队列 使用单链表实现的队列数据结构
 */
public class LinkedQueue<T> {

  private Node<T> front;  // 队头指针
  private Node<T> rear;   // 队尾指针
  private int size;       // 队列中元素个数

  /**
   * 构造函数
   */
  public LinkedQueue() {
    this.front = null;
    this.rear = null;
    this.size = 0;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 链式队列测试 ===");

    LinkedQueue<String> queue = new LinkedQueue<>();

    // 测试入队
    System.out.println("入队操作:");
    String[] elements = {"A", "B", "C", "D", "E"};
    for (String element : elements) {
      queue.enqueue(element);
      System.out.println("入队 " + element + ": " + queue + " (大小: " + queue.size() + ")");
    }

    // 测试查看队头
    System.out.println("\n队头元素: " + queue.peek());

    // 测试出队
    System.out.println("\n出队操作:");
    while (!queue.isEmpty()) {
      String element = queue.dequeue();
      System.out.println("出队 " + element + ": " + queue + " (大小: " + queue.size() + ")");
    }

    // 测试空队列异常
    System.out.println("\n测试空队列异常:");
    try {
      queue.peek();
    } catch (RuntimeException e) {
      System.out.println("捕获异常: " + e.getMessage());
    }

    // 测试重新入队
    System.out.println("\n重新入队测试:");
    queue.enqueue("X");
    queue.enqueue("Y");
    System.out.println("重新入队后: " + queue);
  }

  /**
   * 入队操作
   *
   * @param element 要入队的元素
   */
  public void enqueue(T element) {
    Node<T> newNode = new Node<>(element);

    if (rear == null) {
      // 队列为空，front和rear都指向新节点
      front = rear = newNode;
    } else {
      // 队列不为空，在队尾添加新节点
      rear.next = newNode;
      rear = newNode;
    }
    size++;
  }

  /**
   * 出队操作
   *
   * @return 队头元素
   * @throws RuntimeException 如果队列为空
   */
  public T dequeue() {
    if (isEmpty()) {
      throw new RuntimeException("队列为空，无法执行出队操作");
    }

    T element = front.data;
    front = front.next;

    // 如果队列变为空，rear也要置为null
    if (front == null) {
      rear = null;
    }

    size--;
    return element;
  }

  /**
   * 查看队头元素但不出队
   *
   * @return 队头元素
   * @throws RuntimeException 如果队列为空
   */
  public T peek() {
    if (isEmpty()) {
      throw new RuntimeException("队列为空，无法查看队头元素");
    }
    return front.data;
  }

  /**
   * 判断队列是否为空
   *
   * @return true如果队列为空，否则false
   */
  public boolean isEmpty() {
    return front == null;
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
   * 清空队列
   */
  public void clear() {
    front = rear = null;
    size = 0;
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

    Node<T> current = front;
    while (current != null) {
      sb.append(current.data);
      if (current.next != null) {
        sb.append(", ");
      }
      current = current.next;
    }

    sb.append("] 队尾");
    return sb.toString();
  }

  /**
   * 队列节点内部类
   */
  private static class Node<T> {

    T data;         // 节点数据
    Node<T> next;   // 指向下一个节点的指针

    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }
}