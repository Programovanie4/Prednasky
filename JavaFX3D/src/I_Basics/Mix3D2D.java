package I_Basics;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Mix3D2D extends Application {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Group group = new Group();
    private Scene scene = new Scene(group, WIDTH, HEIGHT);
    private Camera camera = new PerspectiveCamera();
    private Box box = new Box(100, 100, 100);
    private Circle sphere = new Circle(50);

	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        box.setTranslateX(WIDTH / 2);
        box.setTranslateY(HEIGHT / 2 - 50);
        box.setRotationAxis(Rotate.Y_AXIS);
        sphere.setTranslateX(WIDTH / 2);
        sphere.setTranslateY(HEIGHT / 2 + 50);
        sphere.setFill(Color.WHITE);

        group.getChildren().addAll(sphere, box);
        scene.setCamera(camera);
        scene.setFill(Color.GRAY);

        setKeyEvents();
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
}