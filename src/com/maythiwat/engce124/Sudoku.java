package com.maythiwat.engce124;

public class Sudoku {
    public static void main(String[] args) {
        int[][][][] region = {
                {
                        {{5, 3, 4}, {6, 7, 2}, {1, 9, 8}},
                        {{6, 7, 8}, {1, 9, 5}, {3, 4, 2}},
                        {{9, 1, 2}, {3, 4, 8}, {5, 6, 7}}
                },
                {
                        {{8, 5, 9}, {4, 2, 6}, {7, 1, 3}},
                        {{7, 6, 1}, {8, 5, 3}, {9, 2, 4}},
                        {{4, 2, 3}, {7, 9, 1}, {8, 5, 6}},
                },
                {
                        {{9, 6, 1}, {2, 8, 7}, {3, 4, 5}},
                        {{5, 3, 7}, {4, 1, 9}, {2, 8, 6}},
                        {{2, 8, 4}, {6, 3, 5}, {1, 7, 9}},
                }
        };

        int[][][][] input = {
                {
                        {{5, 3, 0}, {0, 0, 0}, {0, 9, 8}},
                        {{0, 7, 0}, {1, 9, 5}, {0, 0, 0}},
                        {{0, 0, 0}, {0, 0, 0}, {0, 6, 0}}
                },
                {
                        {{8, 0, 0}, {4, 0, 0}, {7, 0, 0}},
                        {{0, 6, 0}, {8, 0, 0}, {3, 0, 2}}, //
                        {{0, 0, 3}, {0, 0, 1}, {0, 0, 6}},
                },
                {
                        {{0, 6, 0}, {0, 0, 0}, {0, 0, 0}},
                        {{0, 0, 0}, {4, 1, 9}, {0, 8, 0}},
                        {{2, 8, 0}, {0, 0, 5}, {0, 7, 9}},
                }
        };

        // printRegion(region, 1, 0);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                printRegion(region, r, c);
            }
        }

        System.out.println("WIN!!!");
    }

    public static void printRegion(int[][][][] region, int row, int col) {
        StringBuilder seq = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(region[row][col][i][j]);
                seq.append(region[row][col][i][j]);
            }
            System.out.println();
        }
        // System.out.println(checkDuplicate(seq));

        if (isDuplicate(seq.toString())) {
            System.out.println("LOSE!!!");
            System.exit(0);
        }
    }

    public static void printRow(int[][][][] region, int row) {
        for (int rs = 0; rs < 3; rs++) { // row select
            StringBuilder seq = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(region[row][i][rs][j]);
                    seq.append(region[row][i][rs][j]);
                }
            }
            System.out.println();
            System.out.println(isDuplicate(seq.toString()));
        }
    }

    public static void printCol(int[][][][] region, int c) {
        for (int cs = 0; cs < 3; cs++) { // col select
            StringBuilder seq = new StringBuilder();
            for(int r = 0; r < 3; r++) { // row
                for (int j = 0; j < 3; j++) {
                    System.out.print(region[r][c][j][cs]);
                    seq.append(region[r][c][j][cs]);
                }
            }
            System.out.println();
            System.out.println(isDuplicate(seq.toString()));
        }
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
}
