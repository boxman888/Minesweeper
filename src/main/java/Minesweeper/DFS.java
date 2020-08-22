package main.java.Minesweeper;

public class DFS {
    private int target;
    private int found;
    private int row;
    private int col;

    private final int rowMV[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int colMV[] = {-1, 0,  1, -1, 1, -1, 0, 1};

    private Board board;

    /* DFS constructor. */
    public DFS(Board b, int t){
        board = b;
        target = t;
        found = 0;
    }
    /* Set the starting location for the DFS search. */
    public void setLocation(int r, int c){
        row = r;
        col = c;
    }
    /* Find the target cells */
    public int findTargetCells(){
        if(board.getCell(row,col).getBombsNear() == target &&
                board.getCell(row,col).getIsShown()){
            dfs(row, col);
        }

        return found;
    }
    /* Check if dfs is in bounds of the board. */
    private boolean legalMove(int r, int c){
        if ( 0 <= r  && r < board.getRows() &&
                0 <= c && c < board.getCols()) {
            return true;
        }

        return false;
    }

    /* Recursive utility for dfs search */
    private void dfs(int r, int c){
        found++;
        board.getCell(r, c).setAsShown();
        for (int i = 0; i < 8; i++){
            int rmv = r + rowMV[i];
            int cmv = c + colMV[i];

            if (legalMove(rmv,cmv)) {
                board.getCell(rmv, cmv).setAsShown();
                if (board.getCell(rmv, cmv).getBombsNear() == target &&
                    !board.getCell(rmv, cmv).getIsShown())
                        dfs(rmv, cmv);
            }
        }
    }
}
