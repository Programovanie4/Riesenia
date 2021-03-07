import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class View extends Canvas {
    private Model model;
    private Character gameMatrix[][];
    private double PlaygroundWidth;
    private double PlaygroundHeight;
    private double cellSize;

    public void setCellSize(double _cellSize) {
        cellSize = _cellSize;
        init();
    }

    private void setSize() {
        setWidth(cellSize * model.getPlaygroundWidth());
        setHeight(cellSize * model.getPlaygroundHeight());
    }

    private void init() {
        gameMatrix = model.getGameMatrix();
        setSize();
        paint();
    }


    public View(Label timeLabel) {

        model = new Model(10,timeLabel);
        model.manageTime(100, timeLabel, this);

        setOnMouseClicked(e -> {
            int column = getCol(e.getX());
            int row = getRow(e.getY());
            try {
                model.clicked(row, column);
            } catch (WinException win) {
                gameMatrix = model.getGameMatrix();
                System.out.println("You won! Lvl up.");
                setSize();
            } catch(LooseException loose) {
                System.out.println("You loose restart");
                gameMatrix = model.getGameMatrix();
                setSize();
            }
            paint();
        });
        init();
    }

    public void refresh(Model _model) {
        model = _model;
        init();
    }

    public void refresh() {
        gameMatrix = model.getGameMatrix();
        paint();
    }

    public Model getModel() {
        return model;
    }

    private void paint() {
        GraphicsContext gc = getGraphicsContext2D();

        for(int i=0; i<model.getPlaygroundHeight();i++) {
            for(int j=0; j<model.getPlaygroundWidth();j++) {
                switch (gameMatrix[i][j]) {
                    case 'A':
                        paintACell(i,j);
                        break;
                    case 'B':
                        paintBCell(i,j);
                        break;
                    case 'C':
                        paintCCell(i,j);
                        break;
                    case 'D':
                        paintDCell(i,j);
                        break;
                    default:
                        paintEmptyCell(i,j);
                }
            }
        }
    }

    private int getCol(double x) {
        return (int) (x / cellSize) ;
    }

    private int getRow(double y) {
        return (int) (y / cellSize);
    }

    private double getPixelX(int column) {
        return column * cellSize;
    }

    private double getPixelY(int row) {
        return row * cellSize;
    }


    private void paintACell(int i, int j) {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFill(Color.GREEN);
        paintCell(graphicsContext, i, j);
    }

    private void paintBCell(int i, int j) {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFill(Color.RED);
        paintCell(graphicsContext, i, j);
    }

    private void paintCCell(int i, int j) {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFill(Color.YELLOW);
        paintCell(graphicsContext, i, j);
    }

    private void paintDCell(int i, int j) {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFill(Color.BLUE);
        paintCell(graphicsContext, i, j);
    }


    private void paintEmptyCell(int i, int j) {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFill(Color.WHITE);
        paintCell(graphicsContext, i, j);
    }

    private void paintCell(GraphicsContext gc, int i, int j) {
        double x = getPixelX(i);
        double y = getPixelY(j);
        gc.fillRect(y,x, cellSize, cellSize);
    }

}
