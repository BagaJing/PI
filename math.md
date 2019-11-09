# Math
[凸多边形](#1)
[重新排列得到2的幂](#2)
<h3 id="1">凸多边形</h3>
给定一个按顺序连接的多边形的顶点，判断该多边形是否为凸多边形。（凸多边形的定义）

注：

顶点个数至少为 3 个且不超过 10,000。
坐标范围为 -10,000 到 10,000。
你可以假定给定的点形成的多边形均为简单多边形（简单多边形的定义）。换句话说，保证每个顶点处恰好是两条边的汇合点，并且这些边 互不相交 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convex-polygon
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
[详解](https://juejin.im/post/5989c8996fb9a03c524591b2)
```Java
class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        int LEN = points.size();
        int x1 = 0, x2 =0, y1=0, y2=0;
        long cur = 0, pre = 0;
        for(int i = 0 ; i < LEN ; i++){
            x1 = points.get((i+1)%LEN).get(0) - points.get(i).get(0);
            x2 = points.get((i+2)%LEN).get(0) - points.get(i).get(0);
            y1 = points.get((i+1)%LEN).get(1) - points.get(i).get(1);
            y2 = points.get((i+2)%LEN).get(1) - points.get(i).get(1);
            cur = x1*y2 - x2*y1;
            if(cur!=0&&cur*pre <0) return false;
            if(cur!=0) pre = cur;
        }
        return true;
    }
}
```
<h3 id="2">重新排列得到2的幂</h3>
给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。

如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。

 

示例 1：

输入：1
输出：true
示例 2：

输入：10
输出：false
示例 3：

输入：16
输出：true
示例 4：

输入：24
输出：false
示例 5：

输入：46
输出：true
 

提示：

1 <= N <= 10^9

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reordered-power-of-2
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

```Java
class Solution {
    //全排列
    public boolean reorderedPowerOf2(int N) {
        String s = Integer.toString(N);
        int[] A = new int[s.length()];
        for(int i = 0 ; i < A.length ; i++)
            A[i] = s.charAt(i) - '0';
        return permute(A,0);
    }
    private static boolean permute(int[] A, int start){
        if(start == A.length)
            return isPowerof2(A);
        for(int i = start; i < A.length ; i++){
            swap(A,i,start);
            if(permute(A,start+1))
                return true;
            swap(A,i,start);
        }
        return false;
    }
    private static void swap(int[] A,int i,int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    private static boolean isPowerof2(int[] A){
        if(A[0] == 0)
            return false;
        int num = 0;
        for(int x :A)
            num = num*10 + x;
        while(num>0&&((num&1)==0))
            num >>= 1;
        System.out.println(num);
        return num == 1;
    }
}
```