package backtrack;

import java.util.*;

/**
 * 回溯算法实现集合
 * 包含八皇后问题、0-1背包问题、数独求解、全排列等经典回溯算法
 */
public class BacktrackingAlgorithms {
    
    // ==================== 八皇后问题 ====================
    
    /**
     * 八皇后问题解决方案
     * 在8x8棋盘上放置8个皇后，使得它们互不攻击
     */
    public static class NQueens {
        private int n;
        private List<List<String>> solutions;
        private int[] queens; // queens[i] 表示第i行皇后所在的列
        
        public NQueens(int n) {
            this.n = n;
            this.solutions = new ArrayList<>();
            this.queens = new int[n];
        }
        
        /**
         * 求解N皇后问题的所有解
         * 
         * @return 所有可能的解
         */
        public List<List<String>> solveNQueens() {
            solutions.clear();
            Arrays.fill(queens, -1);
            backtrack(0);
            return new ArrayList<>(solutions);
        }
        
        /**
         * 回溯求解
         * 
         * @param row 当前处理的行
         */
        private void backtrack(int row) {
            if (row == n) {
                // 找到一个解
                solutions.add(generateBoard());
                return;
            }
            
            // 尝试在当前行的每一列放置皇后
            for (int col = 0; col < n; col++) {
                if (isValid(row, col)) {
                    queens[row] = col;
                    backtrack(row + 1);
                    queens[row] = -1; // 回溯
                }
            }
        }
        
        /**
         * 检查在(row, col)位置放置皇后是否有效
         * 
         * @param row 行
         * @param col 列
         * @return true如果有效，否则false
         */
        private boolean isValid(int row, int col) {
            for (int i = 0; i < row; i++) {
                int queenCol = queens[i];
                
                // 检查列冲突
                if (queenCol == col) {
                    return false;
                }
                
                // 检查对角线冲突
                if (Math.abs(i - row) == Math.abs(queenCol - col)) {
                    return false;
                }
            }
            return true;
        }
        
        /**
         * 生成棋盘表示
         * 
         * @return 棋盘字符串列表
         */
        private List<String> generateBoard() {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (queens[i] == j) {
                        row.append('Q');
                    } else {
                        row.append('.');
                    }
                }
                board.add(row.toString());
            }
            return board;
        }
        
        /**
         * 计算N皇后问题的解的数量
         * 
         * @return 解的数量
         */
        public int totalNQueens() {
            return solveNQueens().size();
        }
        
        /**
         * 打印所有解
         */
        public void printAllSolutions() {
            List<List<String>> allSolutions = solveNQueens();
            System.out.println(n + "皇后问题共有 " + allSolutions.size() + " 个解:");
            
            for (int i = 0; i < allSolutions.size(); i++) {
                System.out.println("\n解 " + (i + 1) + ":");
                List<String> solution = allSolutions.get(i);
                for (String row : solution) {
                    System.out.println(row);
                }
            }
        }
    }
    
    // ==================== 0-1背包问题 ====================
    
    /**
     * 0-1背包问题（回溯法求解）
     * 给定一组物品，每个物品有重量和价值，在限定的背包容量下，选择物品使价值最大
     */
    public static class Knapsack01 {
        
        /**
         * 物品类
         */
        public static class Item {
            public int weight;
            public int value;
            public int index;
            
            public Item(int weight, int value, int index) {
                this.weight = weight;
                this.value = value;
                this.index = index;
            }
            
            public double getValuePerWeight() {
                return (double) value / weight;
            }
            
            @Override
            public String toString() {
                return "Item{weight=" + weight + ", value=" + value + ", index=" + index + "}";
            }
        }
        
        /**
         * 背包解决方案
         */
        public static class KnapsackSolution {
            public int maxValue;
            public List<Item> selectedItems;
            public int totalWeight;
            
            public KnapsackSolution() {
                this.maxValue = 0;
                this.selectedItems = new ArrayList<>();
                this.totalWeight = 0;
            }
            
            public KnapsackSolution(int maxValue, List<Item> selectedItems) {
                this.maxValue = maxValue;
                this.selectedItems = new ArrayList<>(selectedItems);
                this.totalWeight = selectedItems.stream().mapToInt(item -> item.weight).sum();
            }
            
            @Override
            public String toString() {
                return "KnapsackSolution{" +
                       "maxValue=" + maxValue +
                       ", totalWeight=" + totalWeight +
                       ", selectedItems=" + selectedItems.size() +
                       "}";
            }
        }
        
        private Item[] items;
        private int capacity;
        private KnapsackSolution bestSolution;
        
        public Knapsack01(Item[] items, int capacity) {
            this.items = items.clone();
            this.capacity = capacity;
            this.bestSolution = new KnapsackSolution();
            
            // 按价值重量比排序，用于剪枝
            Arrays.sort(this.items, (a, b) -> Double.compare(b.getValuePerWeight(), a.getValuePerWeight()));
        }
        
        /**
         * 使用回溯法求解0-1背包问题
         * 
         * @return 最优解
         */
        public KnapsackSolution solve() {
            bestSolution = new KnapsackSolution();
            List<Item> currentItems = new ArrayList<>();
            backtrack(0, 0, 0, currentItems);
            return bestSolution;
        }
        
        /**
         * 回溯求解
         * 
         * @param index 当前考虑的物品索引
         * @param currentWeight 当前背包重量
         * @param currentValue 当前背包价值
         * @param currentItems 当前选中的物品
         */
        private void backtrack(int index, int currentWeight, int currentValue, List<Item> currentItems) {
            // 更新最优解
            if (currentValue > bestSolution.maxValue) {
                bestSolution = new KnapsackSolution(currentValue, currentItems);
            }
            
            // 剪枝：如果当前价值加上剩余物品的理论最大价值仍不如当前最优解，则剪枝
            if (currentValue + getUpperBound(index, capacity - currentWeight) <= bestSolution.maxValue) {
                return;
            }
            
            // 尝试后续每个物品
            for (int i = index; i < items.length; i++) {
                Item item = items[i];
                
                // 如果物品能放入背包
                if (currentWeight + item.weight <= capacity) {
                    currentItems.add(item);
                    backtrack(i + 1, currentWeight + item.weight, currentValue + item.value, currentItems);
                    currentItems.remove(currentItems.size() - 1); // 回溯
                }
            }
        }
        
        /**
         * 计算剩余容量的理论最大价值（用于剪枝）
         * 使用贪心策略按价值重量比选择物品
         * 
         * @param startIndex 开始索引
         * @param remainingCapacity 剩余容量
         * @return 理论最大价值
         */
        private double getUpperBound(int startIndex, int remainingCapacity) {
            double upperBound = 0;
            int weight = remainingCapacity;
            
            for (int i = startIndex; i < items.length && weight > 0; i++) {
                Item item = items[i];
                if (item.weight <= weight) {
                    upperBound += item.value;
                    weight -= item.weight;
                } else {
                    // 部分装入（分数背包的思想）
                    upperBound += item.value * ((double) weight / item.weight);
                    break;
                }
            }
            
            return upperBound;
        }
        
        /**
         * 打印解决方案
         */
        public void printSolution() {
            KnapsackSolution solution = solve();
            System.out.println("0-1背包问题解决方案:");
            System.out.println("背包容量: " + capacity);
            System.out.println("最大价值: " + solution.maxValue);
            System.out.println("总重量: " + solution.totalWeight);
            System.out.println("选中的物品:");
            
            for (Item item : solution.selectedItems) {
                System.out.println("  " + item);
            }
        }
    }
    
    // ==================== 数独求解 ====================
    
    /**
     * 数独求解器
     * 使用回溯算法求解9x9数独
     */
    public static class SudokuSolver {
        private static final int SIZE = 9;
        private static final int EMPTY = 0;
        
        /**
         * 求解数独
         * 
         * @param board 数独棋盘，0表示空格
         * @return true如果有解，否则false
         */
        public static boolean solveSudoku(int[][] board) {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    if (board[row][col] == EMPTY) {
                        // 尝试填入1-9
                        for (int num = 1; num <= 9; num++) {
                            if (isValid(board, row, col, num)) {
                                board[row][col] = num;
                                
                                if (solveSudoku(board)) {
                                    return true;
                                }
                                
                                board[row][col] = EMPTY; // 回溯
                            }
                        }
                        return false; // 无解
                    }
                }
            }
            return true; // 所有格子都填满了
        }
        
        /**
         * 检查在指定位置填入数字是否有效
         * 
         * @param board 棋盘
         * @param row 行
         * @param col 列
         * @param num 要填入的数字
         * @return true如果有效，否则false
         */
        private static boolean isValid(int[][] board, int row, int col, int num) {
            // 检查行
            for (int j = 0; j < SIZE; j++) {
                if (board[row][j] == num) {
                    return false;
                }
            }
            
            // 检查列
            for (int i = 0; i < SIZE; i++) {
                if (board[i][col] == num) {
                    return false;
                }
            }
            
            // 检查3x3子网格
            int boxRow = (row / 3) * 3;
            int boxCol = (col / 3) * 3;
            for (int i = boxRow; i < boxRow + 3; i++) {
                for (int j = boxCol; j < boxCol + 3; j++) {
                    if (board[i][j] == num) {
                        return false;
                    }
                }
            }
            
            return true;
        }
        
        /**
         * 打印数独棋盘
         * 
         * @param board 棋盘
         */
        public static void printBoard(int[][] board) {
            for (int i = 0; i < SIZE; i++) {
                if (i % 3 == 0 && i != 0) {
                    System.out.println("------+-------+------");
                }
                for (int j = 0; j < SIZE; j++) {
                    if (j % 3 == 0 && j != 0) {
                        System.out.print("| ");
                    }
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
        
        /**
         * 验证数独解是否正确
         * 
         * @param board 棋盘
         * @return true如果正确，否则false
         */
        public static boolean isValidSolution(int[][] board) {
            // 检查每行
            for (int i = 0; i < SIZE; i++) {
                boolean[] used = new boolean[SIZE + 1];
                for (int j = 0; j < SIZE; j++) {
                    int num = board[i][j];
                    if (num < 1 || num > 9 || used[num]) {
                        return false;
                    }
                    used[num] = true;
                }
            }
            
            // 检查每列
            for (int j = 0; j < SIZE; j++) {
                boolean[] used = new boolean[SIZE + 1];
                for (int i = 0; i < SIZE; i++) {
                    int num = board[i][j];
                    if (used[num]) {
                        return false;
                    }
                    used[num] = true;
                }
            }
            
            // 检查每个3x3子网格
            for (int boxRow = 0; boxRow < SIZE; boxRow += 3) {
                for (int boxCol = 0; boxCol < SIZE; boxCol += 3) {
                    boolean[] used = new boolean[SIZE + 1];
                    for (int i = boxRow; i < boxRow + 3; i++) {
                        for (int j = boxCol; j < boxCol + 3; j++) {
                            int num = board[i][j];
                            if (used[num]) {
                                return false;
                            }
                            used[num] = true;
                        }
                    }
                }
            }
            
            return true;
        }
    }
    
    // ==================== 全排列生成 ====================
    
    /**
     * 全排列生成器
     * 生成给定数组的所有排列
     */
    public static class PermutationGenerator {
        
        /**
         * 生成数组的所有排列
         * 
         * @param nums 输入数组
         * @return 所有排列的列表
         */
        public static List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            
            backtrack(nums, used, current, result);
            return result;
        }
        
        /**
         * 回溯生成排列
         * 
         * @param nums 原数组
         * @param used 使用标记数组
         * @param current 当前排列
         * @param result 结果列表
         */
        private static void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
            if (current.size() == nums.length) {
                result.add(new ArrayList<>(current));
                return;
            }
            
            for (int i = 0; i < nums.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    current.add(nums[i]);
                    backtrack(nums, used, current, result);
                    current.remove(current.size() - 1); // 回溯
                    used[i] = false;
                }
            }
        }
        
        /**
         * 生成字符串的所有排列
         * 
         * @param s 输入字符串
         * @return 所有排列的列表
         */
        public static List<String> permuteString(String s) {
            List<String> result = new ArrayList<>();
            char[] chars = s.toCharArray();
            backtrackString(chars, 0, result);
            return result;
        }
        
        /**
         * 字符串排列回溯
         * 
         * @param chars 字符数组
         * @param start 开始位置
         * @param result 结果列表
         */
        private static void backtrackString(char[] chars, int start, List<String> result) {
            if (start == chars.length) {
                result.add(new String(chars));
                return;
            }
            
            for (int i = start; i < chars.length; i++) {
                swap(chars, start, i);
                backtrackString(chars, start + 1, result);
                swap(chars, start, i); // 回溯
            }
        }
        
        /**
         * 交换字符数组中的两个字符
         */
        private static void swap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }
    
    // ==================== 组合生成 ====================
    
    /**
     * 组合生成器
     * 生成从n个元素中选择k个元素的所有组合
     */
    public static class CombinationGenerator {
        
        /**
         * 生成组合C(n,k)
         * 
         * @param n 总元素数
         * @param k 选择元素数
         * @return 所有组合的列表
         */
        public static List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            backtrack(1, n, k, current, result);
            return result;
        }
        
        /**
         * 回溯生成组合
         * 
         * @param start 开始数字
         * @param n 结束数字
         * @param k 还需选择的数字个数
         * @param current 当前组合
         * @param result 结果列表
         */
        private static void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
            if (k == 0) {
                result.add(new ArrayList<>(current));
                return;
            }
            
            // 剪枝：如果剩余数字不够选择k个，直接返回
            if (n - start + 1 < k) {
                return;
            }
            
            for (int i = start; i <= n; i++) {
                current.add(i);
                backtrack(i + 1, n, k - 1, current, result);
                current.remove(current.size() - 1); // 回溯
            }
        }
        
        /**
         * 生成数组的所有子集
         * 
         * @param nums 输入数组
         * @return 所有子集的列表
         */
        public static List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            backtrackSubsets(nums, 0, current, result);
            return result;
        }
        
        /**
         * 回溯生成子集
         */
        private static void backtrackSubsets(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
            result.add(new ArrayList<>(current));
            
            for (int i = start; i < nums.length; i++) {
                current.add(nums[i]);
                backtrackSubsets(nums, i + 1, current, result);
                current.remove(current.size() - 1); // 回溯
            }
        }
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 回溯算法测试 ===\n");
        
        // 测试1：八皇后问题
        System.out.println("1. 八皇后问题测试:");
        NQueens eightQueens = new NQueens(8);
        
        long startTime = System.currentTimeMillis();
        int solutionCount = eightQueens.totalNQueens();
        long endTime = System.currentTimeMillis();
        
        System.out.println("8皇后问题解的数量: " + solutionCount);
        System.out.println("求解耗时: " + (endTime - startTime) + "ms");
        
        // 打印第一个解
        List<List<String>> solutions = eightQueens.solveNQueens();
        if (!solutions.isEmpty()) {
            System.out.println("\n第一个解:");
            for (String row : solutions.get(0)) {
                System.out.println(row);
            }
        }
        
        // 测试不同规模的N皇后问题
        System.out.println("\nN皇后问题解的数量统计:");
        for (int n = 1; n <= 10; n++) {
            NQueens nQueens = new NQueens(n);
            startTime = System.currentTimeMillis();
            int count = nQueens.totalNQueens();
            endTime = System.currentTimeMillis();
            System.out.println("N=" + n + ": " + count + " 个解, 耗时: " + (endTime - startTime) + "ms");
        }
        
        // 测试2：0-1背包问题
        System.out.println("\n2. 0-1背包问题测试:");
        
        Knapsack01.Item[] items = {
            new Knapsack01.Item(10, 60, 0),
            new Knapsack01.Item(20, 100, 1),
            new Knapsack01.Item(30, 120, 2),
            new Knapsack01.Item(40, 160, 3),
            new Knapsack01.Item(50, 200, 4)
        };
        
        int capacity = 80;
        Knapsack01 knapsack = new Knapsack01(items, capacity);
        
        startTime = System.currentTimeMillis();
        Knapsack01.KnapsackSolution solution = knapsack.solve();
        endTime = System.currentTimeMillis();
        
        System.out.println("背包容量: " + capacity);
        System.out.println("物品信息:");
        for (Knapsack01.Item item : items) {
            System.out.println("  " + item + ", 价值密度: " + String.format("%.2f", item.getValuePerWeight()));
        }
        
        System.out.println("\n最优解: " + solution);
        System.out.println("求解耗时: " + (endTime - startTime) + "ms");
        System.out.println("选中的物品:");
        for (Knapsack01.Item item : solution.selectedItems) {
            System.out.println("  " + item);
        }
        
        // 测试3：数独求解
        System.out.println("\n3. 数独求解测试:");
        
        int[][] sudokuBoard = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        
        System.out.println("原始数独:");
        SudokuSolver.printBoard(sudokuBoard);
        
        startTime = System.currentTimeMillis();
        boolean solved = SudokuSolver.solveSudoku(sudokuBoard);
        endTime = System.currentTimeMillis();
        
        if (solved) {
            System.out.println("\n求解成功! 耗时: " + (endTime - startTime) + "ms");
            System.out.println("解:");
            SudokuSolver.printBoard(sudokuBoard);
            System.out.println("解的正确性: " + SudokuSolver.isValidSolution(sudokuBoard));
        } else {
            System.out.println("无解!");
        }
        
        // 测试4：全排列生成
        System.out.println("\n4. 全排列生成测试:");
        
        int[] permuteArray = {1, 2, 3, 4};
        startTime = System.currentTimeMillis();
        List<List<Integer>> permutations = PermutationGenerator.permute(permuteArray);
        endTime = System.currentTimeMillis();
        
        System.out.println("数组 " + Arrays.toString(permuteArray) + " 的全排列:");
        System.out.println("排列数量: " + permutations.size());
        System.out.println("生成耗时: " + (endTime - startTime) + "ms");
        
        // 只打印前10个排列
        for (int i = 0; i < Math.min(10, permutations.size()); i++) {
            System.out.println("  " + permutations.get(i));
        }
        if (permutations.size() > 10) {
            System.out.println("  ... (还有 " + (permutations.size() - 10) + " 个排列)");
        }
        
        // 字符串排列测试
        String testString = "ABC";
        List<String> stringPermutations = PermutationGenerator.permuteString(testString);
        System.out.println("\n字符串 \"" + testString + "\" 的全排列:");
        for (String perm : stringPermutations) {
            System.out.print(perm + " ");
        }
        System.out.println();
        
        // 测试5：组合生成
        System.out.println("\n5. 组合生成测试:");
        
        int n = 5, k = 3;
        startTime = System.currentTimeMillis();
        List<List<Integer>> combinations = CombinationGenerator.combine(n, k);
        endTime = System.currentTimeMillis();
        
        System.out.println("C(" + n + "," + k + ") 的所有组合:");
        System.out.println("组合数量: " + combinations.size());
        System.out.println("生成耗时: " + (endTime - startTime) + "ms");
        
        for (List<Integer> combination : combinations) {
            System.out.println("  " + combination);
        }
        
        // 子集生成测试
        int[] subsetArray = {1, 2, 3};
        List<List<Integer>> subsets = CombinationGenerator.subsets(subsetArray);
        System.out.println("\n数组 " + Arrays.toString(subsetArray) + " 的所有子集:");
        System.out.println("子集数量: " + subsets.size());
        for (List<Integer> subset : subsets) {
            System.out.println("  " + subset);
        }
        
        // 测试6：性能测试
        System.out.println("\n6. 性能测试:");
        
        // N皇后性能测试
        System.out.println("N皇后问题性能测试:");
        for (int testN = 8; testN <= 12; testN++) {
            NQueens perfQueens = new NQueens(testN);
            startTime = System.currentTimeMillis();
            int perfCount = perfQueens.totalNQueens();
            endTime = System.currentTimeMillis();
            
            System.out.println("N=" + testN + ": " + perfCount + " 个解, 耗时: " + (endTime - startTime) + "ms");
        }
        
        // 背包问题性能测试
        System.out.println("\n背包问题性能测试:");
        Random random = new Random(42);
        
        for (int itemCount : new int[]{10, 15, 20}) {
            Knapsack01.Item[] perfItems = new Knapsack01.Item[itemCount];
            for (int i = 0; i < itemCount; i++) {
                int weight = 1 + random.nextInt(20);
                int value = 1 + random.nextInt(100);
                perfItems[i] = new Knapsack01.Item(weight, value, i);
            }
            
            int perfCapacity = itemCount * 5;
            Knapsack01 perfKnapsack = new Knapsack01(perfItems, perfCapacity);
            
            startTime = System.currentTimeMillis();
            Knapsack01.KnapsackSolution perfSolution = perfKnapsack.solve();
            endTime = System.currentTimeMillis();
            
            System.out.println("物品数=" + itemCount + ", 容量=" + perfCapacity + 
                             ": 最大价值=" + perfSolution.maxValue + 
                             ", 耗时=" + (endTime - startTime) + "ms");
        }
        
        // 排列组合性能测试
        System.out.println("\n排列组合性能测试:");
        for (int size = 5; size <= 8; size++) {
            int[] perfArray = new int[size];
            for (int i = 0; i < size; i++) {
                perfArray[i] = i + 1;
            }
            
            startTime = System.currentTimeMillis();
            List<List<Integer>> perfPermutations = PermutationGenerator.permute(perfArray);
            endTime = System.currentTimeMillis();
            
            System.out.println("数组大小=" + size + ": " + perfPermutations.size() + 
                             " 个排列, 耗时=" + (endTime - startTime) + "ms");
        }
    }
}