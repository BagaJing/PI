// 交易两次
class Solution {
    public int maxProfit(int[] prices) {
        int buy_1 = Integer.MIN_VALUE, sell_1 = 0;
        int buy_2 = Integer.MIN_VALUE, sell_2 = 0;
        int len = prices.length;
        //和过去比较，每次选最优解
        for(int i = 0; i < len ; i++){
            buy_1 = Math.max(buy_1,-prices[i]); // buy_1:之前第一次买入剩的钱 -prices[i]: 今天第一次买入剩下的
            sell_1 = Math.max(sell_1,buy_1+prices[i]); // sell_1 : 之前第一次卖出剩的钱 buy1+prices[i] 今天第一次卖出剩的
            buy_2 = Math.max(buy_2,sell_1-prices[i]); // buy_2 之前二次买入剩的  sell_1-prices[i] 今天第二次买入剩的
            sell_2 = Math.max(sell_2,buy_2+prices[i]); //之前二次卖出剩的 buy2+prices[i] 今天二次卖出剩的
        }
        return sell_2;
    }
}