package com.xujn.offer.recursiondp;

/**
 * 求 1 到 n 的和。
 */
public class SumOneToN {

    public int sumNums(int n) {
        int sum = n;
        boolean unused = n > 1 && (sum += sumNums(n - 1)) > 0;
        return sum;
    }
}
