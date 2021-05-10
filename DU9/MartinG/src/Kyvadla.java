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
 * 2. Kyvadielka
 */
public class Kyvadla extends Application {
	private boolean running = true;
	Random random = new Random();
	private static Kyvadlo red = new Kyvadlo(Math.PI / 2, 100, 10, Color.RED);
	private static Kyvadlo blue = new Kyvadlo(-Math.PI / 4, 150, 15, Color.BLUE);
	private static Kyvadlo green = new Kyvadlo(-Math.PI / 2, 200, 13, Color.GREEN);
	private static Kyvadlo yellow = new Kyvadlo(Math.PI / 4, 50, 19,Color.YELLOW);
	static ArrayList<Kyvadlo> pendulums = new ArrayList<>(Arrays.asList(red, blue, green, yellow));
	
	@Override
	public void start(Stage primaryStage) {
		KyvadloPane pendulumsPane = new KyvadloPane();

		// moving pendulums
		new Thread(() -> {
			while (running) {
				for (Kyvadlo p: pendulums) p.update();
				Platform.runLater(pendulumsPane::paint);
				try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace();}
			}
		}).start();

		// adding pendulums
		new Thread(() -> {
			while (running) {
				try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace();}
				System.out.println("Adding new pendulum");
				pendulums.add(
						new Kyvadlo(
								random.nextDouble() * (Math.PI / 2 + Math.PI / 2) - Math.PI / 2,
								random.nextInt(101) + 100,
								random.nextDouble() * 21 + 10,
								Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
						)
				);
			}
		}).start();

		Scene scene = new Scene(pendulumsPane, 450, 450);  // vytvor scenu

		primaryStage.setTitle("Kyvadlo"); 		// pomenuj okno aplikacie, javisko
		primaryStage.setScene(scene); 			// vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); 					// zobraz javisko		
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

class KyvadloPane extends Pane {
	protected void paint() {
		// System.out.println("painting pendulums");
		getChildren().clear();

		double nailX = getWidth() / 2, nailY = getHeight() / 4;
		Circle nail = new Circle(nailX, nailY, 7);
		nail.setStroke(Color.BLACK);
		nail.setFill(Color.BLACK);
		getChildren().add(nail);

		for (Kyvadlo p: Kyvadla.pendulums) {
			// draw pendulum by its parameters
			double ballX = nailX + Math.sin(p.getAngle()) * p.getLineLength();
			double ballY = nailY + Math.cos(p.getAngle()) * p.getLineLength();

			Circle ball = new Circle(ballX, ballY, p.getBallRadius());
			ball.setStroke(Color.BLACK);
			ball.setFill(p.getColor());

			Line line = new Line(nailX, nailY, ballX, ballY);
			line.setStroke(Color.BLACK);

			this.getChildren().addAll(List.of(line, ball));
		}
	}
}

class Kyvadlo {
    private double angle;
    private double ballRadius;
    private int lineLength;
    private Color color;
    private double velocity = 0;
    private double acceleration = 0;
    private double damping = 0.995;
    private double gravity = 0.25;

    public Kyvadlo(double startAngle, int lineLength, double ballRadius, Color color) {
		super();
		this.angle = startAngle;
		this.lineLength = lineLength;
		this.color = color;
		this.ballRadius = ballRadius;
	}

	public void update() {
    	acceleration = (-0.75 * gravity / lineLength) * Math.sin(angle);
		velocity += acceleration;
		velocity *= damping;
		angle += velocity;
    }

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getBallRadius() {
		return ballRadius;
	}

	public void setBallRadius(double ballRadius) {
		this.ballRadius = ballRadius;
	}

	public int getLineLength() {
		return lineLength;
	}

	public void setLineLength(int lineLength) {
		this.lineLength = lineLength;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
