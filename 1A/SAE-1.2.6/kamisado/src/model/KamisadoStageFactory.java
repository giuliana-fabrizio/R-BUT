package model;

import gamifier.model.GameStageModel;
import gamifier.model.StageElementsFactory;
import gamifier.model.TextElement;
import javafx.stage.Stage;
import view.KamisadoStageView;
import view.PawnView;
import gamifier.view.GameStageView;

public class KamisadoStageFactory extends StageElementsFactory{
    private KamisadoStageModel stageModel;
    private int NB_PAWNS = 8;

    public KamisadoStageFactory(KamisadoStageModel model){
        super(model);
        this.stageModel = (KamisadoStageModel) model;
    }

    public KamisadoStageModel getStageModel() {
        return stageModel;
    }

    @Override
    public void setup(){
        // create the board
        stageModel.setBoard(new KamisadoBoard(10, 60, stageModel));


        // create the pawns
        Color[] color = new Color[NB_PAWNS];
        final String[] NAME = new String[]{"橙", "红", "绿", "粉", "黄", "蓝", "紫", "棕"};
        final String[] CODEHEXA = new String[] {"#FF8C00", "#f00020", "#2E8B57", "#C71585", "#FFD700", "#6495ED", "#483D8B", "#8B4513"};
        for (int i=0; i<NB_PAWNS; i++)
            color[i] = new Color(NAME[i], CODEHEXA[i]);

        KamisadoPawn[] j1 = new KamisadoPawn[8];
        for (int  i=0; i<8;i++){
            j1[i]= new KamisadoPawn(stageModel,color[i],1);
        }
        stageModel.setj1Pawns(j1);

        KamisadoPawn[] j2 = new KamisadoPawn[8];
        for (int i=7; i>=0;i--){
            j2[i]= new KamisadoPawn(stageModel,color[i],0);
        }
        stageModel.setj2Pawns(j2);

        for (int i=0;i<8;i++) {
            stageModel.getBoard().putElement(j1[i], 7,i);
            stageModel.getBoard().putElement(j2[7-i], 0,i);
        }
    }
}

