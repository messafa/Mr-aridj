public class SudokuSolver {

    public static void main(String[] args) {
        // int[][] board = {
        //         {5, 3, 0, 0, 7, 0, 0, 0, 0},
        //         {6, 0, 0, 1, 9, 5, 0, 0, 0},
        //         {0, 9, 8, 0, 0, 0, 0, 6, 0},
        //         {8, 0, 0, 0, 6, 0, 0, 0, 3},
        //         {4, 0, 0, 8, 0, 3, 0, 0, 1},
        //         {7, 0, 0, 0, 2, 0, 0, 0, 6},
        //         {0, 6, 0, 0, 0, 0, 2, 8, 0},
        //         {0, 0, 0, 4, 1, 9, 0, 0, 5},
        //         {0, 0, 0, 0, 8, 0, 0, 7, 9}
        // };



        // int[][] board = {// all 0
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0}
        // };


//         int[][] board = {
//     {1,0,0,0,0,7,0,9,0},
//     {0,3,0,0,2,0,0,0,8},
//     {0,0,9,6,0,0,5,0,0},
//     {0,0,5,3,0,0,9,0,0},
//     {0,1,0,0,8,0,0,0,2},
//     {6,0,0,0,0,4,0,0,0},
//     {3,0,0,0,0,0,2,0,0},
//     {0,4,0,0,0,0,0,0,0},
//     {0,0,7,0,0,0,0,0,5}
// };



// int[][] board = {
//     {8,0,0,0,0,0,0,0,0},
//     {0,0,3,6,0,0,0,0,0},
//     {0,7,0,0,9,0,2,0,0},
//     {0,5,0,0,0,7,0,0,0},
//     {0,0,0,0,4,5,7,0,0},
//     {0,0,0,1,0,0,0,3,0},
//     {0,0,1,0,0,0,0,6,8},
//     {0,0,8,5,0,0,0,1,0},
//     {0,9,0,0,0,0,4,0,0}
// };

int[][] board = {
    {0,0,0,0,0,0,0,2,0},
    {0,0,0,7,0,0,0,0,0},
    {0,0,1,0,0,0,0,0,8},
    {0,0,0,0,5,0,0,0,0},
    {0,0,0,2,0,6,0,0,0},
    {0,0,0,0,1,0,0,0,0},
    {7,0,0,0,0,0,6,0,0},
    {0,0,0,0,0,8,0,0,0},
    {0,2,0,0,0,0,0,0,0}
};


// int[][] board = {
//     {5, 3, 4, 6, 7, 8, 9, 1, 2},
//     {6, 7, 2, 1, 9, 5, 3, 4, 8},
//     {1, 9, 8, 3, 4, 2, 5, 6, 7},
//     {8, 5, 9, 7, 6, 1, 4, 2, 3},
//     {4, 2, 6, 8, 5, 3, 7, 9, 1},
//     {7, 1, 3, 9, 2, 4, 8, 5, 6},
//     {9, 6, 1, 5, 3, 7, 2, 8, 4},
//     {2, 8, 7, 4, 1, 9, 6, 3, 5},
//     {3, 4, 5, 2, 8, 6, 1, 7, 7} // ❌ ERROR: 7 is duplicated here
// };







        if (solve(board)) {
            print(board);
        } else {
            System.out.println("No solution exists!");
        }
    }

    public static boolean solve(int[][] board) {
        // find an empty cell
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) {
                    // try numbers 1-9
                    for (int num = 1; num <= 9; num++) {

                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            // BACKTRACK
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true; // solved
    }

    // check if num can be placed at board[row][col]
    private static boolean isValid(int[][] board, int row, int col, int num) {

        // check row
        for (int x = 0; x < 9; x++)
            if (board[row][x] == num)
                return false;

        // check column
        for (int x = 0; x < 9; x++)
            if (board[x][col] == num)
                return false;

        // check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int r = startRow; r < startRow + 3; r++)
            for (int c = startCol; c < startCol + 3; c++)
                if (board[r][c] == num)
                    return false;

        return true;
    }

    // just for printing the board
    private static void print(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }
}
