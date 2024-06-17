class Solution {
    public int countBattleships(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int res = 0;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (board[i][j] == 'X'){
                    board[i][j] = '.';
                    for (int k = j + 1; k < col && board[i][k] == 'X'; k++){
                        board[i][k] = '.';
                    }
                    for (int k = i + 1; k < row && board[k][j] == 'X'; k++){
                        board[k][j] = '.';
                    }
                    res++;
                }
            }
        }
        return res;
    }
}
// 枚举起点复现版
class Solution {
    public int countBattleships(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int res = 0;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (board[i][j] == 'X'){
                    if ((i == 0 || board[i-1][j] != 'X') && (j == 0 || board[i][j-1] != 'X')){
                        res++;
                    }
                }
            }
        }
        return res;
    }
}