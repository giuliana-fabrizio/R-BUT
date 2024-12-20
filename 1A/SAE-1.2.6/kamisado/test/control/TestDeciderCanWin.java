package control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDeciderCanWin extends TestDecider{

    @Test
    public void testCanWinBlackPawnNotWin(){
        stage.getBoard().putElement(pawnBlack1, 4,3);

        stage.getBoard().putElement(pawnWhite1, 6,3);
        stage.getBoard().putElement(pawnWhite2, 7,0);
        stage.getBoard().putElement(pawnBlack2, 5,4);

        boolean response = decider.canWin(pawnBlack1);

        Assertions.assertEquals(false, response);
    }

    @Test
    public void testCanWinBlackPawnWinDiagonalsLeft(){
        stage.getBoard().putElement(pawnBlack1, 4,5);

        stage.getBoard().putElement(pawnWhite1, 6,5);

        boolean response = decider.canWin(pawnBlack1);

        Assertions.assertEquals(true, response);
    }

    @Test
    public void testCanWinBlackPawnWinDiagonalsRight(){
        stage.getBoard().putElement(pawnBlack1, 5,2);

        stage.getBoard().putElement(pawnWhite2, 7,0);
        stage.getBoard().putElement(pawnWhite1, 6,2);

        boolean response = decider.canWin(pawnBlack1);

        Assertions.assertEquals(true, response);
    }

    @Test
    public void testCanWinBlackPawnWinStraight(){
        stage.getBoard().putElement(pawnBlack1, 5,2);

        stage.getBoard().putElement(pawnWhite2, 7,0);
        stage.getBoard().putElement(pawnWhite1, 6,3);

        boolean response = decider.canWin(pawnBlack1);

        Assertions.assertEquals(true, response);
    }

    @Test
    public void testCanWinWhitePawnNotWin(){
        stage.getBoard().putElement(pawnWhite1, 3,6);

        stage.getBoard().putElement(pawnWhite2, 1,6);
        stage.getBoard().putElement(pawnBlack2, 2,5);

        boolean response = decider.canWin(pawnWhite1);

        Assertions.assertEquals(false, response);
    }

    @Test
    public void testCanWinWhitePawnWinDiagonalsLeft(){
        stage.getBoard().putElement(pawnWhite1, 2,6);

        stage.getBoard().putElement(pawnBlack1, 1,6);

        boolean response = decider.canWin(pawnWhite1);

        Assertions.assertEquals(true, response);
    }

    @Test
    public void testCanWinWhitePawnWinDiagonalsRight(){
        stage.getBoard().putElement(pawnWhite1, 1,2);

        stage.getBoard().putElement(pawnBlack1, 0,2);
        stage.getBoard().putElement(pawnBlack2, 0,1);

        boolean response = decider.canWin(pawnWhite1);

        Assertions.assertEquals(true, response);
    }

    @Test
    public void testCanWinWhitePawnWinStraight(){
        stage.getBoard().putElement(pawnWhite1, 1,2);

        stage.getBoard().putElement(pawnBlack1, 0,3);
        stage.getBoard().putElement(pawnBlack2, 0,1);

        boolean response = decider.canWin(pawnWhite1);

        Assertions.assertEquals(true, response);
    }
}
