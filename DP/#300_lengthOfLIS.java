class lengthOfLIS{
	    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len==0) return 0;
        int[] dp = new int[len];
        dp[0] = 1;
        int res = 1;
        for(int i = 1 ; i < len ; i++){
            int maxLen = 0;
            for(int j = 0 ; j < i ; j++){
                if(nums[i] > nums[j]){
                    maxLen = Math.max(maxLen,dp[j]);
                }
            }
            dp[i] = maxLen+1;
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}