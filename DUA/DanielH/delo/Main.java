package delo;

import com.sun.javafx.geom.Vec2d;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main extends Application {
    static double BALL_RADIUS = 10;
    static double GRAVITY_ACCELERATION = -0.3;
    static double WIND_RESISTANCE = 0.99;

    static double WIDTH = 700;
    static double HEIGHT = 700;

    double canonLength = 110;
    double canonWidth = 30;
    double canonOffset = 10;

    Random rnd = new Random();
    final BallPane canvas = new BallPane();
    double pivotX = WIDTH/2;
    double pivotY = HEIGHT;

    Delo delo = new Delo();
    Line line = new Line(pivotX,pivotY,delo.lastMx,delo.lastMy);
    List<Gulicka> gulicky;

    @Override
    public void start(Stage stage) throws Exception {
        line.setStroke(Color.GRAY);
        canvas.getChildren().add(line);

        canvas.setOnMouseMoved(event -> {
            //uprav delo
            line.setEndX(event.getX());
            line.setEndY(event.getY());
            delo.update(event.getX(),event.getY());
        });

        canvas.setOnMouseClicked(event -> {
            gulicky.add(new Gulicka());
            //todo vystrel z dela
        });

        gulicky = new ArrayList<>();


        Gulicka g1 = new Gulicka(157,680,0.3,15.3);
        g1.setFill(Color.RED);
        Gulicka g2 = new Gulicka(159,700,-1.5,0);
        g2.setFill(Color.BLUE);

        gulicky.add(g1);
        gulicky.add(g2);







        Timeline tl = new Timeline(new KeyFrame(new Duration(20),event -> {
            gulicky.forEach(Gulicka::update);
            for (int i = 0; i < gulicky.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (gulicky.get(i).collidesWith(gulicky.get(j))) {
                        handleBallCollision(gulicky.get(i),gulicky.get(j));
                    }
                }
            }
        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

        stage.setTitle("Delo");
        stage.setScene(new Scene(new Group(canvas)));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    synchronized void handleBallCollision(Gulicka b1, Gulicka b2) {
        b1.move(b2);

        double oringinalEnergy = b1.getVectorSize() + b2.getVectorSize();

        Point2D orig1 = new Point2D(b1.getCenterX(), b1.getCenterY());
        Point2D orig2 = new Point2D(b2.getCenterX(), b2.getCenterY());
        Point2D origd1 = new Point2D(b1.dx, b1.dy);
        Point2D origd2 = new Point2D(b2.dx, b2.dy);

        Point2D v1 = b1.handleCollision(b2);
        Point2D v2 = b2.handleCollision(b1);


        b1.dx += v2.getX();
        b1.dy += v2.getY();

        b2.dx += v1.getX();
        b2.dy += v1.getY();



        double newEnergy = b1.getVectorSize() + b2.getVectorSize();

        Point2D nove1 = new Point2D(b1.getCenterX(), b1.getCenterY());
        Point2D nove2 = new Point2D(b2.getCenterX(), b2.getCenterY());
        Point2D noved1 = new Point2D(b1.dx, b1.dy);
        Point2D noved2 = new Point2D(b2.dx, b2.dy);

        if (Math.abs(oringinalEnergy - newEnergy) > 0.1) {
            System.out.println();
            System.out.println("povodne xy g1: " + orig1);
            System.out.println("povodne dxy g1: " + origd1);
            System.out.println("nove xy g1: " + nove1);
            System.out.println("nove dxy g1: " + noved1);

            System.out.println("povodne xy g2: " + orig2);
            System.out.println("povodne dxy g2: " + origd2);
            System.out.println("nove xy g2: " + nove2);
            System.out.println("nove dxy g2: " + noved2);
            System.out.println();
        }

    }

    class BallPane extends Pane {
        public BallPane() {
            super();
            setPrefSize(WIDTH,HEIGHT);
            setMaxHeight(HEIGHT);
        }
    }

    class Delo extends Rectangle {
        public double lastMx = WIDTH/2;
        public double lastMy = 0;

        Rotate r;

        Delo() {
            super(pivotX-canonOffset,pivotY-canonWidth/2,canonLength,canonWidth);
            setFill(Color.GRAY);

            r = new Rotate(getCanonAngle(),pivotX,pivotY);
            getTransforms().add(r);

            canvas.getChildren().add(this);
        }

        void update(double mx, double my) {
            lastMx = mx;
            lastMy = my;
            r.setAngle(getCanonAngle());
        }

        public double getCanonAngle() {
            return Math.toDegrees(Math.atan2(lastMy-pivotY, lastMx-pivotX));
        }
    }

    class Gulicka extends Circle {
        double dx,dy;

        public Gulicka(double x, double y, double dx, double dy) {
            super(x,y,
                BALL_RADIUS,
                Color.color(
                    rnd.nextDouble(),
                    rnd.nextDouble(),
                    rnd.nextDouble()
                )
            );
            this.dx = dx;
            this.dy = dy;

            canvas.getChildren().add(this);
            delo.toFront();
        }

        Gulicka() {
            this(pivotX,pivotY,0,0);
            launch();
        }

        void launch() {
            double x = pivotX - canonOffset + canonLength - BALL_RADIUS;

            Affine a = new Affine();
            a.appendRotation(delo.getCanonAngle(),pivotX,pivotY);
            Point2D p = a.transform(x,pivotY);

            setCenterX(p.getX());
            setCenterY(p.getY());


            dx = (delo.lastMx - pivotX) / 10;
            dy = (delo.lastMy - pivotY) / 10;
        }

        void update() {
            setCenterX(getCenterX() + dx);
            setCenterY(getCenterY() + dy);

            dx = dx * WIND_RESISTANCE;
            dy = (dy - GRAVITY_ACCELERATION) * WIND_RESISTANCE;

            if (getCenterX()-BALL_RADIUS <= 0) { //je prilis nalavo
                setCenterX(BALL_RADIUS);
            }
            if (getCenterX()+BALL_RADIUS>=WIDTH) { //je prilis napravo
                setCenterX(WIDTH-BALL_RADIUS);
            }
            if (getCenterY()-BALL_RADIUS <= 0) { //prilis hore
                setCenterY(BALL_RADIUS);
            }
            if (getCenterY()>=HEIGHT) { //prilis dole
                setCenterY(HEIGHT);
            }


            if ((getCenterX()-BALL_RADIUS <= 0 && dx < 0) || (getCenterX()+BALL_RADIUS>=WIDTH && dx > 0) ) {
                //odraz od steny
                dx = -dx;
            }
            if ((getCenterY()-BALL_RADIUS <= 0 && dy < 0) || (getCenterY()>=HEIGHT && dy > 0)) {
                dy = -dy;
            }
            if ((getCenterY()>=HEIGHT && Math.abs(dy) < 10))
                dy *= 0.6;
            if ((getCenterY()>=HEIGHT && Math.abs(dy) < 1))
                dy = 0;
        }

        public void move(Gulicka other) {
            double rozdielX = getCenterX()-other.getCenterX();
            double rozdielY = getCenterY()-other.getCenterY();

            double d = Math.sqrt(Math.pow((rozdielX),2)+Math.pow(rozdielY,2));

            double pomerDX = d/rozdielX;
            double pomerXY = rozdielX/rozdielY;

            double novyDX = ((2*BALL_RADIUS)-d)/pomerDX;
            double novyDY = novyDX/pomerXY;

            setCenterX(getCenterX()+novyDX);
            setCenterY(getCenterY()+novyDY);
        }

        public boolean collidesWith(Gulicka other) {
            double dx = getCenterX()-other.getCenterX();
            double dy = getCenterY()-other.getCenterY();

            double d = Math.sqrt(Math.pow((dx),2)+Math.pow(dy,2));

            if (d > 2*BALL_RADIUS)
                return false; //nepretinaju sa



            if (getCenterY() > other.getCenterY()) { //tato je nizsia ako druha
                if (this.dy < other.dy) {
                    //ak toto ide menej dole ako to druhe
                    //teda druhe sa blizi k tomuto
                    return true;
                }
            } else { //tato je vyssia ako druha
                if (this.dy >= other.dy) {
                    return true;
                }
            }
            if (getCenterX() > other.getCenterX()) { //tato je viac vpravo
                if (this.dx < other.dx) {
                    return true;
                }
            } else {
                if (this.dx >= other.dx) {
                    return true;
                }
            }
            return false;
        }

        public double getVectorAngle() {
            return (Math.atan2(dy-getCenterY(), dx-getCenterX())+2*Math.PI) % (Math.PI/2);
        }

        public double getVectorSize() {
            return Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
        }

        public Point2D handleCollision(Gulicka other) {
            //sprav ciaru medzi dvoma
            double rozdielX = getCenterX()-other.getCenterX();
            double rozdielY = getCenterY()-other.getCenterY();

            double d = Math.sqrt(Math.pow((rozdielX),2)+Math.pow(rozdielY,2));

            //najdi uhol medzi tou ciarom a rychlostnym vektorom
            double uholCiary = (Math.atan2(rozdielY, rozdielX)+2*Math.PI) % (Math.PI/2);

            double phi = ((uholCiary-getVectorAngle())+2*Math.PI) % (Math.PI/2);

            //nova rychlost kolma na stret: sin(fi)*vektor

            //su vymenene
            double along = Math.sin(phi)*getVectorSize();

            double perp = Math.sin((Math.PI/2)-phi)*getVectorSize();

            double alongDY = Math.sin(uholCiary)*along;
            double alongDX = Math.sin((Math.PI/2)-uholCiary)*along;

            double perpDX = dx - alongDX;
            double perpDY = dy - alongDY;

            System.out.println(perp + " == " + Math.sqrt(Math.pow(perpDX,2)+Math.pow(perpDY,2)));


            /*
            if (dx == (perpDX+alongDX)) {
                //System.out.println(true);
            }
            else if (dx - (perpDX+alongDX) > 0.0001) {
                System.out.println("dx="+dx+", perp+along="+(perpDX+alongDX));
            }
            if (dy == (perpDY+alongDY)) {
                //System.out.println(true);
            }
            else if (dy - (perpDY+alongDY) > 0.0001) {
                System.out.println("dy="+dy+", perp+along="+(perpDY+alongDY));
            }

             */

            double originalSpeed = getVectorSize();
            double newSpeed = Math.sqrt(Math.pow(perpDX+alongDX,2)+Math.pow(perpDY+alongDY,2));
            if (Math.abs(originalSpeed-newSpeed) > 0.001) {
                System.err.println(originalSpeed + " " + newSpeed);
            }


            dx = perpDX;
            dy = perpDY;



/*
            double pomerDX = d/rozdielX;
            double pomerXY = rozdielX/rozdielY;

            double novyDX = ((2*BALL_RADIUS)-d)/pomerDX;
            double novyDY = novyDX/pomerXY;

            setCenterX(getCenterX()+novyDX);
            setCenterY(getCenterY()+novyDY);

 */



            return new Point2D(alongDX,alongDY);
        }
    }
}
