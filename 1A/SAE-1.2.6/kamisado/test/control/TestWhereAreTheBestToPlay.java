package control;

import model.KamisadoPawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.*;

public class TestWhereAreTheBestToPlay extends TestDecider {

    // true false
    @Test
    public void testWhereAreTheBestPlaceToPlayWinBlack() {
        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnBlack1;
        decider.IaPawn[1] = pawnBlack2;
        decider.IaPawn[2] = pawnBlack3;
        decider.IaPawn[3] = pawnBlack4;
        decider.IaPawn[4] = pawnBlack5;
        decider.IaPawn[5] = pawnBlack6;
        decider.IaPawn[6] = pawnBlack7;
        decider.IaPawn[7] = pawnBlack8;

        stage.setj1Pawns(decider.IaPawn);


        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 7,1);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 7,3);
        stage.getBoard().putElement(pawnWhite5, 4,7);
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

        stage.setj2Pawns(decider.opponentPawns);

        decider.pawn = pawnBlack4;

        lst = decider.board.computeValidCells(pawnBlack4);

        Point pts = new Point(6, 3);
        Assertions.assertEquals(pts, decider.whereAreTheBestPlaceToPlay(lst));
    }

    @Test
    public void testWhereAreTheBestPlaceToPlayWinWhite() {
        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 7,1);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 7,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,6);
        stage.getBoard().putElement(pawnWhite8, 7,7);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnWhite1;
        decider.IaPawn[1] = pawnWhite2;
        decider.IaPawn[2] = pawnWhite3;
        decider.IaPawn[3] = pawnWhite4;
        decider.IaPawn[4] = pawnWhite5;
        decider.IaPawn[5] = pawnWhite6;
        decider.IaPawn[6] = pawnWhite7;
        decider.IaPawn[7] = pawnWhite8;

        stage.setj2Pawns(decider.IaPawn);


        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 3,0);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        decider.opponentPawns = new KamisadoPawn[8];
        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        stage.setj1Pawns(decider.opponentPawns);

        decider.pawn = pawnWhite5;

        lst = decider.board.computeValidCells(pawnWhite5);

        Point pts = new Point(1, 4);
        Assertions.assertEquals(pts, decider.whereAreTheBestPlaceToPlay(lst));
    }

    @Test
    public void testWhereAreTheBestPlaceToPlayWinBlackTrueFalse() {
        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 6,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnBlack1;
        decider.IaPawn[1] = pawnBlack2;
        decider.IaPawn[2] = pawnBlack3;
        decider.IaPawn[3] = pawnBlack4;
        decider.IaPawn[4] = pawnBlack5;
        decider.IaPawn[5] = pawnBlack6;
        decider.IaPawn[6] = pawnBlack7;
        decider.IaPawn[7] = pawnBlack8;

        stage.setj1Pawns(decider.IaPawn);


        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 7,1);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 7,3);
        stage.getBoard().putElement(pawnWhite5, 4,7);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 3,2);
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

        stage.setj2Pawns(decider.opponentPawns);

        decider.pawn = pawnBlack2;

        lst = decider.board.computeValidCells(pawnBlack2);

        Point pts = new Point(4, 5);
        Assertions.assertFalse(pts.equals(decider.whereAreTheBestPlaceToPlay(lst)));
    }

    @Test
    public void testWhereAreTheBestPlaceToPlayWinWhiteTrueFalse() {
        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 7,1);
        stage.getBoard().putElement(pawnWhite3, 3,6);
        stage.getBoard().putElement(pawnWhite4, 7,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,6);
        stage.getBoard().putElement(pawnWhite8, 7,7);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnWhite1;
        decider.IaPawn[1] = pawnWhite2;
        decider.IaPawn[2] = pawnWhite3;
        decider.IaPawn[3] = pawnWhite4;
        decider.IaPawn[4] = pawnWhite5;
        decider.IaPawn[5] = pawnWhite6;
        decider.IaPawn[6] = pawnWhite7;
        decider.IaPawn[7] = pawnWhite8;

        stage.setj2Pawns(decider.IaPawn);

        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 3,6);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        decider.opponentPawns = new KamisadoPawn[8];
        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        stage.setj1Pawns(decider.opponentPawns);

        decider.pawn = pawnWhite7;

        lst = decider.board.computeValidCells(pawnWhite7);

        Point pts = new Point(6, 7);
        Assertions.assertFalse(pts.equals(decider.whereAreTheBestPlaceToPlay(lst)));
    }
}
