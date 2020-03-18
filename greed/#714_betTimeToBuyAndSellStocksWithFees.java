class Solution {
    public int maxProfit(int[] prices, int fee) {
        int sell = 0, buy = Integer.MIN_VALUE;
        int len = prices.length;
        for(int i = 0; i < len ; i++){
            //保持买入后剩余的钱最大化
            buy = Math.max(buy,sell-prices[i]);
            //保持卖出后的钱最大化
            sell = Math.max(sell,buy+prices[i]-fee);
        }
        return sell;
    }
}