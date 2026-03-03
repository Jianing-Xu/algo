package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：异常的打卡记录
 *
 * 【题目描述】
 * 系统记录了N条员工打卡记录，格式为 "员工ID 打卡时间"。
 * 异常规则：同一员工在同一天有3次及以上打卡记录视为异常。
 * 输出所有异常员工ID，按ID排序。
 *
 * 【解题思路：HashMap<员工ID, HashMap<日期, 打卡次数>> 统计】
 */
public class AbnormalCheckIn {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        // 员工ID -> 日期 -> 打卡次数
        Map<String, Map<String, Integer>> records = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");
            String empId = parts[0];
            String datetime = parts[1];
            String date = datetime.substring(0, 10); // 提取日期部分

            records.computeIfAbsent(empId, k -> new HashMap<>());
            Map<String, Integer> dateMap = records.get(empId);
            dateMap.put(date, dateMap.getOrDefault(date, 0) + 1);
        }

        // 找出异常员工
        List<String> abnormal = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> entry : records.entrySet()) {
            for (int count : entry.getValue().values()) {
                if (count >= 3) {
                    abnormal.add(entry.getKey());
                    break; // 一个日期异常即可
                }
            }
        }

        Collections.sort(abnormal);
        for (String id : abnormal) {
            System.out.println(id);
        }
    }
}
