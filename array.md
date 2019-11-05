# Array
###  Index
[使数组唯一的最小增量](#1)
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