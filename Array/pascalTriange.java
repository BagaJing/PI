class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows==0)return res;
        //推进尖顶
        List<Integer> top = new ArrayList<>();
        top.add(1);
        res.add(top);
        while(numRows-1 > 0){
            List<Integer> upper = res.get(res.size()-1);
            List<Integer> newList = new ArrayList<>();
            newList.add(1);
            for(int i =1; i <upper.size(); i++)
                newList.add(upper.get(i-1)+upper.get(i));
            newList.add(1);
            res.add(newList);
            numRows--;
        }
        return res;
    }
}