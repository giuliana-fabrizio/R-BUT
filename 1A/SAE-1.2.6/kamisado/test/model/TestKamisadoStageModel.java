package model;

import gamifier.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestKamisadoStageModel {
    private KamisadoStageModel stageModel;
    private KamisadoPawn pawn;
    private Model model;


    @Nested
    class TestNest{

        @BeforeEach
        public void setup(){
            pawn = Mockito.mock(KamisadoPawn.class);
            model = new Model(10);
            stageModel = new KamisadoStageModel("test", model);
        }


        @Test
        public void testComputePartyResultsPlayerWhiteWin(){
            Mockito.when(pawn.getBorder()).thenReturn(1);

            stageModel.computePartyResult(pawn);

            Mockito.verify(pawn, times(1)).getBorder();
            Assertions.assertEquals(0, stageModel.getModel().getIdWinner());
        }

        @Test
        public void testComputePartyResultsPlayerBlackWin(){
            Mockito.when(pawn.getBorder()).thenReturn(0);

            stageModel.computePartyResult(pawn);

            Mockito.verify(pawn, times(1)).getBorder();
            Assertions.assertEquals(1, stageModel.getModel().getIdWinner());
        }
    }





    @BeforeEach
    public void setup(){
        pawn = Mockito.mock(KamisadoPawn.class);
        model = Mockito.mock(Model.class);
        stageModel = new KamisadoStageModel("test", model);
    }

    @Test
    public void testComputePartyResultsPlayerWhiteAllFunctionsUsed(){
        Mockito.when(pawn.getBorder()).thenReturn(1);

        stageModel.computePartyResult(pawn);

        Mockito.verify(pawn, times(1)).getBorder();
        Mockito.verify(stageModel.getModel(), times(1)).setIdWinner(1);
        Mockito.verify(stageModel.getModel(), times(1)).stopGame();
    }

    @Test
    public void testComputePartyResultsPlayerBlackAllFunctionsUsed(){
        Mockito.when(pawn.getBorder()).thenReturn(0);

        stageModel.computePartyResult(pawn);

        Mockito.verify(pawn, times(1)).getBorder();
        Mockito.verify(stageModel.getModel(), times(1)).setIdWinner(0);
        Mockito.verify(stageModel.getModel(), times(1)).stopGame();
    }
}
