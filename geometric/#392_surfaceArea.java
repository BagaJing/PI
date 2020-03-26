class Solution {
    public int surfaceArea(int[][] grid) {
        int len = grid.length, area = 0;
        for(int i = 0 ; i < len ; i++){
            for(int j = 0 ; j < len ; j++){
                int level = grid[i][j];
                if(level > 0){
                    area += 4*level+2; //四周面积+上下面积
                    //减去相邻的重叠面积
                    area -= i>0? Math.min(level,grid[i-1][j])*2:0; 
                    area -= j>0? Math.min(level,grid[i][j-1])*2:0;
                }
            }
        }
        return area;
    }
}