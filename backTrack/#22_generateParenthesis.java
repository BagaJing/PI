class Solution {
    public List<String> generateParenthesis(int n) {
        if(n == 1){
            List<String >res = new ArrayList<>();
            res.add("()");
            return res;
        }
        return doStack(n,n,new StringBuilder(),new ArrayList<>());
        
    }
    //带返回值的递归 用递归的形式实现栈
    private List<String> doStack(int left,int right,StringBuilder sb,List<String> res){
        if(left==0&&right==0){
            res.add(sb.toString());
            return res;
        }
        if(left > 0){
            sb.append('(');
            res = doStack(left-1,right,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
        //初次执行条件: left == 0 , right == n. 然后逐层返回递归
        if(left < right){
            sb.append(')');
            res = doStack(left,right-1,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
        return res; 
    }
}