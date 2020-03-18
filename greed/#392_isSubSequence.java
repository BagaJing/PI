class isSubSequence{
	//13ms
	public boolean isSubsequence_1(String s, String t) {
        if(s.length()==0) return true;
        int sIter = 0, tIter = 0;
        while(sIter<s.length()&&tIter<t.length()){
            if(s.charAt(sIter)==t.charAt(tIter)){
                if(sIter == s.length()-1) return true;
                else{
                    sIter++;
                    tIter++;
                }
            }else{
                tIter++;
            }
        }
        return false;
    }
    public boolean isSubsequence_2(String s, String t) {
        //贪心策略：剩余字符串含有当前字符
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            int index = t.indexOf(c);
            if(index == -1) return false;
            else {
                t = t.substring(index+1);
            }
        }
        return true;
    }
}