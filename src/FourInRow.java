
import java.util.Scanner;

public class FourInRow  {

    // Constants for the grid size and number of consecutive tokens needed to win
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final int TOKENS_TO_WIN = 4;

    // 2D array to represent the game grid
    private static char[][] grid = new char[ROWS][COLUMNS];

    // Enum to represent the two players
    private enum Player {
        RED, YELLOW
    }

    // Current player
    private static Player currentPlayer = Player.RED;

    public static void main(String[] args) {
        // Initialize the game grid
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = ' ';
            }
        }

        // Loop until the game is won or the grid is full
        while (true) {
            // Print the current state of the grid
            printGrid();

            // Prompt the current player to make a move
            System.out.print("Player " + currentPlayer + ", enter a column number: ");
            Scanner scanner = new Scanner(System.in);
            int column = scanner.nextInt();

            // Validate the input and place the token in the selected column
            if (column < 0 || column >= COLUMNS || grid[0][column] != ' ') {
                System.out.println("Invalid move, try again.");
                continue;
            }
            int row = ROWS - 1;
            while (row > 0 && grid[row - 1][column] == ' ') {
                row--;
            }
            grid[row][column] = currentPlayer == Player.RED ? 'R' : 'Y';

            // Check if the current player has won
            if (checkWin(row, column)) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            // Switch to the other player
            currentPlayer = currentPlayer == Player.RED ? Player.YELLOW : Player.RED;

            // Check if the grid is full
            if (isFull()) {
                System.out.println("The game is a draw.");
                break;
            }
        }
    }

    // Prints the current state of the game grid
    private static void printGrid() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("|");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(grid[i][j] + "|");
            }
            System.out.println();
        }
    }

    // Returns true if the game grid is full, false otherwise
    private static boolean isFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    // Returns true if the current player has won, false otherwise
    private static boolean checkWin(int row, int column) {
        char token = currentPlayer == Player.RED ? 'R' : 'Y';

        // Check for a horizontal win
        int count = 0;
        for (int i = 0; i < COLUMNS; i++) {
            if (grid[row][i] == token) {
                count++;
            } else {
                count = 0;
            }
            if (count == TOKENS_TO_WIN) {
                return true;
            }
        }

        // Check for a vertical win
        count = 0;
        for (int i = 0; i < ROWS; i++) {
            if (grid[i][column] == token) {
                count++;
            } else {
                count = 0;
            }
            if (count == TOKENS_TO_WIN) {
                return true;
            }
        }

        // Check for a diagonal win (top-left to bottom-right)
        count = 0;
        int i = row;
        int j = column;
        while (i > 0 && j > 0 && grid[i - 1][j - 1] == token) {
            i--;
            j--;
        }
        while (i < ROWS && j < COLUMNS && grid[i][j] == token) {
            count++;
            i++;
            j++;
        }
        if (count == TOKENS_TO_WIN) {
            return true;
        }

        // Check for a diagonal win (top-right to bottom-left)
        count = 0;
        i = row;
        j = column;
        while (i > 0 && j < COLUMNS - 1 && grid[i - 1][j + 1] == token) {
            i--;
            j++;
        }
        while (i < ROWS && j >= 0 && grid[i][j] == token) {
            count++;
            i++;
            j--;
        }
        if (count == TOKENS_TO_WIN) {
            return true;
        }

        // No win
        return false;
    }
}

