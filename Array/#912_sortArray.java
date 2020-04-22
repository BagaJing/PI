class Solution {
    //快排
    public void quicksort(int[] nums,int left,int right){
        if(left >= right) return;
        int pivot = partition(nums,left,right); //pivot左侧为小于枢值的,右侧为大于枢值的
        quicksort(nums,left,pivot-1);
        quicksort(nums,pivot+1,right);
    }
    public int partition(int[] nums, int left, int right){
        int pivot = left; //选left为枢
        int pivotKey = nums[left];
        while(left < right){
            while(left<right&&nums[right]>=pivotKey)right--; //从右侧找到小于枢值的
            while(left<right&&nums[left]<=pivotKey)left++; //从左侧找到大于枢值的
            swap(nums,left,right); //交换
        } //小于枢值的集中在左侧，大于枢值的集中在右侧 结束条件 left == right
        swap(nums,left,pivot);//把枢值放到位置 使得枢左侧都小于枢值，右侧都大于枢值
        return left;
    }
    public void swap(int[] nums, int idx1,int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
    public int[] sortArray(int[] nums) {
        //快排 
        if(nums==null||nums.length==0) return nums;
        quicksort(nums,0,nums.length-1);
        return nums;
    }
}