# Binary Search Tree(二叉搜索树)
* [二叉搜索树的定义](#二叉搜索树的定义)

基础操作

* [验证二叉搜索树](#验证二叉搜索树)
* [二叉搜索树迭代器](#二叉搜索树迭代器)
* [Search in a Binary Search Tree](#Search in a Binary Search Tree)
* [Insert into a Binary Search Tree](#Insert into a Binary Search Tree)
* [Delete Node in a BST](#Delete Node in a BST)
* [二叉搜索树的最近公共祖先](#二叉搜索树的最近公共祖先)

高度平衡的二叉搜索树

* [平衡二叉树](#平衡二叉树)
* [将有序数组转换为二叉搜索树](# 将有序数组转换为二叉搜索树)

应用

* [Kth Largest Element in a Stream](#Kth Largest Element in a Stream)

<h3>二叉搜索树的定义</h3>
```
二叉搜索树（BST）是二叉树的一种特殊表示形式，它满足如下特性：

1.每个节点中的值必须大于（或等于）存储在其左侧子树中的任何值。
2.每个节点中的值必须小于（或等于）存储在其右子树中的任何值。
```
<h2>基础操作</h2>
<h3 id="验证二叉搜索树">验证二叉搜索树</h3>
```Java
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
 // 二叉搜索树的中序遍历是有序数组
 public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        //inorder traverse
        List<Integer> arr = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while(pNode!=null || !stack.isEmpty()){
            if(pNode != null){
                stack.push(pNode);
                pNode = pNode.left;
            }else{
                pNode = stack.pop();
                arr.add(pNode.val);
                pNode = pNode.right;
            }
        }
        for(int i = 0 ; i < arr.size()-1; i++){
            if(arr.get(i+1) <= arr.get(i)) return false;
        }
        return true;
    }
```
<h3 id="二叉搜索树迭代器">二叉搜索树迭代器</h3>
```Java
实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

调用 next() 将返回二叉搜索树中的下一个最小的数。

示例：

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // 返回 3
iterator.next();    // 返回 7
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 9
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 15
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 20
iterator.hasNext(); // 返回 false
 

提示：

next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
class BSTIterator {
    LinkedList<Integer> queue;
    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                queue.add(cur.val);
                cur = cur.right;
            }
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        return queue.removeFirst();
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return queue.size() > 0 ;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```
<h3 id="Search in a Binary Search Tree">Search in a Binary Search Tree</h3>
```Java
给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。

例如，给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和值: 2
你应该返回如下子树:

      2 
     / \ 
    1   3
在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
    // top to down
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val == val) return root;
        else if(root.val > val) return searchBST(root.left,val);
        else return searchBST(root.right,val);
    }
```
<h3 id="Insert into a Binary Search Tree">Insert into a Binary Search Tree</h3>
```Java
给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

例如, 

给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和 插入的值: 5
你可以返回这个二叉搜索树:

         4
       /   \
      2     7
     / \   /
    1   3 5
或者这个树也是有效的:

         5
       /   \
      2     7
     / \   
    1   3
         \
          4
 class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        top_down(root,val);
        return root;
    }
    public void top_down(TreeNode root, int val){
        if(root == null) return;
        if(root.val == val) return;
        if(root.val > val && root.left == null){
            root.left = new TreeNode(val);
        }else if(root.val > val){
            top_down(root.left,val);
        }
        if(root.val < val && root.right == null){
            root.right = new TreeNode(val);
        } else if(root.val < val){
            top_down(root.right,val);
        }
    }
}
```
<h3 id="Delete Node in a BST">Delete Node in a BST</h3>
```
删除要比我们前面提到过的两种操作复杂许多。有许多不同的删除节点的方法，这篇文章中，我们只讨论一种使整体操作变化最小的方法。我们的方案是用一个合适的子节点来替换要删除的目标节点。根据其子节点的个数，我们需考虑以下三种情况：

1. 如果目标节点没有子节点，我们可以直接移除该目标节点。
2. 如果目标节只有一个子节点，我们可以用其子节点作为替换。
3. 如果目标节点有两个子节点，我们需要用其中序后继节点或者前驱节点来替换，再删除该目标节点。
```
```
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。
说明： 要求算法时间复杂度为 O(h)，h 为树的高度。

示例:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

    5
   / \
  4   6
 /     \
2       7

另一个正确答案是 [5,2,6,null,4,null,7]。

    5
   / \
  2   6
   \   \
    4   7
class Solution {
    private int last(TreeNode root){
        root = root.left;
        while(root.right!=null) root = root.right;
        return root.val;
    }
    private int next(TreeNode root){
        root = root.right;
        while(root.left!=null) root = root.left;
        return root.val;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(key > root.val) root.right = deleteNode(root.right,key);
        else if(key < root.val) root.left = deleteNode(root.left,key);
        else{ // target found
            if(root.left==null&&root.right==null){ 
                root = null;
            }else if(root.left!=null){
                root.val = last(root);
                root.left = deleteNode(root.left,root.val);

            }else{
               root.val = next(root);
               root.right = deleteNode(root.right,root.val);
            }
        }
        return root;
    }
    
}
```
<h3 id="二叉搜索树的最近公共祖先">二叉搜索树的最近公共祖先</h3>
```Java
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
示例 1:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
示例 2:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
        //System.out.println(root.val);
        if(p==null&&q==null) return null;
        if(root == null || root == p || root == q) return root;
        TreeNode right = lowestCommonAncestor(root.right,
                                            p!=null&&p.val>root.val?p:null,
                                            q!=null&&q.val>root.val?q:null);
        TreeNode left = lowestCommonAncestor(root.left,
                                             p!=null&&p.val<root.val?p:null,
                                             q!=null&&q.val<root.val?q:null);
        if(left!=null&&right!=null) return root;
        else if(left!=null) return left;
        else return right;
        */
        if(root.val>p.val && root.val>q.val) return lowestCommonAncestor(root.left, p, q);
        if(root.val<p.val && root.val<q.val) return lowestCommonAncestor(root.right,p,q);
        return root;
    }
}
```
<h2>高度平衡的二叉搜索树</h2>
<h3 id="平衡二叉树">平衡二叉树</h3>
```Java
给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:

给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。
class Solution {
    //top  -> down
    private int height(TreeNode root){
        if(root == null) return 0;
        return 1 + Math.max(height(root.left),height(root.right));
    }
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return Math.abs(height(root.left)-height(root.right)) < 2&&
            isBalanced(root.left)&&
            isBalanced(root.right);
    }
}
```
<h3 id=" 将有序数组转换为二叉搜索树"> 将有序数组转换为二叉搜索树</h3>
```Java
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
 //二分法，每次插入中间值，从而使得搜索树平衡
 class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        int mid = (nums.length-1+0)/2;
        TreeNode root = new TreeNode(nums[mid]);
        iterate(root,nums,0,mid-1);
        iterate(root,nums,mid+1,nums.length-1);
        return root;
    }
    private void iterate(TreeNode root,int[] nums,int start,int end){
        if(start == end) insert(root,nums[start]);
        else if(start < end){
            int mid = (end+start)/2;
            insert(root,nums[mid]);
            iterate(root,nums,start,mid-1);
            iterate(root,nums,mid+1,end);
        }
    }
    private void insert(TreeNode root,int num){
        if(root == null) return;
        if(num < root.val){
            if(root.left == null) root.left = new TreeNode(num);
            else insert(root.left,num);
        }else if(num > root.val){
            if(root.right == null) root.right = new TreeNode(num);
            else insert(root.right,num);
        }
    }
}
```
<h2>应用</h2>
<h3 id="Kth Largest Element in a Stream">Kth Largest Element in a Stream</h3>
```Java
设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。

你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。

示例:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
说明:
你可以假设 nums 的长度≥ k-1 且k ≥ 1。
class KthLargest {
    class CountNode{
        int val;
        CountNode left;
        CountNode right;
        int count;
        public CountNode(int val){
            this.val = val;
            this.count = 1;
        }
    }
    private void insert(CountNode node,int val){
        if(node == null) return;
        if(node.val > val){
            if(node.left == null){
                node.left = new CountNode(val);
            }else{
                insert(node.left,val);
            }
        }else if(node.val < val){
            if(node.right == null){
                node.right = new CountNode(val);
            }else{
                insert(node.right,val);
            }
        }
        node.count++;
    }
    private void iterate(CountNode root,int[] nums,int start,int end){
        if(start == end) insert(root,nums[start]);
        else if(start < end){
            int mid = (end+start)/2;
            insert(root,nums[mid]);
            iterate(root,nums,start,mid-1);
            iterate(root,nums,mid+1,end);
        }
    }
    CountNode root = null;
    int kth;
    public KthLargest(int k, int[] nums) {
        kth = k;
        if(nums.length!=0){
            Arrays.sort(nums);
            int mid = (nums.length-1)/2;
            root = new CountNode(nums[mid]);
            iterate(root,nums,0,mid-1);
            iterate(root,nums,mid+1,nums.length-1);
        }
    }
    
    public int add(int val) {
        if(root == null)
            root = new CountNode(val);
        else
            insert(root,val);
        CountNode result = searchKth(root,kth);
        return result!=null? result.val:null;
    }
    private CountNode searchKth(CountNode node,int k){
        if(node == null) return node;
        int leftCount = node.left!=null? node.left.count : 0;
        int rightCount = node.right!=null? node.right.count : 0;
        int currentNode = node.count - leftCount - rightCount;
        if(k <= rightCount) return searchKth(node.right,k);
        else if(k > rightCount+currentNode) return searchKth(node.left,k-rightCount-currentNode);
        else return node;
    }
}

```