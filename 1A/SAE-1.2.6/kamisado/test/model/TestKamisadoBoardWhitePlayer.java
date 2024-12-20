package model;

import gamifier.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestKamisadoBoardWhitePlayer {
    private KamisadoBoard kamisadoBoard;
    private KamisadoStageModel kamisadoStageModel;
    private Model model;
    private KamisadoPawn pawnPlayer;
    private KamisadoPawn pawnEnnemy1;
    private KamisadoPawn pawnEnnemy2;
    private KamisadoPawn pawnEnnemy3;
    private KamisadoPawn pawnAlly;
    private List<Point> lstTest;
    private List<Point> lst;
    private boolean[][] reachableCells;

    @BeforeEach
    public void setup(){
        model = new Model();
        kamisadoStageModel = new KamisadoStageModel("test", model);
        kamisadoBoard = new KamisadoBoard(8,8, kamisadoStageModel);
        pawnPlayer = new KamisadoPawn(any(), any(), 1);
        pawnAlly = new KamisadoPawn(any(), any(), 1);
        pawnEnnemy1 = new KamisadoPawn(any(), any(), 0);
        pawnEnnemy2 = new KamisadoPawn(any(), any(), 0);
        pawnEnnemy3 = new KamisadoPawn(any(), any(), 0);
        lstTest = new ArrayList<>();
        reachableCells = new boolean[8][8];
    }

    @Test
    public void testComputeValidCellsWhitePlayerEmptyBorder(){
        kamisadoBoard.putElement(pawnPlayer, 7,7);
        kamisadoBoard.putElement(pawnEnnemy1, 6, 7);
        kamisadoBoard.putElement(pawnEnnemy2, 6, 6);

        lst = kamisadoBoard.computeValidCells(pawnPlayer);

        Assertions.assertEquals(lstTest, lst);
    }


    @Test
    public void testComputeValidCellsWhitePlayerEmpty3Ennemies(){
        kamisadoBoard.putElement(pawnPlayer, 5,6);
        kamisadoBoard.putElement(pawnEnnemy1, 4, 6);
        kamisadoBoard.putElement(pawnEnnemy2, 4, 5);
        kamisadoBoard.putElement(pawnEnnemy3, 4, 7);

        lst = kamisadoBoard.computeValidCells(pawnPlayer);

        Assertions.assertEquals(lstTest, lst);
    }


    @Test
    public void testComputeValidCellsWhitePlayer2Results(){
        kamisadoBoard.putElement(pawnPlayer, 7,0);
        kamisadoBoard.putElement(pawnAlly, 5,0);
        kamisadoBoard.putElement(pawnEnnemy1, 5, 2);
        lstTest.add(new Point(6,0));
        lstTest.add(new Point(6,1));

        lst = kamisadoBoard.computeValidCells(pawnPlayer);

        Assertions.assertEquals(lstTest, lst);
    }


    @Test
    public void testComputeValidCellsWhitePlayerNoEnnemiesInTrajectory(){
        kamisadoBoard.putElement(pawnPlayer, 7,4);
        kamisadoBoard.putElement(pawnAlly, 4,5);
        kamisadoBoard.putElement(pawnEnnemy1, 2, 3);

        lstTest.add(new Point(6,4));
        lstTest.add(new Point(5,4));
        lstTest.add(new Point(4,4));
        lstTest.add(new Point(3,4));
        lstTest.add(new Point(2,4));
        lstTest.add(new Point(1,4));
        lstTest.add(new Point(0,4));

        lstTest.add(new Point(6,5));
        lstTest.add(new Point(5,6));
        lstTest.add(new Point(4,7));

        lstTest.add(new Point(6,3));
        lstTest.add(new Point(5,2));
        lstTest.add(new Point(4,1));
        lstTest.add(new Point(3,0));

        lst = kamisadoBoard.computeValidCells(pawnPlayer);

        Assertions.assertEquals(lstTest, lst);
    }

    @Test
    public void testComputeValidCellsWhitePlayerCheckingDiagonals(){
        kamisadoBoard.putElement(pawnPlayer, 3,4);
        kamisadoBoard.putElement(pawnEnnemy1, 1, 4);

        lstTest.add(new Point(2,4));

        lstTest.add(new Point(2,5));
        lstTest.add(new Point(1,6));
        lstTest.add(new Point(0,7));

        lstTest.add(new Point(2,3));
        lstTest.add(new Point(1,2));
        lstTest.add(new Point(0,1));

        lst = kamisadoBoard.computeValidCells(pawnPlayer);

        Assertions.assertEquals(lstTest, lst);
    }

    @Test
    public void testSetValidCellsWhitePlayerNoValidCells(){
        kamisadoBoard.putElement(pawnPlayer, 5,6);
        kamisadoBoard.putElement(pawnEnnemy1, 4, 6);
        kamisadoBoard.putElement(pawnEnnemy2, 4, 5);
        kamisadoBoard.putElement(pawnEnnemy3, 4, 7);

        kamisadoBoard.setValidCells(pawnPlayer);

        for(int i = 0; i < reachableCells[0].length; i++) {
            for(int j = 0; j<reachableCells.length; j++){
                Assertions.assertEquals(reachableCells[i][j], kamisadoBoard.getReachableCells()[i][j]);
            }
        }
    }


    @Test
    public void testSetValidCellsWhitePlayer2Results(){
        kamisadoBoard.putElement(pawnPlayer, 7,0);
        kamisadoBoard.putElement(pawnAlly, 5,0);
        kamisadoBoard.putElement(pawnEnnemy1, 5, 2);

        reachableCells[6][0] = true;
        reachableCells[6][1] = true;

        kamisadoBoard.setValidCells(pawnPlayer);

        for(int i = 0; i < reachableCells[0].length; i++) {
            for(int j = 0; j<reachableCells.length; j++){
                Assertions.assertEquals(reachableCells[i][j], kamisadoBoard.getReachableCells()[i][j]);
            }
        }
    }

    @Test
    public void testSetValidCellsWhitePlayerNoEnnemiesInTrajectory(){
        kamisadoBoard.putElement(pawnPlayer, 7,4);
        kamisadoBoard.putElement(pawnAlly, 4,5);
        kamisadoBoard.putElement(pawnEnnemy1, 2, 3);

        reachableCells[6][4] = true;
        reachableCells[5][4] = true;
        reachableCells[4][4] = true;
        reachableCells[3][4] = true;
        reachableCells[2][4] = true;
        reachableCells[1][4] = true;
        reachableCells[0][4] = true;
        reachableCells[6][5] = true;
        reachableCells[5][6] = true;
        reachableCells[4][7] = true;
        reachableCells[6][3] = true;
        reachableCells[5][2] = true;
        reachableCells[4][1] = true;
        reachableCells[3][0] = true;

        kamisadoBoard.setValidCells(pawnPlayer);

        for(int i = 0; i < reachableCells[0].length; i++) {
            for(int j = 0; j<reachableCells.length; j++){
                Assertions.assertEquals(reachableCells[i][j], kamisadoBoard.getReachableCells()[i][j]);
            }
        }
    }

    @Test
    public void testSetValidCellsBlackPlayerCheckingDiagonals(){
        kamisadoBoard.putElement(pawnPlayer, 3,4);
        kamisadoBoard.putElement(pawnEnnemy1, 1, 4);

        reachableCells[2][4] = true;
        reachableCells[2][5] = true;
        reachableCells[1][6] = true;
        reachableCells[0][7] = true;
        reachableCells[2][3] = true;
        reachableCells[1][2] = true;
        reachableCells[0][1] = true;

        kamisadoBoard.setValidCells(pawnPlayer);

        for(int i = 0; i < reachableCells[0].length; i++) {
            for(int j = 0; j<reachableCells.length; j++){
                Assertions.assertEquals(reachableCells[i][j], kamisadoBoard.getReachableCells()[i][j]);
            }
        }
    }
}
