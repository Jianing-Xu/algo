package com.xujn.offer.array;

/**
 * 股票的最大利润。
 */
public class StockMaxProfit {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minPrice = prices[0];
        int best = 0;
        for (int i = 1; i < prices.length; i++) {
            best = Math.max(best, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return best;
    }
}
