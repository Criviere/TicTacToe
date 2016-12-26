
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author Crivi002
 */
public class TicTacToe {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        final int SIZE = 3;

        char[][] board = new char[SIZE][SIZE]; //Game board.

        resetBoard(board); //initialize the board with '' for all cells

        //First, welcome message and display the board.
        System.out.println("===== WELCOME TO TIC-TAC-TOE! READY TO PLAY? =====\n");

        //Query the user and ask which symbol he/she wants to play (x or o).
        System.out.print("  Which symbol do you want to play, \"x\" or \"o\"? ");
        char userSymbol = sc.next().toLowerCase().charAt(0);
        char compSymbol = (userSymbol == 'x') ? 'o' : 'x';

        //Now, ask the user whether he/she wants to go first.
        System.out.println();
        System.out.print("     Do you want to go first(y/n)?");
        char ans = sc.next().toLowerCase().charAt(0);

        int turn; // 0 -- the user, 1 -- the computer
        int remainCount = SIZE * SIZE; //empty cell count;

        //THE VERY FIRST MOVE.
        if (ans == 'y') {
            turn = 0;
            userPlay(board, userSymbol); //User puts his/her first tic
        } else {
            turn = 1;
            userTwoPlay(board, compSymbol); //The computer puts his/her first tic.
        }
        //Show the board, and decrement the count of remaining cells.
        showBoard(board);
        remainCount--;

        //Play the game until one wins.
        boolean done = false;
        int winner = -1; //0 -- the user, 1 -- the computer, -1 -- draw

        while (!done && remainCount > 0) {
            //If there is a winner at this time, set the winner and the done flag to true.

            done = isGameWon(board, turn, userSymbol, compSymbol); // did the user or comp's turn win the game?

            if (done) {
                winner = turn; //The player that made the last move won the game.
            } else {
                //No winner yet. Find the next turn and play.
                turn = (turn + 1) % 2;
                if (turn == 0) {
                    userPlay(board, userSymbol);
                } else {
                    userTwoPlay(board, compSymbol);
                }

                //Show the board after one tic, and decrement the remaining count.
                showBoard(board);
                remainCount--;
            }
        }
        //Winner is found. Declare the winner.
        switch (winner) {
            case 0:
                System.out.println("\n** YOU WON. CONGRATULATIONS!! **");
                break;
            case 1:
                System.out.println("\n** YOU LOST. Maybe next time :) **");
                break;
            default:
                System.out.println("\n** DRAW...**");
                break;
        }
    }

    public static void resetBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void showBoard(char[][] board) {
        int numRow = board.length;
        int numCol = board[0].length;
        System.out.println();

        //First write the column header
        System.out.println("    ");
        for (int i = 0; i < numCol; i++) {
            System.out.print(i + "   ");
        }
        System.out.println("\n");
        System.out.println(); //blank line after the header

        //Write the table
        for (int i = 0; i < numRow; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < numCol; j++) {
                if (j != 0) {
                    System.out.print("|");
                }
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
            if (i != (numRow - 1)) {
                //Line Separator
                System.out.print("   ");
                for (int j = 0; j < numCol; j++) {
                    if (j != 0) {
                        System.out.print("+");
                    }
                    System.out.print("---");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void userPlay(char[][] board, char userSym) {

        System.out.println("\nPlayer 1, Enter the row and column indices: ");
        int rowIndex = sc.nextInt();
        int colIndex = sc.nextInt();

        while (board[rowIndex][colIndex] != ' ') {
            System.out.println("\n The cell is already taken. \n Enter the row and column index");
            rowIndex = sc.nextInt();
            colIndex = sc.nextInt();
        }
        board[rowIndex][colIndex] = userSym;
        //Check win 
    }

    public static void userTwoPlay(char[][] board, char userSym) {

        System.out.println("\nPlayer 2, Enter the row and column indices: ");
        int rowIndex = sc.nextInt();
        int colIndex = sc.nextInt();

        while (board[rowIndex][colIndex] != ' ') {
            System.out.println("\n The cell is already taken. \n Enter the row and column index");
            rowIndex = sc.nextInt();
            colIndex = sc.nextInt();
        }
        board[rowIndex][colIndex] = userSym;
    }

    public static boolean isGameWon(char[][] board, int turn, char userSymbol, char userTwoSymbol) {
        char sym;
        if (turn == 0) {
            sym = userSymbol;
        } else {
            sym = userTwoSymbol;
        }
        int i, j;
        boolean win = false;

        //Check win by row
        for (i = 0; i < board.length && !win; i++) {
            for (j = 0; j < board[0].length; j++) {
                if (board[i][j] != sym) {
                    break;
                }
            }
            if (i == board.length) {
                win = true;
            }
        }
        //Check win by a column
        for (j = 0; j < board[0].length && !win; j++) {
            for (i = 0; i < board.length; i++) {
                if (board[i][j] != sym) {
                    break;
                }
            }
            if (i == board.length) {
                win = true;
            }
        }

        // Check win by a diagnol
        if (!win) {
            for (i = 0; i < board.length; i++) {
                if (board[i][board.length - 1 - i] != sym) {
                    break;
                }
            }
            if (i == board.length) {
                win = true;
            }

            //Finally return win
        }
        return win;
    }
}
