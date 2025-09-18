package linkedlist;

/**
 * 单链表实现
 * 支持增删操作的单向链表数据结构
 */
public class SinglyLinkedList<T> {
    
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
        
        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
    
    private Node<T> head;   // 头节点
    private int size;       // 链表长度
    
    /**
     * 构造函数
     */
    public SinglyLinkedList() {
        this.head = null;
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
        return head == null;
    }
    
    /**
     * 在链表头部添加元素
     * @param data 要添加的数据
     */
    public void addFirst(T data) {
        head = new Node<>(data, head);
        size++;
    }
    
    /**
     * 在链表尾部添加元素
     * @param data 要添加的数据
     */
    public void addLast(T data) {
        if (head == null) {
            addFirst(data);
            return;
        }
        
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(data);
        size++;
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
            return;
        }
        
        Node<T> prev = getNode(index - 1);
        prev.next = new Node<>(data, prev.next);
        size++;
    }
    
    /**
     * 删除头节点
     * @return 被删除的数据
     * @throws RuntimeException 如果链表为空
     */
    public T removeFirst() {
        if (head == null) {
            throw new RuntimeException("链表为空");
        }
        
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
    /**
     * 删除尾节点
     * @return 被删除的数据
     * @throws RuntimeException 如果链表为空
     */
    public T removeLast() {
        if (head == null) {
            throw new RuntimeException("链表为空");
        }
        
        if (head.next == null) {
            return removeFirst();
        }
        
        Node<T> prev = head;
        while (prev.next.next != null) {
            prev = prev.next;
        }
        
        T data = prev.next.data;
        prev.next = null;
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
        }
        
        Node<T> prev = getNode(index - 1);
        T data = prev.next.data;
        prev.next = prev.next.next;
        size--;
        return data;
    }
    
    /**
     * 删除指定值的第一个节点
     * @param data 要删除的数据
     * @return true如果删除成功，false如果未找到
     */
    public boolean removeByValue(T data) {
        if (head == null) {
            return false;
        }
        
        if (head.data.equals(data)) {
            removeFirst();
            return true;
        }
        
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        
        return false;
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
     * 查找元素的索引
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
     * 判断是否包含指定元素
     * @param data 要查找的数据
     * @return true如果包含，否则false
     */
    public boolean contains(T data) {
        return indexOf(data) != -1;
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
        
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    
    /**
     * 反转链表
     * @return 反转后的链表头节点
     */
    public SinglyLinkedList<T> reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next;
        
        while (current != null) {
            next = current.next;    // 保存下一个节点
            current.next = prev;    // 反转当前节点的指针
            prev = current;         // 移动prev指针
            current = next;         // 移动current指针
        }
        
        head = prev;
        return this;
    }
    
    /**
     * 清空链表
     */
    public void clear() {
        head = null;
        size = 0;
    }
    
    /**
     * 转换为字符串表示
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
                sb.append(" -> ");
            }
            current = current.next;
        }
        
        sb.append("]");
        return sb.toString();
    }
}