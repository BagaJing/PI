# Math
[凸多边形](#1)
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