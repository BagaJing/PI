class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] levels = new int[candidates.length];
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        BT(candidates,levels,0,target,0,res,ans);
        return res;
    }
    private void BT(int[] cans,int[] levels,int curLevel,int target,int index,List<List<Integer>> res,List<Integer> ans){
        if(target < 0) return;
        if(target == 0){
            res.add(new ArrayList<>(ans));
            return;
        }
        for(int i = index ; i < cans.length ; i++){
            levels[i] = curLevel;
            if(i>0&&cans[i-1]==cans[i]&&levels[i]==levels[i-1]) continue;
            ans.add(cans[i]);
            BT(cans,levels,curLevel+1,target-cans[i],i+1,res,ans);
            ans.remove(ans.size()-1);
        }
    }
}