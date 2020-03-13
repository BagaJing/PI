class Solution {
    //leetcode:498
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix==null||matrix.length==0) return new int[0];
        int row_len = matrix.length;
        int col_len = matrix[0].length;
        int i=0;
        int[] res = new int[row_len*col_len];
        int iter = 0;
        while(i < row_len+col_len){
            //向上遍历: 1,3,5,7...
            //确认x，y的初始值
            int x1 = i < row_len? i : row_len-1;
            int y1 = i - x1;
            while(x1>=0&&y1<col_len){
                res[iter] = matrix[x1][y1];
                x1--;
                y1++;
                iter++;
            }
            i++;
            if(i >= row_len+col_len)break;
            //向下遍历： 2,4,6,8...
            int y2 = i < col_len? i : col_len-1;
            int x2 = i - y2;
            while(y2>=0&&x2<row_len){
                res[iter] = matrix[x2][y2];
                y2--;
                x2++;
                iter++;
            }
            i++;
        }
        return res;
    }
}