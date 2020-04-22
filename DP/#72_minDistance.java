class Solution {
    //dp[i][j] 为word1中前i个字母转换为word2中前j个字母所需要的步骤
    //当第i个字母等于第j个字母时 说明在此位置不需要操作 则 dp[i][j] = dp[i-1][j-1]
    //否则 三个可达状态中找最优解 dp[i-1][j-1](增一位),dp[i-1][j](删一位),dp[i][j-1](替一位)
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        //初始化 代表着 使word1前i位等于word2前0位需要的步骤数
        for(int i = 0 ; i < len1+1 ; i++)
            dp[i][0] = i;
        //代表着 使word2前0位等于word1前i位需要的步骤数
        for(int i = 0 ; i < len2+1 ; i++)
            dp[0][i] = i;
        for(int i = 1 ; i <= len1 ; i++){
            for(int j = 1 ; j <= len2 ; j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[len1][len2];
    }
}