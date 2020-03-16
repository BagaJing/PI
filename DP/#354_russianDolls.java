class russianDolls{
	    //274ms 排序+最长上升子序列(#300)
    public int maxEnvelopes_1(int[][] envelopes) {
        if(envelopes.length==0||envelopes==null) return 0;
        Arrays.sort(envelopes,new Comparator<int[]>(){
            public int compare(int[] o1,int[] o2){
                if(o1[0] == o2[0])
                    return o1[1] - o2[1];
                else
                    return o1[0] - o2[0];
            }
        });
        //System.out.println(Arrays.toString(envelopes[0]));
        int res = 1, len = envelopes.length;
        int[] dp = new int[len];
        dp[0] = 1;
        //LTS
        for(int i = 1; i < len ; i++){
            int maxLen = 0;
            for(int j = 0; j < i ; j++){
                if(envelopes[i][1]>envelopes[j][1]&&envelopes[i][0]>envelopes[j][0]){
                    maxLen = Math.max(maxLen,dp[j]);
                }
            }
            dp[i] = maxLen+1;
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}