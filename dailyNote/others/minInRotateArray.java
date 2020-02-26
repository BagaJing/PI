import java.util.ArrayList;
public class minNumberInRotateArray{
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 1){
            return array[0];
        }
        int start = 0;
        int end = array.length -1;
        while(start <= end){
            int mid = start + (end - start)/2;
            //当start < end 时　因为原数组为非递减数组　则start为最小值
            if(array[start] < array[end]){
                return array[start];
            }
            //说明从mid+1开始的旋转，　则mid+1为最小值
            if(array[mid] > array[mid+1]){
                return array[mid+1];
            }
            //说明旋转点为mid，则mid为最小值
            if(array[mid] < array[mid -1]){
                return array[mid];
            }
            //说明从0到ｍｉｄ一直为非减区间　取下半区间
            if(array[mid] > array[0]){
                start = mid + 1;
            } else{
                //说明转折点发生在前半段，则取上半区间
                end = mid - 1;
            }
        }
        return 0;
    }
}
