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
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new LinkedList<>();
        if(n==0)return res;
        res.add(new TreeNode(1));
        for(int i = 2 ; i <= n ; i++){ //新的节点肯定是目前最大节点，所以处理方法有:
                                        // 1.让新节点作为root，把i-1的数作为左子树
                                        // 2.把节点i插入i-1的数的右子树中，插入点的子树，作为新节点的左子树
            int len = res.size();
            while(len > 0){
                TreeNode node = res.remove(0);
                //把之前的点作为左子树
                TreeNode newHead = new TreeNode(i);
                newHead.left = node;
                res.add(res.size(),newHead);
                //把新点作为右侧节点插入原树，需要先进行复制
                TreeNode iter = node;
                TreeNode iter_clone = null;
                do{
                    TreeNode clone = cloneTree(node);
                    iter_clone = clone;
                    while(iter_clone.val!=iter.val) iter_clone = iter_clone.right;
                    TreeNode temp = iter_clone.right;
                    iter_clone.right = new TreeNode(i);
                    iter_clone.right.left = temp;
                    res.add(res.size()-1,clone);
                    iter = iter.right;
                }while(iter!=null);
                len--;
            }
        }
        return res;
    }
    private TreeNode cloneTree(TreeNode root){
        if(root == null) return null;
        TreeNode res = new TreeNode(root.val);
        res.left = cloneTree(root.left);
        res.right = cloneTree(root.right);
        return res;
    }
}