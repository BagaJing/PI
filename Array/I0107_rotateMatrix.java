class Solution {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        //对角线置换
        for(int i = 0 ; i < len ; i++){
            for(int j = i+1 ; j < len ; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        //各行中点置换
        int mid = len/2;
        for(int i = 0 ; i < len ; i++){
            for(int j = 0 ; j < mid ; j++){
                int tmp = matrix[i][j];
                 matrix[i][j] = matrix[i][len-j-1];
                 matrix[i][len-j-1] = tmp;
            }
        }
    }
}