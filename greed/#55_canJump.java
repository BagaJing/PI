class canJump{
	public boolean canJump_1(int[] nums) {
        if(nums.length<=1) return true;
        int max = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(max<i) return false;
            if(max >= nums.length-1) return true;
            //贪心思想:每次更新目前能跳到的最远距离
            max = Math.max(max,nums[i]+i);
        }
        return true;
    }
}