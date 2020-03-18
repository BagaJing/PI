class countCharacters{
	public int countCharacters(String[] words, String chars) {
        int res = 0;
        int[] map = new int[26];
        for(int i = 0 ; i < chars.length() ; i++)
            map[chars.charAt(i)-'a']++;
        int[] dic = new int[26];
        boolean isOk = true;
        for(String word : words){
            for(int i = 0; i < word.length() ; i++)
                dic[word.charAt(i)-'a']++;
            for(int i = 0 ; i < 26; i++){
                if(dic[i]>map[i]){
                    isOk = false;
                   break;
                }
            }
            Arrays.fill(dic,0);
            if(isOk) res += word.length();
            isOk = true;
        }
        return res;
    }
}