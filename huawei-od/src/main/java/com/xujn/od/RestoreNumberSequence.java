package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：恢复数字序列
 *
 * 【题目描述】
 * 一个连续递增整数序列（如 123456789101112...）去掉了空格拼成字符串。
 * 给定这个字符串和起始数字，还原完整的数字序列。
 *
 * 【解题思路：模拟 —— 按数字位数逐个匹配】
 * 从起始数字开始，将其转为字符串与输入字符串逐字符对比匹配。
 */
public class RestoreNumberSequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        int start = Integer.parseInt(sc.nextLine().trim());

        List<Integer> result = new ArrayList<>();
        int pos = 0;
        int num = start;

        while (pos < s.length()) {
            String numStr = String.valueOf(num);
            // 验证是否匹配
            if (pos + numStr.length() <= s.length()
                    && s.substring(pos, pos + numStr.length()).equals(numStr)) {
                result.add(num);
                pos += numStr.length();
                num++;
            } else {
                // 不匹配，说明序列有误或结束
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(result.get(i));
        }
        System.out.println(sb.toString());
    }
}
