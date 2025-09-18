package graph;

import java.util.*;

/**
 * 图的基础实现
 * 支持有向图、无向图、有权图、无权图
 * 提供邻接矩阵和邻接表两种表示方法
 */
public class Graph {
    
    private int vertices;                           // 顶点数
    private boolean isDirected;                     // 是否为有向图
    private boolean isWeighted;                     // 是否为有权图
    
    // 邻接表表示
    private List<List<Edge>> adjacencyList;
    
    // 邻接矩阵表示
    private int[][] adjacencyMatrix;
    private double[][] weightMatrix;
    
    /**
     * 边的定义
     */
    public static class Edge {
        public int from;        // 起始顶点
        public int to;          // 目标顶点
        public double weight;   // 权重
        
        public Edge(int from, int to) {
            this(from, to, 1.0);
        }
        
        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public String toString() {
            return from + " -> " + to + (weight != 1.0 ? " (" + weight + ")" : "");
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Edge edge = (Edge) obj;
            return from == edge.from && to == edge.to && 
                   Double.compare(edge.weight, weight) == 0;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
    }
    
    /**
     * 构造函数
     * 
     * @param vertices 顶点数
     * @param isDirected 是否为有向图
     * @param isWeighted 是否为有权图
     */
    public Graph(int vertices, boolean isDirected, boolean isWeighted) {
        this.vertices = vertices;
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        
        // 初始化邻接表
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        
        // 初始化邻接矩阵
        this.adjacencyMatrix = new int[vertices][vertices];
        if (isWeighted) {
            this.weightMatrix = new double[vertices][vertices];
            // 初始化权重矩阵，不存在的边权重为无穷大
            for (int i = 0; i < vertices; i++) {
                Arrays.fill(weightMatrix[i], Double.POSITIVE_INFINITY);
                weightMatrix[i][i] = 0; // 自环权重为0
            }
        }
    }
    
    /**
     * 添加边
     * 
     * @param from 起始顶点
     * @param to 目标顶点
     */
    public void addEdge(int from, int to) {
        addEdge(from, to, 1.0);
    }
    
    /**
     * 添加带权重的边
     * 
     * @param from 起始顶点
     * @param to 目标顶点
     * @param weight 权重
     */
    public void addEdge(int from, int to, double weight) {
        validateVertex(from);
        validateVertex(to);
        
        // 邻接表添加边
        adjacencyList.get(from).add(new Edge(from, to, weight));
        if (!isDirected) {
            adjacencyList.get(to).add(new Edge(to, from, weight));
        }
        
        // 邻接矩阵添加边
        adjacencyMatrix[from][to] = 1;
        if (!isDirected) {
            adjacencyMatrix[to][from] = 1;
        }
        
        // 权重矩阵添加权重
        if (isWeighted) {
            weightMatrix[from][to] = weight;
            if (!isDirected) {
                weightMatrix[to][from] = weight;
            }
        }
    }
    
    /**
     * 删除边
     * 
     * @param from 起始顶点
     * @param to 目标顶点
     */
    public void removeEdge(int from, int to) {
        validateVertex(from);
        validateVertex(to);
        
        // 邻接表删除边
        adjacencyList.get(from).removeIf(edge -> edge.to == to);
        if (!isDirected) {
            adjacencyList.get(to).removeIf(edge -> edge.to == from);
        }
        
        // 邻接矩阵删除边
        adjacencyMatrix[from][to] = 0;
        if (!isDirected) {
            adjacencyMatrix[to][from] = 0;
        }
        
        // 权重矩阵删除权重
        if (isWeighted) {
            weightMatrix[from][to] = Double.POSITIVE_INFINITY;
            if (!isDirected) {
                weightMatrix[to][from] = Double.POSITIVE_INFINITY;
            }
        }
    }
    
    /**
     * 检查是否存在边
     * 
     * @param from 起始顶点
     * @param to 目标顶点
     * @return true如果存在边，否则false
     */
    public boolean hasEdge(int from, int to) {
        validateVertex(from);
        validateVertex(to);
        return adjacencyMatrix[from][to] == 1;
    }
    
    /**
     * 获取边的权重
     * 
     * @param from 起始顶点
     * @param to 目标顶点
     * @return 边的权重，如果不存在返回无穷大
     */
    public double getWeight(int from, int to) {
        validateVertex(from);
        validateVertex(to);
        
        if (!isWeighted) {
            return hasEdge(from, to) ? 1.0 : Double.POSITIVE_INFINITY;
        }
        
        return weightMatrix[from][to];
    }
    
    /**
     * 获取顶点的邻居列表
     * 
     * @param vertex 顶点
     * @return 邻居列表
     */
    public List<Edge> getNeighbors(int vertex) {
        validateVertex(vertex);
        return new ArrayList<>(adjacencyList.get(vertex));
    }
    
    /**
     * 获取顶点的度数
     * 
     * @param vertex 顶点
     * @return 度数
     */
    public int getDegree(int vertex) {
        validateVertex(vertex);
        
        if (isDirected) {
            // 有向图返回出度
            return getOutDegree(vertex);
        } else {
            // 无向图返回度数
            return adjacencyList.get(vertex).size();
        }
    }
    
    /**
     * 获取顶点的入度（仅适用于有向图）
     * 
     * @param vertex 顶点
     * @return 入度
     */
    public int getInDegree(int vertex) {
        validateVertex(vertex);
        
        int inDegree = 0;
        for (int i = 0; i < vertices; i++) {
            if (adjacencyMatrix[i][vertex] == 1) {
                inDegree++;
            }
        }
        return inDegree;
    }
    
    /**
     * 获取顶点的出度（仅适用于有向图）
     * 
     * @param vertex 顶点
     * @return 出度
     */
    public int getOutDegree(int vertex) {
        validateVertex(vertex);
        return adjacencyList.get(vertex).size();
    }
    
    /**
     * 获取所有边
     * 
     * @return 边的列表
     */
    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();
        
        for (int i = 0; i < vertices; i++) {
            for (Edge edge : adjacencyList.get(i)) {
                // 无向图只添加一次边（避免重复）
                if (isDirected || edge.from <= edge.to) {
                    edges.add(edge);
                }
            }
        }
        
        return edges;
    }
    
    /**
     * 获取边的数量
     * 
     * @return 边的数量
     */
    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < vertices; i++) {
            count += adjacencyList.get(i).size();
        }
        
        // 无向图每条边被计算了两次
        return isDirected ? count : count / 2;
    }
    
    /**
     * 深度优先搜索
     * 
     * @param startVertex 起始顶点
     * @return DFS遍历结果
     */
    public List<Integer> depthFirstSearch(int startVertex) {
        validateVertex(startVertex);
        
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[vertices];
        
        dfsRecursive(startVertex, visited, result);
        
        return result;
    }
    
    /**
     * DFS递归辅助方法
     */
    private void dfsRecursive(int vertex, boolean[] visited, List<Integer> result) {
        visited[vertex] = true;
        result.add(vertex);
        
        for (Edge edge : adjacencyList.get(vertex)) {
            if (!visited[edge.to]) {
                dfsRecursive(edge.to, visited, result);
            }
        }
    }
    
    /**
     * 广度优先搜索
     * 
     * @param startVertex 起始顶点
     * @return BFS遍历结果
     */
    public List<Integer> breadthFirstSearch(int startVertex) {
        validateVertex(startVertex);
        
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[startVertex] = true;
        queue.offer(startVertex);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            
            for (Edge edge : adjacencyList.get(vertex)) {
                if (!visited[edge.to]) {
                    visited[edge.to] = true;
                    queue.offer(edge.to);
                }
            }
        }
        
        return result;
    }
    
    /**
     * 检查图是否连通（无向图）或强连通（有向图）
     * 
     * @return true如果连通，否则false
     */
    public boolean isConnected() {
        if (vertices == 0) return true;
        
        // 从顶点0开始DFS
        List<Integer> dfsResult = depthFirstSearch(0);
        
        if (isDirected) {
            // 有向图需要检查强连通性
            // 简化版本：检查是否能从任意顶点到达所有其他顶点
            return dfsResult.size() == vertices && isStronglyConnected();
        } else {
            // 无向图检查是否能访问所有顶点
            return dfsResult.size() == vertices;
        }
    }
    
    /**
     * 检查有向图是否强连通
     */
    private boolean isStronglyConnected() {
        // 简化实现：检查每个顶点是否都能到达其他所有顶点
        for (int i = 0; i < vertices; i++) {
            List<Integer> reachable = depthFirstSearch(i);
            if (reachable.size() != vertices) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 检查是否存在环
     * 
     * @return true如果存在环，否则false
     */
    public boolean hasCycle() {
        if (isDirected) {
            return hasDirectedCycle();
        } else {
            return hasUndirectedCycle();
        }
    }
    
    /**
     * 检查有向图是否存在环
     */
    private boolean hasDirectedCycle() {
        int[] color = new int[vertices]; // 0: 白色(未访问), 1: 灰色(正在访问), 2: 黑色(已完成)
        
        for (int i = 0; i < vertices; i++) {
            if (color[i] == 0 && dfsHasCycle(i, color)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * DFS检查环的辅助方法
     */
    private boolean dfsHasCycle(int vertex, int[] color) {
        color[vertex] = 1; // 标记为正在访问
        
        for (Edge edge : adjacencyList.get(vertex)) {
            if (color[edge.to] == 1) {
                // 遇到正在访问的顶点，说明有环
                return true;
            }
            if (color[edge.to] == 0 && dfsHasCycle(edge.to, color)) {
                return true;
            }
        }
        
        color[vertex] = 2; // 标记为已完成
        return false;
    }
    
    /**
     * 检查无向图是否存在环
     */
    private boolean hasUndirectedCycle() {
        boolean[] visited = new boolean[vertices];
        
        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && dfsUndirectedCycle(i, -1, visited)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 无向图DFS检查环的辅助方法
     */
    private boolean dfsUndirectedCycle(int vertex, int parent, boolean[] visited) {
        visited[vertex] = true;
        
        for (Edge edge : adjacencyList.get(vertex)) {
            if (!visited[edge.to]) {
                if (dfsUndirectedCycle(edge.to, vertex, visited)) {
                    return true;
                }
            } else if (edge.to != parent) {
                // 遇到已访问的非父节点，说明有环
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 拓扑排序（仅适用于有向无环图）
     * 
     * @return 拓扑排序结果，如果图有环返回null
     */
    public List<Integer> topologicalSort() {
        if (!isDirected) {
            throw new UnsupportedOperationException("拓扑排序仅适用于有向图");
        }
        
        if (hasCycle()) {
            return null; // 有环图无法进行拓扑排序
        }
        
        return topologicalSortKahn();
    }
    
    /**
     * Kahn算法实现拓扑排序
     */
    private List<Integer> topologicalSortKahn() {
        List<Integer> result = new ArrayList<>();
        int[] inDegree = new int[vertices];
        
        // 计算所有顶点的入度
        for (int i = 0; i < vertices; i++) {
            inDegree[i] = getInDegree(i);
        }
        
        // 将入度为0的顶点加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // 处理队列中的顶点
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            
            // 减少邻居顶点的入度
            for (Edge edge : adjacencyList.get(vertex)) {
                inDegree[edge.to]--;
                if (inDegree[edge.to] == 0) {
                    queue.offer(edge.to);
                }
            }
        }
        
        return result.size() == vertices ? result : null;
    }
    
    /**
     * 验证顶点是否有效
     */
    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= vertices) {
            throw new IllegalArgumentException("顶点 " + vertex + " 不在有效范围内 [0, " + (vertices - 1) + "]");
        }
    }
    
    // Getter方法
    public int getVertices() { return vertices; }
    public boolean isDirected() { return isDirected; }
    public boolean isWeighted() { return isWeighted; }
    public int[][] getAdjacencyMatrix() { return adjacencyMatrix.clone(); }
    public double[][] getWeightMatrix() { return isWeighted ? weightMatrix.clone() : null; }
    
    /**
     * 打印图的邻接表表示
     */
    public void printAdjacencyList() {
        System.out.println("图的邻接表表示:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("顶点 " + i + ": ");
            for (Edge edge : adjacencyList.get(i)) {
                System.out.print(edge.to);
                if (isWeighted && edge.weight != 1.0) {
                    System.out.print("(" + edge.weight + ")");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    /**
     * 打印图的邻接矩阵表示
     */
    public void printAdjacencyMatrix() {
        System.out.println("图的邻接矩阵表示:");
        System.out.print("   ");
        for (int i = 0; i < vertices; i++) {
            System.out.printf("%3d", i);
        }
        System.out.println();
        
        for (int i = 0; i < vertices; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < vertices; j++) {
                if (isWeighted && weightMatrix[i][j] != Double.POSITIVE_INFINITY) {
                    System.out.printf("%3.0f", weightMatrix[i][j]);
                } else {
                    System.out.printf("%3d", adjacencyMatrix[i][j]);
                }
            }
            System.out.println();
        }
    }
    
    /**
     * 转换为字符串表示
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph{");
        sb.append("vertices=").append(vertices);
        sb.append(", directed=").append(isDirected);
        sb.append(", weighted=").append(isWeighted);
        sb.append(", edges=").append(getEdgeCount());
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 图的基础实现测试 ===\n");
        
        // 测试1：无向无权图
        System.out.println("1. 无向无权图测试:");
        Graph undirectedGraph = new Graph(5, false, false);
        
        // 添加边
        undirectedGraph.addEdge(0, 1);
        undirectedGraph.addEdge(0, 4);
        undirectedGraph.addEdge(1, 2);
        undirectedGraph.addEdge(1, 3);
        undirectedGraph.addEdge(1, 4);
        undirectedGraph.addEdge(2, 3);
        undirectedGraph.addEdge(3, 4);
        
        System.out.println(undirectedGraph);
        undirectedGraph.printAdjacencyList();
        undirectedGraph.printAdjacencyMatrix();
        
        System.out.println("DFS遍历: " + undirectedGraph.depthFirstSearch(0));
        System.out.println("BFS遍历: " + undirectedGraph.breadthFirstSearch(0));
        System.out.println("是否连通: " + undirectedGraph.isConnected());
        System.out.println("是否有环: " + undirectedGraph.hasCycle());
        
        // 测试2：有向无权图
        System.out.println("\n2. 有向无权图测试:");
        Graph directedGraph = new Graph(6, true, false);
        
        directedGraph.addEdge(5, 2);
        directedGraph.addEdge(5, 0);
        directedGraph.addEdge(4, 0);
        directedGraph.addEdge(4, 1);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(3, 1);
        
        System.out.println(directedGraph);
        directedGraph.printAdjacencyList();
        
        System.out.println("DFS遍历: " + directedGraph.depthFirstSearch(5));
        System.out.println("BFS遍历: " + directedGraph.breadthFirstSearch(5));
        System.out.println("是否有环: " + directedGraph.hasCycle());
        System.out.println("拓扑排序: " + directedGraph.topologicalSort());
        
        // 测试3：有向有权图
        System.out.println("\n3. 有向有权图测试:");
        Graph weightedGraph = new Graph(4, true, true);
        
        weightedGraph.addEdge(0, 1, 5.0);
        weightedGraph.addEdge(0, 2, 3.0);
        weightedGraph.addEdge(1, 2, 2.0);
        weightedGraph.addEdge(1, 3, 6.0);
        weightedGraph.addEdge(2, 3, 7.0);
        
        System.out.println(weightedGraph);
        weightedGraph.printAdjacencyList();
        weightedGraph.printAdjacencyMatrix();
        
        System.out.println("边(0,1)的权重: " + weightedGraph.getWeight(0, 1));
        System.out.println("边(0,3)的权重: " + weightedGraph.getWeight(0, 3));
        
        // 测试4：度数统计
        System.out.println("\n4. 度数统计测试:");
        for (int i = 0; i < undirectedGraph.getVertices(); i++) {
            System.out.println("顶点 " + i + " 的度数: " + undirectedGraph.getDegree(i));
        }
        
        for (int i = 0; i < directedGraph.getVertices(); i++) {
            System.out.println("顶点 " + i + " 的入度: " + directedGraph.getInDegree(i) + 
                             ", 出度: " + directedGraph.getOutDegree(i));
        }
        
        // 测试5：边操作
        System.out.println("\n5. 边操作测试:");
        Graph testGraph = new Graph(3, false, false);
        testGraph.addEdge(0, 1);
        testGraph.addEdge(1, 2);
        
        System.out.println("添加边后:");
        testGraph.printAdjacencyList();
        System.out.println("边(0,1)存在: " + testGraph.hasEdge(0, 1));
        System.out.println("边(0,2)存在: " + testGraph.hasEdge(0, 2));
        
        testGraph.removeEdge(0, 1);
        System.out.println("删除边(0,1)后:");
        testGraph.printAdjacencyList();
        System.out.println("边(0,1)存在: " + testGraph.hasEdge(0, 1));
        
        // 测试6：环检测
        System.out.println("\n6. 环检测测试:");
        
        // 无环有向图
        Graph acyclicDirected = new Graph(4, true, false);
        acyclicDirected.addEdge(0, 1);
        acyclicDirected.addEdge(0, 2);
        acyclicDirected.addEdge(1, 3);
        acyclicDirected.addEdge(2, 3);
        System.out.println("无环有向图有环: " + acyclicDirected.hasCycle());
        
        // 有环有向图
        Graph cyclicDirected = new Graph(3, true, false);
        cyclicDirected.addEdge(0, 1);
        cyclicDirected.addEdge(1, 2);
        cyclicDirected.addEdge(2, 0);
        System.out.println("有环有向图有环: " + cyclicDirected.hasCycle());
        
        // 测试7：性能测试
        System.out.println("\n7. 性能测试:");
        int n = 1000;
        Graph largeGraph = new Graph(n, false, false);
        
        long startTime = System.currentTimeMillis();
        
        // 创建随机图
        Random random = new Random();
        for (int i = 0; i < n * 2; i++) {
            int u = random.nextInt(n);
            int v = random.nextInt(n);
            if (u != v && !largeGraph.hasEdge(u, v)) {
                largeGraph.addEdge(u, v);
            }
        }
        
        long constructTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        List<Integer> dfsResult = largeGraph.depthFirstSearch(0);
        long dfsTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        List<Integer> bfsResult = largeGraph.breadthFirstSearch(0);
        long bfsTime = System.currentTimeMillis() - startTime;
        
        System.out.println("图构建耗时: " + constructTime + "ms");
        System.out.println("图信息: " + largeGraph);
        System.out.println("DFS耗时: " + dfsTime + "ms, 访问顶点数: " + dfsResult.size());
        System.out.println("BFS耗时: " + bfsTime + "ms, 访问顶点数: " + bfsResult.size());
        
        // 测试8：边界情况
        System.out.println("\n8. 边界情况测试:");
        
        // 空图
        Graph emptyGraph = new Graph(0, false, false);
        System.out.println("空图: " + emptyGraph);
        System.out.println("空图连通: " + emptyGraph.isConnected());
        
        // 单顶点图
        Graph singleVertex = new Graph(1, false, false);
        System.out.println("单顶点图: " + singleVertex);
        System.out.println("单顶点图连通: " + singleVertex.isConnected());
        System.out.println("单顶点图DFS: " + singleVertex.depthFirstSearch(0));
        
        // 完全图
        Graph completeGraph = new Graph(4, false, false);
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                completeGraph.addEdge(i, j);
            }
        }
        System.out.println("完全图: " + completeGraph);
        System.out.println("完全图连通: " + completeGraph.isConnected());
        System.out.println("完全图有环: " + completeGraph.hasCycle());
    }
}