import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Vrabciaci extends Application {
    final static int N = 500;      // pocet vrabciakov, viac to moc nezvlada
    final static Random rnd = new Random();
    final static int width = 1920;
    final static int height = 1080;
    Vrabciak[] vrabiaci;
    Playground pg;

    @Override
    public void start(Stage primaryStage) {
        pg = new Playground();
        pg.setFocusTraversable(true);  // kvoli KeyPressed eventu
        pg.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            // co robit, ak KeyPressed
        });
        pg.setOnMouseClicked(event -> {
            System.out.println(event.getX() + ", " + event.getY());
            // co robit, ak KeyPressed
        });
        Scene scene = new Scene(new Pane(pg));      // vytvor scenu
        primaryStage.setTitle("Vrabciaci"); // pomenuj okno aplikacie,
        primaryStage.setScene(scene);       // vloz scenu do hlavneho okna, na javisko
        primaryStage.show();                // zobraz javisko
    }
    class Playground extends Canvas {
        public Playground() {
            setWidth(width);
            setHeight(height);
            vrabiaci = new Vrabciak[N];
            for (int i = 0; i<N; i++) {
                int size = 3+rnd.nextInt(3);
                vrabiaci[i] = new Vrabciak(
                        rnd.nextInt(width), rnd.nextInt(height),
                        rnd.nextInt(360),
                        rnd.nextInt(10)+5,
                        size,
                        new Color(
                                (30.0+rnd.nextInt(200))/255,
                                (30.0+rnd.nextInt(200))/255,
                                (30.0+rnd.nextInt(200))/255,
                                1));
            }
            Timeline tl = new Timeline( new KeyFrame(Duration.millis(100),
                    event -> {
                        update();
                        if (pg != null)
                            pg.paint();
                    }
            ));
            tl.setCycleCount(Timeline.INDEFINITE);
            tl.play();
        }
        public void update() {
            for(Vrabciak v : vrabiaci) {

                List<Vrabciak> okolitiVrabciaci = najblizsi(v);
                //chaos(v, okolitiVrabciaci);
                // toto doprogramuj a odkomentuj
                separate(v, okolitiVrabciaci);
                align(v, okolitiVrabciaci);
                cohere(v, okolitiVrabciaci);

                v.update();

                wrapWindow(v);
            }
        }
        private double distance(Vrabciak v1,Vrabciak v2) {
                return Math.sqrt((v1.x-v2.x)*(v1.x-v2.x) + (v1.y-v2.y)*(v1.y-v2.y));
        }
        final int kolkoNajblizsich = 20;
        private List<Vrabciak> najblizsi(Vrabciak v) {
            List<Vrabciak> al = Arrays.asList(vrabiaci);
            return al.stream()
                    .sorted(Comparator.comparingDouble(v2 -> distance(v, v2)))
                    .filter(v2 -> v2 != v)
                    .limit(kolkoNajblizsich)
                    .collect(Collectors.toList());
        }
        /**
         uvazuje len vrabcakov blizsich ako horizont od vrabcaka
         pohne sa smerom opacnym, ako je sucet vektorov k tymto vrabciakom
         rychlostou umernou tomuto suctu vektorov
         */
        final int horizont = 20;
        private void separate(Vrabciak vrabciak, List<Vrabciak> okolitiVrabcaci) {
            double x = 0;
            double y = 0;
            int count = 0;
            for (Vrabciak v : okolitiVrabcaci) {
                if (distance(v,vrabciak) < horizont) {
                    count++;
                    x += v.x;
                    y += v.y;
                }
            }
            if (count == 0)
                return;
            x /= count;
            y /= count;


            vrabciak.away(x,y);
            //vrabciak.update();
        }

        /**
         smeruje k priemernemu susedovi
         urobi priemer vektorov rychlosti najblizsich vrabciakov
         o tolko sa zmeni smerovy vektor aj poloha vrabciaka
         */
        private void align(Vrabciak vrabciak, List<Vrabciak> okolitiVrabcaci) {
            double heading = 0;
            for (Vrabciak v : okolitiVrabcaci) {
                heading += v.heading;
            }
            heading /= okolitiVrabcaci.size();

            vrabciak.heading = vrabciak.normalizedAngle(heading);
        }

        final int coherenceFactor = 8;

        private void cohere(Vrabciak vrabciak, List<Vrabciak> okolitiVrabcaci) {
            double x = 0;
            double y = 0;
            for (Vrabciak v : okolitiVrabcaci) {
                x += v.x;
                y += v.y;
            }
            x /= okolitiVrabcaci.size();
            y /= okolitiVrabcaci.size();


            vrabciak.towards(x,y);
        }

        private void chaos(Vrabciak vrabcak, List<Vrabciak> okolitiVrabcaci) {
            double x = 0, y = 0;
            for (Vrabciak v : okolitiVrabcaci) {
                x += v.x-vrabcak.x;
                y += v.y-vrabcak.y;
            }
            vrabcak.x += rnd.nextInt(2*vrabcak.size+1) - vrabcak.size;
            vrabcak.y += rnd.nextInt(2*vrabcak.size+1) - vrabcak.size;
        }

        private void wrapWindow(Vrabciak v) {
            if (v.x < 0) {
                v.x += width;
            } else if (v.x > width) {
                v.x -= width;
                v.x *=-1;
            }
            if (v.y < 0) {
                v.y += height;
            } else if (v.y > height) {
                v.y -= height;
                v.y *= -1;
            }
        }

        public void paint() {
            GraphicsContext gc = getGraphicsContext2D();
            gc.setFill(new Color(1,1,1,0.3));
            gc.fillRect(0,0,width, height);
            for(Vrabciak v : vrabiaci) {
                gc.setFill(v.color);
                gc.fillOval(v.x - (v.size/2), v.y - (v.size/2), v.size, v.size);
                gc.setStroke(v.color);
                gc.setLineWidth(v.size/2);
                gc.strokeLine(v.x+v.getDx(), v.y+v.getDy(), v.x, v.y);
            }
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
class Vrabciak {
    int x;
    int y;
    //double dx;
    //double dy;
    int size;
    Color color;

    double heading,speed;

    public Vrabciak(int x, int y, double heading, double speed, int size, Color color) {
        this.x = x;
        this.y = y;
        //this.dx = dx;
        //this.dy = dy;
        this.size = size;
        this.color = color;
        //correctVelocity();
        this.heading = heading;
        this.speed = speed;
    }

    void update() {
        x += getDx();
        y += getDy();
    }

    public double getDx() {
        return speed * Math.cos(Math.toRadians(heading));
    }

    public double getDy() {
        return speed * Math.sin(Math.toRadians(heading));
    }

    /*
    void correctVelocity() {
        heading = getAngle(x+dx,y+dy);
        speed = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2))+1;
    }

     */

    void setVector(double dx, double dy) {
        heading = getAngle(x+dx,y+dy);
        speed = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
    }

    double normalizedAngle(double angle) {
        return (360 + (angle % 360)) % 360;
    }

    void towards(double x, double y) {
        heading = normalizedAngle((getAngle(x,y) + heading) / 2);
    }

    void away(double x, double y) {
        heading = normalizedAngle((getAngle(x,y)-180 + heading) / 2);
        update();
    }

    void right(double angle) {
        heading = normalizedAngle(heading + angle);
    }

    double getAngle(double x, double y) {
        return normalizedAngle(Math.toDegrees(Math.atan2(y-this.y, x-this.x)));
    }
}
