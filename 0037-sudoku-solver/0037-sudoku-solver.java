class Solution {

    static boolean isSafeToPlace(char[][] board, char charValue, int rowIndex, int colIndex) {
        // Check for horizontal or same row
        for(int col = 0; col < 9; col++) {
            if(board[rowIndex][col] == charValue) {
                return false;
            }
        }

        // Check for vertical or same col
        for(int row = 0; row < 9; row++) {
            if(board[row][colIndex] == charValue) {
                return false;
            }
        }
        // Check for current 3 x 3 sub board
        int startRow = rowIndex - rowIndex % 3;
        int startCol = colIndex - colIndex % 3;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                int actualRow = startRow + i;
                int actualCol = startCol + j;

                if(board[actualRow][actualCol] == charValue) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean findEmptyCell(char[][] board, int[] emptyCell) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {

                    // Row Index Of Empty Cell
                    emptyCell[0] = i;
                    // Col Index Of Empty Cell
                    emptyCell[1] = j;
                    return true;
                }
            }
        }
        return false;
    }

    static boolean solveSudokuHelper(char[][] board) {
        // Base case
        int[] emptyCell = new int[2];
        if(!findEmptyCell(board, emptyCell)) {
            return true;
        }

        int rowIndex = emptyCell[0];
        int colIndex = emptyCell[1];

        for(int value = 1; value <= 9; value++) {
            char charValue = (char)(value + '0');
            if(isSafeToPlace(board, charValue, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = charValue;
                if(solveSudokuHelper(board) == true) {
                    return true;
                }
                board[rowIndex][colIndex] = '.';
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }
}