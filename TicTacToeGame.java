import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int playerWins = 0;
        int computerWins = 0;
        int draws = 0;

        for (int gameCount = 1; gameCount <= 5; gameCount++)
         {
            char playerSymbol = getPlayerSymbol(scanner);
            char computerSymbol = (playerSymbol == 'X') ? 'O' : 'X';

            char[][] board = initializeBoard();
            boolean isPlayerTurn = true;

            System.out.println("Game " + gameCount + ": Deblina (" + playerSymbol + ") vs Computer (" + computerSymbol + ")");

            while (true) {
                printBoard(board);

                if (isPlayerTurn) {
                    playerMove(board, scanner, playerSymbol);
                    if (checkWin(board, playerSymbol)) 
                    {
                        printBoard(board);
                        System.out.println("Deblina wins!");
                        playerWins++;
                        break;
                    }
                }
                 else
                  {
                    computerMove(board, random, computerSymbol);
                    if (checkWin(board, computerSymbol)) {
                        printBoard(board);
                        System.out.println("Computer wins!");
                        computerWins++;
                        break;
                    }
                }

                if (isBoardFull(board)) 
                {
                    printBoard(board);
                    System.out.println("It's a draw!");
                    draws++;
                    break;
                }

                isPlayerTurn = !isPlayerTurn;
            }
        }

        System.out.println("Final Result:");
        System.out.println("Deblina: " + playerWins + " wins");
        System.out.println("Computer: " + computerWins + " wins");
        System.out.println("Draws: " + draws);
    }

    public static char[][] initializeBoard() 
    {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    public static void printBoard(char[][] board) 
    {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char getPlayerSymbol(Scanner scanner)
     {
        char symbol;
        while (true) {
            System.out.print("Choose your symbol ('X' or 'O'): ");
            String input = scanner.next().toUpperCase();
            if (input.equals("X") || input.equals("O")) 
            {
                symbol = input.charAt(0);
                break;
            }
             else
              {
                System.out.println("Invalid choice. Please enter 'X' or 'O'.");
            }
        }
        return symbol;
    }

    public static void playerMove(char[][] board, Scanner scanner, char playerSymbol)
     {
        int row, col;
        while (true) {
            System.out.print("Enter your move (row [0-2] and column [0-2]): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (isValidMove(board, row, col)) 
            {
                board[row][col] = playerSymbol;
                break;
            } 
            else
             {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    public static void computerMove(char[][] board, Random random, char computerSymbol)
     {
        int row, col;
        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);
            if (isValidMove(board, row, col))
             {
                board[row][col] = computerSymbol;
                break;
            }
        }
        System.out.println("Computer chooses row " + row + " and column " + col);
    }

    public static boolean isValidMove(char[][] board, int row, int col) 
    {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public static boolean checkWin(char[][] board, char symbol) 
    {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
             {
                return true; 
            }
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)
             {
                return true; 
            }
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) 
        {
            return true; 
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
         {
            return true; 
        }
        return false;
    }

    public static boolean isBoardFull(char[][] board)
     {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
             {
                if (board[i][j] == ' ')
                 {
                    return false;
                }
            }
        }
        return true;
    }
}
