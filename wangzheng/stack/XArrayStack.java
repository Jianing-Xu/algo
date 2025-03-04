package stack;

import utils.PrintUtil;

// 用数组实现一个顺序栈
public class XArrayStack {

    int[] data;
    int size;

    public XArrayStack(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    public boolean push(int value) {
        if (size == data.length) {
            return false;
        }
        data[size++] = value;
        return true;
    }

    public int pop() {
        if (size == 0) {
            return -1;
        }
        int datum = data[size - 1];
        data[--size] = 0;
        return datum;
    }

    public static void main(String[] args) {
        XArrayStack stack = new XArrayStack(3);
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        PrintUtil.printArray(stack.data);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        PrintUtil.printArray(stack.data);
    }
}
