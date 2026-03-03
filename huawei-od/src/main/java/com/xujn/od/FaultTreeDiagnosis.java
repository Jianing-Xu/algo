package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：故障树诊断
 *
 * 【题目描述】
 * 给定一棵故障树（二叉树），叶子节点为基本故障事件（0或1），
 * 中间节点为逻辑门（AND/OR）。根据叶子节点状态和逻辑门关系，
 * 计算根节点的故障状态。
 *
 * 【解题思路：后序遍历递归求值】
 */
public class FaultTreeDiagnosis {

    static String[] nodeType;  // "AND"/"OR"/数值
    static int[][] children;   // children[i] = {left, right}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        nodeType = new String[n];
        children = new int[n][2];
        for (int[] c : children) Arrays.fill(c, -1);

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");
            nodeType[i] = parts[0];
            if (parts.length > 1) children[i][0] = Integer.parseInt(parts[1]);
            if (parts.length > 2) children[i][1] = Integer.parseInt(parts[2]);
        }

        System.out.println(evaluate(0));
    }

    private static int evaluate(int node) {
        if (node == -1) return 0;

        String type = nodeType[node];

        // 叶子节点
        if (type.equals("0") || type.equals("1")) {
            return Integer.parseInt(type);
        }

        int leftVal = evaluate(children[node][0]);
        int rightVal = evaluate(children[node][1]);

        if (type.equals("AND")) {
            return (leftVal == 1 && rightVal == 1) ? 1 : 0;
        } else { // OR
            return (leftVal == 1 || rightVal == 1) ? 1 : 0;
        }
    }
}
