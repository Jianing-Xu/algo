package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：查找一个有向网络的头节点和尾节点
 *
 * 【题目描述】
 * 给定若干有向边，找出入度为0的头节点和出度为0的尾节点。
 * 头节点：没有任何边指向它（入度为0）
 * 尾节点：它不指向任何其他节点（出度为0）
 *
 * 【解题思路：统计入度和出度】
 */
public class HeadTailNodes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 边数

        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        Set<Integer> allNodes = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            allNodes.add(from);
            allNodes.add(to);
            outDegree.put(from, outDegree.getOrDefault(from, 0) + 1);
            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
        }

        List<Integer> heads = new ArrayList<>();
        List<Integer> tails = new ArrayList<>();

        for (int node : allNodes) {
            if (inDegree.getOrDefault(node, 0) == 0) heads.add(node);
            if (outDegree.getOrDefault(node, 0) == 0) tails.add(node);
        }

        StringBuilder sb = new StringBuilder();
        for (int h : heads) sb.append(h).append(" ");
        System.out.println(sb.toString().trim());

        sb = new StringBuilder();
        for (int t : tails) sb.append(t).append(" ");
        System.out.println(sb.toString().trim());
    }
}
