class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        if(rows < 1) return 0;
        int cols = obstacleGrid[0].length;
        if(cols < 1) return 0;
        int[][] dp = new int[rows][cols];
        dp[rows-1][cols-1] = obstacleGrid[rows-1][cols-1]==0? 1 : 0;
        for(int i = rows-1; i>=0 ; i--){
            for(int j = cols-1; j>=0 ; j--){
                if(obstacleGrid[i][j] == 1) continue;
                if(i<rows-1) dp[i][j] += dp[i+1][j];
                if(j<cols-1) dp[i][j] += dp[i][j+1];
            }
        }
        return dp[0][0];
    }
}