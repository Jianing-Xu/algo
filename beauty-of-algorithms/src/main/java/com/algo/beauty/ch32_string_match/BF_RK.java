package com.algo.beauty.ch32_string_match;

/**
 * 字符串匹配算法：BF算法 (Brute Force) 和 RK算法 (Rabin-Karp)
 *
 * 【核心原理】
 * 1. BF 算法（暴力匹配算法）：
 *    在主串中，检查起始位置分别是 0、1、2...n-m 且长度为 m 的 n-m+1 个子串，
 *    看有没有跟模式串完全匹配的。
 *    最坏时间复杂度：O(n*m)，但在实际开发中比较常用，因为实现简单且一般情况下的效率并不低。
 *
 * 2. RK 算法（哈希匹配算法）：
 *    RK 算法是 BF 算法的升级版。它通过对主串中 n-m+1 个子串分别求哈希值，
 *    然后逐个与模式串的哈希值进行比较（哈希值比较是一个O(1)的操作）。
 *    如果哈希值相等，那么再去比对具体的字符串内容（防止哈希冲突）。
 *    如果哈希值不相等，说明子串和模式串肯定不匹配。
 *    时间复杂度可以优化到：O(n)。
 */
public class BF_RK {

    /**
     * BF（暴力）算法实现
     * @param mainStr 主串（待匹配的原字符串）
     * @param pattern 模式串（我们要寻找的特征串）
     * @return 匹配成功时的起始下标，失败返回 -1
     */
    public static int bfMatch(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;
        int n = mainStr.length();
        int m = pattern.length();
        if (n < m) return -1;

        char[] mainArr = mainStr.toCharArray();
        char[] patArr = pattern.toCharArray();

        // 外层循环：主串指针 i 从 0 走到 n-m
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            // 内层循环：逐个字符与模式串对比
            for (; j < m; j++) {
                if (mainArr[i + j] != patArr[j]) {
                    break; // 只要遇到不相等的字符，立马停下宣告这一轮失败
                }
            }
            if (j == m) {
                return i; // 如果 j 一路平安地走到了模式串最后，说明完全匹配了
            }
        }
        return -1; // 最终没能匹配
    }

    /**
     * RK (Rabin-Karp) 算法实现 (这里使用一个简单的Hash函数示例)
     * 【重要优化点】：
     * 为了让相邻子串算哈希值变得极快，我们在RK算法中一般使用“进制思想”设计哈希算法。
     * 例如 26 个字母我们当作 26 进制，像计算 "abc" 的十进制值一样，这样在计算下一个子串 "bcd" 时
     * 只需要减去 'a' 的贡献，加上 'd' 的贡献即可（滑动窗口求值）。
     * 下面的代码为了演示清晰，使用较为简单的非进制Hash演示逻辑概念。
     */
    public static int rkMatch(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;
        int n = mainStr.length();
        int m = pattern.length();
        if (n < m) return -1;
        
        // 我们以所有字符 ASCII 码的总和作为简易哈希函数（真实生产中会引发哈希冲突，需要二次确认）
        int patHash = 0;
        int subHash = 0;

        // 【第 1 步】计算模式串的哈希值，以及主串中第一个长度为 m 的子串的哈希值
        for (int i = 0; i < m; i++) {
            patHash += pattern.charAt(i);
            subHash += mainStr.charAt(i);
        }

        // 第一次对比：如果起步的 Hash 就一样，二次验证字符串是否同样一致
        if (patHash == subHash && mainStr.substring(0, m).equals(pattern)) {
            return 0;
        }

        // 【第 2 步】通过滑动窗口的方式求解接下来的 n-m 个子串的 Hash
        // 例如主串是 "abcde"，模式是 "cde"
        // 第一次子串是 "abc", Hash(abc)
        // 把它右移一位变成 "bcd"，它的 Hash(bcd) 其实就等于 Hash(abc) - Hash('a') + Hash('d')
        // 这样避免了每次都重新遍历 m 个字符，把 O(m) 的哈希计算降低到了 O(1)
        for (int i = 1; i <= n - m; i++) {
            // 滑动窗口：减去移出去的前一个字符，加上新进来的后一个字符
            subHash = subHash - mainStr.charAt(i - 1) + mainStr.charAt(i + m - 1);

            if (subHash == patHash) {
                // 哈希值相等时还可能有哈希冲突，所以必须对比明文
                if (mainStr.substring(i, i + m).equals(pattern)) {
                    return i;
                }
            }
        }
        
        return -1;
    }
}
