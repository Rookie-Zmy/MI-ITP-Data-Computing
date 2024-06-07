package com.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P51 {
    private List<List<String>> res = new ArrayList<>();
    private boolean[] cols;        // 列的布尔数组
    private boolean[] diag45;      // 45度斜线的布尔数组
    private boolean[] diag135;     // 135度斜线的布尔数组

    public List<List<String>> solveNQueens(int n) {
        cols = new boolean[n];
        diag45 = new boolean[2 * n - 1];
        diag135 = new boolean[2 * n - 1];
        int[] queens = new int[n];  // 用于记录每行皇后的位置
        backtrack(n, 0, queens);
        return res;
    }

    private void backtrack(int n, int row, int[] queens) {
        if (row == n) {
            addSolution(queens, n);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (cols[col] || diag45[row + col] || diag135[row - col + n - 1]) {
                continue;
            }
            queens[row] = col;
            cols[col] = true;
            diag45[row + col] = true;
            diag135[row - col + n - 1] = true;
            backtrack(n, row + 1, queens);
            cols[col] = false;
            diag45[row + col] = false;
            diag135[row - col + n - 1] = false;
        }
    }

    private void addSolution(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        res.add(board);
    }

    public static void main(String[] args) {
        P51 solution = new P51();
        List<List<String>> solutions = solution.solveNQueens(8);
        for (List<String> solutionStr : solutions) {
            for (String row : solutionStr) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
