package main.java.Minesweeper;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void printBoard() {
        int rows = 14;
        int cols = 18;
        int bombs = (int) (Math.ceil(rows * cols * 0.15));
        Board board = new Board(rows, cols, bombs);
        board.printBoard(true);
        board.printBoard(false);
    }

}