class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2) {
                return a1[0] - a2[0];
            }
        });
        if(intervals.length == 0) return new int[0][];
        int[] unit = new int[2];
        unit[0] = intervals[0][0];
        unit[1] = intervals[0][1];
        for(int i = 1 ; i < intervals.length ; i++){
            if(unit[1] >= intervals[i][0]){
                if(unit[1] <= intervals[i][1])
                    unit[1] = intervals[i][1];
            }else{
                result.add(new int[]{unit[0],unit[1]});
                unit[0] = intervals[i][0];
                unit[1] = intervals[i][1];
            }
        }
        if(result.size() == 0){
            result.add(unit);
        }else{
            int[] tail = result.get(result.size()-1);
            if(tail[1] >= unit[0]){
                if(tail[1] <= unit[1])
                    tail[1] = unit[1];
            }else{
                result.add(unit);
            }
        }
        int[][] res = new int[result.size()][];
        for(int i = 0; i < result.size(); i++){
            res[i] = result.get(i);
        }
        return res;
    }

}