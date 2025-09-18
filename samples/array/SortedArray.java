package array;

/**
 * 固定大小的有序数组实现
 * 支持动态增删改操作，保持数组有序性
 */
public class SortedArray {
    private int[] data;         // 存储数据的数组
    private int size;           // 当前元素个数
    private final int capacity; // 数组容量
    
    /**
     * 构造函数
     * @param capacity 数组容量
     */
    public SortedArray(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.size = 0;
    }
    
    /**
     * 获取数组大小
     * @return 当前元素个数
     */
    public int size() {
        return size;
    }
    
    /**
     * 判断数组是否为空
     * @return true如果数组为空，否则false
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * 判断数组是否已满
     * @return true如果数组已满，否则false
     */
    public boolean isFull() {
        return size == capacity;
    }
    
    /**
     * 插入元素，保持数组有序
     * @param value 要插入的值
     * @return true如果插入成功，false如果数组已满
     */
    public boolean insert(int value) {
        if (isFull()) {
            return false;
        }
        
        // 找到插入位置
        int insertPos = findInsertPosition(value);
        
        // 将插入位置及之后的元素向后移动
        for (int i = size; i > insertPos; i--) {
            data[i] = data[i - 1];
        }
        
        // 插入新元素
        data[insertPos] = value;
        size++;
        return true;
    }
    
    /**
     * 删除指定值的元素
     * @param value 要删除的值
     * @return true如果删除成功，false如果元素不存在
     */
    public boolean delete(int value) {
        int index = binarySearch(value);
        if (index == -1) {
            return false;
        }
        
        // 将删除位置之后的元素向前移动
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        
        size--;
        return true;
    }
    
    /**
     * 更新元素值
     * @param oldValue 原值
     * @param newValue 新值
     * @return true如果更新成功，false如果原值不存在
     */
    public boolean update(int oldValue, int newValue) {
        if (delete(oldValue)) {
            return insert(newValue);
        }
        return false;
    }
    
    /**
     * 查找元素
     * @param value 要查找的值
     * @return 元素的索引，如果不存在返回-1
     */
    public int search(int value) {
        return binarySearch(value);
    }
    
    /**
     * 获取指定索引的元素
     * @param index 索引位置
     * @return 元素值
     * @throws IndexOutOfBoundsException 如果索引越界
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }
    
    /**
     * 二分查找
     * @param value 要查找的值
     * @return 元素的索引，如果不存在返回-1
     */
    private int binarySearch(int value) {
        int left = 0, right = size - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (data[mid] == value) {
                return mid;
            } else if (data[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * 找到插入位置，保持数组有序
     * @param value 要插入的值
     * @return 插入位置的索引
     */
    private int findInsertPosition(int value) {
        int left = 0, right = size;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (data[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * 转换为数组
     * @return 包含所有元素的数组副本
     */
    public int[] toArray() {
        int[] result = new int[size];
        System.arraycopy(data, 0, result, 0, size);
        return result;
    }
    
    /**
     * 转换为字符串表示
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