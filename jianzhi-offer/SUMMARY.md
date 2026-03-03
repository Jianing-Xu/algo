# 《剑指 Offer》题型归纳与实现说明

这个模块按《剑指 Offer》的高频题型组织代码，目标是把书里最常见、最有代表性的题目整理成可运行实现，并把解题模型沉淀成可复用套路。

## 目录结构

- `array`：数组、矩阵、排序拼接、贪心扫描
- `string`：字符串替换、全排列、滑动窗口、回溯搜索
- `linkedlist`：倒序打印、删除节点、反转、合并、复杂链表复制
- `binarytree`：重建、镜像、对称、层序、路径和、BST 特性
- `stackqueue`：两栈实现队列、最小栈、栈序列校验
- `searchsort`：旋转数组二分
- `recursiondp`：斐波那契、剪绳子、机器人数位 DP、数位枚举、丑数
- `bit`：位运算统计

## 1. 数组与矩阵

核心识别点：是否存在单调性、局部最优是否可累积、是否能原地交换或线性扫描。

- `DuplicateNumberInArray`：利用“值域与下标对应”或辅助集合找重复数。
- `FindInSortedMatrix`：从右上角或左下角开始，借助行列单调性排除区域。
- `ReorderArrayByParity`：双指针原地调整奇偶顺序。
- `SpiralMatrixPrinter`：按边界收缩打印矩阵。
- `MajorityElement`：Boyer-Moore 投票法。
- `LeastKNumbers`：维护大小为 `k` 的最大堆。
- `MaxSubarraySum`：Kadane 算法。
- `MinNumberByConcatenation`：核心是自定义排序规则 `a+b < b+a`。
- `MaxValueInGrid`：二维 DP，状态是走到当前位置的最大价值。

面试关注点：

- 是否先识别出“单调”和“有序”。
- 原地交换是否会破坏未处理区间。
- 全负数、空数组、单行单列等边界是否成立。

## 2. 字符串与回溯

核心识别点：字符串往往可以转成字符数组处理；一旦题目要求“是否存在一条路径”“输出所有排列”，通常进入 DFS/回溯模型。

- `ReplaceSpaces`：字符串构造题，注意空指针和空格替换规则。
- `PermutationGenerator`：排序 + 去重剪枝生成全排列。
- `LongestSubstringWithoutRepeat`：滑动窗口维护无重复区间。
- `PathInMatrix`：DFS 搜索矩阵路径，配合访问标记避免重复走格子。

面试关注点：

- 回溯是否正确恢复现场。
- 全排列遇到重复字符时是否去重。
- 滑动窗口左边界是否只前进不回退。

## 3. 链表

核心识别点：链表题本质是指针重连和边界保护，重点是先保存 `next`，再修改引用。

- `ReversePrintList`：栈或递归倒序输出。
- `DeleteNodeInList`：删除目标节点需要处理头节点和不存在节点。
- `KthNodeFromEnd`：快慢指针保持固定间距。
- `ReverseLinkedList`：三指针原地反转。
- `MergeSortedLists`：双指针合并两个有序链表。
- `CopyComplexList`：原地穿插复制节点，再拆分链表。

面试关注点：

- 是否覆盖空链表、删除头节点、删除尾节点。
- 复杂链表复制能否做到 `O(1)` 额外空间。
- 反转或合并后是否保持结构完整。

## 4. 二叉树

核心识别点：树题通常围绕递归定义展开，关键是明确“当前节点需要从子树拿什么信息”。

- `BuildTreeFromTraversals`：前序首元素确定根，中序切分左右子树。
- `SubtreeStructure`：双层递归判断局部结构匹配。
- `MirrorBinaryTree`：交换左右子树。
- `SymmetricBinaryTree`：同时比较左右镜像位置。
- `LevelOrderTraversal`：队列驱动 BFS。
- `VerifyPostorderOfBst`：后序最后一个值是根，左右区间满足 BST 约束。
- `PathSumInTree`：DFS 维护路径和路径栈。
- `BstToDoublyLinkedList`：中序遍历把 BST 原地串成双向链表。

面试关注点：

- 递归终止条件是否准确。
- 路径类题目是否在回溯时移除尾元素。
- BST 题能否利用中序有序性质。

## 5. 栈与队列

核心识别点：常考“用一种结构模拟另一种结构”，以及辅助栈同步维护额外信息。

- `CQueue`：输入栈 + 输出栈实现队列。
- `MinStack`：同步最小值栈，`min()` 保持 `O(1)`。
- `ValidateStackSequences`：模拟压栈出栈过程校验序列是否合法。

面试关注点：

- 搬运元素时机是否正确。
- 空栈、重复值、非法序列是否能处理。

## 6. 递归、动态规划与位运算

核心识别点：先写出递归定义，再压缩成迭代 DP；位运算题重点是利用二进制性质做常数级优化。

- `FibonacciSequence`：递推滚动变量。
- `CuttingRope`：数学拆分与 DP 都能做，优先识别 3 的贪心拆分。
- `MovingCount`：DFS/BFS + 数位和约束。
- `PowerFunction`：快速幂。
- `PrintOneToMaxNDigits`：模拟大数递归枚举，避免整数溢出。
- `TranslateNumber`：按两位是否可翻译做线性 DP。
- `UglyNumber`：三个指针生成丑数序列。
- `NumberOfOneBits`：`n & (n - 1)` 每次消掉最低位的 1。

面试关注点：

- 状态定义和初始化是否一致。
- 幂函数能否处理负指数和最小整数。
- 大数打印是否绕开数值范围限制。

## 使用方式

进入模块目录执行：

```bash
mvn test
```

## 扩展建议

如果继续按书完整补齐，可以优先扩展下面几类：

1. 二叉树专题：序列化/反序列化、平衡树、最近公共祖先。
2. 数组专题：逆序对、数据流中位数、扑克牌顺子。
3. 数学专题：约瑟夫环、求 1 到 n 的和、不用加减乘除做加法。
4. 设计专题：字符流第一个不重复字符、队列最大值、股票最大利润。
