package com.maythiwat.engce124;

public class Sudoku {
    public static void main(String[] args) {
        int[][][][] boardFixed = {
                {
                        {{5, 3, 4}, {6, 7, 8}, {9, 1, 2}},
                        {{1, 7, 2}, {1, 9, 5}, {3, 4, 8}}, //
                        {{1, 9, 8}, {3, 4, 2}, {5, 6, 7}}
                },
                {
                        {{8, 5, 9}, {7, 6, 1}, {4, 2, 3}},
                        {{4, 2, 6}, {8, 5, 3}, {7, 9, 1}},
                        {{7, 1, 3}, {9, 2, 4}, {8, 5, 6}}  //
                },
                {
                        {{9, 6, 1}, {5, 3, 7}, {2, 8, 4}},
                        {{2, 8, 7}, {4, 1, 9}, {6, 3, 5}},
                        {{3, 4, 5}, {2, 8, 6}, {1, 7, 9}}
                }
        };

        int[][][][] boardInput = {
                {
                        {{5, 3, 0}, {0, 7, 0}, {0, 0, 0}},
                        {{0, 0, 0}, {1, 9, 5}, {0, 0, 0}},
                        {{0, 9, 8}, {0, 0, 0}, {0, 6, 0}},
                },
                {
                        {{8, 0, 0}, {0, 6, 0}, {0, 0, 3}},
                        {{4, 0, 0}, {8, 0, 0}, {0, 0, 1}},
                        {{7, 0, 0}, {3, 0, 2}, {0, 6, 0}},
                },
                {
                        {{0, 6, 0}, {0, 0, 0}, {2, 8, 0}},
                        {{0, 0, 0}, {4, 1, 9}, {0, 0, 5}},
                        {{0, 0, 0}, {0, 0, 8}, {7, 9, 0}}
                }
        };

        // fillData(boardFixed, boardInput);

        printBoard(boardFixed);
        processSudoku(boardFixed);
    }

    public static void fillData(int[][][][] fixed, int[][][][] input) {
        printBoard(input);
        System.out.println();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (input[r][c][i][j] == 0) {
                            input[r][c][i][j] = fixed[r][c][i][j];
                        }
                    }
                }
            }
        }
    }

    public static void processSudoku(int[][][][] board) {
        for (int r = 0; r < 3; r++) {
            if (!checkRow(board, r)) {
                System.out.printf("\nLose! @ Row [%d]\n", r);
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (!checkCol(board, c)) {
                System.out.printf("\nLose! @ Column [%d]\n", c);
                return;
            }
        }

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (!checkRegion(board, r, c)) {
                    System.out.printf("\nLose! @ Region [%d,%d]\n", r, c);
                    return;
                }
            }
        }

        System.out.println("\nCongratulations, You won!");
    }

    public static void printBoard(int[][][][] board) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                printRegion(board, r, c);
            }
        }
    }

    public static void printRegion(int[][][][] board, int row, int col) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[row][col][i][j]);
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static boolean isDuplicate(String str) {
        boolean dupe = false;
        for (int i = 0; i < str.length() - 1; i++) {
            String c = str.substring(i, i + 1);
            String seq = str.substring(i + 1);

            if (seq.contains(c)) {
                dupe = true;
                break;
            }
        }
        return dupe;
    }

    public static boolean checkRegion(int[][][][] board, int row, int col) {
        StringBuilder seq = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                seq.append(board[row][col][i][j]);
            }
        }
        System.out.println(seq.toString());
        return !isDuplicate(seq.toString());
    }

    public static boolean checkRow(int[][][][] board, int row) {
        for (int rs = 0; rs < 3; rs++) { // row select
            StringBuilder seq = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    seq.append(board[row][rs][i][j]);

                    System.out.println(row + "," + rs + "," + i + "," + j + " : " + seq);

                    if (isDuplicate(seq.toString())) {
                        System.out.println("\nError happens in: Block[" + row + "," + i + "] Sub[" + rs + "," + j + "] !!!! will exit ...");
                        // return false;
                        return false;
                    }
                }
            }

            if (isDuplicate(seq.toString())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkCol(int[][][][] board, int c) {
        for (int cs = 0; cs < 3; cs++) { // col select
            StringBuilder seq = new StringBuilder();
            for (int r = 0; r < 3; r++) { // row
                for (int j = 0; j < 3; j++) {
                    seq.append(board[r][j][cs][c]);
                }
            }

            if (isDuplicate(seq.toString())) {
                return false;
            }
        }
        return true;
    }
}
