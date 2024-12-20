package control;

import gamifier.control.*;
import gamifier.model.Model;
import model.Color;
import model.KamisadoBoard;
import model.KamisadoPawn;
import model.KamisadoStageFactory;
import model.KamisadoStageModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.util.List;
import java.sql.SQLOutput;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;

public class TestDecider {
    protected DeciderKamisado decider;
    protected Model model;
    protected KamisadoStageModel stage;

    protected KamisadoPawn pawnWhite1;
    protected KamisadoPawn pawnWhite2;
    protected KamisadoPawn pawnWhite3;
    protected KamisadoPawn pawnWhite4;
    protected KamisadoPawn pawnWhite5;
    protected KamisadoPawn pawnWhite6;
    protected KamisadoPawn pawnWhite7;
    protected KamisadoPawn pawnWhite8;

    protected KamisadoPawn pawnBlack1;
    protected KamisadoPawn pawnBlack2;
    protected KamisadoPawn pawnBlack3;
    protected KamisadoPawn pawnBlack4;
    protected KamisadoPawn pawnBlack5;
    protected KamisadoPawn pawnBlack6;
    protected KamisadoPawn pawnBlack7;
    protected KamisadoPawn pawnBlack8;

    protected List<Point> lst;
    protected List<Point> lstResults;


    @BeforeEach
    public void setup(){
        model = new Model();
        stage = new KamisadoStageModel("test", model);
        model.setGameStage(stage);
        stage.setBoard(new KamisadoBoard(8,8,stage));
        decider = new DeciderKamisado(model, mock(Controller.class));

        pawnBlack1 = new KamisadoPawn(stage, new Color("橙", "#FF8C00"), 0); //orange
        pawnBlack2 = new KamisadoPawn(stage, new Color("红","#f00020"), 0);  //red
        pawnBlack3 = new KamisadoPawn(stage, new Color("绿", "#2E8B57"), 0); //green
        pawnBlack4 = new KamisadoPawn(stage, new Color("粉", "#C71585"), 0); //pink
        pawnBlack5 = new KamisadoPawn(stage, new Color("黄", "#FFD700"), 0); //yellow
        pawnBlack6 = new KamisadoPawn(stage, new Color("蓝", "#6495ED"), 0); //blue
        pawnBlack7 = new KamisadoPawn(stage, new Color("紫", "#483D8B"), 0); //purple
        pawnBlack8 = new KamisadoPawn(stage, new Color("棕", "#8B4513"), 0); //brown

        pawnWhite1 = new KamisadoPawn(stage, new Color("棕", "#8B4513"), 1);
        pawnWhite2 = new KamisadoPawn(stage, new Color("紫", "#483D8B"), 1);
        pawnWhite3 = new KamisadoPawn(stage, new Color("蓝", "#6495ED"), 1);
        pawnWhite4 = new KamisadoPawn(stage, new Color("黄", "#FFD700"), 1);
        pawnWhite5 = new KamisadoPawn(stage, new Color("粉", "#C71585"), 1);
        pawnWhite6 = new KamisadoPawn(stage, new Color("绿", "#2E8B57"), 1);
        pawnWhite7 = new KamisadoPawn(stage, new Color("红","#f00020"), 1);
        pawnWhite8 = new KamisadoPawn(stage, new Color("橙", "#FF8C00"), 1);

        lst = new ArrayList<Point>();
        lstResults = new ArrayList<Point>();
    }
}
