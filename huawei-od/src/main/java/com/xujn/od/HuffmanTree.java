package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：生成哈夫曼树
 *
 * 【题目描述】
 * 给定一组权值，据此构建哈夫曼树并输出其带权路径长度(WPL)。
 * 
 * 哈夫曼树构建规则：
 * 1. 从权值集合中选出两个最小的值
 * 2. 将它们合并为一个新节点（权值为两者之和）
 * 3. 重复，直到只剩一个节点
 * WPL = 所有叶子节点的 权值 × 深度 之和
 *
 * 【解题思路：优先队列（小顶堆）模拟】
 * 每次从堆中取出两个最小值，合并后放回堆中，累加合并的中间权值即为WPL。
 *
 * 巧妙之处：每次合并时加到总和里的值，累加起来恰好就是WPL！
 * 因为越早合并的节点越靠底层，它们的权值会被多次累加。
 */
public class HuffmanTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            minHeap.offer(sc.nextInt());
        }

        long wpl = 0;

        while (minHeap.size() > 1) {
            int first = minHeap.poll();  // 取出最小
            int second = minHeap.poll(); // 取出次小
            int merged = first + second;
            wpl += merged; // 每次合并的代价就是WPL的一部分
            minHeap.offer(merged); // 合并后放回
        }

        System.out.println(wpl);
    }
}
