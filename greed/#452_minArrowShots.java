class minArrowShots {
    //排序+合并区间
    public int findMinArrowShots(int[][] points) {
        if(points.length <=1) return points.length;
        Arrays.sort(points,new Comparator<int[]>(){
           public int compare(int[] o1,int[] o2){
               return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
           }
        });
        int n = points.length;
        int res = 1;
        for(int i = 1 ; i < n ; i++){
            //求此区间与上个区间的公共区间
            if(points[i][0]<=points[i-1][1]){
                if(points[i-1][1]<=points[i][1]) points[i][1] = points[i-1][1];
            }else{ //如果没有则加一只箭
                res++;
            }
        }
        return res;
    }
}