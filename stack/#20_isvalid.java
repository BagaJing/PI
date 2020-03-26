class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for(int i = 0 ; i < len ; i++){
            if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
                stack.push(s.charAt(i));
            }else{
                if(!stack.isEmpty()){
                    if(s.charAt(i)-stack.peek()==1||s.charAt(i)-stack.peek()==2)
                        stack.pop();
                    else return false;
                }else return false;
            }
        }
        return stack.isEmpty();
    }
}