package array;

/**
 * 动态扩容数组实现 支持自动扩容和缩容的数组数据结构
 */
public class DynamicArray<T> {

  private static final int DEFAULT_CAPACITY = 10;  // 默认容量
  private static final double LOAD_FACTOR = 0.75;  // 扩容因子
  private static final double SHRINK_FACTOR = 0.25; // 缩容因子
  private Object[] data;      // 存储数据的数组
  private int size;           // 当前元素个数
  private int capacity;       // 数组容量

  /**
   * 默认构造函数
   */
  public DynamicArray() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * 指定初始容量的构造函数
   *
   * @param capacity 初始容量
   */
  public DynamicArray(int capacity) {
    this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
    this.data = new Object[this.capacity];
    this.size = 0;
  }

  /**
   * 获取数组大小
   *
   * @return 当前元素个数
   */
  public int size() {
    return size;
  }

  /**
   * 判断数组是否为空
   *
   * @return true如果数组为空，否则false
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 获取指定索引的元素
   *
   * @param index 索引位置
   * @return 元素值
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  @SuppressWarnings("unchecked")
  public T get(int index) {
    checkIndex(index);
    return (T) data[index];
  }

  /**
   * 设置指定索引的元素
   *
   * @param index   索引位置
   * @param element 新元素值
   * @return 原来的元素值
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  @SuppressWarnings("unchecked")
  public T set(int index, T element) {
    checkIndex(index);
    T oldValue = (T) data[index];
    data[index] = element;
    return oldValue;
  }

  /**
   * 在数组末尾添加元素
   *
   * @param element 要添加的元素
   */
  public void add(T element) {
    // 检查是否需要扩容
    if (size >= capacity * LOAD_FACTOR) {
      resize(capacity * 2);
    }
    data[size++] = element;
  }

  /**
   * 在指定位置插入元素
   *
   * @param index   插入位置
   * @param element 要插入的元素
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  public void add(int index, T element) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // 检查是否需要扩容
    if (size >= capacity * LOAD_FACTOR) {
      resize(capacity * 2);
    }

    // 将index及之后的元素向后移动一位
    System.arraycopy(data, index, data, index + 1, size - index);
    data[index] = element;
    size++;
  }

  /**
   * 删除指定索引的元素
   *
   * @param index 要删除的索引
   * @return 被删除的元素
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  @SuppressWarnings("unchecked")
  public T remove(int index) {
    checkIndex(index);

    T oldValue = (T) data[index];

    // 将index之后的元素向前移动一位
    int numMoved = size - index - 1;
    if (numMoved > 0) {
      System.arraycopy(data, index + 1, data, index, numMoved);
    }

    data[--size] = null; // 清除引用，帮助GC

    // 检查是否需要缩容
    if (size > 0 && size <= capacity * SHRINK_FACTOR && capacity > DEFAULT_CAPACITY) {
      resize(capacity / 2);
    }

    return oldValue;
  }

  /**
   * 查找元素的索引
   *
   * @param element 要查找的元素
   * @return 元素的索引，如果不存在返回-1
   */
  public int indexOf(T element) {
    for (int i = 0; i < size; i++) {
      if (element == null ? data[i] == null : element.equals(data[i])) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 检查索引是否有效
   *
   * @param index 索引值
   * @throws IndexOutOfBoundsException 如果索引越界
   */
  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  /**
   * 调整数组容量
   *
   * @param newCapacity 新容量
   */
  private void resize(int newCapacity) {
    Object[] newData = new Object[newCapacity];
    System.arraycopy(data, 0, newData, 0, size);
    data = newData;
    capacity = newCapacity;
  }

  /**
   * 转换为字符串表示
   *
   * @return 字符串表示
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < size; i++) {
      sb.append(data[i]);
      if (i < size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}