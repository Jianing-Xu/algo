# 🧮 算法学习与练习仓库

> 系统化的算法学习、刷题与面试准备仓库，使用 Java 实现。

---

## 📁 项目结构

```
algo/
├── beauty-of-algorithms/    ← 《数据结构与算法之美》课程代码 + LeetCode 推荐题
├── coding-interview-guide/  ← 《程序员代码面试指南》题型归纳
├── huawei-od/               ← 华为 OD 机考题解集
├── jianzhi-offer/           ← 《剑指 Offer》示例与高频题型总结
└── README.md
```

| 子项目 | 说明 | 题解数量 |
|--------|------|----------|
| [beauty-of-algorithms](./beauty-of-algorithms/) | 王争《数据结构与算法之美》全部核心代码 + LeetCode 推荐题 | 38 |
| [coding-interview-guide](./coding-interview-guide/) | 《程序员代码面试指南》题型归纳与测试 | 12 |
| [huawei-od](./huawei-od/) | 华为 OD 机考高频真题题解 | 52 |
| [jianzhi-offer](./jianzhi-offer/) | 《剑指 Offer》经典题实现与题型归纳 | 30+ |

## 🛠️ 技术栈

- **语言**: Java 17
- **构建工具**: Maven
- **测试框架**: JUnit 5
- **包组织**: `com.xujn.*`

## 🚀 快速开始

```bash
# 克隆仓库
git clone https://github.com/Jianing-Xu/algo.git

# 进入某个子项目
cd beauty-of-algorithms
# 或
cd jianzhi-offer
# 或
cd huawei-od

# 编译
mvn compile

# 运行某个算法的 main 方法（以华为OD的哈夫曼树为例）
mvn exec:java -Dexec.mainClass="com.xujn.od.HuffmanTree"
```

## 📖 学习路线建议

```
基础数据结构 → 排序与查找 → 树与图 → 高级算法思想 → 字符串匹配 → 刷题实战
     ↓              ↓           ↓           ↓              ↓            ↓
   数组           冒泡排序     二叉树      分治算法        KMP        LeetCode
   链表           归并排序     BST        回溯算法        BM算法     华为OD
   栈/队列        快速排序     堆/图      动态规划        Trie树
```

## 📌 持续更新中

本仓库会持续更新更多算法题解与学习笔记，欢迎 ⭐ Star！
