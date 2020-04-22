class Solution {
    //bfs
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 1;
        Queue<int[]> queue= new LinkedList<int[]>();
        queue.add(new int[] { 0, 0, 0, 0 });
        while(queue.size() > 0) {
            int[] x = queue.poll();  
            int newX = x[0],newY = x[1];
            int sum_x = (newX+1)%10!=0? x[2]+1 : x[2]-8;
            if(k>=sum_x+x[3]){
                if( newX+1 < 0 || newX+1 >= m || newY < 0 || newY >= n || visited[newX+1][newY]) {
                    
                }
                else{
                    visited[newX+1][newY] = true;
                    res++;
                    queue.offer(new int[]{newX+1,newY,sum_x,x[3]});
                    }

            }
            int sum_y = (newY+1)%10!=0? x[3]+1 : x[3]-8;
            if(k>=x[2]+sum_y){
                if( newX < 0 || newX >= m || newY+1 < 0 || newY+1 >= n || visited[newX][newY+1]) {
                    
                }
                else{
                    visited[newX][newY+1] = true;
                    res++;
                    queue.offer(new int[]{newX,newY+1,x[2],sum_y});
                }

            }
        }
        return res;
    }
}