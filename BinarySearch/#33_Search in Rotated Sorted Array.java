// leetcode:33
class solution{
	//pattern 1
	public int search(int[] nums, int target) {
		int left = 0, right = nums.length-1;
		while(left <= right){
			int mid = left+(right-left)/2;
			if(nums[mid] == target) return mid;
			if(nums[left] <= nums[mid]) // rotated between mid to right
			{
				if(target >= nums[left]&&target<nums[mid]) // target between left and mid
					right = mid-1;
				else
					left = mid+1;
			}
			else{ // rotated between left and mid
				if(target<=nums[right]&&target>nums[mid]) // target between mid and right
					left = mid+1;
				else
					right = mid-1;
			}
		}
		return -1;
    }
}