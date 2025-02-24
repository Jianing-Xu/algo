package recursion;

import java.util.HashMap;
import java.util.Map;

import utils.PrintUtil;

public class RecursionAlgo {

    // 编程实现斐波那契数列求值f(n)=f(n-1)+f(n-2)
    Map<Integer, Integer> solvedMap = new HashMap<>();

    public int fibonacci(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        if (solvedMap.containsKey(n)) {
            return solvedMap.get(n);
        }
        int ret = fibonacci(n - 1) + fibonacci(n - 2);
        solvedMap.put(n, ret);
        return ret;
    }

    public int fibonacci2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int pre = 1;
        int ret = 1;
        for (int i = 3; i <= n; i++) {
            int temp = pre;
            pre = ret;
            ret = temp + ret;
        }
        return ret;
    }

    // 编程实现求阶乘n!
    public int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }


    // 编程实现一组数据集合的全排列
    public void permutation(int[] arr, int index) {
        if (index == arr.length - 1) {
            PrintUtil.printArray(arr);
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            permutation(arr, index + 1);
            swap(arr, index, i);
        }
    }

    private void swap(int[] arr, int index, int i) {
        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        RecursionAlgo instance = new RecursionAlgo();
        int[] arr = new int[]{1, 2, 3, 4};
        instance.permutation(arr, 0);
    }
}
