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

import static org.mockito.ArgumentMatchers.any;

public class TestKamisadoBoardBlackPlayer {
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
        pawnPlayer = new KamisadoPawn(any(), any(), 0);
        pawnAlly = new KamisadoPawn(any(), any(), 0);
        pawnEnnemy1 = new KamisadoPawn(any(), any(), 1);
        pawnEnnemy2 = new KamisadoPawn(any(), any(), 1);
        pawnEnnemy3 = new KamisadoPawn(any(), any(), 1);
        lstTest = new ArrayList<>();
        reachableCells = new boolean[8][8];
    }

    @Test
    public void testComputeValidCellsBlackPlayerEmptyList(){
        kamisadoBoard.putElement(pawnPlayer, 4,2);
        kamisadoBoard.putElement(pawnEnnemy1, 5, 1);
        kamisadoBoard.putElement(pawnEnnemy2, 5, 2);
        kamisadoBoard.putElement(pawnEnnemy3, 5, 3);

        lst = kamisadoBoard.computeValidCells(pawnPlayer);

        Assertions.assertEquals(lstTest, lst);
    }


    @Test
    public void testComputeValidCellsBlackPlayerCheckingLeftDiagonal(){
        kamisadoBoard.putElement(pawnPlayer, 5,6);
        kamisadoBoard.putElement(pawnEnnemy1, 7, 6);

        lstTest.add(new Point(6,6));

        lstTest.add(new Point(6,7));

        lstTest.add(new Point(6,5));
        lstTest.add(new Point(7,4));

        lst = kamisadoBoard.computeValidCells(pawnPlayer);

        Assertions.assertEquals(lstTest, lst);
    }


    @Test
    public void testComputeValidCellsBlackPlayerCheckingRightDiagonalAndCenter(){
        kamisadoBoard.putElement(pawnPlayer, 5,1);

        lstTest.add(new Point(6,1));
        lstTest.add(new Point(7,1));

        lstTest.add(new Point(6,2));
        lstTest.add(new Point(7,3));

        lstTest.add(new Point(6,0));

        lst = kamisadoBoard.computeValidCells(pawnPlayer);

        Assertions.assertEquals(lstTest, lst);
    }
    
    
    @Test 
    public void testSetValidCellsBlackPlayerNoValidCells(){
        kamisadoBoard.putElement(pawnPlayer, 4,2);
        kamisadoBoard.putElement(pawnEnnemy1, 5, 1);
        kamisadoBoard.putElement(pawnEnnemy2, 5, 2);
        kamisadoBoard.putElement(pawnEnnemy3, 5, 3);

        kamisadoBoard.setValidCells(pawnPlayer);

        for(int i = 0; i < reachableCells[0].length; i++) {
            for(int j = 0; j<reachableCells.length; j++){
                Assertions.assertEquals(reachableCells[i][j], kamisadoBoard.getReachableCells()[i][j]);
            }
        }
    }


    @Test
    public void testSetValidCellsBlackPlayerCheckingLeftDiagonal(){
        kamisadoBoard.putElement(pawnPlayer, 5,6);
        kamisadoBoard.putElement(pawnEnnemy1, 7, 6);

        reachableCells[6][6] = true;
        reachableCells[6][7] = true;
        reachableCells[6][5] = true;
        reachableCells[7][4] = true;

        kamisadoBoard.setValidCells(pawnPlayer);

        for(int i = 0; i < reachableCells[0].length; i++) {
            for(int j = 0; j<reachableCells.length; j++){
                Assertions.assertEquals(reachableCells[i][j], kamisadoBoard.getReachableCells()[i][j]);
            }
        }
    }

    @Test
    public void testSetValidCellsBlackPlayerCheckingRightDiagonalAndCenter(){
        kamisadoBoard.putElement(pawnPlayer, 5,1);

        reachableCells[6][1] = true;
        reachableCells[7][1] = true;
        reachableCells[6][2] = true;
        reachableCells[7][3] = true;
        reachableCells[6][0] = true;

        kamisadoBoard.setValidCells(pawnPlayer);

        for(int i = 0; i < reachableCells[0].length; i++) {
            for(int j = 0; j<reachableCells.length; j++){
                Assertions.assertEquals(reachableCells[i][j], kamisadoBoard.getReachableCells()[i][j]);
            }
        }
    }
}
