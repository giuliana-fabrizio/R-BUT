package control;

import gamifier.control.ActionPlayer;
import gamifier.control.Controller;
import gamifier.model.Model;
import gamifier.model.Player;
import gamifier.view.View;
import model.KamisadoPawn;
import model.KamisadoStageModel;

public class ControllerKamisado extends Controller {

    public ControllerKamisado(Model model, View view) {
        super(model, view);
        setControlKey(new ControllerKamisadoKey(model, view, this));
        setControlMouse(new ControllerKamisadoMouse(model, view, this));
        setControlAction (new ControllerKamisadoAction(model, view, this));
    }

    public void nextPlayer() {
        // use the default method to compute next player
        model.setNextPlayer();
        // get the new player
        Player p = model.getCurrentPlayer();

        // change the pawn selected if pawn are blocked then skip turn
        KamisadoStageModel stageModel = (KamisadoStageModel) model.getGameStage();

        if(model.getIdPlayer() == KamisadoPawn.BLACK) {
            for (KamisadoPawn pawn : stageModel.getj1Pawns()) {
                if(pawn.getColor().getCodeHexa().equals(stageModel.colorToPlay)){
                    if(stageModel.cantMove(pawn)) {
                        if (!stageModel.cantMoveOpposent(pawn)) {
                            stopGame();
                        }
                        else {
                            stageModel.pawnCantMove(pawn);
                            nextPlayer();
                        }
                        return;
                    } else pawn.toggleSelected();
                }
            }
        } else {
            for (KamisadoPawn pawn : stageModel.getj2Pawns()) {
                if(pawn.getColor().getCodeHexa().equals(stageModel.colorToPlay)){
                    if(stageModel.cantMove(pawn)) {
                        if (!stageModel.cantMoveOpposent(pawn)) {
                            stopGame();
                        }
                        else {
                            stageModel.pawnCantMove(pawn);
                            nextPlayer();
                        }
                        return;
                    } else pawn.toggleSelected();
                }
            }
        }
//        stageModel.getPlayerName().setText(p.getName());
        if (p.getType() == Player.COMPUTER) {
            System.out.println("COMPUTER PLAYS");
            DeciderKamisado decider = new DeciderKamisado(model,this);
            ActionPlayer play = new ActionPlayer(model, view, this, decider, null);
            play.start();
        }
        else {
            System.out.println("PLAYER PLAYS");
        }
    }
}