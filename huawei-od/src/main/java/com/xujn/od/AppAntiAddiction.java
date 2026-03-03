package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：手机APP防沉迷系统
 *
 * 【题目描述】
 * 智能手机的防沉迷系统，限制每个APP每天的使用时长。
 * 给定N个APP的注册信息（APP名称、使用时间限制 或 优先级），以及时间段日志。
 * 当用户打开APP时开始计时，如果达到时间限制则自动关闭。
 * 输入包含注册列表和使用日志，输出每个APP的实际使用时长。
 *
 * 【解题思路：模拟 + HashMap】
 * 1. 使用HashMap存储每个APP的最大允许时间
 * 2. 解析日志，计算每个APP的使用时长（取min(实际时长, 最大允许时长)）
 * 3. 按要求格式输出
 */
public class AppAntiAddiction {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // APP数量
        sc.nextLine();

        Map<String, Integer> limitMap = new HashMap<>(); // APP -> 限制时长(分钟)
        Map<String, Integer> usedMap = new LinkedHashMap<>(); // APP -> 已使用时长

        // 读取注册信息
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            String appName = parts[0];
            int limit = Integer.parseInt(parts[1]);
            limitMap.put(appName, limit);
            usedMap.put(appName, 0);
        }

        int m = Integer.parseInt(sc.nextLine().trim()); // 日志条数

        // 读取使用日志
        for (int i = 0; i < m; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            String appName = parts[0];
            int duration = Integer.parseInt(parts[1]); // 本次使用时长

            if (!limitMap.containsKey(appName)) continue;

            int limit = limitMap.get(appName);
            int alreadyUsed = usedMap.getOrDefault(appName, 0);
            int remaining = limit - alreadyUsed;

            if (remaining <= 0) {
                // 已经达到限制，不再累加
                continue;
            }

            // 实际使用 = min(本次请求时长, 剩余可用时长)
            int actualUse = Math.min(duration, remaining);
            usedMap.put(appName, alreadyUsed + actualUse);
        }

        // 输出
        for (Map.Entry<String, Integer> entry : usedMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
