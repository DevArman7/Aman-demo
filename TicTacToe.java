import java.util.InputMismatchException;
import java.util.*;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            initializeBoard();
            Player p1 = new HumanPlayer('X');
            Player p2 = selectMode() == 1 ? new HumanPlayer('O') : new AIPlayer('O');

            Player current = p1;
            while (true) {
                printBoard();
                current.makeMove();

                if (checkWin(current.getSymbol())) {
                    printBoard();
                    System.out.println("Player " + current.getSymbol() + " wins!");
                    break;
                }
                if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }
                current = (current == p1) ? p2 : p1;
            }
        } while (playAgain());
        sc.close();
    }

    // Choose game mode
    static int selectMode() {
        while (true) {
            System.out.println("Select mode: 1) Two-player  2) Play vs AI");
            System.out.print("Enter 1 or 2: ");
            try {
                int mode = sc.nextInt();
                if (mode == 1 || mode == 2) return mode;
            } catch (InputMismatchException e) {
                sc.next(); // discard invalid token
            }
            System.out.println("Invalid choice. Try again.");
        }
    }

    // Ask to play again
    static boolean playAgain() {
        System.out.print("Play again? (Y/N): ");
        String ans = sc.next().trim().toUpperCase();
        return ans.equals("Y");
    }

    // Reset board to spaces
    static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    // Display with 1-based coordinates
    static void printBoard() {
        System.out.println("\n   1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("   -----");
        }
    }

    // Overall win check
    static boolean checkWin(char sym) {
        return checkRows(sym) || checkCols(sym) || checkDiags(sym);
    }

    static boolean checkRows(char sym) {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == sym && board[i][1] == sym && board[i][2] == sym)
                return true;
        return false;
    }

    static boolean checkCols(char sym) {
        for (int j = 0; j < 3; j++)
            if (board[0][j] == sym && board[1][j] == sym && board[2][j] == sym)
                return true;
        return false;
    }

    static boolean checkDiags(char sym) {
        return (board[0][0] == sym && board[1][1] == sym && board[2][2] == sym) ||
               (board[0][2] == sym && board[1][1] == sym && board[2][0] == sym);
    }

    static boolean isBoardFull() {
        for (char[] row : board)
            for (char c : row)
                if (c == ' ')
                    return false;
        return true;
    }

    // Abstract Player
    static abstract class Player {
        protected char symbol;
        Player(char symbol) { this.symbol = symbol; }
        char getSymbol() { return symbol; }
        abstract void makeMove();
    }

    // Human move with validation
    static class HumanPlayer extends Player {
        HumanPlayer(char symbol) { super(symbol); }

        @Override
        void makeMove() {
            while (true) {
                System.out.print("Player " + symbol + " - enter row and column (1-3): ");
                try {
                    int r = sc.nextInt(), c = sc.nextInt();
                    if (r < 1 || r > 3 || c < 1 || c > 3 || board[r-1][c-1] != ' ') {
                        System.out.println("Invalid move. Try again.");
                    } else {
                        board[r-1][c-1] = symbol;
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter two numbers between 1 and 3.");
                    sc.next(); // discard invalid token
                }
            }
        }
    }

    // AI move via Minimax
    static class AIPlayer extends Player {
        AIPlayer(char symbol) { super(symbol); }

        @Override
        void makeMove() {
            int[] move = findBestMove();
            board[move[0]][move[1]] = symbol;
            System.out.printf("AI (%c) chooses %d %d%n", symbol, move[0]+1, move[1]+1);
        }

        // Evaluate best move
        private int[] findBestMove() {
            int bestScore = Integer.MIN_VALUE;
            int[] bestMove = {-1, -1};

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = symbol;
                        int score = minimax(0, false);
                        board[i][j] = ' ';
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = new int[]{i, j};
                        }
                    }
                }
            }
            return bestMove;
        }

        private int minimax(int depth, boolean isMaximizing) {
            char opponent = (symbol == 'X') ? 'O' : 'X';
            if (checkWin(symbol)) return 10 - depth;
            if (checkWin(opponent)) return depth - 10;
            if (isBoardFull()) return 0;

            int best = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            char currentSym = isMaximizing ? symbol : opponent;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = currentSym;
                        int score = minimax(depth + 1, !isMaximizing);
                        board[i][j] = ' ';
                        best = isMaximizing
                             ? Math.max(best, score)
                             : Math.min(best, score);
                    }
                }
            }
            return best;
        }
    }
}