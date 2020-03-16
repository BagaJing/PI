class maxAreaOfIsland{
	    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        if(grid==null||grid.length==0) return res;
        for(int i = 0 ; i < grid.length ;i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                int space = dfs(grid,i,j);
                res = Math.max(res,space);
            }
        }
        return res;
    }
    // bottom to top
    private int dfs(int[][] grid,int row,int col){
        if(row >= grid.length||row<0) return 0;
        if(col >= grid[0].length||col<0) return 0;
        if(grid[row][col]==0||grid[row][col]==-1)return 0;
        grid[row][col] = -1;
        return 1+dfs(grid,row+1,col)+dfs(grid,row,col+1)+dfs(grid,row,col-1)+dfs(grid,row-1,col);
    }
}