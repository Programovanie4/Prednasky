package III_Objects;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("Duplicates")
public class Projectiles4 extends Application {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Group group = new Group();
    private Scene scene = new Scene(group, WIDTH, HEIGHT);
    private Camera camera = new PerspectiveCamera();

    private Box player = new Box(100, 100, 100);
    private final int DEFAULT_Y = HEIGHT - HEIGHT/6;
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        scene.setCamera(camera);

        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                shoot();
            }
        });

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(20), o -> update()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        primaryStage.setScene(scene);
        primaryStage.show();

        player = new Box(100, 100, 100);
        player.setTranslateX(WIDTH / 2);
        player.setTranslateY(DEFAULT_Y);
        player.setMaterial(new PhongMaterial(Color.BLUE));
        group.getChildren().add(player);
    }

    private void shoot() {
        Sphere projectile = new Sphere(10);
        projectile.setTranslateX(WIDTH / 2);
        projectile.setTranslateY(DEFAULT_Y);
        projectile.setMaterial(new PhongMaterial(Color.RED));
        group.getChildren().add(projectile);
    }

    private void update() {
        Set<Node> toRemove = new HashSet<>();
        for (Node n : group.getChildren()) {
            if (n instanceof Sphere) {
                Sphere s = (Sphere) n;
                if (s.getTranslateY() < -20) {
                    toRemove.add(n);
                } else {
                    s.setTranslateY(s.getTranslateY() - 5);
                }
            }
        }
        group.getChildren().removeAll(toRemove);
    }
}