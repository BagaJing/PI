class Solution {
    public int numIslands(char[][] grid) {
        int len1 = grid.length;
        if(len1 == 0) return 0;
        int len2 = grid[0].length;
        if(len2==0) return 0;
        int res = 0;
        for(int i = 0; i < len1 ; i++){
            for(int j = 0; j < len2 ; j++){
                if(grid[i][j] == '1'){
                    res += 1;
                    dfs(grid,i,j);
                } 
            }
        }
        return res;
    }
    private void dfs(char[][] grid,int i, int j){
        if(i<0||i >= grid.length||j<0||j>=grid[0].length||grid[i][j]=='0'||grid[i][j]=='2') return;
        if(grid[i][j] == '1'){
            grid[i][j] = '2';
            dfs(grid,i+1,j);
            dfs(grid,i-1,j);
            dfs(grid,i,j+1);
            dfs(grid,i,j-1);
        }
    }
}