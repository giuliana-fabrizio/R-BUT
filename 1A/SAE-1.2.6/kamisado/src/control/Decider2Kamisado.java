package control;

import gamifier.control.Controller;
import gamifier.control.Decider;
import gamifier.model.Model;
import gamifier.model.action.ActionList;
import gamifier.model.action.GameAction;
import gamifier.model.action.MoveAction;
import gamifier.model.animation.AnimationTypes;
import gamifier.view.GridLook;
import javafx.geometry.Point2D;
import model.KamisadoBoard;
import model.KamisadoPawn;
import model.Color;
import model.KamisadoStageModel;

import java.awt.*;
import java.util.List;

public class Decider2Kamisado extends Decider {
    KamisadoStageModel stage = (KamisadoStageModel) model.getGameStage();
    KamisadoBoard board = stage.getBoard();
    KamisadoPawn[] opponentPawns;
    KamisadoPawn[] IaPawn;

    public ActionList decide(){
        return ia2();
    }

    public Decider2Kamisado(Model model, Controller control) {
        super(model, control);
        if (model.getIdPlayer()==KamisadoPawn.BLACK){
            opponentPawns = stage.getj2Pawns();
            IaPawn = stage.getj1Pawns();
        }
        else {
            opponentPawns = stage.getj1Pawns();
            IaPawn = stage.getj2Pawns();
        }
    }

    public boolean canWin(KamisadoPawn p){
        List<Point> ValidCells = board.computeValidCells(p);
        for (int i=0; i<ValidCells.size();i++){
            if( p.getBorder()== KamisadoPawn.WHITE && ValidCells.get(i).getX()==0)
                return true;
            else if ( p.getBorder()== KamisadoPawn.BLACK && ValidCells.get(i).getX()==7){
                return true;
            }
        }
        return false;
    }

    //fonction utilisé uniquement après canWin()
    public Point winCell(KamisadoPawn p){ // pour le moment renvoie coordonné de la case
        List<Point> ValidCells = board.computeValidCells(p);
        for (int i=0; i<ValidCells.size();i++){
            if( p.getBorder()== KamisadoPawn.BLACK && ValidCells.get(i).getX()==0)
                return ValidCells.get(i);
            else if ( p.getBorder()== KamisadoPawn.WHITE && ValidCells.get(i).getX()==7){
                return ValidCells.get(i);
            }
        }
        return null;
    }

    public boolean[] canloose() {
        boolean[] loose = new boolean[8];
        for (int i = 0; i < loose.length; i++) {
            loose[i] = canWin(opponentPawns[i]);
        }
        return loose;
    }

//    public KamisadoPawn[] pawnsLoose() {
//        boolean[] loose = new boolean[8];
//
//        for (int i = 0; i < loose.length; i++) {
//            loose[i] = canWin(opponentPawns[i]);
//            }
//        return null;
//    }

    public int attackPoint(){
        int counter=0;
        for(int i=0;i<IaPawn.length;i++){
            if(canWin(IaPawn[i])){
                counter+=1;
            }
        }
        return counter;
    }

    public int defensePoint(){
        int counter=0;
        for(int i=0;i<opponentPawns.length;i++){
            if(canWin(opponentPawns[i])){
                counter+=1;
            }
        }
        return counter;
    }

    public ActionList ia2(){
        KamisadoPawn pawnToPlay = (KamisadoPawn) stage.getSelected().get(0); //attention si plusieurs éléments sélectionné à la fin d'1 tours
        List<Point> possibleCells = board.computeValidCells(pawnToPlay);

        if(canWin(pawnToPlay)){
            // create action list. After the last action, it is next player's turn.
            ActionList actions = new ActionList(true);

            // get the dest. cell center in space.
            GridLook look = (GridLook) control.getElementLook(board);
            Point cellToPlay = winCell(pawnToPlay);
            int rowDest = (int)cellToPlay.getX();
            int colDest = (int)cellToPlay.getY();

            Point2D center = look.getRootPaneLocationForCellCenter(rowDest, colDest);

            // create the move action
            GameAction move = new MoveAction(model, pawnToPlay, "kamisadoboard", rowDest, colDest, AnimationTypes.MOVELINEARPROP_NAME, center.getX(), center.getY(), 10);
            actions.addSingleAction(move);
            return actions;

        }
        else{
            for (int i = 0; i < 8; i++) {
                if(canloose()[i]){
                   Color colorPawnLoose = opponentPawns[i].getColor();
//                   for(int j=0;j<possibleCells.size();j++){
//                       if(possibleCells.get(0))
//                   }

                   //boucle sur les cases possible et tesT leur couleur
                   //on veux récupérer la couleur des cases qui sont posssibles pour les enleV
                    //possibleCells.remove()
                }
            }

        }

    return null;}

}


// ordre couleur