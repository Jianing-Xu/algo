package com.xujn.beauty.ch43_topo;

import java.util.LinkedList;

/**
 * 拓扑排序 (Topological Sorting)
 *
 * 【核心原理】
 * 拓扑排序是专门用于处理【有向无环图(DAG)】的，用来解决“依赖关系”的一种算法。
 * 比如我们在编译程序源码时，A 模块依赖 B 模块，B 依赖 C 模块。
 * 拓扑排序能给出一个序列（C -> B -> A），让你按照这个序列去干活，永远不会出现“要执行的模块前置条件没完成”的情况。
 *
 * 两种经典算法思路：
 * 
 * 1. Kahn 算法 (基于贪心思想)：
 *    统计图中所有顶点的入度（有多少个箭头指着它）。
 *    如果入度为 0，说明没有任何人限制它，它现在就可以立刻执行。
 *    把入度为 0 的节点加入执行队列，执行它并去掉它指出去的所有的边（即它指向后续节点的入度减 1）。
 *    继续找接下来入度变成 0 的节点，周而复始。
 * 
 * 2. DFS 深度优先搜索算法：
 *    采用递归从图中的某个节点走到黑。最底层的节点必定是没有后续依赖的叶子节点，它应该最先被执行。
 *    所以我们在递归（DFS）返回的时候打印/记录当前节点，最先回溯的就是被依赖最深的节点。
 *    注意：DFS版本通常需要借助“逆邻接表”（把边反过来：本来 s->t 代表s依赖t，现在改成 s依赖t，邻接表存 t->s）。
 */
public class TopoSort {

    /**
     * 图的简单表示：使用带有 V 个顶点的邻接表来表示有向图
     */
    public static class Graph {
        private int v; // 顶点的个数
        private LinkedList<Integer>[] adj; // 邻接表

        @SuppressWarnings("unchecked")
        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i) {
                adj[i] = new LinkedList<>();
            }
        }

        // 有向图，s 先于 t，边 s->t
        public void addEdge(int s, int t) {
            adj[s].add(t);
        }

        /**
         * 基于 Kahn 算法实现的拓扑排序
         */
        public void topoSortByKahn() {
            int[] inDegree = new int[v]; // 统计每个顶点的入度
            
            // 预处理：计算所有节点的初始入度
            for (int i = 0; i < v; ++i) {
                for (int j = 0; j < adj[i].size(); ++j) {
                    int w = adj[i].get(j); // 意味着有一条 i -> w 的边
                    inDegree[w]++;         // 此时指向了 w，所以 w 的入度加一
                }
            }

            LinkedList<Integer> queue = new LinkedList<>();
            // 将所有入度为 0 的节点（也就是没有被任何前置条件限制的节点）放入队列
            for (int i = 0; i < v; ++i) {
                if (inDegree[i] == 0) queue.add(i);
            }
            
            // 循环处理入度为0的节点
            while (!queue.isEmpty()) {
                int i = queue.remove();
                System.out.print("->" + i); // 被从队列里拿出来，代表它现在被执行了
                
                // 执行完它之后，由于它被移除了，那么那些必须等它执行完才能执行的任务，就少了一个限制
                for (int j = 0; j < adj[i].size(); ++j) {
                    int k = adj[i].get(j);
                    inDegree[k]--; // 把 k 的入度减掉 1
                    
                    // 如果减完后，发现竟然没人限制 k 了，就把 k 放入“立刻可以执行”的队列里
                    if (inDegree[k] == 0) {
                        queue.add(k);
                    }
                }
            }
            System.out.println();
        }

        /**
         * 基于 DFS (深度优先搜索) 实现的拓扑排序
         */
        public void topoSortByDFS() {
            // 第一步：构建逆邻接表 (为了方便：我们要先找到谁被依赖得最深)
            LinkedList<Integer>[] inverseAdj = new LinkedList[v];
            for (int i = 0; i < v; ++i) {
                inverseAdj[i] = new LinkedList<>();
            }
            
            // 将原来的边s->t转换为底部的边t->s，生成逆向图
            for (int i = 0; i < v; ++i) {
                for (int j = 0; j < adj[i].size(); ++j) {
                    int w = adj[i].get(j); // 原图是 i -> w
                    inverseAdj[w].add(i);  // 逆向是 w -> i
                }
            }

            boolean[] visited = new boolean[v];
            for (int i = 0; i < v; ++i) { // 从0开始DFS，如果没被访问就进去深深地走一遭
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i, inverseAdj, visited);
                }
            }
            System.out.println();
        }

        // DFS递归方法
        private void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
            for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
                int w = inverseAdj[vertex].get(i);
                if (visited[w]) continue;
                visited[w] = true;
                dfs(w, inverseAdj, visited); // 继续往下深钻
            } // 等到这里for走完，说明后续全部干完了回溯回来了
            
            // 【核心精髓】：最后再打印当前顶点，保证自己(也就是最底层的依赖)最先输出
            System.out.print("->" + vertex);
        }
    }
}
