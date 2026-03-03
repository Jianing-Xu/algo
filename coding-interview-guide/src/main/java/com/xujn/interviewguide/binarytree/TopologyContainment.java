package com.xujn.interviewguide.binarytree;

import com.xujn.interviewguide.model.TreeNode;

/**
 * 判断一棵树是否包含另一棵树的全部拓扑结构。
 */
public class TopologyContainment {

    public boolean contains(TreeNode first, TreeNode second) {
        if (second == null) {
            return true;
        }
        if (first == null) {
            return false;
        }
        return check(first, second) || contains(first.left, second) || contains(first.right, second);
    }

    private boolean check(TreeNode first, TreeNode second) {
        if (second == null) {
            return true;
        }
        if (first == null || first.value != second.value) {
            return false;
        }
        return check(first.left, second.left) && check(first.right, second.right);
    }
}
