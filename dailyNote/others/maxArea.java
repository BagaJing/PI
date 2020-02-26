class Solution {
    /*双指针法 官方题解*/
    /*两个指针，一个指头，一个指尾，每回移动 较小的坐标，用max记录遍历过的最大值*/
    public int maxArea(int[] height){
        int iter1 = 0;
        int iter2 = height.length-1;
        int max = 0;
        while(iter1 < iter2){
            max = Math.max(Math.min(height[iter1],height[iter2])*(iter2-iter1),max);
            if(height[iter2]>height[iter1]) iter1++;
            else iter2--;
        }
        return max;
    }
    /*暴力法 O(n2)
    public int maxArea(int[] height) {
        int len = height.length-1;
        int max = 0;
        while(len > 0){
            for(int i = 0 ; i <= height.length-1-len;++i){
                int i2 = i+len;
                int yRange = (int)Math.min(height[i],height[i2]);
                int square = yRange*len;
                max = square>max? square:max;
            }
            len--;
        }
        return max;
        
    }
    */
}
