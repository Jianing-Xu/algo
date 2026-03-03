package com.xujn.od;

import java.util.*;
import java.util.regex.*;

/**
 * 华为OD机考题：日志解析
 *
 * 【题目描述】
 * 给定系统日志，每行格式为 "[时间] [级别] [模块] 消息内容"。
 * 统计各级别(ERROR/WARN/INFO)的日志条数，以及ERROR最多的模块名。
 *
 * 【解题思路：正则匹配 + HashMap统计】
 */
public class LogParser {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        Map<String, Integer> levelCount = new LinkedHashMap<>();
        levelCount.put("INFO", 0);
        levelCount.put("WARN", 0);
        levelCount.put("ERROR", 0);

        Map<String, Integer> errorModuleCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            // 解析日志格式
            String[] parts = line.split("\\s+", 4);
            if (parts.length < 3) continue;

            String level = parts[1].toUpperCase();
            String module = parts[2];

            levelCount.put(level, levelCount.getOrDefault(level, 0) + 1);

            if (level.equals("ERROR")) {
                errorModuleCount.put(module, errorModuleCount.getOrDefault(module, 0) + 1);
            }
        }

        // 输出各级别统计
        for (Map.Entry<String, Integer> entry : levelCount.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // 输出ERROR最多的模块
        if (!errorModuleCount.isEmpty()) {
            String maxModule = Collections.max(errorModuleCount.entrySet(),
                    Map.Entry.comparingByValue()).getKey();
            System.out.println("Most errors: " + maxModule);
        }
    }
}
