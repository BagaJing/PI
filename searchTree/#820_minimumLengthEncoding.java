class Solution {
    //逆转字符串,字典排序,找后缀变找前缀
    public int minimumLengthEncoding_1(String[] words) {
        int len = words.length;
        for(int i = 0 ; i < len ; i++){
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }
        Arrays.sort(words);
        int res = 0;
        for(int i = 0 ; i < len; i++){
            if(i+1<len&&words[i+1].startsWith(words[i])) continue;//此字符串为下一个字符串的前缀,则跳过
            else{
                res += words[i].length()+1;
            }
        }
        return res;
    }
    //逆向字典排序,找后缀
    public int minimumLengthEncoding_2(String[] words) {
        int len = words.length;
        Comparator<String> cpm = (s1,s2)->{
            int len1 = s1.length();
            int len2 = s2.length();
            for(int i = 0 ; i < Math.min(len1,len2); i++){
                char c1 = s1.charAt(len1-1-i);
                char c2 = s2.charAt(len2-1-i);
                int res = Character.compare(c1,c2);
                if(res!=0) return res;
            }
            return Integer.compare(len1,len2);
        };
        Arrays.sort(words,cpm);
        int res = 0;
        for(int i = 0 ; i < len; i++){
            if(i+1<len&&words[i+1].endsWith(words[i])) continue; //此字符串包含在下一个字符串的后缀中,则跳过
            else{
                res += words[i].length()+1;
            }
        }
        return res;
    }
    //字典树 后缀
    public int minimumLengthEncoding_3(String[] words) {
        Arrays.sort(words,(s1, s2) -> s2.length() - s1.length()); //按照字符串长度倒序排序
        TrieNode root = new TrieNode();
        int res = 0;
        for(String str : words){
            res += root.insert(str);
        }
        return res;
    }
    //
class TrieNode{
    TrieNode[] children;
    public TrieNode(){
        this.children = new TrieNode[26];
    }
    public int insert(String str){
        TrieNode cur = this;
        int len = str.length();
        boolean isNew = false;
        for(int i = str.length()-1 ; i >=0 ; i--){ //找后缀
            int c = str.charAt(i)-'a';
            if(cur.children[c] == null){
                cur.children[c] = new TrieNode();
                isNew = true;
            }
            cur = cur.children[c];
        }
        return isNew? len+1:0;
    }
}
}