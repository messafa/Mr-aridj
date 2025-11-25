import java.util.*;

public class TicTacToe {
    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("You are X, AI is O");
        
        while (true) {
            playerMove();
            if (isGameOver()) break;

            aiMove();
            if (isGameOver()) break;
        }
    }

    // ======================= PLAYER MOVE =======================
    private static void playerMove() {
        while (true) {
            System.out.print("Enter your move (row col): ");
            int r = input.nextInt();
            int c = input.nextInt();

            if (r >= 0 && r < 3 && c >= 0 && c < 3 && board[r][c] == ' ') {
                board[r][c] = 'X';
                printBoard();
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    // ======================= AI MOVE =======================
    private static void aiMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1, bestCol = -1;

        // تجربة كل الحركات الممكنة
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' ') {
                    board[r][c] = 'O';
                    int score = minimax(false);
                    board[r][c] = ' ';

                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = r;
                        bestCol = c;
                    }
                }
            }
        }

        board[bestRow][bestCol] = 'O';
        System.out.println("\nAI played:");
        printBoard();
    }

    // ======================= MINIMAX =======================
    private static int minimax(boolean isMaximizing) {
        if (checkWinner('O')) return +10; // AI wins
        if (checkWinner('X')) return -10; // Player wins
        if (isBoardFull()) return 0;      // Draw

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == ' ') {
                        board[r][c] = 'O';
                        int score = minimax(false);
                        board[r][c] = ' ';
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == ' ') {
                        board[r][c] = 'X';
                        int score = minimax(true);
                        board[r][c] = ' ';
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }

    // ======================= CHECKS =======================
    private static boolean checkWinner(char p) {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p) return true;

        for (int i = 0; i < 3; i++)
            if (board[0][i] == p && board[1][i] == p && board[2][i] == p) return true;

        if (board[0][0] == p && board[1][1] == p && board[2][2] == p) return true;
        if (board[0][2] == p && board[1][1] == p && board[2][0] == p) return true;

        return false;
    }

    private static boolean isBoardFull() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == ' ') return false;
        return true;
    }

    private static boolean isGameOver() {
        if (checkWinner('X')) {
            System.out.println("You win!");
            return true;
        }
        if (checkWinner('O')) {
            System.out.println("AI wins!");
            return true;
        }
        if (isBoardFull()) {
            System.out.println("Draw!");
            return true;
        }
        return false;
    }

    // ======================= PRINT BOARD =======================
    private static void printBoard() {
        System.out.println("---------");
        for (int r = 0; r < 3; r++) {
            System.out.print("| ");
            for (int c = 0; c < 3; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
