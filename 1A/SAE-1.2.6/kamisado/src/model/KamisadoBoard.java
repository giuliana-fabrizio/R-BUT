package model;

import gamifier.model.GridElement;
import gamifier.model.GameStageModel;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class KamisadoBoard extends GridElement {

    public final String[] CODEHEXA = new String[] {"#FF8C00", "#f00020", "#2E8B57", "#C71585", "#FFD700", "#6495ED", "#483D8B", "#8B4513"};
    public int[][] colorcell =
            {{7,2,1,4,3,6,5,0},
             {6,7,4,5,2,3,0,1},
             {5,4,7,6,1,0,3,2},
             {4,1,2,7,0,5,6,3},
             {3,6,5,0,7,2,1,4},
             {2,3,0,1,6,7,4,5},
             {1,0,3,2,5,4,7,6},
             {0,5,6,3,4,1,2,7}};


    public KamisadoBoard(int x, int y, KamisadoStageModel model) {
        // call the super-constructor to create a 3x3 grid, named "holeboard", and in x,y in space
        super("kamisadoboard", x, y, 8, 8, model);
        resetReachableCells(false);
    }

    public String getcolorcell(int x,int y) {
        return CODEHEXA[colorcell[y][x]];
    }

    public void setValidCells(KamisadoPawn pawn) {
        resetReachableCells(false);
        List<Point> valid = computeValidCells(pawn);
        if (valid != null) {
            for(Point p : valid) {
                reachableCells[p.x][p.y] = true;
            }
        }
        lookChanged = true;
    }

    public List<Point> computeValidCells(KamisadoPawn pawn){
        List<Point> lst = new ArrayList<>();
        int [] pawnposition = getElementCell(pawn);

        int x = pawnposition[0];
        int y = pawnposition[1];

        if(pawn.getBorder()==0) {

            x++;
            while (x<this.nbRows && isEmptyAt(x,y)) {
                lst.add(new Point(x,y));
                x++;
            }

            x = pawnposition[0] +1;
            y = pawnposition[1] +1;
            while (x<this.nbRows && y<this.nbCols && isEmptyAt(x,y)) {
                lst.add(new Point(x,y));
                x++;
                y++;
            }

            x = pawnposition[0] +1;
            y = pawnposition[1] -1;
            while (x<this.nbRows && y>=0 && isEmptyAt(x,y)) {
                lst.add(new Point(x,y));
                x++;
                y--;
            }
        } else {
            x--;
            while (x>=0 && isEmptyAt(x,y)) {
                lst.add(new Point(x,y));
                x--;
            }

            x = pawnposition[0] -1;
            y = pawnposition[1] +1;
            while (x>=0 && y<this.nbCols && isEmptyAt(x,y)) {
                lst.add(new Point(x,y));
                x--;
                y++;
            }

            x = pawnposition[0] -1;
            y = pawnposition[1] -1;
            while (x>=0 && y>=0 && isEmptyAt(x,y)) {
                lst.add(new Point(x,y));
                x--;
                y--;
            }
        }
        return lst;
    }

}

