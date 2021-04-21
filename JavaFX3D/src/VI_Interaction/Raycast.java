package VI_Interaction;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Raycast extends Application {
    Group group = new Group();
    Camera camera = new PerspectiveCamera(true);
    Scene scene = new Scene(group, 1024, 768, true);
    PhongMaterial boxMaterial = new PhongMaterial(Color.LIGHTGREEN);
    PhongMaterial sphereMaterial = new PhongMaterial(Color.RED);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        scene.setFill(Color.LIGHTBLUE);
        scene.setCamera(camera);
        camera.setTranslateX(0);
        camera.setTranslateY(-1000);
        camera.setTranslateZ(-500);
        camera.setFarClip(2000);

        camera.setRotationAxis(Rotate.X_AXIS);
        camera.setRotate(-65);
        createFloor();
        Box box = new Box(50, 50,50);
        box.setTranslateY(-800);
        box.setTranslateZ(-400);
        box.setMaterial(boxMaterial);
        group.getChildren().add(box);

        scene.setOnMousePressed((MouseEvent me) -> {
            Sphere s = new Sphere(2);
            s.setTranslateX(me.getX());
            s.setTranslateY(me.getY());
            s.setTranslateZ(me.getZ());
            s.setMaterial(sphereMaterial);
            group.getChildren().add(s);
        });

        primaryStage.setTitle("Raycasting");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void createFloor() {
        Box box = new Box(600,1,600);
        box.setMaterial(new PhongMaterial(Color.WHEAT));
        group.getChildren().add(box);
    }

}
