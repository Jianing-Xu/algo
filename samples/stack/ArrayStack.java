package stack;

/**
 * 基于数组实现的顺序栈
 * 支持动态扩容的栈数据结构
 */
public class ArrayStack<T> {
    private Object[] data;          // 存储栈元素的数组
    private int top;                // 栈顶指针
    private int capacity;           // 栈容量
    
    private static final int DEFAULT_CAPACITY = 10;  // 默认容量
    private static final double LOAD_FACTOR = 0.75;  // 扩容因子
    
    /**
     * 默认构造函数
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * 指定初始容量的构造函数
     * @param capacity 初始容量
     */
    public ArrayStack(int capacity) {
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        this.data = new Object[this.capacity];
        this.top = -1; // 栈为空时，top指向-1
    }
    
    /**
     * 入栈操作
     * @param element 要入栈的元素
     */
    public void push(T element) {
        // 检查是否需要扩容
        if (top + 1 >= capacity * LOAD_FACTOR) {
            resize(capacity * 2);
        }
        
        data[++top] = element;
    }
    
    /**
     * 出栈操作
     * @return 栈顶元素
     * @throws RuntimeException 如果栈为空
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法执行出栈操作");
        }
        
        T element = (T) data[top];
        data[top--] = null; // 清除引用，帮助GC
        
        // 检查是否需要缩容
        if (top + 1 > 0 && top + 1 <= capacity * 0.25 && capacity > DEFAULT_CAPACITY) {
            resize(capacity / 2);
        }
        
        return element;
    }
    
    /**
     * 查看栈顶元素但不出栈
     * @return 栈顶元素
     * @throws RuntimeException 如果栈为空
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法查看栈顶元素");
        }
        return (T) data[top];
    }
    
    /**
     * 判断栈是否为空
     * @return true如果栈为空，否则false
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * 获取栈中元素个数
     * @return 元素个数
     */
    public int size() {
        return top + 1;
    }
    
    /**
     * 获取栈容量
     * @return 栈容量
     */
    public int capacity() {
        return capacity;
    }
    
    /**
     * 清空栈
     */
    public void clear() {
        // 清除所有引用
        for (int i = 0; i <= top; i++) {
            data[i] = null;
        }
        top = -1;
    }
    
    /**
     * 调整栈容量
     * @param newCapacity 新容量
     */
    private void resize(int newCapacity) {
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, top + 1);
        data = newData;
        capacity = newCapacity;
    }
    
    /**
     * 转换为字符串表示
     * @return 字符串表示
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("栈底 [");
        
        for (int i = 0; i <= top; i++) {
            sb.append(data[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        
        sb.append("] 栈顶");
        return sb.toString();
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 数组栈测试 ===");
        
        ArrayStack<Integer> stack = new ArrayStack<>();
        
        // 测试入栈
        System.out.println("入栈操作:");
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
            System.out.println("入栈 " + i + ": " + stack);
        }
        
        // 测试查看栈顶
        System.out.println("\n栈顶元素: " + stack.peek());
        System.out.println("栈大小: " + stack.size());
        
        // 测试出栈
        System.out.println("\n出栈操作:");
        while (!stack.isEmpty()) {
            int element = stack.pop();
            System.out.println("出栈 " + element + ": " + stack);
        }
        
        // 测试空栈异常
        System.out.println("\n测试空栈异常:");
        try {
            stack.pop();
        } catch (RuntimeException e) {
            System.out.println("捕获异常: " + e.getMessage());
        }
    }
}