public class bst {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0){
            return false;
        }
        return isBST(sequence, 0, sequence.length - 1);
    }
    
    //递归法
    /*二叉搜索树满足：
        1.左子树　< 根　< 右子树
        ２．所有根树也是二叉搜索树
     后序遍历的顺序是　左子树　右子树　根
         则一个序列中　根节点　必在最后
     用此特点不断划分　左右子树
     利用　右子树　大于　根　
         且所有根树都是二叉搜索树
         的特点来判断
   */
    private boolean isBST(int[] seq, int start, int end){
        if(start >= end) return true;
        int inVal = seq[end];
        int split = start;
        for(;split < end && seq[split] < inVal ; split++);
        for(int i = split ; i < end ; i++){
            //右边点　应该大于　根节点
            if(seq[i] < inVal) return false;
        }
        
        return isBST(seq,start,split -1)&&isBST(seq,split,end-1);
    }
}
