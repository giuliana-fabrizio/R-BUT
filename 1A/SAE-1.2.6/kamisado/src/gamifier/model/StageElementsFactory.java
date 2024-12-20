package gamifier.model;

import model.KamisadoStageModel;

public abstract class StageElementsFactory {

    protected GameStageModel gameStageModel;

    public StageElementsFactory(KamisadoStageModel gameStageModel) {
        this.gameStageModel = gameStageModel;
    }

    public abstract void setup();
}
