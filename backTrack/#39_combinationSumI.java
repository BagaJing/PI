class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> sample = new ArrayList<>();
        bt(candidates,0,target,sample,res);
        return res;
        
    }
    private void bt(int[] candidates,int index,int target, List<Integer> ans,List<List<Integer>> res){
     //   if(index>=candidates.length)return;
        if(target < 0) return;
        if(target == 0){
           // System.out.println(index);
            res.add(new ArrayList<>(ans));
            return;
        }
        for(int i = index; i <candidates.length;i++){
                ans.add(candidates[i]);
                bt(candidates,i,target-candidates[i],ans,res);//因为可以包含重复数字，因此i不加1
                ans.remove(ans.size()-1);
        }
    }
}