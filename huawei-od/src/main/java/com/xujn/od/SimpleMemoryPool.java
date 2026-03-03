package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：简易内存池
 *
 * 【题目描述】
 * 实现一个简易内存池，支持:
 * REQUEST=size：申请size大小的连续内存，返回起始地址。内存不足返回error。
 * RELEASE=addr：释放以addr开头的已申请内存块。
 *
 * 内存池总大小为100，地址从0开始。使用首次适配(First Fit)策略。
 *
 * 【解题思路：模拟 + 有序区间管理】
 */
public class SimpleMemoryPool {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalSize = 100;
        // 记录已分配块：起始地址 -> 大小
        TreeMap<Integer, Integer> allocated = new TreeMap<>();
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            if (line.startsWith("REQUEST=")) {
                int size = Integer.parseInt(line.substring(8));
                int addr = allocate(allocated, totalSize, size);
                if (addr == -1) {
                    System.out.println("error");
                } else {
                    System.out.println(addr);
                }
            } else if (line.startsWith("RELEASE=")) {
                int addr = Integer.parseInt(line.substring(8));
                if (allocated.containsKey(addr)) {
                    allocated.remove(addr);
                } else {
                    System.out.println("error");
                }
            }
        }
    }

    // First Fit 策略：找到第一个能放下 size 的空闲区间
    private static int allocate(TreeMap<Integer, Integer> allocated, int totalSize, int size) {
        int start = 0;

        for (Map.Entry<Integer, Integer> entry : allocated.entrySet()) {
            int blockStart = entry.getKey();
            int blockSize = entry.getValue();

            // [start, blockStart) 是空闲区间
            if (blockStart - start >= size) {
                allocated.put(start, size);
                return start;
            }
            start = blockStart + blockSize;
        }

        // 检查最后一段空闲区间
        if (totalSize - start >= size) {
            allocated.put(start, size);
            return start;
        }

        return -1; // 内存不足
    }
}
