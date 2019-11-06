# DP
[预测玩家(自下向上)](#1)
[最长的斐波那契子序列的长度(哈希+dp)](#2)
[最长上升子序列](#3)
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
