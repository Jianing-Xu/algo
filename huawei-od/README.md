# 🏢 华为 OD 机考题解集

> 华为 OD（Outsourcing Development）机考高频真题的 Java 实现，每道题均包含题目描述、解题思路与完整代码。

## 📊 题目列表

### 🔥 动态规划 / 背包问题

| 文件 | 题目 | 核心算法 |
|------|------|----------|
| `WanderingEarth` | 流浪地球 | 网格路径最大和 DP |
| `HopscotchGame` | 跳房子 | 线性 DP（最大收益跳跃） |
| `GameGrouping` | 游戏分组 | 0-1背包变形（最小差分割） |
| `GreedySinger` | 贪心歌手 | 0-1背包（时间=容量，人气=价值） |
| `VirtualGameFinance` | 虚拟游戏理财 | 0-1背包 |
| `ShoppingDiscount` | 网上商城优惠活动 | 子集和问题 |
| `CodingImprovement` | 编码能力提升计划 | 打家劫舍变形 DP |
| `Wonderland` | Wonderland | 最低票价 DP (类LeetCode 983) |

### 🔍 搜索 / 图论

| 文件 | 题目 | 核心算法 |
|------|------|----------|
| `MaxValueMineHeap` | 寻找最大价值矿堆 | BFS 连通分量 |
| `MatrixDiffusion` | 矩阵扩散 | 多源 BFS（腐烂橘子变形） |
| `SingleEntryFreeArea` | 查找单入口空闲区域 | BFS + 边界条件判断 |
| `SecretElevator` | 乘坐保密电梯 | BFS 最短路径 |
| `ShortestPathIntersection` | 路口最短时间 | Dijkstra 最短路径 |
| `HeadTailNodes` | 有向网络头尾节点 | 入度/出度统计 |
| `MicroserviceIntegrationTest` | 微服务集成测试 | 拓扑排序层序输出 |
| `ModuleExecutionOrder` | 产品模块执行顺序 | 拓扑排序 + 环检测 |
| `WhisperPass` | 传递悄悄话 | 二叉树 DFS 最长路径 |

### 🎯 贪心算法

| 文件 | 题目 | 核心算法 |
|------|------|----------|
| `EfficientTaskPlanning` | 高效的任务规划 | 按截止时间排序贪心 |
| `TaskPointsMax` | 执行任务赚积分 | 反悔贪心 + 小顶堆 |
| `HuffmanTree` | 生成哈夫曼树 | 优先队列合并求WPL |
| `WifiCoverage` | WIFI覆盖 | 区间覆盖贪心 |
| `StreetLightIllumination` | 路灯照明问题 | 区间合并 |

### 📝 字符串处理

| 文件 | 题目 | 核心算法 |
|------|------|----------|
| `LongestMathExpression` | 最长数学表达式 | 滑动窗口提取 + 栈计算 |
| `SymmetricString` | 对称字符串 | 中心扩展法求最长回文 |
| `LogDesensitization` | 日志脱敏 | 正则替换 |
| `SensitiveFieldEncrypt` | 敏感字段加密 | 字符串分割 + SHA256 |
| `LispCalculator` | 仿LISP运算 | 递归下降解析器 |
| `PerfectWalkPosition` | 获得完美走位 | 滑动窗口 |

### 🗂️ 模拟 / 数据结构

| 文件 | 题目 | 核心算法 |
|------|------|----------|
| `AppAntiAddiction` | 手机APP防沉迷 | HashMap 模拟 |
| `MessageQueue` | 模拟消息队列 | Queue 模拟 |
| `FileCacheSystem` | 文件缓存系统 | LinkedHashMap LRU |
| `SimpleMemoryPool` | 简易内存池 | TreeMap First-Fit 分配 |
| `WorkQueueSimulation` | 模拟工作队列 | 优先队列调度 |
| `PortGroupMerge` | 关联端口组合并 | 并查集 Union-Find |

### 🔢 数学 / 位运算

| 文件 | 题目 | 核心算法 |
|------|------|----------|
| `IntegerEncoding` | 整数编码 | Varint 位运算 |
| `DivideApples` | 分苹果 | 组合数学 C(m-1,n-1) |
| `KthPermutation` | 第K个排列 | 康托展开逆运算 |
| `BitErrorRate` | 计算误码率 | 逐位比较 |
| `ConstructSequence` | 构造数列 | Collatz 猜想模拟 |

### 📋 排序 / 统计

| 文件 | 题目 | 核心算法 |
|------|------|----------|
| `Championship` | 冠亚季军 | 排序取Top3 |
| `HeightQueue` | 高矮个子排队 | 排序 + 交错排列 |
| `CurrencyConversion` | 货币单位换算 | 字符串解析 + 单位转换 |
| `StarsElection` | 明日之星选举 | HashMap 统计 + 排序 |
| `HotWebsite` | 热点网站统计 | HashMap<URL, Set> 去重统计 |
| `AbnormalCheckIn` | 异常打卡记录 | 嵌套 HashMap 统计 |
| `GuessNumber` | 猜数字 | HashSet 求交集 |
| `TupleCount` | 求符合条件元组 | 排序 + 双指针 |

### 🗺️ 其他

| 文件 | 题目 | 核心算法 |
|------|------|----------|
| `FindTestingPoint` | 寻找核酸检测点 | 距离计算 + 排序 |
| `IpToCity` | 根据IP查找城市 | IP转长整数 + 范围查找 |
| `ExcelCellStatistic` | Excel单元格统计 | 列号解析 + 区域遍历 |
| `AutoParking` | 自动泊车 | 连续0最大长度 |

## 💡 代码特色

- ✅ 每道题均自带 `main` 方法，可通过 `Scanner` 直接输入测试
- ✅ 包含**题目描述**和**解题思路**注释
- ✅ 按算法类型分类，方便系统性复习
- ✅ 覆盖 DP、贪心、BFS/DFS、拓扑排序、背包、并查集等高频考点

## 🏷️ 算法分类速查

| 算法类型 | 涉及题目数 | 关键词 |
|----------|-----------|--------|
| 动态规划 | 8 | 背包、路径和、打家劫舍 |
| BFS/DFS | 9 | 连通分量、最短路径、拓扑排序 |
| 贪心 | 5 | 区间覆盖、任务调度、哈夫曼 |
| 字符串 | 6 | 回文、解析、滑动窗口 |
| 模拟 | 6 | 队列、缓存、内存池 |
| 数学 | 5 | 组合数、位运算、排列 |
| 排序统计 | 8 | HashMap、排序、去重 |
