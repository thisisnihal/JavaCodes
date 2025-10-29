package dsa.leetcode;

public class RotateImage {
    static class Solution {
        public void rotate(int[][] matrix) {

            int row = matrix.length;
            int col = matrix[0].length;
            // transpose
            for (int i = 0; i < row; i++) {
                for (int j = i; j < col; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
            // reverse each column
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col / 2; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][col - j - 1];
                    matrix[i][col - j - 1] = temp;
                }
            }

        }
    }

    static void print2dArray(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("" + matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] matrix = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };

        print2dArray(matrix);
        s.rotate(matrix);
        System.err.println("---------------------");
        print2dArray(matrix);

    }
}
