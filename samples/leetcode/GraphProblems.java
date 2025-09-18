package leetcode;

import java.util.*;

/**
 * LeetCode图相关问题解答
 * 包含岛屿数量等经典图论题目
 */
public class GraphProblems {
    
    // ==================== Number of Islands（岛屿数量）====================
    
    /**
     * LeetCode 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成
     * 
     * 解题思路：DFS/BFS
     * 1. 遍历网格中的每个位置
     * 2. 遇到'1'时，岛屿数量+1，并使用DFS/BFS将相连的所有'1'标记为已访问
     * 3. 继续遍历，直到所有位置都被检查
     * 
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n) - 递归栈空间
     * 
     * @param grid 二维网格
     * @return 岛屿数量
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsMarkIsland(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    /**
     * DFS标记岛屿
     * 
     * @param grid 网格
     * @param row 行
     * @param col 列
     */
    private static void dfsMarkIsland(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // 边界检查和已访问检查
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != '1') {
            return;
        }
        
        // 标记为已访问
        grid[row][col] = '0';
        
        // 递归访问四个方向
        dfsMarkIsland(grid, row - 1, col); // 上
        dfsMarkIsland(grid, row + 1, col); // 下
        dfsMarkIsland(grid, row, col - 1); // 左
        dfsMarkIsland(grid, row, col + 1); // 右
    }
    
    /**
     * 岛屿数量（BFS实现）
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(min(m,n)) - 队列空间
     * 
     * @param grid 二维网格
     * @return 岛屿数量
     */
    public static int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    
                    // BFS标记整个岛屿
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    grid[i][j] = '0';
                    
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int row = current[0];
                        int col = current[1];
                        
                        for (int[] dir : directions) {
                            int newRow = row + dir[0];
                            int newCol = col + dir[1];
                            
                            if (newRow >= 0 && newRow < rows && 
                                newCol >= 0 && newCol < cols && 
                                grid[newRow][newCol] == '1') {
                                
                                grid[newRow][newCol] = '0';
                                queue.offer(new int[]{newRow, newCol});
                            }
                        }
                    }
                }
            }
        }
        
        return count;
    }
    
    /**
     * 岛屿数量（并查集实现）
     * 时间复杂度：O(m*n*α(m*n))
     * 空间复杂度：O(m*n)
     * 
     * @param grid 二维网格
     * @return 岛屿数量
     */
    public static int numIslandsUnionFind(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        UnionFind uf = new UnionFind(grid);
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    for (int[] dir : directions) {
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];
                        
                        if (newRow >= 0 && newRow < rows && 
                            newCol >= 0 && newCol < cols && 
                            grid[newRow][newCol] == '1') {
                            
                            uf.union(i * cols + j, newRow * cols + newCol);
                        }
                    }
                }
            }
        }
        
        return uf.getCount();
    }
    
    /**
     * 并查集数据结构
     */
    static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int count;
        
        public UnionFind(char[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            parent = new int[rows * cols];
            rank = new int[rows * cols];
            count = 0;
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        int id = i * cols + j;
                        parent[id] = id;
                        count++;
                    }
                }
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // 路径压缩
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                // 按秩合并
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }
        
        public int getCount() {
            return count;
        }
    }
    
    // ==================== 岛屿问题的变体 ====================
    
    /**
     * LeetCode 695. 岛屿的最大面积
     * 
     * @param grid 二维网格
     * @return 最大岛屿面积
     */
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int area = dfsCalculateArea(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        
        return maxArea;
    }
    
    /**
     * DFS计算岛屿面积
     * 
     * @param grid 网格
     * @param row 行
     * @param col 列
     * @return 面积
     */
    private static int dfsCalculateArea(int[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != 1) {
            return 0;
        }
        
        grid[row][col] = 0; // 标记为已访问
        
        int area = 1;
        area += dfsCalculateArea(grid, row - 1, col);
        area += dfsCalculateArea(grid, row + 1, col);
        area += dfsCalculateArea(grid, row, col - 1);
        area += dfsCalculateArea(grid, row, col + 1);
        
        return area;
    }
    
    /**
     * LeetCode 463. 岛屿的周长
     * 
     * @param grid 二维网格
     * @return 岛屿周长
     */
    public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int perimeter = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4; // 每个陆地格子贡献4条边
                    
                    // 减去与相邻陆地格子共享的边
                    if (i > 0 && grid[i - 1][j] == 1) perimeter--;
                    if (i < rows - 1 && grid[i + 1][j] == 1) perimeter--;
                    if (j > 0 && grid[i][j - 1] == 1) perimeter--;
                    if (j < cols - 1 && grid[i][j + 1] == 1) perimeter--;
                }
            }
        }
        
        return perimeter;
    }
    
    // ==================== 其他图论问题 ====================
    
    /**
     * LeetCode 133. 克隆图
     * 
     * @param node 图节点
     * @return 克隆的图
     */
    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        Map<Node, Node> visited = new HashMap<>();
        return dfsClone(node, visited);
    }
    
    private static Node dfsClone(Node node, Map<Node, Node> visited) {
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode);
        
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(dfsClone(neighbor, visited));
        }
        
        return cloneNode;
    }
    
    /**
     * 图节点定义
     */
    static class Node {
        public int val;
        public List<Node> neighbors;
        
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    
    /**
     * LeetCode 207. 课程表
     * 判断是否可以完成所有课程（检测有向图中是否有环）
     * 
     * @param numCourses 课程数量
     * @param prerequisites 先修课程关系
     * @return 是否可以完成所有课程
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        int[] indegree = new int[numCourses];
        
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            
            graph.get(preCourse).add(course);
            indegree[course]++;
        }
        
        // 拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int completedCourses = 0;
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            completedCourses++;
            
            for (int nextCourse : graph.get(course)) {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        return completedCourses == numCourses;
    }
    
    /**
     * LeetCode 210. 课程表 II
     * 返回完成所有课程的顺序
     * 
     * @param numCourses 课程数量
     * @param prerequisites 先修课程关系
     * @return 课程完成顺序
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        int[] indegree = new int[numCourses];
        
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            
            graph.get(preCourse).add(course);
            indegree[course]++;
        }
        
        // 拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] result = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;
            
            for (int nextCourse : graph.get(course)) {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        return index == numCourses ? result : new int[0];
    }
    
    // ==================== 辅助方法 ====================
    
    /**
     * 生成随机网格
     * 
     * @param rows 行数
     * @param cols 列数
     * @param landProbability 陆地概率
     * @return 随机网格
     */
    public static char[][] generateRandomGrid(int rows, int cols, double landProbability) {
        Random random = new Random();
        char[][] grid = new char[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = random.nextDouble() < landProbability ? '1' : '0';
            }
        }
        
        return grid;
    }
    
    /**
     * 打印网格
     * 
     * @param grid 网格
     */
    public static void printGrid(char[][] grid) {
        if (grid == null || grid.length == 0) {
            System.out.println("Empty grid");
            return;
        }
        
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * 复制网格
     * 
     * @param original 原始网格
     * @return 复制的网格
     */
    public static char[][] copyGrid(char[][] original) {
        if (original == null) {
            return null;
        }
        
        int rows = original.length;
        int cols = original[0].length;
        char[][] copy = new char[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, cols);
        }
        
        return copy;
    }
    
    /**
     * 验证岛屿数量结果的一致性
     * 
     * @param grid 网格
     * @return 是否一致
     */
    public static boolean verifyIslandCount(char[][] grid) {
        char[][] grid1 = copyGrid(grid);
        char[][] grid2 = copyGrid(grid);
        char[][] grid3 = copyGrid(grid);
        
        int count1 = numIslands(grid1);
        int count2 = numIslandsBFS(grid2);
        int count3 = numIslandsUnionFind(grid3);
        
        return count1 == count2 && count2 == count3;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== LeetCode 图问题测试 ===\n");
        
        // 测试1：岛屿数量基本测试
        System.out.println("1. 岛屿数量基本测试:");
        
        char[][][] testGrids = {
            {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
            },
            {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
            },
            {
                {'1','0','1','0','1'},
                {'0','1','0','1','0'},
                {'1','0','1','0','1'},
                {'0','1','0','1','0'}
            },
            {
                {'1','1','1'},
                {'1','1','1'},
                {'1','1','1'}
            }
        };
        
        for (int i = 0; i < testGrids.length; i++) {
            char[][] grid = testGrids[i];
            
            System.out.printf("测试网格 %d:%n", i + 1);
            printGrid(grid);
            
            char[][] grid1 = copyGrid(grid);
            char[][] grid2 = copyGrid(grid);
            char[][] grid3 = copyGrid(grid);
            
            long startTime = System.nanoTime();
            int count1 = numIslands(grid1);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int count2 = numIslandsBFS(grid2);
            long time2 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int count3 = numIslandsUnionFind(grid3);
            long time3 = System.nanoTime() - startTime;
            
            System.out.printf("DFS: %d (%.2fμs)%n", count1, time1 / 1000.0);
            System.out.printf("BFS: %d (%.2fμs)%n", count2, time2 / 1000.0);
            System.out.printf("并查集: %d (%.2fμs)%n", count3, time3 / 1000.0);
            System.out.printf("一致性: %s%n", count1 == count2 && count2 == count3);
            System.out.println();
        }
        
        // 测试2：岛屿问题变体
        System.out.println("2. 岛屿问题变体测试:");
        
        // 最大岛屿面积
        int[][][] areaGrids = {
            {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
            },
            {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
            }
        };
        
        System.out.println("最大岛屿面积:");
        for (int i = 0; i < areaGrids.length; i++) {
            int[][] grid = areaGrids[i];
            int[][] gridCopy = new int[grid.length][grid[0].length];
            
            for (int j = 0; j < grid.length; j++) {
                System.arraycopy(grid[j], 0, gridCopy[j], 0, grid[j].length);
            }
            
            int maxArea = maxAreaOfIsland(gridCopy);
            System.out.printf("网格 %d 最大面积: %d%n", i + 1, maxArea);
        }
        
        // 岛屿周长
        int[][][] perimeterGrids = {
            {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}},
            {{1}},
            {{1,0}}
        };
        
        System.out.println("\n岛屿周长:");
        for (int i = 0; i < perimeterGrids.length; i++) {
            int[][] grid = perimeterGrids[i];
            int perimeter = islandPerimeter(grid);
            System.out.printf("网格 %d 周长: %d%n", i + 1, perimeter);
        }
        
        // 测试3：课程表问题
        System.out.println("\n3. 课程表问题测试:");
        
        // 课程表测试用例
        int[] courseCounts = {2, 2, 4, 3};
        int[][][] prerequisitesArray = {
            {{1,0}},
            {{1,0},{0,1}},
            {{1,0},{2,0},{3,1},{3,2}},
            {{0,1},{0,2},{1,2}}
        };
        
        System.out.println("课程表 I (是否可以完成所有课程):");
        for (int i = 0; i < courseCounts.length; i++) {
            int numCourses = courseCounts[i];
            int[][] prerequisites = prerequisitesArray[i];
            
            boolean canComplete = canFinish(numCourses, prerequisites);
            System.out.printf("测试 %d: 课程数=%d, 可以完成=%s%n", 
                            i + 1, numCourses, canComplete);
            
            System.out.print("先修关系: ");
            for (int[] prereq : prerequisites) {
                System.out.printf("[%d,%d] ", prereq[0], prereq[1]);
            }
            System.out.println();
        }
        
        System.out.println("\n课程表 II (完成顺序):");
        for (int i = 0; i < courseCounts.length; i++) {
            int numCourses = courseCounts[i];
            int[][] prerequisites = prerequisitesArray[i];
            
            int[] order = findOrder(numCourses, prerequisites);
            System.out.printf("测试 %d: 完成顺序=%s%n", 
                            i + 1, Arrays.toString(order));
        }
        
        // 测试4：性能测试
        System.out.println("\n4. 性能测试:");
        
        // 大网格岛屿数量性能测试
        System.out.println("大网格岛屿数量性能测试:");
        int[] gridSizes = {50, 100, 200};
        double[] landProbabilities = {0.3, 0.5, 0.7};
        
        for (int size : gridSizes) {
            for (double prob : landProbabilities) {
                char[][] largeGrid = generateRandomGrid(size, size, prob);
                
                System.out.printf("网格大小=%dx%d, 陆地概率=%.1f:%n", size, size, prob);
                
                char[][] grid1 = copyGrid(largeGrid);
                char[][] grid2 = copyGrid(largeGrid);
                char[][] grid3 = copyGrid(largeGrid);
                
                long startTime = System.currentTimeMillis();
                int count1 = numIslands(grid1);
                long time1 = System.currentTimeMillis() - startTime;
                
                startTime = System.currentTimeMillis();
                int count2 = numIslandsBFS(grid2);
                long time2 = System.currentTimeMillis() - startTime;
                
                startTime = System.currentTimeMillis();
                int count3 = numIslandsUnionFind(grid3);
                long time3 = System.currentTimeMillis() - startTime;
                
                System.out.printf("  岛屿数量: %d%n", count1);
                System.out.printf("  DFS: %dms, BFS: %dms, 并查集: %dms%n", 
                                time1, time2, time3);
                System.out.printf("  一致性: %s%n", count1 == count2 && count2 == count3);
                System.out.println();
            }
        }
        
        // 课程表性能测试
        System.out.println("课程表性能测试:");
        int[] courseCounts = {100, 500, 1000};
        
        for (int numCourses : courseCounts) {
            // 生成随机先修关系
            Random random = new Random();
            int numPrerequisites = numCourses / 2;
            int[][] prerequisites = new int[numPrerequisites][2];
            
            for (int i = 0; i < numPrerequisites; i++) {
                int course = random.nextInt(numCourses);
                int preCourse = random.nextInt(numCourses);
                
                // 避免自环
                while (course == preCourse) {
                    preCourse = random.nextInt(numCourses);
                }
                
                prerequisites[i] = new int[]{course, preCourse};
            }
            
            System.out.printf("课程数=%d, 先修关系数=%d:%n", numCourses, numPrerequisites);
            
            long startTime = System.currentTimeMillis();
            boolean canComplete = canFinish(numCourses, prerequisites);
            long time1 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            int[] order = findOrder(numCourses, prerequisites);
            long time2 = System.currentTimeMillis() - startTime;
            
            System.out.printf("  可以完成: %s (%dms)%n", canComplete, time1);
            System.out.printf("  找到顺序: %s (%dms)%n", order.length > 0, time2);
            System.out.println();
        }
        
        // 测试5：边界情况
        System.out.println("5. 边界情况测试:");
        
        System.out.println("岛屿数量边界情况:");
        
        // 空网格
        char[][] emptyGrid = {};
        System.out.println("空网格: " + numIslands(emptyGrid));
        
        // 单个格子
        char[][] singleLand = {{'1'}};
        char[][] singleWater = {{'0'}};
        System.out.println("单个陆地: " + numIslands(copyGrid(singleLand)));
        System.out.println("单个水域: " + numIslands(copyGrid(singleWater)));
        
        // 全是陆地
        char[][] allLand = {{'1','1','1'},{'1','1','1'},{'1','1','1'}};
        System.out.println("全是陆地: " + numIslands(copyGrid(allLand)));
        
        // 全是水域
        char[][] allWater = {{'0','0','0'},{'0','0','0'},{'0','0','0'}};
        System.out.println("全是水域: " + numIslands(copyGrid(allWater)));
        
        System.out.println("\n课程表边界情况:");
        System.out.println("0门课程: " + canFinish(0, new int[][]{}));
        System.out.println("1门课程: " + canFinish(1, new int[][]{}));
        System.out.println("无先修关系: " + canFinish(3, new int[][]{}));
        
        // 测试6：正确性验证
        System.out.println("\n6. 正确性验证:");
        
        // 大量随机网格测试
        int correctCount = 0;
        int totalTests = 100;
        
        Random random = new Random(42);
        
        for (int i = 0; i < totalTests; i++) {
            int rows = random.nextInt(20) + 5;
            int cols = random.nextInt(20) + 5;
            double prob = random.nextDouble() * 0.8 + 0.1;
            
            char[][] grid = generateRandomGrid(rows, cols, prob);
            
            if (verifyIslandCount(grid)) {
                correctCount++;
            }
        }
        
        System.out.printf("岛屿数量算法一致性: %d/%d 正确, 正确率: %.2f%%%n", 
                        correctCount, totalTests, 100.0 * correctCount / totalTests);
        
        // 课程表算法一致性验证
        int courseCorrect = 0;
        int courseTotal = 100;
        
        for (int i = 0; i < courseTotal; i++) {
            int numCourses = random.nextInt(50) + 10;
            int numPrereqs = random.nextInt(numCourses);
            
            int[][] prerequisites = new int[numPrereqs][2];
            for (int j = 0; j < numPrereqs; j++) {
                int course = random.nextInt(numCourses);
                int preCourse = random.nextInt(numCourses);
                
                while (course == preCourse) {
                    preCourse = random.nextInt(numCourses);
                }
                
                prerequisites[j] = new int[]{course, preCourse};
            }
            
            boolean canComplete = canFinish(numCourses, prerequisites);
            int[] order = findOrder(numCourses, prerequisites);
            
            // 验证一致性：如果可以完成，应该能找到顺序
            if ((canComplete && order.length == numCourses) || 
                (!canComplete && order.length == 0)) {
                courseCorrect++;
            }
        }
        
        System.out.printf("课程表算法一致性: %d/%d 正确, 正确率: %.2f%%%n", 
                        courseCorrect, courseTotal, 100.0 * courseCorrect / courseTotal);
        
        // 测试7：特殊图结构
        System.out.println("\n7. 特殊图结构测试:");
        
        // 棋盘模式
        char[][] checkerboard = {
            {'1','0','1','0','1'},
            {'0','1','0','1','0'},
            {'1','0','1','0','1'},
            {'0','1','0','1','0'},
            {'1','0','1','0','1'}
        };
        
        System.out.println("棋盘模式:");
        printGrid(checkerboard);
        System.out.println("岛屿数量: " + numIslands(copyGrid(checkerboard)));
        
        // 螺旋模式
        char[][] spiral = {
            {'1','1','1','1','1'},
            {'0','0','0','0','1'},
            {'1','1','1','0','1'},
            {'1','0','0','0','1'},
            {'1','1','1','1','1'}
        };
        
        System.out.println("\n螺旋模式:");
        printGrid(spiral);
        System.out.println("岛屿数量: " + numIslands(copyGrid(spiral)));
        
        // 十字模式
        char[][] cross = {
            {'0','0','1','0','0'},
            {'0','0','1','0','0'},
            {'1','1','1','1','1'},
            {'0','0','1','0','0'},
            {'0','0','1','0','0'}
        };
        
        System.out.println("\n十字模式:");
        printGrid(cross);
        System.out.println("岛屿数量: " + numIslands(copyGrid(cross)));
        
        System.out.println("\n=== 图问题测试完成 ===");
    }
}