package control;

import gamifier.control.ActionPlayer;
import gamifier.control.Controller;
import gamifier.control.ControllerMouse;
import gamifier.model.*;
import gamifier.model.action.ActionList;
import gamifier.model.action.GameAction;
import gamifier.model.action.MoveAction;
import gamifier.model.animation.AnimationTypes;
import gamifier.view.GridLook;
import gamifier.view.View;
import javafx.event.*;
import javafx.geometry.Point2D;
import javafx.scene.input.*;
import model.KamisadoBoard;
import model.KamisadoPawn;
import model.KamisadoStageModel;

import java.util.List;

/**
 * A basic mouse controller that just grabs the mouse clicks and prints out some informations.
 * It gets the elements of the scene that are at the clicked position and prints them.
 */
public class ControllerKamisadoMouse extends ControllerMouse implements EventHandler<MouseEvent> {

    public ControllerKamisadoMouse(Model model, View view, Controller control) {
        super(model, view, control);
    }

    public void handle(MouseEvent event) {
        // if mouse event capture is disabled in the model, just return
        if (!model.isCaptureMouseEvent()) return;
        System.out.println("!model.isCaptureMouseEvent()" + !model.isCaptureMouseEvent());

        // get the clic x,y in the whole scene (this includes the menu bar if it exists)
        Point2D clic = new Point2D(event.getSceneX(),event.getSceneY());
        System.out.println(clic);
        // get elements at that position
        List<GameElement> list = control.elementsAt(clic);
        // for debug, uncomment next instructions to display x,y and elements at that postion
        /*
        System.out.println("click in "+event.getSceneX()+","+event.getSceneY());
        for(GameElement element : list) {
            System.out.println(element);
        }
        */

        KamisadoStageModel stageModel = (KamisadoStageModel)model.getGameStage();
        if (stageModel.getState() == KamisadoStageModel.STATE_SELECTPAWN) {
            for (GameElement element : list) {
                if (element.getType() == ElementTypes.getType("pawn")) {
                    KamisadoPawn pawn = (KamisadoPawn) element;
                    // check if color of the pawn corresponds to the current player id
                    if (pawn.getBorder() != model.getIdPlayer()) {
                        element.toggleSelected();
                        stageModel.setState(KamisadoStageModel.STATE_SELECTDEST);
                        return; // do not allow another element to be selected
                    }
                }
            }
        }
        else if (stageModel.getState() == KamisadoStageModel.STATE_SELECTDEST) {

            if (stageModel.getFirstTurn()) {
                for (GameElement element: list) {
                    if (element.getType() == ElementTypes.getType("pawn") &&
                            ((KamisadoPawn)element).getBorder() == ((KamisadoPawn)model.getSelected().get(0)).getBorder()) {
                        model.unselectAll();
//                        element.toggleSelected();
                        model.setSelected(element, true);
                        break;
                    }
                }
            }


            // secondly, search if the board has been clicked. If not just return
            boolean boardClicked = false;
            for (GameElement element : list) {
                if (element == stageModel.getBoard()) {
                    boardClicked = true;
                    break;
                }
            }
            if (!boardClicked) return;


            // get the board and the selected pawn to simplify code in the following
            KamisadoBoard board = stageModel.getBoard();
            GameElement pawn = model.getSelected().get(0);
            // thirdly, get the clicked cell in the 8x8 board
            GridLook lookBoard = (GridLook) control.getElementLook(board);
            int[] dest = lookBoard.getCellFromSceneLocation(clic);
            // get the cell in the pot that owns the selected pawn
            int[] from = lookBoard.getCellFromSceneLocation(pawn.getX(), pawn.getY());
            System.out.println("try to move pawn from board " + from[0] + "," + from[1] + " to board " + dest[0] + "," + dest[1]);
            // if the destination cell is valid for the selected pawn
            if (board.canReachCell(dest[0], dest[1])) {
                stageModel.setFirstTurn(false);
                // build the list of actions to do, and pass to the next player when done
                ActionList actions = new ActionList(true);
                // determine the destination point in the root pane
                Point2D center = lookBoard.getRootPaneLocationForCellCenter(dest[0], dest[1]);
                // create an action with a linear move animation, with 10 pixel/frame
                GameAction move = new MoveAction(model, pawn, "kamisadoboard", dest[0], dest[1], AnimationTypes.MOVELINEARPROP_NAME, center.getX(), center.getY(), 10);
                // add the action to the action list.
                actions.addSingleAction(move);
                stageModel.unselectAll();
                stageModel.setState(KamisadoStageModel.STATE_SELECTDEST);
                ActionPlayer play = new ActionPlayer(model, view, control, actions);
                play.start();
            }
        }
    }
}

