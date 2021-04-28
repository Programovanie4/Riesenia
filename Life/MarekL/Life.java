import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class Life extends Application {
    final static int SIZE = 100;
    PlayGround pg;
    State ps = new State(SIZE);

    @Override
    public void start(Stage primaryStage) throws Exception{
        pg = new PlayGround();
        Scene scene = new Scene(pg, 800, 800);
        scene.getRoot().requestFocus(); // bez tohoto nefunguje key press event

        pg.paint();
        scene.widthProperty().addListener((observableValue, old, newSceneWidth) -> {
            pg.prefWidth((double) newSceneWidth);
            pg.paint();
        } );
        scene.heightProperty().addListener((observableValue, old, newSceneHeight) -> {
            pg.prefHeight((double) newSceneHeight);
            pg.paint();
        } );

        var timerWrapper = new Object() { // aby som mohol narabat v  lambde
            Timer t = new Timer();
            boolean pause = false;
        };

        timerWrapper.t.scheduleAtFixedRate( // na zaciatku 3s pocka
                new TimerTask() {
                       @Override
                       public void run() {
                           ps.update();
                           Platform.runLater(() -> pg.paint());
                       }
                   }, 3000, 200
        );


        pg.setOnMouseClicked(event -> { // right click pozastavi simulaciu left click prida/odstrani novy live stvorec
            switch(event.getButton()) {
                case PRIMARY:
                    pg.modifyCell(event.getX(), event.getY());
                    break;
                case SECONDARY:
                    if (!timerWrapper.pause) { // ak nie je pozastaveny tak zastav
                        System.out.println("paused");
                        timerWrapper.t.cancel();
                        timerWrapper.pause = true;
                    } else {
                        System.out.println("unpaused");
                        timerWrapper.t = new Timer(); // ak je zastaveny(zruseny) tak vytvor novy
                        timerWrapper.pause = false;
                        timerWrapper.t.scheduleAtFixedRate(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        ps.update();
                                        Platform.runLater(() -> pg.paint());
                                    }
                                }, 0, 200
                        );
                    }
            }
        });

        // mazanie ukladanie a nacitanie pomocou klaves DELETE S a L

        pg.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            switch(event.getCode()) {
                case DELETE:
                    timerWrapper.t.cancel();
                    timerWrapper.pause = true;
                    pg.clear();
                    break;
                case S:
                    ps.save();
                    break;
                case L:
                    State s = ps.load();
                    ps = (s == null) ? ps: s;
                    pg.paint();
            }
        });

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

        private void modifyCell(double x, double y) { // prida novu live cell na poziciu x y
            double width = pg.getWidth();
            double height = pg.getHeight();
            System.out.println("x: " + x + " y: " + y);

            double colSize = height / SIZE;
            double rowSize = width / SIZE;

            int col = (int)(x / rowSize);
            int row = (int)(y / colSize);
            System.out.println("row: " + row + " col: " + col);
            ps.playground[row][col] = Math.abs(ps.playground[row][col]-1); // ak je 0 tak 1 ak je 1 tak 0
            paint();
        }

        private void clear() { // maze plochu
            ps.playground = new int[SIZE][SIZE];
            paint();
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
            gc.setFill((ps.playground[i][j] == 1)? Color.RED:Color.WHITE);
            gc.fillRect(1,1,getWidth()-1, getHeight()-1);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
