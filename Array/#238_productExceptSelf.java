class Solution {
    //6ms
    public int[] productExceptSelf(int[] nums) {
        int totalProduct = 1;
        int zeroCount = 0;
        for(Integer i : nums){
            if(i!=0) totalProduct *= i;
            else zeroCount++;
            if(zeroCount>1)
                return new int[nums.length];
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i]==0){
                Arrays.fill(nums,0);
                nums[i] = totalProduct;
                return nums;
            }
            nums[i] = totalProduct/nums[i];
        }
            
        return nums;
    }
    //1ms
    public int[] productExceptSelf_1(int[] nums) {
        //res[i] 应为左侧所有数的乘积 × 右侧所有数的乘积
        int k = 1, len = nums.length;
        int[] res = new int[len];
        
        //k为i左侧所有数的乘积
        for(int i = 0 ; i < len ; i++){
            res[i] = k;
            k *= nums[i];
        }
        k = 1;
        for(int i = len-1 ; i >=0 ; i--){
            // k为i右侧所数的乘积, res[i]*k 即为 左侧所有数的乘积 × 右侧所有数的乘积
            res[i] *= k;
            k*=nums[i];
        }
        return res;
    }
}