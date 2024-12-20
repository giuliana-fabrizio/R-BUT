package model;

import gamifier.model.*;
import gamifier.model.ElementTypes;
import gamifier.model.animation.AnimationStep;
import gamifier.view.GridGeometry;

public class KamisadoPawn extends GameElement {
    public final static int BLACK = 0;
    public final static int WHITE = 1;

    private int border;
    protected Color color;
    protected Player player;
    protected int state;

    public KamisadoPawn(GameStageModel gameStageModel, Color color, int border){
        super(gameStageModel);
       // this.setLocation(5,5);
        ElementTypes.register("pawn",9);
        type = ElementTypes.getType("pawn");
        this.color=color;
        //this.player=player;
        this.state=0;
        this.border=border;
    }

    public int getBorder() {
        return border;
    }

    public Color getColor() {
        return color;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getState() {
        return this.state;
    }

    @Override
    public void update(double width, double height, GridGeometry gridGeometry) {
        // if must be animated, move the pawn
        if (animation != null) {
            AnimationStep step = animation.next();
            if (step != null) {
                setLocation(step.getInt(0), step.getInt(1));
            }
            else {
                animation = null;
            }
        }
    }
}

