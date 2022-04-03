import Board.Painter;
import Board.WayGrid;
import FuzzyLogic.FuzzyLogic;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FuzzyLogic");
        Group root = new Group();
        Canvas canvas = new Canvas(640, 640);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        WayGrid wayGrid = new WayGrid(canvas);
        Painter painter = new Painter(gc, canvas, wayGrid);
        painter.start();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


}
