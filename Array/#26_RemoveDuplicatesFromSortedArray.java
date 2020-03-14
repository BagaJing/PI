class Solution {
    public int removeDuplicates(int[] nums) {
        //快慢指针
        int slow = 0, quick = 0;
        int len = nums.length;
        while(quick<len&&slow<=quick){
            if(nums[slow]==nums[quick]){
                quick++;
            }else{
                slow++;
                nums[slow]=nums[quick];
                quick++;
            }
        }
        return slow+1;
    }
}