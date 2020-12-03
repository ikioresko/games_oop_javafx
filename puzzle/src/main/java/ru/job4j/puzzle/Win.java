package ru.job4j.puzzle;

public class Win {
    public static boolean check(int[][] board) {
        boolean rsl = false;
        for (int y = 0; y < board.length; y++) {
            if (board[y][y] == 1) {
                if ((rowCheck(board, y) || columnCheck(board, y))) { // когда одна из проверок true игра выигрышная
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    public static boolean rowCheck(int[][] board, int num) {
        boolean rsl = true;
        for (int x = 0; x < board.length; x++) {
            if (board[num][x] != 1) {//проверяет всю ось x на наличие 1
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    public static boolean columnCheck(int[][] board, int num) {
        boolean rsl = true;
        for (int x = 0; x < board.length; x++) {
            if (board[x][num] != 1) {//проверяет всю ось y на наличие 1
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}
