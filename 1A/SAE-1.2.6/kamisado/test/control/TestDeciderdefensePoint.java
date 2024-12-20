package control;

import model.KamisadoPawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDeciderdefensePoint extends TestDecider{

    @Test
    public void testDeciderdefensePointIaAsWhitePawn(){

        stage.getBoard().putElement(pawnBlack1, 5,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 5,4);
        stage.getBoard().putElement(pawnBlack6, 5,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 1,7);

        stage.getBoard().putElement(pawnWhite1, 3,7);
        stage.getBoard().putElement(pawnWhite2, 1,6);
        stage.getBoard().putElement(pawnWhite3, 2,0);
        stage.getBoard().putElement(pawnWhite8, 7,4);
        stage.getBoard().putElement(pawnWhite4, 2,3);
        stage.getBoard().putElement(pawnWhite5, 2,2);
        stage.getBoard().putElement(pawnWhite6, 7,1);
        stage.getBoard().putElement(pawnWhite7, 7,0);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnWhite1;
        decider.IaPawn[1] = pawnWhite2;
        decider.IaPawn[2] = pawnWhite3;
        decider.IaPawn[3] = pawnWhite4;
        decider.IaPawn[4] = pawnWhite5;
        decider.IaPawn[5] = pawnWhite6;
        decider.IaPawn[6] = pawnWhite7;
        decider.IaPawn[7] = pawnWhite8;

        decider.opponentPawns = new KamisadoPawn[8];
        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        int response=decider.defensePoint();

        Assertions.assertEquals(3, response);
    }

    @Test
    public void testDeciderdefensePointIaAsBlackPawn(){

        stage.getBoard().putElement(pawnBlack1, 5,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 5,4);
        stage.getBoard().putElement(pawnBlack6, 5,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 1,7);

        stage.getBoard().putElement(pawnWhite1, 3,7);
        stage.getBoard().putElement(pawnWhite2, 1,6);
        stage.getBoard().putElement(pawnWhite3, 2,0);
        stage.getBoard().putElement(pawnWhite8, 7,4);
        stage.getBoard().putElement(pawnWhite4, 2,3);
        stage.getBoard().putElement(pawnWhite5, 2,2);
        stage.getBoard().putElement(pawnWhite6, 7,1);
        stage.getBoard().putElement(pawnWhite7, 7,0);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnBlack1;
        decider.IaPawn[1] = pawnBlack2;
        decider.IaPawn[2] = pawnBlack3;
        decider.IaPawn[3] = pawnBlack4;
        decider.IaPawn[4] = pawnBlack5;
        decider.IaPawn[5] = pawnBlack6;
        decider.IaPawn[6] = pawnBlack7;
        decider.IaPawn[7] = pawnBlack8;

        decider.opponentPawns = new KamisadoPawn[8];
        decider.opponentPawns[0] = pawnWhite1;
        decider.opponentPawns[1] = pawnWhite2;
        decider.opponentPawns[2] = pawnWhite3;
        decider.opponentPawns[3] = pawnWhite4;
        decider.opponentPawns[4] = pawnWhite5;
        decider.opponentPawns[5] = pawnWhite6;
        decider.opponentPawns[6] = pawnWhite7;
        decider.opponentPawns[7] = pawnWhite8;

        int response=decider.defensePoint();

        Assertions.assertEquals(5, response);
    }

    @Test
    public void testDeciderdefensePointIaAsBlackPawnCounter0(){

        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 7,1);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite8, 7,3);
        stage.getBoard().putElement(pawnWhite4, 7,4);
        stage.getBoard().putElement(pawnWhite5, 7,5);
        stage.getBoard().putElement(pawnWhite6, 7,6);
        stage.getBoard().putElement(pawnWhite7, 7,7);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnBlack1;
        decider.IaPawn[1] = pawnBlack2;
        decider.IaPawn[2] = pawnBlack3;
        decider.IaPawn[3] = pawnBlack4;
        decider.IaPawn[4] = pawnBlack5;
        decider.IaPawn[5] = pawnBlack6;
        decider.IaPawn[6] = pawnBlack7;
        decider.IaPawn[7] = pawnBlack8;

        decider.opponentPawns = new KamisadoPawn[8];
        decider.opponentPawns[0] = pawnWhite1;
        decider.opponentPawns[1] = pawnWhite2;
        decider.opponentPawns[2] = pawnWhite3;
        decider.opponentPawns[3] = pawnWhite4;
        decider.opponentPawns[4] = pawnWhite5;
        decider.opponentPawns[5] = pawnWhite6;
        decider.opponentPawns[6] = pawnWhite7;
        decider.opponentPawns[7] = pawnWhite8;

        int response=decider.defensePoint();

        Assertions.assertEquals(0, response);
    }

    @Test
    public void testDeciderdefensePointIaAsWhitePawnAsCounter0(){

        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

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

        decider.opponentPawns = new KamisadoPawn[8];
        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        int response=decider.defensePoint();

        Assertions.assertEquals(0, response);
    }

}
