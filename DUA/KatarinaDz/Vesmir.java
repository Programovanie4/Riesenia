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

public class Vesmir extends Application {
    final static int N = 10;      // pocet castic
    final static Random rnd = new Random();
    final static int width = 1200;
    final static int height = 900;
    double GK = 6.67;
    double odpor = 0.7;
    int hranicnaRychlostOdporu = 15;
    int maxRychlost = 40;
    int maxZrychlenie = 5;
    int minVzdialenost = 3;
    
    int kolkoNajblizsich = 5;
    int horizont = 350;
    Castica[] castice;
    Playground pg;
/*
    double GK = 6.67;
    int odpor = 5;
    int hranicnaRychlostOdporu = 50;
    int maxRychlost = 50;
    int maxZrychlenie = 5;
    int minVzdialenost = 3;
    
    int kolkoNajblizsich = 5;
    int horizont = 350;
*/
    @Override
    public void start(Stage primaryStage) {
        pg = new Playground();
        pg.setFocusTraversable(true);  // kvoli KeyPressed eventu
        pg.setOnKeyPressed(event -> {    //nema vstup
            //KeyCode c = event.getCode();
            //System.out.println("Pressed " + event.getCode().toString());
        });
        pg.setOnMouseClicked(event -> {    //nema vstup
            //System.out.println(event.getX() + ", " + event.getY());
            // co robit, ak KeyPressed
        });
        Scene scene = new Scene(new Pane(pg));      // vytvor scenu
        primaryStage.setTitle("Vesmir"); // pomenuj okno aplikacie,
        primaryStage.setScene(scene);       // vloz scenu do hlavneho okna, na javisko
        primaryStage.show();                // zobraz javisko
    }
    class Playground extends Canvas {
        public Playground() {
            setWidth(width);
            setHeight(height);
            castice = new Castica[N];
            for (int i = 0; i<N; i++) {
            	int size = 5+rnd.nextInt(60);
                castice[i] = new Castica(i,
                		 rnd.nextInt(width), rnd.nextInt(height), //poloha
                		 0, 0,          //rychlost
                        0, 0,    //zrychlenie
                        size,                                       //hmotnost/velkost
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
            for(Castica c : castice) {
                List<Castica> okoliteCastice = najblizsie(c);
                pohybCastice(c, okoliteCastice);
                wrapWindow(c);
            }
        }
        private double distance(Castica v1,Castica v2) {
                return Math.sqrt((v1.x-v2.x)*(v1.x-v2.x) + (v1.y-v2.y)*(v1.y-v2.y));
        }

        private List<Castica> najblizsie(Castica v) {
            List<Castica> al = Arrays.asList(castice);
            return al.stream().filter(v2 -> minVzdialenost < distance(v, v2) && distance(v, v2)<horizont).sorted(Comparator.comparingDouble(v2 -> distance(v, v2))).limit(kolkoNajblizsich).collect(Collectors.toList());
        }
        
        private void pohybCastice(Castica castica, List<Castica> okoliteCastice) {
        	double silaX = 0;
        	double silaY = 0;
        	
        	for(Castica c: okoliteCastice){
        		double d = distance(castica, c);
        		silaX = silaX + (c.x-castica.x)*GK*castica.m*c.m/d/d/d;    //fyzika pritahovania - x zlozka - sucet sil
        		silaY = silaY + (c.y-castica.y)*GK*castica.m*c.m/d/d/d;    //fyzika pritahovania - y zlozka - sucet sil
        	}
        	silaX = silaX + castica.m * castica.ax;   //zohladnenie zotrvacnej sily castice
        	silaY = silaY + castica.m * castica.ay;   //zohladnenie zotrvacnej sily castice
        	double daX = silaX/castica.m;    //prepocet sily na zrychlenie
        	double daY = silaY/castica.m;
        	castica.ax = (int)daX;
        	castica.ay = (int)daY;

        	//obmedzenie max zrychlenia
        	if(Math.abs(castica.ax) > maxZrychlenie){
        		if(castica.id == 1){
        			//System.out.println("MAXXXXXXXXXXXX AAA x " + castica.ax);
        		}
        		castica.ax = maxZrychlenie * castica.ax/Math.abs(castica.ax);
        	}
        	if(Math.abs(castica.ay) > maxZrychlenie){
        		if(castica.id == 1){
        			//System.out.println("MAXXXXXXXXXXXXX AAA y " + castica.ay);
        		}
        		castica.ay = maxZrychlenie * castica.ay/Math.abs(castica.ay);
        	}
        	if(castica.id == 1){
    			System.out.println("rychlost A x " + castica.dx + " " + castica.ax);
    		}

            //vypocet rychlosti
        	castica.dx = castica.dx + castica.ax;
            castica.dy = castica.dy + castica.ay;
        	if(castica.id == 1){
    			//System.out.println("rychlost B x " + castica.dx + " " + castica.ax);
    		}

        	//zapocitanie odporu
        	if(Math.abs(castica.dx) > hranicnaRychlostOdporu){
        		int sig = castica.dx/Math.abs(castica.dx);
        		castica.dx = (int)(sig*hranicnaRychlostOdporu + odpor*(castica.dx - sig*hranicnaRychlostOdporu));
        		if(castica.id == 1){
        			//System.out.println("rychlost po odpore x " + castica.dx + " " + castica.ax);
        		}
        	}
        	if(Math.abs(castica.dy) > hranicnaRychlostOdporu){
        		int sig = castica.dy/Math.abs(castica.dy);
        		castica.dy = (int)(sig*hranicnaRychlostOdporu + odpor*(castica.dy - sig*hranicnaRychlostOdporu));
        	}

        	//obmedzenie rychlosti
        	if(Math.abs(castica.dx) > maxRychlost){
        		if(castica.id == 1){
        			//System.out.println("MAXXXXXXXXXXXXXXXX x " + castica.dx);
        		}
        		castica.dx = maxRychlost * castica.dx/Math.abs(castica.dx);
        	}
        	if(Math.abs(castica.dy) > maxRychlost){
        		if(castica.id == 1){
        			//System.out.println("MAXXXXXXXXXXXXXXXX y " + castica.dy);
        		}
        		castica.dy = maxRychlost * castica.dy/Math.abs(castica.dy);
        	}

        	//vypocet polohy
        	castica.x = castica.x + castica.dx;
        	castica.y = castica.y + castica.dy;
        	if(castica.id == 1){
        		//System.out.println(castica.toString() + "  daXY " + daX + " " + daY);
        	}
        	
        }

        private void wrapWindow(Castica v) {
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
            for(Castica v : castice) {
                gc.setFill(v.color);
                gc.fillOval(v.x - (v.size/2), v.y - (v.size/2), v.size, v.size);
            }
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
class Castica {
    int id, x, y, dx, dy, size, ax, ay;
    Color color;
    double m;

    public Castica(int id, int x, int y, int dx, int dy, int ax, int ay, int size, Color color) {
        this.id = id; this.x = x;  this.y = y;   this.dx = dx;    this.dy = dy;
        this.size = size;    this.ax = ax;  this.ay = ay; this.color = color;
        this.m = 10.*3.14*size*size*size;
    }
    public String toString(){
    	String res = "id " + id + " pos " + x + " " + y + " rych " + dx + " " + dy + " zrych " + ax + " " + ay;
    	return res;
    }
}
