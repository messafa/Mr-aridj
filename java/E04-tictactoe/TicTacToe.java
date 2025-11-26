public class TicTacToe {
    
    // رموز اللاعبين
    static final char HUMAN = 'O';
    static final char AI = 'X';
    static final char EMPTY = '_';
    
    // طباعة اللوحة
    public static void printBoard(char[][] board) {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }
    
    // التحقق من وجود فائز
    public static int evaluate(char[][] board) {
        // فحص الصفوف
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && 
                board[row][1] == board[row][2]) {
                if (board[row][0] == AI) return +10;
                else if (board[row][0] == HUMAN) return -10;
            }
        }
        
        // فحص الأعمدة
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && 
                board[1][col] == board[2][col]) {
                if (board[0][col] == AI) return +10;
                else if (board[0][col] == HUMAN) return -10;
            }
        }
        
        // فحص القطر الأول
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == AI) return +10;
            else if (board[0][0] == HUMAN) return -10;
        }
        
        // فحص القطر الثاني
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == AI) return +10;
            else if (board[0][2] == HUMAN) return -10;
        }
        
        // لا يوجد فائز
        return 0;
    }
    
    // التحقق من وجود حركات متاحة
    public static boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // خوارزمية Minimax - القلب الأساسي
    public static int minimax(char[][] board, int depth, boolean isMax) {
        int score = evaluate(board);
        
        // إذا فاز AI، أرجع النقاط
        if (score == 10) return score - depth;
        
        // إذا فاز الإنسان، أرجع النقاط
        if (score == -10) return score + depth;
        
        // إذا لا توجد حركات متبقية، تعادل
        if (!isMovesLeft(board)) return 0;
        
        // دور AI (Maximizer)
        if (isMax) {
            int best = -1000;
            
            // تجربة جميع الخلايا الفارغة
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == EMPTY) {
                        // جرب هذه الحركة
                        board[i][j] = AI;
                        
                        // استدعاء Minimax بشكل تكراري
                        best = Math.max(best, minimax(board, depth + 1, false));
                        
                        // تراجع عن الحركة
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        }
        // دور الإنسان (Minimizer)
        else {
            int best = 1000;
            
            // تجربة جميع الخلايا الفارغة
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == EMPTY) {
                        // جرب هذه الحركة
                        board[i][j] = HUMAN;
                        
                        // استدعاء Minimax بشكل تكراري
                        best = Math.min(best, minimax(board, depth + 1, true));
                        
                        // تراجع عن الحركة
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        }
    }
    
    // إيجاد أفضل حركة للـ AI
    public static int[] findBestMove(char[][] board) {
        int bestVal = -1000;
        int[] bestMove = {-1, -1};
        
        System.out.println("\nAI يفكر...");
        
        // تجربة جميع الخلايا الفارغة
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    // جرب هذه الحركة
                    board[i][j] = AI;
                    
                    // احسب قيمة هذه الحركة
                    int moveVal = minimax(board, 0, false);
                    
                    // تراجع عن الحركة
                    board[i][j] = EMPTY;
                    
                    // إذا كانت هذه الحركة أفضل من الأفضل السابق
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        
        System.out.println("أفضل حركة: صف " + bestMove[0] + ", عمود " + bestMove[1]);
        System.out.println("التقييم: " + bestVal);
        
        return bestMove;
    }
    
    // البرنامج الرئيسي
    public static void main(String[] args) {
        char[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };
        
        System.out.println("=== لعبة Tic Tac Toe مع Minimax ===");
        System.out.println("أنت: O | الكمبيوتر: X");
        
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            while (true) {
                // طباعة اللوحة
                printBoard(board);
                
                // فحص نهاية اللعبة
                int score = evaluate(board);
                if (score == 10) {
                    System.out.println("\n🎮 الكمبيوتر فاز!");
                    break;
                }
                if (score == -10) {
                    System.out.println("\n🎉 أنت فزت!");
                    break;
                }
                if (!isMovesLeft(board)) {
                    System.out.println("\n🤝 تعادل!");
                    break;
                }
                
                // دور الإنسان
                System.out.println("\nدورك! أدخل الصف والعمود (0-2):");
                System.out.print("الصف: ");
                int row = scanner.nextInt();
                System.out.print("العمود: ");
                int col = scanner.nextInt();
                
                // التحقق من صحة الحركة
                if (row < 0 || row > 2 || col < 0 || col > 2 ||
                        board[row][col] != EMPTY) {
                    System.out.println("❌ حركة غير صحيحة! حاول مرة أخرى.");
                    continue;
                }
                
                board[row][col] = HUMAN;
                
                // فحص نهاية اللعبة بعد حركة الإنسان
                score = evaluate(board);
                if (score == -10) {
                    printBoard(board);
                    System.out.println("\n🎉 أنت فزت!");
                    break;
                }
                if (!isMovesLeft(board)) {
                    printBoard(board);
                    System.out.println("\n🤝 تعادل!");
                    break;
                }
                
                // دور AI
                int[] bestMove = findBestMove(board);
                board[bestMove[0]][bestMove[1]] = AI;
            }
        }
        System.out.println("\n🎮 انتهت اللعبة!");
    }
}