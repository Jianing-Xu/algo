package graph;

import java.util.LinkedList;

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
        // 无向图一条边存两次
        adj[v].add(w);
        adj[w].add(v);
    }
}
