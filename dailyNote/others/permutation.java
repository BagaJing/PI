import java.util.ArrayList;
public class Solution { 
    public  ArrayList<String> Permutation(String str) { 
       if(str.length() == 0) return new ArrayList<String>();
       char[] array = str.toCharArray();
       //System.out.println(array.length);
       StringBuffer s = new StringBuffer();
        ArrayList<String> result = new ArrayList<>();
        permutate(s,result,array);
        //removeDuplicate(result);
        return result;
    } 

    private  void permutate(StringBuffer s,
                         ArrayList<String> result,
                         char[] array){
         //   System.out.println(s.toString().length());
        //递归出口
        if(s.toString().length() == array.length){
                if(!result.contains(s.toString()))result.add(s.toString());
                return;
        } 
        for(int i = 0 ; i < array.length ; i++){
            if(array[i] != ' '){
                s.append(array[i]);
                char temp = array[i];
                array[i] = ' ';
                permutate(s,result,array);
                array[i] = temp;
                s.deleteCharAt(s.length()-1);
            }   
        }                                                                                                      
    } 
}
