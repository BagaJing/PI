/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class  hasSubTree {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1==null||root2==null){
            return false;
        }
        //从上层到底层筛选
        return isSimilar(root1,root2)||isSimilar(root1.left,root2)||isSimilar(root1.right,root2);
    }
    //满足root2为root1的条件为：
    //你有的我不能没有，我有的你可以没有
    //你的和我的不能不一样
    private static boolean isSimilar(TreeNode root1,TreeNode root2){
        //递归出口
        //你没有
        if(root2 == null){
            return true;
        }
        //你有我没有
        if(root1 == null){
            return false;
        }
        //你和我的不一样
        if(root1.val!=root2.val){
            return false;
        }
        return isSimilar(root1.left,root2.left)&&isSimilar(root1.right,root2.right);
    }
}
