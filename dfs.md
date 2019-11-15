# DFS
* [1 钥匙和房间(DFS)](#1)
* [2 字典序排数(DFS)](#2)
* [字符串解码(栈)](#3)
* [岛屿数量](#4)
#### * 单词搜索
[单词搜索](#5)
[单词搜索 II](#6)
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
```
<h3 id="3">字符串解码</h3>
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

示例:

s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/decode-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
class Solution {
    
    public String decodeString(String s) {
        char[] arrs = s.toCharArray();
        StringBuilder str = new StringBuilder();
        String res = "";
        //用来获取每个重复次数的的temp变量
        int num = 0;
        //用来保存外层的重复次数，每次弹出最里层数字
        Stack<Integer> counts = new Stack<>();
        //用来保存外层的字符串，当碰到新数字时被推入，当碰到']'时被弹出来连接括号里的字符串
        Stack<String> subs = new Stack<>();
        for(int i = 0 ; i < arrs.length; i++){
            if(Character.isDigit(arrs[i])){
                if(arrs[i+1]=='['){
                num = num*10 + Character.getNumericValue(arrs[i]);
                counts.push(num);
                num = 0;
                subs.push(str.toString());
                //清空str来记录下一层括号里的内容
                str.delete(0,str.length());
                i++; // 跳过‘[’
                } else{
                    num = num*10 + Character.getNumericValue(arrs[i]);
                }
            } else if(arrs[i] == ']'){
                String temp = str.toString();
                str.delete(0,str.length());
                int count = counts.pop();
                //先连接栈顶字符串
                str.append(subs.pop());
                //再连接括号内的字符串
                for(int j = 0 ; j < count ; j++)
                    str.append(temp);
            } else{
                str.append(arrs[i]);
            }
        }
        return str.toString();
    }
}
```
<h3 id="4">岛屿数量</h3>
给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

示例 1:

输入:
11110
11010
11000
00000

输出: 1
示例 2:

输入:
11000
11000
00100
00011

输出: 3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-islands
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
//线性遍历，碰到1就开始向四周深度搜索，把访问过的1变成0 复杂度O(n2)
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        int len1 = grid.length;
        if(len1 == 0) return 0;
        int len2 = grid[0].length;
        if(len2 == 0) return 0;
        for(int i = 0; i < len1 ; i++){
            for(int j = 0 ; j < len2 ; j++){
                if(grid[i][j] == '1'){
                    res+=1;
                    dfs(grid,i-1,j);
                    dfs(grid,i+1,j);
                    dfs(grid,i,j+1);
                    dfs(grid,i,j-1);
                }
            }
        }
        return res;
    }
    private void dfs(char[][] grid,int i,int j){
        if(i<0||i>grid.length-1||j<0||j>grid[0].length-1) return;
        if(grid[i][j] == '0') return;
        if(grid[i][j] == '1'){
            grid[i][j] = '0';
            dfs(grid,i-1,j);
            dfs(grid,i+1,j);
            dfs(grid,i,j+1);
            dfs(grid,i,j-1);
        }
    }
}
```
<h3 id = "5">单词搜素</h3>
[题目链接](https://leetcode-cn.com/problems/word-search/)
```Java
class Solution {
    // 深度优先 + 回溯
    public boolean exist(char[][] board, String word) {
        int len1 = board.length;
        if(len1 == 0) return false;
        int len2 = board[0].length;
        if(len2 == 0) return false;
        boolean res = false;
        for(int i = 0 ; i < len1 ; i++){
            for(int j = 0; j < len2 ; j++){
                //找到单词的头，然后进行深度优先搜索
                if(board[i][j] == word.charAt(0)){
                    res = res||dfs(board,word,0,i,j);
                }
            }
        }
        return res;
    }
    private boolean dfs(char[][] board,String word,int index,int i,int j){
        //出口
        if(i > board.length-1|| i < 0 || j > board[0].length-1||j <0||index>=word.length()) return false;
        if(board[i][j] == '#') return false;
        if(board[i][j] != word.charAt(index)) return false;
        if(index == word.length()-1) return true;
        //mark走过的路径
        char tmp = board[i][j];
        board[i][j] = '#';
        //dfs
        boolean res = dfs(board,word,index+1,i+1,j)||
            dfs(board,word,index+1,i-1,j)||
            dfs(board,word,index+1,i,j+1)||
            dfs(board,word,index+1,i,j-1);
        //还原
        board[i][j] = tmp;
        return res;
    }
}
```