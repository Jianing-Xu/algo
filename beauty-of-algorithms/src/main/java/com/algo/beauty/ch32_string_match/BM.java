package com.algo.beauty.ch32_string_match;

/**
 * 字符串匹配算法：Boyer-Moore (BM算法)
 * 
 * 【核心原理】
 * BM 算法包含两部分规则来极大地跳过不必要的比较：
 * 
 * 1. 坏字符规则 (Bad Character Rule)
 *    发生不匹配时，我们称主串中的那个不匹配字符为“坏字符”。
 *    BM 算法不是向后滑动 1 位，而是去模式串中查找这个坏字符最后出现的位置在哪。
 *    - 如果模式串中有这个坏字符，直接将模式串中该字符对齐主串。
 *    - 如果模式串中根本不存在这个坏字符，那么我们可以安全地直接把整个模式串滑动到它之后。
 * 
 * 2. 好后缀规则 (Good Suffix Rule)
 *    我们在从后往前匹配时，遇到不匹配字符之前，后面匹配上的那一段称为“好后缀”。
 *    如果主串和模式串产生了后缀“相合片段”，BM算法要求我们去找模式串前面有没有一样的“片段”，
 *    或是“片段的前边”与“模式串的前缀”能有重叠，如果有再把它位移过去对齐。
 * 
 * **总结**：BM 算法从模式串的末尾向前开始比对，结合这两个规则，一次可以跳跃非常大的长度（甚至好几个模式串的长度）。
 * 实际工业界（比如你在 IDE 里按 Ctrl+F 搜索）经常用到，由于极大的跳跃其最坏时间复杂度也可优化到 O(n)。
 */
public class BM {

    private static final int SIZE = 256; 

    /**
     * 第一部分：构建坏字符哈希表
     * 作用：在 O(1) 的时间内查找到散列字符在模式串中最后出现的位置，如果没出现就返回 -1。
     * @param b 模式串的字符数组
     * @param m 模式串长度
     * @param bc 一个大小为 256 甚至更高字符集大小的位图/哈希表，存着每个字符代表的 ASCII 值在模式串中的最大下标
     */
    private void generateBC(char[] b, int m, int[] bc) {
        // 先全部初始化为 -1，意思是没找着
        for (int i = 0; i < SIZE; ++i) {
            bc[i] = -1; 
        }
        
        // 正向遍历模式串，后出现的相同字符将覆盖前面字符的下标，这也是为什么叫“最后出现位置”
        for (int i = 0; i < m; ++i) {
            int ascii = (int) b[i]; // 将字符直接转成他的整数 ASCII 码表示
            bc[ascii] = i; 
        }
    }

    /**
     * 第二部分：构建好后缀用到的 suffix 和 prefix 数组
     * 作用：由于最长公共前后后缀的思想不好懂，这部分会记录好后缀是否存在另一个与之相合的前缀。
     * 
     * @param b      模式串
     * @param m      模式串长度
     * @param suffix suffix[k]表示长度为k的后缀子串在模式串中，能够相匹配的前置子串的起始下标
     * @param prefix prefix[k]表示长度为k的好后缀，是否有能和模式串前缀重合的子串
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        // 先做各种赋初值
        for (int i = 0; i < m; ++i) { 
            suffix[i] = -1;
            prefix[i] = false;
        }

        // i 表示模式串好后缀前面的每个位置，用来跟在屁股后面的模式后缀对抗
        for (int i = 0; i < m - 1; ++i) { 
            int j = i;
            int k = 0; // k 表示公共后缀子串长度

            // 如果当前的字符和最后一个尾部字符能够相对
            while (j >= 0 && b[j] == b[m - 1 - k]) { 
                --j;
                ++k; // 每匹配上一个，公共长度就增加 1
                suffix[k] = j + 1; // 记录长度为 k 的这片海域对应的最早发现起点
            }

            // 要是一直推进到 j < 0 都成功了，证明长度为 k 的这个好尾巴竟然碰头到了起点！那毫无疑问前缀为真
            if (j == -1) prefix[k] = true; 
        }
    }

    /**
     * 主计算入口：仅实现了坏字符规则和好后缀规则
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     */
    public int bmSearch(char[] a, int n, char[] b, int m) {
        // 第一件事：建散列表方便记录和快速查找
        int[] bc = new int[SIZE]; 
        generateBC(b, m, bc); // 这是为了满足“坏字符规则查找”
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix); // 这是为了满足“好后缀规则查找”
        
        int i = 0; // i 表示主串与模式串对齐的开头位置
        
        while (i <= n - m) {
            int j;
            
            // 从尾部！倒着！开始往前匹配模式串的字符，只要相同，就一直回溯递减
            for (j = m - 1; j >= 0; --j) { 
                if (a[i + j] != b[j]) break; // 坏字符找到了，停下，记录它的位置在 j
            }

            // 如果我们在内层循环中没有一次遇到了 break，最后 j == -1
            // 恭喜你，说明没有一个字符不相等。那么我们终于匹配成功啦！
            if (j < 0) { 
                return i; // i 就是匹配出来的原位
            }

            // 但是如果没走到尽头呢？那代表遇到了坏字符，我们要计算往后滑倒的步数了
            
            // 【策略一】利用坏字符计算：将现在的位置 j，减掉这个坏字符在模式串里最后出现的那个位置 (bc[坏字符的ASCII])
            int x = j - bc[(int) a[i + j]];

            // 【策略二】利用好后缀计算：看看现在的好后缀存不存在
            int y = 0;
            if (j < m - 1) { 
                y = moveByGS(j, m, suffix, prefix);
            }

            // 选择上面两种跳过规则中“最大”的，能跳越远越省事
            i = i + Math.max(x, y);
        }
        
        return -1; // 什么都没找到
    }

    // 辅助好后缀计算出究竟要向后滑动几位
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j; // k就是好后缀的长度
        
        if (suffix[k] != -1) return j - suffix[k] + 1;
        
        for (int r = j + 2; r <= m - 1; ++r) {
            if (prefix[m - r] == true) {
                return r;
            }
        }
        return m;
    }
}
