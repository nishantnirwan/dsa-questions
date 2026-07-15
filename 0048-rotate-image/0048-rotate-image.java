class Solution {
    public void rotate(int[][] matrix) {

    //Step1 - Transpose of matrix
        for(int row = 0; row < matrix.length; row++) {
            for(int col = row+1; col < matrix[0].length; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
    //Step2 - Reverse rows
        for(int row = 0; row < matrix.length; row++) {
            int startCol = 0;
            int endCol = matrix.length - 1;
            while(startCol <= endCol) {
                int temp = matrix[row][startCol];
                matrix[row][startCol] = matrix[row][endCol];
                matrix[row][endCol] = temp;

                startCol++;
                endCol--;
            }
        }
    }
}