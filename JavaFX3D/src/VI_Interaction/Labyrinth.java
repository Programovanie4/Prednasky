package VI_Interaction;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Labyrinth extends Application {
    Group group = new Group();
    Camera camera = new PerspectiveCamera(true);
    Scene scene = new Scene(group, 960, 540, true);
    PhongMaterial boxMaterial = new PhongMaterial(Color.LIGHTGREEN);
    Player player = new Player(this);

    private char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', '.', '.', '.', '#'},
            {'#', '.', '#', '.', '#'},
            {'#', '.', '#', '#', '#'},
            {'#', '.', '.', '.', '#'},
            {'#', '.', '#', '.', '#'},
            {'#', '#', '#', '#', '#'}
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        scene.setFill(Color.LIGHTBLUE);
        scene.setCamera(camera);
        camera.setTranslateX(50);
        camera.setTranslateZ(50);
        camera.setFarClip(500);

        createFloor();
        loadMap();
        setEvents();
        player.start();

        primaryStage.setTitle("Labyrinth");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setEvents() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case R:
                    camera.translateYProperty().set(camera.getTranslateY() - 2);
                    break;
                case F:
                    camera.translateYProperty().set(camera.getTranslateY() + 2);
                    break;
                default:
                    player.setDirection(event);
                    break;
            }
        });

        scene.setOnKeyReleased(event -> player.stopMovement());

        camera.getTransforms().add(new Rotate(0, 0,0,0, Rotate.Y_AXIS));
        camera.getTransforms().add(new Rotate(0, 0,0,0, Rotate.X_AXIS));

        scene.setOnMousePressed(event -> {
            player.mousePressed = true;
            player.lastMouseX = event.getSceneX();
            player.lastMouseY = event.getSceneY();
        });

        scene.setOnMouseReleased(event -> player.mousePressed = false);

        scene.setOnMouseDragged(event -> player.mouseDragged(event));
    }

    private void loadMap() {
        int Z = 0;
        for (int i = map.length - 1; i >= 0; i--) {
            int X = 0;
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '#') {
                    createBox(X, Z);
                }
                X += 51;
            }
            Z += 51;
        }
    }

    private void createBox(int X, int Z) {
        Box box = new Box(50, 50,50);
        box.setTranslateX(X);
        box.setTranslateZ(Z);
        box.setMaterial(boxMaterial);
        group.getChildren().add(box);
    }

    private void createFloor() {
        Box box = new Box(1000,1,1000);
        box.setMaterial(new PhongMaterial(Color.WHEAT));
        box.setTranslateY(15);
        group.getChildren().add(box);
    }

    boolean checkCameraCollision() {
        for (Node n: group.getChildren()) {
            if (n instanceof Box) {
                Box b = (Box) n;
                if (b.getBoundsInParent().intersects(camera.getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Player extends Thread {
    private enum Dir {FORWARD, BACKWARD, STOP}

    private final float MOUSE_SENSITIVITY = 0.1f;
    private Dir direction = Dir.STOP;
    private Labyrinth lab;

    double angle = 0;
    boolean mousePressed = false;
    boolean alive = true;
    double lastMouseX;
    double lastMouseY;

    Player(Labyrinth lab) {
        this.lab = lab;
    }

    @Override
    public void run() {
        while (alive) {
            try {
                movement();
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void setDirection(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                direction = Dir.FORWARD;
                break;
            case S:
                direction = Dir.BACKWARD;
                break;
        }
    }

    void mouseDragged(MouseEvent event) {
        if (mousePressed) {
            var transforms = new ArrayList<>(lab.camera.getTransforms());
            lab.camera.getTransforms().clear();
            for (Transform t: transforms) {
                Rotate rotate = (Rotate) t;
                if (rotate.getAxis().equals(Rotate.Y_AXIS)) {
                    rotate.setAngle(rotate.getAngle() + (event.getSceneX() - lastMouseX) * MOUSE_SENSITIVITY);
                    angle = rotate.getAngle();
                } else {
                    rotate.setAngle(rotate.getAngle() - (event.getSceneY() - lastMouseY) * MOUSE_SENSITIVITY);
                }
                lab.camera.getTransforms().add(rotate);
            }
        }
        lastMouseX = event.getSceneX();
        lastMouseY = event.getSceneY();
    }

    void stopMovement() {
        direction = Dir.STOP;
    }

    private void movement() {
        if (direction == Dir.STOP) return;
        double dx = Math.sin(Math.toRadians(angle));
        double dz = Math.cos(Math.toRadians(angle));
        double oldZ = lab.camera.getTranslateZ();
        double oldX = lab.camera.getTranslateX();
        switch (direction) {
            case FORWARD:
                lab.camera.translateZProperty().set(lab.camera.getTranslateZ() + dz);
                lab.camera.translateXProperty().set(lab.camera.getTranslateX() + dx);
                break;
            case BACKWARD:
                lab.camera.translateZProperty().set(lab.camera.getTranslateZ() - dz);
                lab.camera.translateXProperty().set(lab.camera.getTranslateX() - dx);
                break;
        }
        if (lab.checkCameraCollision()) {
            lab.camera.translateZProperty().set(oldZ);
            lab.camera.translateXProperty().set(oldX);
        }
    }
}