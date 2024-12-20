package control;

import gamifier.control.Controller;
import gamifier.model.Model;
import model.KamisadoBoard;
import model.KamisadoPawn;
import model.KamisadoStageModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;


public class TestDeciderDeleteBadPosition extends TestDecider{

    @Test
    public void testDeleteBadPositionBlackPlayerEmptyList(){
        stage.getBoard().putElement(pawnBlack1, 1,0);

        stage.getBoard().putElement(pawnWhite1, 2,0);
        stage.getBoard().putElement(pawnWhite2, 2,1);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 7,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,6);
        stage.getBoard().putElement(pawnWhite8, 7,7);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnWhite1;
        decider.opponentPawns[1] = pawnWhite2;
        decider.opponentPawns[2] = pawnWhite3;
        decider.opponentPawns[3] = pawnWhite4;
        decider.opponentPawns[4] = pawnWhite5;
        decider.opponentPawns[5] = pawnWhite6;
        decider.opponentPawns[6] = pawnWhite7;
        decider.opponentPawns[7] = pawnWhite8;

        lst = stage.getBoard().computeValidCells(pawnBlack1);
        decider.deletbadposition(lst);

        Assertions.assertEquals(lstResults, lst);
    }

    @Test
    public void testDeleteBadPositionBlackPlayerNoWinOpponent(){
        stage.getBoard().putElement(pawnBlack1, 1,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        stage.getBoard().putElement(pawnWhite1, 3,0);
        stage.getBoard().putElement(pawnWhite2, 3,2);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 7,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,6);
        stage.getBoard().putElement(pawnWhite8, 7,7);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnWhite1;
        decider.opponentPawns[1] = pawnWhite2;
        decider.opponentPawns[2] = pawnWhite3;
        decider.opponentPawns[3] = pawnWhite4;
        decider.opponentPawns[4] = pawnWhite5;
        decider.opponentPawns[5] = pawnWhite6;
        decider.opponentPawns[6] = pawnWhite7;
        decider.opponentPawns[7] = pawnWhite8;

        lst = stage.getBoard().computeValidCells(pawnBlack1);
        decider.deletbadposition(lst);

        lstResults.add(new Point(2,0));
        lstResults.add(new Point(2,1));

        Assertions.assertEquals(lstResults, lst);
    }

    @Test
    public void testDeleteBadPositionBlackPlayerSomeWins(){
        stage.getBoard().putElement(pawnBlack1, 2,3);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,0);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        stage.getBoard().putElement(pawnWhite1, 4,1);
        stage.getBoard().putElement(pawnWhite2, 4,5);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 3,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,6);
        stage.getBoard().putElement(pawnWhite8, 2,5);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnWhite1;
        decider.opponentPawns[1] = pawnWhite2;
        decider.opponentPawns[2] = pawnWhite3;
        decider.opponentPawns[3] = pawnWhite4;
        decider.opponentPawns[4] = pawnWhite5;
        decider.opponentPawns[5] = pawnWhite6;
        decider.opponentPawns[6] = pawnWhite7;
        decider.opponentPawns[7] = pawnWhite8;

        lst = stage.getBoard().computeValidCells(pawnBlack1);
        decider.deletbadposition(lst);

        lstResults.add(new Point(3,2));

        Assertions.assertEquals(lstResults, lst);
    }

    @Test
    public void testDeleteBadPositionBlackPlayerAllWinsOpponent(){
        stage.getBoard().putElement(pawnBlack1, 2,3);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,0);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 5,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        stage.getBoard().putElement(pawnWhite1, 4,1);
        stage.getBoard().putElement(pawnWhite2, 1,6);
        stage.getBoard().putElement(pawnWhite3, 4,5);
        stage.getBoard().putElement(pawnWhite4, 3,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,1);
        stage.getBoard().putElement(pawnWhite8, 2,5);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnWhite1;
        decider.opponentPawns[1] = pawnWhite2;
        decider.opponentPawns[2] = pawnWhite3;
        decider.opponentPawns[3] = pawnWhite4;
        decider.opponentPawns[4] = pawnWhite5;
        decider.opponentPawns[5] = pawnWhite6;
        decider.opponentPawns[6] = pawnWhite7;
        decider.opponentPawns[7] = pawnWhite8;

        lst = stage.getBoard().computeValidCells(pawnBlack1);
        decider.deletbadposition(lst);

        Assertions.assertEquals(lstResults, lst);
    }

    @Test
    public void testDeleteBadPositionWhitePlayerEmptyList(){
        stage.getBoard().putElement(pawnWhite1, 5,0);

        stage.getBoard().putElement(pawnBlack1, 4,0);
        stage.getBoard().putElement(pawnBlack2, 4,1);
        stage.getBoard().putElement(pawnBlack3, 6,2);
        stage.getBoard().putElement(pawnBlack4, 6,3);
        stage.getBoard().putElement(pawnBlack5, 6,4);
        stage.getBoard().putElement(pawnBlack6, 6,5);
        stage.getBoard().putElement(pawnBlack7, 6,6);
        stage.getBoard().putElement(pawnBlack8, 6,7);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        lst = stage.getBoard().computeValidCells(pawnWhite1);
        decider.deletbadposition(lst);

        Assertions.assertEquals(lstResults, lst);
    }

    @Test
    public void testDeleteBadPositionWhitePlayerNoWinOpponent(){
        stage.getBoard().putElement(pawnBlack1, 1,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 1,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        stage.getBoard().putElement(pawnWhite1, 3,0);
        stage.getBoard().putElement(pawnWhite2, 7,1);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 7,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,6);
        stage.getBoard().putElement(pawnWhite8, 7,7);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnWhite1;
        decider.opponentPawns[1] = pawnWhite2;
        decider.opponentPawns[2] = pawnWhite3;
        decider.opponentPawns[3] = pawnWhite4;
        decider.opponentPawns[4] = pawnWhite5;
        decider.opponentPawns[5] = pawnWhite6;
        decider.opponentPawns[6] = pawnWhite7;
        decider.opponentPawns[7] = pawnWhite8;

        lst = stage.getBoard().computeValidCells(pawnWhite1);
        decider.deletbadposition(lst);

        lstResults.add(new Point(2,0));
        lstResults.add(new Point(2,1));

        Assertions.assertEquals(lstResults, lst);
    }

    @Test
    public void testDeleteBadPositionWhitePlayerAsIaSeveralCellsSameColorToRemove(){
        stage.getBoard().putElement(pawnBlack1, 0,7);
        stage.getBoard().putElement(pawnBlack2, 0,6);
        stage.getBoard().putElement(pawnBlack3, 0,5);
        stage.getBoard().putElement(pawnBlack4, 4,0);
        stage.getBoard().putElement(pawnBlack5, 0,3);
        stage.getBoard().putElement(pawnBlack6, 0,2);
        stage.getBoard().putElement(pawnBlack7, 0,1);
        stage.getBoard().putElement(pawnBlack8, 2,0);

        stage.getBoard().putElement(pawnWhite1, 7,7);
        stage.getBoard().putElement(pawnWhite2, 2,6);
        stage.getBoard().putElement(pawnWhite3, 7,5);
        stage.getBoard().putElement(pawnWhite4, 7,4);
        stage.getBoard().putElement(pawnWhite5, 3,3);
        stage.getBoard().putElement(pawnWhite6, 7,2);
        stage.getBoard().putElement(pawnWhite7, 7,1);
        stage.getBoard().putElement(pawnWhite8, 7,0);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        lst = stage.getBoard().computeValidCells(pawnWhite7);
        decider.deletbadposition(lst);

        lstResults.add(new Point(6,1));
        lstResults.add(new Point(4,1));
        lstResults.add(new Point(3,1));
        lstResults.add(new Point(2,1));
        lstResults.add(new Point(1,1));
        lstResults.add(new Point(5,3));
        lstResults.add(new Point(4,4));
        lstResults.add(new Point(3,5));
        lstResults.add(new Point(6,0));

        Assertions.assertEquals(lstResults, lst);

    }

    @Test
    public void testDeleteBadPositionBlackPlayerAsIaSeveralCellsSameColorToRemove(){
        stage.getBoard().putElement(pawnWhite1, 0,7);
        stage.getBoard().putElement(pawnWhite2, 0,6);
        stage.getBoard().putElement(pawnWhite3, 0,5);
        stage.getBoard().putElement(pawnWhite4, 4,0);
        stage.getBoard().putElement(pawnWhite5, 0,3);
        stage.getBoard().putElement(pawnWhite6, 0,2);
        stage.getBoard().putElement(pawnWhite7, 0,1);
        stage.getBoard().putElement(pawnWhite8, 2,0);

        stage.getBoard().putElement(pawnBlack1, 7,7);
        stage.getBoard().putElement(pawnBlack2, 2,6);
        stage.getBoard().putElement(pawnBlack3, 7,5);
        stage.getBoard().putElement(pawnBlack4, 7,4);
        stage.getBoard().putElement(pawnBlack5, 3,3);
        stage.getBoard().putElement(pawnBlack6, 7,2);
        stage.getBoard().putElement(pawnBlack7, 7,1);
        stage.getBoard().putElement(pawnBlack8, 7,0);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        lst = stage.getBoard().computeValidCells(pawnBlack7);
        decider.deletbadposition(lst);

        lstResults.add(new Point(6,1));
        lstResults.add(new Point(4,1));
        lstResults.add(new Point(3,1));
        lstResults.add(new Point(2,1));
        lstResults.add(new Point(1,1));
        lstResults.add(new Point(5,3));
        lstResults.add(new Point(4,4));
        lstResults.add(new Point(3,5));
        lstResults.add(new Point(6,0));

        Assertions.assertEquals(lstResults, lst);

    }

}
