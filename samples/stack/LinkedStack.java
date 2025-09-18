package stack;

/**
 * 基于链表实现的链式栈 使用单链表实现的栈数据结构
 */
public class LinkedStack<T> {

  private Node<T> top;    // 栈顶指针
  private int size;       // 栈中元素个数

  /**
   * 构造函数
   */
  public LinkedStack() {
    this.top = null;
    this.size = 0;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 链式栈测试 ===");

    LinkedStack<String> stack = new LinkedStack<>();

    // 测试入栈
    System.out.println("入栈操作:");
    String[] elements = {"A", "B", "C", "D", "E"};
    for (String element : elements) {
      stack.push(element);
      System.out.println("入栈 " + element + ": " + stack);
    }

    // 测试查看栈顶
    System.out.println("\n栈顶元素: " + stack.peek());
    System.out.println("栈大小: " + stack.size());

    // 测试出栈
    System.out.println("\n出栈操作:");
    while (!stack.isEmpty()) {
      String element = stack.pop();
      System.out.println("出栈 " + element + ": " + stack);
    }

    // 测试空栈异常
    System.out.println("\n测试空栈异常:");
    try {
      stack.peek();
    } catch (RuntimeException e) {
      System.out.println("捕获异常: " + e.getMessage());
    }
  }

  /**
   * 入栈操作
   *
   * @param element 要入栈的元素
   */
  public void push(T element) {
    Node<T> newNode = new Node<>(element, top);
    top = newNode;
    size++;
  }

  /**
   * 出栈操作
   *
   * @return 栈顶元素
   * @throws RuntimeException 如果栈为空
   */
  public T pop() {
    if (isEmpty()) {
      throw new RuntimeException("栈为空，无法执行出栈操作");
    }

    T element = top.data;
    top = top.next;
    size--;
    return element;
  }

  /**
   * 查看栈顶元素但不出栈
   *
   * @return 栈顶元素
   * @throws RuntimeException 如果栈为空
   */
  public T peek() {
    if (isEmpty()) {
      throw new RuntimeException("栈为空，无法查看栈顶元素");
    }
    return top.data;
  }

  /**
   * 判断栈是否为空
   *
   * @return true如果栈为空，否则false
   */
  public boolean isEmpty() {
    return top == null;
  }

  /**
   * 获取栈中元素个数
   *
   * @return 元素个数
   */
  public int size() {
    return size;
  }

  /**
   * 清空栈
   */
  public void clear() {
    top = null;
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
      return "栈底 [] 栈顶";
    }

    StringBuilder sb = new StringBuilder();
    sb.append("栈底 [");

    // 由于是链式栈，需要先遍历到底部再反向构建字符串
    Node<T> current = top;
    StringBuilder temp = new StringBuilder();

    while (current != null) {
      if (temp.length() > 0) {
        temp.insert(0, ", ");
      }
      temp.insert(0, current.data);
      current = current.next;
    }

    sb.append(temp);
    sb.append("] 栈顶");
    return sb.toString();
  }

  /**
   * 栈节点内部类
   */
  private static class Node<T> {

    T data;         // 节点数据
    Node<T> next;   // 指向下一个节点的指针

    Node(T data) {
      this.data = data;
      this.next = null;
    }

    Node(T data, Node<T> next) {
      this.data = data;
      this.next = next;
    }
  }
}