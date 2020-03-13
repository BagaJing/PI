class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        // k 慢 i 快
        int k = 0, i = 0;
        int max = 0;
        while(i < nums.length){
            if(nums[i]!=0){
                max = Math.max(max,i-k+1);
                i++;
            }else{
                i++;
                k=i;
            }
        }
        return max;
    }
}