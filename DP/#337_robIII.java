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
    public int rob(TreeNode root) {
        int[] res = robMax(root);
        return Math.max(res[0],res[1]);
        //return robMax_1(root,true);
    }
    //1494ms 5% 暴力递归 每个节点两次,抢递归一次，不抢递归一次 总共递归N的平方次
    public int robMax_1(TreeNode root,boolean canRob){
        if(root == null) return 0;
        int rob = 0, notRob = 0;
        if(canRob)
            rob = robMax(root.left,false)+robMax(root.right,false)+root.val;
        notRob = robMax(root.left,true)+robMax(root.right,true);
        return Math.max(rob,notRob);
    }
    //题解: 1ms 99% 递归式的dp 每个节点递归一次 总共递归N次
    private int[] robMax(TreeNode root){
        int[] res = new int[2];
        if(root==null) return res;
        int[] left = robMax(root.left);
        int[] right = robMax(root.right);
        res[0] = Math.max(left[0],left[1])+Math.max(right[0],right[1]); //不偷,左右子树可以选择偷或不偷
        res[1] = left[0]+right[0]+root.val; //偷 左右子树只能选择不偷
        return res;
    }
}