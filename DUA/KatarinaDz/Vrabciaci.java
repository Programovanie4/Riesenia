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
            System.out.println("Najblizsie " + pg.kolkoNajblizsich + "horizont " + pg.horizont + "   - " + event.getCode().toString());
            KeyCode c = event.getCode();
            // TODO - ak sa stlacilo Num+
            if(c.equals(KeyCode.ADD)){
            	pg.kolkoNajblizsich = pg.kolkoNajblizsich + 1;
            }
            if(c.equals(KeyCode.SUBTRACT)){
            	if(pg.kolkoNajblizsich > 2){
            		pg.kolkoNajblizsich = pg.kolkoNajblizsich -1;
            	}
            }
             if(c.equals(KeyCode.MULTIPLY)){
            	 pg.horizont = pg.horizont + 1;
            }
            if(c.equals(KeyCode.DIVIDE)){
            	if(pg.horizont > 2){
            		pg.horizont = pg.horizont -1;
            	}
            }
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
                        rnd.nextInt(size),  rnd.nextInt(size),
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
                 chaos(v, okolitiVrabciaci);
                // toto doprogramuj a odkomentuj
                 separate(v, okolitiVrabciaci);
                 align(v, okolitiVrabciaci);
                 cohere(v, okolitiVrabciaci);
                wrapWindow(v);
            }
        }
        private double distance(Vrabciak v1,Vrabciak v2) {
                return Math.sqrt((v1.x-v2.x)*(v1.x-v2.x) + (v1.y-v2.y)*(v1.y-v2.y));
        }
        int kolkoNajblizsich = 30;
        private List<Vrabciak> najblizsi(Vrabciak v) {
            List<Vrabciak> al = Arrays.asList(vrabiaci);
            return al.stream().sorted(Comparator.comparingDouble(v2 -> distance(v, v2))).limit(kolkoNajblizsich).collect(Collectors.toList());
        }
        /**
         uvazuje len vrabcakov blizsich ako horizont od vrabcaka
         pohne sa smerom opacnym, ako je sucet vektorov k tymto vrabciakom
         rychlostou umernou tomuto suctu vektorov
         */
      
        int horizont = 50;
        double koefSeparate = 0.1;
        private void separate(Vrabciak vrabcak, List<Vrabciak> okolitiVrabcaci) {
        	int sucetx = 0;
        	int sucety = 0;
        	int pocet = 0;

        	for(Vrabciak v: okolitiVrabcaci){
        		if(distance(vrabcak, v) < horizont){
            		if(!(v.x == vrabcak.x && v.y == vrabcak.y)){
            			pocet ++;
            			sucetx = sucetx + (v.x-vrabcak.x);
            			sucety = sucety + (v.y-vrabcak.y);
            		}
        		}
        	}
//        	if(pocet > 0){
//        		sucetx = sucetx / pocet;
//        		sucety = sucety / pocet;
//        	}
        	double kk = 0. - koefSeparate;
        	double ddx  = kk * sucetx ;
        	double ddy  = kk * sucety ;
            vrabcak.dx = (int) ddx;
            vrabcak.dy = (int) ddy;
            vrabcak.x += vrabcak.dx;
            vrabcak.y += vrabcak.dy;

        	
        }

        /**
         urobi priemer vektorov rychlosti najblizsich vrabciakov
         o tolko sa zmeni smerovy vektor aj poloha vrabciaka
         */
        double koefAlign = 0.5;
        private void align(Vrabciak vrabcak, List<Vrabciak> okolitiVrabcaci) {
        	int sucetx = 0;
        	int sucety = 0;
        	int pocet = 0;
        	
        	for(Vrabciak v: okolitiVrabcaci){
        		if(!(v.x == vrabcak.x && v.y == vrabcak.y)){
	    			pocet ++;
	    			sucetx = sucetx + (v.dx);
	    			sucety = sucety + (v.dy);
        		}
        	}
        	if(pocet > 0){
        		sucetx = sucetx / pocet;
        		sucety = sucety / pocet;
        	}

        	double ddx  = koefAlign*sucetx;
        	double ddy  = koefAlign*sucety;
        	
            vrabcak.dx = vrabcak.dx + (int) ddx;
            vrabcak.dy = vrabcak.dy + (int) ddy;
            vrabcak.x += (int) ddx;
            vrabcak.y += (int) ddy;
        	
        }

        final int coherenceFactor = 8;
        
        //obr3. kohezia - pohyb smerom do stredu polohy okolitych najbly vrab.
        double koefCohere = 0.1;
        private void cohere(Vrabciak vrabcak, List<Vrabciak> okolitiVrabcaci) {
        	int sucetx = 0;
        	int sucety = 0;
        	int pocet = 0;
        	
        	for(Vrabciak v: okolitiVrabcaci){
        		if(!(v.x == vrabcak.x && v.y == vrabcak.y)){
        			pocet ++;
        			sucetx = sucetx + (v.x);
        			sucety = sucety + (v.y);
       		}
        	}
        	if(pocet > 0){
        		sucetx = sucetx / pocet;
        		sucety = sucety / pocet;
        	}
        	sucetx  = sucetx - vrabcak.x;
        	sucety  = sucety - vrabcak.y;
        	
        	int velkost = (int) Math.sqrt(sucetx*sucetx + sucety*sucety);
        	if(velkost == 0){
        		velkost = 1;
        	}
        	
        	double kk = koefCohere*velkost;
        //	double ddx  = kk * sucetx / velkost;
        //	double ddy  = kk * sucety / velkost;
        	double ddx  = koefCohere * sucetx;
        	double ddy  = koefCohere * sucety;
        	vrabcak.dx = (int) ddx;
            vrabcak.dy = (int) ddy;
            vrabcak.x += vrabcak.dx;
            vrabcak.y += vrabcak.dy;
            
        	
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
            gc.setFill(new Color(1,1,1,0.2));
            gc.fillRect(0,0,width, height);
            for(Vrabciak v : vrabiaci) {
                gc.setFill(v.color);
                gc.fillOval(v.x - (v.size/2), v.y - (v.size/2), v.size, v.size);
                gc.setStroke(v.color);
                gc.setLineWidth(v.size/2);
                gc.strokeLine(v.x-v.dx, v.y-v.dy, v.x, v.y);
            }
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
class Vrabciak {
    int x, y, dx, dy, size;
    Color color;

    public Vrabciak(int x, int y, int dx, int dy, int size, Color color) {
        this.x = x;  this.y = y;   this.dx = dx;    this.dy = dy;
        this.size = size;    this.color = color;
    }
}
