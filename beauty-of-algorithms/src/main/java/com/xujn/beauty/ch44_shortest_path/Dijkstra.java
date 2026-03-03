package com.xujn.beauty.ch44_shortest_path;

import java.util.LinkedList;

/**
 * 单源最短路径算法：Dijkstra (迪杰斯特拉算法)
 * 
 * 【核心原理】
 * Dijkstra 用来解决“带权有向图中，从一个固定起点（单源）到其他所有节点的最短路径”。
 * 最经典的场景：地图导航中计算总耗时或者总距离最短的行车路线。
 * 
 * 【工作机制】
 * 它和广度优先搜索 (BFS) 十分相似，也是一圈一圈往外扫描。但是普通的BFS只能适用于没有权重的边。
 * 迪杰斯特拉算法借助了【优先队列 (小顶堆)】。
 * - 声明一个数组 dist，用来记录 从起点 到 顶点i 的“目前已知的最短距离”。
 * - 不断从优先队列中取出“距离起点目前最近”的那个点，然后用这个点去“松弛” (Relax) 它的相邻节点。
 * - 如果通过该点，能跳板使得到达邻接点的总距离变得更短，我们就更新邻接点的最短距离，并重新入堆。
 * - 直到我们取出要找的最总终点，或者优先队列空掉为止。
 */
public class Dijkstra {

    // 有向有权图的边定义
    private class Edge {
        public int sid; // 边的起始顶点编号
        public int tid; // 边的终止顶点编号
        public int w; // 边的权重（比如地图上两个路口的距离）

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    // 给优先队列使用的顶点包装类，用于按照距起点距离排队
    private class Vertex {
        public int id;    // 顶点编号ID
        public int dist;  // 当前从起始顶点出发到这个顶点的“最短距离”

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    // 优先队列（利用数组充当一个小顶堆，用于频繁找出距离最小的点）
    private class PriorityQueue {
        private Vertex[] nodes;
        private int count;

        public PriorityQueue(int v) {
            nodes = new Vertex[v + 1];
            count = 0;
        }

        public Vertex poll() {
            // 这里为了简化代码写了暴力遍历获取出队元素，
            // 生产环境下为了O(logN)应当实现标准的小顶堆结构并写出 siftDown / siftUp。
            if (count == 0) return null;
            int minVertexIndex = 1;
            for (int i = 2; i <= count; ++i) {
                if (nodes[i].dist < nodes[minVertexIndex].dist) {
                    minVertexIndex = i;
                }
            }
            Vertex minVertex = nodes[minVertexIndex];
            // 把最后一个元素覆盖到极小值位置，相当于删除，个数减 1
            nodes[minVertexIndex] = nodes[count];
            count--;
            return minVertex;
        }

        public void add(Vertex vertex) {
            nodes[++count] = vertex;
        }

        // 在堆内更新已存在节点的值
        public void update(Vertex vertex) {
            for (int i = 1; i <= count; ++i) {
                if (nodes[i].id == vertex.id) {
                    nodes[i].dist = vertex.dist;
                    break;
                }
            }
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }

    // 图
    public class Graph {
        private LinkedList<Edge>[] adj; // 邻接表
        private int v; // 顶点个数

        @SuppressWarnings("unchecked")
        public Graph(int v) {
            this.v = v;
            this.adj = new LinkedList[v];
            for (int i = 0; i < v; ++i) {
                this.adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int t, int w) {
            this.adj[s].add(new Edge(s, t, w));
        }

        /**
         * 迪杰斯特拉核心算法：从起点 s 到终点 t 的最短路径
         */
        public void dijkstra(int s, int t) {
            int[] predecessor = new int[this.v]; // 用于还原最终的最短路径，存放每个节点的前驱是谁
            // 用于包揽所有点从 s 走到它们的路径花费大全。初始化为一个极大的数字表示没走到过
            Vertex[] vertexes = new Vertex[this.v];
            for (int i = 0; i < this.v; ++i) {
                vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
            }
            
            PriorityQueue queue = new PriorityQueue(this.v); // 小顶堆队列
            
            // 是否已经被锁定最短路径的布尔标志(已经在堆外了，不需要再松弛更新了)
            boolean[] inqueue = new boolean[this.v];
            
            // 起点距离自己为 0
            vertexes[s].dist = 0;
            queue.add(vertexes[s]);
            inqueue[s] = true;

            // 如果队列没空，我们持续扫描最近点向外围发散
            while (!queue.isEmpty()) {
                Vertex minVertex = queue.poll(); // 拿到目前手里已知距离起点【最近】的点
                
                // 【找到终点了！】一旦 t 这个最终目标被人从那个最小堆里取出来，
                // 说明从 s 去 t 已经彻底尘埃落定不再可能被任何绕远路的方案再覆盖变得更短，算法结束！
                if (minVertex.id == t) break; 
                
                // 利用手里这个节点去考察、访问他背后相通的所有朋友路口（尝试为他们也创造更短路径）
                for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                    Edge e = adj[minVertex.id].get(i); 
                    Vertex nextVertex = vertexes[e.tid]; // 那个即将被访问的路口兄弟
                    
                    // 【重要：松弛操作 Relax】
                    // 如果（走我这里到达你的总距离） 比你 （以往发现的一条老路到达你的总距离） 还要短
                    // 那你就把路径前驱标记修改成我！把我更新成你的最短距离！
                    if (minVertex.dist + e.w < nextVertex.dist) {
                        nextVertex.dist = minVertex.dist + e.w;
                        predecessor[nextVertex.id] = minVertex.id;
                        
                        // 如果它之前已经被在队列中了，通知它“你的分数改变了哦，你在小顶堆需要挪个更靠前的排序位置了”
                        if (inqueue[nextVertex.id] == true) {
                            queue.update(nextVertex);
                        } else {
                            // 否则说明这帮子朋友以前还没进队列等候过，加进去
                            queue.add(nextVertex);
                            inqueue[nextVertex.id] = true;
                        }
                    }
                }
            }
            // 结果输出：通过前驱反向追溯把路径拼接打印
            System.out.print(s);
            print(s, t, predecessor);
            System.out.println("。距离总花费为: " + vertexes[t].dist);
        }

        private void print(int s, int t, int[] predecessor) {
            if (s == t) return;
            // 递归，因为 predecessor 存的是从屁股到头的前驱指引，递归可以在压栈后将其倒退为正序
            print(s, predecessor[t], predecessor);
            System.out.print(" -> " + t);
        }
    }
}
