class Solution {
    //双指针
    public int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length;
        if(len == 0) return 0;
        int left=0,right = 0;
        int count = 0, preEven = 0, res = 0;
        while(right < len){
            if(count < k){
                if(nums[right]%2!=0) count += 1;
                right += 1;
            }
            if(count == k){ //当子数组奇数为k时，计算子数组左侧有多少个偶数
                preEven = 0;
                while(count == k){
                    res+=1; //一个偶数代表一个子数组
                    if(nums[left]%2!=0) count-=1;
                    left +=1;
                    preEven +=1;
                }
            }else res += preEven; //当right指针所指为偶数时 加上左侧偶数的个数，相当于 左侧偶数*右侧偶数
        }
        return res;
    }
}