# DFS
* [1 钥匙和房间(DFS)](#1)
* [2 字典序排数(DFS)](#2)
<h3 id="1"> 1. 钥匙和房间(DFS)</h3>
有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。

在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。

最初，除 0 号房间外的其余所有房间都被锁住。

你可以自由地在房间之间来回走动。

如果能进入每个房间返回 true，否则返回 false。

示例 1：

输入: [[1],[2],[3],[]]
输出: true
解释:  
我们从 0 号房间开始，拿到钥匙 1。
之后我们去 1 号房间，拿到钥匙 2。
然后我们去 2 号房间，拿到钥匙 3。
最后我们去了 3 号房间。
由于我们能够进入每个房间，我们返回 true。
示例 2：

输入：[[1,3],[3,0,1],[2],[0]]
输出：false
解释：我们不能进入 2 号房间。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/keys-and-rooms
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
//用栈实现广度优先
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Map<Integer,Boolean> visited = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        visited.put(0,true);
        for(Integer i : rooms.get(0))
            stack.push(i);
        while(!stack.isEmpty()){
            int number = stack.pop();
            visited.put(number,true);
            for(Integer i : rooms.get(number)){
                if(!visited.containsKey(i))
                    stack.push(i);
            }
        }
        return visited.size() == rooms.size();
    }
}
```
<h3 id="2">2.字典序排数</h3>
给定一个整数 n, 返回从 1 到 n 的字典顺序。

例如，

给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。

请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lexicographical-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
//参考：https://leetcode-cn.com/problems/lexicographical-numbers/solution/javajie-fa-by-liu-jia-liang-4/
//DFS
class Solution {
    
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new LinkedList<>();
        if(n == 0) return res;
        for(int i = 1 ; i < 10 ; i++){
            if(i <= n){
                res.add(i);
                addOn(res,n,i);
            }
        }
        return res;
    }
    //递归
    private void addOn(List<Integer> res, int n, int startNum){
        startNum *= 10;
        for(int i = 0 ; i < 10 ; i++,startNum++){
            if(startNum <= n){
                res.add(startNum);
                addOn(res,n,startNum);
            } else return;
        }
    }
}