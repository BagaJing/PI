# Sort
[数组中的第K个最大元素](#1)
<h3 id ="1">数组中的第K个最大元素</h3>
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    //快排
    int[] nums;
    public int findKthLargest(int[] nums, int k){
        this.nums = nums;
        int len = this.nums.length;
        return quickSort(0,len-1,len-k);
    }
    private int quickSort(int left, int right, int k_smallest){
        if(left == right)
            return this.nums[left];
        Random ran = new Random();
        int pivot_index = left + ran.nextInt(right - left);
        pivot_index = partition(left,right,pivot_index);
        if(pivot_index == k_smallest)
            return this.nums[pivot_index];
        else if(k_smallest > pivot_index)
            return quickSort(pivot_index+1,right,k_smallest);
        return quickSort(left,pivot_index-1,k_smallest);
    }
    private int partition(int left,int right,int pivot_index){
        int pivot = this.nums[pivot_index];
        int res_index = left;
        swap(pivot_index,right);
        //把枢值放在最后，遍历前面
        for(int i = left ; i < right ; i++){
            //把小值放在左边
            if(this.nums[i] < pivot){
                swap(i,res_index);
                res_index++;
            }
        }
        swap(res_index,right);
        //最后得到res_index左边的值全小于它，右边的值全大于等于,则此点的值为第res_index个最大元素
        return res_index;
    }
    private void swap(int a, int b){
        int temp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = temp;
    }

/*
//堆
//PriorityQueue实现堆的原理：https://blog.csdn.net/u013309870/article/details/71189189
    public int findKthLargest(int[] nums, int k) {
        //默认小顶堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for(int x : nums){
            minHeap.add(x);
            if(minHeap.size() > k)
                minHeap.poll();
        }
        return minHeap.poll();                                      
    }
*/
}
```