import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class Life extends Application {
    final static int SIZE = 60;
    PlayGround pg;
    State ps = new State(SIZE);

    @Override
    public void start(Stage primaryStage) throws Exception{
        pg = new PlayGround();
        Scene scene = new Scene(pg, 800, 800);
        pg.paint();
        scene.widthProperty().addListener((observableValue, old, newSceneWidth) -> {
            pg.prefWidth((double) newSceneWidth);
            pg.paint();
        } );
        scene.heightProperty().addListener((observableValue, old, newSceneHeight) -> {
            pg.prefHeight((double) newSceneHeight);
            pg.paint();
        } );
        Timer t = new Timer();
        t.scheduleAtFixedRate(
                new TimerTask() {
                       @Override
                       public void run() {
                           ps.update();
                           Platform.runLater(() -> pg.paint());
                       }
                   }, 0, 20
        );
        primaryStage.setTitle("Conway's Life");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public class PlayGround extends GridPane {
        Cell[][] canvasGrid = new Cell[SIZE][SIZE];

        public PlayGround() {
            for (int i = 0; i < Life.SIZE; i++)
                for (int j = 0; j < Life.SIZE; j++) {
                    Cell pc = canvasGrid[i][j] = new Cell(i, j);
                    add(pc, j, i);
                    pc.widthProperty().bind(widthProperty().divide(SIZE));
                    pc.heightProperty().bind(heightProperty().divide(SIZE));
                }
        }
        public void paint() {
            for (int i = 0; i < Life.SIZE; i++)
                for (int j = 0; j < Life.SIZE; j++)
                    canvasGrid[i][j].paintCell();
        }
    }
    public class Cell extends Canvas {
        int i, j;
        public Cell(int i, int j) {
            this.i = i; this.j = j;
        }
        public void paintCell() {
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());
            gc.strokeRect(0, 0, getWidth(), getHeight());
            gc.setFill((ps.playground[i][j] == 1)? Color.rgb(255,112,20):Color.BLACK);
            gc.fillRect(1,1,getWidth()-1, getHeight()-1);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
