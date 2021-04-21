package I_Basics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Circle2DPane extends Application {

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(50);

        //circle.setCenterX(200);

        Pane pane = new Pane();
        pane.getChildren().add(circle);

        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	public static void main(String[] args) {
		launch(args);
	}

}
