class Solution {
    //2ms
    public String reverseWords(String s) {
        if(s.trim().equals("")) return "";
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = arr.length-1; i>=0;i--){
            if(arr[i].equals(" ")||arr[i].equals("")) {
                 continue;
            }
            sb.append(arr[i]);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}