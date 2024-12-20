import control.ControllerKamisado;
import gamifier.control.StageFactory;
import gamifier.view.PaneView;
import gamifier.view.SimpleTextView;
import gamifier.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import view.BasicView;

public class Kamisado extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // create the global model
        Model model = new Model();
        // add some players
        // use model.addComputerPlayer(...) ot add a computer player
        model.addHumanPlayer("player1");
//        model.addHumanPlayer("player2");
        model.addComputerPlayer("computer");
//        model.addHumanPlayer("player2");
        // register a single stage for the game, called Kamisado
        StageFactory.registerModel("Kamisado", "model.KamisadoStageModel");
        StageFactory.registerView("Kamisado", "view.KamisadoStageView");
        // create the global view.
        BasicView view = new BasicView(model, stage);
        // create a pane view for the introduction panel
        SimpleTextView introView = new SimpleTextView(600,100, "intro", "playing to The Kamisado");
        // create a pane view for the game itself
        PaneView paneView = new PaneView("gamepane");
        // add pane views to the global view.
        view.addPaneView(introView);
        view.addPaneView(paneView);
        // create the controllers.
        ControllerKamisado control = new ControllerKamisado(model,view);
        // set the name of the first pane view to use when the game is started
        control.setGamePaneViewName("gamepane");
        // set the name of the first stage to create when the game is started
        control.setFirstStageName("Kamisado");
        // set the stage title
        stage.setTitle("Kamisado");
        // show the JavaFx main stage
        stage.show();
        // set the current view to display the intro pane view.
        // In this case, no gamestageview is given since all visual elements are created directly in SimpleTextView.
        view.setView(introView.getName(), null);
    }
}
