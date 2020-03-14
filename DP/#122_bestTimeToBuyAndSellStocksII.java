//尽量多的进行买卖
//必须先买再卖
class Solution {
    public int maxProfit(int[] prices) {
        int sell = 0, buy = Integer.MIN_VALUE;
        int len = prices.length;
        for(int i = 0; i < len; i++){
            //int old_sell = sell;
            buy = Math.max(buy,sell-prices[i]); // buy : 之前买的话剩的钱 sell-pricesi 今天买所剩的钱
            sell = Math.max(sell,buy+prices[i]); // sell：之前卖出的最大利润 buy+pricesi : 今天卖出的或得的利润
            //不考虑当天即买又卖，无利润差
           
        }
        return sell;
    }
}
// 1,2,3,4,5