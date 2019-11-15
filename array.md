# Array
###  Index
[使数组唯一的最小增量](#1)

[寻找重复数](#5)
#### * 回溯
[组合总和](#2)

#### * 堆
[前k个高频元素](#3)

[数组中的第K个最大元素](#4)
***
<h3 id="1">1.使数组唯一的最小增量</h3>

Monday, 04. November 2019 08:20PM 

给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。

返回使 A 中的每个值都是唯一的最少操作次数。

示例 1:

输入：[1,2,2]

输出：1

解释：经过一次 move 操作，数组将变为 [1, 2, 3]。

示例 2:

输入：[3,2,1,2,1,7]

输出：6

解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。

可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。

提示：

0 <= A.length <= 40000

0 <= A[i] < 40000


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    //排序插入
    public int minIncrementForUnique(int[] A){
        //排序后，相等的数字相邻
        Arrays.sort(A);
        int res = 0, taken = 0;
        for(int i = 1; i < A.length ; i++){
            if(A[i] == A[i-1]){
                taken++;
                //先减去A[i]的值
                res -= A[i];
            }else{
                int give = Math.min(taken,A[i]-A[i-1]-1);
                //求和公式
                //此步补上了减去的A[i]的值,差值为+1的次数，这样就不用暴力地逐次去加，第二种方法也用了这种优化
                res += give*(give+1)/2+A[i-1]*give;
                taken -=give;
            }
        }
        //当taken 为 0， 说明重复的数恰好全部插入到数组中两个不相等的数中间了，此一步变化为0
        //当taken > 0. 说明数组中的间隔区间不足以让所有的数都插入，则需要补上从最大数开始剩下的数之和
        //taken < 0的状况不存在，因为每次只去 taken和A[i]-A[i-1]-1中较小的 所以taken>=0
        if(A.length > 0){
            System.out.println(taken);
            res += taken*(taken+1)/2 + A[A.length-1]*taken;
        }
        return res;
    }
    //计数法
    
    public int minIncrementForUnique(int[] A){
    
        //用一个大数组记录A中所有数的次数 大数组的索引代表A[i]
        //假设A[i]中每个数是39999，则最坏情况需要39999个比39999大的数，从而40000无法跑过最坏情况，此时需要大数组长度为80000
        //遍历大数组，当碰到一个A中没有的数时，加上此数，补全A[i]的同时，差值是+1的次数
        int[] count = new int[50000];
        for(int x : A) count[x]++;
        int res =0, taken = 0;
        for(int x = 0; x < 50000; x++){
            if(count[x] >= 2){
                taken += count[x]-1;
                res -= x*(count[x]-1);
            }else if(taken>0&&count[x]==0){
                taken --;
                res += x;
            }
        }
        return res;
    }
    
    //哈希表做法 大用例超时
    /*
    public int minIncrementForUnique(int[] A) {
        Map<Integer,Integer> records = new HashMap<>();
        //O(n)
        for(int i = 0 ; i < A.length ; i++){
            if(records.containsKey(A[i])){
                int val = records.get(A[i]);
                val+=1;
                records.put(A[i],val);
            }else{
                records.put(A[i],1);
            }
        }
        int res = 0;
        //O(n2)
        for(int i = 0 ; i < A.length ; i++){
            if(records.get(A[i]) > 1){
                int times = 0;
                int temp= A[i];
                while(records.containsKey(A[i])){
                    A[i]++;
                    times++;
                }
                records.put(A[i],1);
                int val = records.get(temp);
                val -= 1;
                records.put(temp,val);
                res += times;
            }
        }
        return res;
    }
    */
}
```
<h3 id="5">寻找重复数</h3>
[题目链接](https://leetcode-cn.com/problems/find-the-duplicate-number/)
```Java
class Solution {
    //tricky
    public int findDuplicate(int[] nums){
        int rabit = nums[0];
        int tortoise = nums[0];
        do{
            tortoise = nums[tortoise];
            rabit = nums[nums[rabit]];
           // System.out.println("tortoise "+tortoise+" rabbit "+rabit);
        }while(rabit!=tortoise);
        
        
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while(ptr1 != ptr2){
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
        
    }
    // space: O(n) time:O(n)
    /*
    //集合
    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int[] seq = new int[n+1];
        for(int x : nums){
            seq[x] += 1;
            if(seq[x] >= 2 ) return x;
        }
        return -1;
    }
    */
}
```
<h3 id = "2">组合总和</h3>
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]
示例 2:

输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> unit = new ArrayList<>();
        rollBack(res,unit,candidates,0,target,0);
        return res;
    }

    private void rollBack(List<List<Integer>> res,
                        List<Integer> unit,
                        int[] candidates,
                        int index,
                        int target,
                        int stat)
        {
            if(stat > target) return; //错误的尝试，直接返回
            if(stat == target){
                res.add(new ArrayList<>(unit)); //符合，加入结果 记住深拷贝
                //res.clear();
                return;
            }
            for(int i = index ; i < candidates.length ; i++){
                stat += candidates[i];
                unit.add(candidates[i]);
                //因为可以含有重复数字，所以i不加1
                rollBack(res,unit,candidates,i,target,stat);
                //回溯
                stat -= candidates[i];
                unit.remove(unit.size()-1);
            }
        }

}
```
<h3 id = "3">前k个高频元素</h3>
给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
说明：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/top-k-frequent-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
import javafx.util.Pair;
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        //hash
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            int count = map.getOrDefault(nums[i],0);
            map.put(nums[i],count+1);
        }
        //heap 重载compare函数, 根据Pair中value的大小构建小顶堆
        PriorityQueue<Pair<Integer,Integer>> heap = new PriorityQueue<Pair<Integer,Integer>>(new Comparator<Pair<Integer,Integer>>(){
            @Override
            public int compare(Pair<Integer,Integer> o1,Pair<Integer,Integer> o2){
                return o1.getValue() - o2.getValue();
            }
        });
        Set<Integer> set = map.keySet();
        for(Integer i : set){
            heap.add(new Pair<Integer,Integer>(i,map.get(i)));
            //长度大于k时弹出根节点,根为value最小的元素
            if(heap.size() > k) heap.poll();
        }
        //list
        List<Integer> res = new LinkedList<>();
        for(Pair<Integer,Integer> pair: heap){
            res.add(pair.getKey());
        }
        return res;
    }
}
```
<h3 id ="4">数组中的第K个最大元素</h3>
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