class Solution {
    public void moveZeroes(int[] nums) {
        int slow =0, quick = 0;
        int len = nums.length;
        //int zeroNums = 0;
        while(quick<len&&slow<len){
            if(nums[quick]!=0){
                nums[slow]=nums[quick];
                slow++;
            }
            quick++;
        }
         //System.out.println(slow);
        for(int i = slow; i < len ; i++)
            nums[i] = 0;
    }
}