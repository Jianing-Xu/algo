package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：IMSI匹配（国际移动用户识别码匹配）
 *
 * 【题目描述】
 * IMSI是15位数字，前3位是MCC(国家码)，接下来2-3位是MNC(运营商码)。
 * 给定一组规则（前缀->运营商名称）和若干IMSI号码，
 * 根据最长前缀匹配原则输出每个IMSI对应的运营商。
 *
 * 【解题思路：Trie 字典树 / 暴力前缀匹配】
 */
public class ImsiMatch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim()); // 规则数

        TreeMap<String, String> rules = new TreeMap<>((a, b) -> {
            if (a.length() != b.length()) return b.length() - a.length(); // 长的排前面
            return a.compareTo(b);
        });

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");
            rules.put(parts[0], parts[1]);
        }

        int m = Integer.parseInt(sc.nextLine().trim()); // 查询数
        for (int i = 0; i < m; i++) {
            String imsi = sc.nextLine().trim();
            String matched = "UNKNOWN";
            // 最长前缀匹配
            for (Map.Entry<String, String> rule : rules.entrySet()) {
                if (imsi.startsWith(rule.getKey())) {
                    matched = rule.getValue();
                    break; // TreeMap已经按前缀长度降序排列
                }
            }
            System.out.println(imsi + " " + matched);
        }
    }
}
