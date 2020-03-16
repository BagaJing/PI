class Solution {
    public String compressString(String S) {
        if(S.length()<=1) return S;
        StringBuilder sb = new StringBuilder();
        char c = S.charAt(0);
        int same = 1;
        sb.append(c);
        for(int i = 1 ; i < S.length() ; i++){
            if(S.charAt(i)==S.charAt(i-1)) same++;
            else{
                sb.append(same);
                same = 1;
                sb.append(S.charAt(i));
            }
        }
        sb.append(same);
        return sb.length()>=S.length()? S : sb.toString();
    }
}