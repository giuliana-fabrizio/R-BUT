package control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestDeciderWinCell extends TestDecider{
    private Point point;

    @Test
    public void testWinCellBlackPawnReturnNull(){
        stage.getBoard().putElement(pawnBlack1, 4,3);
        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 7,3);
        stage.getBoard().putElement(pawnWhite3, 7,6);

        Assertions.assertEquals(point, decider.winCell(pawnBlack1));
    }

    @Test
    public void testWinCellWhitePawnReturnNull(){
        stage.getBoard().putElement(pawnWhite1, 2,5);
        stage.getBoard().putElement(pawnBlack1, 0,5);
        stage.getBoard().putElement(pawnBlack2, 0,7);
        stage.getBoard().putElement(pawnBlack3, 0,3);

        Assertions.assertEquals(point, decider.winCell(pawnWhite1));
    }

    @Test
    public void testWinCellWhitePawnStraight(){
        point = new Point(0,5);
        stage.getBoard().putElement(pawnWhite1, 2,5);

        stage.getBoard().putElement(pawnBlack2, 0,7);
        stage.getBoard().putElement(pawnBlack3, 0,3);

        Assertions.assertEquals(point, decider.winCell(pawnWhite1));
    }

    @Test
    public void testWinCellBlackPawnDiagonalLeft(){
        point = new Point(7, 7);
        stage.getBoard().putElement(pawnBlack1, 0,0);

        stage.getBoard().putElement(pawnWhite3, 7,0);

        Assertions.assertEquals(point, decider.winCell(pawnBlack1));
    }

    @Test
    public void testWinCellBlackPawnDiagonalRight(){
        point = new Point(7,3);
        stage.getBoard().putElement(pawnBlack1, 4,0);

        stage.getBoard().putElement(pawnWhite2, 5,0);

        Assertions.assertEquals(point, decider.winCell(pawnBlack1));
    }

    @Test
    public void testWinCellPawnCanWin(){
        stage.getBoard().putElement(pawnWhite1, 1,2);

        stage.getBoard().putElement(pawnBlack1, 0,3);
        stage.getBoard().putElement(pawnBlack2, 0,1);

        lst=stage.getBoard().computeValidCells(pawnWhite1);

        Point response = decider.winCell(lst);
        Point await = new Point(0,2);

        Assertions.assertEquals(await, response);
    }

    @Test
    public void testWinCellPawnCantWin(){
        stage.getBoard().putElement(pawnWhite1, 2,0);

        stage.getBoard().putElement(pawnBlack1, 0,2);
        stage.getBoard().putElement(pawnBlack2, 0,0);

        lst=stage.getBoard().computeValidCells(pawnWhite1);

        Point response = decider.winCell(lst);

        Assertions.assertEquals(null, response);
    }
}
