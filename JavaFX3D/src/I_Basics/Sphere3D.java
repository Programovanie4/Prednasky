package I_Basics;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Sphere3D extends Application {

    @Override
    public void start(Stage primaryStage) {
        Sphere sphere = new Sphere(50);

        Group group = new Group();
        group.getChildren().add(sphere);

        Scene scene = new Scene(group, 800, 600);

        // ak by sme kameru nevytvorili, vytvorí sa automaticky
        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
	public static void main(String[] args) {
		launch(args);
	}

}