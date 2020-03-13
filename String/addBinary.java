//leetcode:67
class Solution {
    public String addBinary(String a, String b) {
        int a_index = a.length()-1,b_index=b.length()-1;
        boolean adv = false;
        StringBuilder sb = new StringBuilder();
        
        while(a_index>=0||b_index>=0){
            //(int)'1' = 49
            int sum = (a_index>=0?(int)a.charAt(a_index)-48:0) +(b_index>=0?(int)b.charAt(b_index)-48:0);
            //System.out.println(sum);
            sum += adv? 1 : 0;
            adv = sum >= 2? true : false;
            a_index--;b_index--;
            sb.append(sum%2);
        }
        if(adv) sb.append(1);
        return sb.reverse().toString();
    }
}