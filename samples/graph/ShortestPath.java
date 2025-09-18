package graph;

import java.util.*;

/**
 * 最短路径算法实现
 * 包含Dijkstra算法、A*算法、Floyd-Warshall算法等
 */
public class ShortestPath {
    
    /**
     * 路径结果类
     */
    public static class PathResult {
        public double distance;         // 最短距离
        public List<Integer> path;      // 最短路径
        public boolean hasPath;         // 是否存在路径
        
        public PathResult() {
            this.distance = Double.POSITIVE_INFINITY;
            this.path = new ArrayList<>();
            this.hasPath = false;
        }
        
        public PathResult(double distance, List<Integer> path) {
            this.distance = distance;
            this.path = new ArrayList<>(path);
            this.hasPath = !path.isEmpty();
        }
        
        @Override
        public String toString() {
            if (!hasPath) {
                return "无路径";
            }
            return "距离: " + distance + ", 路径: " + path;
        }
    }
    
    /**
     * Dijkstra算法 - 单源最短路径
     * 适用于非负权重的图
     * 时间复杂度：O((V + E) log V)，使用优先队列优化
     * 
     * @param graph 图
     * @param source 源顶点
     * @return 从源顶点到所有其他顶点的最短距离数组
     */
    public static double[] dijkstra(Graph graph, int source) {
        int vertices = graph.getVertices();
        double[] distances = new double[vertices];
        boolean[] visited = new boolean[vertices];
        
        // 初始化距离数组
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[source] = 0;
        
        // 优先队列存储 (距离, 顶点) 对
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distance));
        pq.offer(new Node(source, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            
            if (visited[u]) {
                continue;
            }
            
            visited[u] = true;
            
            // 松弛操作：更新邻居顶点的距离
            for (Graph.Edge edge : graph.getNeighbors(u)) {
                int v = edge.to;
                double weight = edge.weight;
                
                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.offer(new Node(v, distances[v]));
                }
            }
        }
        
        return distances;
    }
    
    /**
     * Dijkstra算法 - 返回完整路径信息
     * 
     * @param graph 图
     * @param source 源顶点
     * @param target 目标顶点
     * @return 路径结果
     */
    public static PathResult dijkstraPath(Graph graph, int source, int target) {
        int vertices = graph.getVertices();
        double[] distances = new double[vertices];
        int[] previous = new int[vertices];
        boolean[] visited = new boolean[vertices];
        
        // 初始化
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        Arrays.fill(previous, -1);
        distances[source] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distance));
        pq.offer(new Node(source, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            
            if (visited[u]) {
                continue;
            }
            
            visited[u] = true;
            
            // 如果到达目标顶点，可以提前结束
            if (u == target) {
                break;
            }
            
            for (Graph.Edge edge : graph.getNeighbors(u)) {
                int v = edge.to;
                double weight = edge.weight;
                
                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    previous[v] = u;
                    pq.offer(new Node(v, distances[v]));
                }
            }
        }
        
        // 构建路径
        if (distances[target] == Double.POSITIVE_INFINITY) {
            return new PathResult(); // 无路径
        }
        
        List<Integer> path = new ArrayList<>();
        int current = target;
        while (current != -1) {
            path.add(0, current);
            current = previous[current];
        }
        
        return new PathResult(distances[target], path);
    }
    
    /**
     * A*算法 - 启发式最短路径搜索
     * 使用启发式函数加速搜索过程
     * 
     * @param graph 图
     * @param source 源顶点
     * @param target 目标顶点
     * @param heuristic 启发式函数
     * @return 路径结果
     */
    public static PathResult aStar(Graph graph, int source, int target, HeuristicFunction heuristic) {
        int vertices = graph.getVertices();
        double[] gScore = new double[vertices];  // 从起点到当前点的实际距离
        double[] fScore = new double[vertices];  // gScore + 启发式估计距离
        int[] previous = new int[vertices];
        boolean[] visited = new boolean[vertices];
        
        // 初始化
        Arrays.fill(gScore, Double.POSITIVE_INFINITY);
        Arrays.fill(fScore, Double.POSITIVE_INFINITY);
        Arrays.fill(previous, -1);
        
        gScore[source] = 0;
        fScore[source] = heuristic.estimate(source, target);
        
        // 优先队列按fScore排序
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distance));
        openSet.offer(new Node(source, fScore[source]));
        
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            int u = current.vertex;
            
            if (visited[u]) {
                continue;
            }
            
            visited[u] = true;
            
            // 到达目标
            if (u == target) {
                List<Integer> path = new ArrayList<>();
                int curr = target;
                while (curr != -1) {
                    path.add(0, curr);
                    curr = previous[curr];
                }
                return new PathResult(gScore[target], path);
            }
            
            for (Graph.Edge edge : graph.getNeighbors(u)) {
                int v = edge.to;
                
                if (visited[v]) {
                    continue;
                }
                
                double tentativeGScore = gScore[u] + edge.weight;
                
                if (tentativeGScore < gScore[v]) {
                    previous[v] = u;
                    gScore[v] = tentativeGScore;
                    fScore[v] = gScore[v] + heuristic.estimate(v, target);
                    
                    openSet.offer(new Node(v, fScore[v]));
                }
            }
        }
        
        return new PathResult(); // 无路径
    }
    
    /**
     * Floyd-Warshall算法 - 所有顶点对之间的最短路径
     * 时间复杂度：O(V³)，空间复杂度：O(V²)
     * 
     * @param graph 图
     * @return 距离矩阵
     */
    public static double[][] floydWarshall(Graph graph) {
        int vertices = graph.getVertices();
        double[][] distances = new double[vertices][vertices];
        
        // 初始化距离矩阵
        for (int i = 0; i < vertices; i++) {
            Arrays.fill(distances[i], Double.POSITIVE_INFINITY);
            distances[i][i] = 0; // 自己到自己的距离为0
        }
        
        // 填入直接边的权重
        for (int i = 0; i < vertices; i++) {
            for (Graph.Edge edge : graph.getNeighbors(i)) {
                distances[i][edge.to] = edge.weight;
            }
        }
        
        // Floyd-Warshall核心算法
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
        
        return distances;
    }
    
    /**
     * Bellman-Ford算法 - 单源最短路径（支持负权重）
     * 时间复杂度：O(VE)
     * 
     * @param graph 图
     * @param source 源顶点
     * @return 距离数组，如果存在负权环返回null
     */
    public static double[] bellmanFord(Graph graph, int source) {
        int vertices = graph.getVertices();
        double[] distances = new double[vertices];
        
        // 初始化距离
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[source] = 0;
        
        // 松弛操作，重复V-1次
        for (int i = 0; i < vertices - 1; i++) {
            boolean updated = false;
            
            for (Graph.Edge edge : graph.getAllEdges()) {
                int u = edge.from;
                int v = edge.to;
                double weight = edge.weight;
                
                if (distances[u] != Double.POSITIVE_INFINITY && 
                    distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    updated = true;
                }
            }
            
            // 如果这一轮没有更新，可以提前结束
            if (!updated) {
                break;
            }
        }
        
        // 检查负权环
        for (Graph.Edge edge : graph.getAllEdges()) {
            int u = edge.from;
            int v = edge.to;
            double weight = edge.weight;
            
            if (distances[u] != Double.POSITIVE_INFINITY && 
                distances[u] + weight < distances[v]) {
                return null; // 存在负权环
            }
        }
        
        return distances;
    }
    
    /**
     * 启发式函数接口
     */
    public interface HeuristicFunction {
        /**
         * 估计从当前顶点到目标顶点的距离
         * 
         * @param current 当前顶点
         * @param target 目标顶点
         * @return 估计距离
         */
        double estimate(int current, int target);
    }
    
    /**
     * 曼哈顿距离启发式函数（适用于网格图）
     */
    public static class ManhattanHeuristic implements HeuristicFunction {
        private int[][] coordinates;
        
        public ManhattanHeuristic(int[][] coordinates) {
            this.coordinates = coordinates;
        }
        
        @Override
        public double estimate(int current, int target) {
            int[] currentPos = coordinates[current];
            int[] targetPos = coordinates[target];
            return Math.abs(currentPos[0] - targetPos[0]) + Math.abs(currentPos[1] - targetPos[1]);
        }
    }
    
    /**
     * 欧几里得距离启发式函数
     */
    public static class EuclideanHeuristic implements HeuristicFunction {
        private double[][] coordinates;
        
        public EuclideanHeuristic(double[][] coordinates) {
            this.coordinates = coordinates;
        }
        
        @Override
        public double estimate(int current, int target) {
            double[] currentPos = coordinates[current];
            double[] targetPos = coordinates[target];
            double dx = currentPos[0] - targetPos[0];
            double dy = currentPos[1] - targetPos[1];
            return Math.sqrt(dx * dx + dy * dy);
        }
    }
    
    /**
     * 节点类（用于优先队列）
     */
    private static class Node {
        int vertex;
        double distance;
        
        Node(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
    
    /**
     * 打印距离矩阵
     */
    public static void printDistanceMatrix(double[][] distances) {
        int n = distances.length;
        System.out.println("距离矩阵:");
        
        // 打印表头
        System.out.print("     ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%8d", i);
        }
        System.out.println();
        
        // 打印矩阵
        for (int i = 0; i < n; i++) {
            System.out.printf("%4d ", i);
            for (int j = 0; j < n; j++) {
                if (distances[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.printf("%8s", "∞");
                } else {
                    System.out.printf("%8.1f", distances[i][j]);
                }
            }
            System.out.println();
        }
    }
    
    /**
     * 打印距离数组
     */
    public static void printDistanceArray(double[] distances, int source) {
        System.out.println("从顶点 " + source + " 到各顶点的最短距离:");
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == Double.POSITIVE_INFINITY) {
                System.out.println("到顶点 " + i + ": ∞");
            } else {
                System.out.printf("到顶点 %d: %.1f%n", i, distances[i]);
            }
        }
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 最短路径算法测试 ===\n");
        
        // 创建测试图
        Graph graph = new Graph(6, true, true);
        
        // 添加边 (起点, 终点, 权重)
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 5, 3);
        
        System.out.println("测试图:");
        graph.printAdjacencyList();
        
        // 测试1：Dijkstra算法
        System.out.println("\n1. Dijkstra算法测试:");
        int source = 0;
        double[] dijkstraDistances = dijkstra(graph, source);
        printDistanceArray(dijkstraDistances, source);
        
        // 测试具体路径
        System.out.println("\nDijkstra路径查询:");
        for (int target = 1; target < graph.getVertices(); target++) {
            PathResult result = dijkstraPath(graph, source, target);
            System.out.println("从 " + source + " 到 " + target + ": " + result);
        }
        
        // 测试2：A*算法
        System.out.println("\n2. A*算法测试:");
        
        // 创建简单的启发式函数（这里使用简化版本）
        HeuristicFunction simpleHeuristic = (current, target) -> Math.abs(current - target);
        
        System.out.println("A*路径查询:");
        for (int target = 1; target < graph.getVertices(); target++) {
            PathResult result = aStar(graph, source, target, simpleHeuristic);
            System.out.println("从 " + source + " 到 " + target + ": " + result);
        }
        
        // 测试3：Floyd-Warshall算法
        System.out.println("\n3. Floyd-Warshall算法测试:");
        double[][] allPairsDistances = floydWarshall(graph);
        printDistanceMatrix(allPairsDistances);
        
        // 测试4：Bellman-Ford算法
        System.out.println("\n4. Bellman-Ford算法测试:");
        double[] bellmanFordDistances = bellmanFord(graph, source);
        if (bellmanFordDistances != null) {
            printDistanceArray(bellmanFordDistances, source);
        } else {
            System.out.println("图中存在负权环！");
        }
        
        // 测试5：带负权重的图
        System.out.println("\n5. 负权重图测试:");
        Graph negativeGraph = new Graph(4, true, true);
        negativeGraph.addEdge(0, 1, 1);
        negativeGraph.addEdge(0, 2, 4);
        negativeGraph.addEdge(1, 2, -3);
        negativeGraph.addEdge(1, 3, 2);
        negativeGraph.addEdge(2, 3, 3);
        
        System.out.println("负权重图:");
        negativeGraph.printAdjacencyList();
        
        double[] negativeDistances = bellmanFord(negativeGraph, 0);
        if (negativeDistances != null) {
            printDistanceArray(negativeDistances, 0);
        } else {
            System.out.println("图中存在负权环！");
        }
        
        // 测试6：网格图的A*算法
        System.out.println("\n6. 网格图A*算法测试:");
        
        // 创建4x4网格图
        int gridSize = 4;
        Graph gridGraph = new Graph(gridSize * gridSize, false, true);
        
        // 构建网格图的邻接关系
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int current = i * gridSize + j;
                
                // 右邻居
                if (j < gridSize - 1) {
                    int right = i * gridSize + (j + 1);
                    gridGraph.addEdge(current, right, 1);
                }
                
                // 下邻居
                if (i < gridSize - 1) {
                    int down = (i + 1) * gridSize + j;
                    gridGraph.addEdge(current, down, 1);
                }
            }
        }
        
        // 创建坐标数组用于启发式函数
        int[][] gridCoordinates = new int[gridSize * gridSize][2];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int vertex = i * gridSize + j;
                gridCoordinates[vertex][0] = i;
                gridCoordinates[vertex][1] = j;
            }
        }
        
        ManhattanHeuristic manhattanHeuristic = new ManhattanHeuristic(gridCoordinates);
        
        int gridSource = 0; // 左上角
        int gridTarget = gridSize * gridSize - 1; // 右下角
        
        PathResult gridDijkstra = dijkstraPath(gridGraph, gridSource, gridTarget);
        PathResult gridAStar = aStar(gridGraph, gridSource, gridTarget, manhattanHeuristic);
        
        System.out.println("网格图 " + gridSize + "x" + gridSize + " 从左上角到右下角:");
        System.out.println("Dijkstra: " + gridDijkstra);
        System.out.println("A*算法:   " + gridAStar);
        
        // 测试7：性能比较
        System.out.println("\n7. 性能比较测试:");
        
        // 创建较大的随机图
        int largeVertices = 100;
        Graph largeGraph = new Graph(largeVertices, true, true);
        Random random = new Random(42); // 固定种子保证可重复性
        
        // 添加随机边
        for (int i = 0; i < largeVertices * 3; i++) {
            int u = random.nextInt(largeVertices);
            int v = random.nextInt(largeVertices);
            if (u != v && !largeGraph.hasEdge(u, v)) {
                double weight = 1 + random.nextDouble() * 10;
                largeGraph.addEdge(u, v, weight);
            }
        }
        
        System.out.println("大图测试 (顶点数: " + largeVertices + ", 边数: " + largeGraph.getEdgeCount() + ")");
        
        // Dijkstra性能测试
        long startTime = System.currentTimeMillis();
        double[] largeDijkstra = dijkstra(largeGraph, 0);
        long dijkstraTime = System.currentTimeMillis() - startTime;
        
        // Bellman-Ford性能测试
        startTime = System.currentTimeMillis();
        double[] largeBellmanFord = bellmanFord(largeGraph, 0);
        long bellmanFordTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Dijkstra算法耗时: " + dijkstraTime + "ms");
        System.out.println("Bellman-Ford算法耗时: " + bellmanFordTime + "ms");
        
        // 验证结果一致性
        boolean consistent = true;
        for (int i = 0; i < largeVertices; i++) {
            if (Math.abs(largeDijkstra[i] - largeBellmanFord[i]) > 1e-9) {
                consistent = false;
                break;
            }
        }
        System.out.println("结果一致性: " + (consistent ? "一致" : "不一致"));
        
        // 测试8：边界情况
        System.out.println("\n8. 边界情况测试:");
        
        // 单顶点图
        Graph singleVertex = new Graph(1, true, true);
        double[] singleResult = dijkstra(singleVertex, 0);
        System.out.println("单顶点图距离: " + Arrays.toString(singleResult));
        
        // 不连通图
        Graph disconnected = new Graph(3, true, true);
        disconnected.addEdge(0, 1, 1);
        // 顶点2不连通
        
        double[] disconnectedResult = dijkstra(disconnected, 0);
        System.out.println("不连通图测试:");
        printDistanceArray(disconnectedResult, 0);
        
        PathResult noPath = dijkstraPath(disconnected, 0, 2);
        System.out.println("从0到2的路径: " + noPath);
        
        // 自环图
        Graph selfLoop = new Graph(2, true, true);
        selfLoop.addEdge(0, 0, 5); // 自环
        selfLoop.addEdge(0, 1, 3);
        
        double[] selfLoopResult = dijkstra(selfLoop, 0);
        System.out.println("自环图测试:");
        printDistanceArray(selfLoopResult, 0);
    }
}