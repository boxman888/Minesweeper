package main.java.Minesweeper;

import java.util.Scanner;

public class GameDriver {

    public enum GameDifficulty{  EASY, MEDIUM, HARD, DEBUG };
    public enum GameState {EXIT, LOST, WON, RUNNING };

    private int rows;
    private int cols;
    private int bombs;

    private Board board;
    private GameDifficulty difficulty;
    private GameState gameState;

    /* Game driver*/
    public GameDriver(){
        gameState = Init(14, 18);
    }
    /* Initialize Game */
    public GameState Init(int r, int c){
        String input;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter number difficulty { Easy, Medium, Hard }");
        System.out.print(": ");

        rows = r;
        cols = c;
        input = scan.next();
        return setDifficulty(input);
    }

    public GameState setDifficulty(String input){
        switch (input.toUpperCase()){
            case "EASY":
                difficulty = GameDifficulty.EASY;
                bombs = (int) Math.ceil(rows * cols * 0.10);
                board = new Board(rows, cols, bombs);
                return GameState.RUNNING;
            case "MEDIUM":
                difficulty = GameDifficulty.MEDIUM;
                bombs = (int) Math.ceil(rows * cols * 0.15);
                board = new Board(rows, cols, bombs);
                return GameState.RUNNING;
            case "HARD":
                difficulty = GameDifficulty.HARD;
                bombs = (int) Math.ceil(rows * cols * 0.25);
                board = new Board(rows, cols, bombs);
                return GameState.RUNNING;
            case "DEBUG":
                difficulty = GameDifficulty.DEBUG;
                bombs = (int) Math.ceil(rows * cols * 0.25);
                board = new Board(rows, cols, bombs);
                return GameState.RUNNING;
            default:
                System.out.println("Not a difficulty.");
                return GameState.EXIT;
        }
    }

    /* Get players move */
    public void getPlayersMove(Scanner userInput){
        String input;
        int r, c;

        do { // Keep asking if user tries to access an invalid cell.
            System.out.print("Enter row: ");
            input = userInput.next();
            r = Integer.parseInt(input);

            System.out.print("Enter col: ");
            input = userInput.next();
            c = Integer.parseInt(input);
        } while (r <= 0 && r > rows && c <= 0 && c > cols);

        if (board.flipCell(r, c)){
            gameState = GameState.LOST;
        }
    }

    /* Print out stats of the game */
    public void printStats(){
        System.out.println("Difficulty:\t\t\t" + difficulty);
        System.out.println("Total cells:\t\t" + rows * cols);
        System.out.println("Total bombs:\t\t" + bombs);
        System.out.println("Cells Remaining:\t" + board.getCellsRemaining());
    }

    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);

        GameDriver game = new GameDriver();
        while (game.gameState == GameState.RUNNING){
            game.printStats();
            game.board.printBoard(false);
            if (game.difficulty == GameDifficulty.DEBUG)
                game.board.printBoard(true);
            game.getPlayersMove(userInput);
        }
        game.board.printBoard(true);

        if (game.gameState == GameState.LOST){
            System.out.println("You lose. ");
        }
        else if (game.gameState == GameState.LOST){
            System.out.println("You win. ");
        }

    }
}
