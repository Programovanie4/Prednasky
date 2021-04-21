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

@SuppressWarnings("Duplicates")
public class Projectiles3 extends Application {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Group group = new Group();
    private Scene scene = new Scene(group, WIDTH, HEIGHT);
    private Camera camera = new PerspectiveCamera();

    private Box player = new Box(100, 100, 100);
    private Sphere projectile = new Sphere(10);

    private final int DEFAULT_Y = HEIGHT - HEIGHT/6;
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
        scene.setCamera(camera);

        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE) && projectile.getTranslateY() == DEFAULT_Y) {
                projectile.setTranslateY(projectile.getTranslateY() - 5);
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

        projectile = new Sphere(10);
        projectile.setTranslateX(WIDTH / 2);
        projectile.setTranslateY(DEFAULT_Y);
        projectile.setMaterial(new PhongMaterial(Color.RED));
        group.getChildren().add(projectile);
    }

    private void update() {
        if (projectile.getTranslateY() < -20) {
            projectile.setTranslateY(DEFAULT_Y);
        }
        if (projectile.getTranslateY() != DEFAULT_Y) {
            projectile.setTranslateY(projectile.getTranslateY() - 5);
        }
    }
}