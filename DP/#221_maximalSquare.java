class Solution {
    public int maximalSquare(char[][] matrix) {
        int row_len = matrix.length;
        if(row_len==0) return 0;
        int col_len = matrix[0].length;
        if(col_len==0) return 0;
        //取四个点中的右下点来记录最大正方形的边长,dp数组扩大一圈数组出界
        int[][] space = new int[row_len+1][col_len+1];
        int res = 0;

        for(int i = 1 ; i <= row_len ; i++){
            for(int j = 1 ; j <= col_len ; j++){
                //根据目前的情况，和之前的情况选出最优解 自底向上
                if(matrix[i-1][j-1] == '1'){
                    //把值为1的点当做一个边长为1的正方形, 因此一个正方形的三个点必须都为边长相等的正方形
                    //则取三个点中的最小值则一定能构成一个边长为n+1（n>=0）的正方形
                    space[i][j] = Math.min(space[i-1][j-1],Math.min(space[i-1][j],space[i][j-1]))+1;
                    res = Math.max(res,space[i][j]);
                }
            }
        }
        return res*res;
    }
}