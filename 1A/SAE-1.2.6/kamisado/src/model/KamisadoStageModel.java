package model;
import gamifier.model.GameStageModel;
import gamifier.model.Model;
import gamifier.model.StageElementsFactory;
import gamifier.model.TextElement;


public class KamisadoStageModel extends GameStageModel {
    private boolean firstTurn;

    public final static int STATE_SELECTPAWN = 0; // the player must select a pawn
    public final static int STATE_SELECTDEST = 1; // the player must select a destination

    // states
    public final static int pawnj1 = 1;
    public final static int pawnj2 = 2;
    private KamisadoBoard board;
    private KamisadoPawn[] j1;
    private KamisadoPawn[] j2;
    private int J1PawnsToPlay;
    private int J2PawnsToPlay;
    private TextElement playerName;
    public String colorToPlay;
    public final int DEBUT=0;

    public KamisadoStageModel(String name, Model model) {
        super(name, model);
        state = DEBUT;
        J1PawnsToPlay = 8;
        J2PawnsToPlay = 8;
        setupCallbacks();
        firstTurn = true;
    }

    @Override
    public StageElementsFactory getDefaultElementFactory() {
        return new KamisadoStageFactory(this);
    }

    public KamisadoBoard getBoard() {
        return board;
    }

    public void setBoard(KamisadoBoard board) {
        this.board = board;
        addGrid(board);
    }

    public TextElement getPlayerName() {
        return playerName;
    }

    public void setPlayerName(TextElement playerName) {
        this.playerName = playerName;
        addElement(playerName);
    }

    public KamisadoPawn[] getj1Pawns() {
        return j1;
    }

    public void setj1Pawns(KamisadoPawn[] j1) {
        this.j1 = j1;
        for (int i = 0; i < j1.length; i++) {
            addElement(j1[i]);
        }
    }

    public KamisadoPawn[] getj2Pawns() {
        return j2;
    }

    public void setj2Pawns(KamisadoPawn[] j2) {
        this.j2 = j2;
        for (int i = 0; i < j2.length; i++) {
            addElement(j2[i]);
        }
    }

    public boolean cantMove(KamisadoPawn pawn) {
        return getBoard().computeValidCells(pawn).isEmpty();
    }

    public boolean cantMoveOpposent(KamisadoPawn pawn) {
        pawnCantMove(pawn);
        if (pawn.getBorder() == 0) {
            for (KamisadoPawn p: j1) {
                if (p.getColor().getCodeHexa() == colorToPlay && cantMove(p))
                    return false;
            }
        }
        else if (pawn.getBorder() == 1) {
            for (KamisadoPawn p: j2) {
                if (p.getColor().getCodeHexa() == colorToPlay && cantMove(p))
                    return false;
            }
        }
        return true;
    }

    public void pawnCantMove(KamisadoPawn pawn){
        int [] loc = board.getElementCell(pawn);
        System.out.println("no solution " + pawn.getColor().getNameColor() + " " + pawn.getBorder());
        colorToPlay = board.getcolorcell(loc[0],loc[1]);
    }

    protected void setupCallbacks() {
        onSelectionChange( () -> {
            if (selected.size() == 0) {
                board.resetReachableCells(false);
                return;
            }
            KamisadoPawn pawn = (KamisadoPawn) selected.get(0);
            System.out.println("selected " + pawn.getColor().getNameColor() + " " + pawn.getBorder());
            board.resetReachableCells(false);
            board.setValidCells(pawn);
        });

        onMoveInGrid( (element, gridDest, rowDest, colDest) -> {
            if (gridDest != board) return;
            KamisadoPawn pawn = (KamisadoPawn) element;
            if ((pawn.getBorder() == KamisadoPawn.BLACK && rowDest == 7) || (pawn.getBorder() == KamisadoPawn.WHITE && rowDest == 0)) {
                computePartyResult(pawn);
            }else {
                colorToPlay = board.getcolorcell(rowDest,colDest);
//                model.unselectAll();
            }
        });
    }

    protected void computePartyResult(KamisadoPawn pawn) {
        int idWinner = -1;

        //System.out.println(pawn.getColor());
        // Voit si c J1 ou J2 qui a atteint derniere case.
        // joueur noir
        if(pawn.getBorder() == KamisadoPawn.WHITE)
            idWinner=0;
        else
            idWinner=1;
        //Lui ajouter un point

        //Reprend ses infos
        model.setIdWinner(idWinner);
        // stop de the game
        model.stopGame();
    }

    public boolean getFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }
}