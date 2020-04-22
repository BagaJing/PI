public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
     //space m*a.length
     //time m*a.length
    public int backPack(int m, int[] A){
      int[][] dp = new int[A.length+1][m+1]; //dp[i][v] 背包负重为v时，对于货物A[i-1]的最优解 装或不装
      int res = 0;
      for(int i = 1 ; i <= A.length ; i++){
          for(int v = 1; v <= m ; v++){
              if (v < A[i-1]) dp[i][v] = dp[i-1][v];//背包负重小于货物重量,直接誊写数据
              else dp[i][v] = Math.max(dp[i-1][v],dp[i-1][v-A[i-1]]+A[i-1]);
          }
      }
      return dp[A.length][m];
    }
    //space m
    //time m*a.length
    public int backPack_1(int m, int[] A) {
        // write your code here
        int[] dp = new int[m+1];
        for (int i = 1; i <= A.length ; i++ ) {
            //递减顺序计算，保证dp[m-A[i-1]]保存的状态是dp[i-1,m-A[i-1]]的状态
            for(int v = m ; v >= A[i-1] ; v--){
                // dp[v] 最大容量为v时，
                dp[v] = Math.max(dp[v],dp[v-A[i-1]]+A[i-1]);
            }
        }
        return dp[m];
    }
    
}