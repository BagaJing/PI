# DP
[预测玩家(自下向上)](#1)

[最长的斐波那契子序列的长度(哈希+dp)](#2)

[最长上升子序列](#3)

[硬币兑换(自下向上)](#4)

[最大正方形](#5)

[完全平方数](#6)

[乘积最大子序列](#7)

#### * 股票问题
[买卖股票的最佳时机 I](#8)
[买卖股票的最佳时机 II](#9)
[买卖股票的最佳时机 III](#10)
[买卖股票的最佳时机 IV](#11)
[最佳买卖股票时机含冷冻期](#12)


<h3 id="1">预测玩家(DP)</h3>
给定一个表示分数的非负整数数组。 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，然后玩家1拿，……。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。

给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。

示例 1:

输入: [1, 5, 2]
输出: False
解释: 一开始，玩家1可以从1和2中进行选择。
如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。
所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为 5。
因此，玩家1永远不会成为赢家，返回 False。
示例 2:

输入: [1, 5, 233, 7]
输出: True
解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/predict-the-winner
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

```Java
//参考: https://leetcode-cn.com/problems/predict-the-winner/solution/java-dong-tai-gui-hua-by-zxy0917-2/
class Solution {
    //dp[i][j]表示先手玩家比后手玩家大于的部分
    //若最后dp[i][j]大于0则说明先手玩家大于后手玩家值
    public boolean PredictTheWinner(int[] nums) {
                int len = nums.length;
                if(len<=2||len%2==0) return true;
                  int[][] dp = new int[len][len];
  
                  for(int i = 0 ; i < len ; i++)
                          dp[i][i] = nums[i];
        //从底向上开始 最开始假设只有一个数可以选
        //
                  for(int i = len-1 ; i >= 0 ; i--){
                          for(int j = i+1 ; j < len ; j++){
                              //从头和尾取值，根据剩下的对手的最优解，算出差值，取最大值
                              //此轮迭代算出此时先手最优解, 也是下轮迭代后手的最优解
                                  dp[i][j] = Math.max(nums[i] - dp[i+1][j],
                                                     nums[j] - dp[i][j-1]);
                          }
                  }
                  for(int i =0 ; i < len ; i++)
                          System.out.println(Arrays.toString(dp[i]));
                  return dp[0][len-1] > 0;  
    }
}
```
<h3 id ="2">最长的斐波那契子序列的长度</h3>
如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：

n >= 3
对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。

（回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）

 

示例 1：

输入: [1,2,3,4,5,6,7,8]
输出: 5
解释:
最长的斐波那契式子序列为：[1,2,3,5,8] 。
示例 2：

输入: [1,3,7,11,12,14,18]
输出: 3
解释:
最长的斐波那契式子序列有：
[1,11,12]，[3,11,14] 以及 [7,11,18] 。
 

提示：

3 <= A.length <= 1000
1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
（对于以 Java，C，C++，以及 C# 的提交，时间限制被减少了 50%）

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/length-of-longest-fibonacci-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
//题解：https://leetcode-cn.com/problems/length-of-longest-fibonacci-subsequence/solution/zui-chang-de-fei-bo-na-qi-zi-xu-lie-de-chang-du-by/
class Solution {
    /*
        哈希表+动态规划解法:
            1.一张哈希表来记录数组中的值与相对坐标
            2.另外一张哈希表来记录两个连续斐波那契节点与其所对应的子序列长度 用i*len+j 来代表(i,j)
            3.从序列长度为0的情况开始双层遍历，若index哈希表中能找到A[k]-A[j]的值i且 i < j,则根据(i,j)点的子序列长度增1，存入（j,k）中，从而最终得到最长子序列.
    */
    public int lenLongestFibSubseq(int[] A) {
        int len = A.length;
        Map<Integer,Integer> index = new HashMap<>();
        for(int i = 0 ; i < len ; i++)
            index.put(A[i],i);
        Map<Integer,Integer> longest = new HashMap<>();
        int res = 0;
        for(int k = 0 ; k < len ; k++){
            for(int j = 0 ; j < k ; j++){
                int i = index.getOrDefault(A[k]-A[j],-1);
                if(i >=0&&i < j){
                    //在这里用i*len+j来代表(i,j)
                    int candidate = longest.getOrDefault(i*len+j,2)+1;
                    //(j,k)来存储更新的子序列长度
                    longest.put(j*len+k,candidate);
                    res = Math.max(res,candidate);
                }
            }
        }
        return res>=3?res:0;
    }
}
```
<h3 id="3">最长上升子序列</h3>
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    
    //官方DP
    public int lengthOfLIS(int[] nums){
        int LEN = nums.length;
        if(LEN==0) return 0;
        //初始化DP数组
        int[] dp = new int[LEN];
        dp[0] = 1;
        int res = 1;
        for(int j = 1; j < LEN ; j++){
            int val = 0;
            for(int i = 0 ; i < j ; i++){
                if(nums[j] > nums[i]){
                    val = Math.max(val,dp[i]);
                }
                //取二层遍历的最大值与结果比较,减少操作次数
                dp[j] = val + 1;
                res = Math.max(dp[j],res);
            }
        }
        return res;
    }
    //DP 124ms 自己写的
    /*
    public int lengthOfLIS(int[] nums) {
        int LEN = nums.length;
        if(LEN == 0) return 0;
        Map<Integer,Integer> longest = new HashMap<>();
        int res = 0;
        for(int j = 0 ; j < LEN ; j++){
            for(int i = 0 ; i < j ; i++){
                if(nums[i] < nums[j]){
                    int val = longest.getOrDefault(i,1);
                    val += 1;
                    int current = longest.getOrDefault(j,1);
                    val = Math.max(current,val);
                    longest.put(j,val);
                    res = Math.max(res,val);
                }
            }
        }
        return res==0? 1:res;
    }
    */
}
```
<h3 id="4">硬币兑换</h3>
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

示例 1:

输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1
示例 2:

输入: coins = [2], amount = 3
输出: -1
说明:
你可以认为每种硬币的数量是无限的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    //动态规划 从下而上 计数
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        //初始化dp
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++){
            for(int j = 0; j < coins.length ; j++){
                if(coins[j]<=i)
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
            }
        }
        return dp[amount] > amount? -1:dp[amount];
    }
}
```
<h3 id="5">最大正方形</h3>
在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

示例:

输入: 

```
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
```
输出: 4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximal-square
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    public int maximalSquare(char[][] matrix) {
        int len1 = matrix.length;
        if(len1 == 0) return 0;
        int len2 = matrix[0].length;
        if(len2 == 0) return 0;
        int[][] dp = new int[len1+1][len2+1];
        int res = 0;
        for(int i = 1 ; i <= len1 ; i++){
            for(int j = 1 ; j <= len2 ; j++){
                if(matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
                    res = Math.max(res,dp[i][j]);
                }
            }
        }
        return res*res;
        
    }
}
```
<h3 id ="6">完全平方数</h3>
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/perfect-squares
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

和找零问题差不多，都是从1开始计数记到n，根据n前面的最优解，得到当前的最优解
[硬币兑换](#4)
```Java
class Solution {
    //和找零问题解法一致
    public int numSquares(int n) {
        int max = (int)Math.sqrt(n);
        int[] nums = new int[max];
        for(int i = 0 ; i < nums.length ; i++)
            nums[i] = (i+1)*(i+1);
          //dp[0]空出来
        int[] dp = new int[n+1];
        Arrays.fill(dp,n+1);
        dp[0] = 0;
        for(int i = 1 ; i < dp.length ; i++){
            for(int x : nums){
                if(x <= i){
                    dp[i] = Math.min(dp[i],dp[i-x]+1);
                }
            }
        }
        return dp[n] > n ? -1 : dp[n];
    }
}

```
<h3 id="7">乘积最大子序列</h3>
给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

示例 1:

输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:

输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-product-subarray
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    public int maxProduct(int[] nums){
        //令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
        //由于数组中有负值存在，当前最大乘以负值就变成当前最小，当前最小反之。因此需要一个当前最小值imax,当出现负值时与imax互换
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] < 0){
                //当nums[i]小于零时，当前的最大值和最小值先互换 再计算
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i],nums[i]);
            imin = Math.min(imin*nums[i],nums[i]);
            max = Math.max(max,imax);
        }
        return max;
    }
    /* 暴力
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        if(len == 1) return nums[0];
        int res = Integer.MIN_VALUE;
        int[] dp = new int[len];
        for(int i = 0 ; i <len ; i++){
            for(int j = i; j < len ; j++){
                if(j==i) dp[j] = nums[j];
                if(j > i) dp[j] = nums[j]*dp[j-1];
                //System.out.println(res+" "+dp[i][j]);
                res = Math.max(res,dp[j]);
            }
        }
        return res;
    }
    */
}
```
<h3 id = "8">买卖股票的最佳时机</h3>
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

注意你不能在买入股票前卖出股票。

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

[一篇不错的讲所有股票问题的题解](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems)

```Java
class Solution {
    public int maxProfit(int[] prices) {
        int dp_0 = 0, dp_1 = Integer.MIN_VALUE;
        int len = prices.length;
        for(int i = 0; i < len ; i++){
            dp_0 = Math.max(dp_0,dp_1+prices[i]);
            dp_1 = Math.max(dp_1,-prices[i]);
        }
        return dp_0;
    }
}
```

<h3 id="9">买卖股票的最佳时机 II</h3>
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
示例 2:

输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    public int maxProfit(int[] prices) {
       int dp_0 = 0, dp_1 = Integer.MIN_VALUE;
       int len = prices.length;
       for(int i = 0 ; i < len ; i++){
           int dp_old_0 = dp_0;
           dp_0 = Math.max(dp_0,dp_1+prices[i]);
           dp_1 = Math.max(dp_1,dp_old_0-prices[i]);
       } 
       return dp_0;
    }
}
```

<h3 id="10">买卖股票的最佳时机 III</h3>
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
示例 2:

输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3:

输入: [7,6,4,3,1] 
输出: 0 
解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    public int maxProfit(int[] prices) {
        int dp_1_0 = 0, dp_1_1 = Integer.MIN_VALUE;
        int dp_2_0 = 0, dp_2_1 = Integer.MIN_VALUE;
        int len = prices.length;
        for(int i =0; i < len ; i++){
            dp_2_0 = Math.max(dp_2_0,dp_2_1+prices[i]);
            dp_2_1 = Math.max(dp_2_1,dp_1_0 - prices[i]);
            dp_1_0 = Math.max(dp_1_0,dp_1_1+prices[i]);
            dp_1_1 = Math.max(dp_1_1,-prices[i]);
        }
        return dp_2_0;
    }
}
```
<h3 id="11"> 买卖股票的最佳时机 IV</h3>
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [2,4,1], k = 2
输出: 2
解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
示例 2:

输入: [3,2,6,5,0,3], k = 2
输出: 7
解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        //当k大于或等于len/2时，和 k为无限次的情况相同
        //因为一组数据中最多交易次数为len/2，即第一天买第二天卖
        if(k >= len>>>1){
            int dp_0 = 0, dp_1 = Integer.MIN_VALUE;
            for(int i = 0 ; i < len ; i++){
                int dp_old_0 = dp_0;
                dp_0 = Math.max(dp_0,dp_1+prices[i]);
                dp_1 = Math.max(dp_1,dp_old_0-prices[i]);
            }
            return dp_0;
        }
        //每一天的三种选择，1.保持，买入，卖出
        //0表示无股票状态
        int[] dp_0 = new int[k+1];
        //1表示持有股票状态
        int[] dp_1 = new int[k+1];
        Arrays.fill(dp_1,Integer.MIN_VALUE);
        //遍历prices数组
        for(int price : prices){
            for(int i = 1 ; i <= k ; i++){
                //状态0的两种操作 1.保持 2.卖出股票
                dp_0[i] = Math.max(dp_0[i],dp_1[i]+price);
                //状态1的两种操作 1.保持 2.买入股票 
                dp_1[i] = Math.max(dp_1[i],dp_0[i-1]-price); //买入和卖出要分天进行
                //两种状态每次都取使其最大化的选择
            }
        }
        return dp_0[k];
     }
}
```
<h3 id="12"> 最佳买卖股票时机含冷冻期</h3>
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    public int maxProfit(int[] prices) {
        int dp_0 = 0,dp_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0;
        int len = prices.length;
        for(int i = 0 ; i < len ; i++){
            int temp = dp_0;
            dp_0 = Math.max(dp_0,dp_1+prices[i]);
            dp_1 = Math.max(dp_1,dp_pre_0-prices[i]);
            dp_pre_0 = temp;
        }
        return dp_0;
    }
}
```

































