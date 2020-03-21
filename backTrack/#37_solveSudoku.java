class Solution {
    public void solveSudoku(char[][] board) {
        //建立3x3的哈希集组 用 i，j确定哈希集所属的位置
        Set<Integer>[][] sets = new Set[3][3];
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 3; j++){
                sets[i][j] = new HashSet<>();
                for(int k =0 ;k<3 ;k++){
                    for(int w=0 ;w< 3;w++){
                        if(board[i*3+k][j*3+w]!='.')
                            sets[i][j].add(board[i*3+k][j*3+w]-'0'); //取得该3x3宫的数字
                    }
                }
            }
        }
        backTrack(board,sets,0,0);
        
    }
    private boolean backTrack(char[][] board,Set<Integer>[][] sets,int row,int col){
        if(col>8||row>8)return true;
        boolean whole = false;
        if(board[row][col]=='.'){
            Set<Integer> set = sets[row/3][col/3]; // 找到点所属的哈希集
            for(int i = 1 ; i <= 9 ; i++){
                if(!isOk(set,board,row,col,i)){ //查找行，列，宫是否含有此数字，若没有则尝试加入
                    board[row][col] = (char)('0'+i);
                    set.add(i);
                    if(row==8&&col==8) return true; //若为最后一点则找到答案
                    boolean result = true;
                    if(row<=8&&col<8)
                        result = backTrack(board,sets,row,col+1); //递归下一个
                    else if(row<8) 
                        result = backTrack(board,sets,row+1,0); //递归下一行
                     if(!result){ //若此点不符 则回溯
                        board[row][col] = '.';
                        set.remove(i);
                     }
                    whole = whole||result;
                }
            }
        }else{
            if(row==8&&col==8) whole = true;
            if(row<=8&&col<8)
                whole = backTrack(board,sets,row,col+1);
            else if(row<8)
                whole = backTrack(board,sets,row+1,0);
        }
        return whole;
    }
    private boolean isOk(Set<Integer> set,char[][] board,int row,int col,int val){
        boolean inRow = false, inCol = false, inCube=false;
        inCube = set.contains(val);
        for(int i = 0 ; i < 9 ; i++){
            if(board[row][i]-'0'==val){
                inCol = true;
                break;
            }
        }
        for(int i = 0; i < 9 ;i++){
            if(board[i][col]-'0'==val){
                inRow = true;
                break;
            }
        }
        return inRow||inCol||inCube;
    }
}