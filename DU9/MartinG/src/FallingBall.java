import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


/**
 * 4. Guľka skákalka
 */
public class FallingBall extends Application {
    private boolean running = true;
    Random random = new Random();
    static int width = 450;
    static int height = 450;
    static Ball ball = new Ball(250, 50, 10, Color.BLACK);

    @Override
    public void start(Stage primaryStage) {
        FallingBallPane fallingBallPane = new FallingBallPane();

        // moving pendulums
        new Thread(() -> {
            while (running) {
                ball.update();
                Platform.runLater(fallingBallPane::paint);
                try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace();}
            }
        }).start();

        Scene scene = new Scene(fallingBallPane, width, height);
        primaryStage.setTitle("Guľka skákalka");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        running = false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class FallingBallPane extends Pane {
    protected void paint() {
        // System.out.println("painting pendulums");
        getChildren().clear();

        Ball ball = FallingBall.ball;

        Circle ballCircle = new Circle(ball.getX(), ball.getY(), ball.getBallRadius());
        ballCircle.setStroke(Color.BLACK);
        ballCircle.setFill(Color.BLACK);
        getChildren().add(ballCircle);
    }
}

class Ball {
    private double x, y;
    private double ballRadius;
    private Color color;

    private double dx = 0.7;
    private double y0;
    private double v0;
    private double timeY;
    private final double GRAVITY = -9.81;
    private final double BOUNCE = .7;

    public Ball(double x, double y, double ballRadius, Color color) {
        this.x = x;
        this.color = color;
        this.ballRadius = ballRadius;
        timeY = v0 / GRAVITY + Math.sqrt((Math.pow(v0, 2) - GRAVITY * (FallingBall.height - y) * 2) / Math.pow(GRAVITY, 2));
        this.v0 = v0 - GRAVITY * timeY;
        y0 = y;
    }

    public void update() {
        if (v0 < 0.1) {
            y = FallingBall.height - ballRadius;
            if (dx < 0) dx += 0.01;
            System.out.println(dx);
        }
        else y = y0 - v0 * timeY - 0.5 * GRAVITY * Math.pow(timeY, 2);
        timeY += 0.1;

        x += dx;

        if(y + ballRadius > FallingBall.height) {  // hitting the ground
            System.out.println("bounce");
            timeY = 0;
            v0 *= BOUNCE;
            y0 = FallingBall.height - ballRadius;
        }

        // hitting edges
        if (x + ballRadius >= FallingBall.width) dx *= -1;
        if (x - ballRadius <= 0) dx *= -1;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getBallRadius() {
        return ballRadius;
    }

    public void setBallRadius(double ballRadius) {
        this.ballRadius = ballRadius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
