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
            }while(iter!=start);
            start++;
        }
    }
}