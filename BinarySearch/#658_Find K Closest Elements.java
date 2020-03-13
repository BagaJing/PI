//leetcode:658
//ref:leetcode-cn.com/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/
//双指针 排除法 Time:N Space:1
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
    	int removeSize = arr.length - k;
    	int left = 0, right = arr.length-1;
    	while(removeSize>0){
    		int leftRange = x - arr[left];
    		int rightRange = arr[right] - x;
    		if(leftRange <= rightRange) //排除差值较大的选项
    			right--;
    		else
    			left++;
    		removeSize--;
    	}
    	List<Integer> res = new ArrayList<>();
    	for(int i = left ; i<=right;i++)
    		res.add(arr[i]);
    	return res;
    }
}
//二分查找 Time:LogN Space: 1
// x靠mid更近时 x-arr[mid] <= arr[mid+k]-x 右缩
// x靠mid+k更近时，x-arr[mid]>arr[mid+k]-x 左缩
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
    	int left=0,right = arr.length-k;
    	while(left < right){
    		int mid = left + (right-left)/2;
    		if(x - arr[mid] > arr[mid+k]-x)
    			left=mid+1;
    		else
    			right=mid;
    	}
    	List<Integer> res = new ArrayList<>();
    	for(int i = left ; i < left+k; i++)
    		res.add(arr[i]);
    	return res;
    }
}