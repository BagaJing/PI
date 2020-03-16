class threeSum{
    //排序
    public List<List<Integer>> threeSum_1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length<3) return res;
        int len = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < len-2 ; i++){
            int firstNum = nums[i];
            if(firstNum>0) break;
            if(i>0&&nums[i]==nums[i-1]) continue;
            int left = i+1, right = nums.length-1;
            while(left < right){
                int sum = firstNum+nums[left]+nums[right];
                if(sum==0){ 
                    res.add(Arrays.asList(firstNum,nums[left],nums[right]));
                    while(left<right&&nums[left]==nums[left+1]) left++;
                    while(left<right&&nums[right]==nums[right-1])right--;
                    left++;
                    right--;
                } else if(sum>0) right--;
                else if(sum<0) left++;
            }
        }
        return res;
    }
}