package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：产品模块算法执行顺序（拓扑排序变体）
 *
 * 【题目描述】
 * 产品中有N个模块，模块间有依赖关系。给一个依赖关系列表，
 * 输出一个合法的模块执行顺序。如果存在循环依赖，输出-1。
 *
 * 【解题思路：拓扑排序 + 环检测】
 */
public class ModuleExecutionOrder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 模块数
        int m = sc.nextInt(); // 依赖数

        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt(); // a 依赖 b，b先执行
            adj.get(b).add(a);
            inDegree[a]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int next : adj.get(node)) {
                inDegree[next]--;
                if (inDegree[next] == 0) queue.offer(next);
            }
        }

        if (order.size() != n) {
            System.out.println(-1); // 存在环
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < order.size(); i++) {
                if (i > 0) sb.append(" ");
                sb.append(order.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}
