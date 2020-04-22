class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[0].length ; j++){
                if(matrix[i][j] == 0){
                    queue.offer(new int[]{i,j});
                }else{
                    matrix[i][j] = -1; // not visited
                }
            }
        }
        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};
        int posX, posY;
        int newX, newY;
        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            posX = pos[0];
            posY = pos[1];
            for(int i = 0; i < 4 ; i++){
                newX = posX + dx[i];
                newY = posY + dy[i];
                if(newX>=0&&newX<matrix.length&&newY>=0&&newY<matrix[0].length&&matrix[newX][newY]==-1){
                    matrix[newX][newY] = matrix[posX][posY]+1;
                    queue.offer(new int[]{newX,newY});
                }
            }
        }
        return matrix;
    }
    //dp做法
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                matrix[i][j] = matrix[i][j]==0? 0 : m*n;
            }
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0; j < n ; j++){
                if(i-1>=0)
                    matrix[i][j] = Math.min(matrix[i][j],matrix[i-1][j]+1);
                if(j-1>=0)
                    matrix[i][j] = Math.min(matrix[i][j],matrix[i][j-1]+1);
            }
        }
        for(int i = m-1; i >=0 ; i--){
            for(int j = n-1; j>=0; j--){
                if(i+1<m)
                    matrix[i][j] = Math.min(matrix[i][j],matrix[i+1][j]+1);
                if(j+1<n)
                    matrix[i][j] = Math.min(matrix[i][j],matrix[i][j+1]+1);
            }
        }
        return matrix;
    }
}