import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Delo extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    PlayGround pg;
    Random rnd = new Random();
    double height = 600;
    double width = 800;
    double g = 0.7;
    double k = 0.999;

    double dx = width/2;
    double dy = 570;
    double deloW = 100, deloH=30;

    double mx;
    double my;

    double ballR = 20;
    double BOUNCE = 0.9;
    ArrayList<Ball> balls = new ArrayList<>();
    List<Color> farby = List.of(Color.PURPLE, Color.AQUA,Color.BLUE,Color.CHOCOLATE,Color.RED,Color.ORANGE,Color.LIME,Color.YELLOW);

    Thread main;
    boolean run = true;

    Button reset = new Button("Reset");
    Button gravP = new Button("Grav+");
    Button gravM = new Button("Grav-");
    Button vietorP = new Button("Odpor+");
    Button vietorM = new Button("Odpor-");
    Button quit = new Button("Quit");

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        pg = new PlayGround();
        pg.setOnMouseMoved(event -> {
            mx = event.getX();
            my = event.getY();
            try{Thread.sleep(1);} catch(InterruptedException ignored){};
        });

        pg.setOnMouseClicked(event -> {
            switch(event.getButton()) {
                case PRIMARY: {
                    balls.add(new Ball((event.getX() - dx) * 0.1, (event.getY() - dy) * 0.1));
                    System.out.println(balls);
                    break;
                }
                case SECONDARY:{
                    System.out.println(balls);
                }
            }
        });
        reset.setOnAction(e->{ g = -0.7; k = 0.999;  });
        gravM.setOnAction(e->{ g -= g <= -2 ? 0 : 0.1;   });
        gravP.setOnAction(e->{ g += g >= 2 ? 0 : 0.1;   });
        vietorP.setOnAction(e->{ k -= k <= 0 ? 0: 0.01;  });
        vietorM.setOnAction(e->{
            k += 0.01;
            if(k >= 1) k = 0.999;
        });
        quit.setOnAction(e->{ Platform.exit(); });

        HBox topPanel = new HBox(reset, gravP, gravM, vietorP, vietorM, quit);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setSpacing(50);

        root.setCenter(pg);
        root.setTop(topPanel);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Delo");
        stage.show();

        main = new Thread(()->{
            while(run) {
                pg.paint();
                int i = 0;
                while(i < balls.size()){
                    var ball = balls.get(i);
                    if(ball.stop){
                        balls.remove(i);
                    }
                    else {
                        ball.move();
                        i++;
                    }
                }
                try {Thread.sleep(15);} catch (InterruptedException ignored) {}
            }
        });
        main.start();
    }

    @Override
    public void stop(){
        run = false;
        try{
            main.join();
        } catch (InterruptedException ignored){};
    }

    class PlayGround extends Canvas {
        public PlayGround(){
            setWidth(width);
            setHeight(height);
            setFocusTraversable(true);
        }

        public void paint(){
            GraphicsContext gc = getGraphicsContext2D();
            gc.setFill(Color.LIGHTGRAY);
            gc.fillRect(0,0,width,height);

            for(Ball b: balls){
                b.paint(gc);
            }
            gc.setStroke(Color.BLACK);
            gc.strokeLine(dx,dy,mx,my);

            gc.setFill(Color.GRAY);
            double uhol = Math.atan((my-dy)/(mx-dx));
            gc.setTransform(new Affine((Affine.rotate(degToRad(uhol), dx, dy))));
            gc.fillRect(dx-deloW/2,dy-deloH/2,deloW,deloH);
            gc.setTransform(new Affine());
        }
    }

    public static double degToRad(double rad){
        return rad*180/Math.PI;
    }


    class Ball{
        double x;
        double y;
        double sx;
        double sy;
        Color farba;
        boolean stop = false;

        public Ball(double sx, double sy){
            farba = farby.get(rnd.nextInt(farby.size()));
            this.x = dx;
            this.y = dy;
            this.sx = sx;
            this.sy = sy;
        }
        public Ball(){
            this(0,0);
        }

        public void paint(GraphicsContext gc){
            gc.setFill(farba);
            gc.fillOval(x-ballR/2,y-ballR/2,ballR,ballR);
        }

        public void move(){
            if(stop) return;

            sy = (sy+g)*k;
            sx = sx*k;

            x += sx;
            if((y < height-ballR/2 || Math.abs(sy) > 0.5) && (y > ballR/2 || Math.abs(sy) > 0.5) )
                y += sy;

            if((sx < 0 && x < ballR/2) ||
                    (sx > 0 && width-ballR/2 < x))
                sx *= -BOUNCE;
            else if((sy < 0 && y <= ballR/2) ||
                    (sy > 0 && height-ballR/2 < y))
                sy *= -BOUNCE;

            //ak sa uz nehýbe, zastavíme ju
            //this.stop = true;

        }
        @Override
        public String toString(){
            return "Ball: " + sx + " " + sy;
        }
    }
}
