class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums==null||nums.length==0) return 0;
        int slow = 0, quick = 0;
        int sum = nums[slow];
        int minLen = Integer.MAX_VALUE;
        while(quick < nums.length&&slow<=quick){
            if(sum >= s){
                minLen = Math.min(minLen,quick-slow+1);
                sum -= nums[slow];
                slow++;
            }else{
                quick++;
                if(quick < nums.length) sum += nums[quick];
            }
        }
        return minLen == Integer.MAX_VALUE? 0 : minLen;
    }
}