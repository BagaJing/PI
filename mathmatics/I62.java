class Solution {
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++)
            list.add(i);
        int index = 0;
        while(n>1){
            index = (index+m-1)%n;
            list.remove(index);
            n--;
        }
        return list.get(0);
    }
    public int lastRemaining_1(int n, int m) {
        if(n == 1)
            return 0;
        int x = lastRemaining(n-1,m);
        return (x+m)%n;
    }
}