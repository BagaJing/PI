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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode root = null;
        if(t1!=null) root = t1;
        else if(t2!=null) root = t2;
        else return root;
        root.val = (t1!=null? t1.val :0)+(t2!=null? t2.val:0);
        root.left= mergeTrees(t1!=null? t1.left:null,t2!=null?t2.left:null);
        root.right= mergeTrees(t1!=null? t1.right:null,t2!=null?t2.right:null);
        return root;
    }
}