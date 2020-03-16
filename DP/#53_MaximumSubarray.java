class MaximumSubarry {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]); //两种状态: 1.加入当前数值 2.从当前数值重新计算
            max = Math.max(dp[i],max); //max跟踪记录最大值
        }
        return max;
    }
//简化dp 只记录前一次状态 空间复杂度O(1)
    public int maxSubArray_2(int[] nums) {
        int sum = nums[0];
        int max = nums[0];
        for(int i =1 ; i < nums.length ; i++){
            if(sum<0) sum = nums[i];
            else sum += nums[i];
            max = Math.max(sum,max); //max跟踪记录最大值
        }
        return max;
    }
}