[二叉树的最近公共祖先](#1)

[从前序与中序遍历序列构造二叉树](#2)

[从中序与后序遍历序列构造二叉树](#3)

#### * 遍历
[二叉树的层级遍历（BFS）](#4)
<h3 id="1">二叉树的最近公共祖先</h3>
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

![lc](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/binarytree.png)

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]



 

示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
示例 2:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //递归分治
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //出口
        if(root == null) return root;
        if(root == p||root == q) return root;
        //分
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        //治
        TreeNode res = null;
        if(left!=null&&right!=null) res = root;
        else if(left!=null) res = left;
        else if(right!=null) res = right;
        
        return res;
    }
}
```
<h3 id = "2">从前序与中序遍历序列构造二叉树</h3>
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

[不错的题解](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--22/)


```
    3
   / \
  9  20
    /  \
   15   7
 ```
```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //递归分治
    //18ms
    /*
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0||inorder.length==0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        int mid = 0;
        for(int i = 0 ; i < inorder.length ; i++){
            if(preorder[0] == inorder[i]){
                mid = i;
                break;
            }
        }
        root.left = buildTree(Arrays.copyOfRange(preorder,1,1+mid),Arrays.copyOfRange(inorder,0,mid));
        root.right = buildTree(Arrays.copyOfRange(preorder,1+mid,preorder.length),Arrays.copyOfRange(inorder,mid+1,inorder.length));
        return root;
    }
    */
    //上个方法每次递归回需切分数组，增加了时间
    //优化版本，不做切分，只传指针 加上哈希表来避免每次遍历inorder数组
    //3ms
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < inorder.length ; i++)
            map.put(inorder[i],i);
        return build(preorder,0,preorder.length,inorder,0,inorder.length,map);
    }
    private TreeNode build(int[] preorder,int pre_start,int pre_end,int[] inorder,int in_start,int in_end,Map<Integer,Integer> map){
        if(pre_end-pre_start<=0||in_end-in_start<=0) return null; //出口
        TreeNode root = new TreeNode(preorder[pre_start]);
        int mid = map.get(preorder[pre_start]);
        int range = mid - in_start;
        root.left = build(preorder,pre_start+1,pre_start+range+1,inorder,in_start,mid,map);
        root.right = build(preorder,pre_start+1+range,pre_end,inorder,mid+1,in_end,map);
        return root;
    }
}
```
<h3 id = "3">从中序与后序遍历序列构造二叉树</h3>
根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //和上道题一样的做法
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < inorder.length ; i++)
            map.put(inorder[i],i);
        return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1,map);
    }
    private TreeNode build(int[] inorder,int in_start,int in_end,int[] postorder,int pos_start,int pos_end,Map<Integer,Integer> map){
        if(in_end - in_start < 0 || pos_end - pos_start < 0) return null; //exit
        TreeNode root = new TreeNode(postorder[pos_end]);
        int mid = map.get(postorder[pos_end]);
        int range = in_end - mid;
        root.right = build(inorder,mid+1,in_end,postorder,pos_end-range,pos_end-1,map);
        root.left = build(inorder,in_start,mid-1,postorder,pos_start,pos_end-range-1,map);
        return root;
    }
}
```

<h3 id = "4">二叉树的层级遍历</h3>

[题目链接](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/submissions/)

```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) return res;
        //双队列 轮换存储下一层的节点
        Deque<TreeNode> stack_1 = new LinkedList<>();
        List<Integer> unit = new LinkedList<>();
        Deque<TreeNode> stack_2 = new LinkedList<>();
        boolean isFirst = true;
        stack_1.push(root);
        while(!stack_1.isEmpty()||!stack_2.isEmpty()){
            if(isFirst){
                while(!stack_1.isEmpty()){
                    TreeNode node = stack_1.removeLast();
                    unit.add(node.val);
                    if(node.left!=null) stack_2.addFirst(node.left);
                    if(node.right!=null) stack_2.addFirst(node.right);
                }
                isFirst = false;
            }else{
                    while(!stack_2.isEmpty()){
                    TreeNode node = stack_2.removeLast();
                    unit.add(node.val);
                    if(node.left!=null) stack_1.addFirst(node.left);
                    if(node.right!=null) stack_1.addFirst(node.right);
                    }
                isFirst = true;
            }
            res.add(new LinkedList<>(unit));
            unit.clear();
        }
        return res;
    }
}
```