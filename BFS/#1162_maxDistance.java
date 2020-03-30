class Solution {
    public int maxDistance(int[][] grid) {
        Queue<int[]> arriveQueue = new LinkedList<>();
        for(int i = 0; i < grid.length ; i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j]==1)
                    arriveQueue.offer(new int[]{i,j});
            }
        }
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        boolean hasOcean = false;
        int[] pos = null; 
        //BFS:从各个岛屿出发,最后到达的海洋位置肯定为最远的位置
        //并且肯定是从最近岛屿出发的船只最先到到 
        while(!arriveQueue.isEmpty()){
            pos = arriveQueue.poll();
            int posX = pos[0],posY = pos[1];
            for(int i = 0 ; i < 4 ; i++){
                int newX = posX + dx[i];
                int newY = posY + dy[i];
                if(newX<0||newY<0||newX >= grid.length||newY>=grid[0].length||grid[newX][newY]!=0)continue;
                hasOcean = true;
                grid[newX][newY] = grid[posX][posY]+1;
                arriveQueue.offer(new int[]{newX,newY});
            }
            
        }
        if(pos==null||!hasOcean) return -1;
        return grid[pos[0]][pos[1]]-1;
    }
}