//double iter greed
class Solution {
    public int maxArea(int[] height) {
        int iter1 = 0, iter2 = height.length-1;
        int max = 0;
        while(iter1 < iter2){
            max = Math.max(max,Math.min(height[iter1],height[iter2])*(iter2-iter1));
            if(height[iter1] < height[iter2]) iter1+=1;
            else iter2 -= 1;
        }
        return max;
    }
}