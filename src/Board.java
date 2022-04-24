import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;

public class Board {
    private GridPane pane;
    private Tile[][] surface;

    public Board() {
        pane = new GridPane();
        surface = new Tile[4][4];
    }

    public Tile[][] getSurface() {
        return surface;
    }

    public void addImages(ImageView imageView, Tile newtile) {
        int row = newtile.getRow(newtile.getTileId());
        int colomn = newtile.getColumn(newtile.getTileId());
        pane.add(imageView, colomn, row);
        pane.setAlignment(Pos.CENTER);
        pane.add(newtile, row, colomn);
    }

    public GridPane getPane() {
        return pane;
    }

    public void placeTile(Tile tile) {
        int row, colomn;
        row = tile.getRow(tile.getTileId());
        colomn = tile.getColumn(tile.getTileId());
        surface[row][colomn] = tile;
    }


}
