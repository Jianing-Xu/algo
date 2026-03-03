package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：关联端口组合并
 *
 * 【题目描述】
 * 有N组端口组，每组包含若干端口号。如果两个端口组有交集（有相同端口号），
 * 就将它们合并成一个组。输出最终合并后的所有端口组。
 *
 * 【解题思路：并查集 (Union-Find)】
 * 经典的"合并有交集的集合"问题。
 * 对每个端口号建立映射，相同端口号的组合并到一起。
 */
public class PortGroupMerge {

    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // 端口号 -> 第一次出现在哪个组
        Map<Integer, Integer> portToGroup = new HashMap<>();

        List<List<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(",");
            List<Integer> ports = new ArrayList<>();
            for (String p : parts) {
                int port = Integer.parseInt(p.trim());
                ports.add(port);
                if (portToGroup.containsKey(port)) {
                    // 这个端口已经在某个组中出现过了，合并两个组
                    union(i, portToGroup.get(port));
                } else {
                    portToGroup.put(port, i);
                }
            }
            groups.add(ports);
        }

        // 按根节点分组输出
        Map<Integer, Set<Integer>> result = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            result.computeIfAbsent(root, k -> new TreeSet<>());
            result.get(root).addAll(groups.get(i));
        }

        for (Set<Integer> set : result.values()) {
            StringBuilder sb = new StringBuilder();
            for (int port : set) {
                if (sb.length() > 0) sb.append(",");
                sb.append(port);
            }
            System.out.println(sb.toString());
        }
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra != rb) parent[ra] = rb;
    }
}
