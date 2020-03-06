//leetcode : 154
//ref:https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/154-find-minimum-in-rotated-sorted-array-ii-by-jyd/
class Solution {
	//把数组分为nums1,nums2两部分，并维护 nums1 >= nums2，最终mid应为两数组的临界
    public int findMin(int[] nums) {
    	int left=0, right = nums.length-1;
    	while(left < right){
    		int mid = left + (right - left)/2;
    		//mid在nums1中，维护mid < i < right, 左侧缩
    		if(nums[mid] > nums[right]) left = mid+1;
    		// mid在nums2中，维护 mid < i < right 右缩进
    		else if(nums[mid] < nums[right]) right = mid;
    		//数组中存在重复值
    		else right--;
    	}
    	return nums[left];
    }
}