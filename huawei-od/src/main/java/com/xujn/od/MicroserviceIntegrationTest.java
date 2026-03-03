package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：微服务的集成测试
 *
 * 【题目描述】
 * 有N个微服务，某些服务依赖其他服务。给定依赖关系，
 * 按照层级输出集成测试的执行顺序（同一层级的服务可以并行测试）。
 * 本质上是拓扑排序的层序输出。
 *
 * 【解题思路：拓扑排序（Kahn BFS层序）】
 */
public class MicroserviceIntegrationTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 服务数

        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        int m = sc.nextInt(); // 依赖关系数
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt(); // from 依赖 to，即 to 要先执行
            adj.get(to).add(from);
            inDegree[from]++;
        }

        // BFS 层序拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int s = 0; s < size; s++) {
                int node = queue.poll();
                currentLevel.add(node);
                for (int next : adj.get(node)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) queue.offer(next);
                }
            }
            Collections.sort(currentLevel);
            StringBuilder sb = new StringBuilder();
            sb.append("Level ").append(level).append(": ");
            for (int i = 0; i < currentLevel.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(currentLevel.get(i));
            }
            System.out.println(sb.toString());
            level++;
        }
    }
}
