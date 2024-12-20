package view;

import gamifier.model.GameElement;
import gamifier.view.ElementLook;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import model.KamisadoPawn;
import javafx.scene.paint.Color;

import java.awt.*;

public class PawnView extends ElementLook {
    Circle circle;
    static final int radius=25;

    public PawnView(GameElement element, boolean isPawnFirstPlayer){
        super(element);
        KamisadoPawn pawn = (KamisadoPawn)element;


        circle = new Circle();
        circle.setRadius(radius);


        if(isPawnFirstPlayer){
            circle.setFill(Color.BLACK);
        }
        else{
            circle.setFill(Color.WHITE);
        }
        Bounds btc = circle.getBoundsInLocal();
        circle.setCenterX((((500 - 20) / 8)/2)-5);
        circle.setCenterY((((500 - 20) / 8)/1.9)-5);

        Text text = new Text(pawn.getColor().getNameColor());
        Bounds bt = text.getBoundsInLocal();

        text.setFill(Color.valueOf(pawn.getColor().getCodeHexa()));
        text.setFont(new Font(30));

        text.setX(8);
        text.setY((((500 - 20) / 8)/1.5)-5);

        text.setTextAlignment(TextAlignment.CENTER);

        Group grp = new Group();
        grp.getChildren().addAll(circle,text);

        addShape(circle);
        addShape(text);
    }

    @Override
    public void onSelectionChange() {
        KamisadoPawn pawn = (KamisadoPawn)getElement();
        if (pawn.isSelected()) {
            circle.setStrokeWidth(3);
            circle.setStrokeMiterLimit(10);
            circle.setStrokeType(StrokeType.CENTERED);
            circle.setStroke(Color.valueOf("0x333333"));
        }
        else {
            circle.setStrokeWidth(0);
        }
    }
}
