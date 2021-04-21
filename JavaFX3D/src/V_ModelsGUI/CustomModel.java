package V_ModelsGUI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class CustomModel extends Group {
    int gridSize = 16;
    int pieceSize = 8;
    Map<Integer, PhongMaterial> tiles = new HashMap<>();

    public CustomModel() {
        prepareTiles();
        generateModel();
        applyAnimation();
    }

    public void prepareTiles() {
        PhongMaterial lightGray = new PhongMaterial(Color.LIGHTGRAY);
        PhongMaterial darkGray = new PhongMaterial(Color.DIMGRAY);
        PhongMaterial middleGray = new PhongMaterial(Color.GRAY);
        PhongMaterial lightBrown = new PhongMaterial(Color.PERU);
        PhongMaterial darkBrown = new PhongMaterial(Color.SADDLEBROWN);

        tiles.put(13, darkGray);
        tiles.put(14, darkGray);
        tiles.put(15, darkGray);

        tiles.put(28, darkGray);
        tiles.put(29, lightGray);
        tiles.put(30, lightGray);
        tiles.put(31, darkGray);

        tiles.put(43, darkGray);
        tiles.put(44, lightGray);
        tiles.put(45, lightGray);
        tiles.put(46, lightGray);
        tiles.put(47, darkGray);

        tiles.put(58, darkGray);
        tiles.put(59, lightGray);
        tiles.put(60, lightGray);
        tiles.put(61, lightGray);
        tiles.put(62, darkGray);

        tiles.put(73, darkGray);
        tiles.put(74, lightGray);
        tiles.put(75, lightGray);
        tiles.put(76, lightGray);
        tiles.put(77, darkGray);

        tiles.put(83, darkGray);
        tiles.put(84, darkGray);
        tiles.put(88, darkGray);
        tiles.put(89, lightGray);
        tiles.put(90, lightGray);
        tiles.put(91, lightGray);
        tiles.put(92, darkGray);

        tiles.put(99, darkGray);
        tiles.put(100, middleGray);
        tiles.put(101, darkGray);
        tiles.put(103, darkGray);
        tiles.put(104, lightGray);
        tiles.put(105, lightGray);
        tiles.put(106, lightGray);
        tiles.put(107, darkGray);

        tiles.put(116, darkGray);
        tiles.put(117, middleGray);
        tiles.put(118, darkGray);
        tiles.put(119, lightGray);
        tiles.put(120, lightGray);
        tiles.put(121, lightGray);
        tiles.put(122, darkGray);

        tiles.put(132, darkGray);
        tiles.put(133, middleGray);
        tiles.put(134, darkGray);
        tiles.put(135, lightGray);
        tiles.put(136, lightGray);
        tiles.put(137, darkGray);

        tiles.put(149, darkGray);
        tiles.put(150, middleGray);
        tiles.put(151, darkGray);
        tiles.put(152, darkGray);

        tiles.put(164, darkBrown);
        tiles.put(165, lightBrown);
        tiles.put(166, darkGray);
        tiles.put(167, middleGray);
        tiles.put(168, middleGray);
        tiles.put(169, darkGray);

        tiles.put(179, darkBrown);
        tiles.put(180, lightBrown);
        tiles.put(181, darkBrown);
        tiles.put(183, darkGray);
        tiles.put(184, darkGray);
        tiles.put(185, middleGray);
        tiles.put(186, darkGray);

        tiles.put(193, darkGray);
        tiles.put(194, darkGray);
        tiles.put(195, lightBrown);
        tiles.put(196, darkBrown);
        tiles.put(201, darkGray);
        tiles.put(202, darkGray);

        tiles.put(209, darkGray);
        tiles.put(210, middleGray);
        tiles.put(211, darkGray);

        tiles.put(225, darkGray);
        tiles.put(226, darkGray);
        tiles.put(227, darkGray);
    }

    private void generateModel() {
        for (Integer tileNumber : tiles.keySet()) {
            int x = calculateCol(tileNumber) * pieceSize - ((gridSize/2) * pieceSize);
            int y = calculateRow(tileNumber) * pieceSize - ((gridSize/2) * pieceSize);

            Box b = new Box(pieceSize, pieceSize, pieceSize);
            b.setTranslateX(x);
            b.setTranslateY(y);
            b.setMaterial(tiles.get(tileNumber));
            getChildren().add(b);
        }
    }

    private int calculateCol(int num) {
        return num % gridSize;
    }

    private int calculateRow(int num) {
        return num / gridSize;
    }

    private void applyAnimation() {
        setRotationAxis(Rotate.Y_AXIS);
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(this.rotateProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(10),
                        new KeyValue(this.rotateProperty(), 360)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}