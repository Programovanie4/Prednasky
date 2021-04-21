package V_ModelsGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterface2D extends Application {
    // 2D UI Panel
    private HBox panel = new HBox();
    // 3D Scéna
    Group group = new Group(new Box(20,20,20));
    private SubScene scene = new SubScene(group, 800, 600, true, SceneAntialiasing.BALANCED);
    // Layout aplikácie
    private BorderPane layout = new BorderPane();
    // Root scéna
    private Scene root = new Scene(layout, 800, 600);
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        preparePanel();
        layout.setCenter(scene); // hore bude 3D scéna
        layout.setBottom(panel); // dole 2D UI Panel

        Camera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-100);
        scene.setCamera(camera);
        scene.setFill(Color.NAVAJOWHITE);

        scene.widthProperty().bind(root.widthProperty()); //aby sa 3D scéna prispôsobovala veľkosti okna
        scene.heightProperty().bind(root.heightProperty().subtract(panel.getMinHeight()));

        primaryStage.setScene(root);
        primaryStage.show();
    }

    private void preparePanel() {
        panel.setMinHeight(80);
        panel.setStyle("-fx-background-color: LightBlue;");
        panel.setAlignment(Pos.CENTER);
        panel.setSpacing(64);
        panel.getChildren().add(new Text("UI Text"));
    }

}

