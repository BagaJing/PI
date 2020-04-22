class Solution {
    //把括号字符串分成两个不相交的子序列 A和B, 子序列可不连续
    //策略: 让A B均分括号的最深深度, 最简单的方法是按照奇偶来均摊括号层次
    public int[] maxDepthAfterSplit(String seq) {
        int[] res = new int[seq.length()];
        int idx = 0;
        for(char c : seq.toCharArray()){
            res[idx++] = c == '('? idx&1 : (idx+1)&1;
        }
        return res;
    }
}