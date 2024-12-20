package control;

import model.KamisadoPawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAttackPoint extends TestDecider {

    @Test
    public void testAttackPointWinBlack() {
        stage.getBoard().putElement(pawnBlack1, 6,0);
        stage.getBoard().putElement(pawnBlack2, 3,0);
        stage.getBoard().putElement(pawnBlack3, 6,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 5,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 6,6);
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

        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 6,1);
        stage.getBoard().putElement(pawnWhite3, 1,2);
        stage.getBoard().putElement(pawnWhite4, 1,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 1,6);
        stage.getBoard().putElement(pawnWhite8, 1,7);

        int response = decider.attackPoint();
        Assertions.assertEquals(4, response);
    }

    @Test
    public void testAttackPointWinWhite() {
        stage.getBoard().putElement(pawnWhite1, 3,0); // 1
        stage.getBoard().putElement(pawnWhite2, 5,1);
        stage.getBoard().putElement(pawnWhite3, 2,2); // 2
        stage.getBoard().putElement(pawnWhite4, 4,4); // 3
        stage.getBoard().putElement(pawnWhite5, 1,5); // 4
        stage.getBoard().putElement(pawnWhite6, 7,6); //
        stage.getBoard().putElement(pawnWhite7, 6,4);
        stage.getBoard().putElement(pawnWhite8, 4,6);

        decider.IaPawn = new KamisadoPawn[8];
        decider.IaPawn[0] = pawnWhite1;
        decider.IaPawn[1] = pawnWhite2;
        decider.IaPawn[2] = pawnWhite3;
        decider.IaPawn[3] = pawnWhite4;
        decider.IaPawn[4] = pawnWhite5;
        decider.IaPawn[5] = pawnWhite6;
        decider.IaPawn[6] = pawnWhite7;
        decider.IaPawn[7] = pawnWhite8;

        stage.getBoard().putElement(pawnBlack1, 1,0);
        stage.getBoard().putElement(pawnBlack2, 1,1);
        stage.getBoard().putElement(pawnBlack3, 3,2);
        stage.getBoard().putElement(pawnBlack4, 1,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 2,4);
        stage.getBoard().putElement(pawnBlack7, 3,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        int response = decider.attackPoint();
        Assertions.assertEquals(3, response);
    }

    @Test
    public void testAttackPointCantWinBlack() {
        stage.getBoard().putElement(pawnBlack1, 6,0);
        stage.getBoard().putElement(pawnBlack2, 3,0);
        stage.getBoard().putElement(pawnBlack3, 6,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 5,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 6,6);
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

        stage.getBoard().putElement(pawnWhite1, 7,0);
        stage.getBoard().putElement(pawnWhite2, 7,1);
        stage.getBoard().putElement(pawnWhite3, 7,2);
        stage.getBoard().putElement(pawnWhite4, 7,3);
        stage.getBoard().putElement(pawnWhite5, 7,4);
        stage.getBoard().putElement(pawnWhite6, 7,5);
        stage.getBoard().putElement(pawnWhite7, 7,6);
        stage.getBoard().putElement(pawnWhite8, 7,7);

        int response = decider.attackPoint();
        Assertions.assertEquals(0, response);
    }

    @Test
    public void testAttackPointCantWinWhite() {
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

        stage.getBoard().putElement(pawnBlack1, 0,0);
        stage.getBoard().putElement(pawnBlack2, 0,1);
        stage.getBoard().putElement(pawnBlack3, 0,2);
        stage.getBoard().putElement(pawnBlack4, 0,3);
        stage.getBoard().putElement(pawnBlack5, 0,4);
        stage.getBoard().putElement(pawnBlack6, 0,5);
        stage.getBoard().putElement(pawnBlack7, 0,6);
        stage.getBoard().putElement(pawnBlack8, 0,7);

        int response = decider.attackPoint();
        Assertions.assertEquals(0, response);
    }
}
