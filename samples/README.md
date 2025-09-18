# 算法实现样例集合 (Algorithm Implementation Samples)

本目录包含了基于《数据结构与算法之美》课程内容的完整Java算法实现，涵盖了所有主要的数据结构和算法类别。每个实现都包含详细的中文注释，解释算法原理、时间复杂度、空间复杂度以及使用场景。

## 📁 目录结构

### 1. 数组 (Array)
- **DynamicArray.java** - 动态数组实现，支持自动扩容和缩容
- **SortedArray.java** - 有序数组实现，支持二分查找插入
- **ArrayMerger.java** - 数组合并工具，实现有序数组合并

**核心特性：**
- 动态扩容机制
- 二分查找优化
- 内存管理优化

### 2. 链表 (LinkedList)
- **SinglyLinkedList.java** - 单链表实现，支持基本增删改查操作
- **DoublyLinkedList.java** - 双向链表实现，支持双向遍历
- **CircularLinkedList.java** - 循环链表实现，尾节点指向头节点
- **LinkedListUtils.java** - 链表工具类，包含反转、合并、环检测等算法

**核心算法：**
- 链表反转（迭代和递归）
- 快慢指针环检测
- 有序链表合并
- 链表中点查找

### 3. 栈 (Stack)
- **ArrayStack.java** - 基于数组的顺序栈实现
- **LinkedStack.java** - 基于链表的链式栈实现
- **BrowserHistory.java** - 浏览器历史记录模拟器，使用双栈实现前进后退

**应用场景：**
- 表达式求值
- 括号匹配
- 函数调用栈
- 浏览器历史记录

### 4. 队列 (Queue)
- **ArrayQueue.java** - 基于数组的顺序队列实现
- **LinkedQueue.java** - 基于链表的链式队列实现
- **CircularQueue.java** - 循环队列实现，解决假溢出问题

**核心特性：**
- 动态扩容
- 循环利用空间
- 高效的入队出队操作

### 5. 递归 (Recursion)
- **RecursionExamples.java** - 递归算法集合

**包含算法：**
- 阶乘计算
- 斐波那契数列（递归和优化版本）
- 汉诺塔问题
- 全排列生成
- 树的遍历

### 6. 排序 (Sorting)
- **SortingAlgorithms.java** - 完整的排序算法实现集合
- **QuickSelect.java** - 快速选择算法，用于查找第K大元素

**包含排序算法：**
- 冒泡排序 (O(n²))
- 插入排序 (O(n²))
- 选择排序 (O(n²))
- 归并排序 (O(n log n))
- 快速排序 (O(n log n))
- 计数排序 (O(n+k))
- 基数排序 (O(d(n+k)))
- 桶排序 (O(n+k))

### 7. 二分查找 (Binary Search)
- **BinarySearch.java** - 二分查找算法集合

**包含变体：**
- 基础二分查找
- 查找第一个等于给定值的元素
- 查找最后一个等于给定值的元素
- 查找第一个大于等于给定值的元素
- 查找最后一个小于等于给定值的元素
- 循环有序数组查找

### 8. 散列表 (Hash Table)
- **HashTable.java** - 基于链表法解决冲突的散列表实现
- **LRUCache.java** - LRU缓存实现，结合散列表和双向链表

**核心特性：**
- 链表法解决哈希冲突
- 动态扩容和缩容
- LRU淘汰策略
- O(1)平均时间复杂度

### 9. 字符串 (String)
- **TrieTree.java** - 字典树（前缀树）实现，支持字符串快速查找
- **StringMatching.java** - 字符串匹配算法集合

**包含算法：**
- BF算法（暴力匹配）
- RK算法（Rabin-Karp）
- BM算法（Boyer-Moore）
- KMP算法（Knuth-Morris-Pratt）
- 字典树的插入、查找、删除

### 10. 二叉树 (Binary Tree)
- **BinarySearchTree.java** - 二叉搜索树实现
- **BinaryTreeTraversal.java** - 二叉树遍历算法集合

**包含功能：**
- 前序、中序、后序遍历（递归和迭代）
- 层序遍历
- 二叉搜索树的增删改查
- 树的高度计算
- 平衡性检查

### 11. 堆 (Heap)
- **MinHeap.java** - 小顶堆实现
- **MaxHeap.java** - 大顶堆实现
- **PriorityQueue.java** - 优先队列实现
- **HeapSort.java** - 堆排序算法
- **TopKElements.java** - Top-K问题解决方案

**核心操作：**
- 堆化操作（上浮、下沉）
- 动态维护堆性质
- 堆排序
- Top-K问题求解

### 12. 图 (Graph)
- **Graph.java** - 图的基础实现（邻接表和邻接矩阵）
- **ShortestPath.java** - 最短路径算法集合

**包含算法：**
- 深度优先搜索 (DFS)
- 广度优先搜索 (BFS)
- Dijkstra最短路径算法
- A*搜索算法
- 拓扑排序

### 13. 回溯 (Backtracking)
- **BacktrackingAlgorithms.java** - 回溯算法集合

**包含问题：**
- 八皇后问题
- 0-1背包问题（回溯解法）
- 全排列生成
- 组合生成
- 数独求解
- 迷宫路径查找

### 14. 分治 (Divide and Conquer)
- **DivideConquerAlgorithms.java** - 分治算法集合

**包含算法：**
- 逆序对计算
- 最大子数组和问题
- 快速幂算法
- 大整数乘法（Karatsuba算法）
- 归并排序
- 快速排序

### 15. 动态规划 (Dynamic Programming)
- **DynamicProgramming.java** - 动态规划算法集合

**包含问题：**
- 0-1背包问题
- 最小路径和
- 编辑距离（莱文斯坦距离）
- 最长公共子序列 (LCS)
- 最长递增子序列 (LIS)
- 硬币找零问题
- 最大子数组和（DP解法）

## 🎯 LeetCode经典题目实现

### 16. LeetCode数组问题 (Array Problems)
- **ArrayProblems.java** - LeetCode数组相关经典题目

**包含题目：**
- Two Sum (两数之和)
- 3Sum (三数之和)
- Container With Most Water (盛最多水的容器)
- Merge Intervals (合并区间)
- Rotate Array (旋转数组)
- Product of Array Except Self (除自身以外数组的乘积)

### 17. LeetCode链表问题 (LinkedList Problems)
- **LinkedListProblems.java** - LeetCode链表相关经典题目

**包含题目：**
- Reverse Linked List (反转链表)
- Merge Two Sorted Lists (合并两个有序链表)
- Linked List Cycle (环形链表)
- Intersection of Two Linked Lists (相交链表)
- Remove Nth Node From End (删除倒数第N个节点)
- Add Two Numbers (两数相加)

### 18. LeetCode栈问题 (Stack Problems)
- **StackProblems.java** - LeetCode栈相关经典题目

**包含题目：**
- Valid Parentheses (有效的括号)
- Min Stack (最小栈)
- Largest Rectangle in Histogram (柱状图中最大的矩形)
- Evaluate Reverse Polish Notation (逆波兰表达式求值)
- Daily Temperatures (每日温度)

### 19. LeetCode队列问题 (Queue Problems)
- **QueueProblems.java** - LeetCode队列相关经典题目

**包含题目：**
- Sliding Window Maximum (滑动窗口最大值)
- Implement Stack using Queues (用队列实现栈)
- Design Circular Queue (设计循环队列)
- Moving Average from Data Stream (数据流中的移动平均值)

### 20. LeetCode递归问题 (Recursion Problems)
- **RecursionProblems.java** - LeetCode递归相关经典题目

**包含题目：**
- Climbing Stairs (爬楼梯)
- Generate Parentheses (括号生成)
- Subsets (子集)
- Permutations (全排列)
- Fibonacci Number (斐波那契数)

### 21. LeetCode二分查找问题 (Binary Search Problems)
- **BinarySearchProblems.java** - LeetCode二分查找相关经典题目

**包含题目：**
- Search in Rotated Sorted Array (搜索旋转排序数组)
- Find Peak Element (寻找峰值)
- Search for a Range (搜索范围)
- Find Minimum in Rotated Sorted Array (寻找旋转排序数组中的最小值)
- Search a 2D Matrix (搜索二维矩阵)

### 22. LeetCode字符串问题 (String Problems)
- **StringProblems.java** - LeetCode字符串相关经典题目

**包含题目：**
- Longest Palindromic Substring (最长回文子串)
- Valid Anagram (有效的字母异位词)
- Group Anagrams (字母异位词分组)
- Longest Substring Without Repeating Characters (无重复字符的最长子串)
- Implement strStr() (实现strStr())

### 23. LeetCode二叉树问题 (Tree Problems)
- **TreeProblems.java** - LeetCode二叉树相关经典题目

**包含题目：**
- Binary Tree Inorder Traversal (二叉树的中序遍历)
- Maximum Depth of Binary Tree (二叉树的最大深度)
- Path Sum (路径总和)
- Serialize and Deserialize Binary Tree (二叉树的序列化与反序列化)
- Lowest Common Ancestor (最近公共祖先)

### 24. LeetCode图问题 (Graph Problems)
- **GraphProblems.java** - LeetCode图相关经典题目

**包含题目：**
- Number of Islands (岛屿数量)
- Course Schedule (课程表)
- Clone Graph (克隆图)
- Word Ladder (单词接龙)
- Topological Sorting (拓扑排序)

### 25. LeetCode回溯问题 (Backtrack Problems)
- **BacktrackProblems.java** - LeetCode回溯相关经典题目

**包含题目：**
- N-Queens (N皇后)
- Combination Sum (组合总和)
- Word Search (单词搜索)
- Sudoku Solver (解数独)
- Generate Parentheses (括号生成)

### 26. LeetCode分治问题 (Divide Conquer Problems)
- **DivideConquerProblems.java** - LeetCode分治相关经典题目

**包含题目：**
- Maximum Subarray (最大子数组和)
- Majority Element (多数元素)
- Kth Largest Element in an Array (数组中的第K个最大元素)
- Merge k Sorted Lists (合并K个升序链表)
- Pow(x, n) (快速幂)

### 27. LeetCode动态规划问题 (Dynamic Programming Problems)
- **DynamicProgrammingProblems.java** - LeetCode动态规划相关经典题目

**包含题目：**
- Coin Change (零钱兑换)
- Longest Increasing Subsequence (最长递增子序列)
- Longest Common Subsequence (最长公共子序列)
- Edit Distance (编辑距离)
- Minimum Path Sum (最小路径和)
- House Robber (打家劫舍)

## 🚀 使用方法

每个算法类都包含完整的测试用例和使用示例。可以直接运行main方法查看算法效果：

```bash
# 编译并运行特定算法
javac samples/sorts/SortingAlgorithms.java
java samples.sorts.SortingAlgorithms

# 或者运行其他算法
java samples.graph.Graph
java samples.dynamic_programming.DynamicProgramming
```

## 📊 复杂度分析

每个算法实现都包含详细的复杂度分析：

| 算法类别 | 时间复杂度 | 空间复杂度 | 适用场景 |
|---------|-----------|-----------|----------|
| 快速排序 | O(n log n) | O(log n) | 通用排序 |
| 归并排序 | O(n log n) | O(n) | 稳定排序 |
| 堆排序 | O(n log n) | O(1) | 内存受限 |
| 二分查找 | O(log n) | O(1) | 有序数组查找 |
| 哈希表 | O(1) | O(n) | 快速查找 |
| DFS/BFS | O(V+E) | O(V) | 图遍历 |
| Dijkstra | O((V+E)log V) | O(V) | 最短路径 |
| 动态规划 | O(n²) ~ O(n³) | O(n) ~ O(n²) | 优化问题 |

## 🎯 学习建议

1. **基础数据结构**：先掌握数组、链表、栈、队列的基本操作
2. **排序算法**：理解各种排序算法的原理和适用场景
3. **查找算法**：掌握二分查找和哈希表的使用
4. **树和图**：学习树的遍历和图的搜索算法
5. **高级算法**：最后学习回溯、分治、动态规划等算法思想

## 🔧 代码特点

- **详细注释**：每个方法都有完整的中文注释说明
- **复杂度分析**：标注时间和空间复杂度
- **测试用例**：包含完整的测试代码和边界情况处理
- **性能对比**：提供不同算法的性能对比测试
- **实际应用**：结合实际应用场景的示例

## 📚 参考资料

本实现基于王争老师的《数据结构与算法之美》课程内容，结合经典算法教材和工程实践经验。

## 🤝 贡献

欢迎提交Issue和Pull Request来改进算法实现和文档说明。

---