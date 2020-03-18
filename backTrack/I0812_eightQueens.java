class eightQueens{
	    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] cols = new int[n];
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++)
            sb.append('.');
        queen(cols,0,n,sb,res);
        return res;
    }
    private void queen(int[] cols,int row,int len,StringBuilder sb,List<List<String>> res){
        if(row==len){
            List<String> solution = new ArrayList<>();
            //System.out.println(Arrays.toString(cols));
            for(Integer i : cols){
                sb.replace(i,i+1,"Q");
                solution.add(sb.toString());
                sb.replace(i,i+1,".");
            }
            res.add(solution);
        }else{
            for(int col = 0; col < len ; col++){
                cols[row] = col;
                if(isOk(cols,row))
                    queen(cols,row+1,len,sb,res);
            }
        }
    }
    private boolean isOk(int[] cols,int row){
        for(int i = 0 ; i < row; i++){
            if(cols[i]==cols[row]||cols[row]-row==cols[i]-i||cols[row]+row==cols[i]+i) return false;
        }
        return true;
    }
}