import java.util.HashMap;
import java.util.Set;
public class Solution {
  public  int FirstNotRepeatingChar(String str) { 
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i <str.length() ; i++){
            if(map.containsKey(str.charAt(i))){
                int value = map.get(str.charAt(i));
                value++;
                map.put(str.charAt(i),value);
            } else { 
                map.put(str.charAt(i),1);
            } 
        } 
        if(!map.containsValue(1)){
            return -1;
        } else { 
            Set<Character> set = map.keySet();
            System.out.println(set);
            int index = Integer.MAX_VALUE;
            //哈希表会自动按照二十四个字母的顺序排序Character类型的key
            //从而无法用取首个值的方法得到第一个字符
            //解决方法　遍历整个set得到坐标最小的值。。。有点蠢
            for(Character result: set){
            
                if(map.get(result) == 1){
                        if(str.indexOf(result)<index) index = str.indexOf(result);
                } 

            } 
            return index;
        } 
                                                                                                               
    } 
}
