package linkedlist;

/**
 * 双向链表实现 支持双向遍历的链表数据结构
 */
public class DoublyLinkedList<T> {

  private Node<T> head;   // 头节点
  private Node<T> tail;   // 尾节点
  private int size;       // 链表长度

  /**
   * 构造函数
   */
  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * 获取链表长度
   *
   * @return 链表长度
   */
  public int size() {
    return size;
  }

  /**
   * 判断链表是否为空
   *
   * @return true如果链表为空，否则false
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * 在链表头部添加元素
   *
   * @param data 要添加的数据
   */
  public void addFirst(T data) {
    Node<T> newNode = new Node<>(data);

    if (head == null) {
      // 空链表
      head = tail = newNode;
    } else {
      // 非空链表
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
    size++;
  }

  /**
   * 在链表尾部添加元素
   *
   * @param data 要添加的数据
   */
  public void addLast(T data) {
    Node<T> newNode = new Node<>(data);

    if (tail == null) {
      // 空链表
      head = tail = newNode;
    } else {
      // 非空链表
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
  }

  /**
   * 在指定位置插入元素
   *
   * @param index 插入位置
   * @param data  要插入的数据
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  public void add(int index, T data) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    if (index == 0) {
      addFirst(data);
    } else if (index == size) {
      addLast(data);
    } else {
      Node<T> current = getNode(index);
      Node<T> newNode = new Node<>(data, current.prev, current);

      current.prev.next = newNode;
      current.prev = newNode;
      size++;
    }
  }

  /**
   * 删除头节点
   *
   * @return 被删除的数据
   * @throws RuntimeException 如果链表为空
   */
  public T removeFirst() {
    if (head == null) {
      throw new RuntimeException("链表为空");
    }

    T data = head.data;

    if (head == tail) {
      // 只有一个节点
      head = tail = null;
    } else {
      // 多个节点
      head = head.next;
      head.prev = null;
    }

    size--;
    return data;
  }

  /**
   * 删除尾节点
   *
   * @return 被删除的数据
   * @throws RuntimeException 如果链表为空
   */
  public T removeLast() {
    if (tail == null) {
      throw new RuntimeException("链表为空");
    }

    T data = tail.data;

    if (head == tail) {
      // 只有一个节点
      head = tail = null;
    } else {
      // 多个节点
      tail = tail.prev;
      tail.next = null;
    }

    size--;
    return data;
  }

  /**
   * 删除指定位置的元素
   *
   * @param index 要删除的位置
   * @return 被删除的数据
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  public T remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    if (index == 0) {
      return removeFirst();
    } else if (index == size - 1) {
      return removeLast();
    } else {
      Node<T> current = getNode(index);
      T data = current.data;

      current.prev.next = current.next;
      current.next.prev = current.prev;
      size--;
      return data;
    }
  }

  /**
   * 获取指定位置的元素
   *
   * @param index 位置索引
   * @return 元素数据
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  public T get(int index) {
    return getNode(index).data;
  }

  /**
   * 设置指定位置的元素
   *
   * @param index 位置索引
   * @param data  新数据
   * @return 原来的数据
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  public T set(int index, T data) {
    Node<T> node = getNode(index);
    T oldData = node.data;
    node.data = data;
    return oldData;
  }

  /**
   * 获取指定位置的节点（优化版本，根据索引位置选择从头或从尾开始遍历）
   *
   * @param index 位置索引
   * @return 节点对象
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  private Node<T> getNode(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    Node<T> current;

    // 根据索引位置选择遍历方向，提高效率
    if (index < size / 2) {
      // 从头开始遍历
      current = head;
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
    } else {
      // 从尾开始遍历
      current = tail;
      for (int i = size - 1; i > index; i--) {
        current = current.prev;
      }
    }

    return current;
  }

  /**
   * 查找元素的索引
   *
   * @param data 要查找的数据
   * @return 元素索引，如果不存在返回-1
   */
  public int indexOf(T data) {
    Node<T> current = head;
    int index = 0;

    while (current != null) {
      if (current.data.equals(data)) {
        return index;
      }
      current = current.next;
      index++;
    }

    return -1;
  }

  /**
   * 从尾部开始查找元素的索引
   *
   * @param data 要查找的数据
   * @return 元素索引，如果不存在返回-1
   */
  public int lastIndexOf(T data) {
    Node<T> current = tail;
    int index = size - 1;

    while (current != null) {
      if (current.data.equals(data)) {
        return index;
      }
      current = current.prev;
      index--;
    }

    return -1;
  }

  /**
   * 判断是否包含指定元素
   *
   * @param data 要查找的数据
   * @return true如果包含，否则false
   */
  public boolean contains(T data) {
    return indexOf(data) != -1;
  }

  /**
   * 清空链表
   */
  public void clear() {
    head = tail = null;
    size = 0;
  }

  /**
   * 正向遍历转换为字符串
   *
   * @return 字符串表示
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    Node<T> current = head;
    while (current != null) {
      sb.append(current.data);
      if (current.next != null) {
        sb.append(" <-> ");
      }
      current = current.next;
    }

    sb.append("]");
    return sb.toString();
  }

  /**
   * 反向遍历转换为字符串
   *
   * @return 反向字符串表示
   */
  public String toStringReverse() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    Node<T> current = tail;
    while (current != null) {
      sb.append(current.data);
      if (current.prev != null) {
        sb.append(" <-> ");
      }
      current = current.prev;
    }

    sb.append("]");
    return sb.toString();
  }

  /**
   * 双向链表节点内部类
   */
  private static class Node<T> {

    T data;         // 节点数据
    Node<T> next;   // 指向下一个节点的指针
    Node<T> prev;   // 指向前一个节点的指针

    Node(T data) {
      this.data = data;
      this.next = null;
      this.prev = null;
    }

    Node(T data, Node<T> prev, Node<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }
  }
}