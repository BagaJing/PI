class triangle{
    //额外空间(n)的做法
	    public int minimumTotal(List<List<Integer>> triangle) {
        int maxLen = triangle.size();
        int[] dp = new int[maxLen];
        //初始化dp数组，把三角形最后一行推进
        for(int i = 0 ; i < maxLen; i++){
            dp[i] = triangle.get(maxLen-1).get(i);
        }
        //自底向上 从倒数第二层计算最优解
        for(int i = maxLen-2 ; i >=0 ; i--){
            for(int j = 0 ; j <= i; j++){
                dp[j] = Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
    //额外空间(n2)的做法
        public int minimumTotal_2(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[][] dp = new int[len][len];
        //初始化dp数组
        for(int i = 0 ; i < len ; i++){
            dp[len-1][i] = triangle.get(len-1).get(i);
        }
        //自底向上
        for(int i=len-2; i>=0;i--){
            for(int j = 0; j <= i ; j++){
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}