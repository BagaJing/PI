class giveBiscuits{
	    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res=0,gIter=0,sIter=0;
        //贪心思想: 把最小的饼干分给满足值最小的孩子
        while(sIter<s.length&&gIter<g.length){
            if(g[gIter] <= s[sIter]){
                gIter++;
                sIter++;
                res++;
            } else sIter++;
        }
        return res;
    }
}