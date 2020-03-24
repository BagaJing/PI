class Solution {
    public int massage(int[] nums) {
        int len = nums.length;
        if(len < 1) return 0;
        if(len == 1) return nums[0];
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i = 2 ; i < len+1 ; i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i-1]); //i-1处的最优解 和 i-2处的最优解+i处的value
        }
        return dp[len];
    }
}