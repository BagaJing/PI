# Binary Tree
* [树的遍历](#树的遍历)
* [树的层次遍历](#树的层次遍历)
* [填充每个节点的下一个右侧节点指针 II](#填充每个节点的下一个右侧节点指针 II)
* [递归思想： "自顶向下"与“自底向上”](#自顶向下与自底向上)
* [二叉树的最大深度](#二叉树的最大深度)
* [对称二叉树](#对称二叉树)
* [路径总和](#路径总和)
* [二叉树的最近公共祖先](#二叉树的最近公共祖先)
* [从中序与后序遍历序列构造二叉树](#从中序与后序遍历序列构造二叉树)
* [从前序与中序遍历序列构造二叉树](#从前序与中序遍历序列构造二叉树)
* [二叉树的序列化与反序列化](#二叉树的序列化与反序列化)

<h3 id = "树的遍历">树的遍历</h3>
```Java
//前序遍历
//迭代
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        TreeNode pNode = root;
        Stack<TreeNode> stack = new Stack<>();
        while(pNode != null || !stack.isEmpty()){
            if(pNode != null){
                res.add(pNode.val);
                stack.push(pNode);
                pNode = pNode.left;
            }else{
                pNode = stack.pop();
                pNode = pNode.right;
            }
        }
        return res;
    }
    public void preorderTraversal(TreeNode root){
    	if(root != null){
    		do(root.val);
    		preorderTraversal(root.left);
    		preorderTraversal(root.right);
    	}
    }
 //中序遍历
 class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while(pNode != null || !stack.isEmpty()){
            if(pNode!=null){
                stack.push(pNode);
                pNode = pNode.left;
            }else{
                pNode = stack.pop();
                res.add(pNode.val);
                pNode = pNode.right;
            }
        }
        return res;
    }
}
//递归
	public void inorderTraversal(TreeNode root){
		inorderTraversal(root.left);
		dosome(root.val);
		inorderTraversal(root.right);
	}
//后序遍历
//迭代
    public List<Integer> postorderTraversal(TreeNode root){
        if(root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = root;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.peek();
            if(
                (cur.right==null&&cur.left==null)|| //节点为末端时写入
                (pre!=null&&(pre == cur.left||pre==cur.right)) //节点为上个节点的父节点时写入
              ){
                  res.add(cur.val);
                  pre = cur;
                  stack.pop();
              }else{
                    //入栈顺序 父 右 左
                    //出栈顺序 左 右 父
                  if(cur.right!=null) stack.add(cur.right); 
                  if(cur.left!=null) stack.add(cur.left);
              }
        }
        return res;
//递归
	public void postorderTraversal(TreeNode root){
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		dosome(root.val);
	}
```
<h3 id="树的层次遍历">树的层次遍历</h3>
```java
//双栈法
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        LinkedList<TreeNode> stack_1 = new LinkedList<>();
        LinkedList<TreeNode> stack_2 = new LinkedList<>();
        stack_1.addFirst(root);
        boolean turn = true;
        while(!stack_1.isEmpty() || !stack_2.isEmpty()){
            LinkedList<TreeNode> out_stack = turn? stack_1 : stack_2;
            LinkedList<TreeNode> in_stack = turn? stack_2 : stack_1;
            List<Integer> list = new ArrayList<>();
            while(!out_stack.isEmpty()){
                TreeNode node = out_stack.removeLast();
                list.add(node.val);
                if(node.left != null) in_stack.addFirst(node.left);
                if(node.right != null) in_stack.addFirst(node.right);
            }
           // Collections.reverse(list);
            res.add(list);
            turn = !turn;
        }
        return res;
    }
```
<h3 id = "填充每个节点的下一个右侧节点指针 II">填充每个节点的下一个右侧节点指针 II</h3>
```Java
leetcode code : 117
给定一个二叉树

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。
//层级遍历
    public Node connect(Node root) {
        if(root == null) return root;
        LinkedList<Node> stack_1 = new LinkedList<>();
        LinkedList<Node> stack_2 = new LinkedList<>();
        stack_1.push(root);
        boolean turn = true;
        while(!stack_1.isEmpty() || !stack_2.isEmpty()){
            LinkedList<Node> out_stack = turn? stack_1 : stack_2;
            LinkedList<Node> in_stack = turn? stack_2 : stack_1;
            Node cur = null;
            while(!out_stack.isEmpty()){
                if(cur == null) {
                    cur = out_stack.removeLast();
                } else {
                    cur.next = out_stack.removeLast();
                    cur = cur.next;
                }
                if(cur.left != null)in_stack.addFirst(cur.left);
                if(cur.right != null)in_stack.addFirst(cur.right);
            }
            cur.next = null;
            turn = !turn;
        }
        return root;
    }
```
<h3 id="自顶向下与自底向上">"自顶向下"与“自底向上”</h3>
当遇到树问题时，请先思考一下两个问题：

你能确定一些参数，从该节点自身解决出发寻找答案吗？
你可以使用这些参数和节点本身的值来决定什么应该是传递给它子节点的参数吗？
如果答案都是肯定的，那么请尝试使用 “自顶向下” 的递归来解决此问题。

或者你可以这样思考：对于树中的任意一个节点，如果你知道它子节点的答案，你能计算出该节点的答案吗？ 如果答案是肯定的，那么 “自底向上” 的递归可能是一个不错的解决方法。

在接下来的章节中，我们将提供几个经典例题，以帮助你更好地理解树的结构和递归。
<h3 id="二叉树的最大深度">104.二叉树的最大深度</h3>
```
leetcode code : 104
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
 ```
返回它的最大深度 3 。
```Java
//递归 down to top
    // down_top
    public int maxDepth(TreeNode root){
        return down_top(root);
    }
    public int down_top(TreeNode root){
        if(root == null) return 0;
        int left = down_top(root.left);
        int right = down_top(root.right);
        return Math.max(left,right)+1;
    }
```
<h3 id="对称二叉树">对称二叉树</h3>
```Java
leetcode code : 101
给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3
 //递归 top to down
     public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return top_down(root.left,root.right);
    }
    public boolean top_down(TreeNode root1,TreeNode root2){
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        //my left is your right
        return root1.val == root2.val && 
            top_down(root1.left,root2.right)&&
            top_down(root1.right,root2.left);
    }
```
<h3 id="路径总和">路径总和</h3>
```Java
leetcode code : 112
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

示例: 
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
    // down to top
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && (sum-root.val)==0) return true;
        return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);
    }
```
<h3 id="二叉树的最近公共祖先">二叉树的最近公共祖先</h3>
```Java
leetcode code : 236
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
示例 2:
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
    //down -> top
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == q || root == p) return root;
        //divide
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        //conquer
        TreeNode res = null;
        if(left!=null && right!=null) res = root;
        else if(left!=null) res = left;
        else if(right!=null) res = right;
        return res;
    }
```
<h3 id="从中序与后序遍历序列构造二叉树">从中序与后序遍历序列构造二叉树</h3>
```Java
根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
 // down to top
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = inorder.length;
        for(int i = 0 ; i < inorder.length ; i++)
            map.put(inorder[i],i);
        return down_top(inorder,0,len-1,postorder,0,len-1,map);
    }
    private TreeNode down_top(int[] inorder,int in_start,int in_end,
                     int[] postorder, int post_start,int post_end,
                     Map<Integer,Integer> map){
        if(post_end - post_start <0 || in_end - in_start < 0) return null;
       // System.out.println(postorder[post_end]);
        TreeNode node = new TreeNode(postorder[post_end]);
        int mid = map.get(node.val);
        int range = in_end - mid;
        node.left = down_top(inorder,in_start,mid-1,postorder,post_start,post_end-range-1,map);
        node.right = down_top(inorder,mid+1,in_end,postorder,post_end-range,post_end-1,map);
        return node;
    }
```
<h3 id="从前序与中序遍历序列构造二叉树">从前序与中序遍历序列构造二叉树</h3>
```Java
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = inorder.length;
        for(int i = 0 ; i < len ; i++)
            map.put(inorder[i],i);
        return down_top(inorder,0,len-1,preorder,0,len-1,map);
    }
    public TreeNode down_top(int[] inorder,int in_start,int in_end,
                             int[] preorder,int pre_start,int pre_end,
                             Map<Integer,Integer> map){
        if(in_start - in_end > 0 || pre_start - pre_end > 0) return null; //exit
        TreeNode node = new TreeNode(preorder[pre_start]);
        int mid = map.get(node.val);
        int range = mid - in_start;
        node.left = down_top(inorder,in_start,mid-1,preorder,pre_start+1,pre_start+range,map);
        node.right = down_top(inorder,mid+1,in_end,preorder,pre_start+range+1,pre_end,map);
        return node;
    }
```
<h3 id="二叉树的序列化与反序列化">二叉树的序列化与反序列化</h3>
```Java
leetcode code:297
//前序
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        TreeNode pNode = root;
        Stack<TreeNode> stack = new Stack<>();
        while(pNode != null || !stack.isEmpty() ){
            if(pNode != null){
                sb.append(String.valueOf(pNode.val)+",");
                stack.push(pNode);
                pNode = pNode.left;
            }else{
                sb.append("#,");
                pNode = stack.pop();
                pNode = pNode.right;
            }
        }
        String res = sb.toString();
        return res.substring(0,res.length()-1);
    }
   //递归写法
       // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        return down_top(root);
    }
    public String down_top(TreeNode root){
        if(root == null) return "#";
        String s = String.valueOf(root.val);
        s = s +","+down_top(root.left)+","+down_top(root.right);
        return s;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")) return null;
        data = data+",#";
        System.out.println(data);
        String[] arr = data.split(",");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(arr));
        return top_down(list);
    }
    private TreeNode top_down(LinkedList<String> l){
       // System.out.println(l);
        if(l.size() == 0) return null;
        if(l.get(0).equals("#")){
            l.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        node.left = top_down(l);
        node.right = top_down(l);
        //System.out.println(node.val);
        return node;
    }
```