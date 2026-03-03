package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：传递悄悄话（二叉树层序遍历求最长链路）
 *
 * 【题目描述】
 * 给定一棵二叉树（用数组层序表示，-1表示空节点），
 * 每个节点有一个传递时间值。悄悄话从根节点传到叶子节点。
 * 求悄悄话传递到所有叶子节点的最长时间（即根到叶子路径上权值之和的最大值）。
 *
 * 【解题思路：DFS / BFS 遍历二叉树求最长路径和】
 * 使用数组表示的完全二叉树，节点i的左子节点为2*i+1，右子节点为2*i+2。
 */
public class WhisperPass {

    static int[] tree;
    static int maxTime = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        String[] parts = line.split(" ");
        tree = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            tree[i] = Integer.parseInt(parts[i]);
        }

        if (tree.length == 0 || tree[0] == -1) {
            System.out.println(0);
            return;
        }

        maxTime = 0;
        dfs(0, 0);
        System.out.println(maxTime);
    }

    private static void dfs(int index, int currentSum) {
        if (index >= tree.length || tree[index] == -1) return;

        currentSum += tree[index];

        int left = 2 * index + 1;
        int right = 2 * index + 2;

        boolean isLeaf = true;
        if (left < tree.length && tree[left] != -1) {
            isLeaf = false;
            dfs(left, currentSum);
        }
        if (right < tree.length && tree[right] != -1) {
            isLeaf = false;
            dfs(right, currentSum);
        }

        if (isLeaf) {
            maxTime = Math.max(maxTime, currentSum);
        }
    }
}
