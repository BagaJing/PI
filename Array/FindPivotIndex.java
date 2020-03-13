class Solution {
    //leetcode: 724
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for(Integer i : nums)
            sum += i;
        int leftSum = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(leftSum == sum-nums[i]-leftSum) return i;
            leftSum += nums[i];
        }
        return -1;
    }
}