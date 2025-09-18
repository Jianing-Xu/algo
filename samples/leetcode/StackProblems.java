package leetcode;

import java.util.*;

/**
 * LeetCode栈相关问题解答
 * 包含有效括号、最长有效括号、逆波兰表达式求值等经典题目
 */
public class StackProblems {
    
    // ==================== Valid Parentheses（有效的括号）====================
    
    /**
     * LeetCode 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s，判断字符串是否有效
     * 
     * 有效字符串需满足：
     * 1. 左括号必须用相同类型的右括号闭合
     * 2. 左括号必须以正确的顺序闭合
     * 
     * 解题思路：使用栈
     * 1. 遇到左括号就入栈
     * 2. 遇到右括号就检查栈顶是否为对应的左括号
     * 3. 最后检查栈是否为空
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 
     * @param s 输入字符串
     * @return 是否为有效括号
     */
    public static boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false; // 奇数长度不可能有效
        }
        
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put('}', '{');
        mapping.put(']', '[');
        
        for (char c : s.toCharArray()) {
            if (mapping.containsKey(c)) {
                // 右括号
                if (stack.isEmpty() || stack.pop() != mapping.get(c)) {
                    return false;
                }
            } else {
                // 左括号
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * 有效括号的优化版本（不使用HashMap）
     * 
     * @param s 输入字符串
     * @return 是否为有效括号
     */
    public static boolean isValidOptimized(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                default:
                    return false; // 无效字符
            }
        }
        
        return stack.isEmpty();
    }
    
    // ==================== Longest Valid Parentheses（最长有效括号）====================
    
    /**
     * LeetCode 32. 最长有效的括号
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度
     * 
     * 解题思路：动态规划
     * dp[i] 表示以下标 i 字符结尾的最长有效括号的长度
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 
     * @param s 输入字符串
     * @return 最长有效括号长度
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        
        int n = s.length();
        int[] dp = new int[n];
        int maxLen = 0;
        
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // 情况1: ...()
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (dp[i - 1] > 0) {
                    // 情况2: ...))
                    int matchIndex = i - dp[i - 1] - 1;
                    if (matchIndex >= 0 && s.charAt(matchIndex) == '(') {
                        dp[i] = dp[i - 1] + 2 + (matchIndex > 0 ? dp[matchIndex - 1] : 0);
                    }
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        
        return maxLen;
    }
    
    /**
     * 最长有效括号的栈解法
     * 
     * 解题思路：
     * 1. 使用栈存储索引
     * 2. 栈底始终保持最后一个没有被匹配的右括号的索引
     * 3. 遇到左括号入栈，遇到右括号出栈并计算长度
     * 
     * @param s 输入字符串
     * @return 最长有效括号长度
     */
    public static int longestValidParenthesesStack(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 栈底放入-1作为基准
        int maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 当前右括号无法匹配
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        
        return maxLen;
    }
    
    /**
     * 最长有效括号的双向遍历解法（空间复杂度O(1)）
     * 
     * @param s 输入字符串
     * @return 最长有效括号长度
     */
    public static int longestValidParenthesesTwoPass(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        
        int left = 0, right = 0, maxLen = 0;
        
        // 从左到右遍历
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        
        left = right = 0;
        
        // 从右到左遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        
        return maxLen;
    }
    
    // ==================== Evaluate Reverse Polish Notation（逆波兰表达式求值）====================
    
    /**
     * LeetCode 150. 逆波兰表达式求值
     * 根据逆波兰表示法，求表达式的值
     * 
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式
     * 
     * 解题思路：使用栈
     * 1. 遇到数字就入栈
     * 2. 遇到操作符就弹出两个数字进行运算，结果入栈
     * 3. 最后栈中只剩一个数字，就是结果
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 
     * @param tokens 逆波兰表达式数组
     * @return 计算结果
     */
    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        
        for (String token : tokens) {
            if (operators.contains(token)) {
                // 操作符：弹出两个操作数
                int b = stack.pop();
                int a = stack.pop();
                int result = 0;
                
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = a / b; // 整数除法，向零截断
                        break;
                }
                
                stack.push(result);
            } else {
                // 操作数：直接入栈
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
    
    /**
     * 逆波兰表达式求值的优化版本（不使用HashSet）
     * 
     * @param tokens 逆波兰表达式数组
     * @return 计算结果
     */
    public static int evalRPNOptimized(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        
        return stack.pop();
    }
    
    // ==================== 辅助方法 ====================
    
    /**
     * 生成随机括号字符串（用于测试）
     * 
     * @param length 字符串长度
     * @param validRatio 有效括号的比例
     * @return 随机括号字符串
     */
    public static String generateRandomParentheses(int length, double validRatio) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        
        int validCount = (int) (length * validRatio);
        int invalidCount = length - validCount;
        
        // 生成有效括号对
        for (int i = 0; i < validCount / 2; i++) {
            sb.append('(');
        }
        for (int i = 0; i < validCount / 2; i++) {
            sb.append(')');
        }
        
        // 生成无效括号
        for (int i = 0; i < invalidCount; i++) {
            sb.append(random.nextBoolean() ? '(' : ')');
        }
        
        // 打乱字符串
        char[] chars = sb.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        
        return new String(chars);
    }
    
    /**
     * 生成随机逆波兰表达式（用于测试）
     * 
     * @param length 表达式长度
     * @return 逆波兰表达式数组
     */
    public static String[] generateRandomRPN(int length) {
        Random random = new Random();
        List<String> tokens = new ArrayList<>();
        String[] operators = {"+", "-", "*", "/"};
        
        // 确保至少有两个操作数
        tokens.add(String.valueOf(random.nextInt(100) + 1));
        tokens.add(String.valueOf(random.nextInt(100) + 1));
        
        for (int i = 2; i < length; i++) {
            if (tokens.size() >= 2 && random.nextBoolean()) {
                // 添加操作符
                tokens.add(operators[random.nextInt(operators.length)]);
            } else {
                // 添加操作数
                tokens.add(String.valueOf(random.nextInt(100) + 1));
            }
        }
        
        // 确保表达式有效（操作数比操作符多1）
        long operandCount = tokens.stream().filter(t -> !Arrays.asList(operators).contains(t)).count();
        long operatorCount = tokens.size() - operandCount;
        
        while (operatorCount >= operandCount) {
            tokens.add(String.valueOf(random.nextInt(100) + 1));
            operandCount++;
        }
        
        return tokens.toArray(new String[0]);
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== LeetCode 栈问题测试 ===\n");
        
        // 测试1：有效括号
        System.out.println("1. 有效括号测试:");
        
        String[] testCases = {
            "()",
            "()[]{}",
            "(]",
            "([)]",
            "{[]}",
            "",
            "(((",
            ")))",
            "({[]})"
        };
        
        for (String testCase : testCases) {
            long startTime = System.nanoTime();
            boolean result1 = isValid(testCase);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            boolean result2 = isValidOptimized(testCase);
            long time2 = System.nanoTime() - startTime;
            
            System.out.printf("输入: \"%s\" -> HashMap版本: %s (%.2fμs), 优化版本: %s (%.2fμs), 一致性: %s%n",
                            testCase, result1, time1 / 1000.0, result2, time2 / 1000.0, result1 == result2);
        }
        
        // 测试2：最长有效括号
        System.out.println("\n2. 最长有效括号测试:");
        
        String[] parenthesesCases = {
            "(()",
            ")()())",
            "",
            "()(()",
            "()(())",
            "((()))",
            ")()())",
            "(()())"
        };
        
        for (String testCase : parenthesesCases) {
            long startTime = System.nanoTime();
            int result1 = longestValidParentheses(testCase);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int result2 = longestValidParenthesesStack(testCase);
            long time2 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int result3 = longestValidParenthesesTwoPass(testCase);
            long time3 = System.nanoTime() - startTime;
            
            System.out.printf("输入: \"%s\" -> DP: %d (%.2fμs), 栈: %d (%.2fμs), 双向: %d (%.2fμs)%n",
                            testCase, result1, time1 / 1000.0, result2, time2 / 1000.0, result3, time3 / 1000.0);
            
            if (result1 != result2 || result2 != result3) {
                System.out.println("  ⚠️ 结果不一致！");
            }
        }
        
        // 测试3：逆波兰表达式求值
        System.out.println("\n3. 逆波兰表达式求值测试:");
        
        String[][] rpnCases = {
            {"2", "1", "+", "3", "*"},           // ((2 + 1) * 3) = 9
            {"4", "13", "5", "/", "+"},          // (4 + (13 / 5)) = 6
            {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, // 复杂表达式
            {"3", "4", "+"},                     // 3 + 4 = 7
            {"3", "4", "*", "2", "/"},          // (3 * 4) / 2 = 6
        };
        
        for (String[] rpnCase : rpnCases) {
            System.out.println("输入: " + Arrays.toString(rpnCase));
            
            long startTime = System.nanoTime();
            int result1 = evalRPN(rpnCase);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            int result2 = evalRPNOptimized(rpnCase.clone());
            long time2 = System.nanoTime() - startTime;
            
            System.out.printf("HashMap版本: %d (%.2fμs), 优化版本: %d (%.2fμs), 一致性: %s%n",
                            result1, time1 / 1000.0, result2, time2 / 1000.0, result1 == result2);
        }
        
        // 测试4：性能测试
        System.out.println("\n4. 性能测试:");
        
        // 有效括号性能测试
        System.out.println("有效括号性能测试:");
        for (int length : new int[]{1000, 5000, 10000}) {
            String testString = generateRandomParentheses(length, 0.8);
            
            long startTime = System.currentTimeMillis();
            boolean result1 = isValid(testString);
            long time1 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            boolean result2 = isValidOptimized(testString);
            long time2 = System.currentTimeMillis() - startTime;
            
            System.out.printf("长度=%d: HashMap=%dms, 优化=%dms, 加速比=%.2fx%n",
                            length, time1, time2, time1 == 0 ? 1.0 : (double) time1 / time2);
        }
        
        // 最长有效括号性能测试
        System.out.println("\n最长有效括号性能测试:");
        for (int length : new int[]{1000, 3000, 5000}) {
            String testString = generateRandomParentheses(length, 0.6);
            
            long startTime = System.currentTimeMillis();
            int result1 = longestValidParentheses(testString);
            long time1 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            int result2 = longestValidParenthesesStack(testString);
            long time2 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            int result3 = longestValidParenthesesTwoPass(testString);
            long time3 = System.currentTimeMillis() - startTime;
            
            System.out.printf("长度=%d: DP=%dms, 栈=%dms, 双向=%dms%n",
                            length, time1, time2, time3);
        }
        
        // 逆波兰表达式性能测试
        System.out.println("\n逆波兰表达式性能测试:");
        for (int length : new int[]{100, 500, 1000}) {
            String[] testRPN = generateRandomRPN(length);
            
            long startTime = System.currentTimeMillis();
            int result1 = evalRPN(testRPN);
            long time1 = System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            int result2 = evalRPNOptimized(testRPN.clone());
            long time2 = System.currentTimeMillis() - startTime;
            
            System.out.printf("长度=%d: HashMap=%dms, 优化=%dms%n",
                            length, time1, time2);
        }
        
        // 测试5：边界情况
        System.out.println("\n5. 边界情况测试:");
        
        // 有效括号边界情况
        System.out.println("有效括号边界情况:");
        System.out.println("null: " + isValid(null));
        System.out.println("空字符串: " + isValid(""));
        System.out.println("单个字符: " + isValid("("));
        System.out.println("奇数长度: " + isValid("(()"));
        
        // 最长有效括号边界情况
        System.out.println("\n最长有效括号边界情况:");
        System.out.println("null: " + longestValidParentheses(null));
        System.out.println("空字符串: " + longestValidParentheses(""));
        System.out.println("单个字符: " + longestValidParentheses("("));
        System.out.println("全是左括号: " + longestValidParentheses("(((("));
        System.out.println("全是右括号: " + longestValidParentheses("))))"));
        
        // 逆波兰表达式边界情况
        System.out.println("\n逆波兰表达式边界情况:");
        System.out.println("单个数字: " + evalRPN(new String[]{"42"}));
        System.out.println("简单加法: " + evalRPN(new String[]{"1", "2", "+"}));
        System.out.println("除法截断: " + evalRPN(new String[]{"7", "3", "/"}));
        System.out.println("负数除法: " + evalRPN(new String[]{"-7", "3", "/"}));
        
        // 测试6：复杂情况
        System.out.println("\n6. 复杂情况测试:");
        
        // 嵌套括号
        String nestedParentheses = "(((())))";
        System.out.println("嵌套括号 \"" + nestedParentheses + "\": " + 
                         isValid(nestedParentheses) + ", 最长有效: " + 
                         longestValidParentheses(nestedParentheses));
        
        // 混合括号
        String mixedParentheses = "({[]})";
        System.out.println("混合括号 \"" + mixedParentheses + "\": " + isValid(mixedParentheses));
        
        // 复杂逆波兰表达式
        String[] complexRPN = {"15", "7", "1", "1", "+", "/", "/", "3", "/", "2", "1", "1", "+", "+", "-"};
        System.out.println("复杂RPN " + Arrays.toString(complexRPN) + ": " + evalRPN(complexRPN));
    }
}