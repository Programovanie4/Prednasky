package IV_Enhancements;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Enhancements extends Application {
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        Box box = prepareBox();

        Group group = new Group();
        group.getChildren().add(box);
        //group.getChildren().addAll(createLights());

        Camera camera = new PerspectiveCamera(true);
        camera.setFarClip(1000);
        camera.setTranslateZ(-200);

        Scene scene = new Scene(group, 1200, 600, true);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        initMouseControl(group, scene);

        primaryStage.setTitle("IV_Enhancements");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Node[] createLights() {
        PointLight pointLight = new PointLight();
        pointLight.setColor(Color.RED);
        pointLight.getTransforms().add(new Translate(0, -50, 0));
        pointLight.setRotationAxis(Rotate.X_AXIS);

        Sphere sphere = new Sphere(2);
        sphere.getTransforms().setAll(pointLight.getTransforms());
        sphere.rotateProperty().bind(pointLight.rotateProperty());
        sphere.rotationAxisProperty().bind(pointLight.rotationAxisProperty());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                pointLight.setRotate(pointLight.getRotate() + 1);
            }
        };
        timer.start();

        AmbientLight ambientLight = new AmbientLight(Color.BLUE);

        return new Node[]{pointLight, ambientLight, sphere};
    }

    private Box prepareBox() {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image("file:wood.jpg"));
        //material.setBumpMap(new Image("file:bump.jpg"));

        Box box = new Box(100, 20, 50);
        box.setMaterial(material);
        return box;
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