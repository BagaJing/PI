class lcis{
	    public int findLengthOfLCIS(int[] nums) {
        if(nums.length==0||nums==null) return 0;
        int res = 1;
        int slow = 0, quick = 1;
        int maxLen = 1;
        while(quick<nums.length&&slow<=quick){
            if(nums[quick]>nums[slow]){
                maxLen++;
                res = Math.max(res,maxLen);
            }else{
                maxLen = 1;
            }
                slow = quick;
                quick++;
        }
        return res;
    }
}