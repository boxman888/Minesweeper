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

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public Cell getCell(int r, int c){
        return cells[r][c];
    }

    /* Get the number of cells remaining. */
    public int getCellsRemaining(){
        return cellsRemaining;
    }

    public void printBoard(boolean showHidden){
        System.out.println("\n");
        System.out.print("   ");
        for (int i = 0; i < cols; i++)
            System.out.print(i + "  ");
        System.out.println();

        for (int i = 0; i < rows; i++){
            System.out.print(i + "| ");
            for (int j = 0; j < cols; j++){
                if (j >= 10)
                    System.out.print(" ");

                if (showHidden){
                    System.out.print(cells[i][j].getHiddenState());
                }
                else{
                    System.out.print(cells[i][j].getVisibleState());
                }
            }
            System.out.println();
        }
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
            bombs[i] = cells[r][c];
            bombs[i].setAsBomb();
        }
    }

    /* Set the state of a cell as fliped*/
    public boolean flipCell(int r, int c){
        if (!checkBounds(r, c))
            System.out.println("Not a valid location.");

        cells[r][c].setAsShown();
        if (cells[r][c].getBombsNear() == 0){
            DFS search = new DFS(this, 0);
            search.setLocation(r,c);
            search.findTargetCells();
        }
        else if (cells[r][c].getIsBomb()){
            return true;
        }

        return false;
    }

    /* Check if a position is legal move. */
    private boolean checkBounds(int r, int c) {
        return r < rows && r >= 0 && c < cols && c >= 0;
    }

    /* Kernel frame for managing cells around a bomb. */
    private void bombKernelFrame(int r, int c) {
        int rn[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        int cn[] = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++)
            if (checkBounds(r + rn[i], c + cn[i]))
                cells[r + rn[i]][c + cn[i]].incrementBombCount();
    }
    /* Let other cells know they are  neighbors to a bomb. */
    private void setBombsNearCells(){
        for (int i = 0; i < bombCount; i++) {
            bombKernelFrame(bombs[i].getRow(), bombs[i].getCol());
        }
    }

}
