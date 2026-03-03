package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：货币单位换算
 *
 * 【题目描述】
 * 记账本中记录了若干条金额记录，金额可能用不同币种表示。
 * 已知汇率关系：1 � = 100 分, 1 英镑 = 100 便士, 1 法郎 = 100 生丁
 * 所有币种统一换算成"分"并求和。
 * 输入格式示例："100元50分" 或 "3英镑20便士"等
 *
 * 【解题思路：字符串解析 + 模拟】
 * 1. 识别数字和单位
 * 2. 根据单位转换成统一的最小单位（分）
 * 3. 求和
 */
public class CurrencyConversion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        long total = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            total += parseLine(line);
        }

        System.out.println(total);
    }

    private static long parseLine(String line) {
        long result = 0;
        // 提取所有 "数字+单位" 对
        StringBuilder numBuf = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) {
                numBuf.append(c);
            } else {
                if (numBuf.length() > 0) {
                    long value = Long.parseLong(numBuf.toString());
                    // 向后提取单位
                    StringBuilder unitBuf = new StringBuilder();
                    while (i < line.length() && !Character.isDigit(line.charAt(i))) {
                        unitBuf.append(line.charAt(i));
                        i++;
                    }
                    i--; // 回退一位让外层for正常递增
                    String unit = unitBuf.toString().trim();
                    result += convertToFen(value, unit);
                    numBuf = new StringBuilder();
                }
            }
        }
        return result;
    }

    private static long convertToFen(long value, String unit) {
        switch (unit) {
            case "元":
            case "英镑":
            case "法郎":
                return value * 100;
            case "分":
            case "便士":
            case "生丁":
                return value;
            default:
                return value; // 默认当最小单位处理
        }
    }
}
