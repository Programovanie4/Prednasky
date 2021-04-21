package V_ModelsGUI;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class BasicViewer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group group = new Group();

        Box box = new Box(100, 20, 50);
        group.getChildren().add(box);

        Camera camera = new PerspectiveCamera(true);
        camera.setFarClip(5000);
        camera.setTranslateZ(-200);

        Scene scene = new Scene(group, 1280, 720, true);
        scene.setFill(Color.GRAY);
        scene.setCamera(camera);

        initMouseControl(group, scene);

        primaryStage.setTitle("ModelViewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double anchorX, anchorY, anchorAngleX, anchorAngleY;
    private void initMouseControl(Group group, Scene scene) {
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        group.getTransforms().addAll(xRotate, yRotate);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = xRotate.getAngle();
            anchorAngleY = yRotate.getAngle();
        });

        scene.setOnMouseDragged(event -> {
            xRotate.setAngle(anchorAngleX - (anchorY - event.getSceneY()));
            yRotate.setAngle(anchorAngleY + (anchorX - event.getSceneX()));
        });

        scene.setOnScroll(event -> group.translateZProperty().set(group.getTranslateZ() + event.getDeltaY()));
    }
}