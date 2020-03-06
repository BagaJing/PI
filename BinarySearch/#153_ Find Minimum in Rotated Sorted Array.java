class Solution {
    public int findMin(int[] nums) {
    	int left = 0, right = nums.length-1;
    	while(left < right) // end condition: left == right
    	{
    		int mid = left + (right - left)/2;
    		if(nums[left] <= nums[mid]) // left between mid are ordered
    		{
    			if(nums[left] > nums[right]) // rotate between mid and right
    				left = mid+1;
    			else //rotate between left and mid
    				right = mid;
    		}
    		else // mid between right are ordered
    		{
    			if(nums[left] > nums[right]) //rotate between left and mid
    				right = mid;
    			else // rotate between mid and right
    				left = mid+1;
    		}
    	}
    	return nums[left];
    }
}