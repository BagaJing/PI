//只交易一次
class Solution {
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE, sell = 0;
        int len = prices.length;
        for(int i = 0; i < len ; i++){
            sell = Math.max(sell,buy+prices[i]); //决定次日是卖，还是持有 若今日卖出利润小于目前sell,则持有
            buy = Math.max(buy,-prices[i]); //决定今日买不买 ,迭代过程会找到买入的最低价
        }
        return sell;
    }
}