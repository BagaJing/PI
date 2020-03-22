class Solution {
    public int minIncrementForUnique(int[] A) {
        int[] nums = new int[50000];
        for(int x : A) nums[x]++;
        int res = 0, duplicates = 0;
        //策略:取最小操作数的方法为: 把多余的数字填补到个数为0的数字位置上去
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] >= 2){
                duplicates += nums[i]-1; //需要进行增量的多余数字
                res -= i*(nums[i]-1);   //表示nums[i]-1个需要进行增量的数字i
            }else if(duplicates>0&&nums[i]==0){
                duplicates--;
                res += i; //需要进行增量的数字为i1，目前数字为i2，从i1变成i2需要进行的增量为 i2-i1,因此次数因为i2-i1
            }
        }
        return res;
    }
}