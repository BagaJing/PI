class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        // k 满指针 i 快指针
        for(int i = 0 ; i < nums.length ;i++){
            if(nums[i]!=val){
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}