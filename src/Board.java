import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;

public class Board extends Node {
    private GridPane pane;
    private Tile[][] surface;

    public Board() {
        pane = new GridPane();
        surface = new Tile[4][4];
    }

    public Tile[][] getSurface() {
        return surface;
    }


    public GridPane getPane() {
        return pane;
    }

    public void placeTileAndAppendToPane(Tile tile) {
        int row, colomn;
        row = tile.getRow(tile.getTileId());
        colomn = tile.getColumn(tile.getTileId());
        surface[row][colomn] = tile;
        pane.add(tile, colomn, row);
    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
