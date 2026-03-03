package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：明日之星选举（投票统计）
 *
 * 【题目描述】
 * 公司举办明日之星选举活动。N个候选人参加投票。
 * 每个人投出一票（用候选人名字表示）。
 * 按照得票数降序输出所有候选人。票数相同按名字字典序排列。
 *
 * 【解题思路：HashMap统计 + 排序】
 */
public class StarsElection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        Map<String, Integer> voteMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = sc.nextLine().trim();
            voteMap.put(name, voteMap.getOrDefault(name, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(voteMap.entrySet());
        // 按票数降序，票数相同按名字字典序
        list.sort((a, b) -> {
            if (!a.getValue().equals(b.getValue())) return b.getValue() - a.getValue();
            return a.getKey().compareTo(b.getKey());
        });

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey());
        }
    }
}
