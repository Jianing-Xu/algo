package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：关键数据查询（Top-K 频率查询）
 *
 * 【题目描述】
 * 给定N个数据记录，每条包含一个关键字key和值value。
 * 查询出现频率最高的前K个关键字。频率相同按字典序排列。
 *
 * 【解题思路：HashMap统计频率 + 排序取TopK】
 */
public class KeyDataQuery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();

        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String key = sc.nextLine().trim();
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((a, b) -> {
            if (!a.getValue().equals(b.getValue())) return b.getValue() - a.getValue();
            return a.getKey().compareTo(b.getKey());
        });

        for (int i = 0; i < Math.min(k, list.size()); i++) {
            System.out.println(list.get(i).getKey() + " " + list.get(i).getValue());
        }
    }
}
