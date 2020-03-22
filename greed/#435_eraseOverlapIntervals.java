class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length <= 1) return 0;
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return o1[0]==o2[0]? o1[1]-o2[1]:o1[0]-o2[0];
            }
        });
        int res = 0;
        int quick = 1, slow = 0;
        while(quick < intervals.length&&slow < quick){
           // System.out.print(Arrays.toString(intervals[slow])+" ");
           // System.out.println(Arrays.toString(intervals[quick]));
            if(intervals[quick][0]<intervals[slow][1]){ //发现冲突
                res++;
                //贪心策略: 当发生冲突时，保留“尾巴短的”，才能保证后面发生的冲突最少
                if(intervals[slow][1] <= intervals[quick][1]){//quick数组尾巴大，把quick所在数组跳过
                    quick++; 
                }else{ //slow数组尾巴大，把slow所在数组跳过
                    slow = quick; 
                    quick++;
                }
            }else{ //无冲突，slow与quick之间的数组代表要删除的数组
                slow = quick; 
                quick++;
            }
            
        }
        return res;
    }
}