package view;

import gamifier.model.GameStageModel;
import gamifier.model.Player;
import gamifier.view.GameStageView;
import gamifier.view.TextLook;
import javafx.scene.layout.GridPane;
import model.Color;
import model.KamisadoPawn;
import model.KamisadoStageModel;

public class KamisadoStageView extends GameStageView {
    public KamisadoStageView(String name, GameStageModel gameStageModel) {
        super(name, gameStageModel);
        width = 600;
        height = 600;
    }

    @Override
    public void createLooks() {
        KamisadoStageModel model = (KamisadoStageModel) gameStageModel;

        addLook(new KamisadoBoardLook(450, model.getBoard()));

        for (int  i=0; i<8;i++){
            addLook(new PawnView(model.getj1Pawns()[i],true));
            addLook(new PawnView(model.getj2Pawns()[i],false));
        }
    }
}
