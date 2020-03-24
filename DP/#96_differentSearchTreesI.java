class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1; //空树只有一种构建方法
        dp[1] = 1; //节点数为1时有一种构建方法
        for(int i =2 ; i <= n; i++){
            for(int j = 1 ; j <= i ; j++){
                dp[i] += dp[j-1]*dp[i-j]; 
                //笛卡尔积 以j为树节点 dp[j-1]，为j构建左子树的种类，i-j为j构建右子树的种类
            }
        }
        return dp[n];
    }
}