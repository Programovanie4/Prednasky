package II_Camera;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("Duplicates")
public class DepthBuffer extends Application {
    private Group group = new Group();
    private Camera camera = new PerspectiveCamera(true);
    private Scene scene = new Scene(group, 800, 600, true);
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        Box b1 = new Box(15, 15, 15);
        Box b2 = new Box(10, 10, 10);
        b1.setMaterial(new PhongMaterial(Color.RED));
        b2.setMaterial(new PhongMaterial(Color.BLUE));
        b1.setTranslateZ(70);
        b2.setTranslateZ(50);
        group.getChildren().addAll(b1, b2);
        groupRotation();

        scene.setCamera(camera);
        scene.setFill(Color.GRAY);
        primaryStage.setTitle("DepthBuffer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void groupRotation() {
        group.setRotationAxis(Rotate.Y_AXIS);
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(group.rotateProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(15),
                        new KeyValue(group.rotateProperty(), 360)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}