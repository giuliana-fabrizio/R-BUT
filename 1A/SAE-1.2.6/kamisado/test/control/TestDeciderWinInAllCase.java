package control;

import model.Color;
import model.KamisadoPawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.ArgumentMatchers.any;

public class TestDeciderWinInAllCase extends TestDecider{
    private Point point;


    @Test
    public void testWinInAllCaseOneChoiceAiAndNoMoveOpponentNoWin(){
        stage.getBoard().putElement(pawnWhite1, 7,7);
        decider.pawn = new KamisadoPawn(any(), new Color("violet", "483D8B"), 1);
        stage.getBoard().putElement(decider.pawn, 2,6);
        stage.getBoard().putElement(pawnWhite3, 7,5);
        stage.getBoard().putElement(pawnWhite4, 5,4);
        stage.getBoard().putElement(pawnWhite5, 6,4);
        stage.getBoard().putElement(pawnWhite6, 1,2);
        stage.getBoard().putElement(pawnWhite7, 1,1);
        stage.getBoard().putElement(pawnWhite8, 6,1);

        stage.getBoard().putElement(pawnBlack1, 2,7);
        stage.getBoard().putElement(pawnBlack2, 1,6);
        stage.getBoard().putElement(pawnBlack3, 1,5);
        stage.getBoard().putElement(pawnBlack4, 1,3);
        stage.getBoard().putElement(pawnBlack5, 4,7);
        stage.getBoard().putElement(pawnBlack6, 0,2);
        stage.getBoard().putElement(pawnBlack7, 0,1);
        stage.getBoard().putElement(pawnBlack8, 1,0);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnWhite1;
        decider.IaPawn[1] = decider.pawn;
        decider.IaPawn[2] = pawnWhite3;
        decider.IaPawn[3] = pawnWhite4;
        decider.IaPawn[4] = pawnWhite5;
        decider.IaPawn[5] = pawnWhite6;
        decider.IaPawn[6] = pawnWhite7;
        decider.IaPawn[7] = pawnWhite8;
        stage.setj1Pawns(decider.IaPawn);

        decider.opponentPawns = new KamisadoPawn[8];
        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;
        stage.setj2Pawns(decider.opponentPawns);

        lst.add(new Point(1,7));

        Assertions.assertEquals(point, decider.winInAllCase(lst));
    }

    @Test
    public void testWinInAllCaseOneChoiceAiAndNoMoveOpponentReturnPoint(){
        point = new Point(1,7);
        stage.getBoard().putElement(pawnWhite1, 7,7);
        decider.pawn = new KamisadoPawn(any(), new Color("violet", "483D8B"), 1);
        stage.getBoard().putElement(decider.pawn, 2,6);
        stage.getBoard().putElement(pawnWhite3, 4,4);
        stage.getBoard().putElement(pawnWhite4, 5,4);
        stage.getBoard().putElement(pawnWhite5, 6,4);
        stage.getBoard().putElement(pawnWhite6, 1,2);
        stage.getBoard().putElement(pawnWhite7, 1,1);
        stage.getBoard().putElement(pawnWhite8, 6,1);

        stage.getBoard().putElement(pawnBlack1, 2,7);
        stage.getBoard().putElement(pawnBlack2, 1,6);
        stage.getBoard().putElement(pawnBlack3, 1,5);
        stage.getBoard().putElement(pawnBlack4, 1,3);
        stage.getBoard().putElement(pawnBlack5, 4,7);
        stage.getBoard().putElement(pawnBlack6, 0,2);
        stage.getBoard().putElement(pawnBlack7, 0,1);
        stage.getBoard().putElement(pawnBlack8, 1,0);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnWhite1;
        decider.IaPawn[1] = decider.pawn;
        decider.IaPawn[2] = pawnWhite3;
        decider.IaPawn[3] = pawnWhite4;
        decider.IaPawn[4] = pawnWhite5;
        decider.IaPawn[5] = pawnWhite6;
        decider.IaPawn[6] = pawnWhite7;
        decider.IaPawn[7] = pawnWhite8;
        stage.setj1Pawns(decider.IaPawn);

        decider.opponentPawns = new KamisadoPawn[8];
        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;
        stage.setj2Pawns(decider.opponentPawns);

        lst.add(new Point(1,7));

        Assertions.assertEquals(point, decider.winInAllCase(lst));
    }

    @Test
    public void testWinInAllCaseOneChoiceAiAllCellsWin(){
        point = new Point(1,0);
        decider.pawn = new KamisadoPawn(any(), new Color("orange", "FF8C00"), 1);
        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 2,1);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 2,4);
        stage.getBoard().putElement(pawnWhite5, 2,3);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,6);
        stage.getBoard().putElement(decider.pawn, 2,7);

        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 1,6);
        stage.getBoard().putElement(pawnBlack3, 0,5);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 2,5);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 1,1);
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
        stage.setj2Pawns(decider.opponentPawns);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnWhite1;
        decider.IaPawn[1] = decider.pawn;
        decider.IaPawn[2] = pawnWhite3;
        decider.IaPawn[3] = pawnWhite4;
        decider.IaPawn[4] = pawnWhite5;
        decider.IaPawn[5] = pawnWhite6;
        decider.IaPawn[6] = pawnWhite7;
        decider.IaPawn[7] = pawnWhite8;
        stage.setj1Pawns(decider.IaPawn);

        lst.add(new Point(1,0));

        Assertions.assertEquals(point, decider.winInAllCase(lst));
    }

    @Test
    public void testWinInAllCase1WinAnd1LossSoReturnNull(){
        decider.pawn = new KamisadoPawn(any(), new Color("orange", "FF8C00"), 0);
        stage.getBoard().putElement(decider.pawn, 5,7);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 5,4);
        stage.getBoard().putElement(pawnBlack5, 5,3);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 5,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = decider.pawn;
        decider.IaPawn[1] = pawnBlack2;
        decider.IaPawn[2] = pawnBlack3;
        decider.IaPawn[3] = pawnBlack4;
        decider.IaPawn[4] = pawnBlack5;
        decider.IaPawn[5] = pawnBlack6;
        decider.IaPawn[6] = pawnBlack7;
        decider.IaPawn[7] = pawnBlack8;
        stage.setj1Pawns(decider.IaPawn);

        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 6,6);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 5,2);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,2);
        stage.getBoard().putElement(pawnWhite7, 6,1);
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

        lst.add(new Point(6,7));

        Assertions.assertEquals(point, decider.winInAllCase(lst));
    }
}
