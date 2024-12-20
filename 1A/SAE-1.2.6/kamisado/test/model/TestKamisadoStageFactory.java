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

public class TestKamisadoStageFactory {
    private KamisadoStageFactory kamisadoStageFactory;
    private KamisadoStageModel kamisadoStageModel;
    private Model model;

    @BeforeEach
    public void setup(){
//        kamisadoStageModel = mock(KamisadoStageModel.class);
        model = mock(Model.class);
        kamisadoStageModel = new KamisadoStageModel("test", model);
        kamisadoStageFactory = new KamisadoStageFactory(kamisadoStageModel);
//        when().then;
    }


    @Test
    public void testSetup(){


        kamisadoStageFactory.setup();

        verify(kamisadoStageModel, times(1)).setBoard(mock(KamisadoBoard.class));
        verify(kamisadoStageModel, times(1)).setj1Pawns(kamisadoStageModel.getj1Pawns());
        verify(kamisadoStageModel, times(1)).setj2Pawns(kamisadoStageModel.getj2Pawns());
        for (int i=0;i<8;i++) {
            verify(kamisadoStageModel, times(1)).getBoard().putElement(mock(GameElement.class), 0, i);
            verify(kamisadoStageModel, times(1)).getBoard().putElement(mock(GameElement.class), 7, i);
//            Assertions.assertEquals(kamisadoStageModel.getBoard().getLocation());
        }

    }
}
