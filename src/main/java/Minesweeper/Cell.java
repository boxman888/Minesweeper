package main.java.Minesweeper;

public class Cell {
    private int row;
    private int col;
    private int bombsNear;
    private boolean isBomb;
    private boolean isShown;
    private boolean flagged;

    /* Initializer for a Cell*/
    public Cell(int r, int c){
        row = r;
        col = c;
        bombsNear = 0;
        isBomb = false;
        isShown = false;
        flagged = false;
    }
    /* Set the location of a cell*/
    public void setLocation(int r, int c){
        row = r;
        col = c;
    }
    /* Set the cell as a bomb*/
    public void setAsBomb(){
        isBomb = true;
        bombsNear = -1;
    }
    /* Increment the the number of bombs near the cell*/
    public void incrementBombCount(){
        bombsNear++;
    }
    /* Toggle a flag on or off a cell*/
    public void toggleFlag(){
        if (flagged)
            flagged = false;
        else
            flagged = true;
    }
    /* Mark a cell as visible to player*/
    public void setAsShown(){
        isShown = true;
    }
    /*  Get the row of the cell*/
    public int getRow(){
        return row;
    }
    /* Get the col of the cell*/
    public int getCol(){
        return col;
    }
    /* Show the symbol state to the player*/
    public String getVisibleState() {
        if (isShown){
            return getHiddenState();
        }else if (flagged){
            return "f ";
        } else {
            return "  ";
        }
    }
    public String getHiddenState(){
        if (isBomb){
            return "B ";
        } else if (bombsNear > 0) {
            return bombsNear + " ";
        }else{
            return "  ";
        }
    }
}
