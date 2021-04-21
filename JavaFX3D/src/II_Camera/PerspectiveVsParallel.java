package II_Camera;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class PerspectiveVsParallel extends Application {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Group group = new Group();
    //private Camera camera = new ParallelCamera();
    private Camera camera = new PerspectiveCamera();
    private Scene scene = new Scene(group, WIDTH, HEIGHT, true);

    private char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', '.', '.', '.', '#'},
            {'#', '.', '#', '.', '#'},
            {'#', '.', '.', '.', '#'},
            {'#', '#', '#', '#', '#'},
    };
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        scene.setFill(Color.LIGHTBLUE);
        scene.setCamera(camera);

        camera.setTranslateY(-HEIGHT/2);
        camera.setTranslateX(-WIDTH/4);
        camera.setTranslateZ(WIDTH/3);
        camera.setRotationAxis(Rotate.X_AXIS);
        camera.setRotate(-25);

        loadMap();
        setKeyEvents();

        primaryStage.setTitle("PerspectiveVsParallel");
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
            }
        });
    }

    private void loadMap() {
        int Z = 0;
        for (int i = map.length - 1; i >= 0; i--) {
            int X = 0;
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '#') {
                    createBox(X, Z);
                }
                X += 70;
            }
            Z += 70;
        }
    }

    private void createBox(int X, int Z) {
        Box box = new Box(60, 60,60);
        box.setTranslateX(X);
        box.setTranslateZ(Z);
        group.getChildren().add(box);
    }
}