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
import model.KamisadoStageModel;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class DeciderKamisado extends Decider {
    private static final Random loto = new Random();

    KamisadoStageModel stage = (KamisadoStageModel) model.getGameStage();
    KamisadoBoard board = stage.getBoard();
    KamisadoPawn[] opponentPawns;
    KamisadoPawn pawn;
    KamisadoPawn[] IaPawn;

    int rowDest; // the dest. row in board
    int colDest; // the dest. col in board

    public DeciderKamisado(Model model, Controller control) {
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

    public boolean[] canloose() {
        boolean[] loose = new boolean[8];
        for (int i = 0; i < loose.length; i++) {
            loose[i] = canWin(opponentPawns[i]);
        }
        return loose;
    }

    public boolean canWin(KamisadoPawn p) {
        List<Point> lst = board.computeValidCells(p);
        for ( int i = 0; i< lst.size(); i++ ) {
            if( p.getBorder()== KamisadoPawn.WHITE && lst.get(i).x==0)
                return true;
            else if ( p.getBorder()== KamisadoPawn.BLACK && lst.get(i).x==7)
                return true;
        }
        return false;
    }

    //TODO n't test this one
    public boolean canblock(int x, int y){
        String colortoplay = board.getcolorcell(x,y);
        KamisadoPawn opawn = null;
        for (KamisadoPawn p : opponentPawns) {
            if (p.getColor().getCodeHexa().equals(colortoplay)) {
                opawn = p;
            }
        }
        List<Point> lst = board.computeValidCells(opawn);
        if (lst.isEmpty()) {
            return true;
        }
        return true;
    }

    public void deletbadposition(List<Point> lst){
        boolean[] notplayablecell;
        notplayablecell = canloose();
        //remove cell than make IA loose
        for (int i = 0; i < notplayablecell.length; i++) {
            int j=0;
            if (notplayablecell[i])
                while (j<lst.size()){
                    if(board.getcolorcell(lst.get(j).x,lst.get(j).y).equals(board.CODEHEXA[i])){
                        lst.remove(j);
                        j--;
                    }
                    j++;
                }
        }
    }

    public ActionList playFirstPosition(List<Point> lst){
        lst =board.computeValidCells(pawn);
        rowDest= lst.get(0).x;
        colDest= lst.get(0).y;

        return play();
    }

    public ActionList play(){
        // create action list. After the last action, it is next player's turn.
        ActionList actions = new ActionList(true);
        // get the dest. cell center in space.
        GridLook look = (GridLook) control.getElementLook(board);
        Point2D center = look.getRootPaneLocationForCellCenter(rowDest, colDest);
        // create the move action
        GameAction move = new MoveAction(model, pawn, "kamisadoboard", rowDest, colDest, AnimationTypes.MOVELINEARPROP_NAME, center.getX(), center.getY(), 10);
        actions.addSingleAction(move);
        stage.unselectAll();
        return actions;
    }

    /**
     * move simulation for avoid a hidden bad position
     * @param x x-axis test position
     * @param y y-axis test position
     * @param pawnx x-axis pawn position
     * @param pawny y-axis pawn position
     * @return true if the opponent can win next turn esle return false
     */
    public boolean checkIfLoosingNextTurn(int x, int y , int pawnx, int pawny){
        boolean canwin = false;
        board.moveElement(pawn,x,y);
        String color = board.getcolorcell(x,y);
        for (KamisadoPawn pawn : opponentPawns) {
            if(pawn.getColor().getCodeHexa().equals(color)) {
                canwin = canWin(pawn);
                break;
            }
        }
        board.moveElement(pawn, pawnx, pawny);
        return canwin;
    }

    //fonction utilisé uniquement après canWin()

    /**
     * Get the cell that wins the pawn
     * @param p the pawn than can win
     * @return the cell that wins
     */
    public Point winCell(KamisadoPawn p){ // pour le moment renvoie coordonné de la case
        List<Point> ValidCells = board.computeValidCells(p);
        for (int i=0; i<ValidCells.size(); i++){
            if( p.getBorder()== KamisadoPawn.WHITE && ValidCells.get(i).getX()==0)
                return ValidCells.get(i);
            else if ( p.getBorder()== KamisadoPawn.BLACK && ValidCells.get(i).getX()==7){
                return ValidCells.get(i);
            }
        }
        return null;
    }

    /**
     * Compute all possibility for the next 2 play from the current turn to check if IA can win
     * @param possibleCells List of cell than the selected pawn can play
     * @return a position than wins in 2 turn or null
     */
    public Point winInAllCase(List<Point> possibleCells) {
        //boucle sur les cases différentes que l'ia peut joueur
        for(int i=0; i<possibleCells.size();i++){
            //boucle sur les pions adverse
            boolean cantwin = false;
            int[] locpawn = board.getElementCell(pawn);
            String colorPlayed = board.getcolorcell(possibleCells.get(i).x,possibleCells.get(i).y);
            board.moveElement(pawn,possibleCells.get(i).x,possibleCells.get(i).y);

            for( int j=0; j<opponentPawns.length;j++){
                //tesT si la couleur du pion opposant correspond à la couleur des case possiblers que ia peut jouer
                if(opponentPawns[j].getColor().getCodeHexa().equals(colorPlayed)){
                    List<Point> ValidCellsOpponent = board.computeValidCells(opponentPawns[j]);
                    if(ValidCellsOpponent.isEmpty()){
                        int[] locationOpponent = board.getElementCell(opponentPawns[j]);
                        String colorOpponentCase = board.getcolorcell(locationOpponent[0], locationOpponent[1]);
                        for(int l = 0; l < IaPawn.length; l++){
                            if(IaPawn[l].getColor().getCodeHexa().equals(colorOpponentCase)){
                                cantwin = !canWin(IaPawn[l]);
                            }
                        }
                        break;
                    }
                    if(!canWin(opponentPawns[j])) {
                        //boucle sur les cases possible de l'adversaire pour chaque pion adverse possible
                        for (int k = 0; k < ValidCellsOpponent.size() && !cantwin; k++) {
                            int[] locpawnoppo = board.getElementCell(opponentPawns[j]);
                            String colorCellOpp = board.getcolorcell(ValidCellsOpponent.get(k).x, ValidCellsOpponent.get(k).y);
                            board.moveElement(opponentPawns[j], ValidCellsOpponent.get(k).x, ValidCellsOpponent.get(k).y);
                            //veux récup le pion de ia correspopndant à la couleur
                            for (int o = 0; o < IaPawn.length; o++) {
                                // get the pawn how play after opponent move simulation
                                if (IaPawn[o].getColor().getCodeHexa().equals(colorCellOpp)) {
                                    if (!canWin(IaPawn[o])) {
                                        cantwin = true;
                                    }
                                    else cantwin = false;
                                    break;
                                }
                            }
                            board.moveElement(opponentPawns[j], locpawnoppo[0], locpawnoppo[1]);
                        }
                    }
                    else cantwin = true;
                    break;
                }
            }
            board.moveElement(pawn,locpawn[0],locpawn[1]);
            if (!cantwin) {
                return possibleCells.get(i);}
        }
        return null;
    }

    /**
     * Compute the attack point of the AI
     * @return number of AI pawn than can win next turn
     */
    public int attackPoint(){
        int counter=0;
        for(int i=0;i<IaPawn.length;i++){
            if(canWin(IaPawn[i])){
                counter+=1;
            }
        }
        return counter;
    }

    /**
     * Compute the attack point of the opponent
     * @return number of opponent pawn than can win
     */
    public int defensePoint(){
        int counter=0;
        for(int i=0;i<opponentPawns.length;i++){
            if(canWin(opponentPawns[i])){
                counter+=1;
            }
        }
        return counter;
    }

    /**
     * compute an indices to find the most valuable place
     * @param possibleCells List of cell than the selected pawn can play
     * @return  the most valuable play
     */
    public Point whereAreTheBestPlaceToPlay(List<Point> possibleCells) {
        Point best = null;
        int count = -10;
        for (Point possibleCell : possibleCells) {
            int[] pawnloc = board.getElementCell(pawn);
            board.moveElement(pawn, possibleCell.x, possibleCell.y);
            int atk, def; atk=attackPoint(); def=defensePoint();
            if (count < (atk - def) && !checkIfLoosingNextTurn(possibleCell.x, possibleCell.y, pawnloc[0], pawnloc[1])) {
                count = atk - def;
                best = possibleCell;
            }
        }
        return  best;
    }

    @Override
    public ActionList decide(){
        return ia1();
    }

    public ActionList ia1() {
        if(stage.getSelected().isEmpty()) {
            //return a random number between 1 and 6
            colDest=loto.nextInt(6)+1;
            pawn = (KamisadoPawn) board.getElement(0,colDest);
            rowDest=5;
        }
        else{
            pawn = (KamisadoPawn) stage.getSelected().get(0);
            int[] pawnloc = board.getElementCell(pawn);
//            System.out.println(pawnloc[0] + "," + pawnloc[1]+" " + pawn.getColor().getNameColor());
            rowDest = pawnloc[0];
            colDest = pawnloc[1];
            List<Point> lst = board.computeValidCells(pawn);

            if(canWin(pawn)){
                winCell(lst);
            }
            else if (!lst.isEmpty()){
                deletbadposition(lst);

                //It's loose
                if(lst.isEmpty()) return playFirstPosition(lst);

                //try to find a safe cell
                else fetchCellToPlay(lst, pawnloc);
            }
        }
    return play();
    }

    /**
     * Get the cell that wins the pawn
     * @param lst List of cell than the selected pawn can play
     * @return the cell that wins
     */
    public Point winCell(List<Point> lst){
        for (Point point: lst) {
            if (point.x==0 || point.x==7){
                rowDest = point.x;
                colDest = point.y;
                return point;
            }
        }
        return null;
    }

    /**
     * select the cell than AI1 will play in last resort
     * @param lst List of cell than the selected pawn can play
     * @param pawnloc current position of the selected pawn in our board
     */
    public void fetchCellToPlay(List<Point> lst, int[] pawnloc){
        rowDest= lst.get(0).x;
        colDest= lst.get(0).y;
        if( pawn.getBorder()== KamisadoPawn.BLACK) {
            fetchGoodPose(lst, pawnloc[0]+1, pawnloc[1], pawnloc[0], pawnloc[1]);
            fetchGoodPose(lst, pawnloc[0]+1, pawnloc[1]+1, pawnloc[0], pawnloc[1]);
            fetchGoodPose(lst, pawnloc[0]+1, pawnloc[1]-1, pawnloc[0], pawnloc[1]);
            for (int i = 2; i < 8; i++) {
                fetchGoodPose(lst, pawnloc[0] + i, pawnloc[1] + i, pawnloc[0], pawnloc[1]);
                fetchGoodPose(lst, pawnloc[0] + i, pawnloc[1] - i, pawnloc[0], pawnloc[1]);
            }
            for (int i = 2; i < 6; i++) {
                fetchGoodPose(lst,pawnloc[0]+i, pawnloc[1], pawnloc[0], pawnloc[1]);
            }
        }else {
            fetchGoodPose(lst, pawnloc[0]-1, pawnloc[1], pawnloc[0], pawnloc[1]);
            fetchGoodPose(lst, pawnloc[0]-1, pawnloc[1]-1, pawnloc[0], pawnloc[1]);
            fetchGoodPose(lst, pawnloc[0]-1, pawnloc[1]+1, pawnloc[0], pawnloc[1]);
            for (int i = 2; i < 8; i++) {
                fetchGoodPose(lst, pawnloc[0] - i, pawnloc[1] + i, pawnloc[0], pawnloc[1]);
                fetchGoodPose(lst, pawnloc[0] - i, pawnloc[1] - i, pawnloc[0], pawnloc[1]);
            }
            for (int i = 2; i < 6; i++) {
                fetchGoodPose(lst, pawnloc[0]-i, pawnloc[1], pawnloc[0], pawnloc[1]);
            }
        }
    }

    /**
     * fetch a position to play if she's not reassign
     * @param lst List of cell than the selected pawn can play
     * @param x1 x-axis test position
     * @param y1 y-axis test position
     * @param x2 x-axis pawn position
     * @param y2 y-axis test position
     */
    public void fetchGoodPose(List<Point> lst, int x1, int y1, int x2, int y2) {
        if(lst.contains(new Point(x1,y1))
                && !checkIfLoosingNextTurn(x1,y1, x2, y2)) {
            rowDest = x1;
            colDest = y1;
        }
    }

    public ActionList ia2(){
        pawn = (KamisadoPawn) stage.getSelected().get(0); //attention si plusieurs éléments sélectionné à la fin d'1 tours
        List<Point> possibleCells = board.computeValidCells(pawn);

        if(canWin(pawn)){
            Point cellToPlay = winCell(pawn);
            rowDest = cellToPlay.x;
            colDest = cellToPlay.y;
        }
        else{
            //strat 2)
            deletbadposition(possibleCells);

            //strat 3)
            Point cellToPlay = winInAllCase(possibleCells);
            if (cellToPlay!=null) {
                rowDest = cellToPlay.x;
                colDest = cellToPlay.y;
            }
            else {
                Point best = whereAreTheBestPlaceToPlay(possibleCells);
                if(best!=null){
                    rowDest = best.x;
                    colDest = best.y;
                }

                //loose
                else{
                    playFirstPosition(possibleCells);
                }
            }
        }
        return play();
    }
}