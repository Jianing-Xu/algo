package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：简单序列化接口实现
 *
 * 【题目描述】
 * 实现一个简单的序列化/反序列化接口：
 * 将Java对象（Map/List/基本类型）序列化为字符串，支持反序列化还原。
 * 格式类似JSON简化版：{key1:value1,key2:[v1,v2,v3]}
 *
 * 【解题思路：递归解析】
 */
public class SimpleSerializer {

    public static String serialize(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("{");
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (i > 0) sb.append(",");
            sb.append(entry.getKey()).append(":");
            Object val = entry.getValue();
            if (val instanceof List) {
                sb.append("[");
                List<?> list = (List<?>) val;
                for (int j = 0; j < list.size(); j++) {
                    if (j > 0) sb.append(",");
                    sb.append(list.get(j));
                }
                sb.append("]");
            } else {
                sb.append(val);
            }
            i++;
        }
        sb.append("}");
        return sb.toString();
    }

    public static Map<String, String> deserialize(String s) {
        Map<String, String> result = new LinkedHashMap<>();
        // 去掉外层 {}
        s = s.substring(1, s.length() - 1);
        // 分割键值对（需要处理嵌套的 []）
        List<String> pairs = splitTopLevel(s, ',');
        for (String pair : pairs) {
            int colonIdx = pair.indexOf(':');
            String key = pair.substring(0, colonIdx);
            String value = pair.substring(colonIdx + 1);
            result.put(key, value);
        }
        return result;
    }

    private static List<String> splitTopLevel(String s, char delimiter) {
        List<String> parts = new ArrayList<>();
        int depth = 0;
        StringBuilder current = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '[') depth++;
            else if (c == ']') depth--;
            if (c == delimiter && depth == 0) {
                parts.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0) parts.add(current.toString());
        return parts;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        Map<String, String> map = deserialize(input);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
