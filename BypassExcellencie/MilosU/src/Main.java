import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hra");
        BorderPane pane = new BorderPane();
        Pane settings = new FlowPane();
        Label timer = new Label("Zaciname");
        View view = new View(timer);
        Button save = new Button("Save");
        Button load = new Button("Load");
        Button undo = new Button("undo");
        Model model = view.getModel();
        save.setOnAction(e -> {model.save();});
        load.setOnAction(e -> {
            Model newModel = Model.load();
            view.refresh(newModel);
        });
        undo.setOnAction(e -> {
            model.undo();
            view.refresh();
        });

        pane.setTop(settings);
        pane.setCenter(view);
        settings.getChildren().add(timer);
        settings.getChildren().add(save);
        settings.getChildren().add(load);
        settings.getChildren().add(undo);

        stage.setScene(new Scene(pane, 500,500));
        stage.setMinWidth(model.getPlaygroundWidth() * 5);
        stage.setMinHeight(model.getPlaygroundHeight() * 5);
        stage.widthProperty().addListener(e -> {
            view.setCellSize(getMin(stage, model) - 1);
        });
        stage.heightProperty().addListener(e -> {
            view.setCellSize(getMin(stage, model) - 1);
        });
        stage.show();
    }

    private double getMin(Stage stage, Model model) {
        double dueToWidth = stage.getWidth() / model.getPlaygroundWidth();
        double dueToHeight = stage.getHeight() / model.getPlaygroundHeight();
        return Math.min(dueToHeight, dueToWidth);
    }


    public static void main(String[] args) {
        launch(args);
    }
}