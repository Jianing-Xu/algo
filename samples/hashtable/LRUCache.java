package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU（Least Recently Used）缓存淘汰算法实现 使用哈希表 + 双向链表实现O(1)时间复杂度的get和put操作
 */
public class LRUCache<K, V> {

  private final int capacity;                    // 缓存容量
  private final Map<K, Node<K, V>> cache;       // 哈希表，用于O(1)查找
  private final Node<K, V> head;                // 虚拟头节点
  private final Node<K, V> tail;                // 虚拟尾节点

  /**
   * 构造函数
   *
   * @param capacity 缓存容量
   */
  public LRUCache(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("容量必须大于0");
    }

    this.capacity = capacity;
    this.cache = new HashMap<>();

    // 创建虚拟头尾节点，简化边界处理
    this.head = new Node<>(null, null);
    this.tail = new Node<>(null, null);
    head.next = tail;
    tail.prev = head;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== LRU缓存测试 ===\n");

    // 创建容量为3的LRU缓存
    LRUCache<String, Integer> lru = new LRUCache<>(3);

    System.out.println("1. 基本操作测试:");
    System.out.println("创建容量为3的LRU缓存");
    System.out.println(lru.getStatus());

    // 测试put操作
    System.out.println("\n2. 添加元素测试:");
    lru.put("A", 1);
    System.out.println("添加 A=1: " + lru);

    lru.put("B", 2);
    System.out.println("添加 B=2: " + lru);

    lru.put("C", 3);
    System.out.println("添加 C=3: " + lru);
    System.out.println(lru.getStatus());

    // 测试get操作
    System.out.println("\n3. 访问元素测试:");
    Integer value = lru.get("A");
    System.out.println("访问 A: " + value + " -> " + lru);

    value = lru.get("B");
    System.out.println("访问 B: " + value + " -> " + lru);

    // 测试缓存满时的淘汰
    System.out.println("\n4. 缓存淘汰测试:");
    lru.put("D", 4);
    System.out.println("添加 D=4 (缓存已满): " + lru);
    System.out.println("C被淘汰了吗？" + !lru.containsKey("C"));

    lru.put("E", 5);
    System.out.println("添加 E=5: " + lru);

    // 测试更新操作
    System.out.println("\n5. 更新操作测试:");
    lru.put("A", 10);
    System.out.println("更新 A=10: " + lru);

    // 测试访问不存在的键
    System.out.println("\n6. 访问不存在的键:");
    value = lru.get("C");
    System.out.println("访问 C (已被淘汰): " + value);

    value = lru.get("F");
    System.out.println("访问 F (从未存在): " + value);

    // 测试删除操作
    System.out.println("\n7. 删除操作测试:");
    Integer removedValue = lru.remove("B");
    System.out.println("删除 B: " + removedValue + " -> " + lru);
    System.out.println(lru.getStatus());

    // 测试包含操作
    System.out.println("\n8. 包含操作测试:");
    String[] testKeys = {"A", "B", "D", "E", "F"};
    for (String key : testKeys) {
      boolean contains = lru.containsKey(key);
      System.out.println("包含 " + key + ": " + contains);
    }

    // 测试获取键的顺序
    System.out.println("\n9. 访问顺序测试:");
    Object[] keys = lru.getKeysInOrder();
    System.out.print("当前键的访问顺序: ");
    for (Object key : keys) {
      System.out.print(key + " ");
    }
    System.out.println();

    // 测试清空操作
    System.out.println("\n10. 清空操作测试:");
    System.out.println("清空前: " + lru.getStatus());
    lru.clear();
    System.out.println("清空后: " + lru.getStatus());
    System.out.println("缓存内容: " + lru);

    // 性能测试
    System.out.println("\n11. 性能测试:");
    LRUCache<Integer, String> perfCache = new LRUCache<>(1000);

    long startTime = System.currentTimeMillis();

    // 插入10000个元素
    for (int i = 0; i < 10000; i++) {
      perfCache.put(i, "value" + i);
    }

    // 随机访问5000次
    for (int i = 0; i < 5000; i++) {
      int key = (int) (Math.random() * 10000);
      perfCache.get(key);
    }

    long endTime = System.currentTimeMillis();
    System.out.println("插入10000个元素并随机访问5000次耗时: " + (endTime - startTime) + "ms");
    System.out.println("最终缓存大小: " + perfCache.size());
  }

  /**
   * 获取缓存中的值 如果键存在，将其移动到链表头部（标记为最近使用）
   *
   * @param key 键
   * @return 对应的值，如果不存在返回null
   */
  public V get(K key) {
    Node<K, V> node = cache.get(key);
    if (node == null) {
      return null;
    }

    // 将访问的节点移动到链表头部
    moveToHead(node);
    return node.value;
  }

  /**
   * 向缓存中添加或更新键值对
   *
   * @param key   键
   * @param value 值
   */
  public void put(K key, V value) {
    Node<K, V> node = cache.get(key);

    if (node != null) {
      // 键已存在，更新值并移动到头部
      node.value = value;
      moveToHead(node);
    } else {
      // 键不存在，创建新节点
      Node<K, V> newNode = new Node<>(key, value);

      if (cache.size() >= capacity) {
        // 缓存已满，删除最久未使用的节点（尾部节点）
        Node<K, V> lastNode = removeTail();
        cache.remove(lastNode.key);
      }

      // 添加新节点到头部
      addToHead(newNode);
      cache.put(key, newNode);
    }
  }

  /**
   * 删除指定键的缓存项
   *
   * @param key 键
   * @return 被删除的值，如果不存在返回null
   */
  public V remove(K key) {
    Node<K, V> node = cache.get(key);
    if (node == null) {
      return null;
    }

    removeNode(node);
    cache.remove(key);
    return node.value;
  }

  /**
   * 判断缓存中是否包含指定键
   *
   * @param key 键
   * @return true如果包含，否则false
   */
  public boolean containsKey(K key) {
    return cache.containsKey(key);
  }

  /**
   * 获取缓存中的元素个数
   *
   * @return 元素个数
   */
  public int size() {
    return cache.size();
  }

  /**
   * 获取缓存容量
   *
   * @return 容量
   */
  public int capacity() {
    return capacity;
  }

  /**
   * 判断缓存是否为空
   *
   * @return true如果为空，否则false
   */
  public boolean isEmpty() {
    return cache.isEmpty();
  }

  /**
   * 判断缓存是否已满
   *
   * @return true如果已满，否则false
   */
  public boolean isFull() {
    return cache.size() >= capacity;
  }

  /**
   * 清空缓存
   */
  public void clear() {
    cache.clear();
    head.next = tail;
    tail.prev = head;
  }

  /**
   * 将节点添加到链表头部
   *
   * @param node 要添加的节点
   */
  private void addToHead(Node<K, V> node) {
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }

  /**
   * 从链表中移除节点
   *
   * @param node 要移除的节点
   */
  private void removeNode(Node<K, V> node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  /**
   * 将节点移动到链表头部
   *
   * @param node 要移动的节点
   */
  private void moveToHead(Node<K, V> node) {
    removeNode(node);
    addToHead(node);
  }

  /**
   * 移除链表尾部节点
   *
   * @return 被移除的节点
   */
  private Node<K, V> removeTail() {
    Node<K, V> lastNode = tail.prev;
    removeNode(lastNode);
    return lastNode;
  }

  /**
   * 获取缓存状态信息
   *
   * @return 状态信息字符串
   */
  public String getStatus() {
    return String.format("LRU缓存状态: 大小=%d/%d, 使用率=%.1f%%",
        size(), capacity, (double) size() / capacity * 100);
  }

  /**
   * 按访问顺序获取所有键（从最近访问到最久访问）
   *
   * @return 键的数组
   */
  @SuppressWarnings("unchecked")
  public K[] getKeysInOrder() {
    K[] keys = (K[]) new Object[size()];
    Node<K, V> current = head.next;
    int index = 0;

    while (current != tail && index < keys.length) {
      keys[index++] = current.key;
      current = current.next;
    }

    return keys;
  }

  /**
   * 转换为字符串表示（按访问顺序）
   *
   * @return 字符串表示
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("LRU[");

    Node<K, V> current = head.next;
    boolean first = true;

    while (current != tail) {
      if (!first) {
        sb.append(", ");
      }
      sb.append(current.key).append("=").append(current.value);
      first = false;
      current = current.next;
    }

    sb.append("] (最近 -> 最久)");
    return sb.toString();
  }

  /**
   * 双向链表节点
   */
  private static class Node<K, V> {

    K key;          // 键
    V value;        // 值
    Node<K, V> prev; // 前驱节点
    Node<K, V> next; // 后继节点

    Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}