import java.util.ArrayList;
public class Solution { 
    //自己写的　用递归暴力搜索
    public  String PrintMinNumber(int [] numbers) { 
        if(numbers.length ==0) return new String();
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 0 ; i < numbers.length ; i++){
            nums.add(numbers[i]);
        }
        ArrayList<Long> resultList = new ArrayList<>();
        permutate(nums,resultList,Long.valueOf(0));
        //System.out.println(resultList);
        Long result = min(resultList);
        return String.valueOf(result);
    }
    private  void permutate(ArrayList<Integer> nums, ArrayList<Long> result,Long unit){
        //递归出口
        if(nums.isEmpty()){
            result.add(unit);
            return;
        }
        for(int i = 0; i < nums.size(); i++){
            int num = nums.get(i);                                                                             
            int digit = digits(num);
            int rate = (int)Math.pow(10,digit);
            Long temp = unit;
            unit = unit*Long.valueOf(rate) + Long.valueOf(num);
            nums.remove(i);
            permutate(nums,result,unit);
            unit = temp;
            nums.add(i,num);
        }
    }
    private  int digits(int num){
        int count = 0;
        while(num != 0){
            count +=1;
            num /= 10;
        }
        return count;
    }
    private  Long min(ArrayList<Long> list){
        Long min = Long.MAX_VALUE;
        for(Long i : list){
            if(i < min) min = i;
        }
        return min;
    }
    //网上大佬的写法
    /*
      中心思想:
        比较两个字符串的整数值大小
          如果　s1+s2 < s2+s1
          则说明s1应该排在s2前面
          然后互换位置　把相对较小的数值往前放
    */
        public  String PrintMinNumber(int [] numbers) { 
        if(numbers.length ==0||numbers == null) return new String();
        for(int i = 0; i < numbers.length ; i++){
            for(int j = i+1 ; j < numbers.length ; j++){
                int num1 = Integer.valueOf(numbers[i]+""+numbers[j]);
                int num2 = Integer.valueOf(numbers[j]+""+numbers[i]);
                if(num2<num1){
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        String result = new String("");
        for(int i = 0; i < numbers.length ; i++){
            result += numbers[i];
        }
        return result;
    }

}
