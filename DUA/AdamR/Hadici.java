import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Hadici extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    PlayGround pg;
    Thread mainThread;
    boolean run = false;
    boolean gameOn = false;

    Hadik hrac1;
    Hadik hrac2;
    List<Obstacle> prekazky = new ArrayList<>();

    //rychlost vpred;
    double velocity = 4;
    //rychlost otačania;
    int turnV = 6;
    double r = 10;

    Random rnd = new Random();

    Button startButton = new Button("Start");
    Button restartButton = new Button("Restart");

    @Override
    public void stop() throws Exception {
        gameOn = false;
        run = false;
        try{
            mainThread.join();
        } catch(InterruptedException ignored) {}
    }

    @Override
    public void start(Stage stage) throws Exception {
        pg = new PlayGround(800,600);
        BorderPane root = new BorderPane();
        HBox topPanel = new HBox(startButton, restartButton);
        topPanel.setSpacing(50);
        topPanel.setAlignment(Pos.CENTER);

        startButton.setOnAction(e->{
            if(!run && !gameOn) {
                run = true;
                gameOn = true;
                run();
            }
            pg.requestFocus();
            startButton.setText("hello");
        });

        restartButton.setOnAction(e->{
            restart();
            pg.requestFocus();
        });

        root.setTop(topPanel);
        root.setCenter(pg);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hadici");
        stage.show();

        pg.setOnMouseClicked(e->{
            if(e.getButton().equals(MouseButton.PRIMARY)){
                System.out.println("klick");
            }
        });

        pg.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case A: hrac1.smer = -turnV; break;
                case D: hrac1.smer = turnV; break;
                case LEFT: hrac2.smer = -turnV; break;
                case RIGHT: hrac2.smer = turnV; break;
            }
            System.out.println(e.getCode());
        });
        pg.setOnKeyReleased(e ->{
            switch(e.getCode()){
                case A:
                case D: hrac1.smer = 0; break;
                case LEFT:
                case RIGHT: hrac2.smer = 0; break;
            }
            System.out.println(e.getCode());
        });
        //nepekne pridavanie prekažok, ale nestiham vymysliet nahodne a osetrovat aby neboli v hračovi
        prekazky.add(new Obstacle(100,100,100,50));
        prekazky.add(new Obstacle(300,100,50,100));
        prekazky.add(new Obstacle(450,400,50,50));

        mainThread = new Thread(()->{
            while(run) {
                if(gameOn) {
                    hrac1 = new Hadik(50, 50, Color.RED);
                    hrac2 = new Hadik(50, 550, Color.BLUE);
                    pg.paintObstacles();
                }
                while (gameOn) {
                    pg.paint();
                    hrac1.move();
                    hrac2.move();

                    for (Obstacle o : prekazky) {
                        if (hrac1.collide(o)) {
                            end("Hráč 1");
                        }
                        else if(hrac2.collide(o)){
                            end("Hráč 2"  );
                        }
                    }

                    if (hrac1.outside()) {
                        end("Hráč 1");
                    }
                    else if(hrac2.outside()){
                        end("Hráč 2"  );
                    }

                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ignored) {
                    }
                }
                try { Thread.sleep(400);} catch (InterruptedException ignored) {}
            }
        });
    }
    private void end(String hrac){
        gameOn = false;
        var gc = pg.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Verdana",50));
        gc.fillText(hrac +" narazil!", pg.getWidth()/2-100,pg.getHeight()/2-50);
    }

    private void restart(){
        gameOn = false;
        var gc = pg.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,pg.getWidth(),pg.getHeight());
        try{
            Thread.sleep(400);
        } catch (InterruptedException ignored){}
        gameOn = true;
    }

    private void run(){
        mainThread.start();
    }

    class PlayGround extends Canvas {
        public PlayGround(double width, double height){
            setHeight(height);
            setWidth(width);
            this.setFocusTraversable(true);
            this.requestFocus();
        }

        public void paint(){
            var gc = getGraphicsContext2D();
            hrac1.paint(gc);
            hrac2.paint(gc);
        }
        //stači nam ich nakresliť raz
        public void paintObstacles(){
            var gc = getGraphicsContext2D();

            for(Obstacle o: prekazky){
                var x = o.getLeftTop().getX();
                var y = o.getLeftTop().getY();
                gc.setFill(o.farba);
                gc.fillRect(x,y,o.sirka,o.vyska);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(5);
                gc.strokeRect(x,y,o.sirka,o.vyska);
            }
        }
        public void clear(){
            var gc = getGraphicsContext2D();
            gc.setFill(Color.WHITE);
            gc.fillRect(0,0,getWidth(),getHeight());
        }
    }

    class Obstacle{
        private final Point2D lt;
        Color farba;
        double sirka,vyska;
        public Obstacle(double x, double y, double sirka, double vyska){
            lt = new Point2D(x,y);
            this.sirka = sirka;
            this.vyska = vyska;
            this.farba = Color.color(rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble());
        }
        public Point2D getLeftTop(){
            return lt;
        }
        public Point2D getLeftBottom(){
            return new Point2D(lt.getX(), lt.getY()+vyska);
        }
        public Point2D getRightTop(){
            return new Point2D(lt.getX()+sirka, lt.getY());
        }
        public Point2D getRightBottom(){
            return new Point2D(lt.getX()+sirka, lt.getY()+vyska);
        }
        public List<Point2D> points(){
            return List.of(getLeftTop(),getLeftBottom(),getRightBottom(),getRightTop());
        }
    }

    class Hadik{
        double uhol, x,y;
        Color color;
        int smer = 0;
        public Hadik(double x, double y, Color fill){
            this.uhol = 0;
            this.x = x;
            this.y = y;
            color = fill;
        }
        public void move(){
            uhol += smer;
            if(uhol > 360) uhol -= 360;
            else if(uhol < 0) uhol += 360;

            x += Math.cos(degToRad(uhol))*velocity;
            y += Math.sin(degToRad(uhol))*velocity;
        }
        public void paint(GraphicsContext gc){
            gc.setFill(color);
            gc.fillOval(x-r,y-r,r*2,2*r);
        }

        public boolean collide(Obstacle prekazka){
            Point2D lt = prekazka.getLeftTop();
            Point2D rt = prekazka.getRightBottom();

            //najblisi bod z odlznika
            double xn = Math.max(lt.getX(), Math.min(x, rt.getX()));
            double yn = Math.max(lt.getY(), Math.min(y, rt.getY()));

            return Math.pow(xn-x,2) + Math.pow(yn-y,2) <= r*r+5;
        }
        public boolean outside(){
            return x <= r || x >= pg.getWidth()-r || y <= r || y >= pg.getHeight()-r;
        }
    }

    public static double degToRad(double deg){
        return deg * Math.PI / 180;
    }
}
class Point2D {
    private double x, y;
    public Point2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    public double distance(Point2D o){
        return Math.sqrt(Math.pow(x-o.getX(),2) + Math.pow(y-o.getY(),2));
    }

    public static double distance(Point2D a, Point2D b){
        return Math.sqrt(Math.pow(a.getX()-b.getX(),2) + Math.pow(a.getY()-b.getY(),2));
    }
}