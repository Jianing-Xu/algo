package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：模拟数据序列化传输
 *
 * 【题目描述】
 * 实现一个简单的序列化/反序列化协议：
 * 将多个键值对序列化成"key1:value1,key2:value2"格式的字符串传输，
 * 接收端反序列化还原成Map并按key排序输出。
 *
 * 【解题思路：字符串分割 + TreeMap排序】
 */
public class DataSerialization {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String serialized = sc.nextLine().trim();

        TreeMap<String, String> map = new TreeMap<>();
        String[] pairs = serialized.split(",");
        for (String pair : pairs) {
            String[] kv = pair.split(":");
            if (kv.length == 2) {
                map.put(kv[0].trim(), kv[1].trim());
            }
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
