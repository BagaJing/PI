/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;
public class reconstructTree {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        //递归出口
        if(pre.length == 0 || in.length == 0){
            return null;
        }
        //前序第一个元素为根节点
        TreeNode head = new TreeNode(pre[0]);
        for(int i = 0; i < in.length ; i++){
            //找到中序数组中根节点的坐标
            if(in[i] == pre[0]){
                //左子树
                head.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
                //右子数
                head.right = reConstructBinaryTree(Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
                break;
            }
        }
        return head;
        
    }
}