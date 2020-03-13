//leetcode:54
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix==null||matrix.length==0) return new ArrayList<>();
        int col_size = matrix.length,row_size = matrix[0].length;
        int col =0,row=0,range=0;
        List<Integer> list = new ArrayList<>();
        while(list.size()<col_size*row_size){
            list.add(matrix[col][row]);
            //坐标在上边界，往右走
            if(col==range&&row<row_size-range-1) row++;
            //坐标在右边界，往下走
            else if(row==row_size-range-1&&col<col_size-range-1) col++;
            //坐标在下边界，往左走
            else if(col==col_size-range-1&&row>range&&row<=row_size-range-1)row--;
            //坐标在左边界，往上走
            else if(row==range&&col>range+1&&col<=col_size-range-1) col--;
            //坐标到达拐点，进入下一螺旋层
            else if(row==range&&col==range+1){
                range++;
                row = range;
                col = range;
            }
        }
        return list;
    }
}