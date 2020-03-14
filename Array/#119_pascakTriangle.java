//ref: https://leetcode-cn.com/problems/pascals-triangle-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--28/
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        int N = rowIndex;
        for(int k = 0 ; k <= N ; k++)
            res.add(com(N,k));
        return res;
    }
    private int com(int N,int k){
        long res = 1;
        for(int i = 1 ; i <= k ; i++)
            res = res*(N-k+i)/i;
        return (int)res;
    }
}