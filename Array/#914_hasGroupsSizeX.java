class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int len = deck.length;
        if(len<=1) return false;
        Arrays.sort(deck);
        int first = 0, last = 0;
        int X = 0, cur = 0;
        while(last < len){
            while(last<len&&deck[first]==deck[last]) last++;
            cur = last - first;
            first = last;
            if(X==0){
                X = cur;
                continue;
            }
            X = GCD(X,cur);
            if(X==1) return false;
            
        }
        return true;
    }
    //求最大公约数
    private int GCD(int m, int n){
        int min = m > n? n : m;
        for(int i = min ; i > 1 ; i--){
            if(m%i==0&&n%i==0) return i;
        }
        return 1;
    }
}