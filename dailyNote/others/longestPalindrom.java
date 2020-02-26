/*
  根据回文串的定义，正着读反着读都一样，所以可以取倒转字符串，转化成求　“最长公共子串的问题”
  最长公共子串问题
    动态规划解法：
    １．创建一个长度为String长度的二维数组
    ２．双层循环，判断另个字符串中对应的字符是否相等
       若相等：　arr[i][j] = arr[i-1][j-1] + 1;
               arr[i][j]的值则为公共子串的长度
               当　i为０　或　j为０时，说明有一个串在开头匹配,单独处理此种情况 arr[i][j] = 1
*/
class Solution {
    public String longestPalindrome(String s) {
        if(s.equals("")) return "";
        int len = s.length();
        String origin = s;
        /*反转得到对比用的字符串*/
        String reverse = new StringBuilder(s).reverse().toString();
        int[][] matrix = new int[len][len];
      　/*最后一个坐标的*/
        int maxEnd = 0;
        int maxLen = 0;
        for(int i = 0 ; i < len ; i++){
            for(int j = 0 ; j < len ; j++){
                if(origin.charAt(i) == reverse.charAt(j)){
                    if(i == 0 || j == 0){
                        matrix[i][j] = 1;
                    } else{
                        matrix[i][j] = matrix[i-1][j-1] + 1;
                    }
                    /*更新目前发现最长的公共子串*/
                    if(matrix[i][j] > maxLen){
                        int beforeRev = len - 1 - j;
                       　/*不是所用公共子串都是回文串，
                        例如“abc456cba”和"abc654cba",其中"abc"是公共子串，但明显不是公共回文串
                        则我们找到公共子串后，要判断该子串倒置前的下标和　当前的下标　是否相等
                        beforeRev = len - 1 -j 为j倒置前的坐标
                        若　倒置前的坐标　＋ 公共子串的长度-1 == i的坐标， 则能确定公共子串为回文串
                        ps(j：倒转字符串中匹配字符串的最末坐标
                           i：原始字符串中匹配字符串的最末坐标
                           len-j-1:j所在位置字符倒转前的坐标，若为回文，则也应为公共回文子串的其实坐标
                           len-1-j+maxLen-1:在公共子串为回文串的情况下，理论上公共串的末坐标，若与i相等，则说明为回文
                        */
                        if(beforeRev + matrix[i][j] - 1 == i){
                            maxLen = matrix[i][j];
                            maxEnd = i;
                        }
                    }
                }
            }
        }
        return s.substring(maxEnd-maxLen+1,maxEnd+1);
    }
}
