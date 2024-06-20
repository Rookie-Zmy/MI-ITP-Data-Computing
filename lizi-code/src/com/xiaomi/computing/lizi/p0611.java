package com.xiaomi.computing.lizi;

/**
 * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
 * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-11 10:59
 **/
public class p0611 {
    /**
     * dfs
     * @param board
     * @return
     */
    boolean[][] visited;
    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        visited = new boolean[m][n];
        int res = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j] && board[i][j]=='X'){
                    dfs(board,i,j);
                    res++;
                }
            }
        }
        return res;
    }
    public void dfs(char[][]board,int i,int j){
        //统一先判断坐标临界条件 后面就不用每个都写
        if(i<0 || i>board.length-1 ||j<0||j>board[0].length-1 ||board[i][j]!='X')
            return;
        visited[i][j]=true;
        //由于是按顺序dfs的，坐标小的肯定已经遍历过了，所以这里就去掉了
        dfs(board,i+1,j);
        dfs(board,i,j+1);
    }
}
