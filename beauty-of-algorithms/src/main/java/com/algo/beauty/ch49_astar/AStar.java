package com.algo.beauty.ch49_astar;

import java.util.LinkedList;

/**
 * 启发式搜索 (Heuristic Search)：A* (A-Star) 寻路算法
 * 
 * 【背景思想：Dijkstra 算法的痛点】
 * Dijkstra 就像扔进入水池的一颗石子产生的波纹，在保证找出绝对最短路径的时候，它是盲目的：
 * 对于不朝着终点靠拢的岔路和朝着终点走的岔路，Dijkstra 毫无偏好，只要距离短它就去探索。
 * 等它扫描到终点的时候往往已经快把整个地图给找完了，耗费的时间较长。
 * 
 * 【A* 算法的核心原理】
 * A* 算法通过给 Dijkstra 加入一组【评估函数：启发函数 Heuristic function h(n)】来进行优化：
 * f(n) = g(n) + h(n)
 * => f(n) : 是这步综合实力的总估价，我们希望选 f(n) 最小的点出列开垦。
 * => g(n) : 是从起点出发走到当前路口 n【已经产生的真实代偿距离】（相当于 Dijkstra 的 dist）。
 * => h(n) : 是从当前路口 n 走到【终点】的【预估曼哈顿/欧几里得直线距离】。预估值！！
 *
 * 这样一来，如果两条岔路目前产生的行军距离一样长(g相等)，
 * A* 就会倾向于优先出列那个“大方向直指终点(h更小)”的路口！A* 是一种更聪明的，拥有宏观上帝视角的 Dijkstra。
 */
public class AStar {

    private class Edge {
        public int sid;
        public int tid;
        public int w;
        public Edge(int sid, int tid, int w) {
            this.sid = sid; 
            this.tid = tid; 
            this.w = w;
        }
    }

    private class Vertex {
        public int id; // 顶点编号ID
        public int dist; // 起点到这个顶点的“已有路径走完的具体直线距离(g)”
        // 用于 A* 特设。不仅考虑来时的距离，还加入向未来“直指目标点的直线预估直线距离(h)”。f(n) = g(n)+h(n)
        public int f; 
        public int x, y; // 顶点的地图横纵坐标（A星必不可少，我们需要利用地图坐标来计算曼哈顿估价函数 h(n)！）
        
        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
        }
    }

    /**
     * 【精简小顶堆实现：用来支持A星每次只去挑 f(n) 即未来潜力与现实消耗加起来最值的那个点】
     */
    private class PriorityQueue {
        private Vertex[] nodes;
        private int count;

        public PriorityQueue(int v) {
            nodes = new Vertex[v + 1];
            count = 0;
        }

        public Vertex poll() {
            if (count == 0) return null;
            int minVertexIndex = 1;
            // 注意这里：Dijkstra 中是通过节点的 dist 比拼开垦权限
            // A* 中是通过比拼节点的综合预测代价 f(n) 进行竞争入场！
            for (int i = 2; i <= count; ++i) {
                if (nodes[i].f < nodes[minVertexIndex].f) { 
                    minVertexIndex = i;
                }
            }
            Vertex minVertex = nodes[minVertexIndex];
            nodes[minVertexIndex] = nodes[count];
            count--;
            return minVertex;
        }

        public void add(Vertex vertex) {
            nodes[++count] = vertex;
        }

        public void update(Vertex vertex) {
            for (int i = 1; i <= count; ++i) {
                if (nodes[i].id == vertex.id) {
                    nodes[i].dist = vertex.dist;
                    nodes[i].f = vertex.f; // 同步更新 f 分数
                    break;
                }
            }
        }

        public void clear() {
            count = 0;
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }

    public class Graph { 
        private LinkedList<Edge> adj[]; // 邻接表
        private int v; // 顶点个数
        private Vertex[] vertexes; // 用来存放所有节点信息的包揽数组

        @SuppressWarnings("unchecked")
        public Graph(int v) {
            this.v = v;
            this.adj = new LinkedList[v];
            for (int i = 0; i < v; ++i) {
                this.adj[i] = new LinkedList<>();
            }
            this.vertexes = new Vertex[this.v];
        }

        public void addEdge(int s, int t, int w) { 
            this.adj[s].add(new Edge(s, t, w));
        }

        public void addVertex(int id, int x, int y) {
            vertexes[id] = new Vertex(id, x, y);
        }

        /**
         * 估价函数 h(x)：用来模拟从节点 v1 走到 最终节点 v2 纯理想状态下的路程消耗
         * 这里使用 【曼哈顿距离】
         */
        int hManhattan(Vertex v1, Vertex v2) { // Vertex表示顶点，后面有定义
            return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
        }

        /**
         * 类似 Dijkstra 的 A* 主搜索逻辑
         */
        public void astar(int s, int t) { 
            int[] predecessor = new int[this.v]; // 前驱追踪用于路径还原
            PriorityQueue queue = new PriorityQueue(this.v); // 小顶堆队列
            boolean[] inqueue = new boolean[this.v]; // 防重复加入判定数组
            
            // 还原全部点的距离为无限大
            for(int i=0; i<this.v; i++){
                vertexes[i].dist = Integer.MAX_VALUE;
                vertexes[i].f = Integer.MAX_VALUE;
            }

            vertexes[s].dist = 0;
            // A* 特色点1：起始节点的代价值，是距离起点路程0，加上起点直线预估去终站距离的曼哈顿结果
            vertexes[s].f = 0 + hManhattan(vertexes[s], vertexes[t]);
            
            queue.add(vertexes[s]);
            inqueue[s] = true;

            while (!queue.isEmpty()) {
                Vertex minVertex = queue.poll(); // 对堆里的候选者按照潜力 f(n) 最低的选出开垦！
                
                // 跟 Dijkstra 一样，取到了最终目的地说明搜索完美落幕
                if (minVertex.id == t) break;
                
                // 四处扩散开垦周围的点
                for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                    Edge e = adj[minVertex.id].get(i); // 将要走的一条去邻居家的新边
                    Vertex nextVertex = vertexes[e.tid]; // 邻居
                    
                    // 松弛操作！如果走我现在你家到起点的距离，是不是比原来发现的其他路线走更近？
                    if (minVertex.dist + e.w < nextVertex.dist) { 
                        nextVertex.dist = minVertex.dist + e.w; // 记录实际耗费的成本 g(n)
                        
                        // A* 特色点2：不仅仅把老一套更新进去算数，还要为他赋能启发函数综合判断他是否有未来前景 f(n) = g(n) + h(n)
                        nextVertex.f = nextVertex.dist + hManhattan(nextVertex, vertexes[t]);
                        predecessor[nextVertex.id] = minVertex.id;

                        if (inqueue[nextVertex.id] == true) {
                            queue.update(nextVertex);
                        } else {
                            queue.add(nextVertex);
                            inqueue[nextVertex.id] = true;
                        }
                    }
                }
            }
            
            System.out.print(s);
            print(s, t, predecessor); 
            System.out.println();
        }

        private void print(int s, int t, int[] predecessor) {
            if (s == t) return;
            print(s, predecessor[t], predecessor);
            System.out.print(" -> " + t);
        }
    }
}
