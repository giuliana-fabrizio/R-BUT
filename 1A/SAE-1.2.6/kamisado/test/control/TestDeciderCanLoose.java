package control;

import model.KamisadoPawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDeciderCanLoose extends TestDecider{

    @Test
    public void testCanLooseBlackPawn(){
        stage.getBoard().putElement(pawnWhite1, 1,0);
        stage.getBoard().putElement(pawnWhite2, 1,1);
        stage.getBoard().putElement(pawnWhite3, 1,2);
        stage.getBoard().putElement(pawnWhite4, 1,3);
        stage.getBoard().putElement(pawnWhite5, 1,4);
        stage.getBoard().putElement(pawnWhite6, 1,5);
        stage.getBoard().putElement(pawnWhite7, 1,6);
        stage.getBoard().putElement(pawnWhite8, 1,7);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnWhite1;
        decider.opponentPawns[1] = pawnWhite2;
        decider.opponentPawns[2] = pawnWhite3;
        decider.opponentPawns[3] = pawnWhite4;
        decider.opponentPawns[4] = pawnWhite5;
        decider.opponentPawns[5] = pawnWhite6;
        decider.opponentPawns[6] = pawnWhite7;
        decider.opponentPawns[7] = pawnWhite8;

        boolean[] response = decider.canloose();

        for (int i = 0; i < response.length; i++) {
            Assertions.assertEquals(true, response[i]);
        }
    }

    @Test
    public void testCanLooseWhitePawn(){
        stage.getBoard().putElement(pawnBlack1, 6,0);
        stage.getBoard().putElement(pawnBlack2, 6,1);
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

        boolean[] response = decider.canloose();

        for (int i = 0; i < response.length; i++) {
            Assertions.assertEquals(true, response[i]);
        }
    }

    @Test
    public void testCanLooseBlackPawnOne(){
        stage.getBoard().putElement(pawnBlack1, 0,2);
        stage.getBoard().putElement(pawnBlack2, 0,4);

        stage.getBoard().putElement(pawnWhite1, 2,0);
        stage.getBoard().putElement(pawnWhite2, 2,2);
        stage.getBoard().putElement(pawnWhite3, 2,4);
        stage.getBoard().putElement(pawnWhite4, 6,3);
        stage.getBoard().putElement(pawnWhite5, 6,4);
        stage.getBoard().putElement(pawnWhite6, 6,5);
        stage.getBoard().putElement(pawnWhite7, 6,6);
        stage.getBoard().putElement(pawnWhite8, 6,7);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnWhite1;
        decider.opponentPawns[1] = pawnWhite2;
        decider.opponentPawns[2] = pawnWhite3;
        decider.opponentPawns[3] = pawnWhite4;
        decider.opponentPawns[4] = pawnWhite5;
        decider.opponentPawns[5] = pawnWhite6;
        decider.opponentPawns[6] = pawnWhite7;
        decider.opponentPawns[7] = pawnWhite8;

        boolean[] response = decider.canloose();

        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(true, response[i]);
        }
    }

    @Test
    public void testCanLooseWhitePawnOne(){
        stage.getBoard().putElement(pawnWhite1, 7,2);
        stage.getBoard().putElement(pawnWhite2, 7,4);

        stage.getBoard().putElement(pawnBlack1, 5,0);
        stage.getBoard().putElement(pawnBlack2, 5,2);
        stage.getBoard().putElement(pawnBlack3, 5,4);
        stage.getBoard().putElement(pawnBlack4, 4,3);
        stage.getBoard().putElement(pawnBlack5, 4,4);
        stage.getBoard().putElement(pawnBlack6, 4,5);
        stage.getBoard().putElement(pawnBlack7, 4,6);
        stage.getBoard().putElement(pawnBlack8, 4,7);

        decider.opponentPawns = new KamisadoPawn[8];

        decider.opponentPawns[0] = pawnBlack1;
        decider.opponentPawns[1] = pawnBlack2;
        decider.opponentPawns[2] = pawnBlack3;
        decider.opponentPawns[3] = pawnBlack4;
        decider.opponentPawns[4] = pawnBlack5;
        decider.opponentPawns[5] = pawnBlack6;
        decider.opponentPawns[6] = pawnBlack7;
        decider.opponentPawns[7] = pawnBlack8;

        boolean[] response = decider.canloose();

        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(true, response[i]);
        }
    }

    @Test
    public void testCantLooseBlackPawn(){
        stage.getBoard().putElement(pawnWhite1, 7,0);
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

        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        boolean[] response = decider.canloose();

        for (int i = 0; i < response.length; i++) {
            Assertions.assertEquals(false, response[i]);
        }
    }

    @Test
    public void testCantLooseWhitePawnOne(){

        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
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

        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 7,1);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 7,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,6);
        stage.getBoard().putElement(pawnWhite8, 7,7);

        boolean[] response = decider.canloose();

        for (int i = 0; i < response.length; i++) {
            Assertions.assertEquals(false, response[i]);
        }
    }
}