class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder unit = new StringBuilder();
        String[] arr = s.split(" ");
        for(int i = 0; i < arr.length ; i++){
            unit.append(arr[i]);
            unit.reverse();
            sb.append(unit);
            sb.append(' ');
            unit.replace(0,unit.length(),"");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}