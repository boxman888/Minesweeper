package main.java.Minesweeper;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void setLocation() {
        Cell cell = new Cell(0,0);
        Assert.assertEquals(cell.getRow(), 0);
    }

    @Test
    public void toggleFlag(){
        Cell cell = new Cell(0,0);

        cell.toggleFlag();
        Assert.assertEquals(cell.getVisibleState(), "f ");
        cell.toggleFlag();
        Assert.assertEquals(cell.getVisibleState(), "  ");
    }
}