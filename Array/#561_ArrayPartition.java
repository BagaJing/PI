//leetcode:561
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0 ; i < nums.length ;i+=2){
            if(i >= nums.length) break;
            sum += nums[i];
        }
        return sum;
    }
}