package I_Basics;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class CameraAtOrigin extends Application {
    private Group group = new Group();
    private Scene scene = new Scene(group, 800, 600);
    private Camera camera = new PerspectiveCamera(true);
    private Box box = new Box(20, 20, 20);

    @Override
    public void start(Stage primaryStage) {
        box.setRotationAxis(Rotate.Y_AXIS);
        box.setTranslateZ(60);
        box.setRotate(-20);
        group.getChildren().add(box);
        scene.setCamera(camera);
        scene.setFill(Color.GRAY);
        camera.setFarClip(1000);

        setKeyEvents();
        primaryStage.setTitle("3DTemplate");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setKeyEvents() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    camera.setTranslateZ(camera.getTranslateZ() + 10);
                    break;
                case S:
                    camera.setTranslateZ(camera.getTranslateZ() - 10);
                    break;
                case A:
                    camera.setTranslateX(camera.getTranslateX() - 10);
                    break;
                case D:
                    camera.setTranslateX(camera.getTranslateX() + 10);
                    break;
                case R:
                    camera.setTranslateY(camera.getTranslateY() - 10);
                    break;
                case F:
                    camera.setTranslateY(camera.getTranslateY() + 10);
                    break;
                case Q:
                    box.setRotate(box.getRotate() + 10);
                    break;
                case E:
                    box.setRotate(box.getRotate() - 10);
                    break;
            }
        });
    }
	public static void main(String[] args) {
		launch(args);
	}

}