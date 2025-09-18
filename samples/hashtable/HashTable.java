package hashtable;

/**
 * 基于链表法解决冲突的散列表实现
 * 使用链地址法处理哈希冲突
 */
public class HashTable<K, V> {
    
    /**
     * 散列表节点内部类
     */
    private static class Node<K, V> {
        K key;          // 键
        V value;        // 值
        Node<K, V> next; // 指向下一个节点的指针
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    private Node<K, V>[] buckets;   // 散列表桶数组
    private int size;               // 当前元素个数
    private int capacity;           // 散列表容量
    
    private static final int DEFAULT_CAPACITY = 16;  // 默认容量
    private static final double LOAD_FACTOR = 0.75;  // 负载因子
    
    /**
     * 默认构造函数
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        this.capacity = DEFAULT_CAPACITY;
        this.buckets = new Node[capacity];
        this.size = 0;
    }
    
    /**
     * 指定初始容量的构造函数
     * @param capacity 初始容量
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        this.buckets = new Node[this.capacity];
        this.size = 0;
    }
    
    /**
     * 哈希函数
     * 使用除法散列法
     * 
     * @param key 键
     * @return 哈希值
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % capacity;
    }
    
    /**
     * 插入或更新键值对
     * 
     * @param key 键
     * @param value 值
     * @return 如果是更新操作，返回旧值；如果是插入操作，返回null
     */
    public V put(K key, V value) {
        int index = hash(key);
        Node<K, V> head = buckets[index];
        
        // 遍历链表，查找是否已存在该键
        Node<K, V> current = head;
        while (current != null) {
            if (current.key != null && current.key.equals(key)) {
                // 键已存在，更新值
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }
        
        // 键不存在，插入新节点到链表头部
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        buckets[index] = newNode;
        size++;
        
        // 检查是否需要扩容
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }
        
        return null;
    }
    
    /**
     * 获取指定键的值
     * 
     * @param key 键
     * @return 对应的值，如果不存在返回null
     */
    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = buckets[index];
        
        while (current != null) {
            if (current.key != null && current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        
        return null; // 未找到
    }
    
    /**
     * 删除指定键的键值对
     * 
     * @param key 键
     * @return 被删除的值，如果不存在返回null
     */
    public V remove(K key) {
        int index = hash(key);
        Node<K, V> head = buckets[index];
        
        if (head == null) {
            return null;
        }
        
        // 如果要删除的是头节点
        if (head.key != null && head.key.equals(key)) {
            buckets[index] = head.next;
            size--;
            return head.value;
        }
        
        // 遍历链表查找要删除的节点
        Node<K, V> current = head;
        while (current.next != null) {
            if (current.next.key != null && current.next.key.equals(key)) {
                V value = current.next.value;
                current.next = current.next.next;
                size--;
                return value;
            }
            current = current.next;
        }
        
        return null; // 未找到
    }
    
    /**
     * 判断是否包含指定键
     * 
     * @param key 键
     * @return true如果包含，否则false
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    
    /**
     * 判断散列表是否为空
     * 
     * @return true如果为空，否则false
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * 获取散列表中键值对的个数
     * 
     * @return 键值对个数
     */
    public int size() {
        return size;
    }
    
    /**
     * 获取散列表容量
     * 
     * @return 容量
     */
    public int capacity() {
        return capacity;
    }
    
    /**
     * 获取负载因子
     * 
     * @return 负载因子
     */
    public double getLoadFactor() {
        return (double) size / capacity;
    }
    
    /**
     * 清空散列表
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            buckets[i] = null;
        }
        size = 0;
    }
    
    /**
     * 扩容操作
     * 当负载因子超过阈值时，将容量扩大一倍并重新哈希
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldBuckets = buckets;
        int oldCapacity = capacity;
        
        // 扩大容量
        capacity *= 2;
        buckets = new Node[capacity];
        size = 0;
        
        // 重新插入所有元素
        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> current = oldBuckets[i];
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }
    
    /**
     * 获取所有键的数组
     * 
     * @return 包含所有键的数组
     */
    @SuppressWarnings("unchecked")
    public K[] keys() {
        K[] result = (K[]) new Object[size];
        int index = 0;
        
        for (int i = 0; i < capacity; i++) {
            Node<K, V> current = buckets[i];
            while (current != null) {
                result[index++] = current.key;
                current = current.next;
            }
        }
        
        return result;
    }
    
    /**
     * 获取散列表的统计信息
     * 
     * @return 统计信息字符串
     */
    public String getStatistics() {
        int[] chainLengths = new int[capacity];
        int maxChainLength = 0;
        int nonEmptyBuckets = 0;
        
        for (int i = 0; i < capacity; i++) {
            int length = 0;
            Node<K, V> current = buckets[i];
            while (current != null) {
                length++;
                current = current.next;
            }
            chainLengths[i] = length;
            if (length > 0) {
                nonEmptyBuckets++;
            }
            maxChainLength = Math.max(maxChainLength, length);
        }
        
        double avgChainLength = nonEmptyBuckets > 0 ? (double) size / nonEmptyBuckets : 0;
        
        return String.format(
            "散列表统计信息:\n" +
            "  容量: %d\n" +
            "  大小: %d\n" +
            "  负载因子: %.2f\n" +
            "  非空桶数: %d\n" +
            "  最大链长: %d\n" +
            "  平均链长: %.2f",
            capacity, size, getLoadFactor(), nonEmptyBuckets, maxChainLength, avgChainLength
        );
    }
    
    /**
     * 转换为字符串表示
     * 
     * @return 字符串表示
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        boolean first = true;
        for (int i = 0; i < capacity; i++) {
            Node<K, V> current = buckets[i];
            while (current != null) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(current.key).append("=").append(current.value);
                first = false;
                current = current.next;
            }
        }
        
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 散列表测试 ===\n");
        
        HashTable<String, Integer> hashTable = new HashTable<>();
        
        // 测试插入操作
        System.out.println("1. 插入操作测试:");
        String[] keys = {"apple", "banana", "orange", "grape", "kiwi", "mango", "peach"};
        for (int i = 0; i < keys.length; i++) {
            Integer oldValue = hashTable.put(keys[i], i + 1);
            System.out.println("插入 " + keys[i] + "=" + (i + 1) + 
                             (oldValue != null ? " (更新，旧值: " + oldValue + ")" : " (新增)"));
        }
        
        System.out.println("\n散列表内容: " + hashTable);
        System.out.println(hashTable.getStatistics());
        
        // 测试查找操作
        System.out.println("\n2. 查找操作测试:");
        for (String key : keys) {
            Integer value = hashTable.get(key);
            System.out.println("查找 " + key + ": " + value);
        }
        
        System.out.println("查找不存在的键 'watermelon': " + hashTable.get("watermelon"));
        
        // 测试更新操作
        System.out.println("\n3. 更新操作测试:");
        Integer oldValue = hashTable.put("apple", 100);
        System.out.println("更新 apple=100, 旧值: " + oldValue);
        System.out.println("散列表内容: " + hashTable);
        
        // 测试删除操作
        System.out.println("\n4. 删除操作测试:");
        String[] keysToRemove = {"banana", "grape", "watermelon"};
        for (String key : keysToRemove) {
            Integer removedValue = hashTable.remove(key);
            System.out.println("删除 " + key + ": " + 
                             (removedValue != null ? "成功，值为 " + removedValue : "失败，键不存在"));
        }
        
        System.out.println("\n删除后散列表内容: " + hashTable);
        System.out.println(hashTable.getStatistics());
        
        // 测试扩容
        System.out.println("\n5. 扩容测试:");
        System.out.println("当前容量: " + hashTable.capacity());
        
        // 添加更多元素触发扩容
        for (int i = 0; i < 20; i++) {
            hashTable.put("key" + i, i);
        }
        
        System.out.println("添加20个元素后:");
        System.out.println("新容量: " + hashTable.capacity());
        System.out.println(hashTable.getStatistics());
        
        // 测试包含操作
        System.out.println("\n6. 包含操作测试:");
        String[] testKeys = {"apple", "key5", "nonexistent"};
        for (String key : testKeys) {
            boolean contains = hashTable.containsKey(key);
            System.out.println("包含 " + key + ": " + contains);
        }
        
        // 测试清空操作
        System.out.println("\n7. 清空操作测试:");
        System.out.println("清空前大小: " + hashTable.size());
        hashTable.clear();
        System.out.println("清空后大小: " + hashTable.size());
        System.out.println("散列表内容: " + hashTable);
    }
}