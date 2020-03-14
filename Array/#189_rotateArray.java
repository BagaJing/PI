class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int rotated = 0;
        int start = 0;
        while(rotated < nums.length){
            int iter = start;
            int next = nums[iter];
            do{
                int temp = nums[(iter+k)%len];
                nums[(iter+k)%len] = next;
                next = temp;
                iter = (iter+k)%len;
                rotated++;
                if(rotated == nums.length) break;
            }while(iter!=start); //如果下一个坐标等于起始点则跳出循环，更新起始点,避免环
            start++;
        }
    }
}