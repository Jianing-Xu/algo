package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：根据IP查找城市
 *
 * 【题目描述】
 * 给定IP段和对应的城市映射表，根据输入的IP地址查找其所属城市。
 *
 * 【解题思路：将IP转为长整数进行二分查找】
 */
public class IpToCity {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        long[] starts = new long[n];
        long[] ends = new long[n];
        String[] cities = new String[n];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(",");
            starts[i] = ipToLong(parts[0]);
            ends[i] = ipToLong(parts[1]);
            cities[i] = parts[2];
        }

        String queryIp = sc.nextLine().trim();
        long queryVal = ipToLong(queryIp);

        for (int i = 0; i < n; i++) {
            if (queryVal >= starts[i] && queryVal <= ends[i]) {
                System.out.println(cities[i]);
                return;
            }
        }
        System.out.println("UNKNOWN");
    }

    static long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        long result = 0;
        for (String part : parts) {
            result = result * 256 + Integer.parseInt(part.trim());
        }
        return result;
    }
}
