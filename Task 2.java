import java.util.Scanner;

public class TicTacToeAI {

    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    
    private char[][] board = new char[3][3];
    private char currentPlayer = PLAYER_X;

    public static void main(String[] args) {
        TicTacToeAI game = new TicTacToeAI();
        game.playGame();
    }

    public TicTacToeAI() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("--|---|--");
        }
    }

    private void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printBoard();

            if (currentPlayer == PLAYER_X) {
                System.out.println("Player X's turn.");
                int row, col;
                while (true) {
                    System.out.print("Enter row and column (0-2): ");
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                    if (isValidMove(row, col)) {
                        board[row][col] = PLAYER_X;
                        break;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                }
            } else {
                System.out.println("Player O (AI) is making a move.");
                int[] move = findBestMove();
                board[move[0]][move[1]] = PLAYER_O;
            }

            if (checkWin(PLAYER_X)) {
                printBoard();
                System.out.println("Player X wins!");
                running = false;
            } else if (checkWin(PLAYER_O)) {
                printBoard();
                System.out.println("Player O wins!");
                running = false;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw!");
                running = false;
            } else {
                currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
            }
        }

        scanner.close();
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY;
    }

    private boolean checkWin(char player) {
        // Check rows, columns, and diagonals
        return (checkRows(player) || checkCols(player) || checkDiagonals(player));
    }

    private boolean checkRows(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCols(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(char player) {
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] findBestMove() {
        int bestValue = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = PLAYER_O;
                    int moveValue = minimax(false);
                    board[i][j] = EMPTY;
                    if (moveValue > bestValue) {
                        bestValue = moveValue;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(boolean isMaximizing) {
        if (checkWin(PLAYER_O)) {
            return 10;
        } else if (checkWin(PLAYER_X)) {
            return -10;
        } else if (isBoardFull()) {
            return 0;
        }

        int bestValue = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = isMaximizing ? PLAYER_O : PLAYER_X;
                    int currentValue = minimax(!isMaximizing);
                    board[i][j] = EMPTY;
                    if (isMaximizing) {
                        bestValue = Math.max(bestValue, currentValue);
                    } else {
                        bestValue = Math.min(bestValue, currentValue);
                    }
                }
            }
        }
        return bestValue;
    }
}
