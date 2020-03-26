class Solution {
    public int numRookCaptures(char[][] board) {
        int len = board.length;
        int row_r  = 0, col_r = 0;
        int res = 0;
        for(int i = 0 ; i < len ; i++){
            for(int j = 0 ; j < len ; j++){
                if(board[i][j] == 'R'){
                    row_r = i;
                    col_r = j;
                    for(int row = row_r-1; row >=0 ; row--){
                        if(board[row][col_r]=='B') break;
                        if(board[row][col_r]=='p'){
                            res++;
                            break;
                        }
                    }
                    for(int row = row_r+1;row<len;row++){
                        if(board[row][col_r]=='B')break;
                        if(board[row][col_r]=='p'){
                            res++;
                            break;
                        }
                    }
                    for(int col = col_r-1;col>=0;col--){
                        if(board[row_r][col]=='B')break;
                        if(board[row_r][col]=='p'){
                            res++;
                            break;
                        }
                    }
                    for(int col = col_r+1;col<len;col++){
                        if(board[row_r][col]=='B')break;
                        if(board[row_r][col]=='p'){
                            res++;
                            break;
                        }
                    }
                    return res;
                }
            }
        }
        return 0;
    }
}