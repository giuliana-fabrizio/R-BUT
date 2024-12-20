package control;

import model.KamisadoPawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDeciderFetchGoodPose extends TestDecider{

    @Test
    public void testFetchGoodPoseLooseNextMove(){
        stage.getBoard().putElement(pawnWhite1, 2,0);
        stage.getBoard().putElement(pawnWhite8, 7,7);

        stage.getBoard().putElement(pawnBlack1, 0,7);
        stage.getBoard().putElement(pawnBlack2, 0,6);
        stage.getBoard().putElement(pawnBlack3, 0,5);
        stage.getBoard().putElement(pawnBlack4, 0,4);
        stage.getBoard().putElement(pawnBlack5, 0,3);
        stage.getBoard().putElement(pawnBlack6, 0,2);
        stage.getBoard().putElement(pawnBlack7, 0,1);
        stage.getBoard().putElement(pawnBlack8, 0,0);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        decider.pawn=pawnWhite1;

        decider.colDest=0;
        decider.rowDest=2;

        lst = stage.getBoard().computeValidCells(pawnWhite1);

        decider.fetchGoodPose(lst,1,1,2,0);

        Assertions.assertEquals(2,decider.rowDest);
        Assertions.assertEquals(0,decider.colDest);
    }

    @Test
    public void testFetchGoodPoseCanPlayAMove() {
        stage.getBoard().putElement(pawnWhite1, 2, 0);
        stage.getBoard().putElement(pawnWhite2, 6, 0);
        stage.getBoard().putElement(pawnWhite8, 7, 7);

        stage.getBoard().putElement(pawnBlack1, 0, 7);
        stage.getBoard().putElement(pawnBlack2, 0, 6);
        stage.getBoard().putElement(pawnBlack3, 0, 5);
        stage.getBoard().putElement(pawnBlack4, 0, 4);
        stage.getBoard().putElement(pawnBlack5, 0, 3);
        stage.getBoard().putElement(pawnBlack6, 0, 2);
        stage.getBoard().putElement(pawnBlack7, 0, 1);
        stage.getBoard().putElement(pawnBlack8, 0, 0);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        decider.pawn = pawnWhite1;

        decider.colDest = 0;
        decider.rowDest = 2;

        lst = stage.getBoard().computeValidCells(pawnWhite1);

        decider.fetchGoodPose(lst, 1, 1, 2, 0);

        Assertions.assertEquals(1, decider.rowDest);
        Assertions.assertEquals(1, decider.colDest);
    }

    @Test
    public void testFetchGoodPoseNotAValidCell() {
        stage.getBoard().putElement(pawnWhite1, 2, 0);
        stage.getBoard().putElement(pawnWhite2, 6, 0);
        stage.getBoard().putElement(pawnWhite8, 7, 7);

        stage.getBoard().putElement(pawnBlack1, 0, 7);
        stage.getBoard().putElement(pawnBlack2, 0, 6);
        stage.getBoard().putElement(pawnBlack3, 0, 5);
        stage.getBoard().putElement(pawnBlack4, 0, 4);
        stage.getBoard().putElement(pawnBlack5, 0, 3);
        stage.getBoard().putElement(pawnBlack6, 0, 2);
        stage.getBoard().putElement(pawnBlack7, 0, 1);
        stage.getBoard().putElement(pawnBlack8, 0, 0);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        decider.pawn = pawnWhite1;

        decider.colDest = 0;
        decider.rowDest = 2;

        lst = stage.getBoard().computeValidCells(pawnWhite1);

        decider.fetchGoodPose(lst, 5, 5, 2, 0);

        Assertions.assertEquals(2, decider.rowDest);
        Assertions.assertEquals(0, decider.colDest);
    }
}