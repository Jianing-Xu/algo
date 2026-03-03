package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：文件缓存系统
 *
 * 【题目描述】
 * 实现一个文件缓存系统，给定缓存总容量capacity。
 * 支持操作：
 * put <filename> <size>：放入文件，如果空间不足则按LRU策略淘汰
 * get <filename>：访问文件（刷新为最近使用）
 *
 * 【解题思路：LinkedHashMap 或自定义LRU结构】
 * Java的LinkedHashMap天然支持LRU（设置accessOrder=true）。
 */
public class FileCacheSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int capacity = Integer.parseInt(sc.nextLine().trim());
        int n = Integer.parseInt(sc.nextLine().trim());

        // LinkedHashMap 以访问顺序排序，最近访问的在最后
        LinkedHashMap<String, Integer> cache = new LinkedHashMap<>(16, 0.75f, true);
        int usedSpace = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");
            String op = parts[0];

            if (op.equals("put")) {
                String filename = parts[1];
                int size = Integer.parseInt(parts[2]);

                if (size > capacity) {
                    // 单个文件就超出总容量，无法放入
                    continue;
                }

                // 如果已存在，先移除旧的
                if (cache.containsKey(filename)) {
                    usedSpace -= cache.get(filename);
                    cache.remove(filename);
                }

                // 空间不够时，按LRU淘汰（LinkedHashMap遍历顺序就是LRU顺序）
                while (usedSpace + size > capacity && !cache.isEmpty()) {
                    Iterator<Map.Entry<String, Integer>> it = cache.entrySet().iterator();
                    Map.Entry<String, Integer> eldest = it.next();
                    usedSpace -= eldest.getValue();
                    it.remove();
                }

                cache.put(filename, size);
                usedSpace += size;

            } else if (op.equals("get")) {
                String filename = parts[1];
                if (cache.containsKey(filename)) {
                    cache.get(filename); // 访问一次即可刷新LRU顺序
                    System.out.println(filename + " " + cache.get(filename));
                } else {
                    System.out.println("NONE");
                }
            }
        }
    }
}
