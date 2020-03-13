class Solution {
    //leetcode:747
    public int dominantIndex(int[] nums) {
        int max = 0;
        int iter = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] > max){
                max = nums[i];
                iter = i;
            }
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i]==0) continue;
            if(max/nums[i]<2&&i!=iter) return -1;
        }
        return iter;
    }
}