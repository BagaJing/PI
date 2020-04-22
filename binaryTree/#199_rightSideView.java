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
    /*每次层次遍历取最右侧点*/
    public List<Integer> rightSideView(TreeNode root) {
         List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        LinkedList<TreeNode> level = new LinkedList<>();
        level.addFirst(root);
        while(!level.isEmpty()){
            res.add(level.peekLast().val);
            int size = level.size();
            for(int i = 0 ; i < size ; i++){
                TreeNode node = level.removeFirst();
                if(node.left!=null) level.addLast(node.left);
                if(node.right!=null) level.addLast(node.right);
            }
        }
            return res;
    }

}