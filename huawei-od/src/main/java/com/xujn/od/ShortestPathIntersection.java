package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：路口最短时间问题
 *
 * 【解题思路：Dijkstra 单源最短路径】
 */
public class ShortestPathIntersection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }
        int start = sc.nextInt(), end = sc.nextInt();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[1] > dist[cur[0]]) continue;
            if (cur[0] == end) break;
            for (int[] e : adj[cur[0]]) {
                if (dist[cur[0]] + e[1] < dist[e[0]]) {
                    dist[e[0]] = dist[cur[0]] + e[1];
                    pq.offer(new int[]{e[0], dist[e[0]]});
                }
            }
        }
        System.out.println(dist[end] == Integer.MAX_VALUE ? -1 : dist[end]);
    }
}
