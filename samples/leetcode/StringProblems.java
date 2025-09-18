package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * LeetCode字符串相关问题解答 包含反转字符串等经典字符串题目
 */
public class StringProblems {

  // ==================== Reverse String（反转字符串）====================

  /**
   * LeetCode 344. 反转字符串 编写一个函数，其作用是将输入的字符串反转过来 输入字符串以字符数组 s 的形式给出
   * <p>
   * 解题思路：双指针 1. 使用左右两个指针 2. 交换左右指针指向的字符 3. 向中间移动指针，直到相遇
   * <p>
   * 时间复杂度：O(n) 空间复杂度：O(1)
   *
   * @param s 字符数组
   */
  public static void reverseString(char[] s) {
    if (s == null || s.length <= 1) {
      return;
    }

    int left = 0;
    int right = s.length - 1;

    while (left < right) {
      // 交换字符
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;

      left++;
      right--;
    }
  }

  /**
   * 反转字符串的递归实现 时间复杂度：O(n) 空间复杂度：O(n) - 递归栈空间
   *
   * @param s 字符数组
   */
  public static void reverseStringRecursive(char[] s) {
    if (s == null || s.length <= 1) {
      return;
    }
    reverseHelper(s, 0, s.length - 1);
  }

  private static void reverseHelper(char[] s, int left, int right) {
    if (left >= right) {
      return;
    }

    // 交换字符
    char temp = s[left];
    s[left] = s[right];
    s[right] = temp;

    // 递归处理内部
    reverseHelper(s, left + 1, right - 1);
  }

  /**
   * 使用位运算交换字符
   *
   * @param s 字符数组
   */
  public static void reverseStringXOR(char[] s) {
    if (s == null || s.length <= 1) {
      return;
    }

    int left = 0;
    int right = s.length - 1;

    while (left < right) {
      // 使用异或运算交换
      s[left] ^= s[right];
      s[right] ^= s[left];
      s[left] ^= s[right];

      left++;
      right--;
    }
  }

  // ==================== 字符串反转的变体问题 ====================

  /**
   * LeetCode 541. 反转字符串 II 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符
   *
   * @param s 输入字符串
   * @param k 反转长度
   * @return 处理后的字符串
   */
  public static String reverseStr(String s, int k) {
    if (s == null || s.length() == 0 || k <= 0) {
      return s;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;

    for (int i = 0; i < n; i += 2 * k) {
      int left = i;
      int right = Math.min(i + k - 1, n - 1);

      // 反转前k个字符
      while (left < right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
        left++;
        right--;
      }
    }

    return new String(chars);
  }

  /**
   * LeetCode 557. 反转字符串中的单词 III 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序
   *
   * @param s 输入字符串
   * @return 处理后的字符串
   */
  public static String reverseWords(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;
    int start = 0;

    for (int i = 0; i <= n; i++) {
      if (i == n || chars[i] == ' ') {
        // 反转单词
        reverseRange(chars, start, i - 1);
        start = i + 1;
      }
    }

    return new String(chars);
  }

  /**
   * 反转字符数组中指定范围的字符
   *
   * @param chars 字符数组
   * @param start 起始位置
   * @param end   结束位置
   */
  private static void reverseRange(char[] chars, int start, int end) {
    while (start < end) {
      char temp = chars[start];
      chars[start] = chars[end];
      chars[end] = temp;
      start++;
      end--;
    }
  }

  /**
   * LeetCode 151. 反转字符串中的单词 给你一个字符串 s ，请你反转字符串中单词的顺序
   *
   * @param s 输入字符串
   * @return 处理后的字符串
   */
  public static String reverseWordsOrder(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }

    // 去除多余空格并分割单词
    String[] words = s.trim().split("\\s+");

    // 反转单词顺序
    StringBuilder result = new StringBuilder();
    for (int i = words.length - 1; i >= 0; i--) {
      result.append(words[i]);
      if (i > 0) {
        result.append(" ");
      }
    }

    return result.toString();
  }

  /**
   * 反转字符串中的单词（原地算法）
   *
   * @param s 输入字符串
   * @return 处理后的字符串
   */
  public static String reverseWordsOrderInPlace(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;

    // 1. 反转整个字符串
    reverseRange(chars, 0, n - 1);

    // 2. 反转每个单词
    int start = 0;
    for (int i = 0; i <= n; i++) {
      if (i == n || chars[i] == ' ') {
        reverseRange(chars, start, i - 1);
        start = i + 1;
      }
    }

    // 3. 处理多余空格
    return cleanSpaces(chars);
  }

  /**
   * 清理多余的空格
   *
   * @param chars 字符数组
   * @return 清理后的字符串
   */
  private static String cleanSpaces(char[] chars) {
    int n = chars.length;
    int i = 0, j = 0;

    while (j < n) {
      // 跳过空格
      while (j < n && chars[j] == ' ') {
        j++;
      }

      // 复制单词
      while (j < n && chars[j] != ' ') {
        chars[i++] = chars[j++];
      }

      // 跳过空格
      while (j < n && chars[j] == ' ') {
        j++;
      }

      // 添加单个空格
      if (j < n) {
        chars[i++] = ' ';
      }
    }

    return new String(chars, 0, i);
  }

  // ==================== 其他字符串问题 ====================

  /**
   * LeetCode 125. 验证回文串 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样，则可以认为该短语是一个回文串
   *
   * @param s 输入字符串
   * @return 是否为回文串
   */
  public static boolean isPalindrome(String s) {
    if (s == null || s.length() == 0) {
      return true;
    }

    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
      // 跳过非字母数字字符
      while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
        left++;
      }
      while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
        right--;
      }

      // 比较字符（忽略大小写）
      if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
        return false;
      }

      left++;
      right--;
    }

    return true;
  }

  /**
   * LeetCode 680. 验证回文串 II 给你一个字符串 s，最多可以从中删除一个字符。请你判断 s 能否成为回文字符串
   *
   * @param s 输入字符串
   * @return 是否能成为回文串
   */
  public static boolean validPalindrome(String s) {
    if (s == null || s.length() <= 1) {
      return true;
    }

    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        // 尝试删除左边或右边的字符
        return isPalindromeRange(s, left + 1, right) ||
            isPalindromeRange(s, left, right - 1);
      }
      left++;
      right--;
    }

    return true;
  }

  /**
   * 检查字符串指定范围是否为回文
   *
   * @param s     字符串
   * @param left  左边界
   * @param right 右边界
   * @return 是否为回文
   */
  private static boolean isPalindromeRange(String s, int left, int right) {
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  /**
   * LeetCode 5. 最长回文子串 给你一个字符串 s，找到 s 中最长的回文子串
   *
   * @param s 输入字符串
   * @return 最长回文子串
   */
  public static String longestPalindrome(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }

    int start = 0;
    int maxLen = 1;

    for (int i = 0; i < s.length(); i++) {
      // 奇数长度回文
      int len1 = expandAroundCenter(s, i, i);
      // 偶数长度回文
      int len2 = expandAroundCenter(s, i, i + 1);

      int len = Math.max(len1, len2);
      if (len > maxLen) {
        maxLen = len;
        start = i - (len - 1) / 2;
      }
    }

    return s.substring(start, start + maxLen);
  }

  /**
   * 从中心向两边扩展寻找回文
   *
   * @param s     字符串
   * @param left  左中心
   * @param right 右中心
   * @return 回文长度
   */
  private static int expandAroundCenter(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }
    return right - left - 1;
  }

  /**
   * LeetCode 242. 有效的字母异位词 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词
   *
   * @param s 字符串s
   * @param t 字符串t
   * @return 是否为字母异位词
   */
  public static boolean isAnagram(String s, String t) {
    if (s == null || t == null || s.length() != t.length()) {
      return false;
    }

    int[] count = new int[26];

    for (int i = 0; i < s.length(); i++) {
      count[s.charAt(i) - 'a']++;
      count[t.charAt(i) - 'a']--;
    }

    for (int c : count) {
      if (c != 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * LeetCode 49. 字母异位词分组 给你一个字符串数组，请你将字母异位词组合在一起
   *
   * @param strs 字符串数组
   * @return 分组结果
   */
  public static List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null || strs.length == 0) {
      return new ArrayList<>();
    }

    Map<String, List<String>> map = new HashMap<>();

    for (String str : strs) {
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      String key = new String(chars);

      map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
    }

    return new ArrayList<>(map.values());
  }

  // ==================== 辅助方法 ====================

  /**
   * 生成随机字符串
   *
   * @param length  长度
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
   * 验证字符串反转的正确性
   *
   * @param original 原始字符串
   * @param reversed 反转后的字符串
   * @return 是否正确
   */
  public static boolean verifyReverse(String original, String reversed) {
    if (original == null && reversed == null) {
      return true;
    }
    if (original == null || reversed == null) {
      return false;
    }
    if (original.length() != reversed.length()) {
      return false;
    }

    int n = original.length();
    for (int i = 0; i < n; i++) {
      if (original.charAt(i) != reversed.charAt(n - 1 - i)) {
        return false;
      }
    }

    return true;
  }

  /**
   * 测试字符串算法性能
   *
   * @param testName 测试名称
   * @param function 测试函数
   * @param input    输入参数
   * @return 执行时间（纳秒）
   */
  public static long testStringPerformance(String testName, Runnable function, String input) {
    long startTime = System.nanoTime();
    function.run();
    long endTime = System.nanoTime();

    System.out.printf("%s (长度=%d): %.2fμs%n",
        testName, input.length(), (endTime - startTime) / 1000.0);

    return endTime - startTime;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== LeetCode 字符串问题测试 ===\n");

    // 测试1：反转字符串
    System.out.println("1. 反转字符串测试:");

    String[] testStrings = {
        "hello",
        "world",
        "a",
        "",
        "abcdef",
        "racecar",
        "A man a plan a canal Panama"
    };

    for (String str : testStrings) {
      char[] chars1 = str.toCharArray();
      char[] chars2 = str.toCharArray();
      char[] chars3 = str.toCharArray();

      System.out.printf("原始: \"%s\"%n", str);

      long time1 = testStringPerformance("双指针", () -> reverseString(chars1), str);
      long time2 = testStringPerformance("递归", () -> reverseStringRecursive(chars2), str);
      long time3 = testStringPerformance("异或", () -> reverseStringXOR(chars3), str);

      String result1 = new String(chars1);
      String result2 = new String(chars2);
      String result3 = new String(chars3);

      System.out.printf("双指针: \"%s\"%n", result1);
      System.out.printf("递归: \"%s\"%n", result2);
      System.out.printf("异或: \"%s\"%n", result3);

      boolean consistent = result1.equals(result2) && result2.equals(result3);
      boolean correct = verifyReverse(str, result1);

      System.out.printf("一致性: %s, 正确性: %s%n", consistent, correct);
      System.out.println();
    }

    // 测试2：反转字符串变体
    System.out.println("2. 反转字符串变体测试:");

    // 反转字符串 II
    String[] reverseStrTests = {"abcdefg", "abcd", "a", "ab"};
    int[] kValues = {2, 2, 2, 1};

    System.out.println("反转字符串 II:");
    for (int i = 0; i < reverseStrTests.length; i++) {
      String original = reverseStrTests[i];
      int k = kValues[i];
      String result = reverseStr(original, k);

      System.out.printf("原始: \"%s\", k=%d, 结果: \"%s\"%n", original, k, result);
    }

    // 反转字符串中的单词 III
    String[] wordTests = {
        "Let's take LeetCode contest",
        "Mr Ding",
        "a",
        "hello world"
    };

    System.out.println("\n反转字符串中的单词 III:");
    for (String test : wordTests) {
      String result = reverseWords(test);
      System.out.printf("原始: \"%s\"%n", test);
      System.out.printf("结果: \"%s\"%n", result);
      System.out.println();
    }

    // 反转字符串中的单词
    String[] orderTests = {
        "the sky is blue",
        "  hello world  ",
        "a good   example",
        "  Bob    Loves  Alice   ",
        "Alice does not even like bob"
    };

    System.out.println("反转字符串中的单词:");
    for (String test : orderTests) {
      String result1 = reverseWordsOrder(test);
      String result2 = reverseWordsOrderInPlace(test);

      System.out.printf("原始: \"%s\"%n", test);
      System.out.printf("方法1: \"%s\"%n", result1);
      System.out.printf("方法2: \"%s\"%n", result2);
      System.out.printf("一致性: %s%n", result1.equals(result2));
      System.out.println();
    }

    // 测试3：回文串
    System.out.println("3. 回文串测试:");

    String[] palindromeTests = {
        "A man, a plan, a canal: Panama",
        "race a car",
        " ",
        "a",
        "",
        "Madam",
        "12321",
        "hello"
    };

    System.out.println("验证回文串:");
    for (String test : palindromeTests) {
      boolean result = isPalindrome(test);
      System.out.printf("\"%s\" -> %s%n", test, result);
    }

    // 验证回文串 II
    String[] palindrome2Tests = {
        "aba",
        "abca",
        "abc",
        "raceacar",
        "deeee"
    };

    System.out.println("\n验证回文串 II:");
    for (String test : palindrome2Tests) {
      boolean result = validPalindrome(test);
      System.out.printf("\"%s\" -> %s%n", test, result);
    }

    // 最长回文子串
    String[] longestTests = {
        "babad",
        "cbbd",
        "a",
        "ac",
        "racecar",
        "noon"
    };

    System.out.println("\n最长回文子串:");
    for (String test : longestTests) {
      long startTime = System.nanoTime();
      String result = longestPalindrome(test);
      long time = System.nanoTime() - startTime;

      System.out.printf("\"%s\" -> \"%s\" (%.2fμs)%n", test, result, time / 1000.0);
    }

    // 测试4：字母异位词
    System.out.println("\n4. 字母异位词测试:");

    String[][] anagramPairs = {
        {"anagram", "nagaram"},
        {"rat", "car"},
        {"listen", "silent"},
        {"evil", "vile"},
        {"a", "ab"}
    };

    System.out.println("有效的字母异位词:");
    for (String[] pair : anagramPairs) {
      boolean result = isAnagram(pair[0], pair[1]);
      System.out.printf("\"%s\" 和 \"%s\" -> %s%n", pair[0], pair[1], result);
    }

    // 字母异位词分组
    String[] groupTests = {"eat", "tea", "tan", "ate", "nat", "bat"};

    System.out.println("\n字母异位词分组:");
    System.out.println("输入: " + Arrays.toString(groupTests));

    List<List<String>> groups = groupAnagrams(groupTests);
    System.out.println("分组结果:");
    for (int i = 0; i < groups.size(); i++) {
      System.out.printf("组%d: %s%n", i + 1, groups.get(i));
    }

    // 测试5：性能测试
    System.out.println("\n5. 性能测试:");

    // 大字符串反转性能
    System.out.println("大字符串反转性能:");
    int[] lengths = {1000, 10000, 100000, 1000000};

    for (int length : lengths) {
      String largeString = generateRandomString(length, "abcdefghijklmnopqrstuvwxyz");

      char[] chars1 = largeString.toCharArray();
      char[] chars2 = largeString.toCharArray();
      char[] chars3 = largeString.toCharArray();

      System.out.printf("长度=%d:%n", length);

      long time1 = testStringPerformance("双指针", () -> reverseString(chars1), largeString);
      long time2 = testStringPerformance("递归", () -> reverseStringRecursive(chars2), largeString);
      long time3 = testStringPerformance("异或", () -> reverseStringXOR(chars3), largeString);

      System.out.printf("性能比较: 双指针=1.0x, 递归=%.2fx, 异或=%.2fx%n",
          (double) time2 / time1, (double) time3 / time1);
      System.out.println();
    }

    // 回文检测性能
    System.out.println("回文检测性能:");
    for (int length : new int[]{1000, 10000, 100000}) {
      // 生成回文字符串
      String half = generateRandomString(length / 2, "abcdefghijklmnopqrstuvwxyz");
      String palindrome = half + new StringBuilder(half).reverse().toString();

      long startTime = System.nanoTime();
      boolean result = isPalindrome(palindrome);
      long time = System.nanoTime() - startTime;

      System.out.printf("长度=%d: %s (%.2fμs)%n", palindrome.length(), result, time / 1000.0);
    }

    // 测试6：边界情况
    System.out.println("\n6. 边界情况测试:");

    System.out.println("反转字符串边界情况:");
    char[] empty = {};
    char[] single = {'a'};
    char[] two = {'a', 'b'};

    reverseString(empty);
    reverseString(single);
    reverseString(two);

    System.out.println("空数组: " + Arrays.toString(empty));
    System.out.println("单字符: " + Arrays.toString(single));
    System.out.println("双字符: " + Arrays.toString(two));

    System.out.println("\n回文串边界情况:");
    System.out.println("空字符串: " + isPalindrome(""));
    System.out.println("单字符: " + isPalindrome("a"));
    System.out.println("null: " + isPalindrome(null));

    System.out.println("\n字母异位词边界情况:");
    System.out.println("空字符串: " + isAnagram("", ""));
    System.out.println("不同长度: " + isAnagram("a", "ab"));
    System.out.println("null: " + isAnagram(null, "a"));

    // 测试7：正确性验证
    System.out.println("\n7. 正确性验证:");

    // 大量随机字符串反转测试
    Random random = new Random(42);
    int correctCount = 0;
    int totalTests = 1000;

    for (int i = 0; i < totalTests; i++) {
      int length = random.nextInt(100) + 1;
      String original = generateRandomString(length, "abcdefghijklmnopqrstuvwxyz");

      char[] chars = original.toCharArray();
      reverseString(chars);
      String reversed = new String(chars);

      if (verifyReverse(original, reversed)) {
        correctCount++;
      }
    }

    System.out.printf("字符串反转: %d/%d 正确, 正确率: %.2f%%%n",
        correctCount, totalTests, 100.0 * correctCount / totalTests);

    // 回文检测正确性
    int palindromeCorrect = 0;
    int palindromeTotal = 1000;

    for (int i = 0; i < palindromeTotal; i++) {
      int length = random.nextInt(50) + 1;
      String str = generateRandomString(length, "abcdefghijklmnopqrstuvwxyz");

      // 创建回文
      String palindrome = str + new StringBuilder(str).reverse().toString();

      boolean result = isPalindrome(palindrome);
      if (result) {
        palindromeCorrect++;
      }
    }

    System.out.printf("回文检测: %d/%d 正确, 正确率: %.2f%%%n",
        palindromeCorrect, palindromeTotal,
        100.0 * palindromeCorrect / palindromeTotal);

    // 字母异位词检测正确性
    int anagramCorrect = 0;
    int anagramTotal = 1000;

    for (int i = 0; i < anagramTotal; i++) {
      int length = random.nextInt(20) + 1;
      String str1 = generateRandomString(length, "abcdefghijklmnopqrstuvwxyz");

      // 创建异位词
      char[] chars = str1.toCharArray();
      for (int j = 0; j < length; j++) {
        int swapIndex = random.nextInt(length);
        char temp = chars[j];
        chars[j] = chars[swapIndex];
        chars[swapIndex] = temp;
      }
      String str2 = new String(chars);

      boolean result = isAnagram(str1, str2);
      if (result) {
        anagramCorrect++;
      }
    }

    System.out.printf("字母异位词检测: %d/%d 正确, 正确率: %.2f%%%n",
        anagramCorrect, anagramTotal,
        100.0 * anagramCorrect / anagramTotal);
  }
}