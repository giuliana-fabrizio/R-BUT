package control;

import gamifier.control.ActionPlayer;
import gamifier.control.Controller;
import gamifier.control.ControllerKey;
import gamifier.model.ElementTypes;
import gamifier.model.GameElement;
import gamifier.model.Model;
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

import java.awt.*;
import java.util.List;

/**
 * A basic keystrokes handler.
 * Generally useless for board games, but it can still be used if needed
 */
public class ControllerKamisadoKey extends ControllerKey {
    static KamisadoStageModel stage;
    static KamisadoPawn[] tabPawn;
    static KamisadoBoard board;
    GameElement pawn;
    GridLook lookBoard;

    public ControllerKamisadoKey(Model model, View view, Controller control) {
        super(model, view, control);
    }

    @Override
    public void handle(KeyEvent event) {
        stage = (KamisadoStageModel)model.getGameStage();
        tabPawn = stage.getj1Pawns();
        board = stage.getBoard();
        lookBoard = (GridLook) control.getElementLook(board);
        int[] pos;

        if ((event.getCode() == KeyCode.SPACE))
            control.stopGame();

        if (!model.isCaptureKeyEvent()) return;

        if (model.getSelected().isEmpty()) {
            if (model.getIdPlayer() == 0) {
                stage.setState(KamisadoStageModel.STATE_SELECTDEST);
                tabPawn[0].toggleSelected();
                model.setSelected(tabPawn[0], true);
            }
        }

        else if (event.getCode() == KeyCode.RIGHT && event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (stage.getFirstTurn() == true) {
                for (int i = 0; i < tabPawn.length; i++) {
                    if (model.getSelected().get(0) == tabPawn[7]) {
                        model.unselectAll();
                        tabPawn[0].toggleSelected();
                        model.setSelected(tabPawn[0], true);
                        break;
                    }
                    if (model.getSelected().get(0) == tabPawn[i]) {
                        model.unselectAll();
                        tabPawn[i+1].toggleSelected();
                        model.setSelected(tabPawn[i+1], true);
                        break;
                    }
                }
            }
            else {
                pawn = model.getSelected().get(0);
                pos = board.getElementCell(pawn);
                if (board.canReachCell(pos[0], pos[1]+1)) {
                    Point2D center = lookBoard.getRootPaneLocationForCellCenter(pos[0], pos[1]+1);
                    GameAction move = new MoveAction(model, pawn, "kamisadoboard", pos[0], pos[1]+1, AnimationTypes.MOVETELEPORT_NAME, center.getX(), center.getY(), 10);
                    ActionList actions = new ActionList(false);
                    actions.addSingleAction(move);
                    ActionPlayer play = new ActionPlayer(model, view, control, actions);
                    play.start();
                }
                else if (board.canReachCell(pos[0]-1, pos[1]+1)) {
                    Point2D center = lookBoard.getRootPaneLocationForCellCenter(pos[0]-1, pos[1]+1);
                    GameAction move = new MoveAction(model, pawn, "kamisadoboard", pos[0]-1, pos[1]+1, AnimationTypes.MOVETELEPORT_NAME, center.getX(), center.getY(), 10);
                    ActionList actions = new ActionList(false);
                    actions.addSingleAction(move);
                    ActionPlayer play = new ActionPlayer(model, view, control, actions);
                    play.start();
                }
                else if (board.canReachCell(pos[0]+1, pos[1]+1)) {
                    Point2D center = lookBoard.getRootPaneLocationForCellCenter(pos[0]+1, pos[1]+1);
                    GameAction move = new MoveAction(model, pawn, "kamisadoboard", pos[0]+1, pos[1]+1, AnimationTypes.MOVETELEPORT_NAME, center.getX(), center.getY(), 10);
                    ActionList actions = new ActionList(false);
                    actions.addSingleAction(move);
                    ActionPlayer play = new ActionPlayer(model, view, control, actions);
                    play.start();
                }
            }
        }

        else if (event.getCode() == KeyCode.LEFT && event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (stage.getFirstTurn() == true) {
                for (int i = 0; i < tabPawn.length; i++) {
                    if (model.getSelected().get(0) == tabPawn[0]) {
                        model.unselectAll();
                        tabPawn[7].toggleSelected();
                        model.setSelected(tabPawn[7], true);
                        break;
                    }
                    if (model.getSelected().get(0) == tabPawn[i]) {
                        model.unselectAll();
                        tabPawn[i-1].toggleSelected();
                        model.setSelected(tabPawn[i-1], true);
                        break;
                    }
                }
            }
            else {
                pawn = model.getSelected().get(0);
                pos = board.getElementCell(pawn);
                System.out.println(board.canReachCell(pos[0]-1, pos[1]-1));
                if (board.canReachCell(pos[0], pos[1]-1)) {
                    Point2D center = lookBoard.getRootPaneLocationForCellCenter(pos[0], pos[1] - 1);
                    GameAction move = new MoveAction(model, pawn, "kamisadoboard", pos[0], pos[1] - 1, AnimationTypes.MOVETELEPORT_NAME, center.getX(), center.getY(), 10);
                    ActionList actions = new ActionList(false);
                    actions.addSingleAction(move);
                    ActionPlayer play = new ActionPlayer(model, view, control, actions);
                    play.start();
                }
                else if (board.canReachCell(pos[0]-1, pos[1]-1)) {
                    Point2D center = lookBoard.getRootPaneLocationForCellCenter(pos[0]-1, pos[1]-1);
                    GameAction move = new MoveAction(model, pawn, "kamisadoboard", pos[0]-1, pos[1]-1, AnimationTypes.MOVETELEPORT_NAME, center.getX(), center.getY(), 10);
                    ActionList actions = new ActionList(false);
                    actions.addSingleAction(move);
                    ActionPlayer play = new ActionPlayer(model, view, control, actions);
                    play.start();
                }
                else if (board.canReachCell(pos[0]+1, pos[1]-1)) {
                    Point2D center = lookBoard.getRootPaneLocationForCellCenter(pos[0]+1, pos[1]-1);
                    GameAction move = new MoveAction(model, pawn, "kamisadoboard", pos[0]+1, pos[1]-1, AnimationTypes.MOVETELEPORT_NAME, center.getX(), center.getY(), 10);
                    ActionList actions = new ActionList(false);
                    actions.addSingleAction(move);
                    ActionPlayer play = new ActionPlayer(model, view, control, actions);
                    play.start();
                }
            }
        }

        else if (event.getCode() == KeyCode.UP && event.getEventType() == KeyEvent.KEY_PRESSED && !stage.getFirstTurn()) {
            pawn = model.getSelected().get(0);
            pos = board.getElementCell(pawn);
            if (board.canReachCell(pos[0]-1, pos[1])) {
                Point2D center = lookBoard.getRootPaneLocationForCellCenter(pos[0]-1, pos[1]);
                GameAction move = new MoveAction(model, pawn, "kamisadoboard", pos[0]-1, pos[1], AnimationTypes.MOVETELEPORT_NAME, center.getX(), center.getY(), 10);
                ActionList actions = new ActionList(false);
                actions.addSingleAction(move);
                ActionPlayer play = new ActionPlayer(model, view, control, actions);
                play.start();
            }
        }

        else if (event.getCode() == KeyCode.DOWN && event.getEventType() == KeyEvent.KEY_PRESSED) {
            pawn = model.getSelected().get(0);
            pos = board.getElementCell(pawn);
            if (board.canReachCell(pos[0]+1, pos[1])) {
                Point2D center = lookBoard.getRootPaneLocationForCellCenter(pos[0]+1, pos[1]);
                GameAction move = new MoveAction(model, pawn, "kamisadoboard", pos[0]+1, pos[1], AnimationTypes.MOVETELEPORT_NAME, center.getX(), center.getY(), 10);
                ActionList actions = new ActionList(false);
                actions.addSingleAction(move);
                ActionPlayer play = new ActionPlayer(model, view, control, actions);
                play.start();
            }
        }


        else if (event.getCode() == KeyCode.ENTER && event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (!stage.getFirstTurn()) {
                model.unselectAll();
                control.nextPlayer();
            }
            stage.setFirstTurn(false);
        }
    }
}
