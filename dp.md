# DP
[预测玩家(自下向上)](#1)
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