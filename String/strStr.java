class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) return 0;
        if(haystack.length()==needle.length()) return needle.equals(haystack)? 0:-1;
        int winRange = needle.length();
        for(int i = 0 ; i < haystack.length()-winRange+1;i++){
            if(needle.equals(haystack.substring(i,i+winRange))) return i;
        }
        return -1;
    }
}