class removeKdigits{
    public String removeKdigits_1(String num, int k) {
        StringBuilder sb = new StringBuilder(num);
        int iter = 0;
        while(k>0&&sb.length()>0){
            if(iter < sb.length()-1){
            	//贪心策略 如果前一个数比后一个数大,则删掉前一个数 否则左移
                if(sb.charAt(iter)-'0'>sb.charAt(iter+1)-'0'){
                    sb.deleteCharAt(iter);
                    k--;
                    if(iter>0) iter--;
                }else iter++;
            } else { //iter==sb.length-1处于最后一个元素，说明数字为升序，直接删除最后一个
                sb.deleteCharAt(sb.length()-1);
                k--;
            }

        }
        while(sb.length()>0&&sb.charAt(0)=='0') sb.deleteCharAt(0);
        return sb.length()>0?sb.toString():"0";
    }
}