//leetcode: 167
//binarySearch
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        while(numbers[left] <= target/2){
            int right = binarySearch(numbers,left+1,target-numbers[left]);
            if(right!=-1&&right!=left) return new int[]{left+1,right+1};
            left++;
        }
        return new int[]{-1,-1};
    }
    private int binarySearch(int[] nums,int start,int target){
        if(start> nums.length-1) return -1;
        int left = start, right = nums.length-1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target) return mid;
            if(target > nums[mid]) left = mid+1;
            else right = mid-1;
        }
        return -1;
    }
}