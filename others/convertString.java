class Solution {
    public String convert(String s, int numRows) {
        /*当行数为1 或 字符串长度小于行数时 不用转换*/
        if(s.length()<=numRows||numRows ==1) return s;
        /*Z字形转换，中间斜杠的列数，第一行和最后一行没有,所以为numRows-2*/
        int diff = numRows - 2;
        /*重排字符串下个字符间隔距离*/
        int mod = numRows + diff;
        int len = s.length();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < numRows ; i++){
            int iter = i;
            int jump = mod;
            /*当不为最后一行或第一行时，先要取中间斜杠上的字符，减去2*iter的原因为总结规律所得。。。恩。*/
            if(iter!=0&&iter!=numRows-1) jump -= 2*iter;
            while(iter < len){
                str.append(s.charAt(iter));
                iter += jump;
                /*若不为两头，下一点要跳到 斜杠上的字符或列上的字符，用mod取相对值*/
                if(jump != mod) jump = mod - jump;
            }
        }
        return str.toString();
    }
}
