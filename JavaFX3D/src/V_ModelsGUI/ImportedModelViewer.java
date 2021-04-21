package V_ModelsGUI;

import com.interactivemesh.jfx.importer.tds.TdsModelImporter;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class ImportedModelViewer extends Application {
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        Group group = new Group();

        TdsModelImporter tdsImporter = new TdsModelImporter();
        tdsImporter.read("hst.3ds");
        Node[] rootNodes = tdsImporter.getImport();

        group.getChildren().addAll(rootNodes);

        Camera camera = new PerspectiveCamera(true);
        camera.setFarClip(5000);
        camera.setTranslateZ(-200);

        Scene scene = new Scene(group, 1280, 720, true);
        scene.setFill(Color.BLACK);
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