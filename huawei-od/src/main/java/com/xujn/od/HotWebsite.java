package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：热点网站统计
 *
 * 【题目描述】
 * 给定N条用户访问记录，每条包含用户ID和访问的网站URL。
 * 找出被最多不同用户访问的网站（即独立用户数最多的网站）。
 * 如果有多个网站并列，按URL字典序排列输出。
 *
 * 【解题思路：HashMap<URL, Set<UserID>> 统计去重】
 */
public class HotWebsite {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        // URL -> 不同用户集合
        Map<String, Set<String>> siteUsers = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split(" ");
            String userId = parts[0];
            String url = parts[1];
            siteUsers.computeIfAbsent(url, k -> new HashSet<>()).add(userId);
        }

        // 找出最大独立用户数
        int maxUsers = 0;
        for (Set<String> users : siteUsers.values()) {
            maxUsers = Math.max(maxUsers, users.size());
        }

        // 收集并列的网站
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : siteUsers.entrySet()) {
            if (entry.getValue().size() == maxUsers) {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result); // 字典序排列
        for (String url : result) {
            System.out.println(url);
        }
    }
}
