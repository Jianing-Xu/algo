package graph;

import java.util.*;

import utils.PrintUtil;

public class XGraph {

    int v; // No. of vertices
    LinkedList<Integer> adj[]; // Adjacency Lists

    XGraph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public void breadthFirstSearch(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[v]; // 是否访问过该节点
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>(); // 按广度入队
        queue.add(s);
        int[] prev = new int[v]; // 记录当前节点的前驱节点
        Arrays.fill(prev, -1);
        while (!queue.isEmpty()) {
            Integer w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                Integer q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    boolean found = false;

    public void deepFirstSearch(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        visited[s] = true;
        int[] prev = new int[v];
        Arrays.fill(prev, -1);
        recurDfs(visited, prev, s, t);
        print(prev, s, t);
    }

    private void recurDfs(boolean[] visited, int[] prev, int s, int t) {
        if (found) return;
        if (s == t) {
            found = true;
            return;
        }
        visited[s] = true;
        for (int i = 0; i < adj[s].size(); i++) {
            int q = adj[s].get(i);
            if (!visited[q]) {
                prev[q] = s;
                recurDfs(visited, prev, q, t);
            }
        }
    }

    public void printMatrix() {
        int[][] matrix = new int[v][v];
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                matrix[i][adj[i].get(j)] = 1;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            PrintUtil.printArray(matrix[i]);
        }
    }


    public static void main(String[] args) {
        XGraph graph = new XGraph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        graph.printMatrix();

        System.out.println("广度优先遍历：");
        graph.breadthFirstSearch(0, 6);
        System.out.println();

        System.out.println("深度优先遍历：");
        graph.deepFirstSearch(0, 6);
        System.out.println();
    }
}
