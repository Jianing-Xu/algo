package queue;

/**
 * 循环队列实现
 * 基于数组的循环队列，有效利用数组空间
 */
public class CircularQueue<T> {
    private Object[] data;          // 存储队列元素的数组
    private int front;              // 队头指针
    private int rear;               // 队尾指针
    private final int capacity;     // 队列容量
    
    /**
     * 构造函数
     * @param capacity 队列容量
     */
    public CircularQueue(int capacity) {
        // 实际数组大小比容量大1，用于区分队列满和空的状态
        this.capacity = capacity;
        this.data = new Object[capacity + 1];
        this.front = 0;
        this.rear = 0;
    }
    
    /**
     * 入队操作
     * @param element 要入队的元素
     * @return true如果入队成功，false如果队列已满
     */
    public boolean enqueue(T element) {
        if (isFull()) {
            return false;
        }
        
        data[rear] = element;
        rear = (rear + 1) % data.length;
        return true;
    }
    
    /**
     * 出队操作
     * @return 队头元素，如果队列为空返回null
     */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        
        T element = (T) data[front];
        data[front] = null;  // 清除引用，帮助GC
        front = (front + 1) % data.length;
        return element;
    }
    
    /**
     * 查看队头元素但不出队
     * @return 队头元素，如果队列为空返回null
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) data[front];
    }
    
    /**
     * 判断队列是否为空
     * @return true如果队列为空，否则false
     */
    public boolean isEmpty() {
        return front == rear;
    }
    
    /**
     * 判断队列是否已满
     * @return true如果队列已满，否则false
     */
    public boolean isFull() {
        return (rear + 1) % data.length == front;
    }
    
    /**
     * 获取队列中元素个数
     * @return 元素个数
     */
    public int size() {
        return (rear - front + data.length) % data.length;
    }
    
    /**
     * 获取队列容量
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
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        front = rear = 0;
    }
    
    /**
     * 转换为字符串表示
     * @return 字符串表示
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "队头 [] 队尾 (容量: " + capacity + ")";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("队头 [");
        
        int current = front;
        boolean first = true;
        while (current != rear) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(data[current]);
            current = (current + 1) % data.length;
            first = false;
        }
        
        sb.append("] 队尾 (容量: ").append(capacity).append(", 大小: ").append(size()).append(")");
        return sb.toString();
    }
    
    /**
     * 显示队列的详细状态
     */
    public void showStatus() {
        System.out.println("=== 循环队列状态 ===");
        System.out.println("容量: " + capacity);
        System.out.println("大小: " + size());
        System.out.println("是否为空: " + isEmpty());
        System.out.println("是否已满: " + isFull());
        System.out.println("front指针: " + front);
        System.out.println("rear指针: " + rear);
        System.out.println("队列内容: " + this);
        System.out.println();
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 循环队列测试 ===\n");
        
        CircularQueue<Integer> queue = new CircularQueue<>(5);
        queue.showStatus();
        
        // 测试入队
        System.out.println("1. 入队操作:");
        for (int i = 1; i <= 5; i++) {
            boolean success = queue.enqueue(i);
            System.out.println("入队 " + i + ": " + (success ? "成功" : "失败") + " - " + queue);
        }
        queue.showStatus();
        
        // 测试队列满时入队
        System.out.println("2. 队列满时尝试入队:");
        boolean success = queue.enqueue(6);
        System.out.println("入队 6: " + (success ? "成功" : "失败") + " - " + queue);
        queue.showStatus();
        
        // 测试出队
        System.out.println("3. 出队操作:");
        for (int i = 0; i < 3; i++) {
            Integer element = queue.dequeue();
            System.out.println("出队: " + element + " - " + queue);
        }
        queue.showStatus();
        
        // 测试循环使用空间
        System.out.println("4. 继续入队（测试循环使用）:");
        for (int i = 6; i <= 8; i++) {
            success = queue.enqueue(i);
            System.out.println("入队 " + i + ": " + (success ? "成功" : "失败") + " - " + queue);
        }
        queue.showStatus();
        
        // 测试查看队头
        System.out.println("5. 查看队头元素:");
        Integer head = queue.peek();
        System.out.println("队头元素: " + head + " - " + queue);
        
        // 清空队列
        System.out.println("6. 清空队列:");
        while (!queue.isEmpty()) {
            Integer element = queue.dequeue();
            System.out.println("出队: " + element + " - " + queue);
        }
        queue.showStatus();
        
        // 测试空队列操作
        System.out.println("7. 空队列操作测试:");
        System.out.println("空队列出队: " + queue.dequeue());
        System.out.println("空队列查看队头: " + queue.peek());
    }
}