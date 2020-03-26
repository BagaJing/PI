class Solution {
    //ref:https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
    //Time O: N*C N为数组长度，C为数组之和的一半
    //数组范围一个一个增大，target从0逐渐变大
    public boolean canPartition(int[] nums) {
        int sum = 0,len = nums.length;
        for(Integer i : nums)
            sum += i;
        if(sum%2==1) return false;
        int target = sum/2;
        boolean[][] dp = new boolean[len][target+1];
        dp[0][0] = true;
        if(nums[0]<=target)
            dp[0][nums[0]] = true;
        for(int i = 1 ; i < len ; i++){
            for(int j = 0 ; j <= target ; j++){
                dp[i][j] = dp[i-1][j];
                if(j == nums[i]){
                    dp[i][j] = true;
                    continue;
                }  
                if(j > nums[i]){
                    dp[i][j] = dp[i-1][j]||dp[i-1][j-nums[i]];
                }
                if(dp[i][target] == true) return true;
            }
        }
        return dp[len-1][target];
    }
    //time  N*C
    //space C
    //增加减枝，减掉对target小于nums[i]的迭代
    public boolean canPartition_1(int[] nums) {
        int sum = 0, len = nums.length;
        for(Integer i : nums)
            sum+=i;
        if(sum%2==1)return false;
        int target = sum/2;
        boolean dp[] = new boolean[target+1];
        dp[0] = true;
        if(nums[0] <= target) dp[nums[0]] = true;
        for(int i = 1 ; i < len ; i++){
            for(int j = target; nums[i]<=j ; j--){
                if(dp[target]) return true;
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}