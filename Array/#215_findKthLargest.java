class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums==null||nums.length==0) return 0;
        //第k大 等于 第len-k小
        return quicksort(nums,0,nums.length-1,nums.length-k);
    }
    private int quicksort(int[] nums,int left,int right, int smalls){
        if(left == right) return nums[left];
        //随机选取枢
        Random ran = new Random();
        int pivot = left + ran.nextInt(right-left);
        //int pivot = left + (right-left)/2; //选mid为枢
        int pivot_index = partition(nums,left,right,pivot); // index的值为第index小的值
        if(pivot_index==smalls) return nums[pivot_index]; 
        else if(pivot_index < smalls) return quicksort(nums,pivot_index+1,right,smalls);
        else return quicksort(nums,left,pivot_index-1,smalls);
    }
    private int partition(int[] nums,int left,int right,int pivot){
        int pivot_val = nums[pivot];
        int index = left;
        swap(nums,pivot,right); //暂时把枢值放在最右侧，遍历其他数值
        for(int i = left; i < right ; i++){
            if(nums[i] < pivot_val){ //把小于枢值的放在左侧
                swap(nums,i,index);
                index++;
            }
        }
        swap(nums,index,right); //0到index-1的值都小于枢值,把枢值换到index,则index的值为第index小的值
        return index;
    }
    private void swap(int[] nums,int idx1,int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}