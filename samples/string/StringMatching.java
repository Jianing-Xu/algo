package string;

import java.util.*;

/**
 * 字符串匹配算法集合
 * 包含朴素匹配、KMP、Boyer-Moore等算法
 */
public class StringMatching {
    
    /**
     * 朴素字符串匹配算法（暴力匹配）
     * 时间复杂度：O(nm)，其中n是文本长度，m是模式长度
     * 空间复杂度：O(1)
     * 
     * @param text 文本字符串
     * @param pattern 模式字符串
     * @return 第一次匹配的位置，如果不匹配返回-1
     */
    public static int naiveSearch(String text, String pattern) {
        if (text == null || pattern == null || pattern.length() > text.length()) {
            return -1;
        }
        
        int n = text.length();
        int m = pattern.length();
        
        // 遍历文本的每个可能起始位置
        for (int i = 0; i <= n - m; i++) {
            int j;
            
            // 检查从位置i开始是否匹配模式
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            
            // 如果完全匹配
            if (j == m) {
                return i;
            }
        }
        
        return -1; // 未找到匹配
    }
    
    /**
     * 朴素算法查找所有匹配位置
     * 
     * @param text 文本字符串
     * @param pattern 模式字符串
     * @return 所有匹配位置的列表
     */
    public static List<Integer> naiveSearchAll(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        
        if (text == null || pattern == null || pattern.length() > text.length()) {
            return matches;
        }
        
        int n = text.length();
        int m = pattern.length();
        
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                matches.add(i);
            }
        }
        
        return matches;
    }
    
    /**
     * KMP（Knuth-Morris-Pratt）字符串匹配算法
     * 时间复杂度：O(n + m)，空间复杂度：O(m)
     * 
     * @param text 文本字符串
     * @param pattern 模式字符串
     * @return 第一次匹配的位置，如果不匹配返回-1
     */
    public static int kmpSearch(String text, String pattern) {
        if (text == null || pattern == null || pattern.length() > text.length()) {
            return -1;
        }
        
        int n = text.length();
        int m = pattern.length();
        
        // 构建部分匹配表（失效函数）
        int[] lps = buildLPSArray(pattern);
        
        int i = 0; // 文本指针
        int j = 0; // 模式指针
        
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == m) {
                return i - j; // 找到匹配
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1]; // 使用失效函数
                } else {
                    i++;
                }
            }
        }
        
        return -1; // 未找到匹配
    }
    
    /**
     * 构建KMP算法的LPS（Longest Proper Prefix which is also Suffix）数组
     * 
     * @param pattern 模式字符串
     * @return LPS数组
     */
    private static int[] buildLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // 最长相等前后缀的长度
        int i = 1;
        
        lps[0] = 0; // 第一个字符的LPS值总是0
        
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
    
    /**
     * KMP算法查找所有匹配位置
     * 
     * @param text 文本字符串
     * @param pattern 模式字符串
     * @return 所有匹配位置的列表
     */
    public static List<Integer> kmpSearchAll(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        
        if (text == null || pattern == null || pattern.length() > text.length()) {
            return matches;
        }
        
        int n = text.length();
        int m = pattern.length();
        int[] lps = buildLPSArray(pattern);
        
        int i = 0, j = 0;
        
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == m) {
                matches.add(i - j);
                j = lps[j - 1]; // 继续查找下一个匹配
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return matches;
    }
    
    /**
     * Boyer-Moore算法的坏字符规则实现
     * 时间复杂度：平均O(n/m)，最坏O(nm)
     * 
     * @param text 文本字符串
     * @param pattern 模式字符串
     * @return 第一次匹配的位置，如果不匹配返回-1
     */
    public static int boyerMooreSearch(String text, String pattern) {
        if (text == null || pattern == null || pattern.length() > text.length()) {
            return -1;
        }
        
        int n = text.length();
        int m = pattern.length();
        
        // 构建坏字符表
        int[] badChar = buildBadCharTable(pattern);
        
        int shift = 0; // 模式相对于文本的偏移量
        
        while (shift <= n - m) {
            int j = m - 1;
            
            // 从右向左匹配
            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }
            
            if (j < 0) {
                return shift; // 找到匹配
            } else {
                // 根据坏字符规则计算偏移量
                char badCharacter = text.charAt(shift + j);
                shift += Math.max(1, j - badChar[badCharacter]);
            }
        }
        
        return -1; // 未找到匹配
    }
    
    /**
     * 构建Boyer-Moore算法的坏字符表
     * 
     * @param pattern 模式字符串
     * @return 坏字符表
     */
    private static int[] buildBadCharTable(String pattern) {
        int[] badChar = new int[256]; // ASCII字符集
        
        // 初始化所有字符的位置为-1
        Arrays.fill(badChar, -1);
        
        // 记录模式中每个字符最后出现的位置
        for (int i = 0; i < pattern.length(); i++) {
            badChar[pattern.charAt(i)] = i;
        }
        
        return badChar;
    }
    
    /**
     * Rabin-Karp算法（滚动哈希）
     * 平均时间复杂度：O(n + m)，最坏时间复杂度：O(nm)
     * 
     * @param text 文本字符串
     * @param pattern 模式字符串
     * @return 第一次匹配的位置，如果不匹配返回-1
     */
    public static int rabinKarpSearch(String text, String pattern) {
        if (text == null || pattern == null || pattern.length() > text.length()) {
            return -1;
        }
        
        int n = text.length();
        int m = pattern.length();
        int prime = 101; // 用于哈希的质数
        int base = 256;  // 字符集大小
        
        long patternHash = 0; // 模式的哈希值
        long textHash = 0;    // 文本窗口的哈希值
        long h = 1;           // base^(m-1) % prime
        
        // 计算h = base^(m-1) % prime
        for (int i = 0; i < m - 1; i++) {
            h = (h * base) % prime;
        }
        
        // 计算模式和文本第一个窗口的哈希值
        for (int i = 0; i < m; i++) {
            patternHash = (base * patternHash + pattern.charAt(i)) % prime;
            textHash = (base * textHash + text.charAt(i)) % prime;
        }
        
        // 滑动窗口
        for (int i = 0; i <= n - m; i++) {
            // 如果哈希值匹配，进行字符比较
            if (patternHash == textHash) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return i;
                }
            }
            
            // 计算下一个窗口的哈希值
            if (i < n - m) {
                textHash = (base * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % prime;
                
                // 确保哈希值为正数
                if (textHash < 0) {
                    textHash += prime;
                }
            }
        }
        
        return -1; // 未找到匹配
    }
    
    /**
     * 字符串匹配性能测试
     * 
     * @param algorithm 算法名称
     * @param text 文本
     * @param pattern 模式
     * @param searchFunction 搜索函数
     */
    public static void performanceTest(String algorithm, String text, String pattern, 
                                     java.util.function.BiFunction<String, String, Integer> searchFunction) {
        long startTime = System.nanoTime();
        int result = searchFunction.apply(text, pattern);
        long endTime = System.nanoTime();
        
        double timeMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("%s: 结果=%d, 耗时=%.3fms%n", algorithm, result, timeMs);
    }
    
    /**
     * 生成随机字符串
     * 
     * @param length 长度
     * @param charset 字符集
     * @return 随机字符串
     */
    public static String generateRandomString(int length, String charset) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charset.length());
            sb.append(charset.charAt(index));
        }
        
        return sb.toString();
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 字符串匹配算法测试 ===\n");
        
        // 基本功能测试
        System.out.println("1. 基本功能测试:");
        String text = "ABABDABACDABABCABCABCABCABC";
        String pattern = "ABABCABCABCABC";
        
        System.out.println("文本: " + text);
        System.out.println("模式: " + pattern);
        System.out.println();
        
        // 测试各种算法
        int result1 = naiveSearch(text, pattern);
        System.out.println("朴素算法结果: " + result1);
        
        int result2 = kmpSearch(text, pattern);
        System.out.println("KMP算法结果: " + result2);
        
        int result3 = boyerMooreSearch(text, pattern);
        System.out.println("Boyer-Moore算法结果: " + result3);
        
        int result4 = rabinKarpSearch(text, pattern);
        System.out.println("Rabin-Karp算法结果: " + result4);
        
        // 测试查找所有匹配
        System.out.println("\n2. 查找所有匹配测试:");
        String text2 = "AABAACAADAABAABA";
        String pattern2 = "AABA";
        
        System.out.println("文本: " + text2);
        System.out.println("模式: " + pattern2);
        
        List<Integer> matches1 = naiveSearchAll(text2, pattern2);
        System.out.println("朴素算法所有匹配: " + matches1);
        
        List<Integer> matches2 = kmpSearchAll(text2, pattern2);
        System.out.println("KMP算法所有匹配: " + matches2);
        
        // 测试LPS数组构建
        System.out.println("\n3. KMP算法LPS数组测试:");
        String[] testPatterns = {"ABABCABAB", "AABAACAABA", "ABCABCABCABC"};
        
        for (String testPattern : testPatterns) {
            int[] lps = buildLPSArray(testPattern);
            System.out.println("模式 '" + testPattern + "' 的LPS数组: " + Arrays.toString(lps));
        }
        
        // 边界情况测试
        System.out.println("\n4. 边界情况测试:");
        String[] testCases = {
            "", // 空模式
            "A", // 单字符模式
            "AAAAAAA", // 重复字符
            "ABCDEFG" // 无匹配
        };
        
        String testText = "AAAAAABCDEFGAAAAAAA";
        System.out.println("测试文本: " + testText);
        
        for (String testCase : testCases) {
            if (testCase.isEmpty()) {
                System.out.println("空模式测试跳过");
                continue;
            }
            
            int naiveResult = naiveSearch(testText, testCase);
            int kmpResult = kmpSearch(testText, testCase);
            
            System.out.println("模式 '" + testCase + "': 朴素=" + naiveResult + ", KMP=" + kmpResult);
        }
        
        // 性能测试
        System.out.println("\n5. 性能测试:");
        String largeText = generateRandomString(100000, "ABCD");
        String searchPattern = "ABCD";
        
        System.out.println("大文本长度: " + largeText.length());
        System.out.println("搜索模式: " + searchPattern);
        
        performanceTest("朴素算法", largeText, searchPattern, StringMatching::naiveSearch);
        performanceTest("KMP算法", largeText, searchPattern, StringMatching::kmpSearch);
        performanceTest("Boyer-Moore算法", largeText, searchPattern, StringMatching::boyerMooreSearch);
        performanceTest("Rabin-Karp算法", largeText, searchPattern, StringMatching::rabinKarpSearch);
        
        // 最坏情况性能测试
        System.out.println("\n6. 最坏情况性能测试:");
        String worstText = "A".repeat(10000) + "B";
        String worstPattern = "A".repeat(100) + "B";
        
        System.out.println("最坏情况文本长度: " + worstText.length());
        System.out.println("最坏情况模式长度: " + worstPattern.length());
        
        performanceTest("朴素算法(最坏)", worstText, worstPattern, StringMatching::naiveSearch);
        performanceTest("KMP算法(最坏)", worstText, worstPattern, StringMatching::kmpSearch);
        
        // 验证算法正确性
        System.out.println("\n7. 算法正确性验证:");
        Random random = new Random();
        int testCount = 100;
        int correctCount = 0;
        
        for (int i = 0; i < testCount; i++) {
            String randomText = generateRandomString(1000, "ABCDE");
            String randomPattern = generateRandomString(10, "ABCDE");
            
            int naiveResult = naiveSearch(randomText, randomPattern);
            int kmpResult = kmpSearch(randomText, randomPattern);
            int bmResult = boyerMooreSearch(randomText, randomPattern);
            int rkResult = rabinKarpSearch(randomText, randomPattern);
            
            if (naiveResult == kmpResult && kmpResult == bmResult && bmResult == rkResult) {
                correctCount++;
            }
        }
        
        System.out.println("随机测试 " + testCount + " 次，正确率: " + (correctCount * 100.0 / testCount) + "%");
    }
}