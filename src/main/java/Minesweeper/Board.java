package main.java.Minesweeper;

import java.util.Random;

public class Board {
    private int rows;
    private int cols;
    private int bombCount;
    private int cellsRemaining;

    private Cell cells[][];
    private Cell bombs[];

    /* Initialize the board. */
    public Board(int r, int c, int bCount) {
        rows = r;
        cols = c;
        bombCount = bCount;
        cellsRemaining = rows * cols - bombCount;

        BuildBoard();
        setBombsNearCells();
    }
    /* Get the number of cells remaining. */
    public int getCellsRemaining(){
        return cellsRemaining;
    }
    /* Build the board */
    private void BuildBoard() {
        cells = new Cell[rows][cols];
        bombs = new Cell[bombCount];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        int c, r;
        Random rand = new Random();
        for (int i = 0; i < bombCount; i++) {
            c = rand.nextInt(cols);
            r = rand.nextInt(rows);

            if(cells[r][c].checkIfBomb()) {
                i--;
                continue;
            }
            cells[r][c].setAsBomb();
            bombs[i] = cells[r][c];
        }
    }
    /* Check if a position is legal move. */
    private boolean checkBounds(int r, int c) {
        return r < rows && r >= 0 && c < cols && cols >= 0;
    }

    /* Kernel frame for managing cells around a bomb. */
    private void bombKernelFrame(int r, int c) {
        int rn[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        int cn[] = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++)
            if(checkBounds(r + rn[i], c + cn[i]))
                cells[r + rn[i]][c + cn[i]].incrementBombCount();

    }
    /* Let other cells know they are  neighbors to a bomb. */
    private void setBombsNearCells(){
        for (int i = 0; i < bombCount; i++) {
            bombKernelFrame(bombs[i].getRow(), bombs[i].getCol());
        }
    }
}
