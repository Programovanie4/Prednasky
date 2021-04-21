package VI_Interaction;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Corners extends Application {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Group group = new Group();
    private Scene scene = new Scene(group, WIDTH, HEIGHT, true);
    private Camera camera = new PerspectiveCamera(true);
    private Box box = new Box(10, 10, 10);

    @Override
    public void start(Stage primaryStage) {
        scene.setCamera(camera);
        scene.setFill(Color.GRAY);
        camera.setTranslateZ(-50);

        setKeyEvents();
        boxWithCorners();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void boxWithCorners() {
        int x1 = (int) (box.getTranslateX() - box.getWidth()/2);
        int y1 = (int) (box.getTranslateY() - box.getHeight()/2);
        int z1 = (int) (box.getTranslateZ() - box.getDepth()/2);

        int x2 = (int) (box.getTranslateX() + box.getWidth()/2);
        int y2 = (int) (box.getTranslateY() + box.getHeight()/2);
        int z2 = (int) (box.getTranslateZ() + box.getDepth()/2);

        Sphere s1 = new Sphere(1);
        s1.setTranslateX(x1);
        s1.setTranslateY(y1);
        s1.setTranslateZ(z1);

        Sphere s2 = new Sphere(1);
        s2.setTranslateX(x2);
        s2.setTranslateY(y2);
        s2.setTranslateZ(z2);

        PhongMaterial material = new PhongMaterial(Color.RED);
        s2.setMaterial(material);
        s1.setMaterial(material);
        group.getChildren().addAll(box, s1, s2);
    }

    private void setKeyEvents() {
        scene.setOnKeyPressed(event -> {
            Rotate r = new Rotate();
            switch (event.getCode()) {
                case W:
                    r.setAxis(Rotate.X_AXIS);
                    r.setAngle(-10);
                    break;
                case S:
                    r.setAxis(Rotate.X_AXIS);
                    r.setAngle(10);
                    break;
                case A:
                    r.setAxis(Rotate.Y_AXIS);
                    r.setAngle(10);
                    break;
                case D:
                    r.setAxis(Rotate.Y_AXIS);
                    r.setAngle(-10);
                    break;
                default:
                    return;
            }
            group.getTransforms().add(r);
        });
    }
	public static void main(String[] args) {
		launch(args);
	}

}