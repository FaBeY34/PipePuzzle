import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Level {
    private int levelNo;

    public int getLevelNo() {
        return levelNo;
    }

    //private Tile draggingTile;
    //private Tile nextTile;
    //private Tile starterTile;
    //private Tile currentTile;
    private ArrayList<Tile> tiles;
    private Circle circle;
    private Path path;
    private static int numberOfMoves;

    public static int getNumberOfMoves() {
        return numberOfMoves;
    }

    public Level() {
        this(1);
        circle = new Circle();
        path = new Path();

    }

    public Level(int levelNo) {
        this.levelNo = levelNo;
        circle = new Circle();
        path = new Path();
    }

    public Level(ArrayList<Tile> tiles, int levelNo) {
        this.tiles = tiles;
        this.levelNo = levelNo;
        circle = new Circle();
        path = new Path();
    }


}




