package view;

import gamifier.model.GameElement;
import gamifier.view.GridLook;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.KamisadoBoard;

public class KamisadoBoardLook extends GridLook {

    // the array of rectangle composing the grid
    private Rectangle[][] cells;
    private Circle[][] cell;

    public KamisadoBoardLook(int size, GameElement element) {
        // NB: To have more liberty in the design, GridLook does not compute the cell size from the dimension of the element parameter.
        // If we create the 3x3 board by adding a border of 10 pixels, with cells occupying all the available surface,
        // then, cells have a size of (size-20)/3
        super(size, size, (size - 20) / 8, (size - 20) / 8, 10, "F8B9FF", element);
        cells = new Rectangle[8][8];
        cell = new Circle[8][8];
        // create the rectangles.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color c = null;
                if (i == j) {
                    c = Color.BROWN;
                }
                if (((i - j) == 4) || ((j - i) == 4)) {
                    c = Color.DEEPPINK;
                }
                if ((j - i == 2 && j % 2 != 0) || (i - j == 2 && j % 2 == 0) || (j - i == 6 && j % 2 == 0) || (i - j == 6 && j % 2 != 0)) {
                    c = Color.RED;
                }
                if ((j - i == 2 && j % 2 == 0) || (i - j == 2 && j % 2 != 0) || (j - i == 6 && j % 2 != 0) || (i - j == 6 && j % 2 == 0)) {
                    c = Color.BLUE;
                }
                if (i + j == 7) {
                    c = Color.DARKORANGE;
                }
                if (i + j == 3 || i + j == 11) {
                    c = Color.YELLOW;
                }
                if ((i + j == 5 && i % 2 == 0) || (i + j == 9 && i % 2 != 0) || (i + j == 1 && i % 2 != 0) || (i + j == 13 && i % 2 == 0)) {
                    c = Color.GREEN;
                }
                if ((i + j == 5 && i % 2 != 0) || (i + j == 9 && i % 2 == 0) || (i + j == 1 && i % 2 == 0) || (i + j == 13 && i % 2 != 0)) {
                    c = Color.PURPLE;
                }
                cells[i][j] = new Rectangle(cellWidth, cellHeight, c);
                cells[i][j].setX(j * cellWidth + borderWidth);
                cells[i][j].setY(i * cellHeight + borderWidth);
                addShape(cells[i][j]);
            }
        }

        cell = new Circle[8][8];
        // create the circle.
        for (int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                cell[i][j] = new Circle(cellWidth/5, Color.TRANSPARENT);
                cell[i][j].setCenterX((j+1)*cellWidth+borderWidth-(cellWidth/2));
                cell[i][j].setCenterY((i+1)*cellWidth+borderWidth-(cellWidth/2));
                addShape(cell[i][j]);
            }
        }
    }

    @Override
    public void onChange() {
        // in a pawn is selected, reachableCells changes. Thus, the look of the board must also changes.
        KamisadoBoard board = (KamisadoBoard) element;
        boolean[][] reach = board.getReachableCells();
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                if (reach[i][j]) {
                    cell[i][j].setStrokeWidth(5);
                    cell[i][j].setStrokeMiterLimit(50);
                    cell[i][j].setStrokeType(StrokeType.CENTERED);
                    cell[i][j].setStroke(Color.valueOf("0x333333"));
                } else {
                    cell[i][j].setStrokeWidth(0);
                }
            }
        }
    }
}