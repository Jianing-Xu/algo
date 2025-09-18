package linkedlist;

/**
 * 循环链表实现
 * 尾节点指向头节点的单向循环链表
 */
public class CircularLinkedList<T> {
    
    /**
     * 链表节点内部类
     */
    private static class Node<T> {
        T data;         // 节点数据
        Node<T> next;   // 指向下一个节点的指针
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node<T> tail;   // 尾节点（指向最后一个节点）
    private int size;       // 链表长度
    
    /**
     * 构造函数
     */
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * 获取链表长度
     * @return 链表长度
     */
    public int size() {
        return size;
    }
    
    /**
     * 判断链表是否为空
     * @return true如果链表为空，否则false
     */
    public boolean isEmpty() {
        return tail == null;
    }
    
    /**
     * 在链表头部添加元素
     * @param data 要添加的数据
     */
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (tail == null) {
            // 空链表，新节点指向自己
            tail = newNode;
            newNode.next = newNode;
        } else {
            // 非空链表，插入到头部
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }
    
    /**
     * 在链表尾部添加元素
     * @param data 要添加的数据
     */
    public void addLast(T data) {
        addFirst(data);
        tail = tail.next; // 移动尾指针
    }
    
    /**
     * 在指定位置插入元素
     * @param index 插入位置
     * @param data 要插入的数据
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
            Node<T> prev = getNode(index - 1);
            Node<T> newNode = new Node<>(data);
            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }
    }
    
    /**
     * 删除头节点
     * @return 被删除的数据
     * @throws RuntimeException 如果链表为空
     */
    public T removeFirst() {
        if (tail == null) {
            throw new RuntimeException("链表为空");
        }
        
        Node<T> head = tail.next;
        T data = head.data;
        
        if (head == tail) {
            // 只有一个节点
            tail = null;
        } else {
            // 多个节点，删除头节点
            tail.next = head.next;
        }
        
        size--;
        return data;
    }
    
    /**
     * 删除尾节点
     * @return 被删除的数据
     * @throws RuntimeException 如果链表为空
     */
    public T removeLast() {
        if (tail == null) {
            throw new RuntimeException("链表为空");
        }
        
        T data = tail.data;
        
        if (tail.next == tail) {
            // 只有一个节点
            tail = null;
        } else {
            // 多个节点，找到倒数第二个节点
            Node<T> current = tail.next;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = tail.next;
            tail = current;
        }
        
        size--;
        return data;
    }
    
    /**
     * 删除指定位置的元素
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
            Node<T> prev = getNode(index - 1);
            T data = prev.next.data;
            prev.next = prev.next.next;
            size--;
            return data;
        }
    }
    
    /**
     * 获取指定位置的元素
     * @param index 位置索引
     * @return 元素数据
     * @throws IndexOutOfBoundsException 如果索引越界
     */
    public T get(int index) {
        return getNode(index).data;
    }
    
    /**
     * 设置指定位置的元素
     * @param index 位置索引
     * @param data 新数据
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
     * 获取指定位置的节点
     * @param index 位置索引
     * @return 节点对象
     * @throws IndexOutOfBoundsException 如果索引越界
     */
    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> current = tail.next; // 从头节点开始
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    
    /**
     * 查找元素的索引
     * @param data 要查找的数据
     * @return 元素索引，如果不存在返回-1
     */
    public int indexOf(T data) {
        if (tail == null) {
            return -1;
        }
        
        Node<T> current = tail.next;
        int index = 0;
        
        do {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        } while (current != tail.next);
        
        return -1;
    }
    
    /**
     * 判断是否包含指定元素
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
        tail = null;
        size = 0;
    }
    
    /**
     * 转换为字符串表示
     * @return 字符串表示
     */
    @Override
    public String toString() {
        if (tail == null) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        Node<T> current = tail.next;
        do {
            sb.append(current.data);
            current = current.next;
            if (current != tail.next) {
                sb.append(" -> ");
            }
        } while (current != tail.next);
        
        sb.append(" -> (循环)]");
        return sb.toString();
    }
}